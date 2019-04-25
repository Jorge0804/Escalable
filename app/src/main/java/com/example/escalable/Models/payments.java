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
import com.example.escalable.Adapters.Myplains_adapter;
import com.example.escalable.Class.Data;
import com.example.escalable.R;
import com.example.escalable.Singletones.VolleyS;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.List;

public class payments {
    Integer id, user_id;
    String plan, status, finished_at;
    Double price;

    public payments(Integer id, Integer user_id, String plan, String status, String finished_at, Double price) {
        this.id = id;
        this.user_id = user_id;
        this.plan = plan;
        this.status = status;
        this.finished_at = finished_at;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFinished_at() {
        return finished_at;
    }

    public void setFinished_at(String finished_at) {
        this.finished_at = finished_at;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

//    Como que el error esta aqui
    public static void ShowPayments(View v, final Context context)
    {
        final RecyclerView recyclerView;
        recyclerView = v.findViewById(R.id.containerplains);

        JSONArray payment = new JSONArray();
        try {
            payment.put(0, 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonArrayRequest jar = new JsonArrayRequest(
                Request.Method.POST,
                Data.url + "showmypayments",
                payment,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<payments>>(){}.getType();
                        List<payments> lc = gson.fromJson(response.toString(), type);
                        Myplains_adapter myplains_adapter = new Myplains_adapter(lc);

                        recyclerView.setAdapter(myplains_adapter);

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
