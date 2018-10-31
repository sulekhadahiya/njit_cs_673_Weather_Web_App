/*
 * Copyright (c) 2018.
 */

package weatherapp.controllers.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import weatherapp.services.LocationPhotoService;

/**
 * @author sulekha
 * njit_cs_673_Weather_Web_App, 2018
 */
@RequestMapping(value = "/locations-photos", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class PhotoController {
    private Logger logger = LoggerFactory.getLogger(PhotoController.class);

    private LocationPhotoService locationPhotoService;


}
