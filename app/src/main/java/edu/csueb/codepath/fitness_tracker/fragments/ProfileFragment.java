package edu.csueb.codepath.fitness_tracker.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseUser;

import edu.csueb.codepath.fitness_tracker.DateSummary;
import edu.csueb.codepath.fitness_tracker.R;

public class ProfileFragment extends Fragment {

    TextView tvName;
    TextView tvUserHeight;
    TextView tvUserWeight;
    RecyclerView rvWorkouts;

    TextView tvDate;
    TextView tvWeather;

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

        getCurrentUser();

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
            tvName.setText(currentUser.getUsername());
            tvUserHeight.setText(String.valueOf(currentUser.get("height"))); // need info
            tvUserWeight.setText(String.valueOf(currentUser.get("weight"))); // need info

            tvDate.setText(DateSummary.getDate());
            tvWeather.setText(DateSummary.getWeather());
        }
}