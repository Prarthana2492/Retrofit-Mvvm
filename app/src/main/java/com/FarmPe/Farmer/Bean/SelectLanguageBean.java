package com.FarmPe.Farmer.Bean;

public class SelectLanguageBean {

    String vendor,imageicon;

    public String getLanguageid() {
        return languageid;
    }

    public void setLanguageid(String languageid) {
        this.languageid = languageid;
    }

    String languageid;


    public String getVendor() {
        return vendor;
    }

    public String getImageicon() {
        return imageicon;
    }



    public SelectLanguageBean( String vendor, String languageid,String imageicon) {
        this.languageid=languageid;
        this.vendor = vendor;
        this.imageicon=imageicon;
    }
}


