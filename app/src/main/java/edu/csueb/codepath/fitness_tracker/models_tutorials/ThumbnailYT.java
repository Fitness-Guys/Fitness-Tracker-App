package edu.csueb.codepath.fitness_tracker.models_tutorials;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ThumbnailYT {

    @SerializedName("medium")
    @Expose
    private MediumThumbnail medium;

    public ThumbnailYT() { }

    public ThumbnailYT(MediumThumbnail medium) {
        this.medium = medium;
    }

    public MediumThumbnail getMedium() {
        return medium;
    }

    public void setMedium(MediumThumbnail medium) {
        this.medium = medium;
    }

    public class MediumThumbnail {

        @SerializedName("url")
        @Expose
        private String url;

        public MediumThumbnail() { }

        public MediumThumbnail(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
