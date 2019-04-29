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
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.escalable.Adapters.Comments_adapter;
import com.example.escalable.Class.Data;
import com.example.escalable.R;
import com.example.escalable.Singletones.VolleyS;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class comments {
    Integer id, status;
    String content, user_name;
    List<responses> lr;

    public comments(Integer id, Integer status, String content, String user_name, List<responses> lr) {
        this.id = id;
        this.status = status;
        this.content = content;
        this.user_name = user_name;
        this.lr = lr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public List<responses> getLr() {
        return lr;
    }

    public void setLr(List<responses> lr) {
        this.lr = lr;
    }

    public static void ShowComments(String blog_id, final RecyclerView recyclerView, final Context context)
    {
        JSONArray blog = new JSONArray();
        try {
            blog.put(0, blog_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonArrayRequest jar = new JsonArrayRequest(
                Request.Method.POST,
                Data.url + "comments",
                blog,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<comments>>(){}.getType();
                        List<comments> lc = gson.fromJson(response.toString(), type);
                        Toast.makeText(context, lc.get(0).getContent(), Toast.LENGTH_SHORT).show();
                        Comments_adapter comments_adapter = new Comments_adapter(lc);
                        recyclerView.setAdapter(comments_adapter);
                        LinearLayoutManager lm = new LinearLayoutManager(context);
                        lm.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
            }
        });
        VolleyS.getinstance(context).getRq().add(jar);
    }

    public static void makecomment(final Context context, String post_id, String content)
    {
        JSONObject comment = new JSONObject();
        try {
            comment.put("api_token", Data.getapi_token());
            comment.put("post_id", post_id);
            comment.put("content", content);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jar = new JsonObjectRequest(
                Request.Method.POST,
                Data.url + "makecomment",
                comment,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(context, "Tu comentario está en espera de aprobación", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "No se pudo publicar el comentario", Toast.LENGTH_SHORT).show();
            }
        });
        VolleyS.getinstance(context).getRq().add(jar);
    }
}
