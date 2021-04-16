package edu.csueb.codepath.fitness_tracker.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import edu.csueb.codepath.fitness_tracker.DateSummary;
import edu.csueb.codepath.fitness_tracker.R;
import edu.csueb.codepath.fitness_tracker.WorkoutListAdapter;

public class ProfileFragment extends Fragment {

    TextView tvName;
    TextView tvUserHeight;
    TextView tvUserWeight;
    RecyclerView rvWorkouts;

    TextView tvDate;
    TextView tvWeather;

    List workout;
    WorkoutListAdapter adapter;

    public ProfileFragment() {
        // Required empty public constructor
    }

    // The onCreateView method is called when Fragment should create its View object hierarchy
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here. E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvName = view.findViewById(R.id.tvUserName);
        tvUserHeight = view.findViewById(R.id.tvUserHeight);
        tvUserWeight = view.findViewById(R.id.tvUserWeight);
        rvWorkouts = view.findViewById(R.id.rvWorkouts);
        tvDate = view.findViewById(R.id.tvDate);
        tvWeather = view.findViewById(R.id.tvWeather);


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

            tvName.setText(currentUser.getUsername());
            tvUserHeight.setText(currentUser.getJSONObject("height"));
            tvUserWeight.setText(currentUser.getJSONObject("weight"));

        }

        // Initialize the list of tweets and adapter
        workout = new ArrayList<>();
        adapter = new WorkoutListAdapter(this, workout);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        //Recycler view setup: layout manager and the adapter
        rvWorkouts.setLayoutManager(new LinearLayoutManager(this));
        rvWorkouts.setAdapter(adapter);



    }
}