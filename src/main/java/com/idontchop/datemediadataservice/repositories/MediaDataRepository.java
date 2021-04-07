package com.idontchop.datemediadataservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.idontchop.datemediadataservice.entities.MediaData;

public interface MediaDataRepository extends MongoRepository <MediaData, String>{

}
