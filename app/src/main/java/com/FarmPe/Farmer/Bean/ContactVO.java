package com.FarmPe.Farmer.Bean;

public class ContactVO {


    private String ContactName;
    private String ContactNumber;



    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String contactName) {
        ContactName = contactName;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }
    public ContactVO(String ContactName, String ContactNumber) {


    this.ContactName = ContactName;
    this.ContactNumber = ContactNumber;
}
}