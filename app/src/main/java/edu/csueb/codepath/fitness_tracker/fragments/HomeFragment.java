package edu.csueb.codepath.fitness_tracker.fragments;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.csueb.codepath.fitness_tracker.DateSummary;
import edu.csueb.codepath.fitness_tracker.HomeWorkoutListAdapter;
import edu.csueb.codepath.fitness_tracker.ProfileEdit;
import edu.csueb.codepath.fitness_tracker.R;
import edu.csueb.codepath.fitness_tracker.Workout;
import edu.csueb.codepath.fitness_tracker.WorkoutListAdapter;

public class HomeFragment extends Fragment {

    TextView tvUserName;
    TextView tvDate;
    TextView tvActivity;
    TextView tvCalories;
    RecyclerView rvWorkouts;
    private String TAG = "HomeFragment";

    List<String> workout;   //workout name
    List<String> listTime;  //time for each workout
    List<String> indivCalo; //indivial calories per workout
    HomeWorkoutListAdapter adapter;
    private Double totalCal;
    private int totalTime;
    private static DecimalFormat df2 = new DecimalFormat("#.##");



    public HomeFragment() {
        // Required empty public constructor
    }

    // The onCreateView method is called when Fragment should create its View object hierarchy
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        tvUserName = (TextView) view.findViewById(R.id.tvUserName);
        tvDate = (TextView) view.findViewById(R.id.tvDate);
        tvCalories = (TextView) view.findViewById(R.id.tvCalories);
        tvActivity = (TextView) view.findViewById(R.id.tvActivity);
        getCurrentUser();
        workout = new ArrayList<>();
        listTime = new ArrayList<>();
        indivCalo = new ArrayList<>();
        totalCal = 0.0;

        rvWorkouts = (RecyclerView) view.findViewById(R.id.rvWorkouts);
        adapter = new HomeWorkoutListAdapter(workout, listTime, indivCalo,this);
        rvWorkouts.setLayoutManager(new LinearLayoutManager(getContext()));
        rvWorkouts.setAdapter(adapter);

        populateWorkout();


        tvCalories.setText(String.valueOf(df2.format(totalCal)));
        Log.e(TAG, String.valueOf(workout.size()));

        return view;
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here. E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void getCurrentUser() {
        // After login, Parse will cache it on disk, so
        // we don't need to login every time we open this
        // application
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // do stuff with the user
        } else {
            // show the signup or login screen
        }
        this.tvUserName.setText("Hello " + (currentUser.getUsername()));
        this.tvDate.setText(DateSummary.getDate());
    }

    public void populateWorkout() {
        ParseQuery<Workout> query = ParseQuery.getQuery("Workout");
        query.include(Workout.KEY_USER);
        query.whereEqualTo(Workout.KEY_USER, ParseUser.getCurrentUser());
        query.setLimit(20);
        //query.whereEqualTo("createdAt", new Date());
        query.findInBackground(new FindCallback<Workout>() {
            @Override
            public void done(List<Workout> objects, ParseException e) {
                int min = 0;
                int sec = 0;
                if(e != null){
                    Log.e(TAG, "Error populating workouts", e);
                    return;
                }
                for(Workout w: objects){
                    workout.add((String) w.get("WorkoutType"));  //POSSIBLE BUGs
                    listTime.add((String) w.get("duration"));
                    indivCalo.add(String.valueOf(w.get("calories")));
                    totalCal += ((Double) w.get("calories"));

                    String temp = (String) w.get("duration");
                    Log.i(TAG, "duration: " + temp);
                    String[] time = temp.split(":");
                    min = Integer.parseInt(time[0]);
                    sec = Integer.parseInt(time[1]);
                    totalTime += (min * 60) + sec;
                    Log.i(TAG, "Time: " + String.valueOf(totalTime));
                }

                Log.e(TAG, String.valueOf(totalCal));
                tvCalories.setText(String.valueOf(df2.format(totalCal)));
                String finalSec = "";
                if((totalTime % 60) < 9){
                    finalSec = "0" + String.valueOf(totalTime % 60);
                } else {
                    finalSec = String.valueOf(totalTime % 60);
                }

                String finalTime = String.valueOf(totalTime / 60) + ":" + String.valueOf(finalSec);
                tvActivity.setText(finalTime);

                adapter.notifyDataSetChanged();
            }
        });

    }

}