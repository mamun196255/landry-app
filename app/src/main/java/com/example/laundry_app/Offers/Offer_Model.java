package com.example.laundry_app.Offers;

public class Offer_Model {

    String image1,image2;
    String title,offer_text;
    int offer_price;

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


    public String getOffer_text() {
        return offer_text;
    }

    public void setOffer_text(String offer_text) {
        this.offer_text = offer_text;
    }




    public Offer_Model() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOffer_price() {
        return offer_price;
    }

    public void setOffer_price(int offer_price) {
        this.offer_price = offer_price;
    }

    public Offer_Model(String image1, String image2, String title, String offer_text, int offer_price){

        this.image1=image1;
        this.image2=image2;
        this.title=title;
        this.offer_text=offer_text;
        this.offer_price=offer_price;
    }


}
