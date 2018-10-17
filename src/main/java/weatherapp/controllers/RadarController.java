package weatherapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import weatherapp.controllers.responses.ErrorResponse;
import weatherapp.controllers.responses.RadarResponse;
import weatherapp.services.IGeolocationService;
import weatherapp.services.IRadarService;
import weatherapp.services.MapQuestGeolocationService;
import weatherapp.services.NWSRadarService;
import weatherapp.utils.CoordPair;
import weatherapp.utils.exceptions.LocationNotFoundException;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RadarController {

    private static final String GET_CurrentRadar = "/radar/{location}";
    private static final String GET_AnimatedRadar = "/radar/{location}/animated";

    private final IGeolocationService geolocationService = new MapQuestGeolocationService();
    private final IRadarService radarService = new NWSRadarService();

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

    @RequestMapping(value = GET_CurrentRadar, method = RequestMethod.GET)
    public @ResponseBody RadarResponse getCurrentRadar(@PathVariable String location) throws Exception {
        try {
            CoordPair coords = this.geolocationService.locationToCoords(location);
            String radarURL = this.radarService.getRadarMapURL(coords, false);
            return new RadarResponse(radarURL);
        } catch (Exception e) {
            throw e;
        }
    }

    @RequestMapping(value = GET_AnimatedRadar, method = RequestMethod.GET)
    public @ResponseBody RadarResponse getAnimatedRadar(@PathVariable String location) throws Exception {
        try {
            CoordPair coords = this.geolocationService.locationToCoords(location);
            String radarURL = this.radarService.getRadarMapURL(coords, true);
            return new RadarResponse(radarURL);
        } catch (Exception e) {
            throw e;
        }
    }
}
