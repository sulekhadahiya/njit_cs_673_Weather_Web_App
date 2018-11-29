/*
 * Copyright (c) 2018.
 */

package weatherapp.domain.restmodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import weatherapp.domain.dbmodel.RealTimeWeatherUpdate;

import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RealTimeWeatherUpdateRest {
    private String weatherUpdateRest;
    private String cityRest;
    private ZonedDateTime zonedDateTimeRest;

    public RealTimeWeatherUpdateRest() {
    }

    public static RealTimeWeatherUpdateRest fromReadTimeWeatherUpdate(RealTimeWeatherUpdate realTimeWeatherUpdate) {
        RealTimeWeatherUpdateRest realTimeWeatherUpdateRest = new RealTimeWeatherUpdateRest();
        realTimeWeatherUpdateRest.setCityRest(realTimeWeatherUpdate.getCity());
        realTimeWeatherUpdateRest.setWeatherUpdateRest(realTimeWeatherUpdate.getWeatherUpdate());
        if (Objects.nonNull(realTimeWeatherUpdate.getDate())) {
            try {
                realTimeWeatherUpdateRest.setZonedDateTimeRest(ZonedDateTime.parse(realTimeWeatherUpdate.getDate()));
            } catch (DateTimeParseException dateTimeParseException) {
                //do nothing
            }
        }
        return realTimeWeatherUpdateRest;
    }

    public String getWeatherUpdateRest() {
        return weatherUpdateRest;
    }

    public void setWeatherUpdateRest(String weatherUpdateRest) {
        this.weatherUpdateRest = weatherUpdateRest;
    }

    public String getCityRest() {
        return cityRest;
    }

    public void setCityRest(String cityRest) {
        this.cityRest = cityRest;
    }

    public ZonedDateTime getZonedDateTimeRest() {
        return zonedDateTimeRest;
    }

    public void setZonedDateTimeRest(ZonedDateTime zonedDateTimeRest) {
        this.zonedDateTimeRest = zonedDateTimeRest;
    }

    @Override
    public String toString() {
        return "RealTimeWeatherUpdateRest{" +
                "weatherUpdateRest='" + weatherUpdateRest + '\'' +
                ", cityRest='" + cityRest + '\'' +
                ", zonedDateTimeRest=" + zonedDateTimeRest +
                '}';
    }

    public RealTimeWeatherUpdate toRealTimeWeatherUpdate() {
        RealTimeWeatherUpdate realTimeWeatherUpdate = new RealTimeWeatherUpdate();
        realTimeWeatherUpdate.setCity(this.cityRest);
        realTimeWeatherUpdate.setWeatherUpdate(this.weatherUpdateRest);
        realTimeWeatherUpdate.setDate(ZonedDateTime.now().toString());
        return realTimeWeatherUpdate;
    }
}
