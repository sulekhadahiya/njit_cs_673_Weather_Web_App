package weatherapp.utils;

public class CoordPair {
    private double latitude;
    private double longitude;
    private String name;

    public CoordPair(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = "";
    }

    public CoordPair(double latitude, double longitude, String name) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("<CoordPair(latitude: %f, longitude: %f, name: \"%s\")>",
                this.latitude, this.longitude, this.name);
    }
}
