package com.example.laundry_app.Main_Page;

public class MainPageModelClass {
    String image1,image2;
    String image;
    String date1,date2,service_type1,service_type2;
    int quantity;
    double service_amount1,service_amount2;

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public String getService_type1() {
        return service_type1;
    }

    public void setService_type1(String service_type1) {
        this.service_type1 = service_type1;
    }

    public String getService_type2() {
        return service_type2;
    }

    public void setService_type2(String service_type2) {
        this.service_type2 = service_type2;
    }

    public double getService_amount1() {
        return service_amount1;
    }

    public void setService_amount1(double service_amount1) {
        this.service_amount1 = service_amount1;
    }

    public double getService_amount2() {
        return service_amount2;
    }

    public void setService_amount2(double service_amount2) {
        this.service_amount2 = service_amount2;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public MainPageModelClass(String image, int quantity) {

        this.image = image;
        this.quantity = quantity;
    }
    public MainPageModelClass(){};
    public MainPageModelClass(
           String image1,String image2,String date1,
            String date2,String service_type1,String service_type2,
            double service_amount1,double service_amount2
    ){
        this.image1 = image1;
        this.image2 = image2;
        this.date1 = date1;
        this.date2 = date2;
        this.service_type1 = service_type1;
        this.service_type2 = service_type2;
        this.service_amount1 = service_amount1;
        this.service_amount2 = service_amount2;
    }
}
