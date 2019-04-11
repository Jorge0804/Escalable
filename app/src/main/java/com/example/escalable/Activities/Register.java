package com.example.escalable.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.escalable.Models.users;
import com.example.escalable.R;

public class Register extends AppCompatActivity implements View.OnClickListener {
    EditText name_txt, email_txt, password_txt, confirm_password_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findViewById(R.id.button_register).setOnClickListener(this);
        findViewById(R.id.txt_cancel).setOnClickListener(this);

        name_txt = findViewById(R.id.edit_name);
        email_txt = findViewById(R.id.edit_mail);
        password_txt = findViewById(R.id.edit_pass);
        confirm_password_txt = findViewById(R.id.edit_confirm_pass);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_register:
                users.Register(name_txt.getText().toString(), email_txt.getText().toString(), password_txt.getText().toString(), getApplicationContext());
                break;
            case R.id.txt_cancel:
                finish();
                break;
        }
    }
}
