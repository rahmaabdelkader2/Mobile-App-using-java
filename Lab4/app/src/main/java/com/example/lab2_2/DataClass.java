package com.example.lab2_2;

public class DataClass {
    private String mobileNumber;
    private String message;

    public DataClass(String mobileNumber, String message) {
        this.mobileNumber = mobileNumber;
        this.message = message;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}