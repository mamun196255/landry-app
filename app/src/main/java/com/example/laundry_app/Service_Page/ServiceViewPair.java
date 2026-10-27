package com.example.laundry_app.Service_Page;

public class ServiceViewPair {
    private Service_Model pair1;
    private Service_Model pair2;

   public ServiceViewPair(){};

    public Service_Model getPair2() {
        return pair2;
    }
    public void setPair2(Service_Model pair2) {
        this.pair2 = pair2;
    }

    public Service_Model getPair1() {
        return pair1;
    }

    public void setPair1(Service_Model pair1) {
        this.pair1 = pair1;
    }
    ServiceViewPair(Service_Model pair1, Service_Model pair2){
       this.pair1=pair1;
       this.pair2=pair2;
   }
}
