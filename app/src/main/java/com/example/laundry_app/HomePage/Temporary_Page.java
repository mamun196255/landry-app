package com.example.laundry_app.HomePage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.laundry_app.R;

public class Temporary_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_temporary_page);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(Temporary_Page.this,HomePage.class);
            startActivity(intent);
            finish();
        },2000);

    }
}