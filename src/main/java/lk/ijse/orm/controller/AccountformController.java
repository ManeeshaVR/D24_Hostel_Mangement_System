package lk.ijse.orm.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.orm.bo.BOFactory;
import lk.ijse.orm.bo.custom.AccountBo;
import lk.ijse.orm.bo.custom.LoginBo;
import lk.ijse.orm.dto.UserDTO;

import java.io.IOException;

public class AccountformController {

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private Label lblAdmin;

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtNewPass;

    @FXML
    private JFXTextField txtOldPass;

    @FXML
    private JFXTextField txtUserName;

    AccountBo accountBo = (AccountBo) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ACCOUNT);

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboardform.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
        stage.setResizable(true);
        stage.setMaximized(true);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (!(txtUserName.getText().isEmpty() || txtOldPass.getText().isEmpty() || txtNewPass.getText().isEmpty())){
            boolean isExist = accountBo.authenticateUser(txtUserName.getText(), txtOldPass.getText());
            if (isExist){
                UserDTO userDTO = new UserDTO();
                userDTO.setUsername(txtUserName.getText());
                userDTO.setPassword(txtNewPass.getText());
                boolean isUpdated = accountBo.updateUser(userDTO);
                if (isUpdated){
                    new Alert(Alert.AlertType.CONFIRMATION,"User Updated Successfully").show();
                    txtUserName.setText("");
                    txtOldPass.setText("");
                    txtNewPass.setText("");
                }else {
                    new Alert(Alert.AlertType.WARNING,"Failed to update the user").show();
                }
            }else {
                new Alert(Alert.AlertType.WARNING,"No user found").show();
            }
        }
    }

    @FXML
    void txtNewPassOnAction(ActionEvent event) {

    }

    @FXML
    void txtOldPassOnAction(ActionEvent event) {
        txtNewPass.requestFocus();
    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {
        txtOldPass.requestFocus();
    }

}
