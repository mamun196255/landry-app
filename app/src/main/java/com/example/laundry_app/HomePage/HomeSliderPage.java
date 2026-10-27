package com.example.laundry_app.HomePage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundry_app.R;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class HomeSliderPage extends AppCompatActivity {

    private RecyclerView recyclerView;
    HomeSliderViewHolder adapter;
    List<HomeSliderModel> itemList;
    int offer_price=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_slider_page);

        recyclerView = findViewById(R.id.home_slider_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemList = new ArrayList<>();

        Intent intent=getIntent();
        offer_price=intent.getIntExtra("offer_price",0);


        adapter = new HomeSliderViewHolder(this, itemList,offer_price);
        recyclerView.setAdapter(adapter);


        DatabaseReference databaseReference =
                FirebaseDatabase.getInstance()
                        .getReference("Laundry app")
                        .child("All Service");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                itemList.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    HomeSliderModel item = dataSnapshot.getValue(HomeSliderModel.class);

                    if (item != null) {
                        itemList.add(item);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeSliderPage.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}