package edu.csueb.codepath.fitness_tracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.csueb.codepath.fitness_tracker.fragments.HomeFragment;

public class HomeWorkoutListAdapter extends RecyclerView.Adapter<HomeWorkoutListAdapter.ViewHolder> {

    private List<String> workout;   //name for each workout
    private List<String> time;  //time for each individual workout
    private List<String> indivCalor;    //individual calories per workout
    private HomeFragment activity;

    public HomeWorkoutListAdapter(){

    }

    public HomeWorkoutListAdapter(List<String> w, List<String> t, List<String> i, HomeFragment fragment){
        this.workout = w;
        this.time = t;
        this.indivCalor = i;
        this.activity = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_workout, parent, false);
        RecyclerView rc;
        rc = parent.findViewById(R.id.rvWorkouts);
        rc.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(parent.getContext(), R.anim.layout_fade_animation));

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(workout.get(position), time.get(position), indivCalor.get(position));
        //RecyclerView rc;
        //holder.rc.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(holder.rc.getContext(), R.anim.layout_fade_animation));
    }

    //workout and calories list will contain the same number of elements
    @Override
    public int getItemCount() {
        return workout.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvWorkout;
        public TextView time;
        public TextView calo;
        public CardView cardView;
        public RecyclerView rc;

        public ViewHolder(View itemView) {
            super(itemView);
            tvWorkout = itemView.findViewById(R.id.tvWorkoutHome);
            time = itemView.findViewById(R.id.tvTimeHome);
            calo = itemView.findViewById(R.id.tvIndivCalories);
            cardView = itemView.findViewById(R.id.cardWorkoutHome);
            rc = itemView.findViewById(R.id.rvWorkouts);
        }

        public void bind(String wrk, String t, String c){
            tvWorkout.setText(wrk);
            time.setText(t);
            calo.setText(c);
            cardView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(itemView.getContext(), R.anim.layout_fade_animation));

        }

    }
}
