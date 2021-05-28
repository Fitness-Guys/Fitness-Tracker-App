package edu.csueb.codepath.fitness_tracker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.SaveCallback;

public class workout_timer extends FragmentActivity {
    private String TAG = "workout_timer";
    private Chronometer chronometer;
    private boolean running;
    private boolean started;
    private Button start_stop;
    private Button reset;
    private Button finishWorkout;
    private long pauseOffset;
    private int currentTime;    //will be used to store the current time on the chronometer
    public List<String> workouts;
    private TextView workoutTitle;
    private Date startTime;    //stores the date, and time of the started workout
    private Date finishTime;   //stores the date and time of the finished workout
    private ProgressBar progressBar;




    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_timer);
        SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        chronometer = (Chronometer) findViewById(R.id.timer);
        //chronometer.setFormat("HH:MM:SS");

        start_stop = (Button) findViewById(R.id.btnStart_stop);
        reset = (Button) findViewById(R.id.btnReset);
        finishWorkout = (Button) findViewById(R.id.btnFinish);
        workoutTitle = (TextView) findViewById(R.id.tvWorkoutList);
        progressBar = findViewById(R.id.circleProgress);
        running = false;
        started = false;
        long elapsedMillis = SystemClock.elapsedRealtime()
                - chronometer.getBase();

        workouts = (List<String>) getIntent().getSerializableExtra("Workout");
        Log.e(TAG, Arrays.toString(workouts.toArray()));
        String workouttitle = Arrays.toString(workouts.toArray());
        workoutTitle.setText(workouttitle);

        start_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStopTimer(v, dtf);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer(v);
            }
        });

        finishWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishWorkout(v);
            }
        });

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                Log.i(TAG, String.valueOf(SystemClock.elapsedRealtime() - chronometer.getBase()));
                long elapsedMillis = SystemClock.elapsedRealtime()
                        - chronometer.getBase();
                Log.e(TAG, String.valueOf((Math.round(elapsedMillis/1000))));
                int timerSeconds = Math.round(elapsedMillis/1000);
                int finalTimerSeconds = 0;
                if(timerSeconds > 60){
                    finalTimerSeconds = timerSeconds % 60;
                } else {
                    finalTimerSeconds = timerSeconds;
                }

                Log.e(TAG, String.valueOf(finalTimerSeconds * 1.666));
                progressBar.setProgress((int) Math.round(finalTimerSeconds * 1.666));
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void startStopTimer(View view, SimpleDateFormat dtf){
        if(!running && !started){   //If workout has just started

            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            running = true;
            start_stop.setText("Stop");
            start_stop.setBackgroundTintList(getResources().getColorStateList(R.color.pastelred));
            started = true;
            startTime = new Date();
            Date st = startTime;
            Log.i(TAG, String.valueOf(st));
        }else if(!running && started) { //if workout is getting unpaused
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
            start_stop.setText("Stop");
            start_stop.setBackgroundTintList(getResources().getColorStateList(R.color.pastelred));
        }else if(running){  //stops the timer
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
            long elapsedMili = SystemClock.elapsedRealtime()
                    - chronometer.getBase();
            currentTime = Math.round(elapsedMili/1000);
            start_stop.setText("start");
            start_stop.setBackgroundTintList(getResources().getColorStateList(R.color.pastelgreen));

        }
    }


    public void resetTimer(View view){
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
        //Toast.makeText(this, "Finished", Toast.LENGTH_SHORT).show();
    }

    public void finishWorkout(View view){
        //https://www.semicolonworld.com/question/46673/android-get-time-of-chronometer-widget

        //int seconds = Math.round(time/1000);
        int seconds;
        if(!running){
            seconds = currentTime;
        }else{
            //long time = SystemClock.elapsedRealtime() - pauseOffset;
            //Log.e("workout_timer", String.valueOf(time));
            long elapsedMillis = SystemClock.elapsedRealtime()
                    - chronometer.getBase();
            seconds = Math.round(elapsedMillis/1000);

            Log.e("workout_timer", String.valueOf(seconds));

        }

        if(seconds == 0){
            Toast.makeText(this, "Workout Not Started!", Toast.LENGTH_SHORT).show();
            return;
        }

        finishTime = new Date();
        Log.i(TAG, String.valueOf(finishTime));

        Intent i = new Intent(this, workout_summary.class);
        i.putExtra("Workout2", (Serializable) workouts);
        i.putExtra("finalTime", seconds);
        i.putExtra("startTime",(Serializable) startTime);
        i.putExtra("finishTime",(Serializable) finishTime);
        startActivity(i);
        Animatoo.animateSlideLeft(this);

        //Toast.makeText(this, "Elapsed seconds: " + seconds, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Animatoo.animateSlideRight(this);
    }
}
