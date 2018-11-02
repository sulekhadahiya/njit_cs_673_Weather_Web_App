package weatherapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import weatherapp.controllers.responses.*;
import weatherapp.resources.WeatherReport;
import weatherapp.services.*;
import weatherapp.utils.CoordPair;
import weatherapp.utils.exceptions.LocationNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ForecastController {

    private static final String GET_CurrentWeather = "/now/{location}";
    private static final String GET_HourlyWeather = "/hourly/{location}";
    private static final String GET_5DayWeather = "/5day/{location}";
    private static final String Get_10DayWeather = "/10day/{location}";

    private final IGeolocationService geolocationService = new MapQuestGeolocationService();
    private final IForecastService shortForecastService = new NWSForecastService();
    private final IForecastService longForecastService = new WeatherBitForecastService();

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(LocationNotFoundException.class)
    public @ResponseBody ErrorResponse handleLocationNotFound(HttpServletRequest req, Exception ex) {
        return new ErrorResponse(req.getRequestURL().toString(), ex.getLocalizedMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnsupportedOperationException.class)
    public @ResponseBody ErrorResponse handleUnsupportedOperation(HttpServletRequest req, Exception ex) {
        return new ErrorResponse(req.getRequestURL().toString(), ex.getLocalizedMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public @ResponseBody ErrorResponse handleRuntimeException(HttpServletRequest req, Exception ex) {
        return new ErrorResponse(req.getRequestURL().toString(), ex.getLocalizedMessage());
    }

    @RequestMapping(value = GET_CurrentWeather, method = RequestMethod.GET)
    public @ResponseBody CurrentWeatherResponse getCurrentWeather(@PathVariable String location) throws Exception {
        try {
            CoordPair coords = this.geolocationService.locationToCoords(location);
            WeatherReport report = this.shortForecastService.getCurrentWeather(coords);
            return new CurrentWeatherResponse(report);
        } catch (Exception e) {
            throw e;
        }
    }

    @RequestMapping(value = GET_HourlyWeather, method = RequestMethod.GET)
    public @ResponseBody HourlyWeatherResponse getHourlyWeather(@PathVariable String location) throws Exception {
        try {
            CoordPair coords = this.geolocationService.locationToCoords(location);
            List<WeatherReport> reports = this.shortForecastService.getHourlyWeather(coords);
            return new HourlyWeatherResponse(reports);
        } catch (Exception e) {
            throw e;
        }
    }

    @RequestMapping(value = GET_5DayWeather, method = RequestMethod.GET)
    public @ResponseBody FiveDayWeatherResponse get5DayWeather(@PathVariable String location) throws Exception {
        try {
            CoordPair coords = this.geolocationService.locationToCoords(location);
            List<WeatherReport> reports = this.shortForecastService.get5DayWeather(coords);
            return new FiveDayWeatherResponse(reports);
        } catch (Exception e) {
            throw e;
        }
    }

    @RequestMapping(value = Get_10DayWeather, method = RequestMethod.GET)
    public @ResponseBody TenDayWeatherResponse get10DayWeather(@PathVariable String location) throws Exception {
        try {
            CoordPair coords = this.geolocationService.locationToCoords(location);
            List<WeatherReport> reports = this.longForecastService.get10DayWeather(coords);
            return new TenDayWeatherResponse(reports);
        } catch (Exception e) {
            throw e;
        }
    }
}
