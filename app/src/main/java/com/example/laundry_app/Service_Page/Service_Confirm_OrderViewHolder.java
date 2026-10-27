package com.example.laundry_app.Service_Page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundry_app.Main_Page.MainPageModelClass;
import com.example.laundry_app.Main_Page.MainPageViewHolder;
import com.example.laundry_app.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Service_Confirm_OrderViewHolder  extends RecyclerView.Adapter<Service_Confirm_OrderViewHolder.SliderViewHolder> {

    List<MainPageModelClass>itemList;
    Context context;

    public Service_Confirm_OrderViewHolder(List<MainPageModelClass> itemList, Context context){

        this.itemList=itemList;
        this.context=context;
    }

    @NonNull
    @Override
    public Service_Confirm_OrderViewHolder.SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Service_Confirm_OrderViewHolder.SliderViewHolder(LayoutInflater.from(context).inflate(R.layout.service_confirm_orderview, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull Service_Confirm_OrderViewHolder.SliderViewHolder holder, int position) {

        holder.quantity.setText(String.valueOf(itemList.get(position).getQuantity()));
        Picasso.get()
                .load(itemList.get(position).getImage())
                .placeholder(R.drawable.load_back)
                .error(R.drawable.load_back)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class SliderViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView quantity;
        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            quantity=itemView.findViewById(R.id.quantity);
        }
    }
}
