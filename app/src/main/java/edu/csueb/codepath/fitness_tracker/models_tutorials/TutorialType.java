package edu.csueb.codepath.fitness_tracker.models_tutorials;

import org.parceler.Parcel;

public class TutorialType {

    protected String Title;
    protected String Description;
    protected String video_URL;
    protected String image_URL;

    public TutorialType(String title, String description, String video_URL) {
        Title = title;
        Description = description;
        this.video_URL = video_URL;
    }

    public TutorialType(String title, String description, String video_URL, String image_URL) {
        Title = title;
        Description = description;
        this.video_URL = video_URL;
        this.image_URL = image_URL;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public String getVideo_URL() {
        return video_URL;
    }

    public String getImage_URL() {
        return image_URL;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setVideo_URL(String video_URL) {
        this.video_URL = video_URL;
    }

    public void setImage_URL(String image_URL) {
        this.image_URL = image_URL;
    }
}
