package com.FarmPe.Farmer.Bean;

public class FarmsImageBean {

  //  private String image;
    private String prod_price,modelname,duration,farmer_name,location,id,hp;
    private  String image;

    public String getLocation_details() {
        return location_details;
    }

    public void setLocation_details(String location_details) {
        this.location_details = location_details;
    }

    private String location_details;

    public FarmsImageBean(String image, String prod_price, String modelname,String hp,String duration,String farmer_name,String location,String id,String location_details) {

        this.image = image;
        this.prod_price = prod_price;
        this.modelname = modelname;
        this.duration = duration;
        this.farmer_name = farmer_name;
        this.location = location;
        this.id = id;
        this.hp = hp;
        this.location_details = location_details;

    }

    public String getImage() {
        return image;
    }

    public String getProd_price() {
        return prod_price;
    }

    public String getModelname() {
        return modelname;
    }

    public String getHp() {
        return hp;
    }

    public String getDuration() {
        return duration;
    }

    public String getFarmer_name() {
        return farmer_name;
    }

    public String getLocation() {
        return location;
    }

    public String getId() {
        return id;
    }
}
