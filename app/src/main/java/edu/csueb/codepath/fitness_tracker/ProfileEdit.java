package edu.csueb.codepath.fitness_tracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.parse.ParseUser;

import edu.csueb.codepath.fitness_tracker.fragments.ProfileFragment;

public class ProfileEdit extends ProfileFragment {
    UserModel profile;
    TextView etProfileImage;
    TextView etUsername;
    TextView etEmail;
    TextView etPassword1;
    TextView etPassword2;
    TextView etHeight;
    TextView etWeight;

    Button btnUpdate;

    public ProfileEdit() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etProfileImage = view.findViewById(R.id.etProfileImage);
        etUsername = view.findViewById(R.id.etUsername);
        etEmail = view.findViewById(R.id.etEmail);
        etPassword1 = view.findViewById(R.id.etPassword1);
        etPassword2 = view.findViewById(R.id.etPassword2);
        etPassword2 = view.findViewById(R.id.etPassword2);
        etWeight = view.findViewById(R.id.etWeight);
        btnUpdate = view.findViewById(R.id.btnUpdate);
    }

}
