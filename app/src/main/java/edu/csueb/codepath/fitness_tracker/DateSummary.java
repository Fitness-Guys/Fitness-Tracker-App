package edu.csueb.codepath.fitness_tracker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateSummary {

    public static String Date;
    public String Weather;

    public static String getDate() {
        // Using the SimpleDateFormat to get today's date and return the value
        java.util.Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MMM d");
        Date = format.format(today);

        return Date;
    }

    public static String getWeather() {
        return "";
    }

}

