package com.example.laundry_app.HomePage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundry_app.Main_Page.SliderItem;
import com.example.laundry_app.Order.OrderActivity;
import com.example.laundry_app.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class HomeSliderViewHolder extends RecyclerView.Adapter<HomeSliderViewHolder.ViewHolder>{

    private Context context;
    private List<HomeSliderModel> itemList;
    private int offerPrice;
    public HomeSliderViewHolder(Context context,List<HomeSliderModel> itemList,int offerPrice){
        this.context = context;
        this.itemList = itemList;
        this.offerPrice = offerPrice;
    }

    @NonNull
    @Override
    public HomeSliderViewHolder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeSliderViewHolder.ViewHolder(LayoutInflater.from(context).inflate(R.layout.select_service_view_slider, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull HomeSliderViewHolder.ViewHolder holder, int position) {


        holder.time_line_text.setText(itemList.get(position).getTime_line());
        holder.service_type.setText(itemList.get(position).getService_type());


        Picasso.get()
                .load(itemList.get(position).getImage())
                .placeholder(R.drawable.load_back)
                .error(R.drawable.load_back)
                .into(holder.image);

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int pos = holder.getAdapterPosition();
                if (pos == RecyclerView.NO_POSITION) return;
                HomeSliderModel item = itemList.get(pos);
                Intent intent = new Intent(context,OrderActivity.class);
                intent.putExtra("offer_price", offerPrice);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

       TextView time_line_text,service_type;
       CardView button;
       ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            time_line_text=itemView.findViewById(R.id.time_line_text);
            service_type=itemView.findViewById(R.id.service_type);
            button=itemView.findViewById(R.id.book_button);
        }
    }
}
