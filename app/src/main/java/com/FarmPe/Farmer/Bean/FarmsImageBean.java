package com.FarmPe.Farmer.Bean;

public class FarmsImageBean {

  //  private String image;

    String modelname;
    String looking_fordetails;
    String brandname;

    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname;
    }

    public String getLooking_fordetails() {
        return looking_fordetails;
    }

    public void setLooking_fordetails(String looking_fordetails) {
        this.looking_fordetails = looking_fordetails;
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    public String getHorse_power() {
        return horse_power;
    }

    public void setHorse_power(String horse_power) {
        this.horse_power = horse_power;
    }

    public String getModel_image() {
        return model_image;
    }

    public void setModel_image(String model_image) {
        this.model_image = model_image;
    }

    public String getLooking_for_finance() {
        return looking_for_finance;
    }

    public void setLooking_for_finance(String looking_for_finance) {
        this.looking_for_finance = looking_for_finance;
    }

    public String getPurchase_timeline() {
        return purchase_timeline;
    }

    public void setPurchase_timeline(String purchase_timeline) {
        this.purchase_timeline = purchase_timeline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModelid() {
        return modelid;
    }

    public void setModelid(String modelid) {
        this.modelid = modelid;
    }

    public String getLookingfordetails_id() {
        return lookingfordetails_id;
    }

    public void setLookingfordetails_id(String lookingfordetails_id) {
        this.lookingfordetails_id = lookingfordetails_id;
    }

    String horse_power;
    String model_image;
    String looking_for_finance;
    String purchase_timeline;
    String name;
    String mobileno;
    String location;
    String id;
    String modelid;
    String lookingfordetails_id;



    public FarmsImageBean(String modelname, String looking_fordetails, String brandname,String horse_power,String model_image,String looking_for_finance,
                          String purchase_timeline,String name,String mobileno,String location,String id,String modelid,String lookingfordetails_id) {


        this.modelname = modelname;
        this.looking_fordetails = looking_fordetails;
        this.brandname = brandname;
        this.horse_power = horse_power;
        this.model_image = model_image;
        this.looking_for_finance = looking_for_finance;
        this.purchase_timeline = purchase_timeline;
        this.name = name;
        this.mobileno = mobileno;
        this.location = location;
        this.id = id;
        this.modelid = modelid;
        this.lookingfordetails_id = lookingfordetails_id;

    }

}
