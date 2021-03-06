package com.example.escalable.Models;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.BoringLayout;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.escalable.Activities.Login;
import com.example.escalable.Activities.SidebarActivity;
import com.example.escalable.Class.Data;
import com.example.escalable.R;
import com.example.escalable.Singletones.VolleyS;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class users {
    Integer id;
    String name, email, password, api_token;
    SharedPreferences archive;

    public users(Integer id, String name, String email, String password, String api_token) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.api_token = api_token;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

    public static void Login(String mail, String pass, final Context context, final Activity activity)
    {
        JSONObject user = new JSONObject();
        try {
            user.put("email", mail);
            user.put("password", pass);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jar = new JsonObjectRequest(
                Request.Method.POST,
                Data.url + "login",
                user,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Data.user_name = response.getString("name");
                            Data.setapi_token(response.getString("api_token"));
                            Toast.makeText(context, "Bienvenido "+ response.getString("name"), Toast.LENGTH_SHORT).show();
                            activity.finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Intent in = new Intent(context, SidebarActivity.class);
                        context.startActivity(in);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "El usuario o contraseña no es correcto", Toast.LENGTH_SHORT).show();
            }
        });
        VolleyS.getinstance(context).getRq().add(jar);
    }

    public static void Register(String name, String email, String pass, final Context context, final Activity activity)
    {
        JSONObject user = new JSONObject();
        try {
            user.put("name", name);
            user.put("email", email);
            user.put("password", pass);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jar = new JsonObjectRequest(
                Request.Method.POST,
                Data.url + "register",
                user,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Data.setapi_token(response.getString("api_token"));
                            Toast.makeText(context, "Registro exitoso: " + response.getString("name"), Toast.LENGTH_SHORT).show();
                            Intent in = new Intent(context, SidebarActivity.class);
                            context.startActivity(in);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        activity.finish();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "El usuario ya existe", Toast.LENGTH_SHORT).show();
            }
        });
        VolleyS.getinstance(context).getRq().add(jar);
    }

    public static void GetUser(final View v, final Context context)
    {
        String name = "User", email = "user@gmail.com";
        final JSONObject user = new JSONObject();
        try {
            user.put("api_token", Data.getapi_token());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jar = new JsonObjectRequest(
                Request.Method.POST,
                Data.url + "getuser",
                user,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            ((TextView) v.findViewById(R.id.myprofileusername)).setText(response.getString("name"));
                            ((TextView) v.findViewById(R.id.myprofileemail)).setText(response.getString("email"));
                            ((TextView) v.findViewById(R.id.welcomeuser)).setText("Bienvenido " + response.getString("email"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "No tienes Internet", Toast.LENGTH_SHORT).show();
            }
        });
        VolleyS.getinstance(context).getRq().add(jar);

    }
}
