package com.app.restaurant_app.Yeahia.controller_classes;
import com.app.restaurant_app.Yeahia.model_classes.Waiter_staff;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Waiter_dashboard_controller {

    @javafx.fxml.FXML
    private TableColumn day_table_column;
    @javafx.fxml.FXML
    private Label designation_label;
    @javafx.fxml.FXML
    private Label id_label;
    @javafx.fxml.FXML
    private TableColumn shift_table_column;
    @javafx.fxml.FXML
    private TableView schedule_tableview;
    @javafx.fxml.FXML
    private Label name_label;

    public void initialize(){

    }
    public void get_resources(Waiter_staff waiter_staff){

    }

    @javafx.fxml.FXML
    public void order_food_button_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void log_out_button_on_action(ActionEvent actionEvent) {
    }
}
