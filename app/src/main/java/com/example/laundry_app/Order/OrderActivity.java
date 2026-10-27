package com.example.laundry_app.Order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundry_app.R;
import com.example.laundry_app.Service_Page.Service_Confirm_Order;
import com.google.firebase.database.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView order_recycler;
    private List<Ordered_Model> itemList;
    CardView place_order;
    TextView total_amount;

    double offerPercent = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order);

        order_recycler = findViewById(R.id.order_recycler);
        place_order = findViewById(R.id.place_order);
        total_amount = findViewById(R.id.total_amount);
        offerPercent = getIntent().getIntExtra("offer_price",0);
        order_recycler.setLayoutManager(new LinearLayoutManager(this));
        itemList = new ArrayList<>();
        Order_ViewHolder adapter = new Order_ViewHolder(
                this,
                itemList,
                offerPercent,
                total -> total_amount.setText("৳" + total)
        );

        order_recycler.setAdapter(adapter);

        FirebaseDatabase.getInstance()
                .getReference("Laundry app")
                .child("Order Item")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        itemList.clear();

                        for (DataSnapshot data : snapshot.getChildren()) {
                            Ordered_Model item = data.getValue(Ordered_Model.class);
                            if (item != null) itemList.add(item);
                        }

                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(OrderActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });

        place_order.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 3);

        String date = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                .format(calendar.getTime());

        DatabaseReference dbRef = FirebaseDatabase.getInstance()
                .getReference("Laundry app")
                .child("Orders");

        String orderId = dbRef.push().getKey();

         double totalAmount = 0f;

        List<Map<String, Object>> orderedItems = new ArrayList<>();

        for (Ordered_Model item : itemList) {

            if (item.getQuantity() > 0) {

                double discount = (item.getPrice() * offerPercent) / 100;
                double finalPrice = item.getPrice() - discount;

                Map<String, Object> map = new HashMap<>();
                map.put("service_type", item.getService_type());
                map.put("price", finalPrice);
                map.put("image", item.getImage());
                map.put("quantity", item.getQuantity());

                orderedItems.add(map);

                totalAmount += finalPrice * item.getQuantity();
            }
        }

        if (orderedItems.isEmpty()) {
            Toast.makeText(this, "No item selected", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> orderMap = new HashMap<>();
        orderMap.put("totalAmount", "৳" + totalAmount);
        orderMap.put("items", orderedItems);

        double finalTotalAmount = totalAmount;

        dbRef.child(orderId).setValue(orderMap)
                .addOnSuccessListener(unused -> {

                    Intent intent = new Intent(this, Service_Confirm_Order.class);
                    intent.putExtra("pickup_date", date);
                    intent.putExtra("orderId", orderId);
                    intent.putExtra("total_amount", finalTotalAmount);
                    startActivity(intent);
                });
    }
}