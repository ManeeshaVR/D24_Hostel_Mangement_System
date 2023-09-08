package lk.ijse.orm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Appinitializer extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/loginform.fxml"))));
        primaryStage.getIcons().add(new Image("images/Hostel-Logo-removebg.png"));
        primaryStage.setTitle("Login");
        primaryStage.show();
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
    }
}
