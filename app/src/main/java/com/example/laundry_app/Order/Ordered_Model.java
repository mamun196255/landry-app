package com.example.laundry_app.Order;

public class Ordered_Model {
    int image;
    String service_type,item_amount;
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }



    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getItem_amount() {
        return item_amount;
    }

    public void setItem_amount(String item_amount) {
        this.item_amount = item_amount;
    }


    public Ordered_Model(int image, String service_type, String item_amount){

       this.image=image;

       this.service_type=service_type;
       this.item_amount=item_amount;

   }
}
