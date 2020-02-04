package com.FarmPe.Oxkart.Bean;

public class ListBean {

    String name;

    int id,image,loanid,insuranceid;

    public int getInsuranceid() {
        return insuranceid;
    }

    public void setInsuranceid(int insuranceid) {
        this.insuranceid = insuranceid;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getImage() {
        return image;
    }

    public int getLoanid() {
        return loanid;
    }



    public ListBean(String name, int id, int image, int loanid,int insuranceid) {
        this.name=name;
        this.id = id;
        this.loanid = loanid;
        this.image=image;
        this.insuranceid=insuranceid;
    }
}


