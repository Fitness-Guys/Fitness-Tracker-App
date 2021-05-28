package edu.csueb.codepath.fitness_tracker.network;

import android.content.Context;

import edu.csueb.codepath.fitness_tracker.R;
import edu.csueb.codepath.fitness_tracker.models_tutorials.JSON_TutorialResponseModel;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;
import android.content.res.Resources;

public class YoutubeAPI {
    // https://www.googleapis.com/youtube/v3/
    // search?
    // part=snippet&
    // maxResults=40&
    // order=relevance&
    // q=Wieght Training workout&
    // type=video&
    // key=AIzaSyDYpic7fNdBTwnXsfImgjjiQLDgntuaDUY

    //  'https://youtube.googleapis.com/youtube/v3/search?part=snippet&maxResults=40&order=relevance&q=yoga%20workout&type=video&key=[YOUR_API_KEY]'

    public static final String BASE_URL = "https://www.googleapis.com/youtube/v3/";
    public static final String search = "search?";
    public static final String part = "part=snippet&";
    public static final String max = "maxResults=40&";
    public static final String  order = "order=relevance&";


    public static final String StretchingAfter_query = "q=Stretching%20After%20Workout&";
    public static final String StretchingBefore_query = "q=Stretching%20Before%20Workout&";
    public static final String MeditationAfter_query = "q=Meditation$20After%20Workout&";

    public static final String Yoga_query = "q=Fitness%20Workout&";
    public static final String Pilates_query = "q=Pilates%20Workout&";

    public static final String HIIT_query = "q=HIIT%20Workout&";
    public static final String Endurance_query = "q=Endurance%20Workout&";
    public static final String Aerobic_query = "q=Aerobic%20Workout&";
    public static final String WeightLoss_query = "q=Weight%20Loss%20Workout&";
    public static final String Balance_query = "q=Balance%20Workout&";

    public static final String WeightTraining_query = "q=Weight%20Training%20workout&";
    public static final String Crossfit_query = "q=Crossfit%20workout&";
    public static final String CoreBuilding_query = "q=Core%20Building%20workout&";
    public static final String StrengthBuilding_query = "q=Strength%20Building%20Workout&";
    public static final String NoEquipmentStrength_query = "q=No%20Equipment%20StrengthWorkout&";

    public static final String Workout_query = "q=Workout&";


    public static final String type = "type=video&";
    public static final String API_KEY = "key=";

    public interface VideoAPI {
        @GET
        Call<JSON_TutorialResponseModel> getYT(@Url String url);
    }

    private static VideoAPI videoAPI = null;
    public static VideoAPI getVideoAPI() {
        if(videoAPI == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            videoAPI = retrofit.create(VideoAPI.class);
        }
        return videoAPI;
    }
}
