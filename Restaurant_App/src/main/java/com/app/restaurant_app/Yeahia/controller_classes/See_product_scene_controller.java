package com.app.restaurant_app.Yeahia.controller_classes;

import com.app.restaurant_app.Non_user_classes.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

import static com.app.restaurant_app.Utility.*;

public class See_product_scene_controller {
    @javafx.fxml.FXML
    private TableView<Product> product_list_tableview;
    @javafx.fxml.FXML
    private TableColumn<Product,String> product_name_table_column;
    @javafx.fxml.FXML
    private TableColumn<Product, ArrayList<String>> product_ingredient_table_column;
    @javafx.fxml.FXML
    private TableColumn<Product,Integer> product_sales_table_column;
    @javafx.fxml.FXML
    private TableColumn<Product,Integer> product_price_table_column;
    @javafx.fxml.FXML
    private TextArea new_product_ingredients_textarea;
    @javafx.fxml.FXML
    private TextField new_product_price_textfield;
    @javafx.fxml.FXML
    private ChoiceBox<String> ingredient_choice_box;
    @javafx.fxml.FXML
    private TextField new_product_name_textfield;
    @javafx.fxml.FXML
    private TextField new_ingredient_name_textfield;

    ArrayList<String> new_product_ingredient_arraylist = new ArrayList<String>();

    public void initialize(){
        // choice box
        try{
        ArrayList<Object> object_arraylist = read_object("data_files/ingredients_data.bin");
            for (Object object : object_arraylist){
                ingredient_choice_box.getItems().add((String) object);
            }
            ingredient_choice_box.setValue(ingredient_choice_box.getItems().getFirst());
        }
        catch (Exception ignored){
//            show_information_alert("choice box initialize in see_product");
        }

        // tableview
        product_name_table_column.setCellValueFactory(new PropertyValueFactory<Product,String>("product_name"));
        product_ingredient_table_column.setCellValueFactory(new PropertyValueFactory<Product,ArrayList<String>>("ingredients"));
        product_price_table_column.setCellValueFactory(new PropertyValueFactory<Product,Integer>("price"));
        product_sales_table_column.setCellValueFactory(new PropertyValueFactory<Product,Integer>("sales"));
        try{
            ArrayList<Object> object_arraylist = read_object("data_files/products_data.bin");
            for (Object object:object_arraylist){
                product_list_tableview.getItems().add((Product)object);
            }
        }
        catch (Exception e) {

        }

    }

    @javafx.fxml.FXML
    public void add_product_ingredient_button_on_action(ActionEvent actionEvent) {
        if (new_product_ingredient_arraylist.contains(ingredient_choice_box.getValue())){
            show_information_alert("ingredient already added");
        }
        else {
            new_product_ingredient_arraylist.add(ingredient_choice_box.getValue());
            new_product_ingredients_textarea.appendText(ingredient_choice_box.getValue()+",\n");
        }
    }

    @javafx.fxml.FXML
    public void add_new_product_button_on_action(ActionEvent actionEvent) {
        if (is_integer(new_product_price_textfield.getText()) && !new_product_ingredient_arraylist.isEmpty() && !new_product_name_textfield.getText().isEmpty()){
            Product product = Product.verify_new_product_and_return(new_product_name_textfield.getText(),new_product_ingredient_arraylist,Integer.parseInt(new_product_price_textfield.getText()));
            try{
                write_object("data_files/products_data.bin",product);
                product_list_tableview.getItems().add(product);
                new_product_ingredients_textarea.clear();
                new_product_price_textfield.clear();
                new_product_name_textfield.clear();
                new_product_ingredient_arraylist.clear();
            }
            catch (Exception e){
                show_information_alert("Exception in add_new_product_button_on_action");
            }

        }
        else {
            show_information_alert("Invalid product input");
        }

    }

    @javafx.fxml.FXML
    public void remove_product_button_on_action(ActionEvent actionEvent) {
        try {
            Product product = product_list_tableview.selectionModelProperty().get().getSelectedItem();
            ArrayList<Object> products_arraylist = new ArrayList<>(product_list_tableview.getItems());
            product_list_tableview.getItems().remove(product);
            products_arraylist.remove(product);
            write_object_arraylist_replace("data_files/products_data.bin",products_arraylist);
        }
        catch (Exception e){
            show_information_alert("Exception in remove_product_button_on_action");
        }
    }

    @javafx.fxml.FXML
    public void add_new_ingredient_button_on_action(ActionEvent actionEvent) {
        if (new_ingredient_name_textfield.getText().isEmpty()){
            show_information_alert("Invalid ingredient name");
            return;
        }

        try {
            ArrayList<Object> object_arraylist = read_object("data_files/ingredients_data.bin");

            if (object_arraylist.contains(new_ingredient_name_textfield.getText())) {
                return;
            }
            else {
                object_arraylist.add(new_ingredient_name_textfield.getText());
                write_object_arraylist_replace("data_files/ingredients_data.bin", object_arraylist);
                ingredient_choice_box.getItems().add(new_ingredient_name_textfield.getText());
                ingredient_choice_box.setValue(ingredient_choice_box.getItems().getFirst());
                new_ingredient_name_textfield.clear();
            }
        }
        catch(Exception e){
            show_information_alert("Exception in add_new_ingredient_button_on_action");
        }
    }

    @javafx.fxml.FXML
    public void reset_new_product_ingredients_button_on_action(ActionEvent actionEvent) {
        new_product_ingredient_arraylist.clear();
        new_product_ingredients_textarea.clear();
    }

    @FXML
    public void go_to_previous_scene_on_action(ActionEvent actionEvent) {
        try{
            scene_changer(actionEvent,"Yeahia/Restaurant_manager_dashboard.fxml");
        }
        catch (Exception e){
            show_information_alert("smth happened");
        }
    }
}
