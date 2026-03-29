package com.example.laundry_app.Main_Page;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.laundry_app.HomePage.HomeSliderPage;
import com.example.laundry_app.R;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends Fragment implements View.OnClickListener{

    private RecyclerView recyclerView;
    private List<MainPageModelClass> itemList;

    private ViewPager2 imageSlider;
    private ImageSliderAdapter sliderAdapter;
    private LinearLayout aDotsLayout;
    private TextView[] dots;
    ImageView addImage;
    private ScrollView scrollView;

    private Handler sliderHandler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_page, container, false);

        recyclerView = view.findViewById(R.id.services_recycler);
        imageSlider = view.findViewById(R.id.imageSlider);
        scrollView = view.findViewById(R.id.scrollView);
        aDotsLayout = view.findViewById(R.id.indicator);
        addImage=view.findViewById(R.id.adImage);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        itemList = new ArrayList<>();

        itemList.add(new MainPageModelClass(R.drawable.cloth, R.drawable.cargo,"9 march,2026","7 march,2026","Shirt","Cargo",12.00,20.00));
        itemList.add(new MainPageModelClass(R.drawable.pants,R.drawable.tshirt,"12 march,2026","18 march,2026","Pants","T-Shirt",18.00,10.00));
        itemList.add(new MainPageModelClass(R.drawable.cargo, R.drawable.shoe,"8 march,2026","12 march,2026","Cargo","Shoe",20.00,30.00));
        itemList.add(new MainPageModelClass(R.drawable.cloth, R.drawable.cargo,"9 march,2026","7 march,2026","Shirt","Cargo",12.00,20.00));
        itemList.add(new MainPageModelClass(R.drawable.cargo, R.drawable.shoe,"8 march,2026","12 march,2026","Cargo","Shoe",20.00,30.00));

        MainPageViewHolder adapter = new MainPageViewHolder(getContext(), itemList);
        recyclerView.setAdapter(adapter);

        setImageSlider();
        getScrollViewAnimation();

        addImage.setOnClickListener(this);

        return view;
    }

    private void getScrollViewAnimation() {

        scrollView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {

            float alphaSlider = 1f - (float) scrollY / imageSlider.getHeight();
            alphaSlider = Math.max(0f, Math.min(1f, alphaSlider));

            imageSlider.setAlpha(alphaSlider);
            aDotsLayout.setAlpha(alphaSlider);
        });
    }

    private void setImageSlider() {

        List<SliderItem> images = new ArrayList<>();

        images.add(new SliderItem(R.drawable.cover1));
        images.add(new SliderItem(R.drawable.img_1));
        images.add(new SliderItem(R.drawable.img_2));
        images.add(new SliderItem(R.drawable.img_3));

        sliderAdapter = new ImageSliderAdapter(images,getContext());
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

                setCurrentIndicator(position % dots.length);

                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 3000);
            }
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