package com.app.restaurant_app;

import com.app.restaurant_app.Yeahia.model_classes.Restaurant_manager;
import com.app.restaurant_app.Utility.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Restaurant_Application extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Restaurant_Application.class.getResource("Log_in_scene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Restaurant App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException{
//        Employee employee = new Restaurant_manager(100000,"Restaurant Manager");
//        Utility.write_object("data_files/employee_data.bin",employee);
        launch();


        // to sign in just use id: 100000, and password: 1000000 ( this logs you in as the restaurant manager)
    }
}