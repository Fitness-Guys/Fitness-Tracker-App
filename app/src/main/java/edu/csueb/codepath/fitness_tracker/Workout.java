package edu.csueb.codepath.fitness_tracker;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;


@ParseClassName("Workout")
public class Workout extends ParseObject {


    public static final String KEY_START = "start";
    public static final String KEY_END = "end";
    public static final String KEY_CALORIES = "calories";
    public static final String KEY_DURATION = "duration";
    public static final String KEY_WORKOUT_TYPE = "WorkoutType";
    public static final String KEY_CREATE_AT = "createdAt";
    public static final String KEY_USER = "user";

    public Workout(){ }

    public void setStartDate(Date s){
        put(KEY_START, s);
    }

    public void setKeyEnd(Date end){
        put(KEY_END, end);
    }

    public void setCalories(double cal){
        put(KEY_CALORIES, cal);
    }

    public void setDuration(String dur){
        put(KEY_DURATION, dur);
    }
    public void setWorkoutType(String wt){
        put(KEY_WORKOUT_TYPE, wt);
    }
    public void setKeyUser(ParseUser parseUser){
        put(KEY_USER, parseUser);
    }

    /////////

    public ParseFile getStart(){return getParseFile(KEY_START);}

    public ParseFile getEnd() {
        return getParseFile(KEY_END);
    }

    public ParseFile getCalories() {
        return getParseFile(KEY_CALORIES);
    }

    public ParseFile getDuration() {
        return getParseFile(KEY_DURATION);
    }

    public ParseFile getWorkoutType() {
        return getParseFile(KEY_WORKOUT_TYPE);
    }

    public String getWorkoutTypeString(){return KEY_WORKOUT_TYPE;}

    public String getDurationString(){return KEY_DURATION;}

}
