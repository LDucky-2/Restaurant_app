package com.app.restaurant_app.Sakib.model_classes;

import com.app.restaurant_app.Employee;

import java.io.Serializable;

public class Kitchen_staff extends Employee implements Serializable {

    public Kitchen_staff(int staff_id, String staff_type) {
        super(staff_id,staff_type);
    }
}
