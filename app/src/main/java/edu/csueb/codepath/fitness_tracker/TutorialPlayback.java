package edu.csueb.codepath.fitness_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class TutorialPlayback extends YouTubeBaseActivity {

    protected static final String YOUTUBE_API = "AIzaSyCpHJIJMmfO6rDXXMEl-qDVKjcc-gqx7YQ";
    YouTubePlayerView youTubePlayerView;
    TextView tvTitle;
    TextView tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_playback);
        tvTitle = findViewById(R.id.tvTitlePlayback);
        tvDescription = findViewById(R.id.tvDescriptionPlayback);
        youTubePlayerView = findViewById(R.id.player);


        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        String video_url = getIntent().getStringExtra("video_url");

        youTubePlayerView.initialize(YOUTUBE_API, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo(video_url);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
            }
        });
        tvTitle.setText(title);
        tvDescription.setText(description);
    }
}