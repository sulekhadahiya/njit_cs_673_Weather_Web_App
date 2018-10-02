package weatherapp.utils;

public class CoordPair {
    private double latitude;
    private double longitude;

    public CoordPair(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
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

    @Override
    public String toString() {
        return String.format("<CoordPair(%f, %f)>", this.latitude, this.longitude);
    }
}
