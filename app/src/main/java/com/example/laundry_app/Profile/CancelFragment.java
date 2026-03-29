package com.example.laundry_app.Profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.laundry_app.OrderHistory.OrderHistoryModel;
import com.example.laundry_app.OrderHistory.OrderHistoryViewHolder;
import com.example.laundry_app.R;

import java.util.ArrayList;
import java.util.List;

public class CancelFragment extends Fragment {

    RecyclerView cancel_recycler;
    List<OrderHistoryModel> itemList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_cancel, container, false);

        cancel_recycler=view.findViewById(R.id.cancel_recycler);

        cancel_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        itemList=new ArrayList<>();
        itemList.add(new OrderHistoryModel("12 March, 2026","AD45283","Cancelled","15 March, 2026","Shirt + pant","401(c), CSTU Student Hall","৳120.00"));
        itemList.add(new OrderHistoryModel("14 March, 2026","ADF4523","Cancelled","17 March, 2026","Ironing","401(c), CSTU Student Hall","৳110.90"));
        itemList.add(new OrderHistoryModel("17 March, 2026","BFF4523","Cancelled","20 March, 2026","Shirt Wash","401(c), CSTU Student Hall","৳80.00"));
        itemList.add(new OrderHistoryModel("25 March, 2026","BFF4523","Cancelled","28March, 2026","Shirt + pant","401(c), CSTU Student Hall","৳96.00"));


        OrderHistoryViewHolder adapter=new OrderHistoryViewHolder(getContext(),itemList);
        cancel_recycler.setAdapter(adapter);



       return view;
    }
}