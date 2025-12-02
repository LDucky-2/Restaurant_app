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

public class Utility {
    public static void scene_changer(ActionEvent action_event, String scene_path) throws IOException {
        // this changes the current scene to another scene in the same window/stage.

        // actionEvent parameter, to get the window or stage (the on_action of any node will have actionEvent as a parameter send that),
        // Scene_path parameter, a string of the fxml path,

        // new fxml_loader made
        FXMLLoader fxml_loader = new FXMLLoader(Utility.class.getResource(scene_path));
        // the current stage taken from the action_event parameter
        Stage stage = (Stage)((Node) action_event.getSource()).getScene().getWindow();
        // new scene made, stage set and shown!
        stage.setScene(new Scene(fxml_loader.load()));
        stage.show();

    }
    public static void new_scene(String Scene_path) throws IOException{
        // this changes the current scene to another scene in a new window/stage.

        // Scene_path parameter, a string of the fxml path,

        // new fxml_loader made
        FXMLLoader fxmlLoader = new FXMLLoader(Utility.class.getResource(Scene_path));
        // new scene made
        Scene scene = new Scene(fxmlLoader.load());
        // stage set and shown!
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    public static <T> T scene_changer_returns_controller(ActionEvent actionEvent, String scenePath) throws IOException {
        // this changes the current scene to another scene in the same window/stage and also returns the controller instance of the fxml.

        // actionEvent parameter, to get the window or stage (the on_action of any node will have actionEvent as a parameter send that),
        // Scene_path parameter, a string of the fxml path,

        // new fxml_loader made
        FXMLLoader fxmlLoader = new FXMLLoader(Utility.class.getResource(scenePath));
        // the current stage taken from the action_event parameter
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        // // new scene made, stage set and shown!
        stage.setScene(new Scene(fxmlLoader.load()));

        T controller_instance = fxmlLoader.getController();
        stage.show();

        return controller_instance;
    }

    public static <T> T new_scene_returns_controller(String scenePath) throws IOException {
        // this creates a new stage loading the given scene and also returns the controller instance of the fxml.

        // Scene_path parameter, a string of the fxml path,

        // new fxml_loader made
        FXMLLoader fxmlLoader = new FXMLLoader(Utility.class.getResource(scenePath));
        // new stage and scene set
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        // controller made and returned after scene shown.
        T controller = fxmlLoader.getController();
        stage.show();

        return controller;
    }

    public static <T> void write_object(String file_path, T object) throws IOException{

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

    public static <T> void write_object_arraylist_replace(String file_path, ArrayList<T> object_arraylist) throws IOException{
        ObjectOutputStream object_output_stream;
        File file = new File(file_path);

        FileOutputStream file_output_stream = new FileOutputStream(file);
        object_output_stream = new ObjectOutputStream(file_output_stream);
        for (Object object: object_arraylist){
            object_output_stream.writeObject(object);
        }
        object_output_stream.close();
    }

    public static <T> ArrayList<T> read_object(String file_path) throws IOException {

        File file = new File(file_path);
        ArrayList<T> object_arraylist = new ArrayList<T>();
        if (file.exists()) {

            FileInputStream file_input_stream = new FileInputStream(file);
            ObjectInputStream object_input_stream = new ObjectInputStream(file_input_stream);
            while (true) {
                try {
                    T object = (T) object_input_stream.readObject();
                    object_arraylist.add(object);
                }
                catch (Exception e) {
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
        }
        catch (NumberFormatException e) {
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
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    public static void show_information_alert(String alert_information){
        Alert alert = new Alert(Alert.AlertType.INFORMATION,alert_information);
        alert.showAndWait();
    }
}

