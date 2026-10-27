package com.example.laundry_app.Service_Page;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundry_app.Main_Page.MainPageModelClass;
import com.example.laundry_app.Main_Page.MainPageViewHolder;
import com.example.laundry_app.Order.OrderActivity;
import com.example.laundry_app.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Service_ViewHolder extends RecyclerView.Adapter<Service_ViewHolder.ViewHolder>implements View.OnClickListener{

    private Context context;
    private List<ServiceViewPair> itemList;

    public Service_ViewHolder(Context context,List<ServiceViewPair> itemList){
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public Service_ViewHolder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Service_ViewHolder.ViewHolder(LayoutInflater.from(context).inflate(R.layout.all_service_view, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull Service_ViewHolder.ViewHolder holder, int position) {


        ServiceViewPair pair=itemList.get(position);
        holder.time_line_text1.setText((pair.getPair1()).getTime_line());
        holder.time_line_text2.setText((pair.getPair2()).getTime_line());
        holder.service_type1.setText(pair.getPair1().getService_type());
        holder.service_type2.setText(pair.getPair2().getService_type());
        holder.service_amount1.setText(String.valueOf(pair.getPair1().getService_amount()));
        holder.service_amount2.setText(String.valueOf(pair.getPair2().getService_amount()));


        Picasso.get()
                .load(pair.getPair1().getImage())
                .placeholder(R.drawable.load_back)
                .error(R.drawable.load_back)
                .into(holder.image1);

        Picasso.get()
                .load(pair.getPair2().getImage())
                .placeholder(R.drawable.load_back)
                .error(R.drawable.load_back)
                .into(holder.image2);

        int color,color2;

        switch (position % 4) {
            case 0:
                color = Color.parseColor("#87c8fc");
                color2=Color.parseColor("#f7b587");
                break;
            case 1:
                color = Color.parseColor("#f7d787");
                color2=Color.parseColor("#ffc8f3");
                break;
            case 2:
                color = Color.parseColor("#A478E7");
                color2=Color.parseColor("#14D0A4");
                break;
            default:
                color = Color.parseColor("#F89E9D");
                color2=Color.parseColor("#CDDC39");
                break;
        }

        holder.time_line_card1.setCardBackgroundColor(color);
        holder.time_line_card2.setCardBackgroundColor(color2);

        holder.card1.setOnClickListener(this);
        holder.card2.setOnClickListener(this);


    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public void onClick(View v) {

        context.startActivity(new Intent(context, OrderActivity.class));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image1,image2;
        TextView time_line_text1,time_line_text2,service_type1,service_type2,service_amount1,service_amount2;
        CardView time_line_card1,time_line_card2,card1,card2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image1=itemView.findViewById(R.id.image1);
            image2=itemView.findViewById(R.id.image2);
            time_line_card1=itemView.findViewById(R.id.time_line_card1);
            time_line_card2=itemView.findViewById(R.id.time_line_card2);
            time_line_text1=itemView.findViewById(R.id.time_line_text1);
            time_line_text2=itemView.findViewById(R.id.time_line_text2);
            service_type1=itemView.findViewById(R.id.service_type1);
            service_type2=itemView.findViewById(R.id.service_type2);
            service_amount1=itemView.findViewById(R.id.service_amount1);
            service_amount2=itemView.findViewById(R.id.service_amount2);
            card1=itemView.findViewById(R.id.card1);
            card2=itemView.findViewById(R.id.card2);

        }
    }
}
