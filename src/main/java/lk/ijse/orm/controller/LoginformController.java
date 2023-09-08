package lk.ijse.orm.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
    private JFXTextField txtPassword;

    LoginBo loginBO = (LoginBo) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.LOGIN);

    @FXML
    void txtNameOnActon(ActionEvent event) {
        txtPassword.requestFocus();
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {

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

                    Stage stage = (Stage) txtName.getScene().getWindow();
                    stage.setTitle("D24 Hostel Management System");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboardform.fxml"));
                    Parent scene = loader.load();
                    stage.setScene(new Scene(scene));
                    HomeformController home = new HomeformController();
                    home.addUser(userDTO);
                    stage.show();

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtName.requestFocus();
    }
}
