package com.idontchop.datemediadataservice.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.idontchop.datemediadataservice.entities.ErrorMessage;
import com.idontchop.datemediadataservice.entities.MediaData;

public interface ErrorMessageRepository extends MongoRepository <ErrorMessage, String> {
	
	public List<ErrorMessage> findByCreatedAfter(LocalDateTime created);
	public List<ErrorMessage> findByName(String name);

}
