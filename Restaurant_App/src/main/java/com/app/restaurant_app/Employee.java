package com.app.restaurant_app;


import javafx.scene.control.Alert;
import java.io.Serializable;
import java.util.ArrayList;

import static com.app.restaurant_app.Utility.*;

public class Employee implements Serializable {
    private final int employee_id;
    private final String employee_type;
    private String password;
    protected String name;
    protected long mobile_number;

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

    @Override
    public String toString() {
        return "Employee{" +
                "employee_id=" + employee_id +
                ", employee_type='" + employee_type + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Employee(int employee_id, String employee_type) {
        this.employee_id = employee_id;
        this.employee_type = employee_type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmployee_type() {
        return employee_type;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public static Employee verify_login_and_return_employee(int employee_id,String password){
        try {

        ArrayList<Object> employee_arraylist = new ArrayList<Object>(read_object("data_files/employee_data.bin"));

        for (Object employee : employee_arraylist) {
            if ((((Employee) employee).getEmployee_id()) == employee_id) {
                if (((Employee) employee).getPassword() == null) {
                    show_information_alert( "Employee isn't registered please sign up");
                    return null;
                } else if ((((Employee) employee).getPassword()).equals(password)) {
                    return (Employee) employee;
                } else {
                    show_information_alert( "Wrong password");
                    return null;
                }
            }
        }
        show_information_alert( "Employee with this id doesn't exist");
        return null;
        }
        catch (Exception e){
            show_information_alert("Exception in verify_login_and_return_employee method in Employee class");
            return null;
        }

    }

    public static Employee verify_sign_in_and_return_employee(int employee_id) {
        try {
            ArrayList<Object> employee_arraylist = new ArrayList<Object>(Utility.read_object("data_files/employee_data.bin"));
            for (Object employee : employee_arraylist) {
                if ((((Employee) employee).getEmployee_id()) == employee_id) {

                    if (((Employee) employee).getPassword() == null) {
                        employee_arraylist.remove(employee);
                        write_object_arraylist_replace("data_files/employee_data.bin", employee_arraylist);
                        return (Employee) employee;
                    } else {
                        show_information_alert( "A employee with this id is already signed up, please log in");
                        return null;
                    }

                }
            }
            show_information_alert("Given id doesn't match existing registered employee");
            return null;
        }
        catch (Exception e) {
            show_information_alert("Exception in verify_sign_in_and_return_employee method in Employee class");
            return null;
        }
    }

}
