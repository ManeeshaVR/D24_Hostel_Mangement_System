package lk.ijse.orm.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.orm.bo.BOFactory;
import lk.ijse.orm.bo.custom.LoginBo;
import lk.ijse.orm.dto.UserDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginformController implements Initializable {

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtVisiblePass;

    @FXML
    private JFXButton btnSignUp;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private AnchorPane root;

    LoginBo loginBO = (LoginBo) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.LOGIN);

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
        if (!txtName.getText().isEmpty()){
            if (!txtPassword.getText().isEmpty()){

                String username = txtName.getText();
                String password = txtPassword.getText();

                boolean isAuthenticated = loginBO.authenticateUser(username,password);
                if (isAuthenticated){
                    UserDTO userDTO = loginBO.getUser(username,password);

                    AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboardform.fxml"));
                    Scene scene = new Scene(anchorPane);
                    Stage stage = (Stage) root.getScene().getWindow();
                    stage.setScene(scene);
                    stage.setTitle("Dashboard Form");
                    stage.centerOnScreen();
                    stage.setResizable(true);
                    stage.setMaximized(true);

                }else{
                    new Alert(Alert.AlertType.WARNING,"incorrect username or password.", ButtonType.OK).show();
                }
            }else{
                new Alert(Alert.AlertType.WARNING,"enter valid password.", ButtonType.OK).show();
            }
        }else{
            new Alert(Alert.AlertType.WARNING,"enter valid username.", ButtonType.OK).show();
        }
    }

    @FXML
    void txtVisiblePassOnAction(ActionEvent event) throws IOException {
        if (!txtName.getText().isEmpty()){
            if (!txtPassword.getText().isEmpty()){

                String username = txtName.getText();
                String password = txtPassword.getText();

                boolean isAuthenticated = loginBO.authenticateUser(username,password);
                if (isAuthenticated){
                    UserDTO userDTO = loginBO.getUser(username,password);

                    AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboardform.fxml"));
                    Scene scene = new Scene(anchorPane);
                    Stage stage = (Stage) root.getScene().getWindow();
                    stage.setScene(scene);
                    stage.setTitle("Dashboard Form");
                    stage.centerOnScreen();
                    stage.setResizable(true);
                    stage.setMaximized(true);

                }else{
                    new Alert(Alert.AlertType.WARNING,"incorrect username or password.", ButtonType.OK).show();
                }
            }else{
                new Alert(Alert.AlertType.WARNING,"enter valid password.", ButtonType.OK).show();
            }
        }else{
            new Alert(Alert.AlertType.WARNING,"enter valid username.", ButtonType.OK).show();
        }
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        if (!txtName.getText().isEmpty()){
            if (!txtPassword.getText().isEmpty()){

                String username = txtName.getText();
                String password = txtPassword.getText();

                boolean isAuthenticated = loginBO.authenticateUser(username,password);
                if (isAuthenticated){
                    UserDTO userDTO = loginBO.getUser(username,password);

                    AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboardform.fxml"));
                    Scene scene = new Scene(anchorPane);
                    Stage stage = (Stage) root.getScene().getWindow();
                    stage.setScene(scene);
                    stage.setTitle("Dashboard Form");
                    stage.centerOnScreen();
                    stage.setResizable(true);
                    stage.setMaximized(true);

                }else{
                    new Alert(Alert.AlertType.WARNING,"incorrect username or password.", ButtonType.OK).show();
                }
            }else{
                new Alert(Alert.AlertType.WARNING,"enter valid password.", ButtonType.OK).show();
            }
        }else{
            new Alert(Alert.AlertType.WARNING,"enter valid username.", ButtonType.OK).show();
        }
    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/signupform.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Signup Form");
        stage.centerOnScreen();
        stage.setResizable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtName.requestFocus();
        txtPassword.setVisible(true);
        txtVisiblePass.setVisible(false);
        txtVisiblePass.textProperty().bindBidirectional(txtPassword.textProperty());
    }
}
