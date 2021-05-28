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

import edu.csueb.codepath.fitness_tracker.R;
import edu.csueb.codepath.fitness_tracker.UserModel;

public class ProfileFragment extends Fragment {

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

        TextView tvName;
        TextView tvUserHeight;
        TextView tvUserWeight;
        RecyclerView rvWorkouts; // working on
        ParseUser user = ParseUser.getCurrentUser();

       // only firstname is populated



//        tvName.setText(user.get("firstname").toString()); // need info
//        tvUserHeight.setText(); // need info
//        tvUserWeight.setText(); // need info



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

//        workout = new ArrayList<>();
//        adapter = new WorkoutListAdapter(this, workout);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//
//        //Recycler view setup: layout manager and the adapter
//        rvWorkouts.setLayoutManager(new LinearLayoutManager(this));
//        rvWorkouts.setAdapter(adapter);


        Log.i("ProfileFragment", "@"+ currentUser.getUsername());
        tvName.setText("@" + currentUser.getUsername());
        tvUsername.setText(currentUser.get("firstname") + " " + currentUser.get("lastname"));
        Log.i("ProfileFragment", currentUser.get("firstname") + " " + currentUser.get("lastname"));
        tvUserHeight.setText(String.valueOf(currentUser.get("height")));
        tvUserWeight.setText(String.valueOf(currentUser.get("weight")));

        /*
        File file = currentUser.get("profile_image".toString());
        Uri uri = Uri.fromFile(file);
        ivProfileImage.setImageURI(uri);*/

    }
}