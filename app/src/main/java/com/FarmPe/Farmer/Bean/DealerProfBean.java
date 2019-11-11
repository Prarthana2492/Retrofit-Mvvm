package com.FarmPe.Farmer.Bean;

public class DealerProfBean {

  //  private String image;
    private String dealer_name,product,dealer_type,location;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    private  int image;

    public DealerProfBean(int image, String dealer_name, String product, String dealer_type, String location) {

        this.image = image;
        this.dealer_name = dealer_name;
        this.product = product;
        this.dealer_type = dealer_type;
        this.location = location;
    }

    public String getDealer_name() {
        return dealer_name;
    }

    public String getProduct() {
        return product;
    }

    public String getDealer_type() {
        return dealer_type;
    }

    public String getLocation() {
        return location;
    }


}
