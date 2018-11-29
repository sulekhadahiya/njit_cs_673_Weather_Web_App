/*
 * Copyright (c) 2018.
 */

package weatherapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import weatherapp.domain.dbmodel.security.ApplicationUser;

/**
 * @author sulekha
 * njit_cs_673_Weather_Web_App, 2018
 */
public interface ApplicationUserRepository extends MongoRepository<ApplicationUser, String> {
    ApplicationUser findByUsername(String userName);
}
