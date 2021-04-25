package edu.csueb.codepath.fitness_tracker.models_tutorials;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class VideoYT {

    @SerializedName("id")
    @Expose
    protected VideoID id;

    @SerializedName("snippet")
    @Expose
    protected SnippetYT snippet;

    public VideoYT() { }

    public VideoYT(VideoID id, SnippetYT snippet) {
        this.id = id;
        this.snippet = snippet;
    }

    public VideoID getId() {
        return id;
    }

    public void setId(VideoID id) {
        this.id = id;
    }

    public SnippetYT getSnippet() {
        return snippet;
    }

    public void setSnippet(SnippetYT snippet) {
        this.snippet = snippet;
    }
}
