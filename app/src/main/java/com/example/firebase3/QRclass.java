package com.example.firebase3;

public class QRclass {
    String patient_name;
    String username;
    String password;


    public String getPatient_name() {
        return patient_name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public QRclass(){}

    public QRclass(String TextFromQR) {
        String[] strsplit = TextFromQR.split("\\|");
        this.patient_name = strsplit[0];
        this.username = strsplit[1];
        this.password = strsplit[2];
    }
}
