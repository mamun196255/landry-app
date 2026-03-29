package com.example.laundry_app.Service_Page;

public class Service_Model {

    private int image1,image2;
    private String time_line_text1,time_line_text2,service_type1,service_type2;
    private double service_amount1,service_amount2;


    public int getImage1() {
        return image1;
    }

    public void setImage1(int image1) {
        this.image1 = image1;
    }

    public int getImage2() {
        return image2;
    }

    public void setImage2(int image2) {
        this.image2 = image2;
    }

    public String getTime_line_text1() {
        return time_line_text1;
    }

    public void setTime_line_text1(String time_line_text1) {
        this.time_line_text1 = time_line_text1;
    }

    public String getTime_line_text2() {
        return time_line_text2;
    }

    public void setTime_line_text2(String time_line_text2) {
        this.time_line_text2 = time_line_text2;
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

    public Service_Model(int image1, int image2, String time_line_text1,
                         String time_line_text2, String service_type1,
                         String service_type2, double service_amount1, double service_amount2){

                   this.image1=image1;
                   this.image2=image2;
                   this.time_line_text1=time_line_text1;
                   this.time_line_text2=time_line_text2;
                   this.service_type1=service_type1;
                   this.service_type2=service_type2;
                   this.service_amount1=service_amount1;
                   this.service_amount2=service_amount2;


    }


}
