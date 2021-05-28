package edu.csueb.codepath.fitness_tracker;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Workout.class); //We will need to connect to the database of workout here.


        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("mTgNYUC7mlx5G9v1vGQuatFrdiFr6sWGYTV2TnMc")
                .clientKey("Lldz8npc4JQiFtox5pyYnKwE76V6gxOvet3KU8xO")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
