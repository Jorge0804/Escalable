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

import com.example.escalable.Adapters.Expandiblecoursesinfo_adapter;
import com.example.escalable.Class.Data;
import com.example.escalable.R;
import com.squareup.picasso.Picasso;

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

        listDataHeader = new ArrayList<>();
        listView = (ExpandableListView)findViewById(R.id.expandibleinfocourses);
        Iniciar();

        listCourseInfo = new Expandiblecoursesinfo_adapter(this,listDataHeader,listHash);
        listView.setAdapter(listCourseInfo);





        InfoCourseName = findViewById(R.id.InfoCourseName);
        InfoCourseInfo = findViewById(R.id.InfoCourseInfo);
        InfoCourseImage = findViewById(R.id.InfoCourseImage);
        InfoCourseSeeCourse = findViewById(R.id.InfoCourseSeeCourse);

        InfoCourseSeeCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), VideoCourse.class);
                in.putExtra("name", getIntent().getExtras().getString("name"));
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
