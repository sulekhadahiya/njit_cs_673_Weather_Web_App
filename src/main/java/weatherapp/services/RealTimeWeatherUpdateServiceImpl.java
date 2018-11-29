/*
 * Copyright (c) 2018.
 */

package weatherapp.services;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;
import weatherapp.domain.dbmodel.RealTimeWeatherUpdate;
import weatherapp.repositories.RealTimeWeatherUpdateRepository;

import java.util.List;

@Service
public class RealTimeWeatherUpdateServiceImpl implements RealTimeWeatherUpdateService {
    private RealTimeWeatherUpdateRepository realTimeWeatherUpdateRepository;

    public RealTimeWeatherUpdateServiceImpl(RealTimeWeatherUpdateRepository realTimeWeatherUpdateRepository) {
        this.realTimeWeatherUpdateRepository = realTimeWeatherUpdateRepository;
    }

    @Override
    public RealTimeWeatherUpdate saveRealTimeWeatherUpdate(RealTimeWeatherUpdate realTimeWeatherUpdate) {
        RealTimeWeatherUpdate saved = realTimeWeatherUpdateRepository.save(realTimeWeatherUpdate);
        return saved;
    }

    @Override
    public List<RealTimeWeatherUpdate> getRealTimeWeatherUpdate(String city) {
        Query query = new Query();
        TextCriteria textCriteria = new TextCriteria();
        textCriteria.matching(city);
        query.addCriteria(textCriteria);
        Sort sort = Sort.by("score");
        List<RealTimeWeatherUpdate> byCity = realTimeWeatherUpdateRepository.findBy(textCriteria, sort);
        return byCity;
    }
}
