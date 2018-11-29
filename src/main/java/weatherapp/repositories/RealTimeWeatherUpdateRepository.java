/*
 * Copyright (c) 2018.
 */

package weatherapp.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import weatherapp.domain.dbmodel.RealTimeWeatherUpdate;

import java.util.List;

@Repository
public interface RealTimeWeatherUpdateRepository extends MongoRepository<RealTimeWeatherUpdate, String> {
    List<RealTimeWeatherUpdate> findByCity(String city);

    List<RealTimeWeatherUpdate> findBy(TextCriteria criteria, Sort sort);

}
