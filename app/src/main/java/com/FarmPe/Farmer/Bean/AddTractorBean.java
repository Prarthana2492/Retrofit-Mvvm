package com.FarmPe.Farmer.Bean;

public class AddTractorBean {

   private String image;
    private String prod_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    private  boolean isSelected;
   /// private  int image;

    public AddTractorBean(String image, String prod_name, String id,boolean isSelected) {

        this.image = image;
        this.prod_name = prod_name;
        this.id = id;
        this.isSelected = isSelected;

    }


    public String getProd_name() {
        return prod_name;
    }



    public String getImage() {
        return image;
    }

/* public String getImage() {
        return image;
    }*/
}
