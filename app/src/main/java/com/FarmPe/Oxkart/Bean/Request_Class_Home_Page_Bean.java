package com.FarmPe.Oxkart.Bean;

public class Request_Class_Home_Page_Bean {


    String looking_fr_price;
    String brand;
    String model;
    String purchase_timline;
    String location;
    String user_name;
    String phone_no;

    public String getMasked_phone_no() {
        return masked_phone_no;
    }

    public void setMasked_phone_no(String masked_phone_no) {
        this.masked_phone_no = masked_phone_no;
    }

    String masked_phone_no;
     int insurance;

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }


    public int getInsurance() {
        return insurance;
    }

    public void setInsurance(int insurance) {
        this.insurance = insurance;
    }

    public int getFinance() {
        return finance;
    }

    public void setFinance(int finance) {
        this.finance = finance;
    }

    int finance;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getLooking_fr_price_id() {
        return looking_fr_price_id;
    }

    public void setLooking_fr_price_id(String looking_fr_price_id) {
        this.looking_fr_price_id = looking_fr_price_id;
    }

    String looking_fr_price_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;

    public String getModel_id() {
        return model_id;
    }

    public void setModel_id(String model_id) {
        this.model_id = model_id;
    }

    String model_id;

    public Boolean getIsshortlisted() {
        return isshortlisted;
    }

    public void setIsshortlisted(Boolean isshortlisted) {
        this.isshortlisted = isshortlisted;
    }

    Boolean isshortlisted;

    public String getProfile_image() {
        return profile_image;
    }


    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    String profile_image;

    public String getModel_image() {
        return model_image;
    }

    public void setModel_image(String model_image) {
        this.model_image = model_image;
    }

    String model_image;

    public String getLooking_fr_price() {
        return looking_fr_price;
    }

    public void setLooking_fr_price(String looking_fr_price) {
        this.looking_fr_price = looking_fr_price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPurchase_timline() {
        return purchase_timline;
    }

    public void setPurchase_timline(String purchase_timline) {
        this.purchase_timline = purchase_timline;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }




    public Request_Class_Home_Page_Bean(String looking_fr_price, String brand, String model, String purchase_timline, String location,
                                        String model_image, String profile_image, String id, String model_id, String looking_fr_price_id,
                                        Boolean isshortlisted, String user_name, String phone_no, String masked_phone_no, int insurance, int finance) {

        this.looking_fr_price = looking_fr_price;
        this.brand = brand;
        this.model = model;
        this.purchase_timline = purchase_timline;
        this.location = location;
        this.model_image = model_image;
        this.profile_image = profile_image;
        this.id = id;
        this.model_id = model_id;
        this.looking_fr_price_id = looking_fr_price_id;
        this.isshortlisted = isshortlisted;
        this.user_name = user_name;
        this.phone_no = phone_no;
        this.masked_phone_no = masked_phone_no;
        this.insurance = insurance;
        this.finance = finance;

    }


}
