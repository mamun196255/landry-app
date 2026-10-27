package com.example.laundry_app.Profile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.laundry_app.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private List<NotificationItem> notifications;

    public NotificationAdapter(List<NotificationItem> notifications) {
        this.notifications = notifications;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title, time;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.notificationImage);
            title = itemView.findViewById(R.id.notificationTitle);
            time = itemView.findViewById(R.id.notificationTime);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NotificationItem item = notifications.get(position);
        holder.title.setText(item.getTitle());
        holder.time.setText(item.getTime());
        holder.imageView.setImageResource(item.getImageRes());
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }
}
