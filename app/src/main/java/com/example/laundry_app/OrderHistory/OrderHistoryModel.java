package com.example.laundry_app.OrderHistory;

public class OrderHistoryModel {
    String order_date,order_id,status_text,delivery_date,service_type,pickup_address,total_amount;

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getPickup_address() {
        return pickup_address;
    }

    public void setPickup_address(String pickup_address) {
        this.pickup_address = pickup_address;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getStatus_text() {
        return status_text;
    }

    public void setStatus_text(String status_text) {
        this.status_text = status_text;
    }

    public OrderHistoryModel(String order_date, String order_id, String status_text, String delivery_date, String service_type, String pickup_address, String total_amount){

        this.order_date=order_date;
        this.order_id=order_id;
        this.status_text=status_text;
        this.delivery_date=delivery_date;
        this.service_type=service_type;
        this.pickup_address=pickup_address;
        this.total_amount=total_amount;
    }
}
