/*
 * Copyright (c) 2018.
 */

package weatherapp.services;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;
import weatherapp.domain.dbmodel.LocationPhoto;
import weatherapp.domain.restmodel.LocationPhotoRest;
import weatherapp.repositories.LocationPhotoRepository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author sulekha
 * njit_cs_673_Weather_Web_App, 2018
 */
@Service
public class LocationPhotoServiceImpl implements LocationPhotoService {

    public static final String LOCATION_PHOTO_KEY_NAME = "fileName";
    private LocationPhotoRepository locationPhotoRepository;
    private AmazonS3Client amazonS3Client;
    private String bucketName = "sdpm2018/location-images";
    private MongoTemplate mongoTemplate;

    public LocationPhotoServiceImpl(LocationPhotoRepository locationPhotoRepository,
                                    MongoTemplate mongoTemplate,
                                    AmazonS3Client amazonS3Client) {
        this.locationPhotoRepository = locationPhotoRepository;
        this.mongoTemplate = mongoTemplate;
        this.amazonS3Client = amazonS3Client;
    }

    @Override
    public LocationPhoto saveLocationPhoto(LocationPhotoRest locationPhotoRest) throws IOException {
        //save the photo in aws first
        if (Objects.isNull(locationPhotoRest)) {
            return null;
        }

        if (Objects.isNull(locationPhotoRest.getLocationPhoto()) || locationPhotoRest.getLocationPhoto().isEmpty()) {
            return null;
        }

        byte[] bytes = locationPhotoRest.getLocationPhoto().getBytes();
        String key = String.format("%s_%s", UUID.nameUUIDFromBytes(bytes).toString(), locationPhotoRest.getLocationPhoto().getOriginalFilename());

        Map<String, String> locationPhotoUserMetaData = new HashMap<>();
        locationPhotoUserMetaData.put(LOCATION_PHOTO_KEY_NAME, key);

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(bytes.length);
        objectMetadata.setUserMetadata(locationPhotoUserMetaData);

        PutObjectRequest request = new PutObjectRequest(bucketName,
                key, new ByteArrayInputStream(bytes), objectMetadata);
        PutObjectResult putObjectResult = amazonS3Client.putObject(request);
        if (Objects.nonNull(putObjectResult) && Objects.nonNull(putObjectResult.getETag())) {
            //save the key with other details in mongodb
            LocationPhoto locationPhoto = locationPhotoRest.convertToLocationPhoto();
            locationPhoto.setId(key);
            locationPhoto.setSavedLocationPhotoKeyName(key);
            locationPhoto.seteTag(putObjectResult.getETag());
            LocationPhoto savedLocationPhotoDocument = locationPhotoRepository.save(locationPhoto);
            return savedLocationPhotoDocument;
        } else {
            return null;
        }
    }

    public List<LocationPhoto> getLocationPhoto(String searchKey) {
        Query query = new Query();
        TextCriteria textCriteria = new TextCriteria();
        textCriteria.matching(searchKey);
        query.addCriteria(textCriteria);
        Sort sort = Sort.by("score");
        List<LocationPhoto> locationPhotos = locationPhotoRepository.findAllBy(textCriteria, sort);
//        List<LocationPhoto> locationPhotos = mongoTemplate.find(query, LocationPhoto.class, "locationPhoto");
        return locationPhotos;
    }

    @Override
    public LocationPhoto deleteLocationPhoto(String photoName) {
        Optional<LocationPhoto> locationPhotoOptional = Optional.ofNullable(this.locationPhotoRepository.findBySavedLocationPhotoKeyName(photoName));

        if (locationPhotoOptional.isEmpty()) {
            throw new RuntimeException("This location photo does not exist.");
        } else {
            LocationPhoto locationPhoto = locationPhotoOptional.get();
            String savedLocationPhotoKeyName = locationPhoto.getSavedLocationPhotoKeyName();
            DeleteObjectRequest deleteRequest = new DeleteObjectRequest(bucketName, savedLocationPhotoKeyName);
            amazonS3Client.deleteObject(deleteRequest);
            locationPhotoRepository.deleteById(locationPhoto.getId());
        }
        return locationPhotoOptional.get();
    }

    @Override
    public byte[] retrieveLocationPhotoFromS3(String photoName) throws IOException {
        LocationPhoto savedLocationPhotoKeyName = locationPhotoRepository.findBySavedLocationPhotoKeyName(photoName);
        if (Objects.isNull(savedLocationPhotoKeyName)) {
            throw new RuntimeException(String.format("Location photo with this %s does not exist.", photoName));
        }
        GetObjectRequest locationPhotoRequest = new GetObjectRequest(bucketName, photoName);
        S3Object userProfilePhoto = amazonS3Client.getObject(locationPhotoRequest);
        S3ObjectInputStream objectContent = userProfilePhoto.getObjectContent();
        byte[] photoBytes = objectContent.readAllBytes();
        return photoBytes;
    }

}
