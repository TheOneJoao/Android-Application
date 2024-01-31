package com.outspin.app.ui.camera;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.outspin.app.R;

import java.io.File;

public class DisplayVideoActivity extends AppCompatActivity {
    private VideoView videoView;
    private ImageButton imageButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_video_activity);

        videoView = (VideoView) findViewById(R.id.videoView);
        imageButton = (ImageButton) findViewById(R.id.videoToCameraButton);

        Intent intent = getIntent();
        VideoFile videoFile = (VideoFile) intent.getSerializableExtra("VideoFile");

        videoView.setVideoURI(Uri.fromFile(new File(videoFile.getVideoFileName())));
        videoView.start();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoView.start();
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent videoToCamera = new Intent(DisplayVideoActivity.this, CameraActivity.class);
                startActivity(videoToCamera);
            }
        });

    }
}

