package com.example.escalable.Activities;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.escalable.Adapters.Expandiblecoursesvideos_adapter;
import com.example.escalable.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VideoCourse extends AppCompatActivity {
    ExpandableListView listView;
    Expandiblecoursesvideos_adapter listCourseInfo;
    List<String> listDataHeader;
    HashMap<String,List<String>> listHash;
    TextView VideoCoursename;
    VideoView VideoCourseVideo;
    Uri link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_course);

        listDataHeader = new ArrayList<>();
        listView = (ExpandableListView)findViewById(R.id.expandibllistvideos);
        Iniciar();

        listCourseInfo = new Expandiblecoursesvideos_adapter(this,listDataHeader,listHash);
        listView.setAdapter(listCourseInfo);




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

    private void Iniciar() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        List<String> list1 = new ArrayList<>();
        list1.add("Google Map Google MapGoogle MapGoogle MapGoogle MapGoogle MapGoogle MapGoogle MapGoogle MapGoogle MapGoogle MapGoogle MapGoogle MapGoogle MapGoogle MapGoogle MapGoogle Map");

        List<String> list2 = new ArrayList<>();
        list2.add("Xamarin Expandable ListView");

        listDataHeader.add("Kotlin");
        listDataHeader.add("JavaScript");

        listHash.put(listDataHeader.get(0),list2);
        listHash.put(listDataHeader.get(1),list1);

    }


}
