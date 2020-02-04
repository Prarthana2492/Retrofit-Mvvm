package com.FarmPe.Oxkart.Bean;

public class AddTractorBean2 {

   private String image1;
    private String prod_name;
    private String id;
    private String prod_category;

    public String getProd_category() {
        return prod_category;
    }

    public void setProd_category(String prod_category) {
        this.prod_category = prod_category;
    }


   /// private  int image;


    public String getProd_name() {
        return prod_name;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image1;
    }


    public AddTractorBean2(String image1, String prod_name,String prod_category, String id) {

        this.image1 = image1;
        this.prod_name = prod_name;
        this.prod_category = prod_category;
        this.id = id;

    }


}
