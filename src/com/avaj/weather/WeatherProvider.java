package com.avaj.weather;

import com.avaj.aircraft.Coordinates;
import java.util.Random;

public class WeatherProvider {
    private static Random random;
    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static String[] weather = {
            "RAIN",
            "FOG",
            "SUN",
            "SNOW"
    };
    private WeatherProvider() {
    };

    public static WeatherProvider getProvider() {
      return weatherProvider;
    };

    public String getCurrentWeather(Coordinates coordinates){
        int rnd = ((coordinates.getLongitude() + coordinates.getLatitude()) % 5 - coordinates.getHeight() + 100) % 4;
        return weather[rnd];
    };
}
