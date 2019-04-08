package com.example.escalable.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.escalable.Adapters.Courses_adapter;
import com.example.escalable.Models.courses;
import com.example.escalable.R;
import com.example.escalable.Singletones.VolleyS;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.List;

public class Courses extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);



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


                        Log.d("respuesta", response.toString());
                        Courses_adapter courses_adapter = new Courses_adapter(lc);

                        recyclerView = findViewById(R.id.contenedor);
                        recyclerView.setAdapter(courses_adapter);

                        LinearLayoutManager lm = new LinearLayoutManager(Courses.this);
                        lm.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                        Toast.makeText(Courses.this, response.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "nothing", Toast.LENGTH_SHORT).show();
            }
        });
        VolleyS.getinstance(getApplicationContext()).getRq().add(jar);
    }
}
