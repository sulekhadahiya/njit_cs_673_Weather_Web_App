/*
 * Copyright (c) 2018.
 */

package weatherapp.services;

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

    public RealTimeWeatherUpdateServiceImpl() {

    }

    @Override
    public RealTimeWeatherUpdate saveRealTimeWeatherUpdate(RealTimeWeatherUpdate realTimeWeatherUpdate) {
        RealTimeWeatherUpdate saved = realTimeWeatherUpdateRepository.save(realTimeWeatherUpdate);
        return saved;
    }

    @Override
    public List<RealTimeWeatherUpdate> getRealTimeWeatherUpdate(String city) {
        List<RealTimeWeatherUpdate> byCity = realTimeWeatherUpdateRepository.findByCity(city);
        return byCity;
    }
}
