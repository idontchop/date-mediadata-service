package com.idontchop.datemediadataservice.services;

import java.io.IOException;
import java.util.Date;
import java.util.NoSuchElementException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.idontchop.datemediadataservice.entities.MediaData;
import com.idontchop.datemediadataservice.repositories.MediaDataRepository;

@Service
public class MediadataService {
	
	@Autowired
	private MediaDataRepository mediaDataRepository;
	
	public MediaData addMedia ( MultipartFile file, String owner ) throws IOException {
		
		MediaData newData = new MediaData( 
				new Binary ( BsonBinarySubType.BINARY, file.getBytes()), owner);
		
		newData = mediaDataRepository.insert(newData);
		
		return newData;
	}
	
	public MediaData getMedia ( String id ) throws NoSuchElementException {
		return mediaDataRepository.findById(id).orElseThrow();
	}

	/**
	 * Allows a user to replace their pic. This will not allow a change of owner.
	 * 
	 * @param id
	 * @param file
	 * @param owner
	 * @return
	 * @throws IOException
	 * @throws NoSuchElementException
	 */
	public MediaData replaceMedia ( String id, MultipartFile file, String owner ) throws IOException, NoSuchElementException {
		
		MediaData replaceData = mediaDataRepository.findById(id).orElseThrow();
		
		if (!replaceData.getOwner().equals(owner)) throw new NoSuchElementException("Owner does not match ID");
		
		replaceData.setData( new Binary ( BsonBinarySubType.BINARY, file.getBytes()));
		replaceData.setCreated(new Date());
		
		replaceData = mediaDataRepository.save(replaceData);
		
		return replaceData;
	}
	
	public byte[] getMediaData (String id ) throws NoSuchElementException {
		
		MediaData mediaData = mediaDataRepository.findById(id).orElseThrow();
		
		return mediaData.getData().getData();
	}
}
