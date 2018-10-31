/*
 * Copyright (c) 2018.
 */

package weatherapp.repositories;

/**
 * @author sulekha
 * njit_cs_673_Weather_Web_App, 2018
 */

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import weatherapp.domain.dbmodel.UserProfile;

@Repository
public interface LocationPhotoRepository extends MongoRepository<UserProfile, String> {

}
