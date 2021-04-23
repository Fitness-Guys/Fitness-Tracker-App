package edu.csueb.codepath.fitness_tracker;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.sql.Date;

@ParseClassName("Workout")
public class workoutObject extends ParseObject {

    private String objectID;
    private Date start;
    private Date end;
    private int calories;
    private String duration;
    private String WorkoutType;

    public workoutObject(){ }

    public void setDates(Date s, Date e){
        start = s;
        end = e;
    }

    public void setCalories(int cal){

    }


}
