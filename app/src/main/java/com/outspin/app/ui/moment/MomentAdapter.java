package com.outspin.app.ui.moment;

import android.app.DownloadManager;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.outspin.app.R;
import com.outspin.app.data.DummieData;
import com.outspin.app.data.Macros;
import com.outspin.app.databinding.MomentItemBinding;
import com.outspin.app.models.Moment;
import com.outspin.app.network.NetworkManager;

import java.util.ArrayList;

public class MomentAdapter extends RecyclerView.Adapter<MomentAdapter.MomentViewHolder> {
    private final ArrayList<Moment> momentItems;
    private MomentItemBinding itemBinding;
    Context context;

    private int numOfSwipes = 0;

    public MomentAdapter(ArrayList<Moment> items) {
        this.momentItems = items;
    }
    public MomentAdapter(Context context) {
        // info from server
        this.context = context;
        this.momentItems = new ArrayList<>(DummieData.DUMMY_MOMENTS);
    }

    @NonNull
    @Override
    public MomentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        itemBinding = MomentItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new MomentViewHolder(
                inflater.inflate(R.layout.moment_item, parent, false), itemBinding
        );
    }

    //  async task goes here
    @Override
    public void onBindViewHolder(@NonNull MomentViewHolder holder, int position) {
        // misses asyncTask
        if(momentItems != null && momentItems.size() > 0) {
            position = position % momentItems.size();

            // async task (holder, moment item)
            //new NetworkManager.MomentLoader(holder).execute();
            new NetworkManager.BindMoment(this.context, holder).execute();

            //holder.bindMomentData(momentItems.get(position));   // do in async task


            if (numOfSwipes % Macros.CONST_INTERNET_NUM_OF_DOWNLOADED_MOMENTS == 0) {
                momentItems.add(DummieData.DUMMY_MOMENTS_FULL.get(numOfSwipes % DummieData.DUMMY_MOMENTS_FULL.size()));
                Log.d("ONBIND: ", "NEW DOWNLOAD OF DATA; new length: " + momentItems.size());
            }

            numOfSwipes++;
        } else {
            Log.d("ONBIND: ", "No new download data to show");
        }
    }

    @Override
    public int getItemCount() {
        return (momentItems != null && momentItems.size() > 0) ? Integer.MAX_VALUE : 0;
    }

    /*  WHY BINDING NOT WORKING??   */
    public static class MomentViewHolder extends RecyclerView.ViewHolder {

        private final VideoView videoView;
        private final TextView titleTextView;
        private final TextView descriptionTextView;
        private final ProgressBar loadingBar;

        private final MomentItemBinding itemBinding;

        public MomentViewHolder(@NonNull View momentView, MomentItemBinding itemBinding) {
            super(momentView);

            this.itemBinding = itemBinding;

            //LayoutInflater inflater = LayoutInflater.from(momentView.getContext());
            //itemBinding = MomentItemBinding.inflate(inflater);

            videoView = momentView.findViewById(R.id.vv_moment_video);
            titleTextView = momentView.findViewById(R.id.tv_title);
            descriptionTextView = momentView.findViewById(R.id.tv_description);
            loadingBar = momentView.findViewById(R.id.pb_loading);

            //itemBinding.tvTitle.setText("yo");
        }

        public void bindMomentData(Moment moment) {
            //itemBinding.tvTitle.setText("yo");

            titleTextView.setText((moment.getMomentTitle()));
            descriptionTextView.setText(moment.getMomentDisco());
            //itemBinding.tvTitle.setText(moment.getMomentTitle());
            //itemBinding.tvDescription.setText(moment.getMomentDisco());

            // async task here

            videoView.setVideoPath(moment.getUri());
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    loadingBar.setVisibility(View.GONE);

                    mediaPlayer.start();

                    float videoRatio = mediaPlayer.getVideoWidth() / (float)mediaPlayer.getVideoHeight();
                    float screenRatio = videoView.getWidth() / (float)videoView.getHeight();
                    float scale  = videoRatio / screenRatio;

                    if (scale >= 1f) videoView.setScaleX(scale);
                    else videoView.setScaleY(1f / scale);
                }
            });

            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });

        }

    }
}
