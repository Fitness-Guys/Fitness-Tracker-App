package edu.csueb.codepath.fitness_tracker;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import edu.csueb.codepath.fitness_tracker.fragments.TrackFragment;

public class WorkoutListAdapter extends RecyclerView.Adapter<WorkoutListAdapter.ViewHolder> {

    public interface OnClickListener{
        void onItemClicked(int position);
    }

    private List<String> workouts;
    private List<CheckBox> workoutCheckboxs;
    private TrackFragment activity;

    OnClickListener clickListener;

    public WorkoutListAdapter(List<String> list, TrackFragment trackFragment){ //, OnClickListener clicklistener
        this.workouts = list;
        this.activity = trackFragment;
        //this.clickListener = clicklistener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.workout_task_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String item = workouts.get(position);
        holder.task.setText(item);

    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    public void setWorkouts(List<String> workoutList){
        this.workouts = workoutList;
        this.notifyDataSetChanged();
    }

    /*
    public List<String> getChecked(){
        List<String> checked = new ArrayList<>();
        for(CheckBox item: workoutCheckboxs){
            if(item.isChecked()){
                checked.add(item.getText().toString());
            }
        }

        return checked;
    }
    */

    class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox task;

        public ViewHolder(View view){
            super(view);
            task = (CheckBox) view.findViewById(R.id.checkbox);
            //workoutCheckboxs.add(task);
        }

        public void bind(String item) {
            task.setText(item);

            task.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClicked(getAdapterPosition());
                }
            });
        }
    }
}
