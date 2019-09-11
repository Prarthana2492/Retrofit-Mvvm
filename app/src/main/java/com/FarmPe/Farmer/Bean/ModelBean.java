package com.FarmPe.Farmer.Bean;

public class ModelBean {

    private String brand_name;
    private String model_name;
    private String horse_power;

    public String getPdf_brochure() {
        return pdf_brochure;
    }

    public void setPdf_brochure(String pdf_brochure) {
        this.pdf_brochure = pdf_brochure;
    }

    private String pdf_brochure;

    public String getDrive_type() {
        return drive_type;
    }

    public void setDrive_type(String drive_type) {
        this.drive_type = drive_type;
    }

    private String drive_type;

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public String getHorse_power() {
        return horse_power;
    }

    public void setHorse_power(String horse_power) {
        this.horse_power = horse_power;
    }

    public String getCubic_capcity() {
        return cubic_capcity;
    }

    public void setCubic_capcity(String cubic_capcity) {
        this.cubic_capcity = cubic_capcity;
    }

    public String getTransmission_type() {
        return transmission_type;
    }

    public void setTransmission_type(String transmission_type) {
        this.transmission_type = transmission_type;
    }

    public String getClutch_type() {
        return clutch_type;
    }

    public void setClutch_type(String clutch_type) {
        this.clutch_type = clutch_type;
    }

    public String getSteering() {
        return steering;
    }

    public void setSteering(String steering) {
        this.steering = steering;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String cubic_capcity;
    private String transmission_type;
    private String clutch_type;
    private String steering;
    private String id;
    private String image;
    private String lookingForDetailsId;


    public String getLookingForDetailsId() {
        return lookingForDetailsId;
    }

    public void setLookingForDetailsId(String lookingForDetailsId) {
        this.lookingForDetailsId = lookingForDetailsId;
    }

    public ModelBean(String brand_name, String model_name, String drive_type, String steering, String horse_power, String clutch_type,
                     String transmission_type , String cubic_capcity, String image, String pdf_brochure, String id, String lookingForDetailsId)
                    {

        this.brand_name = brand_name;
        this.model_name = model_name;
        this.drive_type = drive_type;
        this.steering = steering;
        this.horse_power = horse_power;

        this.clutch_type = clutch_type;
        this.transmission_type = transmission_type;
        this.cubic_capcity = cubic_capcity;
        this.image = image;
        this.pdf_brochure = pdf_brochure;
        this.id = id;
        this. lookingForDetailsId = lookingForDetailsId;

    }


}
