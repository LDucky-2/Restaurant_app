package com.app.restaurant_app.Yeahia.controller_classes;

import com.app.restaurant_app.Employee;
import com.app.restaurant_app.Non_user_classes.Schedule;
import com.app.restaurant_app.Yeahia.controller_classes.Dummy_classes.Select_days_for_add_staff_dummy;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

import static com.app.restaurant_app.Utility.*;
import static com.app.restaurant_app.Yeahia.model_classes.Restaurant_manager.validate_and_verify_then_add_staff_return;

public class Add_new_staff_scene_controller {
    @FXML
    private ChoiceBox<String> new_staff_type_choicebox;
    @FXML
    private TextField new_staff_id_textfield;
    @FXML
    private TableColumn<Select_days_for_add_staff_dummy,String> days_coloumn;
    @FXML
    private TableView<Select_days_for_add_staff_dummy> schedule_tableview;
    @FXML
    private TableColumn<Select_days_for_add_staff_dummy,String> shifts_coloumn;


    public void initialize() {
        // choice box initialization
        new_staff_type_choicebox.getItems().addAll("Waiter staff", "Accountant", "Marketing manager", "Inventory manager", "Kitchen staff", "Delivery driver");
        new_staff_type_choicebox.setValue("Waiter staff");

        // tableview initialization
        String[] day_array = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        schedule_tableview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        days_coloumn.setCellValueFactory(new PropertyValueFactory<Select_days_for_add_staff_dummy,String>("day"));
        shifts_coloumn.setCellValueFactory(new PropertyValueFactory<Select_days_for_add_staff_dummy,String>("shift"));

        for (String day: day_array){
            schedule_tableview.getItems().add((new Select_days_for_add_staff_dummy(
                    day,
                    "Shift A (09:00 - 16:00)"
                    ))
            );
            schedule_tableview.getItems().add((new Select_days_for_add_staff_dummy(
                    day,
                    "Shift B (16:00 - 23:00)"
            )));
        }


    }
    @FXML
    public void add_staff_button_on_action(ActionEvent actionEvent) {
        ObservableList<Select_days_for_add_staff_dummy> observable_list =  schedule_tableview.getSelectionModel().getSelectedItems();
        ArrayList<Select_days_for_add_staff_dummy> schedule = new ArrayList<Select_days_for_add_staff_dummy>(observable_list);
        Employee employee = validate_and_verify_then_add_staff_return((new_staff_id_textfield.getText()),new_staff_type_choicebox.getValue(),schedule);

        if (employee != null){
            Schedule employee_schedule = new Schedule(Integer.parseInt(new_staff_id_textfield.getText()),schedule);
            try{
                write_object("data_files/schedules_data.bin",employee_schedule);
            }
            catch (Exception ignored) {

            }
            close_window(actionEvent);
        }
    }




}
