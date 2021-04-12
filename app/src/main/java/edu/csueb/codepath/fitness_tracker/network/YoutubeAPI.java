package edu.csueb.codepath.fitness_tracker.network;

import edu.csueb.codepath.fitness_tracker.models_tutorials.JSON_TutorialResponseModel;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

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

    public static final String Yoga_query = "q=Fitness%20Workout&";
    public static final String WeightTraining_query = "q=Weight%20Training%20workout&";
    public static final String Crossfit_query = "q=Crossfit%20workout&";
    public static final String CoreBuilding_query = "q=Core%20Building%20workout&";

    public static final String type = "type=video&";
    public static final String API_KEY = "key=AIzaSyDYpic7fNdBTwnXsfImgjjiQLDgntuaDUY";

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
