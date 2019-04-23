package com.example.escalable.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.escalable.Adapters.Expandiblecoursesinfo_adapter;
import com.example.escalable.Class.Data;
import com.example.escalable.Models.modules;
import com.example.escalable.R;
import com.example.escalable.Singletones.VolleyS;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InfoCourse extends AppCompatActivity {

    ExpandableListView listView;
    Expandiblecoursesinfo_adapter listCourseInfo;
    List<String> listDataHeader;
    HashMap<String,List<String>> listHash;
    TextView InfoCourseName, InfoCourseInfo;
    ImageView InfoCourseImage;
    Button InfoCourseSeeCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_course);

        listView = (ExpandableListView)findViewById(R.id.expandibleinfocourses);
        FillIn();

        InfoCourseName = findViewById(R.id.InfoCourseName);
        InfoCourseInfo = findViewById(R.id.InfoCourseInfo);
        InfoCourseImage = findViewById(R.id.InfoCourseImage);
        InfoCourseSeeCourse = findViewById(R.id.InfoCourseSeeCourse);

        InfoCourseSeeCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), VideoCourse.class);
                in.putExtra("name", getIntent().getExtras().getString("name"));
                in.putExtra("id", getIntent().getExtras().getString("id"));
                startActivity(in);
            }
        });

        InfoCourseName.setText(getIntent().getExtras().getString("name"));
        InfoCourseInfo.setText(getIntent().getExtras().getString("information"));
        Picasso
                .with(getApplicationContext())
                .load(Data.Image_url + "courses_img/" + getIntent().getExtras().getString("src"))
                .fit()
                .into(InfoCourseImage);
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
                                list1.add(lm.get(i).getInformation());
                                listHash.put(listDataHeader.get(i),list1);
                            }
                            listCourseInfo = new Expandiblecoursesinfo_adapter(getApplicationContext(),listDataHeader,listHash);
                            listView.setAdapter(listCourseInfo);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(InfoCourse.this, "Hay un problema", Toast.LENGTH_SHORT).show();
                }
            });
            VolleyS.getinstance(getApplicationContext()).getRq().add(jar);
        }


}
