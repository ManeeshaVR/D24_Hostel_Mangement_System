package lk.ijse.orm.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.orm.dto.UserDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardformController implements Initializable {

    @FXML
    private JFXButton btnAccount;

    @FXML
    private JFXButton btnDash;

    @FXML
    private JFXButton btnReserv;

    @FXML
    private JFXButton btnRoom;

    @FXML
    private JFXButton btnStudent;

    @FXML
    private JFXButton btnlogout;

    @FXML
    private AnchorPane pane;

    @FXML
    private AnchorPane root;



    @FXML
    void accountBtnOnAction(ActionEvent event) throws IOException {
        backgroundRemove();
        pane.getChildren().clear();
        pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/studentform.fxml")));
        btnDash.setStyle("-fx-background-color:  #00b4d8;");
    }

    @FXML
    void btnInterestOnAction(ActionEvent event) {

    }

    @FXML
    void btnlogoutOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/loginform.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.centerOnScreen();
    }

    @FXML
    void dashboardBtnOnAction(ActionEvent event) throws IOException {
        backgroundRemove();
        pane.getChildren().clear();
        pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/homeform.fxml")));
        btnDash.setStyle("-fx-background-color:  #00b4d8;");
    }

    @FXML
    void reserveBtnOnAction(ActionEvent event) throws IOException {
        backgroundRemove();
        pane.getChildren().clear();
        pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/reservationform.fxml")));
        btnReserv.setStyle("-fx-background-color:  #00b4d8;");
    }

    @FXML
    void roomBtnOnAction(ActionEvent event) throws IOException {
        backgroundRemove();
        pane.getChildren().clear();
        pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/roomform.fxml")));
        btnRoom.setStyle("-fx-background-color:  #00b4d8;");
    }

    @FXML
    void studentBtnOnAction(ActionEvent event) throws IOException {
        backgroundRemove();
        pane.getChildren().clear();
        pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/studentform.fxml")));
        btnStudent.setStyle("-fx-background-color:  #00b4d8;");
    }

    private void backgroundRemove() {
        btnDash.setStyle(null);
        btnStudent.setStyle(null);
        btnRoom.setStyle(null);
        btnReserv.setStyle(null);
        btnAccount.setStyle(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pane.getChildren().clear();
        try {
            pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/homeform.fxml")));
            btnDash.setStyle("-fx-background-color:  #00b4d8;");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
