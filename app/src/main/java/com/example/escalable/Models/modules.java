package com.example.escalable.Models;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.escalable.Class.Data;
import com.example.escalable.Class.JSONCustomRequest;
import com.example.escalable.Singletones.VolleyS;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class modules {
    Integer id;
    String nombre, information;
    List<videos> videos;

    public modules(Integer id, String nombre, String information, List<videos> videos) {
        this.id = id;
        this.nombre = nombre;
        this.information = information;
        this.videos = videos;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public List<com.example.escalable.Models.videos> getVideos() {
        return videos;
    }

    public void setVideos(List<com.example.escalable.Models.videos> videos) {
        this.videos = videos;
    }
}
