package com.idontchop.datemediadataservice.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.idontchop.datemediadataservice.entities.ErrorMessage;
import com.idontchop.datemediadataservice.services.ErrorMessageService;

@RestController
public class ErrorMessageController {
	
	@Autowired
	ErrorMessageService emService;
	
	@PostMapping("/errorMessage")
	public ErrorMessage saveNew (@RequestBody ErrorMessage em, Principal principal) {
		em.setName(principal.getName());
		return emService.save(em);
	}

	@GetMapping("/errorMessage")
	public List<ErrorMessage> latestErrorMessages () {
		return emService.findRecent();
	}
}
