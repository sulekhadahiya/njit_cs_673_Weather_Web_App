/*
 * Copyright (c) 2018.
 */

package weatherapp.domain.dbmodel;

import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;

@Document
public class RealTimeWeatherUpdate {
    @TextIndexed
    private String weatherUpdate;
    @TextIndexed
    private String city;
    @TextIndexed
    private String date;
    @TextScore
    Float score;

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

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
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
