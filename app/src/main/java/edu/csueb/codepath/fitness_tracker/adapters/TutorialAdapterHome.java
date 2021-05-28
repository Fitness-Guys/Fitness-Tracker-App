package edu.csueb.codepath.fitness_tracker.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import org.parceler.Parcels;

import java.util.List;

import edu.csueb.codepath.fitness_tracker.R;
import edu.csueb.codepath.fitness_tracker.TutorialPlayback;
import edu.csueb.codepath.fitness_tracker.models_tutorials.VideoYT;

public class TutorialAdapterHome extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<VideoYT> videoList;

    public TutorialAdapterHome(Context context, List<VideoYT> videoList) {
        this.context = context;
        this.videoList = videoList;
    }

    public class YoutubeHolder extends RecyclerView.ViewHolder{

        ImageView imageView, ivPosterOverlay;
        TextView tvTitle, tvDescription;
        RelativeLayout rvContainer;

        public YoutubeHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            rvContainer = itemView.findViewById(R.id.tutorialContainer);
            ivPosterOverlay = itemView.findViewById(R.id.ivPosterOverlay);
        }

        public void setData(VideoYT videoYT) {
            String getTitle = videoYT.getSnippet().getTitle();
            String getDescription = videoYT.getSnippet().getDescription();
            String getThumb = videoYT.getSnippet().getThumbnails().getMedium().getUrl();

            tvTitle.setText(getTitle);
            tvDescription.setText(getDescription);
            int radius = 20; // corner radius, higher value = more rounded
            int margin = 0; // crop margin, set to 0 for corners with no crop
            Glide.with(itemView.getContext()).load(getThumb).transform(new RoundedCorners(radius)).placeholder(R.drawable.loading).into(imageView);
            Glide.with(itemView.getContext()).load(R.drawable.play_button).into(ivPosterOverlay);

            rvContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(itemView.getContext(), TutorialPlayback.class);
                    i.putExtra("videoYT", Parcels.wrap(videoYT));
                    Pair<View, String> ImageVideo = Pair.create((View) imageView, "ImageVideoName");
                    Pair<View, String> Title = Pair.create((View) tvTitle, "tvTitleName");
                    Pair<View, String> Description = Pair.create((View) tvTitle, "tvDescriptionName");
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation((Activity) itemView.getContext(), ImageVideo, Title, Description);

                    //i.putExtra("title", videoYT.getSnippet().getTitle());
                    //i.putExtra("description", videoYT.getSnippet().getDescription());
                    //i.putExtra("video_url", videoYT.getId().getVideoId());
                    itemView.getContext().startActivity(i, options.toBundle());
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_tutorial, parent, false);
        return new YoutubeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VideoYT videoYT = videoList.get(position);
        YoutubeHolder yth = (YoutubeHolder) holder;
        yth.setData(videoYT);
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }
}
