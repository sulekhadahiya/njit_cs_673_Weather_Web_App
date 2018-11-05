/*
 * Copyright (c) 2018.
 */

package weatherapp.controllers.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import weatherapp.domain.dbmodel.LocationPhoto;
import weatherapp.domain.restmodel.LocationPhotoRest;
import weatherapp.services.LocationPhotoService;

import java.util.List;
import java.util.Objects;

/**
 * @author sulekha
 * njit_cs_673_Weather_Web_App, 2018
 */
@RestController
@RequestMapping(value = "/locations-photos", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class LocationPhotoController {
    private Logger logger = LoggerFactory.getLogger(LocationPhotoController.class);

    private LocationPhotoService locationPhotoService;

    public LocationPhotoController(LocationPhotoService locationPhotoService) {
        this.locationPhotoService = locationPhotoService;
    }

    @PostMapping(value = "/upload-location-photo", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public LocationPhoto uploadLocationPhoto(LocationPhotoRest locationPhotoRest) throws Exception {
        logger.info(locationPhotoRest.toString());
        verifyLocationCoOrdinates(locationPhotoRest);
        LocationPhoto locationPhoto = this.locationPhotoService.saveLocationPhoto(locationPhotoRest);
        return locationPhoto;
    }


    @GetMapping(value = "/get-location-photos")
    public List<LocationPhoto> getLocationPhoto(@RequestParam("searchquery") String searchQuery) {
        logger.info(searchQuery);
        List<LocationPhoto> locationPhotos = this.locationPhotoService.getLocationPhoto(searchQuery);
        return locationPhotos;
    }

    @DeleteMapping(value = "/delete-location-photo/{photoId}")
    public LocationPhoto deleteLocationPhoto(@PathVariable(value = "photoId") String photoId) {
        logger.info(photoId);
        return this.locationPhotoService.deleteLocationPhoto(photoId);
    }


    void verifyLocationCoOrdinates(LocationPhotoRest locationPhotoRest) {
        if (Objects.isNull(locationPhotoRest.getLongitude()) ||
                locationPhotoRest.getLongitude().isEmpty()) {
            if (!Objects.isNull(locationPhotoRest.getLatitude()) ||
                    !locationPhotoRest.getLatitude().isEmpty()) {
                throw new RuntimeException("Longitude is not defined, but Latitude is defined");
            }
        }

        if (!Objects.isNull(locationPhotoRest.getLongitude()) ||
                !locationPhotoRest.getLongitude().isEmpty()) {
            if (Objects.isNull(locationPhotoRest.getLatitude()) ||
                    locationPhotoRest.getLatitude().isEmpty()) {
                throw new RuntimeException("Latitude is not defined, but Longitude is defined");
            }
        }
    }
}
