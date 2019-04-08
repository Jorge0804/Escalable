package com.example.escalable.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.escalable.R;
import com.example.escalable.Singletones.VolleyS;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText edit_user, edit_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.button_login).setOnClickListener(this);
        findViewById(R.id.txt_register).setOnClickListener(this);
        findViewById(R.id.txt_password).setOnClickListener(this);

        edit_user = findViewById(R.id.usertxt);
        edit_pass = findViewById(R.id.passwordtxt);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_login:
                Intent intent = new Intent(getApplicationContext(), Courses.class);
                startActivity(intent);
                break;
            case R.id.txt_register:
                Intent intent1 = new Intent(getApplicationContext(), Register.class);
                startActivity(intent1);
                break;
            case R.id.txt_password:
                Intent intent2 = new Intent(getApplicationContext(), Recover_account.class);
                startActivity(intent2);
                break;
        }
    }
}
