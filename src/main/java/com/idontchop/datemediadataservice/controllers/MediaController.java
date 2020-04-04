package com.idontchop.datemediadataservice.controllers;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.idontchop.datemediadataservice.services.MediadataService;

/**
 * Returns image and video resources.
 * 
 * @author micro
 *
 */
@RestController
public class MediaController {
	
	@Autowired
	MediadataService mediadataService;

	@GetMapping ("/image/{id}")
	public ResponseEntity<byte[]> downloadImage (@PathVariable ( name = "id", required = true) String id ) {
		
		try {
			return ResponseEntity.ok()
					.contentType(MediaType.parseMediaType("image/jpeg"))
					.body( mediadataService.getMediaData(id));
		} catch ( NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}
	
}
