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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import edu.csueb.codepath.fitness_tracker.R;

public class LearnFragment extends Fragment {

    ListView listView;

    public LearnFragment() {
        // Required empty public constructor
    }

    // The onCreateView method is called when Fragment should create its View object hierarchy
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_learn, container, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here. E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.listView);
        String [] categories = new String[] {
                "Before Workout Stretches", "Yoga", "Pilates", "HIIT Training", "Endurance Training",
                "Aerobic Training", "Balance Training", "Weight Loss", "Weight Training", "Crossfit",
                "Core Building", "Strength Building", "No Equipment Strength Training",
                "After Workout Stretches", "After Workout Meditation",
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_expandable_list_item_1,
                android.R.id.text1, categories);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), rvLearnFragmentNotFrag.class);
                if(i == 0) {
                    intent.putExtra("category", "StretchingBefore_query");
                    startActivity(intent);
                } else if (i==1) {
                    intent.putExtra("category", "Yoga_query");
                    startActivity(intent);
                } else if (i==2) {
                    intent.putExtra("category", "Pilates_query");
                    startActivity(intent);
                } else if (i==3) {
                    intent.putExtra("category", "HIIT_query");
                    startActivity(intent);
                } else if (i==4) {
                    intent.putExtra("category", "Endurance_query");
                    startActivity(intent);
                } else if (i==5) {
                    intent.putExtra("category", "Aerobic_query");
                    startActivity(intent);
                } else if (i==6) {
                    intent.putExtra("category", "Balance_query");
                    startActivity(intent);
                } else if (i==7) {
                    intent.putExtra("category", "WeightLoss_query");
                    startActivity(intent);
                } else if (i==8) {
                    intent.putExtra("category", "WeightTraining_query");
                    startActivity(intent);
                } else if (i==9) {
                    intent.putExtra("category", "Crossfit_query");
                    startActivity(intent);
                } else if (i==10) {
                    intent.putExtra("category", "CoreBuilding_query");
                    startActivity(intent);
                } else if (i==11) {
                    intent.putExtra("category", "StrengthBuilding_query");
                    startActivity(intent);
                } else if (i==12) {
                    intent.putExtra("category", "NoEquipmentStrength_query");
                    startActivity(intent);
                } else if (i==13) {
                    intent.putExtra("category", "StretchingAfter_query");
                    startActivity(intent);
                } else if (i==14) {
                    intent.putExtra("category", "MeditationAfter_query");
                    startActivity(intent);
                } else {
                    Log.i("LearnFragment", "onItemClick: intent if check failed");
                }
            }
        });
    }
}