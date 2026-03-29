package com.example.laundry_app.HomePage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.laundry_app.Main_Page.Confirm_Order;
import com.example.laundry_app.R;

public class Service_Show extends AppCompatActivity {

    CardView item_order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_service_show);

        item_order=findViewById(R.id.item_order);

        item_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Service_Show.this, Confirm_Order.class));
            }
        });
    }
}