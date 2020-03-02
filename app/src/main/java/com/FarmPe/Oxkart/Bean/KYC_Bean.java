package com.FarmPe.Oxkart.Bean;


public class KYC_Bean {


    String doc_type;
    String id_number;

    public String getDoc_type() {
        return doc_type;
    }

    public void setDoc_type(String doc_type) {
        this.doc_type = doc_type;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getDoc_name() {
        return doc_name;
    }

    public void setDoc_name(String doc_name) {
        this.doc_name = doc_name;
    }

    String doc_name;


    public KYC_Bean(String doc_type,String id_number,String doc_name) {

        this.doc_type = doc_type;
        this.id_number = id_number;
        this.doc_name = doc_name;

    }
}


