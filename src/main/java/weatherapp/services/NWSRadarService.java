package weatherapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.client.RestTemplate;
import weatherapp.utils.CoordPair;

public class NWSRadarService implements IRadarService {

    private final String BASE_URL = "https://api.weather.gov/points/%f,%f";
    private final String RADAR_URL = "https://radar.weather.gov/ridge/lite/N0R/%s";

    private RestTemplate rest = new RestTemplate();

    @Override
    public String getRadarMapURL(CoordPair coords, boolean animated) throws RuntimeException {
        JsonNode info_root = rest.getForObject(String.format(BASE_URL, coords.getLatitude(), coords.getLongitude()), JsonNode.class);
        String RADAR_stationID = info_root.get("properties").get("radarStation").asText();

        RADAR_stationID = RADAR_stationID.substring(RADAR_stationID.length()-3);

        if (animated) {
            return String.format(RADAR_URL, RADAR_stationID + "_loop.gif");
        } else {
            return String.format(RADAR_URL, RADAR_stationID + "_0.png");
        }
    }
}
