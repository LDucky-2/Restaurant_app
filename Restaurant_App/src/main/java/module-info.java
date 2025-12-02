module com.app.restaurant_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.app.restaurant_app to javafx.fxml;
    exports com.app.restaurant_app;
    opens com.app.restaurant_app.Yeahia.controller_classes to javafx.fxml;
    exports com.app.restaurant_app.Yeahia.controller_classes;
    opens com.app.restaurant_app.Yeahia.model_classes to javafx.fxml;
    exports com.app.restaurant_app.Yeahia.model_classes;
    opens com.app.restaurant_app.Yeahia.controller_classes.Dummy_classes;
    exports com.app.restaurant_app.Yeahia.controller_classes.Dummy_classes to javafx.fxml;
    opens com.app.restaurant_app.Non_user_classes;
    exports com.app.restaurant_app.Non_user_classes to javafx.fxml;
    opens com.app.restaurant_app.Sakib.model_classes;
    exports com.app.restaurant_app.Sakib.model_classes to javafx.fxml;
    opens com.app.restaurant_app.Sakib.controller_classes;
    exports com.app.restaurant_app.Sakib.controller_classes to javafx.fxml;
    opens com.app.restaurant_app.Soyaiminul.model_classes;
    exports com.app.restaurant_app.Soyaiminul.model_classes to javafx.fxml;
    opens com.app.restaurant_app.Soyaiminul.controller_classes;
    exports com.app.restaurant_app.Soyaiminul.controller_classes to javafx.fxml;
}