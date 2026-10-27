package com.example.laundry_app.Order;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundry_app.Main_Page.MainPageModelClass;
import com.example.laundry_app.OrderHistory.OrderHistoryModel;
import com.example.laundry_app.R;
import com.example.laundry_app.Service_Page.Service_Confirm_OrderViewHolder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Order_Details extends AppCompatActivity implements View.OnClickListener {

    ImageView back_icon;
    TextView orderId,order_date,order_status,delivery_date,pickup_address,payment_method,total_amount,full_name,phone_number,estimated_delivery;
    CardView download,cancel_order,contract;
    RecyclerView order_recycler;
    DatabaseReference reference;
    String Key,Key2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_details);



        orderId=findViewById(R.id.orderId);
        order_date=findViewById(R.id.order_date);
        order_status=findViewById(R.id.order_status);
        delivery_date=findViewById(R.id.delivery_date);
        pickup_address=findViewById(R.id.pickup_address);
        payment_method=findViewById(R.id.payment_method);
        total_amount=findViewById(R.id.total_amount);
        full_name=findViewById(R.id.full_name);
        phone_number=findViewById(R.id.phone_number);
        estimated_delivery=findViewById(R.id.estimated_delivery);
        download=findViewById(R.id.download);
        cancel_order=findViewById(R.id.cancel_order);
        contract=findViewById(R.id.contract);
        order_recycler=findViewById(R.id.order_recycler);

        Intent intent=getIntent();
        Key=intent.getStringExtra("key");

        reference = FirebaseDatabase.getInstance().getReference("Laundry app").child("Confirm Order").child(Key);

        back_icon=findViewById(R.id.back);
        back_icon.setOnClickListener(this);
        download.setOnClickListener(this);
        cancel_order.setOnClickListener(this);
        contract.setOnClickListener(this);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                orderId.setText(snapshot.child("orderId").getValue(String.class));
                order_date.setText(snapshot.child("order_date").getValue(String.class));
                order_status.setText(snapshot.child("order_status").getValue(String.class));
                delivery_date.setText(snapshot.child("delivery_date").getValue(String.class));
                pickup_address.setText(snapshot.child("pickup_address").getValue(String.class));
                total_amount.setText(snapshot.child("total_amount").getValue(String.class));
                full_name.setText(snapshot.child("first_name").getValue(String.class));
                phone_number.setText(snapshot.child("phone").getValue(String.class));
                estimated_delivery.setText(snapshot.child("orderId").getValue(String.class));
                Key2=snapshot.child("key").getValue(String.class);

                if (Key2 != null) {
                    getItem();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void getItem(){

        List<MainPageModelClass> list;
        Service_Confirm_OrderViewHolder adapter;

        list = new ArrayList<>();
        adapter = new Service_Confirm_OrderViewHolder(list,getApplicationContext());

        order_recycler.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );
        order_recycler.setAdapter(adapter);

        DatabaseReference dbRef = FirebaseDatabase.getInstance()
                .getReference("Laundry app")
                .child("Orders")
                .child(Key2)
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

    @Override
    public void onClick(View v) {

            if (v.getId() == R.id.back) {
                onBackPressed();
            }

            else if (v.getId() == R.id.download) {
                downloadInvoice();
            }

            else if (v.getId() == R.id.cancel_order) {
                cancelOrder();
            }

            else if (v.getId() == R.id.contract) {
                contactUs();
            }

        onBackPressed();
    }

    private void contactUs() {


        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + "013085089655"));
        startActivity(intent);
    }
    private void cancelOrder() {

        if (Key != null) {
            reference.child("order_status").setValue("Cancelled")
                    .addOnSuccessListener(unused -> {
                        Toast.makeText(this, "Order Cancelled", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Failed to cancel", Toast.LENGTH_SHORT).show();
                    });
        }
    }

    private void downloadInvoice() {

        try {

            String fileName = "Invoice_" + orderId.getText().toString() + ".pdf";

            File file = new File(getExternalFilesDir(null), fileName);

            PdfDocument pdfDocument = new PdfDocument();

            Paint paint = new Paint();
            Paint title = new Paint();

            PdfDocument.PageInfo pageInfo =
                    new PdfDocument.PageInfo.Builder(1200, 2000, 1).create();

            PdfDocument.Page page = pdfDocument.startPage(pageInfo);
            Canvas canvas = page.getCanvas();


            title.setTextSize(60);
            title.setFakeBoldText(true);
            canvas.drawText("Laundry Invoice", 300, 100, title);


            paint.setTextSize(35);

            int y = 200;

            canvas.drawText("Order ID: " + orderId.getText(), 50, y, paint);
            y += 60;

            canvas.drawText("Order Date: " + order_date.getText(), 50, y, paint);
            y += 60;

            canvas.drawText("Delivery Date: " + delivery_date.getText(), 50, y, paint);
            y += 60;

            canvas.drawText("Name: " + full_name.getText(), 50, y, paint);
            y += 60;

            canvas.drawText("Phone: " + phone_number.getText(), 50, y, paint);
            y += 60;

            canvas.drawText("Address: " + pickup_address.getText(), 50, y, paint);
            y += 60;

            canvas.drawText("Payment: " + payment_method.getText(), 50, y, paint);
            y += 60;

            canvas.drawText("Total: " + total_amount.getText(), 50, y, paint);

            pdfDocument.finishPage(page);

            pdfDocument.writeTo(new FileOutputStream(file));
            pdfDocument.close();

            Toast.makeText(this, "PDF Saved: " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();

            openPDF(file);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    private void openPDF(File file) {

        Uri uri = FileProvider.getUriForFile(
                this,
                getPackageName() + ".provider",
                file
        );

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "application/pdf");
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "No PDF viewer found", Toast.LENGTH_SHORT).show();
        }
    }
}