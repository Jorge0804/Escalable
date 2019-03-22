package com.example.escalable.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.escalable.R;

public class Login extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.button_login).setOnClickListener(this);
        findViewById(R.id.txt_register).setOnClickListener(this);
        findViewById(R.id.txt_password).setOnClickListener(this);
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
