package com.app.restaurant_app.Yeahia.model_classes;

import com.app.restaurant_app.Employee;

import java.io.Serializable;

public class Waiter_staff extends Employee implements Serializable {


    public Waiter_staff(int staff_id ,String staff_type) {
        super(staff_id,staff_type);

    }

}
