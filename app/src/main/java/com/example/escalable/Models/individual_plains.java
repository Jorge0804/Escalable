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
import com.example.escalable.Adapters.Individual_purchases_adapter;
import com.example.escalable.Class.Data;
import com.example.escalable.R;
import com.example.escalable.Singletones.VolleyS;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.List;

public class individual_plains {
    Integer id, course_id;
    Double price;
    String created_at;

    public individual_plains(Integer id, Integer course_id, Double price, String created_at) {
        this.id = id;
        this.course_id = course_id;
        this.price = price;
        this.created_at = created_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

//    O aqui
    public static void ShowIndividualPlains(View v, final Context context)
    {
        final RecyclerView recyclerView;
        recyclerView = v.findViewById(R.id.containerindividualpurchases);

        JSONArray individual = new JSONArray();
        try {
            individual.put(0, 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonArrayRequest jar = new JsonArrayRequest(
                Request.Method.POST,
                Data.url + "showmyplans",
                individual,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<individual_plains>>(){}.getType();
                        List<individual_plains> lc = gson.fromJson(response.toString(), type);
                        Individual_purchases_adapter  individual_purchases_adapter = new Individual_purchases_adapter(lc);

                        recyclerView.setAdapter(individual_purchases_adapter);

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
