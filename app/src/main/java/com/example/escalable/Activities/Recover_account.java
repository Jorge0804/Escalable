package com.example.escalable.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.escalable.R;

public class Recover_account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_account);

        Toast.makeText(this, getIntent().getExtras().getString("information"), Toast.LENGTH_SHORT).show();
    }
}
