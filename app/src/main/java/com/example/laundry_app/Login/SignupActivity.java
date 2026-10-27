package com.example.laundry_app.Login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.laundry_app.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText user_name,user_password,user_email,confirm_password,user_number;
    CardView login_btn;
    TextView sign_btn;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        reference= FirebaseDatabase.getInstance().getReference("Laundry app").child("User");

        user_email=findViewById(R.id.user_email);
        user_name=findViewById(R.id.user_name);
        user_password=findViewById(R.id.user_password);
        confirm_password=findViewById(R.id.confirm_password);
        user_number=findViewById(R.id.user_phone);

        login_btn=findViewById(R.id.login_btn);
        sign_btn=findViewById(R.id.sign_btn);

        login_btn.setOnClickListener(this);
        sign_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.sign_btn){
            onBackPressed();
            finish();
        }
        else{
            setUserSignUp();
        }
    }
    private void setUserSignUp() {

        String email = user_email.getText().toString();
        String phone = user_number.getText().toString();
        String password = user_password.getText().toString();
        String confirm = confirm_password.getText().toString();
        String name=user_name.getText().toString();

        onCheckPassword checkPassword = new onCheckPassword();

        boolean isValid = true;

        if (email.isEmpty()) {
            user_email.setBackgroundResource(R.drawable.signup_back);
            isValid = false;
        }

        if (phone.isEmpty()) {
            user_number.setBackgroundResource(R.drawable.signup_back);
            isValid = false;
        }

        if (password.isEmpty()||name.isEmpty()) {
            user_password.setBackgroundResource(R.drawable.signup_back);
            isValid = false;
        }

        if (confirm.isEmpty()) {
            confirm_password.setBackgroundResource(R.drawable.signup_back);
            isValid = false;
        }

        if (!checkPassword.validPassword(password)) {
            Toast.makeText(this, "password must be 6 character and 1 special character", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        if (!checkPassword.validEmail(email)) {
            Toast.makeText(this, "not valid email", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        if (!checkPassword.ValidNumber(phone)) {
            Toast.makeText(this, "not valid phone number", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        if (!password.equals(confirm)) {
            Toast.makeText(this, "give same password", Toast.LENGTH_SHORT).show();
            isValid = false;
        }


        if (isValid) {
            View dialogView = LayoutInflater.from(SignupActivity.this).inflate(R.layout.loading_view, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);

            builder.setView(dialogView);
            AlertDialog dialog = builder.create();
            dialog.show();

            String sPassword = onCheckPassword.sha256(password);

            HashMap<String,Object> userMap = new HashMap<>();
            userMap.put("email", email);
            userMap.put("user_name",name);
            userMap.put("hotel_notification",true);
            userMap.put("place_notification", true);
            userMap.put("transaction_notification",true);
            userMap.put("transport_notification",true);
            userMap.put("switch_mode",true);
            userMap.put("name","");
            userMap.put("phone", phone);
            userMap.put("password", sPassword);
            userMap.put("wallet", "");
            userMap.put("wishlist", "");
            userMap.put("award", "");
            userMap.put("transaction","");
            userMap.put("photos", "https://firebasestorage.googleapis.com/v0/b/image-store-infirebase.appspot.com/o/profile%2Fdefualt%20profile.jpg?alt=media&token=8857f8b4-b373-49df-baac-d94242e37028");

            reference.child(reference.push().getKey()).setValue(userMap).addOnCompleteListener(task -> {
                dialog.dismiss();
                if (task.isSuccessful()) {
                    Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                } else {
                    Toast.makeText(this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}