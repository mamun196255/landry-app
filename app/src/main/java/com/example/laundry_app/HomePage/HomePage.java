package com.example.laundry_app.HomePage;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.laundry_app.Main_Page.MainPage;
import com.example.laundry_app.Offers.OffersPage;
import com.example.laundry_app.Profile.ProfilePage;
import com.example.laundry_app.R;
import com.example.laundry_app.Service_Page.ServicesPage;

public class HomePage extends AppCompatActivity implements View.OnClickListener{

    CardView home,service,offer,profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);

        home=findViewById(R.id.home);
        service=findViewById(R.id.service);
        offer=findViewById(R.id.offer);
        profile=findViewById(R.id.profile);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame,new MainPage()).commit();

        home.setOnClickListener(this);
        service.setOnClickListener(this);
        offer.setOnClickListener(this);
        profile.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.home){

            getSupportFragmentManager().beginTransaction().replace(R.id.frame,new MainPage()).commit();
            home.setCardBackgroundColor(Color.parseColor("#FFA500"));
            service.setCardBackgroundColor(Color.parseColor("#f3f3f3"));
            offer.setCardBackgroundColor(Color.parseColor("#f3f3f3"));
            profile.setCardBackgroundColor(Color.parseColor("#f3f3f3"));
        }

         else if(v.getId()==R.id.service){

            getSupportFragmentManager().beginTransaction().replace(R.id.frame,new ServicesPage()).commit();
            home.setCardBackgroundColor(Color.parseColor("#f3f3f3"));
            service.setCardBackgroundColor(Color.parseColor("#FFA500"));
            offer.setCardBackgroundColor(Color.parseColor("#f3f3f3"));
            profile.setCardBackgroundColor(Color.parseColor("#f3f3f3"));
        }
       else if(v.getId()==R.id.offer){

            getSupportFragmentManager().beginTransaction().replace(R.id.frame,new OffersPage()).commit();
            home.setCardBackgroundColor(Color.parseColor("#f3f3f3"));
            service.setCardBackgroundColor(Color.parseColor("#f3f3f3"));
            offer.setCardBackgroundColor(Color.parseColor("#FFA500"));
            profile.setCardBackgroundColor(Color.parseColor("#f3f3f3"));
        }
       else if(v.getId()==R.id.profile){

            getSupportFragmentManager().beginTransaction().replace(R.id.frame,new ProfilePage()).commit();
            home.setCardBackgroundColor(Color.parseColor("#f3f3f3"));
            service.setCardBackgroundColor(Color.parseColor("#f3f3f3"));
            offer.setCardBackgroundColor(Color.parseColor("#f3f3f3"));
            profile.setCardBackgroundColor(Color.parseColor("#FFA500"));
        }
    }
}