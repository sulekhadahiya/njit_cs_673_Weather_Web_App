/*
 * Copyright (c) 2018.
 */

package weatherapp.controllers.responses;

import weatherapp.resources.WeatherReport;

public class CurrentWeatherResponse {

    private final WeatherReport report;

    public CurrentWeatherResponse(WeatherReport report) {
        this.report = report;
    }

    public WeatherReport getReport() {
        return this.report;
    }
}
