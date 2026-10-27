package com.example.laundry_app.Main_Page;

public class SliderItem {

    private String image;
    private int offer_price;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getOffer_price() {
        return offer_price;
    }

    public void setOffer_price(int offer_price) {
        this.offer_price = offer_price;
    }

    public SliderItem(){};
    public SliderItem(String image, int offer_price) {
        this.image = image;
        this.offer_price=offer_price;
    }

}