package weatherapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.client.RestTemplate;

import weatherapp.utils.CoordPair;
import weatherapp.utils.exceptions.LocationNotFoundException;

public class MapQuestGeolocationService implements IGeolocationService {

    private final String BASE_URL = "http://open.mapquestapi.com/geocoding/v1/address?key=37In1DQJS3pHZWajdvZVEDrsMbGn2A0p&location=%s";

    private final RestTemplate rest = new RestTemplate();

    @Override
    public CoordPair locationToCoords(String location) throws LocationNotFoundException {
        JsonNode root = rest.getForObject(String.format(BASE_URL, location), JsonNode.class);

        if (root == null) throw new LocationNotFoundException();

        JsonNode coords_node = root.get("results").get(0).get("locations").get(0).get("latLng");

        double lat = coords_node.get("lat").asDouble();
        double lon = coords_node.get("lng").asDouble();

        return new CoordPair(lat, lon);
    }
}
