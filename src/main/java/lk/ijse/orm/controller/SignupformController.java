package lk.ijse.orm.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.orm.bo.BOFactory;
import lk.ijse.orm.bo.custom.LoginBo;
import lk.ijse.orm.bo.custom.SignupBo;
import lk.ijse.orm.dto.UserDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignupformController implements Initializable {

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXButton btnSignUp;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtVisiblePass;

    @FXML
    private AnchorPane root;

    SignupBo signupBo = (SignupBo) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.SIGNUP);

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/loginform.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.centerOnScreen();
        stage.setResizable(false);
    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) throws IOException {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(txtName.getText());
        userDTO.setPassword(txtPassword.getText());
        boolean isSaved = signupBo.saveUser(userDTO);
        if (isSaved){
            new Alert(Alert.AlertType.CONFIRMATION,"User Saved Successfully").show();
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/loginform.fxml"));
            Scene scene = new Scene(anchorPane);

            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Login Form");
            stage.centerOnScreen();
            stage.setResizable(false);
        }else {
            new Alert(Alert.AlertType.WARNING,"Failed to save the user").show();
        }
    }

    @FXML
    void btnVisibleOnAction(ActionEvent event) {
        if (txtPassword.isVisible()==true){
            txtPassword.setVisible(false);
            txtVisiblePass.setVisible(true);
        }else {
            txtPassword.setVisible(true);
            txtVisiblePass.setVisible(false);
        }
    }

    @FXML
    void txtNameOnActon(ActionEvent event) {
        txtPassword.requestFocus();
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) throws IOException {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(txtName.getText());
        userDTO.setPassword(txtPassword.getText());
        boolean isSaved = signupBo.saveUser(userDTO);
        if (isSaved){
            new Alert(Alert.AlertType.CONFIRMATION,"User Saved Successfully").show();
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/loginform.fxml"));
            Scene scene = new Scene(anchorPane);

            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Login Form");
            stage.centerOnScreen();
            stage.setResizable(false);
        }else {
            new Alert(Alert.AlertType.WARNING,"Failed to save the user").show();
        }
    }

    @FXML
    void txtVisiblePassOnAction(ActionEvent event) throws IOException {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(txtName.getText());
        userDTO.setPassword(txtPassword.getText());
        boolean isSaved = signupBo.saveUser(userDTO);
        if (isSaved){
            new Alert(Alert.AlertType.CONFIRMATION,"User Saved Successfully").show();
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/loginform.fxml"));
            Scene scene = new Scene(anchorPane);

            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Login Form");
            stage.centerOnScreen();
            stage.setResizable(false);
        }else {
            new Alert(Alert.AlertType.WARNING,"Failed to save the user").show();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtName.requestFocus();
        txtPassword.setVisible(true);
        txtVisiblePass.setVisible(false);
        txtVisiblePass.textProperty().bindBidirectional(txtPassword.textProperty());
    }
}
