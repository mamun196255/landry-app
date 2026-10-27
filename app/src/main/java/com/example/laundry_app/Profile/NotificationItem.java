package com.example.laundry_app.Profile;

public class NotificationItem {
        private String title;
        private String time;
        private int imageRes;

        public NotificationItem(String title, String time, int imageRes) {
            this.title = title;
            this.time = time;
            this.imageRes = imageRes;
        }

        public String getTitle() { return title; }
        public String getTime() { return time; }
        public int getImageRes() { return imageRes; }
    }


