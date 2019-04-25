package com.example.escalable.Activities;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.escalable.Adapters.Expandiblecoursesinfo_adapter;
import com.example.escalable.Adapters.Expandiblecoursesvideos_adapter;
import com.example.escalable.Class.Data;
import com.example.escalable.Models.modules;
import com.example.escalable.R;
import com.example.escalable.Singletones.VolleyS;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.lang.reflect.Type;
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

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        listDataHeader = new ArrayList<>();
        listView = (ExpandableListView)findViewById(R.id.expandibllistvideos);
        FillIn();

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

    private void FillIn() {
        JSONArray course = new JSONArray();
        try {
            course.put(0, getIntent().getExtras().getString("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonArrayRequest jar = new JsonArrayRequest(
                Request.Method.POST,
                Data.url + "showmodules",
                course,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<modules>>(){}.getType();
                        List<modules> lm = gson.fromJson(response.toString(), type);


                        listDataHeader = new ArrayList<>();
                        listHash = new HashMap<>();

                        for (int i = 0; i<lm.size(); i++) {
                            List<String> list1 = new ArrayList<>();
                            listDataHeader.add(lm.get(i).getNombre());
                            for (int x = 0; x<lm.get(i).getVideos().size(); x ++)
                            {
                                list1.add(lm.get(i).getVideos().get(x).getNombre());
                            }
                            listHash.put(listDataHeader.get(i),list1);
                        }
                        listCourseInfo = new Expandiblecoursesvideos_adapter(getApplicationContext(),listDataHeader,listHash);
                        listView.setAdapter(listCourseInfo);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Hay un problema", Toast.LENGTH_SHORT).show();
            }
        });
        VolleyS.getinstance(getApplicationContext()).getRq().add(jar);

    }


}
