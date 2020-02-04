package com.FarmPe.Oxkart.Bean;

public class BankBean {



    String bank_name;
    String branch_name;
    String acc_number;
    String ifsccode_exist;
    String state_id;
    String district_id;
    String bank_details_id;

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    String state_name;
    String district_name;

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getAcc_number() {
        return acc_number;
    }

    public void setAcc_number(String acc_number) {
        this.acc_number = acc_number;
    }

    public String getIfsccode_exist() {
        return ifsccode_exist;
    }

    public void setIfsccode_exist(String ifsccode_exist) {
        this.ifsccode_exist = ifsccode_exist;
    }

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getBank_details_id() {
        return bank_details_id;
    }

    public void setBank_details_id(String bank_details_id) {
        this.bank_details_id = bank_details_id;
    }








    public BankBean(String bank_name, String branch_name, String acc_number, String ifsccode_exist, String state_name,String district_name, String state_id, String district_id, String bank_details_id) {

        this.bank_name = bank_name;
        this.branch_name = branch_name;
        this.acc_number = acc_number;
        this.ifsccode_exist = ifsccode_exist;
        this.state_name = state_name;
        this.district_name = district_name;
        this.state_id = state_id;
        this.district_id = district_id;
        this.bank_details_id = bank_details_id;
    }
}
