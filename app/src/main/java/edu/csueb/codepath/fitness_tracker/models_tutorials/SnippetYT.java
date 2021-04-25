package edu.csueb.codepath.fitness_tracker.models_tutorials;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.text.DateFormat;
import java.util.Date;

@Parcel
public class SnippetYT {

    protected Date publishedAt;
    @SerializedName("title")
    @Expose
    protected String title;
    @SerializedName("description")
    @Expose
    protected String description;
    @SerializedName("thumbnails")
    @Expose
    protected ThumbnailYT thumbnails;

    public SnippetYT() { }

    public SnippetYT(String title, String description, ThumbnailYT thumbnails) {
        this.title = title;
        this.description = description;
        this.thumbnails = thumbnails;
    }

    public SnippetYT(Date publishedAt, String title, String description, ThumbnailYT thumbnails) {
        this.publishedAt = publishedAt;
        this.title = title;
        this.description = description;
        this.thumbnails = thumbnails;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ThumbnailYT getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(ThumbnailYT thumbnails) {
        this.thumbnails = thumbnails;
    }
}
