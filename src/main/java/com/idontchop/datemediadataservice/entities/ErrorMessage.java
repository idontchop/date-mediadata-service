package com.idontchop.datemediadataservice.entities;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ErrorMessage {
	
	@Id
	private String id;
	
	private String name;
	
	private LocalDateTime created = LocalDateTime.now();
	/*
	 *  Often a stringify from the front end.
	 */	
	private List<String> message = new ArrayList<>();
	
	private String note; 	// user submitted

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getMessage() {
		return message;
	}

	public void setMessage(List<String> message) {
		this.message = message;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}


	
	
}
