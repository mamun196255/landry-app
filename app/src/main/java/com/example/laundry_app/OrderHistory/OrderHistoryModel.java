package com.example.laundry_app.OrderHistory;

public class OrderHistoryModel {
    String order_date,orderId,order_status,delivery_date,serviceType,pickup_address,total_amount;


    String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getOrder_id() {
        return orderId;
    }

    public void setOrder_id(String orderId) {
        this.orderId = orderId;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getService_type() {
        return serviceType;
    }

    public void setService_type(String serviceType) {
        this.serviceType = serviceType;
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
        return order_status;
    }

    public void setStatus_text(String order_status) {
        this.order_status = order_status;
    }
    public OrderHistoryModel() {}
    public OrderHistoryModel(String order_date, String orderId, String order_status, String delivery_date, String service_type, String pickup_address, String total_amount){

        this.order_date=order_date;
        this.orderId=orderId;
        this.order_status=order_status;
        this.delivery_date=delivery_date;
        this.serviceType=service_type;
        this.pickup_address=pickup_address;
        this.total_amount=total_amount;
    }
}
