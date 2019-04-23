package com.example.escalable.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.escalable.Class.Data;
import com.example.escalable.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Data.archive = getSharedPreferences("archive", MODE_PRIVATE);
                if(Data.check_session())
                {
                    Intent intent = new Intent(getApplicationContext(), SidebarActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                }
                finish();
            }
        }, 1500);
    }
}
