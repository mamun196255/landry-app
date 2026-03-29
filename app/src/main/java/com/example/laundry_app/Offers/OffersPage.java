package com.example.laundry_app.Offers;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.laundry_app.R;

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

        itemList.add(new Offer_Model(R.drawable.iron,R.drawable.clothing_iron,"Ironing","Enjoy 10% off ironning services"));
        itemList.add(new Offer_Model(R.drawable.iron,R.drawable.clothing_iron,"Ironing","Enjoy 10% off ironning services"));
        itemList.add(new Offer_Model(R.drawable.iron,R.drawable.clothing_iron,"Ironing","Enjoy 10% off ironning services"));
        itemList.add(new Offer_Model(R.drawable.iron,R.drawable.clothing_iron,"Ironing","Enjoy 10% off ironning services"));
        itemList.add(new Offer_Model(R.drawable.iron,R.drawable.clothing_iron,"Ironing","Enjoy 10% off ironning services"));
        itemList.add(new Offer_Model(R.drawable.iron,R.drawable.clothing_iron,"Ironing","Enjoy 10% off ironning services"));
        itemList.add(new Offer_Model(R.drawable.iron,R.drawable.clothing_iron,"Ironing","Enjoy 10% off ironning services"));

        Offer_ViewHolder adapter;

        adapter=new Offer_ViewHolder(getContext(),itemList);

        recyclerView.setAdapter(adapter);

        return view;
    }
}