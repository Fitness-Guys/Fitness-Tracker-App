package edu.csueb.codepath.fitness_tracker;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import java.text.SimpleDateFormat;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DateSummary {

    public static String Date;
    public String Weather;

    double longitude, latitude;

    public static String getDate() {
        // Using the SimpleDateFormat to get today's date and return the value
        java.util.Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MMM d");
        Date = format.format(today);

        return Date;
    }

    public static String getWeather() {

        // Using weather API
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://community-open-weather-map.p.rapidapi.com/weather?q=London%2Cuk&lat=latitude&lon=longitude&callback=test&id=2172797&lang=null&units=%22metric%22%20or%20%22imperial%22&mode=xml%2C%20html")
                .get("temp")
                .addHeader("x-rapidapi-key", "5ab7a188bcmsh4fca33d9fa0f295p1d07dfjsn61a7dd0847dd")
                .addHeader("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();

        return "";
    }

    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }
    }
*/
}

