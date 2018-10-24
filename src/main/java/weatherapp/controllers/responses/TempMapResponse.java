package weatherapp.controllers.responses;

import java.util.List;

public class TempMapResponse {
    private final List<String> urls;

    public TempMapResponse(List<String> urls) {
        this.urls = urls;
    }

    public List<String> getUrls() {
        return this.urls;
    }
}
