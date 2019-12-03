package com.FarmPe.Farmer.Bean;

public class BankBean {

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getIfsccode() {
        return ifsccode;
    }

    public void setIfsccode(String ifsccode) {
        this.ifsccode = ifsccode;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    String bankname,name,phonenumber,ifsccode,area,city,id;

    public BankBean(String bankname, String name, String phonenumber, String ifsccode, String area, String city, String id) {

        this.bankname = bankname;
        this.name = name;
        this.phonenumber = phonenumber;
        this.ifsccode = ifsccode;
        this.area = area;
        this.city = city;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
