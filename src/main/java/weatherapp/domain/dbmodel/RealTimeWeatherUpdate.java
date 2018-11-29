/*
 * Copyright (c) 2018.
 */

package weatherapp.domain.dbmodel;

import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class RealTimeWeatherUpdate {
    @TextIndexed
    private String weatherUpdate;
    @TextIndexed
    private String city;
    @TextIndexed
    private String date;

    public RealTimeWeatherUpdate() {
    }

    public String getWeatherUpdate() {
        return weatherUpdate;
    }

    public void setWeatherUpdate(String weatherUpdate) {
        this.weatherUpdate = weatherUpdate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "RealTimeWeatherUpdateRest{" +
                "weatherUpdate='" + weatherUpdate + '\'' +
                ", city='" + city + '\'' +
                ", date=" + date +
                '}';
    }
}
