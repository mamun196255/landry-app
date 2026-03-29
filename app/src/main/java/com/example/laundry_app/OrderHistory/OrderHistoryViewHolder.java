package com.example.laundry_app.OrderHistory;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundry_app.Order.Order_Details;
import com.example.laundry_app.R;

import java.util.List;

public class OrderHistoryViewHolder extends RecyclerView.Adapter<OrderHistoryViewHolder.ViewHolder>{

    private Context context;
    private List<OrderHistoryModel> itemList;

    public OrderHistoryViewHolder(Context context,List<OrderHistoryModel> itemList){
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public OrderHistoryViewHolder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderHistoryViewHolder.ViewHolder(LayoutInflater.from(context).inflate(R.layout.order_history, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryViewHolder.ViewHolder holder, int position) {
        holder.order_date.setText(itemList.get(position).getOrder_date());
        holder.order_id.setText(itemList.get(position).getOrder_id());
        holder.delivery_date.setText(itemList.get(position).getDelivery_date());
        holder.status_text.setText(itemList.get(position).getStatus_text());
        holder.service_type.setText(itemList.get(position).getService_type());
        holder.pickup_address.setText(itemList.get(position).getPickup_address());
        holder.total_amount.setText(itemList.get(position).getTotal_amount());

        String status = holder.status_text.getText().toString();

        switch (status) {
            case "Pending":
                holder.status_color.setCardBackgroundColor(
                        ContextCompat.getColor(holder.itemView.getContext(), R.color.yellow)
                );
                break;

            case "Delivered":
                holder.status_color.setCardBackgroundColor(
                        ContextCompat.getColor(holder.itemView.getContext(), R.color.green)
                );
                break;

            case "Cancelled":
                holder.status_color.setCardBackgroundColor(
                        ContextCompat.getColor(holder.itemView.getContext(), R.color.red)
                );
                break;

            case "Accepted":
                holder.status_color.setCardBackgroundColor(
                        ContextCompat.getColor(holder.itemView.getContext(), R.color.blue)
                );
                break;

            default:
                holder.status_color.setCardBackgroundColor(
                        ContextCompat.getColor(holder.itemView.getContext(), R.color.gray)
                );
                break;
        }

        holder.card_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, Order_Details.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView order_date,order_id,delivery_date,status_text,service_type,pickup_address,total_amount;
        CardView status_color,card_click;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            order_date=itemView.findViewById(R.id.order_date);
            order_id=itemView.findViewById(R.id.order_id);
            delivery_date=itemView.findViewById(R.id.delivery_date);
            status_text=itemView.findViewById(R.id.status_text);
            status_color=itemView.findViewById(R.id.status_color);
            service_type=itemView.findViewById(R.id.service_type);
            pickup_address=itemView.findViewById(R.id.pickup_address);
            total_amount=itemView.findViewById(R.id.total_amount);
            card_click=itemView.findViewById(R.id.card_click);
        }
    }
}
