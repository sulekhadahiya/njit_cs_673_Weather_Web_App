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

import java.util.List;

@Repository
public interface UserProfileRepository extends MongoRepository<UserProfile, String> {
    List<UserProfile> findByNameFirstName(String firstName);

    UserProfile findByEmail(String email);

    List<UserProfile> findByNameLastName(String lastName);
}
