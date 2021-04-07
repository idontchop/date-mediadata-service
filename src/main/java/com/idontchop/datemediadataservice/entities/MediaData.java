package com.idontchop.datemediadataservice.entities;

import java.util.Date;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document ( collection = "media-data")
public class MediaData {
	
	public MediaData() {}
	
	@Id
	private String id;
	
	private Binary data;
	
	private String owner;
	
	private Date created = new Date();
	
	private boolean hidden = false;
	
	public MediaData ( Binary data, String owner ) {
		this.data = data;
		this.owner = owner;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonIgnore
	public Binary getData() {
		return data;
	}

	public void setData(Binary data) {
		this.data = data;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	
	

}
