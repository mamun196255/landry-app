package com.example.laundry_app.Profile;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import com.example.laundry_app.Login.SigninActivity;
import com.example.laundry_app.OrderHistory.Order_History;
import com.example.laundry_app.R;
import java.util.ArrayList;
import java.util.List;
public class ProfilePage extends Fragment implements View.OnClickListener{

    CardView order_history,pending,accept,cancel,delivered,login_btn;
    ImageButton settings,notification;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_profile_page, container, false);

        order_history=view.findViewById(R.id.order_history);
        pending=view.findViewById(R.id.pending);
        accept=view.findViewById(R.id.accept);
        cancel=view.findViewById(R.id.cancel);
        delivered=view.findViewById(R.id.delivered);
        settings=view.findViewById(R.id.setting);
        notification=view.findViewById(R.id.notification);


        getChildFragmentManager().beginTransaction()
                .replace(R.id.frame, new Order_History()).commit();


        order_history.setOnClickListener(this);
        pending.setOnClickListener(this);
        accept.setOnClickListener(this);
        cancel.setOnClickListener(this);
        delivered.setOnClickListener(this);
        settings.setOnClickListener(this);
        notification.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.order_history){

            getChildFragmentManager().beginTransaction().replace(R.id.frame, new Order_History()).commit();
            order_history.setCardBackgroundColor(Color.parseColor("#FFA500"));
            pending.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
            accept.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
            cancel.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
            delivered.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));

        }
        else if(v.getId()==R.id.pending){

            getChildFragmentManager().beginTransaction().replace(R.id.frame, new PendingFragment()).commit();
            order_history.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
            pending.setCardBackgroundColor(Color.parseColor("#FFA500"));
            accept.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
            cancel.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
            delivered.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
        }

        else if(v.getId()==R.id.accept){

            getChildFragmentManager().beginTransaction().replace(R.id.frame, new AcceptFragment()).commit();
            order_history.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
            pending.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
            accept.setCardBackgroundColor(Color.parseColor("#FFA500"));
            cancel.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
            delivered.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
        }
        else if(v.getId()==R.id.cancel){

            getChildFragmentManager().beginTransaction().replace(R.id.frame, new CancelFragment()).commit();
            order_history.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
            pending.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
            accept.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
            cancel.setCardBackgroundColor(Color.parseColor("#FFA500"));
            delivered.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
        }

        else if (v.getId()==R.id.setting){
            setSettings(v);
        }
        else if (v.getId()==R.id.notification){
            setNotification(v);
        }

        else{
            getChildFragmentManager().beginTransaction().replace(R.id.frame, new DeliveredFragment()).commit();
            order_history.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
            pending.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
            accept.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
            cancel.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
            delivered.setCardBackgroundColor(Color.parseColor("#FFA500"));
        }
    }
    private void setNotification(View v) {
        View popupView = getLayoutInflater().inflate(R.layout.notification_popup, null);

        RecyclerView recyclerView = popupView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<NotificationItem> itemList = new ArrayList<>();
        itemList.add(new NotificationItem("Order Ready", "10 minutes ago", R.drawable.cargo));
        itemList.add(new NotificationItem("Washing Started", "20 minutes ago", R.drawable.pants));
        itemList.add(new NotificationItem("Out for Delivery", "30 minutes ago", R.drawable.img));

        NotificationAdapter adapter = new NotificationAdapter(itemList);
        recyclerView.setAdapter(adapter);

        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int marginRight = 10;
        int popupHeight = (3 * screenHeight) / 4;
        int popupWidth = (5 * screenWidth) / 6;

        final PopupWindow popupWindow = new PopupWindow(
                popupView,
                popupWidth,
                popupHeight,
                true
        );

        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setElevation(2);


        popupWindow.showAsDropDown(v,0, 10);
    }

    private void setSettings(View v) {

        View popupView = getLayoutInflater().inflate(R.layout.settings_profile, null);

        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

        int popupHeight = (3 * screenHeight) / 4;
        int popupWidth = (5 * screenWidth) / 6;

        final PopupWindow popupWindow = new PopupWindow(
                popupView,
                popupWidth,
                popupHeight,
                true
        );

        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setElevation(10);

        popupWindow.showAsDropDown(v,0, 10);

         CardView login_btn=popupView.findViewById(R.id.login_btn);
         login_btn.setOnClickListener(v1 -> {
             ShowSigninActivity();
         });




    }

    private void ShowSigninActivity() {
        startActivity(new Intent(getContext(),SigninActivity.class));
    }


    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 100);
    }
}