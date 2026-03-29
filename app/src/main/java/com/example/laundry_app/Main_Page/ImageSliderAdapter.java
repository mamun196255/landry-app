package com.example.laundry_app.Main_Page;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundry_app.HomePage.HomeSliderPage;

import java.util.List;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.SliderViewHolder> {

    private List<SliderItem> imageList;
    Context context;

    public ImageSliderAdapter(List<SliderItem> imageList, Context context) {
        this.imageList = imageList;
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

        holder.imageView.setImageResource(imageList.get(actualPosition).getImage());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.startActivity(new Intent(context, HomeSliderPage.class));

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