package com.example.laundry_app.Offers;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.laundry_app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class OffersPage extends Fragment {

RecyclerView recyclerView;
private List<Offer_Model>itemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_offers_page, container, false);

        recyclerView=view.findViewById(R.id.offer_recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        itemList=new ArrayList<>();
        Offer_ViewHolder adapter;
        adapter=new Offer_ViewHolder(getContext(),itemList);
        recyclerView.setAdapter(adapter);


        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Laundry app").child("Offers");
        reference.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                itemList.clear();

                for (DataSnapshot dataSnapshot :snapshot.getChildren()){
                    Offer_Model item=dataSnapshot.getValue(Offer_Model.class);
                    if(item != null){
                        itemList.add(item);
                    }
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}