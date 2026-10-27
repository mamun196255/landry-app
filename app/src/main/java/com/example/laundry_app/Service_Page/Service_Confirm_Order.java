package com.example.laundry_app.Service_Page;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundry_app.Main_Page.MainPageModelClass;
import com.example.laundry_app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class Service_Confirm_Order extends AppCompatActivity implements View.OnClickListener {

    TextView pickup_date,sub_total,total_amount;
    RecyclerView recyclerView;
    EditText first_name,last_name,phone_number;
    Spinner spinner1,spinner2;
   CardView confirm_button;
   ProgressBar progressBar;
   String key;
   String productId="LL1254D5";
   String service_type="wash";

   DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_service_confirm_order);

        reference=FirebaseDatabase.getInstance().getReference();
        pickup_date=findViewById(R.id.pickup_date);
        sub_total=findViewById(R.id.sub_total);
        total_amount=findViewById(R.id.total_amount);
        first_name=findViewById(R.id.first_name);
        last_name=findViewById(R.id.last_name);
        phone_number=findViewById(R.id.phone_number);
        spinner1=findViewById(R.id.spinner1);
        spinner2=findViewById(R.id.spinner2);
        confirm_button=findViewById(R.id.confirm_button);
        recyclerView=findViewById(R.id.recycler);
        progressBar=findViewById(R.id.progress);

        reference = FirebaseDatabase.getInstance().getReference("Laundry app").child("Confirm Order");

        Intent intent=getIntent();
        pickup_date.setText(intent.getStringExtra("pickup_date"));
        key=intent.getStringExtra("orderId");

        sub_total.setText(String.format("Subtotal: %s", intent.getDoubleExtra("total_amount", 0)));
        total_amount.setText(String.format("Total Amount: %s", intent.getDoubleExtra("total_amount", 0)));

        setSpinner1();
        setSpinner2();
        loading_data();


        confirm_button.setOnClickListener(this);

    }

    private void loading_data() {

        List<MainPageModelClass> list;
        Service_Confirm_OrderViewHolder adapter;

        list = new ArrayList<>();
        adapter = new Service_Confirm_OrderViewHolder(list,getApplicationContext());

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );
        recyclerView.setAdapter(adapter);

        DatabaseReference dbRef = FirebaseDatabase.getInstance()
                .getReference("Laundry app")
                .child("Orders")
                .child(key)
                .child("items");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();

                for (DataSnapshot data : snapshot.getChildren()) {

                    MainPageModelClass model = data.getValue(MainPageModelClass.class);
                    list.add(model);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void setSpinner1() {
        String[] spinnerData = getResources().getStringArray(R.array.spinner1);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_item,
                R.id.textView,
                spinnerData
        );

        spinner1.setAdapter(adapter);
        spinner1.setPopupBackgroundResource(R.drawable.pop_up);
    }

    private void setSpinner2() {
        String[] spinnerData = getResources().getStringArray(R.array.spinner2);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_item,
                R.id.textView,
                spinnerData
        );

        spinner2.setAdapter(adapter);
        spinner2.setPopupBackgroundResource(R.drawable.pop_up);
    }

    @Override
    public void onClick(View v) {

        String firstName = first_name.getText().toString().trim();
        String lastName = last_name.getText().toString().trim();
        String phone = phone_number.getText().toString().trim();

        if (firstName.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please fill required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        confirm_button.setAlpha(0.6f);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        String orderDate = sdf.format(calendar.getTime());


        String pickupDate = pickup_date.getText().toString();


        HashMap<String, Object> history_map = new HashMap<>();
        history_map.put("order_date", orderDate);
        history_map.put("orderId", productId);
        history_map.put("delivery_date", pickupDate);
        history_map.put("serviceType", service_type);
        history_map.put("first_name", firstName);
        history_map.put("last_name", lastName);
        history_map.put("phone", phone);
        history_map.put("key",key);
        history_map.put("pickup_address",
                spinner2.getSelectedItem().toString() + ", " +
                        spinner1.getSelectedItem().toString());
        history_map.put("total_amount", "৳"+total_amount);
        history_map.put("order_status", "Pending");


        String key = reference.push().getKey();

        if (key != null) {
            reference.child(key).setValue(history_map).addOnCompleteListener(task -> {

                progressBar.setVisibility(View.GONE);
                confirm_button.setAlpha(1f);

                if (task.isSuccessful()) {
                    Toast.makeText(this, "Order Confirmed", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Order Failed", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            progressBar.setVisibility(View.GONE);
            confirm_button.setAlpha(1f);
            Toast.makeText(this, "Error generating order ID", Toast.LENGTH_SHORT).show();
        }
    }
    
}