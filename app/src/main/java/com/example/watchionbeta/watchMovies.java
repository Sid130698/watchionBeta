package com.example.watchionbeta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class watchMovies extends AppCompatActivity {
    VideoView videoView;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_movies);
        videoView=(VideoView)findViewById(R.id.videoView);
        url= getIntent().getExtras().get("movieUrl").toString();
        videoView.setVideoURI(Uri.parse(url));
        MediaController mediaController=new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.start();

    }
}
