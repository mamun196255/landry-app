package com.example.laundry_app.Service_Page;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.laundry_app.Main_Page.MainPageModelClass;
import com.example.laundry_app.R;

import java.util.ArrayList;
import java.util.List;


public class ServicesPage extends Fragment {

 RecyclerView service_recycler;

    private List<Service_Model> itemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view= inflater.inflate(R.layout.fragment_services_page, container, false);


      service_recycler=view.findViewById(R.id.service_recycler);

      service_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
      itemList=new ArrayList<>();

      itemList.add(new Service_Model(R.drawable.washing,R.drawable.clothing_iron,"ETA 24 hrs","ETA 48 hrs","Regular Wash","Wash &amp; Ironing",15.00,25.00));
        itemList.add(new Service_Model(R.drawable.ironing, R.drawable.shoe,"ETA 24 hrs","ETA 48 hrs","Ironing","Shoe Washing",20.00,30.00));
        itemList.add(new Service_Model(R.drawable.cloth, R.drawable.laundry,"ETA 1- 4 days","ETA 72 hrs","Regular Wash","Bag Cleaning",35.00,30.00));

        Service_ViewHolder adapter=new Service_ViewHolder(getContext(),itemList);

        service_recycler.setAdapter(adapter);
        return view;
    }
}