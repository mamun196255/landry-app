package com.example.laundry_app.Order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundry_app.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Order_ViewHolder  extends RecyclerView.Adapter<Order_ViewHolder.ViewHolder>{

    private Context context;
    private List<Ordered_Model> itemList;

    public Order_ViewHolder(Context context,List<Ordered_Model> itemList){
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public Order_ViewHolder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Order_ViewHolder.ViewHolder(LayoutInflater.from(context).inflate(R.layout.order_view, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull Order_ViewHolder.ViewHolder holder, int position) {

        Picasso.get()
                .load(itemList.get(position).getImage())
                .placeholder(R.drawable.load_back)
                .error(R.drawable.load_back)
                .into(holder.image);

        holder.service_type.setText(itemList.get(position).getService_type());
        holder.item_amount.setText(itemList.get(position).getItem_amount());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image,added,removed;
        TextView quantity,service_type,item_amount,sub_amount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            added=itemView.findViewById(R.id.added);
            removed=itemView.findViewById(R.id.removed);
            quantity=itemView.findViewById(R.id.quantity);
            service_type=itemView.findViewById(R.id.service_type);
            item_amount=itemView.findViewById(R.id.item_amount);
            sub_amount=itemView.findViewById(R.id.sub_amount);

        }
    }
}
