package com.app.restaurant_app;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;


public class Utility{
    public static void scene_changer(ActionEvent actionEvent, String Scene_path) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Utility.class.getResource(Scene_path));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    public static void new_scene(String Scene_path) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Utility.class.getResource(Scene_path));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    public static <T> T scene_changer_returns_controller(ActionEvent actionEvent, String scenePath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Utility.class.getResource(scenePath));

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load()));
        T controller = fxmlLoader.getController();
        stage.show();

        return controller;
    }
    public static <T> T new_scene_returns_controller(String scenePath) throws IOException {
        FXMLLoader loader = new FXMLLoader(Utility.class.getResource(scenePath));
        Parent root = loader.load();

        T controller = loader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

        return controller;
    }

    public static void write_object(String file_path, Object object) throws IOException{

            ObjectOutputStream object_output_stream;
            File file = new File(file_path);

            if (file.exists()){
                FileOutputStream file_output_stream = new FileOutputStream(file, true);
                object_output_stream = new AppendableObjectOutputStream(file_output_stream);
            }else {
                FileOutputStream file_output_stream = new FileOutputStream(file);
                object_output_stream = new ObjectOutputStream(file_output_stream);
            }

        object_output_stream.writeObject(object);
        object_output_stream.close();
    }

    public static void write_object_arraylist_replace(String file_path, ArrayList<Object> object_arraylist) throws IOException{
        ObjectOutputStream object_output_stream;
        File file = new File(file_path);

        FileOutputStream file_output_stream = new FileOutputStream(file);
        object_output_stream = new ObjectOutputStream(file_output_stream);
        for (Object object: object_arraylist){
            object_output_stream.writeObject(object);
        }
        object_output_stream.close();
    }

    public static ArrayList<Object> read_object(String file_path) throws IOException {
        File file = new File(file_path);
        ArrayList<Object> object_arraylist = new ArrayList<Object>();
        if (file.exists()) {

            FileInputStream file_input_stream = new FileInputStream(file);
            ObjectInputStream object_input_stream = new ObjectInputStream(file_input_stream);
            while (true) {
                try {
                    object_arraylist.add(object_input_stream.readObject());
                } catch (Exception e) {
                    break;
                }

            }
            object_input_stream.close();
        }
        return object_arraylist;

    }
    public static void close_window(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public static boolean is_integer(String string) {
        if (string == null) {
            return false;
        }
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean is_long(String string) {
        if (string == null) {
            return false;
        }
        try {
            Long.parseLong(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void show_information_alert(String alert_information){
        Alert alert = new Alert(Alert.AlertType.INFORMATION,alert_information);
        alert.showAndWait();
    }
}
