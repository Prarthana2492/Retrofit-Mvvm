package com.FarmPe.Farmer.Bean;


public class Request_Class_HomePage_Bean {

    public String getVeg_name() {
        return veg_name;
    }

    public void setVeg_name(String veg_name) {
        this.veg_name = veg_name;
    }

    String veg_name;
    String id;
    int image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }


    public void setImage(int image) {
        this.image = image;
    }


    public Request_Class_HomePage_Bean(String veg_name,String id,int image) {

        this.veg_name = veg_name;
        this.id = id;
        this.image = image;

    }
}


