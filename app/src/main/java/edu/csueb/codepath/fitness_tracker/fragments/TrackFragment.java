package edu.csueb.codepath.fitness_tracker.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.csueb.codepath.fitness_tracker.R;
import edu.csueb.codepath.fitness_tracker.WorkoutListAdapter;
import edu.csueb.codepath.fitness_tracker.workout_timer;

import static java.lang.String.join;

public class TrackFragment extends Fragment {

    public static final String TAG = "TrackFragment";
    private RecyclerView rvWorkout;
    private WorkoutListAdapter workoutListAdapter;
    List<String> workouts;  //is going to be used to store the workouts
    List<String> selected;  //is going to hold workouts that has been checked

    private Button startButton;

    public TrackFragment() {
        // Required empty public constructor
    }

    // The onCreateView method is called when Fragment should create its View object hierarchy
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_track, container, false);
        rvWorkout = (RecyclerView) view.findViewById(R.id.rvWorkout);
        CardView cardView = (CardView) view.findViewById(R.id.cardWorkoutHome);

        startButton = (Button) view.findViewById(R.id.btnStart);
        workoutListAdapter = new WorkoutListAdapter(workouts, this);

        startButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                /*
                Log.i(TAG, "Start Button Clicked");
                //selected = workoutListAdapter.getChecked();
                String str = join(", ", selected);
                 */
                selected = workoutListAdapter.getChecked();
                Log.i(TAG, Arrays.toString(selected.toArray()));
                if(selected.size() > 0){
                    Intent i = new Intent(getActivity(), workout_timer.class);
                    i.putExtra("Workout", (Serializable) selected);
                    startActivity(i);
                    Animatoo.animateSlideLeft(getContext());
                }else {
                    Toast.makeText(getContext(), "No workout selected!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Context context = view.getContext();
        //rvWorkout.setLayoutManager(new LinearLayoutManager(context));
        rvWorkout.setLayoutManager(new LinearLayoutManager(context));
        workoutListAdapter = new WorkoutListAdapter(workouts, this);
        rvWorkout.setAdapter(workoutListAdapter);

        return view;
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here. E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        workouts = new ArrayList<>();
        workouts.add("General");
        workouts.add("Strength Training");
        workouts.add("Run");
        workouts.add("Walk");
        workouts.add("Yoga");



        workoutListAdapter.setWorkouts(workouts);
    }

}