package com.example.laundry_app.Main_Page;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.laundry_app.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class Confirm_Order extends AppCompatActivity implements View.OnClickListener {

    private Spinner spinner1, spinner2;
    CardView confirm_button;
    ProgressBar progressBar;
    DatabaseReference reference;
    ImageView image;

    TextView pickup_date, product_name, product_quantity, sub_total, total_amount;
    EditText first_name, last_name, phone_number;

    String productId, service_type;
    int quantity = 0;
    double price = 0.0;
    String key;
    String image_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirm_order);

        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        pickup_date = findViewById(R.id.pickup_date);
        product_name = findViewById(R.id.product_name);
        product_quantity = findViewById(R.id.product_quantity);
        sub_total = findViewById(R.id.sub_total);
        total_amount = findViewById(R.id.total_amount);
        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        phone_number = findViewById(R.id.phone_number);
        confirm_button = findViewById(R.id.confirm_button);
        progressBar = findViewById(R.id.progress);
        image=findViewById(R.id.image);

        Intent intent = getIntent();

        if (intent != null) {
            price = intent.getDoubleExtra("sub_total", 0.0);
            quantity = intent.getIntExtra("quantity", 0);
            productId = intent.getStringExtra("productId");
            service_type = intent.getStringExtra("service_type");
            image_url=intent.getStringExtra("image");
            key=intent.getStringExtra("key");


            sub_total.setText("Subtotal: ৳" + price);
            total_amount.setText("Total Amount: ৳" + price);
            product_quantity.setText("Quantity: " + quantity);
            pickup_date.setText(intent.getStringExtra("pickup_date"));
            product_name.setText(intent.getStringExtra("type"));
            Picasso.get()
                    .load(image_url)
                    .placeholder(R.drawable.load_back)
                    .error(R.drawable.load_back)
                    .into(image);
        }

        reference = FirebaseDatabase.getInstance().getReference("Laundry app").child("Confirm Order");

        setSpinner1();
        setSpinner2();

        confirm_button.setOnClickListener(this);
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
        history_map.put("pickup_address",
                spinner2.getSelectedItem().toString() + ", " +
                        spinner1.getSelectedItem().toString());
        history_map.put("total_amount", "৳"+price);
        history_map.put("order_status", "Pending");
        history_map.put("key",key);


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