package edu.csueb.codepath.fitness_tracker.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import edu.csueb.codepath.fitness_tracker.R;
import edu.csueb.codepath.fitness_tracker.WorkoutListAdapter;

public class TrackFragment extends Fragment {

    private RecyclerView rvWorkout;
    private WorkoutListAdapter workoutListAdapter;
    List<String> workouts;


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
        Context context = view.getContext();
        rvWorkout.setLayoutManager(new LinearLayoutManager(context));

        workoutListAdapter = new WorkoutListAdapter(workouts, this);

        workouts = new ArrayList<>();
        workouts.add("General");
        workouts.add("Strength Training");
        workouts.add("Run");
        workouts.add("Walk");
        workouts.add("Yoga");


        workoutListAdapter.setWorkouts(workouts);



        return view;
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here. E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}