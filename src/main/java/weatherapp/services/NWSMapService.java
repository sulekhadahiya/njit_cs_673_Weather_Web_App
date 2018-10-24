package weatherapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.client.RestTemplate;
import weatherapp.utils.CoordPair;

import java.util.ArrayList;
import java.util.List;

public class NWSMapService implements IMapService {

    private final String BASE_URL = "https://api.weather.gov/points/%f,%f";
    private final String RADAR_URL = "https://radar.weather.gov/ridge/lite/N0R/%s";
    private final String TEMP_URL = "https://graphical.weather.gov/images/conus/ApparentT%d_conus.png";

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

    @Override
    public List<String> getTempMapURLs() throws RuntimeException {
        List<String> out = new ArrayList<>(8);

        for (int i = 0; i < 8; i++) {
            out.add(i, String.format(TEMP_URL, i + 1));
        }

        return out;
    }
}
