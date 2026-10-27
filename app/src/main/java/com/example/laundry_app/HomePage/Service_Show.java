package com.example.laundry_app.HomePage;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

import com.example.laundry_app.Main_Page.Confirm_Order;
import com.example.laundry_app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class Service_Show extends AppCompatActivity implements View.OnClickListener{

    CardView item_order;
    ImageView minus_button,plus_button,imageView;
    TextView quantity,pickup_date,sub_total,total_amount,product_name;
    double price=0.0f,total;
    String productId;
    String type;
    Spinner spinner;
    String image_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_service_show);

        item_order=findViewById(R.id.item_order);
        minus_button=findViewById(R.id.minus_button);
        plus_button=findViewById(R.id.plus_button);
        quantity=findViewById(R.id.quantity);
        pickup_date=findViewById(R.id.pickup_date);
        sub_total=findViewById(R.id.sub_total);
        total_amount=findViewById(R.id.total_amount);
        spinner=findViewById(R.id.spinner);
        product_name=findViewById(R.id.product_name);
        imageView=findViewById(R.id.image);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 0);

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        String date = sdf.format(calendar.getTime());
        pickup_date.setText(date);


        Intent intent=getIntent();
        price=intent.getDoubleExtra("price",0);
        type=intent.getStringExtra("type");
        productId=intent.getStringExtra("productId");
        image_url=intent.getStringExtra("image");

        Picasso.get()
                .load(image_url)
                .placeholder(R.drawable.load_back)
                .error(R.drawable.load_back)
                .into(imageView);
        total=price;
        sub_total.setText(String.valueOf(total));
        total_amount.setText(String.valueOf(total));
        product_name.setText(type);

        setSpinner();
        minus_button.setOnClickListener(this);
        plus_button.setOnClickListener(this);
        item_order.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        int qnt = Integer.parseInt(quantity.getText().toString());

        if (v.getId() == R.id.minus_button && qnt > 1) {
            qnt = qnt - 1;
        }

       else if (v.getId() == R.id.plus_button && qnt < 15) {
            qnt = qnt + 1;
        }

        quantity.setText(String.valueOf(qnt));

         total = qnt * price;

        sub_total.setText(String.valueOf(total));
        total_amount.setText(String.valueOf(total));

        if(v.getId()==R.id.item_order){

            addQuantity(qnt);


        }
    }

    public void addQuantity(int qnt){

        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("Laundry app")
                .child("Orders");

        String orderId = reference.push().getKey();

        HashMap<String, Object> history_map = new HashMap<>();
        history_map.put("image",image_url);
        history_map.put("price",price);
        history_map.put("quantity",qnt);
        history_map.put("service_type",type);

        reference.child(orderId).child("items").child("0").setValue(history_map).addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                Intent intent=new Intent(Service_Show.this, Confirm_Order.class);
                intent.putExtra("sub_total",total);
                intent.putExtra("quantity",qnt);
                intent.putExtra("pickup_date",pickup_date.getText().toString());
                intent.putExtra("product_name",type);
                intent.putExtra("productId",productId);
                intent.putExtra("service_type",spinner.getSelectedItem().toString());
                intent.putExtra("image",image_url);
                intent.putExtra("key",orderId);
                startActivity(intent);

            } else {
                Toast.makeText(this, "Order Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void setSpinner() {

        String[] spinner1String = getResources().getStringArray(R.array.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_item,
                R.id.textView,
                spinner1String
        );

        spinner.setAdapter(adapter);
        spinner.setPopupBackgroundResource(R.drawable.pop_up);
    }
}