package com.FarmPe.Oxkart.Bean;



public class First_Language_Bean {

    String vendor;
    int imageicon;
    String languageid;



    public int getImageicon() {
        return imageicon;
    }

    public void setImageicon(int imageicon) {
        this.imageicon = imageicon;
    }



    public String getLanguageid() {
        return languageid;
    }

    public void setLanguageid(String languageid) {
        this.languageid = languageid;
    }


    public String getVendor() {
        return vendor;
    }



    public First_Language_Bean( String vendor, String languageid,int imageicon) {

        this.vendor = vendor;
        this.languageid=languageid;
        this.imageicon=imageicon;
    }
}


