package com.example.laundry_app.Main_Page;

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

import com.example.laundry_app.HomePage.Service_Show;
import com.example.laundry_app.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainPageViewHolder extends RecyclerView.Adapter<MainPageViewHolder.ViewHolder>{
    private Context context;
    private List<MainPageModelClass>itemList;

    public MainPageViewHolder(Context context,List<MainPageModelClass> itemList){
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MainPageViewHolder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainPageViewHolder.ViewHolder(LayoutInflater.from(context).inflate(R.layout.select_service_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainPageViewHolder.ViewHolder holder, int position) {

        Picasso.get()
                .load(itemList.get(position).getImage1())
                .placeholder(R.drawable.load_back)
                .error(R.drawable.load_back)
                .into(holder.image1);

        Picasso.get()
                .load(itemList.get(position).getImage2())
                .placeholder(R.drawable.load_back)
                .error(R.drawable.load_back)
                .into(holder.image2);

        holder.date1.setText(itemList.get(position).getDate1());
        holder.date2.setText(itemList.get(position).getDate2());
        holder.service_type1.setText(itemList.get(position).getService_type1());
        holder.service_type2.setText(itemList.get(position).getService_type2());
        holder.service_amount1.setText("৳"+itemList.get(position).getService_amount1());
        holder.service_amount2.setText("৳"+itemList.get(position).getService_amount2());

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

        holder.date_card1.setCardBackgroundColor(color);
        holder.date_card2.setCardBackgroundColor(color2);

        holder.card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Service_Show.class);
                intent.putExtra("price", itemList.get(position).service_amount1);
                intent.putExtra("productId","LLLDF201");
                intent.putExtra("type",itemList.get(position).service_type1);
                intent.putExtra("image",itemList.get(position).getImage1());
                context.startActivity(intent);
            }
        });
        holder.card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Service_Show.class);
                intent.putExtra("price", itemList.get(position).service_amount2);
                intent.putExtra("type",itemList.get(position).service_type2);
                intent.putExtra("image",itemList.get(position).getImage2());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image1,image2;
        TextView date1,date2,service_type1,service_type2,service_amount1,service_amount2;
        CardView date_card1,date_card2,card1,card2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image1=itemView.findViewById(R.id.image);
            image2=itemView.findViewById(R.id.image2);
            date1=itemView.findViewById(R.id.date);
            date2=itemView.findViewById(R.id.date2);

            service_type1=itemView.findViewById(R.id.service_type);
            service_type2=itemView.findViewById(R.id.service_type2);
            date_card1=itemView.findViewById(R.id.date_card);
            date_card2=itemView.findViewById(R.id.date_card2);
            service_amount1=itemView.findViewById(R.id.service_amount);
            service_amount2=itemView.findViewById(R.id.service_amount2);
            card1=itemView.findViewById(R.id.card1);
            card2=itemView.findViewById(R.id.card2);

        }
    }
}
