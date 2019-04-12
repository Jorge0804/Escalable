package com.example.escalable.Activities;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.escalable.R;

import org.w3c.dom.Text;

public class VideoCourse extends AppCompatActivity {
    TextView VideoCoursename;
    VideoView VideoCourseVideo;
    Uri link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_course);

        VideoCourseVideo = findViewById(R.id.VideoCourseVideo);
        VideoCoursename = findViewById(R.id.VideoCoursename);

        VideoCoursename.setText(getIntent().getExtras().getString("name"));

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(VideoCourseVideo);

        link = Uri.parse("http://toshito.mipantano.com/Videos/presentacion.mp4");
        VideoCourseVideo.setVideoURI(link);
        VideoCourseVideo.setMediaController(mediaController);

        VideoCourseVideo.start();


    }
}
