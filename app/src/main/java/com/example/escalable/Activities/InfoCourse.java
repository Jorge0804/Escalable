package com.example.escalable.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.escalable.Class.Data;
import com.example.escalable.R;
import com.squareup.picasso.Picasso;

public class InfoCourse extends AppCompatActivity {
    TextView InfoCourseName, InfoCourseInfo;
    ImageView InfoCourseImage;
    Button InfoCourseSeeCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_course);

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
}
