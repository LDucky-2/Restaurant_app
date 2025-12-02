package com.app.restaurant_app.Ashik.model_classes;

import java.io.Serializable;

public class Customer implements Serializable {
    private final String gmail;
    private String password;
    private String name;
    private long mobile_number;

    public Customer(String gmail, long mobile_number, String name, String password) {
        this.gmail = gmail;
        this.mobile_number = mobile_number;
        this.name = name;
        this.password = password;
    }

    public String getGmail() {
        return gmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(long mobile_number) {
        this.mobile_number = mobile_number;
    }
}
