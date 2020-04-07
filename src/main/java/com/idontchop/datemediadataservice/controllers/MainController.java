package com.idontchop.datemediadataservice.controllers;

import java.io.IOException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.idontchop.datemediadataservice.dtos.RestMessage;
import com.idontchop.datemediadataservice.entities.MediaData;
import com.idontchop.datemediadataservice.services.MediadataService;

/**
 * Data service for user media. 
 * 
 * This service is designed to only store and retrieve data. The ID of the data
 * must be known. Searches for media are not allowed here. That is the function of
 * media-service.
 * 
 * Media-service uses these endpoints to save the data and then store the ID to its
 * relational database.
 * 
 * @author micro
 *
 */
@RestController
public class MainController {
	
	@Value ("${server.port}")
	private String serverPort;
	
	@Value("${spring.application.name}")
	private String appName;
	
	@Autowired
	MediadataService mediaDataService;
	
	@PostMapping ( "/media" )
	public MediaData newMedia (
			@RequestParam("file") MultipartFile file,
			@RequestParam( value = "owner", required=true) String owner) throws IOException {
		
		if (file.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		
		return mediaDataService.addMedia(file, owner);
		
	}
	
	@PutMapping ( "/media" )
	public MediaData replaceMedia (
			@RequestParam("file") MultipartFile file,
			@RequestParam (value = "owner", required=true) String owner,
			@RequestParam (value = "id", required=true) String id) throws IOException, NoSuchElementException {
		
		if ( file.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		try {
			return mediaDataService.replaceMedia(id, file, owner);
		} catch ( NoSuchElementException e ) {
			throw new ResponseStatusException (HttpStatus.NOT_FOUND, "Owner does not match Id");
		}
	}
	
	@GetMapping ( "/media/{id}" )
	public MediaData getMedia (@PathVariable ( name = "id", required = true) String id) {
		
		try {
			return mediaDataService.getMedia(id);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping ( "/media/{id}" )
	public ResponseEntity<Void> deleteMedia (@PathVariable ( name = "id", required = true) String id ) {
		
		return mediaDataService.deleteMedia(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}

	@GetMapping("/helloWorld")
	public RestMessage helloWorld () {
		String serverAddress,serverHost;
		try {
			serverAddress = NetworkInterface.getNetworkInterfaces().nextElement()
					.getInetAddresses().nextElement().getHostAddress();
		} catch (SocketException e) {
			serverAddress = e.getMessage();
		}
		try {
			serverHost = NetworkInterface.getNetworkInterfaces().nextElement()
					.getInetAddresses().nextElement().getHostName();
		} catch (SocketException e) {
			serverHost = e.getMessage();
		}
		return RestMessage.build("Hello from " + appName)
				.add("service", appName)
				.add("host", serverHost)
				.add("address", serverAddress)
				.add("port", serverPort);
			
	}
}
