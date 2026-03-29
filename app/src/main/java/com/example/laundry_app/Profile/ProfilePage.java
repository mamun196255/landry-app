package com.example.laundry_app.Profile;

import android.graphics.Color;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.laundry_app.OrderHistory.Order_History;
import com.example.laundry_app.R;

public class ProfilePage extends Fragment implements View.OnClickListener{

    CardView order_history,pending,accept,cancel,delivered;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_profile_page, container, false);

        order_history=view.findViewById(R.id.order_history);
        pending=view.findViewById(R.id.pending);
        accept=view.findViewById(R.id.accept);
        cancel=view.findViewById(R.id.cancel);
        delivered=view.findViewById(R.id.delivered);


        getChildFragmentManager().beginTransaction()
                .replace(R.id.frame, new Order_History()).commit();


        order_history.setOnClickListener(this);
        pending.setOnClickListener(this);
        accept.setOnClickListener(this);
        cancel.setOnClickListener(this);
        delivered.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.order_history){

            getChildFragmentManager().beginTransaction().replace(R.id.frame, new Order_History()).commit();
            order_history.setCardBackgroundColor(Color.parseColor("#FFA500"));
            pending.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
            accept.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
            cancel.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
            delivered.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));

        }
        else if(v.getId()==R.id.pending){

            getChildFragmentManager().beginTransaction().replace(R.id.frame, new PendingFragment()).commit();
            order_history.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
            pending.setCardBackgroundColor(Color.parseColor("#FFA500"));
            accept.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
            cancel.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
            delivered.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
        }

        else if(v.getId()==R.id.accept){

            getChildFragmentManager().beginTransaction().replace(R.id.frame, new AcceptFragment()).commit();
            order_history.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
            pending.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
            accept.setCardBackgroundColor(Color.parseColor("#FFA500"));
            cancel.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
            delivered.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
        }
        else if(v.getId()==R.id.cancel){

            getChildFragmentManager().beginTransaction().replace(R.id.frame, new CancelFragment()).commit();
            order_history.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
            pending.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
            accept.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
            cancel.setCardBackgroundColor(Color.parseColor("#FFA500"));
            delivered.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
        }
        else{
            getChildFragmentManager().beginTransaction().replace(R.id.frame, new DeliveredFragment()).commit();
            order_history.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
            pending.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
            accept.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
            cancel.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
            delivered.setCardBackgroundColor(Color.parseColor("#FFA500"));
        }
    }
}