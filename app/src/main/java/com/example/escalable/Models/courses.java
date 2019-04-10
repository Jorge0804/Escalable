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
import com.example.escalable.Adapters.Courses_adapter;
import com.example.escalable.R;
import com.example.escalable.Singletones.VolleyS;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

public class courses {
    Integer id;
    String name, information, src;
    Double price;

    public courses(Integer id, String name, String information, String src, Double price) {
        this.id = id;
        this.name = name;
        this.information = information;
        this.src = src;
        this.price = price;
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

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public static void ShowCourses(View v, final Context context)
    {
        final RecyclerView recyclerView;
        recyclerView = v.findViewById(R.id.courses_container);

        JsonArrayRequest jar = new JsonArrayRequest(
                Request.Method.GET,
                "http://toshito.mipantano.com/api/showcourse",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<courses>>(){}.getType();
                        List<courses> lc = gson.fromJson(response.toString(), type);
                        Courses_adapter courses_adapter = new Courses_adapter(lc);

                        recyclerView.setAdapter(courses_adapter);

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

