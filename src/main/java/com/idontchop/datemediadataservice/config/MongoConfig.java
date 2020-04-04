package com.idontchop.datemediadataservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig {
	
	@Value ("${spring.datasource.mongo.database}")
	private String dbName;
	
	//@Value ("${spring.application.type}")
	//private String matchType;
  
    @Bean
    public MongoClient mongo() {
        return MongoClients.create();
    }
 
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), dbName);
    }
}
