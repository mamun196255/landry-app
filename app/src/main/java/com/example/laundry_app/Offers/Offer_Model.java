package com.example.laundry_app.Offers;

public class Offer_Model {

    int icon,image;
    String service_type,offer_text;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

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

    public String getOffer_text() {
        return offer_text;
    }

    public void setOffer_text(String offer_text) {
        this.offer_text = offer_text;
    }

    public Offer_Model(int icon, int image, String service_type, String offer_text){

        this.icon=icon;
        this.image=image;
        this.service_type=service_type;
        this.offer_text=offer_text;
    }

}
