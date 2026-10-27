package com.example.laundry_app.Order;

public class Ordered_Model {
    String image;
    private String service_type;
    private double price;
    private int quantity;

    public Ordered_Model(){};

    public Ordered_Model(String  image, String service_type, double price, int quantity) {
        this.image = image;
        this.service_type = service_type;
        this.price = price;
        this.quantity = quantity;
    }

    public String getImage() { return image; }
    public String getService_type() { return service_type; }

    public double getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
