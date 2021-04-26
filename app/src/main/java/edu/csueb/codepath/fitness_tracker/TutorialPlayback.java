package edu.csueb.codepath.fitness_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import edu.csueb.codepath.fitness_tracker.models_tutorials.VideoYT;

public class TutorialPlayback extends YouTubeBaseActivity {

    //private final String YOUTUBE_API = getString(R.string.YOUTUBE_API);
    YouTubePlayerView youTubePlayerView;
    TextView tvTitle, tvDescription, tvPublishedAt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_playback);
        tvTitle = findViewById(R.id.tvTitlePlayback);
        tvDescription = findViewById(R.id.tvDescriptionPlayback);
        youTubePlayerView = findViewById(R.id.player);
        tvPublishedAt = findViewById(R.id.tvPublishedAt);

        VideoYT videoYT = (VideoYT) Parcels.unwrap(getIntent().getParcelableExtra("videoYT"));

        String title = videoYT.getSnippet().getTitle();
        String description = videoYT.getSnippet().getDescription();
        String video_url = videoYT.getId().getVideoId();
        long published = videoYT.getSnippet().getPublishedAt().getTime();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M/dd/yyyy");
        String simpleDateFormatString = simpleDateFormat.format(published);

        /*
        * long epochMillis = videoYT.getSnippet().getPublishedAt();
        * ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(epochMillis), ZoneId.systemDefault());
        * String date = zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        * simpleDateFormat.format(DateTimeFormatter.ofPattern(""));
        */

        //String title = getIntent().getStringExtra("title");
        //String description = getIntent().getStringExtra("description");
        //String video_url = getIntent().getStringExtra("video_url");

        youTubePlayerView.initialize(getString(R.string.YOUTUBE_API), new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                youTubePlayer.loadVideo(video_url);
             /* int orientation = getResources().getConfiguration().orientation;
                if(orientation == Configuration.ORIENTATION_PORTRAIT) {
                    youTubePlayer.loadVideo(video_url);
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    youTubePlayer.setFullscreen(true);
                    youTubePlayer.loadVideo(video_url);
                    youTubePlayer.setFullscreen(true);
                } else {
                    youTubePlayer.loadVideo(video_url);
                }
                //youTubePlayer.setFullscreen(true);*/
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
            }
        });
        tvTitle.setText(title);
        tvDescription.setText(description);
        tvPublishedAt.setText(getString(R.string.Published) + simpleDateFormatString);
    }
}