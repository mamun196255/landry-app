package com.example.laundry_app.Profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.laundry_app.OrderHistory.OrderHistoryModel;
import com.example.laundry_app.OrderHistory.OrderHistoryViewHolder;
import com.example.laundry_app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CancelFragment extends Fragment {

    RecyclerView cancel_recycler;
    List<OrderHistoryModel> itemList;
    DatabaseReference reference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_cancel, container, false);

        cancel_recycler=view.findViewById(R.id.cancel_recycler);

        reference = FirebaseDatabase.getInstance().getReference("All_Service");

        cancel_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        itemList=new ArrayList<>();
        OrderHistoryViewHolder adapter=new OrderHistoryViewHolder(getContext(),itemList);
        cancel_recycler.setAdapter(adapter);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                itemList.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    if(dataSnapshot.child("order_status").getValue(String.class).equals("Cancelled")) {

                        String order_date = dataSnapshot.child("order_date").getValue(String.class);
                        String orderId = dataSnapshot.child("orderId").getValue(String.class);
                        String delivery_date = dataSnapshot.child("delivery_date").getValue(String.class);
                        String serviceType = dataSnapshot.child("serviceType").getValue(String.class);
                        String pickup_address = dataSnapshot.child("pickup_address").getValue(String.class);
                        String order_status = dataSnapshot.child("order_status").getValue(String.class);
                        String total_amount = dataSnapshot.child("total_amount").getValue(String.class);
                        itemList.add(new OrderHistoryModel(order_date, orderId, order_status, delivery_date, serviceType, pickup_address, total_amount));
                    }
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}