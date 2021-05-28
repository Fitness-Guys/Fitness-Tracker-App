package edu.csueb.codepath.fitness_tracker.models_tutorials;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class JSON_TutorialResponseModel {

    @SerializedName("nextPageToken")
    @Expose
    protected String nextPageToken;
    @SerializedName("items")
    @Expose
    protected List<VideoYT> items;

    public JSON_TutorialResponseModel() { }

    public JSON_TutorialResponseModel(String nextPageToken, List<VideoYT> items) {
        this.nextPageToken = nextPageToken;
        this.items = items;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public List<VideoYT> getItems() {
        return items;
    }

    public void setItems(List<VideoYT> items) {
        this.items = items;
    }
}
