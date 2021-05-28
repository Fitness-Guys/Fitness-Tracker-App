package edu.csueb.codepath.fitness_tracker;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import edu.csueb.codepath.fitness_tracker.fragments.HomeFragment;

public class workout_summary extends FragmentActivity {

    public String TAG = "workout_summary";
    private TextView workoutType;
    private TextView timeDisplay;
    private TextView caloriesDisplay;
    private Button close;
    public List<String> workouts;
    public double calories;
    private int numOfActivities;
    private Date startTimeDate;
    private Date finishTimeDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_summary);

        workoutType = (TextView) findViewById(R.id.tvWorkout_summary);
        timeDisplay = (TextView) findViewById(R.id.tvWorkoutTimeSummary);
        caloriesDisplay = (TextView) findViewById(R.id.tvCaloriesDisplay);
        workouts = (List<String>) getIntent().getSerializableExtra("Workout2");
        numOfActivities = workouts.size();

        String workoutTypeString = createWorkoutString(workouts);
        workoutType.setText(workoutTypeString);

        int finalTime = (int) getIntent().getIntExtra("finalTime", 0);
        String time = createTimerString(finalTime);
        timeDisplay.setText(String.valueOf(time));

        calories = calculateCalories(finalTime, numOfActivities);
        caloriesDisplay.setText(String.valueOf(calories));

        startTimeDate = (Date) getIntent().getSerializableExtra("startTime");
        finishTimeDate = (Date) getIntent().getSerializableExtra("finishTime");
        Log.e(TAG, String.valueOf(startTimeDate));
        Log.e(TAG, String.valueOf(finishTimeDate));

        close = (Button) findViewById(R.id.btnFinish);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitWorkout(time, workoutTypeString);
                Intent i = new Intent(workout_summary.this, MainActivity.class);
                startActivity(i);
                Animatoo.animateFade(workout_summary.this);
            }
        });

    }

    private void submitWorkout(String time, String workoutTypeString) {
        Workout workout = new Workout();
        workout.setStartDate(startTimeDate);
        workout.setKeyEnd(finishTimeDate);
        workout.setCalories(calories);
        workout.setDuration(time);
        workout.setWorkoutType(workoutTypeString);
        workout.setKeyUser(ParseUser.getCurrentUser());

        workout.saveInBackground((e) ->{
            if(e != null){
                Log.e(TAG, "ERROR WHILE SAVING");
            } else {
                Log.i(TAG, "Save Success");
            }
        });

    }

    public String createWorkoutString(List<String> workouts){
        String workoutTypeString = "";

        for(int i = 0; i < workouts.size(); i++){
            if(!(i < workouts.size())){
                workoutTypeString = workoutTypeString + workouts.get(i);
            } else if(i == 0) {
                workoutTypeString = workouts.get(i);
            } else {
                workoutTypeString = workoutTypeString + ", " + workouts.get(i);
            }
        }
        return workoutTypeString;
    }

    public String createTimerString(int finalTime){

        int min = 0;
        int sec = 0;

        String time = "";
        String sec_str = "";

        if(finalTime >= 60){    //when the time is over 1 minute
            min = finalTime / 60; //getting the minutes value
            sec = finalTime % 60; //getting the seconds value
            if(sec < 10){
                sec_str = "0" + String.valueOf(sec);
            } else {
                sec_str = String.valueOf(sec);
            }

        } else { //in case if time doesn't exceed 1 minute
            if(finalTime < 10){ //in case if time doesn't exceed 10 seconds
                sec_str = "0" + String.valueOf(finalTime);
            } else {
                sec_str = String.valueOf(finalTime);
            }
        }
        time = String.valueOf(min) + ":" + sec_str;
        return time;
    }

    private double calculateCalories(int finalTime, int numOfActivities){

        ParseUser user = ParseUser.getCurrentUser();
        Integer w = (Integer) user.get("weight");


        double cal = 0.0;
        int MET = 0;    //MET = metabolic equivalent for task
        switch (numOfActivities){
            case 1:
                MET =  4;
                break;
            case 2:
                MET = 5;
                break;
            case 3:
                MET = 7;
                break;
            case 4:
                MET = 8;
                break;
            case 5:
                MET = 8;
                break;
            default:
                return 0;
        }

        double time = ((double) finalTime) / ((double) 60);
        //kg --> pounds formula: lb/2.2046
        double kgConv = ((double) w/2.2046);  //kilogram conversion from weight
        //Log.i(TAG, String.valueOf(kgConv));

        cal = (time)*((double) MET*3.5*kgConv)/200; //calculates the calories burned
                                                    //this will be using users weight, and MET which will determine
                                                    //the amount of work done during workout

        //Toast.makeText(workout_summary.this, String.valueOf(time), Toast.LENGTH_SHORT).show();

        BigDecimal bd = new BigDecimal(cal).setScale(2, RoundingMode.HALF_UP);
        cal = bd.doubleValue();

        return cal;
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Animatoo.animateSlideRight(this);
    }
}