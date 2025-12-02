package com.app.restaurant_app;

import com.app.restaurant_app.Ashik.controller_classes.Customer_dashboard_controller;
import com.app.restaurant_app.Ashik.model_classes.Accountant;
import com.app.restaurant_app.Ashik.model_classes.Customer;
import com.app.restaurant_app.Sakib.controller_classes.Inventory_manager_dashboard_controller;
import com.app.restaurant_app.Sakib.controller_classes.Kitchen_staff_dashboard_controller;
import com.app.restaurant_app.Sakib.model_classes.Inventory_manager;
import com.app.restaurant_app.Sakib.model_classes.Kitchen_staff;
import com.app.restaurant_app.Soyaiminul.controller_classes.Delivery_driver_dashboard_controller;
import com.app.restaurant_app.Soyaiminul.controller_classes.Marketing_manager_dashboard_controller;
import com.app.restaurant_app.Soyaiminul.model_classes.Delivery_driver;
import com.app.restaurant_app.Soyaiminul.model_classes.Marketing_manager;
import com.app.restaurant_app.Yeahia.controller_classes.Restaurant_manager_dashboard_controller;
import com.app.restaurant_app.Yeahia.controller_classes.Waiter_dashboard_controller;
import com.app.restaurant_app.Yeahia.model_classes.Restaurant_manager;
import com.app.restaurant_app.Yeahia.model_classes.Waiter_staff;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

import static com.app.restaurant_app.Employee.verify_sign_in_and_return_employee;
import static com.app.restaurant_app.Utility.*;


public class Sign_in_scene_controller {
    @FXML
    private TextField gmail_textfield;
    @FXML
    private TextField mobile_number_textfield;
    @FXML
    private TextField password_textfield;
    @FXML
    private TextField name_textfield;

