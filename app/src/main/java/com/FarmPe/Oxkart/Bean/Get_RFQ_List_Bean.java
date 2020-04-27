package com.FarmPe.Oxkart.Bean;

public class Get_RFQ_List_Bean {

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


    public String getModel_image() {
        return model_image;
    }

    public void setModel_image(String model_image) {
        this.model_image = model_image;
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

//    public String getModelid() {
//        return modelid;
//    }
//
//    public void setModelid(String modelid) {
//        this.modelid = modelid;
//    }

    public String getLookingfordetails_id() {
        return lookingfordetails_id;
    }

    public void setLookingfordetails_id(String lookingfordetails_id) {
        this.lookingfordetails_id = lookingfordetails_id;
    }


    String model_image;
    String location;
    String id;
  //  String modelid;
    String lookingfordetails_id;



    public Get_RFQ_List_Bean(String modelname, String looking_fordetails, String brandname,String model_image,
                      String location,String id,String lookingfordetails_id) {


        this.modelname = modelname;
        this.looking_fordetails = looking_fordetails;
        this.brandname = brandname;
        this.model_image = model_image;
        this.location = location;
        this.id = id;
      //  this.modelid = modelid;
        this.lookingfordetails_id = lookingfordetails_id;

    }

}
