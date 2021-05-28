package edu.csueb.codepath.fitness_tracker.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import org.parceler.Parcels;

import java.util.List;

import edu.csueb.codepath.fitness_tracker.R;
import edu.csueb.codepath.fitness_tracker.TutorialPlayback;
import edu.csueb.codepath.fitness_tracker.fragments.rvLearnFragmentNotFrag;
import edu.csueb.codepath.fitness_tracker.models_tutorials.VideoYT;

public class LearnListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<String> categoryList;

    public LearnListAdapter(Context context, List<String> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    public class Holder extends RecyclerView.ViewHolder{

        ImageView imageView, ivPosterOverlay;
        TextView tvTitle, tvDescription;
        CardView cardView;
        RelativeLayout rvContainer;
        //RecyclerView rvLearnWorkout;

        public Holder(@NonNull View itemView) {
            super(itemView);
            //imageView = itemView.findViewById(R.id.imageView);
            tvTitle = itemView.findViewById(R.id.cardViewTextBox);
            //rvLearnWorkout = itemView.findViewById(R.id.card_learn_item);
            //tvDescription = itemView.findViewById(R.id.tvDescription);
            // rvContainer = itemView.findViewById(R.id.card_learn_item);
            // ivPosterOverlay = itemView.findViewById(R.id.ivPosterOverlay);
            cardView = itemView.findViewById(R.id.card_learn_item);
        }

        public void setData(String category) {
            String getTitle = category;

            tvTitle.setText(getTitle);

//            rvContainer.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent i = new Intent(itemView.getContext(), TutorialPlayback.class);
//                    i.putExtra("videoYT", Parcels.wrap(videoYT));
//                    Pair<View, String> ImageVideo = Pair.create((View) imageView, "ImageVideoName");
//                    Pair<View, String> Title = Pair.create((View) tvTitle, "tvTitleName");
//                    Pair<View, String> Description = Pair.create((View) tvTitle, "tvDescriptionName");
//                    ActivityOptionsCompat options = ActivityOptionsCompat.
//                            makeSceneTransitionAnimation((Activity) itemView.getContext(), ImageVideo, Title, Description);
//
//                    //i.putExtra("title", videoYT.getSnippet().getTitle());
//                    //i.putExtra("description", videoYT.getSnippet().getDescription());
//                    //i.putExtra("video_url", videoYT.getId().getVideoId());
//                    itemView.getContext().startActivity(i, options.toBundle());
//                }
//            });

            tvTitle.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), rvLearnFragmentNotFrag.class);
                    if(category == "Before Workout Stretches"){
                        intent.putExtra("category", "StretchingBefore_query");
                        itemView.getContext().startActivity(intent);
                    } else if (category == "Yoga") {
                        intent.putExtra("category", "Yoga_query");
                        itemView.getContext().startActivity(intent);
                    } else if (category == "Pilates") {
                        intent.putExtra("category", "Pilates_query");
                        itemView.getContext().startActivity(intent);
                    } else if (category == "HIIT Training") {
                        intent.putExtra("category", "HIIT_query");
                        itemView.getContext().startActivity(intent);
                    } else if (category == "Endurance Training") {
                        intent.putExtra("category", "Endurance_query");
                        itemView.getContext().startActivity(intent);
                    } else if (category == "Aerobic Training") {
                        intent.putExtra("category", "Aerobic_query");
                        itemView.getContext().startActivity(intent);
                    } else if (category == "Balance Training") {
                        intent.putExtra("category", "Balance_query");
                        itemView.getContext().startActivity(intent);
                    } else if (category == "Weight Loss") {
                        intent.putExtra("category", "WeightLoss_query");
                        itemView.getContext().startActivity(intent);
                    } else if (category == "Weight Training") {
                        intent.putExtra("category", "WeightTraining_query");
                        itemView.getContext().startActivity(intent);
                    } else if (category == "Crossfit") {
                        intent.putExtra("category", "Crossfit_query");
                        itemView.getContext().startActivity(intent);
                    } else if (category == "Core Building") {
                        intent.putExtra("category", "CoreBuilding_query");
                        itemView.getContext().startActivity(intent);
                    } else if (category == "Strength Building") {
                        intent.putExtra("category", "StrengthBuilding_query");
                        itemView.getContext().startActivity(intent);
                    } else if (category == "No Equipment Strength Training") {
                        intent.putExtra("category", "NoEquipmentStrength_query");
                        itemView.getContext().startActivity(intent);
                    } else if (category == "After Workout Stretches") {
                        intent.putExtra("category", "StretchingAfter_query");
                        itemView.getContext().startActivity(intent);
                    }else if (category == "After Workout Meditation") {
                        intent.putExtra("category", "MeditationAfter_query");
                        itemView.getContext().startActivity(intent);
                    } else {
                        Log.i("LearnFragment", "onItemClick: intent if check failed");
                    }
                }
            });
            //cardView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(itemView.getContext(), R.anim.layout_fade_animation));
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.learn_item_layout, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String category = categoryList.get(position);
        LearnListAdapter.Holder hold = (LearnListAdapter.Holder) holder;
        hold.setData(category);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
