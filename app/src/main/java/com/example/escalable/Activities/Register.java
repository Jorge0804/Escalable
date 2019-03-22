package com.example.escalable.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.escalable.R;

public class Register extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findViewById(R.id.button_register).setOnClickListener(this);
        findViewById(R.id.txt_cancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_register:
                Intent intent = new Intent(getApplicationContext(), Courses.class);
                startActivity(intent);
                break;
            case R.id.txt_cancel:
                finish();
                break;
        }
    }
}
