package com.app.restaurant_app.Non_user_classes;

import com.app.restaurant_app.Yeahia.controller_classes.Dummy_classes.Select_days_for_add_staff_dummy;

import java.io.Serializable;
import java.util.ArrayList;

public class Schedule implements Serializable {
    private final int employee_id;
    private ArrayList<Select_days_for_add_staff_dummy> assigned_schedule_arraylist;

    public Schedule(int employee_id, ArrayList<Select_days_for_add_staff_dummy> assigned_schedule_arraylist) {
        this.employee_id = employee_id;
        this.assigned_schedule_arraylist = assigned_schedule_arraylist;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public ArrayList<Select_days_for_add_staff_dummy> getAssigned_schedule_arraylist() {
        return assigned_schedule_arraylist;
    }

    public void setAssigned_schedule_arraylist(ArrayList<Select_days_for_add_staff_dummy> assigned_schedule_arraylist) {
        this.assigned_schedule_arraylist = assigned_schedule_arraylist;
    }
}
