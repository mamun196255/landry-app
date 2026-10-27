package com.example.laundry_app.Main_Page;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.laundry_app.HomePage.HomeSliderPage;
import com.example.laundry_app.R;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private List<MainPageModelClass> itemList;

    private ViewPager2 imageSlider;
    private ImageSliderAdapter sliderAdapter;
    private LinearLayout aDotsLayout;
    private TextView[] dots;

    private ImageView addImage;
    private ScrollView scrollView;

    private Handler sliderHandler = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_page, container, false);

        recyclerView = view.findViewById(R.id.services_recycler);
        imageSlider = view.findViewById(R.id.imageSlider);
        scrollView = view.findViewById(R.id.scrollView);
        aDotsLayout = view.findViewById(R.id.indicator);
        addImage = view.findViewById(R.id.adImage);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        itemList = new ArrayList<>();
        MainPageViewHolder adapter = new MainPageViewHolder(getContext(), itemList);
        recyclerView.setAdapter(adapter);


        DatabaseReference databaseReference =
                FirebaseDatabase.getInstance()
                        .getReference("Laundry app")
                        .child("Mainpage_Recyclerview");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                itemList.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    MainPageModelClass model = dataSnapshot.getValue(MainPageModelClass.class);

                    if (model != null) {
                        itemList.add(model);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });

        setImageSlider();
        getScrollViewAnimation();

        addImage.setOnClickListener(this);

        return view;
    }


    private void setImageSlider() {

        List<SliderItem> images = new ArrayList<>();

        DatabaseReference databaseReference =
                FirebaseDatabase.getInstance()
                        .getReference("Laundry app")
                        .child("homeOffer");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                images.clear();

                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    SliderItem item = childSnapshot.getValue(SliderItem.class);
                    if (item != null) {
                        images.add(item);
                    }
                }

                sliderAdapter = new ImageSliderAdapter(images, imageSlider, getContext());
                imageSlider.setAdapter(sliderAdapter);


                setupIndicator(images.size());

                imageSlider.setOffscreenPageLimit(3);

                CompositePageTransformer transformer = new CompositePageTransformer();
                transformer.addTransformer(new MarginPageTransformer(20));
                transformer.addTransformer((page, position) -> {
                    float r = 1 - Math.abs(position);
                    page.setScaleY(0.85f + r * 0.15f);
                });

                imageSlider.setPageTransformer(transformer);

                imageSlider.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                    @Override
                    public void onPageSelected(int position) {
                        super.onPageSelected(position);

                        setCurrentIndicator(position);
                        sliderHandler.removeCallbacks(sliderRunnable);
                        sliderHandler.postDelayed(sliderRunnable, 3000);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseError", error.getMessage());
            }
        });
    }

    private void getScrollViewAnimation() {

        scrollView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {

            if (imageSlider == null || imageSlider.getHeight() == 0) return;

            float alpha = 1f - (float) scrollY / imageSlider.getHeight();
            alpha = Math.max(0f, Math.min(1f, alpha));

            imageSlider.setAlpha(alpha);
            aDotsLayout.setAlpha(alpha);
        });
    }

    private Runnable sliderRunnable = () ->
            imageSlider.setCurrentItem(imageSlider.getCurrentItem() + 1, true);

    @Override
    public void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 3000);
    }

    private void setupIndicator(int count) {

        if (count <= 0) return;

        dots = new TextView[count];
        aDotsLayout.removeAllViews();

        for (int i = 0; i < count; i++) {

            dots[i] = new TextView(getContext());
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.black, getContext().getTheme()));

            aDotsLayout.addView(dots[i]);
        }

        setCurrentIndicator(0);
    }

    private void setCurrentIndicator(int position) {

        if (dots == null || dots.length == 0) return;

        for (int i = 0; i < dots.length; i++) {

            if (i == position) {
                dots[i].setTextColor(getResources().getColor(R.color.y1, getContext().getTheme()));
            } else {
                dots[i].setTextColor(getResources().getColor(R.color.black, getContext().getTheme()));
            }
        }
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getContext(), HomeSliderPage.class));
    }
}