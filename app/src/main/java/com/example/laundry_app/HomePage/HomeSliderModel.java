package com.example.laundry_app.HomePage;

public class HomeSliderModel {

    String image;
    String time_line;
    String service_type;
    double offer_price;

    public HomeSliderModel() {}


    public String getImage() {
        return image;
    }

    public String getTime_line() {
        return time_line;
    }

    public String getService_type() {
        return service_type;
    }

    public double getOffer_price() {
        return offer_price;
    }

    // 🔥 SETTERS (IMPORTANT for Firebase)
    public void setImage(String image) {
        this.image = image;
    }

    public void setTime_line(String time_line) {
        this.time_line = time_line;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public void setOffer_price(double offer_price) {
        this.offer_price = offer_price;
    }

    // OPTIONAL constructor
    public HomeSliderModel(String image, String time_line, String service_type, double offer_price) {
        this.image = image;
        this.time_line = time_line;
        this.service_type = service_type;
        this.offer_price = offer_price;
    }
}