package weatherapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import weatherapp.AppConfig;
import weatherapp.controllers.responses.*;
import weatherapp.resources.WeatherReport;
import weatherapp.utils.CoordPair;
import weatherapp.utils.exceptions.LocationNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ForecastController {

    private static final String GET_CurrentWeather = "/now/{location}";
    private static final String GET_HourlyWeather = "/hourly/{location}";
    private static final String GET_5DayWeather = "/5day/{location}";
    private static final String Get_10DayWeather = "/10day/{location}";

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(LocationNotFoundException.class)
    @ResponseBody ErrorResponse
    handleLocationNotFound(HttpServletRequest req, Exception ex) {
        return new ErrorResponse(req.getRequestURL().toString(), ex.getLocalizedMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnsupportedOperationException.class)
    @ResponseBody ErrorResponse
    handleUnsupportedOperation(HttpServletRequest req, Exception ex) {
        return new ErrorResponse(req.getRequestURL().toString(), ex.getLocalizedMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody ErrorResponse
    handleRuntimeException(HttpServletRequest req, Exception ex) {
        return new ErrorResponse(req.getRequestURL().toString(), ex.getLocalizedMessage());
    }

    @RequestMapping(value = GET_CurrentWeather, method = RequestMethod.GET)
    @ResponseBody CurrentWeatherResponse
    getCurrentWeather(@PathVariable String location) {
        try {
            CurrentWeatherResponse response = new CurrentWeatherResponse();

            CoordPair coords = AppConfig.geolocationService.locationToCoords(location);
            WeatherReport report = AppConfig.shortForecastService.getCurrentWeather(coords);
            // TODO: populate response object

            return response;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @RequestMapping(value = GET_HourlyWeather, method = RequestMethod.GET)
    @ResponseBody HourlyWeatherResponse
    getHourlyWeather(@PathVariable String location) {
        try {
            HourlyWeatherResponse response = new HourlyWeatherResponse();

            CoordPair coords = AppConfig.geolocationService.locationToCoords(location);
            List<WeatherReport> reports = AppConfig.shortForecastService.getHourlyWeather(coords);
            // TODO: Populate response object

            return response;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @RequestMapping(value = GET_5DayWeather, method = RequestMethod.GET)
    @ResponseBody FiveDayWeatherResponse
    get5DayWeather(@PathVariable String location) {
        try {
            FiveDayWeatherResponse response = new FiveDayWeatherResponse();

            CoordPair coords = AppConfig.geolocationService.locationToCoords(location);
            List<WeatherReport> reports = AppConfig.shortForecastService.get5DayWeather(coords);
            // TODO: populate response object

            return response;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @RequestMapping(value = Get_10DayWeather, method = RequestMethod.GET)
    @ResponseBody TenDayWeatherResponse
    get10DayWeather(@PathVariable String location) {
        try {
            TenDayWeatherResponse response = new TenDayWeatherResponse();

            CoordPair coords = AppConfig.geolocationService.locationToCoords(location);
            List<WeatherReport> reports = AppConfig.longForecastService.get10DayWeather(coords);
            // TODO: populate response object

            return response;
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
