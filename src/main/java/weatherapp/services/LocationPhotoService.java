/*
 * Copyright (c) 2018.
 */

package weatherapp.services;

import weatherapp.domain.dbmodel.LocationPhoto;
import weatherapp.domain.restmodel.LocationPhotoRest;

import java.io.IOException;
import java.util.List;

/**
 * @author sulekha
 * njit_cs_673_Weather_Web_App, 2018
 */
public interface LocationPhotoService {
    LocationPhoto saveLocationPhoto(LocationPhotoRest locationPhotoRest) throws IOException;

    List<LocationPhoto> getLocationPhoto(String searchQuery);

    LocationPhoto deleteLocationPhoto(String locationPhotoId);

    byte[] retrieveLocationPhotoFromS3(String photoName) throws IOException;

}
