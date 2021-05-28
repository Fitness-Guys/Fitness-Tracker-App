package edu.csueb.codepath.fitness_tracker;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateSummary {

    public static String mDate;
    public String Weather;

    double longitude, latitude;

    public static String getDate() {
        // Using the SimpleDateFormat to get today's date and return the value
        java.util.Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MMM d");
        mDate = format.format(today);

        return mDate;
    }
}

