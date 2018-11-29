/*
 * Copyright (c) 2018.
 */

package weatherapp.services;

import org.springframework.stereotype.Service;
import weatherapp.domain.dbmodel.RealTimeWeatherUpdate;

import java.util.List;

@Service
public interface RealTimeWeatherUpdateService {

    RealTimeWeatherUpdate saveRealTimeWeatherUpdate(RealTimeWeatherUpdate realTimeWeatherUpdate);

    List<RealTimeWeatherUpdate> getRealTimeWeatherUpdate(String city);
}
