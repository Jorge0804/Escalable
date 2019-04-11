package com.example.escalable.Models;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.escalable.Adapters.Blogs_adapter;
import com.example.escalable.Adapters.Courses_adapter;
import com.example.escalable.R;
import com.example.escalable.Singletones.VolleyS;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

public class blogs {
    Integer id;
    String name, excerpt, file, description, created_at;

    public blogs(Integer id, String name, String excerpt, String file, String description, String created_at) {
        this.id = id;
        this.name = name;
        this.excerpt = excerpt;
        this.file = file;
        this.description = description;
        this.created_at = created_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public static void ShowBlogs(View v, final Context context)
    {
        final RecyclerView recyclerView;
        recyclerView = v.findViewById(R.id.blogs_container);

        JsonArrayRequest jar = new JsonArrayRequest(
                Request.Method.GET,
                "http://toshito.mipantano.com/api/showposts",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<blogs>>(){}.getType();
                        List<blogs> lb = gson.fromJson(response.toString(), type);
                        Blogs_adapter blogs_adapter = new Blogs_adapter(lb);
                        recyclerView.setAdapter(blogs_adapter);
                        LinearLayoutManager lm = new LinearLayoutManager(context);
                        lm.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Verifica tu conexión a internet", Toast.LENGTH_SHORT).show();
            }
        });
        VolleyS.getinstance(context).getRq().add(jar);
    }
}