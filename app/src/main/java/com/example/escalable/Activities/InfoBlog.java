package com.example.escalable.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.escalable.Adapters.Comments_adapter;
import com.example.escalable.Class.Data;
import com.example.escalable.Models.comments;
import com.example.escalable.R;
import com.example.escalable.Singletones.VolleyS;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.List;

public class InfoBlog extends AppCompatActivity implements View.OnClickListener {
    ImageView blogimage;
    TextView bloginfo, blogtitle, excerpt, blogcontent, user_text;
    RecyclerView recyclerView;
    EditText comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_blog);

        blogimage = findViewById(R.id.blogimage);
        bloginfo = findViewById(R.id.bloginfo);
        blogtitle = findViewById(R.id.blogtitle);
        excerpt = findViewById(R.id.excerpt);
        blogcontent = findViewById(R.id.blogcontent);
        recyclerView = findViewById(R.id.comments_container);
        user_text = findViewById(R.id.user_comment);
        user_text.setText(Data.user_name);

        findViewById(R.id.cancel_commentt).setOnClickListener(this);
        findViewById(R.id.post_commment).setOnClickListener(this);

        comment = findViewById(R.id.edit_comment);

        comments.ShowComments(getIntent().getExtras().getString("id"), recyclerView, this);

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

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.post_commment:
                comments.makecomment(getApplicationContext(), getIntent().getExtras().getString("id"), comment.getText().toString());
                break;
            case R.id.cancel_commentt:
                comment.setText(" ");
                break;
        }
    }
}
