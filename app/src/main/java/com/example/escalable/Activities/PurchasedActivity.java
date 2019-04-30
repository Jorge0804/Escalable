package com.example.escalable.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.escalable.R;

public class PurchasedActivity extends AppCompatActivity {
    TextView title, info, price, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchased);

        title = findViewById(R.id.purcheseindividual_title);
        info = findViewById(R.id.purcheseindividual_content);
        name = findViewById(R.id.purcheseindividual_name);
        price = findViewById(R.id.purcheseindividual_price);

        title.setText(getIntent().getExtras().getString("name"));
        info.setText(getIntent().getExtras().getString("information"));
        name.setText(getIntent().getExtras().getString("name"));
        price.setText("$" +getIntent().getExtras().getString("price"));
    }
}
