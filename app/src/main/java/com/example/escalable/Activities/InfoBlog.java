package com.example.escalable.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.escalable.Class.Data;
import com.example.escalable.R;
import com.squareup.picasso.Picasso;

public class InfoBlog extends AppCompatActivity {
    ImageView blogimage;
    TextView bloginfo, blogtitle, excerpt, blogcontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_blog);

        blogimage = findViewById(R.id.blogimage);
        bloginfo = findViewById(R.id.bloginfo);
        blogtitle = findViewById(R.id.blogtitle);
        excerpt = findViewById(R.id.excerpt);
        blogcontent = findViewById(R.id.blogcontent);

        bloginfo.setText(getIntent().getExtras().getString("created"));
        blogtitle.setText(getIntent().getExtras().getString("name"));
        excerpt.setText(getIntent().getExtras().getString("excerpt"));
        blogcontent.setText(Html.fromHtml(getIntent().getExtras().getString("description")));
        Picasso
                .with(getApplicationContext())
                .load(Data.Image_url + "posts_img/" + getIntent().getExtras().getString("file"))
                .fit()
                .into(blogimage);
    }
}
