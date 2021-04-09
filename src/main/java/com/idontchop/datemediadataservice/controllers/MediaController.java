package com.idontchop.datemediadataservice.controllers;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.idontchop.datemediadataservice.entities.MediaData;
import com.idontchop.datemediadataservice.services.JwtTokenService;
import com.idontchop.datemediadataservice.services.MediadataService;

/**
 * Returns image and video resources.
 * 
 * @author micro
 *
 */
@RestController
@CrossOrigin
public class MediaController {
	
	@Autowired
	MediadataService mediadataService;


	@CrossOrigin("http://localhost")
	@GetMapping ("/image/{id}")
	public ResponseEntity<byte[]> downloadImage (
			@PathVariable ( name = "id", required = true) String id,
			@RequestParam ( name = "token", required = false) String token) {
		
		try {
			MediaData mediaData = mediadataService.getMedia(id);
			if (mediaData.isHidden()) {
				// parse token if supplied
				if ( ! JwtTokenService.getAuthenticationFromString(token).equals(mediaData.getId())) 
					throw new IOException("hidden"); // not athenticated
			} 
			return ResponseEntity.ok()
					.contentType(MediaType.parseMediaType("image/jpeg"))
					.body( mediaData.getData().getData() );
		} catch ( NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} catch ( IOException e ) {
			throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT);
		} catch ( NullPointerException e ) {
			throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT);
		}
		
	}
	
}
