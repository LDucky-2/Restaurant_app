package com.app.restaurant_app.Yeahia.controller_classes;

import com.app.restaurant_app.Employee;
import com.app.restaurant_app.Yeahia.model_classes.Restaurant_manager;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.util.ArrayList;

import static com.app.restaurant_app.Utility.*;

public class Restaurant_manager_dashboard_controller {
    @javafx.fxml.FXML
    private ChoiceBox<String> staff_filter_choice_box;
    @javafx.fxml.FXML
    private Label filtered_staff_count_label;
    @javafx.fxml.FXML
    private TableView<Employee> staff_tableview;
    @javafx.fxml.FXML
    private Label designation_label;
    @javafx.fxml.FXML
    private Label id_label;
    @javafx.fxml.FXML
    private Label name_label;
    @javafx.fxml.FXML
    private TableColumn<Employee,String> designation_table_column;
    @javafx.fxml.FXML
    private TableColumn<Employee,Integer> staff_id_table_column;
    @javafx.fxml.FXML
    private TableColumn<Employee,String> name_table_column;
    @javafx.fxml.FXML
    private TableColumn<Employee,Long> mobile_no_table_column;


    public void initialize(){
        // choice box
        staff_filter_choice_box.getItems().addAll("All","Waiter staff", "Accountant", "Marketing manager", "Inventory manager", "Kitchen staff", "Delivery driver");
        staff_filter_choice_box.setValue("All");

        // table view
        name_table_column.setCellValueFactory(new PropertyValueFactory<Employee,String>("name"));
        mobile_no_table_column.setCellValueFactory(new PropertyValueFactory<Employee,Long>("mobile_number"));
        staff_id_table_column.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("employee_id"));
        designation_table_column.setCellValueFactory(new PropertyValueFactory<Employee,String>("employee_type"));
        try {
            ArrayList<Object> employeeArrayList = read_object("data_files/employee_data.bin");
            for (Object employee:employeeArrayList){
                staff_tableview.getItems().add((Employee) employee);
            }
        }
        catch (Exception ignored){

        }
        filtered_staff_count_label.setText(String.valueOf(staff_tableview.getItems().size()));
    }

    public void get_resources(Restaurant_manager restaurant_manager){
        name_label.setText(restaurant_manager.getName());
        designation_label.setText(restaurant_manager.getEmployee_type());
        id_label.setText(String.valueOf(restaurant_manager.getEmployee_id()));

    }

    @javafx.fxml.FXML
    public void Manage_staff_button_on_action(ActionEvent actionEvent) {
        try {
            Manage_staff_scene_controller controller = scene_changer_returns_controller(actionEvent, "Yeahia/Manage_staff_scene.fxml");
            controller.get_resources(staff_tableview.getSelectionModel().getSelectedItem());
        }
        catch (Exception ignored){

        }
    }

    @javafx.fxml.FXML
    public void add_staff_button_on_action(ActionEvent actionEvent){
        try{
            new_scene("Yeahia/Add_new_staff_scene.fxml");
        }
        catch (Exception e){
            show_information_alert("Exception in add_staff_button_on_action in Restaurant_manager_dashboard_controller, check code");
        }

    }

    @javafx.fxml.FXML
    public void see_profile_button_on_action(ActionEvent actionEvent) {

    }

    @javafx.fxml.FXML
    public void filter_staff_button_on_action(ActionEvent actionEvent) {
        staff_tableview.getItems().clear();
        String staff_type = staff_filter_choice_box.getValue();
        try{
            ArrayList<Object> object_arraylist =  read_object("data_files/employee_data.bin");
            Employee employee;
            if (staff_type.equals("All")){
                for (Object object : object_arraylist){
                    employee = (Employee)object;
                    staff_tableview.getItems().add(employee);
                }
            }
            else{
                for (Object object : object_arraylist){
                    employee = (Employee)object;
                    if (employee.getEmployee_type().equals(staff_type)){
                        staff_tableview.getItems().add(employee);
                    }
                }
            }

            filtered_staff_count_label.setText(String.valueOf(staff_tableview.getItems().size()));
        } catch (Exception ignored) {

        }
    }

    @javafx.fxml.FXML
    public void see_product_button_on_action(ActionEvent actionEvent) {
        try{
            scene_changer(actionEvent,"Yeahia/see_product_scene.fxml");
        } catch (Exception ignored) {

        }
    }

    @javafx.fxml.FXML
    public void log_out_button_on_action(ActionEvent actionEvent) {
        try{
            scene_changer(actionEvent,"Log_in_scene.fxml");
        }
        catch (Exception e){
            show_information_alert("smth happened");
        }
    }
}
