/*
 * Copyright (c) 2018.
 */

package weatherapp.controllers.responses;

import weatherapp.resources.WeatherReport;

import java.util.List;

public class TenDayWeatherResponse {

    private final List<WeatherReport> reports;

    public TenDayWeatherResponse(List<WeatherReport> reports) {
        this.reports = reports;
    }

    public List<WeatherReport> getReports() {
        return this.reports;
    }
}
