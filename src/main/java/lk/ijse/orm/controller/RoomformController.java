package lk.ijse.orm.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.ijse.orm.bo.BOFactory;
import lk.ijse.orm.bo.custom.RoomBo;
import lk.ijse.orm.dto.RoomDTO;
import lk.ijse.orm.dto.StudentDTO;
import lk.ijse.orm.tm.RoomTM;
import lk.ijse.orm.tm.StudentTM;
import lk.ijse.orm.util.RegExPatterns;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class RoomformController implements Initializable {

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colMoney;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colRoomId;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableView<RoomTM> tblRooms;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtMoney;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private TextField txtSearch;

    @FXML
    private JFXTextField txtType;

    RoomBo roomBO = (RoomBo) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ROOM);

    ObservableList<RoomTM> rooms = FXCollections.observableArrayList();

    RoomDTO roomDTO = new RoomDTO();

    private void clearTextFields() {
        txtId.setText("");
        txtType.setText("");
        txtMoney.setText("");
        txtQty.setText("");
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearTextFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No",ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove "+txtId.getText()+" room type ?", yes, no).showAndWait();

        if (result.orElse(no) == yes){
            boolean isDeleted = roomBO.deleteRoom(txtId.getText());
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Student deleted successfully", ButtonType.OK).show();
                populateRoomTable();
                clearTextFields();
            }else{
                new Alert(Alert.AlertType.WARNING, "Failed to delete the item").show();
            }

        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (!(txtId.getText().isEmpty() || txtType.getText().isEmpty() || txtMoney.getText().isEmpty() || txtQty.getText().isEmpty())){
            if (RegExPatterns.getDoublePattern().matcher(txtMoney.getText()).matches()) {
                txtMoney.setUnFocusColor(Paint.valueOf("#0077b6"));
                if (RegExPatterns.getDoublePattern().matcher(txtMoney.getText()).matches()) {
                    txtMoney.setUnFocusColor(Paint.valueOf("#0077b6"));
                    if (RegExPatterns.getIntPattern().matcher(txtQty.getText()).matches()) {
                        txtQty.setUnFocusColor(Paint.valueOf("#0077b6"));

                        RoomDTO roomDTO = new RoomDTO();
                        roomDTO.setRoomTypeId(txtId.getText());
                        roomDTO.setType(txtType.getText());
                        roomDTO.setKeyMoney(Double.valueOf(txtMoney.getText()));
                        roomDTO.setQty(Integer.valueOf(txtQty.getText()));

                        boolean isExists = roomBO.existRoom(txtId.getText());
                        if (!isExists) {

                            boolean isSaved = roomBO.saveRoom(roomDTO);
                            if (isSaved) {
                                new Alert(Alert.AlertType.CONFIRMATION, "Room saved successfully", ButtonType.OK).show();
                                clearTextFields();
                                populateRoomTable();

                            } else {
                                new Alert(Alert.AlertType.WARNING, "Failed to save the room").show();
                            }

                        } else {
                            new Alert(Alert.AlertType.WARNING, txtId.getText() + " already exists").show();
                        }
                    } else{
                        txtQty.setUnFocusColor(Paint.valueOf("#ff006e"));
                        new Alert(Alert.AlertType.WARNING,"Invalid Quantity Number").show();
                    }
                } else{
                    txtMoney.setUnFocusColor(Paint.valueOf("#ff006e"));
                    new Alert(Alert.AlertType.WARNING,"Invalid Key Money").show();
                }
            }else{
                txtId.setUnFocusColor(Paint.valueOf("#ff006e"));
                new Alert(Alert.AlertType.WARNING,"Invalid Room Id").show();
            }
        }else{
            new Alert(Alert.AlertType.WARNING, "Please fill all details").show();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        if (!txtSearch.getText().isEmpty()){
            if (RegExPatterns.getRoomIdPattern().matcher(txtSearch.getText()).matches()) {
                boolean isExists = roomBO.existRoom(txtSearch.getText());
                if (isExists) {
                    roomDTO = roomBO.getRoom(txtSearch.getText());
                    rooms.clear();
                    rooms.add(new RoomTM(roomDTO.getRoomTypeId(), roomDTO.getType(), roomDTO.getKeyMoney(), roomDTO.getQty()));
                    tblRooms.setItems(rooms);
                } else {
                    new Alert(Alert.AlertType.WARNING, "No Student Found").show();
                }
            }else {
                new Alert(Alert.AlertType.WARNING,"Enter Valid Room Number").show();
            }
        }else {
            populateRoomTable();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (!(txtId.getText().isEmpty() || txtType.getText().isEmpty() || txtMoney.getText().isEmpty() || txtQty.getText().isEmpty())){
            if (RegExPatterns.getRoomIdPattern().matcher(txtId.getText()).matches()) {
                txtId.setUnFocusColor(Paint.valueOf("#0077b6"));
                if (RegExPatterns.getDoublePattern().matcher(txtMoney.getText()).matches()) {
                    txtMoney.setUnFocusColor(Paint.valueOf("#0077b6"));
                    if (RegExPatterns.getIntPattern().matcher(txtQty.getText()).matches()) {
                        txtQty.setUnFocusColor(Paint.valueOf("#0077b6"));

                        RoomDTO roomDTO = new RoomDTO();
                        roomDTO.setRoomTypeId(txtId.getText());
                        roomDTO.setType(txtType.getText());
                        roomDTO.setKeyMoney(Double.valueOf(txtMoney.getText()));
                        roomDTO.setQty(Integer.valueOf(txtQty.getText()));

                        boolean isExists = roomBO.existRoom(txtId.getText());
                        if (isExists) {

                            boolean isUpdated = roomBO.updateRoom(roomDTO);
                            if (isUpdated) {
                                new Alert(Alert.AlertType.CONFIRMATION, "Room updated successfully", ButtonType.OK).show();
                                clearTextFields();
                                populateRoomTable();

                            } else {
                                new Alert(Alert.AlertType.WARNING, "Failed to update the room").show();
                            }

                        } else {
                            new Alert(Alert.AlertType.WARNING, txtId.getText() + " already exists").show();
                        }
                    } else{
                        txtQty.setUnFocusColor(Paint.valueOf("#ff006e"));
                        new Alert(Alert.AlertType.WARNING,"Invalid Quantity Number").show();
                    }
                } else{
                    txtMoney.setUnFocusColor(Paint.valueOf("#ff006e"));
                    new Alert(Alert.AlertType.WARNING,"Invalid Key Money").show();
                }
            }else{
                txtId.setUnFocusColor(Paint.valueOf("#ff006e"));
                new Alert(Alert.AlertType.WARNING,"Invalid Room Id").show();
            }
        }else{
            new Alert(Alert.AlertType.WARNING, "Please fill all details").show();
        }
    }

    @FXML
    void txtIdOnAction(ActionEvent event) {
        boolean isExists = roomBO.existRoom(txtId.getText());
        if (isExists) {
            roomDTO = roomBO.getRoom(txtId.getText());
            txtType.setText(roomDTO.getType());
            txtMoney.setText(String.valueOf(roomDTO.getKeyMoney()));
            txtQty.setText(String.valueOf(roomDTO.getQty()));
        }else {
            new Alert(Alert.AlertType.WARNING, "No Room Found").show();
        }
    }

    @FXML
    void txtMoneyOnAction(ActionEvent event) {
        txtQty.requestFocus();
    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {

    }

    @FXML
    void txtTypeOnAction(ActionEvent event) {
        txtMoney.requestFocus();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValue();
        populateRoomTable();
    }

    private void setCellValue() {
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    private void populateRoomTable() {
        List<RoomDTO> roomDTOS = roomBO.getAllRooms();
        rooms.clear();
        for (RoomDTO roomDTO : roomDTOS) {
            rooms.add(new RoomTM(roomDTO.getRoomTypeId(), roomDTO.getType(),roomDTO.getKeyMoney(), roomDTO.getQty()
            ));
            tblRooms.setItems(rooms);
        }
    }
}
