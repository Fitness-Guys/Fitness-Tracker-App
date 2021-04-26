package edu.csueb.codepath.fitness_tracker;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import java.util.List;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class workout_summary extends FragmentActivity {

    private TextView workoutType;
    private TextView timeDisplay;
    private TextView caloriesDisplay;
    private Button close;
    public List<String> workouts;
    public double calories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_summary);

        workoutType = (TextView) findViewById(R.id.tvWorkout_summary);
        timeDisplay = (TextView) findViewById(R.id.tvWorkoutTimeSummary);
        caloriesDisplay = (TextView) findViewById(R.id.tvCaloriesDisplay);
        workouts = (List<String>) getIntent().getSerializableExtra("Workout2");

        String workoutTypeString = createWorkoutString(workouts);
        workoutType.setText(workoutTypeString);

        int finalTime = (int) getIntent().getIntExtra("finalTime", 0);
        String time = createTimerString(finalTime);
        timeDisplay.setText(String.valueOf(time));

        close = (Button) findViewById(R.id.btnFinish);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calories = calculateCalories(finalTime);
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

    private double calculateCalories(int finalTime){
        double cal = 0.0;
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("username", ParseUser.getCurrentUser());
        query.findInBackground((users, e) ->{
            if(e==null){
                Log.d("workout_summary", "query success!");
                //Log.d("workout_summary", users.get);    //figure out how to get information.
            } else {
                Log.e("workout_summary", "query FAILED");
            }
        });

        return cal;
    }
}