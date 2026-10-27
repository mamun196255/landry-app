package com.example.laundry_app.Main_Page;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.laundry_app.HomePage.HomeSliderPage;
import com.example.laundry_app.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.SliderViewHolder> {

    private List<SliderItem> imageList;
    private final int originalSize;
    private Context context;
    private ViewPager2 viewPager2;

    public ImageSliderAdapter(List<SliderItem> imageList, ViewPager2 viewPager2, Context context) {
        this.imageList = imageList;
        this.originalSize = imageList.size();
        this.viewPager2=viewPager2;
        this.context=context;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ImageView imageView = new ImageView(parent.getContext());

        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));

        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        return new SliderViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {

        int actualPosition = position % imageList.size();

        Picasso.get()
                .load(imageList.get(actualPosition).getImage())
                .placeholder(R.drawable.load_back)
                .error(R.drawable.load_back)
                .into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, HomeSliderPage.class);
                intent.putExtra("offer_price",imageList.get(actualPosition).getOffer_price());
                context.startActivity(intent);

            }
        });
    }
    @Override
    public int getItemCount() {

        return Integer.MAX_VALUE;
    }

    public static class SliderViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public SliderViewHolder(@NonNull ImageView itemView) {
            super(itemView);
            imageView = itemView;
        }
    }
}