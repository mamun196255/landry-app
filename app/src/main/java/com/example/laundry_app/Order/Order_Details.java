package com.example.laundry_app.Order;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.example.laundry_app.R;

public class Order_Details extends AppCompatActivity implements View.OnClickListener {

    ImageView back_icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_details);

        back_icon=findViewById(R.id.back);
        back_icon.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}