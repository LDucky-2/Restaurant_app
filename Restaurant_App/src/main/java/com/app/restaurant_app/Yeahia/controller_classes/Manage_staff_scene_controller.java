package com.app.restaurant_app.Yeahia.controller_classes;

import com.app.restaurant_app.Employee;
import com.app.restaurant_app.Non_user_classes.Schedule;
import com.app.restaurant_app.Yeahia.controller_classes.Dummy_classes.Select_days_for_add_staff_dummy;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

import static com.app.restaurant_app.Utility.*;
import static com.app.restaurant_app.Yeahia.model_classes.Restaurant_manager.add_staff_schedule_validation;

public class Manage_staff_scene_controller {

    @javafx.fxml.FXML
    private Label staff_id_label;
    @javafx.fxml.FXML
    private Label staff_mobile_number_label;
    @javafx.fxml.FXML
    private Label staff_designation_label;
    @javafx.fxml.FXML
    private TableColumn<Select_days_for_add_staff_dummy,String> day_table_column;
    @javafx.fxml.FXML
    private TableColumn<Select_days_for_add_staff_dummy,String> shift_table_column;
    @javafx.fxml.FXML
    private Label staff_name_label;
    @javafx.fxml.FXML
    private TableView<Select_days_for_add_staff_dummy> staff_schedule_tableview;
    @javafx.fxml.FXML
    private ComboBox<String> shift_combo_box;
    @javafx.fxml.FXML
    private ComboBox<String> day_combo_box;


    public void initialize(){
        shift_combo_box.getItems().addAll("Shift A (09:00 - 16:00)","Shift B (16:00 - 23:00)");
        shift_combo_box.setValue("Shift A (09:00 - 16:00)");
        day_combo_box.getItems().addAll("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
        day_combo_box.setValue("Sunday");

        day_table_column.setCellValueFactory(new PropertyValueFactory<Select_days_for_add_staff_dummy,String>("day"));
        shift_table_column.setCellValueFactory(new PropertyValueFactory<Select_days_for_add_staff_dummy,String>("shift"));

    }
    @javafx.fxml.FXML
    public void fire_employee_button_on_action(ActionEvent actionEvent) {
        try{
            ArrayList<Object> object_arraylist = read_object("data_files/employee_data.bin");
            for (Object object:object_arraylist){
                if (((Employee)object).getEmployee_id() == Integer.parseInt(staff_id_label.getText())){
                    object_arraylist.remove(object);
                    write_object_arraylist_replace("data_files/employee_data.bin",object_arraylist);
                    break;
                }
            }

            ArrayList<Object> schedule_arraylist = read_object("data_files/schedules_data.bin");
            for (Object object : schedule_arraylist){
                if (((Schedule)object).getEmployee_id() == Integer.parseInt(staff_id_label.getText())){
                    schedule_arraylist.remove(object);
                    write_object_arraylist_replace("data_files/schedules_data.bin",schedule_arraylist);
                    return;
                }

            }


        }
        catch (Exception e){
            show_information_alert("smth happened fire_employee_button_on_action");
        }

    }

    @javafx.fxml.FXML
    public void add_to_schedule_button_on_action(ActionEvent actionEvent) {
        ArrayList<Select_days_for_add_staff_dummy> schedule_list = new ArrayList<Select_days_for_add_staff_dummy>(staff_schedule_tableview.getItems());
        for (Select_days_for_add_staff_dummy schedule : schedule_list){
            if (schedule.getDay().equals(day_combo_box.getValue())){
                show_information_alert("Can't have 2 shifts in the same day");
                return;
            }
        }
        staff_schedule_tableview.getItems().add(
                new Select_days_for_add_staff_dummy(
                        day_combo_box.getValue(),
                        shift_combo_box.getValue()
                )
        );
    }

    @javafx.fxml.FXML
    public void remove_from_schedule_button_on_action(ActionEvent actionEvent) {
        ArrayList<Select_days_for_add_staff_dummy> schedule_list = new ArrayList<>(staff_schedule_tableview.getItems());
        for (Select_days_for_add_staff_dummy schedule : schedule_list){
            if (schedule.getDay().equals(day_combo_box.getValue())){
                staff_schedule_tableview.getItems().remove(schedule);
                return;
            }
        }
    }

    @javafx.fxml.FXML
    public void set_schedule_button_on_action(ActionEvent actionEvent) {
        ArrayList<Select_days_for_add_staff_dummy> schedule_list = new ArrayList<>(staff_schedule_tableview.getItems());
        if(add_staff_schedule_validation(schedule_list)){
            try{
                ArrayList<Object> object_arraylist = read_object("data_files/schedules_data.bin");
                for (Object object : object_arraylist){
                    if (((Schedule)object).getEmployee_id() == Integer.parseInt(staff_id_label.getText())){
                        ((Schedule)object).setAssigned_schedule_arraylist(schedule_list);
                        write_object_arraylist_replace("data_files/schedules_data.bin",object_arraylist);
                        return;
                    }

                }
            }
            catch (Exception e) {
                show_information_alert("smth happened set_schedule_button_on_action");
            }
        }
    }
    public void get_resources(Employee employee){
        staff_id_label.setText(String.valueOf(employee.getEmployee_id()));
        staff_designation_label.setText(employee.getEmployee_type());
        staff_mobile_number_label.setText(String.valueOf(employee.getMobile_number()));
        staff_name_label.setText(employee.getName());

        try{
            ArrayList<Object> object_arraylist = read_object("data_files/schedules_data.bin");
            for (Object object: object_arraylist){
                if (((Schedule)object).getEmployee_id() == employee.getEmployee_id()){
                    for (Select_days_for_add_staff_dummy schedule: ((Schedule)object).getAssigned_schedule_arraylist()){
                        staff_schedule_tableview.getItems().add(schedule);
                    }
                }
            }
        }
        catch (Exception e) {

        }
    }

    @javafx.fxml.FXML
    public void go_to_previous_scene_button_on_action(ActionEvent actionEvent) {
        try{
            scene_changer(actionEvent,"Yeahia/Restaurant_manager_dashboard.fxml");
        }
        catch (Exception e){
            show_information_alert("smth happened");
        }
    }
}
