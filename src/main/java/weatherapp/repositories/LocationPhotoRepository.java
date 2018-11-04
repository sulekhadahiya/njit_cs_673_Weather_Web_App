/*
 * Copyright (c) 2018.
 */

package weatherapp.repositories;

/**
 * @author sulekha
 * njit_cs_673_Weather_Web_App, 2018
 */

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import weatherapp.domain.dbmodel.LocationPhoto;

import java.util.List;

@Repository
public interface LocationPhotoRepository extends MongoRepository<LocationPhoto, String> {

    // Execute a full-text search and define sorting dynamically
    List<LocationPhoto> findAllBy(TextCriteria criteria, Sort sort);
}
