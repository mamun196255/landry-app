package com.example.laundry_app.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.laundry_app.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SigninActivity extends AppCompatActivity implements View.OnClickListener {

     CardView signin_btn;
     TextInputEditText user_email,user_password;
     TextView signup,forget_password;
    DatabaseReference reference;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signin);

        reference= FirebaseDatabase.getInstance().getReference("Laundry app").child("User");

        sharedPreferences = getSharedPreferences("UserRole", Context.MODE_PRIVATE);
        user_email=findViewById(R.id.user_email);
        user_password=findViewById(R.id.user_password);
        signin_btn=findViewById(R.id.signin_btn);
        signup=findViewById(R.id.sign_up);
        forget_password=findViewById(R.id.forget_password);

        String savedEmail = sharedPreferences.getString("email", null);
        String savedPassword = sharedPreferences.getString("password", null);

        if (savedEmail != null && savedPassword != null) {
            user_email.setText(savedEmail);
            user_password.setText(savedPassword);
        }

        forget_password.setOnClickListener(this);
        signup.setOnClickListener(this);
        signin_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.signin_btn){
            setUser();
        }
        else if(v.getId()==R.id.sign_up){
            startActivity(new Intent(SigninActivity.this,SignupActivity.class));
        }
        else if(v.getId()==R.id.forget_password){
            startActivity(new Intent(this,Forget_Password.class));
        }
    }
    private void setUser() {

        String user_name=user_email.getText().toString();
        String user_pass=user_password.getText().toString();
        if(user_pass.isEmpty()||user_name.isEmpty()){
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        else {

            View dialogView = LayoutInflater.from(SigninActivity.this).inflate(R.layout.loading_view, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(SigninActivity.this);
            builder.setView(dialogView);
            AlertDialog dialog = builder.create();
            dialog.show();

            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                boolean found = false;

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot userSnap : snapshot.getChildren()) {

                        String dbEmail = userSnap.child("email").getValue(String.class);
                        String dbPass = userSnap.child("password").getValue(String.class);
                        String profile_url=userSnap.child("Photos").getValue(String.class);
                        String name=userSnap.child("user_name").getValue(String.class);

                        if (user_name.equals(dbEmail) && onCheckPassword.sha256(user_pass).equals(dbPass)) {
                            found = true;

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("profile_key",userSnap.getKey());
                            editor.putString("profile_url",profile_url);
                            editor.putString("profile_name",name);
                            editor.apply();

                            break;
                        }
                    }

                    dialog.dismiss();

                    if (found) {
                        Toast.makeText(SigninActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }

                    else {
                        Toast.makeText(SigninActivity.this, "Invalid User", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    dialog.dismiss();
                    Toast.makeText(SigninActivity.this, "Server Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        }
    }

int void (int x){

int i=0;

     for(i=0;i<x;i++){
                    Toast.makeText(SigninActivity.this, "Server Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
