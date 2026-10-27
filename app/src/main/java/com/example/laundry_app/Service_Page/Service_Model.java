package com.example.laundry_app.Service_Page;

public class Service_Model {

    private String image;
    private String time_line,service_type;
    private double service_amount;


   public Service_Model(){};
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getTime_line() {
        return time_line;
    }

    public void setTime_line_text(String time_line) {
        this.time_line = time_line;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public double getService_amount() {
        return service_amount;
    }
    public void setService_amount(double service_amount) {
        this.service_amount = service_amount;
    }
    public Service_Model(String image, String time_line,
                        String service_type1,
                         double service_amount){

                   this.image=image;
                   this.time_line=time_line;
                   this.service_type=service_type1;
                   this.service_amount=service_amount;
    }
}
