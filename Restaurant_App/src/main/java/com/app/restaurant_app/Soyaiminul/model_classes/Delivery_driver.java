package com.app.restaurant_app.Soyaiminul.model_classes;

import com.app.restaurant_app.Employee;

import java.io.Serializable;

public class Delivery_driver extends Employee implements Serializable {

    public Delivery_driver(int staff_id, String staff_type) {
        super(staff_id,staff_type);
    }
}
