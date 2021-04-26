package edu.csueb.codepath.fitness_tracker.models_tutorials;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoID {

    @SerializedName("videoId")
    @Expose
    private String videoId;

    public VideoID() { }

    public VideoID(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
