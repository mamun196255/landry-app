package com.example.laundry_app.Order;

import android.content.Context;
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

public class Order_ViewHolder extends RecyclerView.Adapter<Order_ViewHolder.ViewHolder> {

    private Context context;
    private List<Ordered_Model> itemList;
    private double offerPercent;
    private OnTotalChangeListener listener;

    public interface OnTotalChangeListener {
        void onTotalChanged(double total);
    }

    public Order_ViewHolder(Context context, List<Ordered_Model> itemList,
                            double offerPercent,
                            OnTotalChangeListener listener) {

        this.context = context;
        this.itemList = itemList;
        this.offerPercent = offerPercent;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.order_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Ordered_Model item = itemList.get(position);

        Picasso.get().load(item.getImage()).into(holder.image);

        holder.service_type.setText(item.getService_type());

        double original = item.getPrice();
        double discount = (original * offerPercent) / 100;
        double finalPrice = original - discount;

        holder.item_amount.setText("৳" + finalPrice);
        holder.quantity.setText(String.valueOf(item.getQuantity()));

        double subtotal = finalPrice * item.getQuantity();
        holder.sub_amount.setText("৳" + subtotal);

        holder.added.setOnClickListener(v -> {
            item.setQuantity(item.getQuantity() + 1);
            notifyItemChanged(position);
            updateTotal();
        });

        holder.removed.setOnClickListener(v -> {
            if (item.getQuantity() > 0) {
                item.setQuantity(item.getQuantity() - 1);
                notifyItemChanged(position);
                updateTotal();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    private double calculateTotal() {

        double total = 0;

        for (Ordered_Model item : itemList) {

            double discount = (item.getPrice() * offerPercent) / 100;
            double finalPrice = item.getPrice() - discount;

            total += finalPrice * item.getQuantity();
        }

        return total;
    }

    private void updateTotal() {
        if (listener != null) {
            listener.onTotalChanged(calculateTotal());
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        CardView added, removed;
        TextView quantity, service_type, item_amount, sub_amount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            added = itemView.findViewById(R.id.added);
            removed = itemView.findViewById(R.id.removed);
            quantity = itemView.findViewById(R.id.quantity);
            service_type = itemView.findViewById(R.id.service_type);
            item_amount = itemView.findViewById(R.id.item_amount);
            sub_amount = itemView.findViewById(R.id.sub_amount);
        }
    }
}