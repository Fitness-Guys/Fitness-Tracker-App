package edu.csueb.codepath.fitness_tracker.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.csueb.codepath.fitness_tracker.R;
import edu.csueb.codepath.fitness_tracker.adapters.TutorialAdapterHome;
import edu.csueb.codepath.fitness_tracker.models_tutorials.JSON_TutorialResponseModel;
import edu.csueb.codepath.fitness_tracker.models_tutorials.VideoYT;
import edu.csueb.codepath.fitness_tracker.network.YoutubeAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class rvLearnFragmentNotFrag extends AppCompatActivity {

    Context context;
    List<VideoYT> tutorials;
    public TutorialAdapterHome tutorialAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_rv_learn_not);

        RecyclerView rvTutorials = findViewById(R.id.rvTutorials);
        tutorials = new ArrayList<>();

        tutorialAdapter = new TutorialAdapterHome(context, tutorials);
        rvTutorials.setAdapter(tutorialAdapter);
        rvTutorials.setLayoutManager(new LinearLayoutManager(context));

        if(tutorials.size() == 0) {
            getJson();
        }
    }

    private void getJson() {
        String category = getIntent().getStringExtra("category");
        Log.i("rvLearnFragmentNotFrag", "|||||||||||||||||||category: " + category);

        String YT_API_KEY = YoutubeAPI.API_KEY + getString(R.string.YOUTUBE_API);
        String url;

        switch (category) {
            case "StretchingBefore_query":
                url = YoutubeAPI.BASE_URL + YoutubeAPI.search + YoutubeAPI.part + YoutubeAPI.max + YoutubeAPI.order
                        + YoutubeAPI.StretchingBefore_query + YoutubeAPI.type + YT_API_KEY;
                break;
            case "Yoga_query":
                url = YoutubeAPI.BASE_URL + YoutubeAPI.search + YoutubeAPI.part + YoutubeAPI.max + YoutubeAPI.order
                        + YoutubeAPI.Yoga_query + YoutubeAPI.type + YT_API_KEY;
                break;
            case "Pilates_query":
                url = YoutubeAPI.BASE_URL + YoutubeAPI.search + YoutubeAPI.part + YoutubeAPI.max + YoutubeAPI.order
                        + YoutubeAPI.Pilates_query + YoutubeAPI.type + YT_API_KEY;
                break;
            case "HIIT_query":
                url = YoutubeAPI.BASE_URL + YoutubeAPI.search + YoutubeAPI.part + YoutubeAPI.max + YoutubeAPI.order
                        + YoutubeAPI.HIIT_query + YoutubeAPI.type + YT_API_KEY;
                break;
            case "Endurance_query":
                url = YoutubeAPI.BASE_URL + YoutubeAPI.search + YoutubeAPI.part + YoutubeAPI.max + YoutubeAPI.order
                        + YoutubeAPI.Endurance_query + YoutubeAPI.type + YT_API_KEY;
                break;
            case "Aerobic_query":
                url = YoutubeAPI.BASE_URL + YoutubeAPI.search + YoutubeAPI.part + YoutubeAPI.max + YoutubeAPI.order
                        + YoutubeAPI.Aerobic_query + YoutubeAPI.type + YT_API_KEY;
                break;
            case "Balance_query":
                url = YoutubeAPI.BASE_URL + YoutubeAPI.search + YoutubeAPI.part + YoutubeAPI.max + YoutubeAPI.order
                        + YoutubeAPI.Balance_query + YoutubeAPI.type + YT_API_KEY;
                break;
            case "WeightLoss_query":
                url = YoutubeAPI.BASE_URL + YoutubeAPI.search + YoutubeAPI.part + YoutubeAPI.max + YoutubeAPI.order
                        + YoutubeAPI.WeightLoss_query + YoutubeAPI.type + YT_API_KEY;
                break;
            case "WeightTraining_query":
                url = YoutubeAPI.BASE_URL + YoutubeAPI.search + YoutubeAPI.part + YoutubeAPI.max + YoutubeAPI.order
                        + YoutubeAPI.WeightTraining_query + YoutubeAPI.type + YT_API_KEY;
                break;
            case "Crossfit_query":
                url = YoutubeAPI.BASE_URL + YoutubeAPI.search + YoutubeAPI.part + YoutubeAPI.max + YoutubeAPI.order
                        + YoutubeAPI.Crossfit_query + YoutubeAPI.type + YT_API_KEY;
                break;
            case "CoreBuilding_query":
                url = YoutubeAPI.BASE_URL + YoutubeAPI.search + YoutubeAPI.part + YoutubeAPI.max + YoutubeAPI.order
                        + YoutubeAPI.CoreBuilding_query + YoutubeAPI.type + YT_API_KEY;
                break;
            case "StrengthBuilding_query":
                url = YoutubeAPI.BASE_URL + YoutubeAPI.search + YoutubeAPI.part + YoutubeAPI.max + YoutubeAPI.order
                        + YoutubeAPI.StrengthBuilding_query + YoutubeAPI.type + YT_API_KEY;
                break;
            case "NoEquipmentStrength_query":
                url = YoutubeAPI.BASE_URL + YoutubeAPI.search + YoutubeAPI.part + YoutubeAPI.max + YoutubeAPI.order
                        + YoutubeAPI.NoEquipmentStrength_query + YoutubeAPI.type + YT_API_KEY;
                break;
            case "StretchingAfter_query":
                url = YoutubeAPI.BASE_URL + YoutubeAPI.search + YoutubeAPI.part + YoutubeAPI.max + YoutubeAPI.order
                        + YoutubeAPI.StretchingAfter_query + YoutubeAPI.type + YT_API_KEY;
                break;
            case "MeditationAfter_query":
                url = YoutubeAPI.BASE_URL + YoutubeAPI.search + YoutubeAPI.part + YoutubeAPI.max + YoutubeAPI.order
                        + YoutubeAPI.MeditationAfter_query + YoutubeAPI.type + YT_API_KEY;
                break;
            default:
                url = YoutubeAPI.BASE_URL + YoutubeAPI.search + YoutubeAPI.part + YoutubeAPI.max + YoutubeAPI.order
                        + YoutubeAPI.Workout_query + YoutubeAPI.type + YT_API_KEY;
                break;
        }
        Call<JSON_TutorialResponseModel> data = YoutubeAPI.getVideoAPI().getYT(url);
        data.enqueue(new Callback<JSON_TutorialResponseModel>() {
            @Override
            public void onResponse(Call<JSON_TutorialResponseModel> call, Response<JSON_TutorialResponseModel> response) {
                if (response.errorBody() == null) {
                    JSON_TutorialResponseModel responseModel = response.body();

                    tutorials.addAll(responseModel.getItems());
                    tutorialAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<JSON_TutorialResponseModel> call, Throwable t) { }
        });
    }
}