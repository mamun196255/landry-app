package com.example.laundry_app.Offers;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundry_app.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class Offer_ViewHolder extends RecyclerView.Adapter<Offer_ViewHolder.ViewHolder>{

    private Context context;
    private List<Offer_Model> itemList;

    public Offer_ViewHolder(Context context,List<Offer_Model> itemList){
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public Offer_ViewHolder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Offer_ViewHolder.ViewHolder(LayoutInflater.from(context).inflate(R.layout.offers_view, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull Offer_ViewHolder.ViewHolder holder, int position) {

        Picasso.get()
                .load(itemList.get(position).getIcon())
                .placeholder(R.drawable.load_back)
                .error(R.drawable.load_back)
                .into(holder.icon);

        Picasso.get()
                .load(itemList.get(position).getImage())
                .placeholder(R.drawable.load_back)
                .error(R.drawable.load_back)
                .into(holder.image);

        holder.offer_text.setText(itemList.get(position).getOffer_text());
        holder.service_type.setText(itemList.get(position).getService_type());

        int color,color2,color3;

        switch (position % 4) {
            case 0:
                color = Color.parseColor("#ffc8f3");
                color2=Color.parseColor("#ffd8f8");
                color3=Color.parseColor("#FBD3F6");
                break;
            case 1:
                color = Color.parseColor("#f7d787");
                color2 = Color.parseColor("#f9e5ac");
                color3 = Color.parseColor("#f9e4ae");
                break;
            case 2:
                color = Color.parseColor("#f8b587");
                color2 = Color.parseColor("#f7ccad");
                color3 = Color.parseColor("#f8cdac");

                break;
            default:
                color = Color.parseColor("#87c8f9");
                color2 = Color.parseColor("#aedaf9");
                color3 = Color.parseColor("#97D2FB");
                break;
        }

        holder.background.setCardBackgroundColor(color);
        holder.order_card.setCardBackgroundColor(color2);
        holder.icon_card.setCardBackgroundColor(color3);

        holder.order_card.setOnClickListener(v -> {

        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon,image;
        TextView service_type,offer_text;
        CardView order_card,background,icon_card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            icon=itemView.findViewById(R.id.icon);
            image=itemView.findViewById(R.id.image);
            service_type=itemView.findViewById(R.id.service_type);
            offer_text=itemView.findViewById(R.id.offer_text);
            order_card=itemView.findViewById(R.id.order_card);
            background=itemView.findViewById(R.id.background);
            icon_card=itemView.findViewById(R.id.icon_card);
        }
    }
}
