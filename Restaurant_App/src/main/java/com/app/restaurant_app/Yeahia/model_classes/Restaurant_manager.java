package com.app.restaurant_app.Yeahia.model_classes;

import com.app.restaurant_app.Ashik.model_classes.Accountant;
import com.app.restaurant_app.Employee;
import com.app.restaurant_app.Sakib.model_classes.Inventory_manager;
import com.app.restaurant_app.Sakib.model_classes.Kitchen_staff;
import com.app.restaurant_app.Soyaiminul.model_classes.Delivery_driver;
import com.app.restaurant_app.Soyaiminul.model_classes.Marketing_manager;
import com.app.restaurant_app.Yeahia.controller_classes.Dummy_classes.Select_days_for_add_staff_dummy;

import static com.app.restaurant_app.Utility.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Restaurant_manager extends Employee implements Serializable {
    private static byte same_type_staff_count = 0;

    public Restaurant_manager(int staff_id, String staff_type) {
        super(staff_id,staff_type);
    }
    public static Employee validate_and_verify_then_add_staff_return(String employee_id, String employee_type, ArrayList<Select_days_for_add_staff_dummy> schedule){
        if (add_staff_schedule_validation(schedule)){
            if (is_integer(employee_id) && employee_id.length() == 6) {

                int staff_id = Integer.parseInt(employee_id);

                try {
                    ArrayList<Object> object_arraylist = read_object("data_files/employee_data.bin");


                    for (Object employeeObj : object_arraylist) {
                        Employee existingEmployee = (Employee) employeeObj;
                        if (existingEmployee.getEmployee_id() == staff_id) {
                            show_information_alert("Staff with this ID already exists, please use another ID");
                            return null;
                        }
                    }
                }
                catch (Exception e) {
                    show_information_alert("smth happened");
                    return null;
                }
            }
            else {
                show_information_alert("Invalid id");
                return null;
            }

            Employee employee1;
            int staff_id = Integer.parseInt(employee_id);

            if (employee_type.equals("Waiter staff")) {
                employee1 = new Waiter_staff(staff_id, employee_type);
            } else if (employee_type.equals("Accountant")) {
                employee1 = new Accountant(staff_id, employee_type);
            } else if (employee_type.equals("Marketing manager")) {
                employee1 = new Marketing_manager(staff_id, employee_type);
            } else if (employee_type.equals("Inventory manager")) {
                employee1 = new Inventory_manager(staff_id, employee_type);
            } else if (employee_type.equals("Kitchen staff")) {
                employee1 = new Kitchen_staff(staff_id, employee_type);
            } else if (employee_type.equals("Delivery driver")) {
                employee1 = new Delivery_driver(staff_id, employee_type);
            } else {
                show_information_alert("Invalid employee type");
                return null;
            }

            // 5. Save employee
            try{
                write_object("data_files/employee_data.bin", employee1);
            } catch (Exception e) {
                show_information_alert("smth happened");
            }

            return employee1;
        }
        else {
            return null;
        }
    }
    public static Boolean add_staff_schedule_validation(ArrayList<Select_days_for_add_staff_dummy> assigned_schedule){
        if (assigned_schedule.size() <= 4 && assigned_schedule.size() >= 2){
            ArrayList<String> days_array = new ArrayList<String>();
            for (Select_days_for_add_staff_dummy schedule: assigned_schedule){
                if (days_array.contains(schedule.getDay())){
                    show_information_alert("can't have two shifts on the same day");
                    return false;
                }
                else {
                    days_array.add(schedule.getDay());
                }
            }
            return true;
        }
        else {
            show_information_alert("Schedule has to consist of 2-4 shifts (14 to 28 hours per week)");
            return false;
        }
    }

    public static short getSame_type_staff_count() {
        return same_type_staff_count;
    }

    public static void setSame_type_staff_count(byte same_type_staff_count) {
        Restaurant_manager.same_type_staff_count = same_type_staff_count;
    }
}
