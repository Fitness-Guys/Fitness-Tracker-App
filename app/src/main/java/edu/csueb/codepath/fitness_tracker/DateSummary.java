package edu.csueb.codepath.fitness_tracker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateSummary {

    public String Date;
    public String Weather;


    public String getDate() {
        // Using the SimpleDateFormat to get today's date and return the value
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MMM d");
        Date = format.format(today);

        return Date;
    }

    public String getWeather() {
        //api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}
        return Weather;
    }

}
}
