package com.example.escalable.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.escalable.Class.Data;
import com.example.escalable.R;
import com.squareup.picasso.Picasso;

public class InfoCourse extends AppCompatActivity {
    TextView InfoCourseName, InfoCourseInfo;
    ImageView InfoCourseImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_course);

        InfoCourseName = findViewById(R.id.InfoCourseName);
        InfoCourseInfo = findViewById(R.id.InfoCourseInfo);
        InfoCourseImage = findViewById(R.id.InfoCourseImage);

        InfoCourseName.setText(getIntent().getExtras().getString("name"));
        InfoCourseInfo.setText(getIntent().getExtras().getString("information"));
        Picasso
                .with(getApplicationContext())
                .load(Data.Image_url + "courses_img/" + getIntent().getExtras().getString("src"))
                .fit()
                .into(InfoCourseImage);
    }
}
