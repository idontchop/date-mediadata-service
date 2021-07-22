package com.idontchop.datemediadataservice.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idontchop.datemediadataservice.entities.ErrorMessage;
import com.idontchop.datemediadataservice.repositories.ErrorMessageRepository;

@Service
public class ErrorMessageService {
	
	@Autowired
	ErrorMessageRepository emRepository;
	
	public ErrorMessage save(ErrorMessage em) {
		return emRepository.save(em);
	}

	/**
	 * Finds error message created in last 24 hours
	 */
	public List<ErrorMessage> findRecent() {
		return emRepository.findByCreatedAfter(LocalDateTime.now().minusDays(1));
	}
}
