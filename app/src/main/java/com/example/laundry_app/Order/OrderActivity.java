package com.example.laundry_app.Order;

import static java.security.AccessController.getContext;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundry_app.Offers.Offer_Model;
import com.example.laundry_app.Offers.Offer_ViewHolder;
import com.example.laundry_app.R;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    RecyclerView order_recycler;
    private List<Ordered_Model>itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order);

        order_recycler=findViewById(R.id.order_recycler);

        order_recycler.setLayoutManager(new LinearLayoutManager(this));

        itemList=new ArrayList<>();

        itemList.add(new Ordered_Model(R.drawable.cloth,"T-Shirt","৳12/item"));
        itemList.add(new Ordered_Model(R.drawable.laundry,"T-Shirt","৳12/item"));
        itemList.add(new Ordered_Model(R.drawable.cargo,"T-Shirt","৳12/item"));
        itemList.add(new Ordered_Model(R.drawable.pants,"T-Shirt","৳12/item"));
        itemList.add(new Ordered_Model(R.drawable.tshirt,"T-Shirt","৳12/item"));

        Order_ViewHolder adapter;

        adapter=new Order_ViewHolder(this,itemList);

        order_recycler.setAdapter(adapter);

    }
}