package weatherapp.utils;

public class WindHelper {
    private static String directions[] = {"N", "NE", "E", "SE", "S", "SW", "W", "NW"};

    public static String bearingToCardinal(int bearing) {
        return WindHelper.directions[(int)Math.round(((double)bearing % 360) / 45) % 8];
    }
}
