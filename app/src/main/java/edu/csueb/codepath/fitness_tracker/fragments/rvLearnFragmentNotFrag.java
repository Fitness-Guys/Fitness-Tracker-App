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
        String category = getIntent().getStringExtra("category").toString();

        Log.i("rvLearnFragmentNotFrag", "|||||||||||||||||||category: " + category);

        String url;

        switch (category) {
            case "Yoga_query":
                url = YoutubeAPI.BASE_URL + YoutubeAPI.search + YoutubeAPI.part
                        + YoutubeAPI.max + YoutubeAPI.order + YoutubeAPI.Crossfit_query
                        + YoutubeAPI.type + YoutubeAPI.API_KEY;
                break;
            default:
                url = YoutubeAPI.BASE_URL + YoutubeAPI.search + YoutubeAPI.part
                        + YoutubeAPI.max + YoutubeAPI.order + YoutubeAPI.Yoga_query
                        + YoutubeAPI.type + YoutubeAPI.API_KEY;
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
            public void onFailure(Call<JSON_TutorialResponseModel> call, Throwable t) {

            }
        });
    }
}