    @FXML
    public void sign_in_on_action(ActionEvent actionEvent) throws IOException {

        boolean are_any_fields_empty =
                gmail_textfield.getText().isEmpty() &&
                        mobile_number_textfield.getText().isEmpty() &&
                        password_textfield.getText().isEmpty() &&
                        name_textfield.getText().isEmpty();
        boolean is_number_valid =
                (mobile_number_textfield.getText().length() == 11) && is_long(mobile_number_textfield.getText()) && mobile_number_textfield.getText().startsWith("0");
        boolean is_gmail_valid =
                (gmail_textfield.getText().length() == 6 && is_integer(gmail_textfield.getText())) ||
                        gmail_textfield.getText().endsWith("@gmail.com");
        boolean is_password_valid = password_textfield.getText().length() >= 6;

        if(!are_any_fields_empty && is_gmail_valid && is_number_valid && is_password_valid) {
            String unique_identifier = gmail_textfield.getText();

            if (is_integer(unique_identifier)) {
                Employee employee = verify_sign_in_and_return_employee(Integer.parseInt(unique_identifier));
                if (employee instanceof Waiter_staff waiter_staff) {
                    waiter_staff.setMobile_number(Long.parseLong(mobile_number_textfield.getText()));
                    waiter_staff.setPassword(password_textfield.getText());
                    waiter_staff.setName(name_textfield.getText());
                    Waiter_dashboard_controller controller = scene_changer_returns_controller(actionEvent, "Yeahia/Waiter_staff_dashboard_scene.fxml");
                    controller.get_resources(waiter_staff);
                    write_object("data_files/employee_data.bin", waiter_staff);
                }
                else if (employee instanceof Restaurant_manager restaurant_manager) {
                    if (Restaurant_manager.getSame_type_staff_count() == 0) {
                        restaurant_manager.setMobile_number(Long.parseLong(mobile_number_textfield.getText()));
                        restaurant_manager.setPassword(password_textfield.getText());
                        restaurant_manager.setName(name_textfield.getText());

                        Restaurant_manager.setSame_type_staff_count((byte) (Restaurant_manager.getSame_type_staff_count() + 1));
                        Restaurant_manager_dashboard_controller controller = scene_changer_returns_controller(actionEvent, "Yeahia/Restaurant_manager_dashboard.fxml");
                        controller.get_resources(restaurant_manager);
                        write_object("data_files/employee_data.bin", restaurant_manager);
                    }
                    else {
                        show_information_alert("Restaurant manager already exists");
                    }

                }
                else if (employee instanceof Inventory_manager inventory_manager) {
                    inventory_manager.setMobile_number(Long.parseLong(mobile_number_textfield.getText()));
                    inventory_manager.setPassword(password_textfield.getText());
                    inventory_manager.setName(name_textfield.getText());
                    Inventory_manager_dashboard_controller controller = scene_changer_returns_controller(actionEvent,"Sakib/Inventory_manager_dashboard.fxml");
                    controller.get_resources(inventory_manager);
                    write_object("data_files/employee_data.bin", inventory_manager);

                } else if (employee instanceof Marketing_manager marketing_manager) {
                    marketing_manager.setMobile_number(Long.parseLong(mobile_number_textfield.getText()));
                    marketing_manager.setPassword(password_textfield.getText());
                    marketing_manager.setName(name_textfield.getText());
                    Marketing_manager_dashboard_controller controller = scene_changer_returns_controller(actionEvent,"Soyaiminul/marketing_manager_dashboard.fxml");
                    controller.get_resources(marketing_manager);
                    write_object("data_files/employee_data.bin",marketing_manager);
                }
                else if (employee instanceof Delivery_driver delivery_driver) {
                    delivery_driver.setMobile_number(Long.parseLong(mobile_number_textfield.getText()));
                    delivery_driver.setPassword(password_textfield.getText());
                    delivery_driver.setName(name_textfield.getText());
                    Delivery_driver_dashboard_controller controller = scene_changer_returns_controller(actionEvent,"Soyaiminul/delivery_driver_dashboard.fxml");
                    controller.get_resources(delivery_driver);
                    write_object("data_files/employee_data.bin",delivery_driver);
                }
                else if (employee instanceof Kitchen_staff kitchen_staff) {
                    kitchen_staff.setMobile_number(Long.parseLong(mobile_number_textfield.getText()));
                    kitchen_staff.setPassword(password_textfield.getText());
                    kitchen_staff.setName(name_textfield.getText());
                    Kitchen_staff_dashboard_controller controller = scene_changer_returns_controller(actionEvent,"Sakib/kitchen_staff_dashboard.fxml");
                    controller.get_resources(kitchen_staff);
                    write_object("data_files/employee_data.bin",kitchen_staff);
                }
                else if (employee == null) {


                }
                else {
                    write_object("data_files/employee_data.bin", employee);
                    show_information_alert("employee was returned, and was valid but didn't match any instance check, please debug the code");
                }
            }
            else {
                String alert_string;
                Alert alert;
                ArrayList<Object> customer_arraylist = new ArrayList<>(Utility.read_object("data_files/customer_data.bin"));

                for (Object customer : customer_arraylist) {
                    if (unique_identifier.equals(((Customer) customer).getGmail())) {
                        alert_string = "Gmail already used, please go login or use another gmail";
                        alert = new Alert(Alert.AlertType.INFORMATION, alert_string);
                        alert.showAndWait();
                        return;
                    }
                }
                write_object("data_files/customer_data.bin", (new Customer(
                        gmail_textfield.getText(),
                        Long.parseLong(mobile_number_textfield.getText()),
                        name_textfield.getText(),
                        password_textfield.getText()))
                );
                scene_changer(actionEvent, "Ashik/Customer_dashboard");
                Customer_dashboard_controller.get_resources((new Customer(
                        gmail_textfield.getText(),
                        Long.parseLong(mobile_number_textfield.getText()),
                        name_textfield.getText(),
                        password_textfield.getText()))
                );
            }
        }
        else {
            show_information_alert("Invalid input");

        }
        gmail_textfield.clear();
        password_textfield.clear();
        mobile_number_textfield.clear();
        name_textfield.clear();
    }

    public void login_button_on_action(ActionEvent actionEvent) {
        try{
        scene_changer(actionEvent,"Log_in_scene.fxml");
        }
        catch (Exception e){
            show_information_alert("smth happened");
        }
    }
}
