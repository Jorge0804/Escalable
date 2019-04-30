package com.example.escalable.Models;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.escalable.Activities.InfoCourse;
import com.example.escalable.Adapters.Courses_adapter;
import com.example.escalable.Class.Data;
import com.example.escalable.Fragments.InfoCourseFragment;
import com.example.escalable.R;
import com.example.escalable.Singletones.VolleyS;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class courses{
    Integer id;
    String name, information, src;
    Double price;
    List<modules> modulesList;

    public courses(Integer id, String name, String information, String src, Double price, List<modules> modulesList) {
        this.id = id;
        this.name = name;
        this.information = information;
        this.src = src;
        this.price = price;
        this.modulesList = modulesList;
    }

    public List<modules> getModulesList() {
        return modulesList;
    }

    public void setModulesList(List<modules> modulesList) {
        this.modulesList = modulesList;
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

    public static void ShowMyCourses(View v, final Context context)
    {
        final RecyclerView recyclerView;
        recyclerView = v.findViewById(R.id.my_courses_container);

        JSONArray user = new JSONArray();
        try {
            user.put(0, Data.getapi_token());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonArrayRequest jar = new JsonArrayRequest(
                Request.Method.POST,
                Data.url + "mycourses",
                user,
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
                Toast.makeText(context, "No tienes cursos disponibles", Toast.LENGTH_SHORT).show();
            }
        });
        VolleyS.getinstance(context).getRq().add(jar);
    }

    public static void ShowBestCourses(View v, final Context context)
    {
        final RecyclerView recyclerView;
        recyclerView = v.findViewById(R.id.bestcourses);


        JsonArrayRequest jar = new JsonArrayRequest(
                Request.Method.GET,
                Data.url + "bestcourses",
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
                Toast.makeText(context, "No tienes cursos disponibles", Toast.LENGTH_SHORT).show();
            }
        });
        VolleyS.getinstance(context).getRq().add(jar);
    }

    public static View.OnClickListener showinfo(final courses c, final Context context)
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(v.getContext(), InfoCourse.class);
                in.putExtra("id", c.getId().toString());
                in.putExtra("name", c.getName());
                in.putExtra("information", c.getInformation());
                in.putExtra("src", c.getSrc());
                in.putExtra("price", c.getPrice().toString());
                context.startActivity(in);
            }
        };
    }
}

