package edu.csueb.codepath.fitness_tracker.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
import edu.csueb.codepath.fitness_tracker.ProfileEdit;
import edu.csueb.codepath.fitness_tracker.R;
import edu.csueb.codepath.fitness_tracker.WorkoutListAdapter;

public class HomeFragment extends Fragment {

    TextView tvUserName;
    TextView tvDate;
    TextView tvWeather;
    TextView tvCalories;
    ImageButton btnEdit;
    RecyclerView rvWorkouts;


    List workout;
    WorkoutListAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    // The onCreateView method is called when Fragment should create its View object hierarchy
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here. E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvUserName = view.findViewById(R.id.tvUserName);
        tvDate = view.findViewById(R.id.tvDate);
        tvWeather = view.findViewById(R.id.tvWeather);
        btnEdit = view.findViewById(R.id.btnEdit);
        rvWorkouts = view.findViewById(R.id.rvWorkouts);
        tvCalories = view.findViewById(R.id.tvCalories);

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
            tvUserName.setText(currentUser.getUsername()); // need info
            tvDate.setText(DateSummary.getDate());
            tvWeather.setText(DateSummary.getWeather());
        }

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Workout");

        // The query will search for a ParseObject, given its objectId.
        // When the query finishes running, it will invoke the GetCallback
        // with either the object, or the exception thrown
        query.getInBackground("<PARSE_OBJECT_ID>", (object, e) -> {
            if (e == null) {
                //Object was successfully retrieved
                tvCalories.setText(object.get("calories"));

            } else {
                // something went wrong
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeFragment.this, ProfileEdit.class);
                startActivity(i);
            }
        });

        // Initialize the list of tweets and adapter
        workout = new ArrayList<>();
        adapter = new WorkoutListAdapter(this, workout);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        //Recycler view setup: layout manager and the adapter
        rvWorkouts.setLayoutManager(new LinearLayoutManager(this));
        rvWorkouts.setAdapter(adapter);
    }
}