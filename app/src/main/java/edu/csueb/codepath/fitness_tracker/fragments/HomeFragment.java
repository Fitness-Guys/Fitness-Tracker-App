package edu.csueb.codepath.fitness_tracker.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.w3c.dom.Text;

import edu.csueb.codepath.fitness_tracker.DateSummary;
import edu.csueb.codepath.fitness_tracker.LoginActivity;
import edu.csueb.codepath.fitness_tracker.R;

public class HomeFragment extends Fragment {

    TextView tvUserName;
    TextView tvDate;
    TextView tvWeather;
    ImageButton btnEdit;

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


        //tvUserName.setText(); // need info
        //tvDate.setText(DateSummary.getDate());
        //tvWeather.setText(DateSummary.getWeather());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeFragment.this, MainActivity2.class);
                startActivity(i);
            }
        }

    }
}