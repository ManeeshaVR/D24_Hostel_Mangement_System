package lk.ijse.orm.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.ijse.orm.bo.BOFactory;
import lk.ijse.orm.bo.custom.ReservationBo;
import lk.ijse.orm.dto.ReservationDTO;
import lk.ijse.orm.dto.RoomDTO;
import lk.ijse.orm.dto.StudentDTO;
import lk.ijse.orm.tm.ReservationTM;
import lk.ijse.orm.tm.StudentTM;
import lk.ijse.orm.util.RegExPatterns;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ReservationformController implements Initializable {

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
    private JFXButton btnPending;

    @FXML
    private JFXComboBox<String> cmbRoomId;

    @FXML
    private JFXComboBox<String> cmbStatus;

    @FXML
    private JFXComboBox<String> cmbStudentId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colRoomId;

    @FXML
    private TableColumn<?, ?> colRoomType;

    @FXML
    private TableColumn<?, ?> colRservationId;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colStudentName;

    @FXML
    private DatePicker dateReserve;

    @FXML
    private TableView<ReservationTM> tblReservations;

    @FXML
    private JFXTextField txtGender;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtReservationsId;

    @FXML
    private TextField txtSearch;

    @FXML
    private JFXTextField txtType;

    ReservationBo reservationBO = (ReservationBo) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.RESERVATION);

    ObservableList<ReservationTM> reservations = FXCollections.observableArrayList();

    ReservationDTO reservationDTO = new ReservationDTO();

    private void clearTextFields() {
        txtReservationsId.setText("");
        cmbStatus.setValue(null);
        dateReserve.setValue(null);
        cmbStudentId.setValue(null);
        txtName.setText("");
        txtGender.setText("");
        cmbRoomId.setValue(null);
        txtType.setText("");
        txtQty.setText("");

        txtReservationsId.setEditable(true);
        cmbStudentId.setEditable(true);
        cmbRoomId.setEditable(true);
    }

    @FXML
    void btnPendingOnAction(ActionEvent event) {
        List<ReservationDTO> reservationDTOS = reservationBO.getPendingPayments();
        reservations.clear();
        for (ReservationDTO reservationDTO : reservationDTOS) {
            reservations.add(new ReservationTM(
                    reservationDTO.getReservationId(),reservationDTO.getDate(), reservationDTO.getStudent().getStudentId(), reservationDTO.getStudent().getName(), reservationDTO.getRoom().getRoomTypeId(), reservationDTO.getRoom().getType(), reservationDTO.getStatus()
            ));
        }
        tblReservations.setItems(reservations);
    }


    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearTextFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No",ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove "+txtReservationsId.getText()+" reservation ?", yes, no).showAndWait();

        if (result.orElse(no) == yes){
            RoomDTO roomDTO = reservationBO.getRoom(cmbRoomId.getValue());
            roomDTO.setQty(roomDTO.getQty()+1);
            boolean isDeleted = reservationBO.deleteReservation(txtReservationsId.getText(), roomDTO);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Reservation deleted successfully", ButtonType.OK).show();
                populateReservationTable();
                clearTextFields();
            }else{
                new Alert(Alert.AlertType.WARNING, "Failed to delete the reservation").show();
            }

        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (!(txtReservationsId.getText().isEmpty() || cmbStudentId.getValue() == null || cmbRoomId.getValue() == null || dateReserve.getValue() == null || cmbStatus.getValue() == null)){
            if (RegExPatterns.getReserveIdPattern().matcher(txtReservationsId.getText()).matches()) {
                txtReservationsId.setUnFocusColor(Paint.valueOf("#0077b6"));
                boolean isExists = reservationBO.existReservation(txtReservationsId.getText());
                if (!isExists) {
                    ReservationDTO reservationDTO = new ReservationDTO();
                    reservationDTO.setReservationId(txtReservationsId.getText());
                    reservationDTO.setDate(dateReserve.getValue());
                    reservationDTO.setStatus(cmbStatus.getValue());

                    StudentDTO studentDTO = reservationBO.getStudent(cmbStudentId.getValue());
                    reservationDTO.setStudent(studentDTO);

                    RoomDTO roomDTO = reservationBO.getRoom(cmbRoomId.getValue());
                    roomDTO.setQty(roomDTO.getQty()-1);
                    reservationDTO.setRoom(roomDTO);

                    boolean isSaved = reservationBO.saveReservation(reservationDTO, roomDTO);
                    if (isSaved) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Reservation added successfully", ButtonType.OK).show();
                        clearTextFields();
                        populateReservationTable();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Failed to add the reservation").show();
                    }
                }else {
                    new Alert(Alert.AlertType.WARNING,"Reservation Id already exist").show();
                }
            }else{
                txtReservationsId.setUnFocusColor(Paint.valueOf("#ff006e"));
                new Alert(Alert.AlertType.WARNING,"Invalid Reservation Id").show();
            }
        }else{
            new Alert(Alert.AlertType.WARNING, "Please fill all details").show();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        if (!txtSearch.getText().isEmpty()){
                reservationDTO = reservationBO.getReservation(txtSearch.getText());
                reservations.clear();
                reservations.add(new ReservationTM(
                            reservationDTO.getReservationId(),reservationDTO.getDate(), reservationDTO.getStudent().getStudentId(), reservationDTO.getStudent().getName(), reservationDTO.getRoom().getRoomTypeId(), reservationDTO.getRoom().getType(), reservationDTO.getStatus()
                ));
                tblReservations.setItems(reservations);
        }else {
            populateReservationTable();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        boolean isExists = reservationBO.existReservation(txtReservationsId.getText());
        if (isExists) {
            ReservationDTO reservationDTO = new ReservationDTO();
            reservationDTO.setReservationId(txtReservationsId.getText());
            reservationDTO.setDate(dateReserve.getValue());
            reservationDTO.setStatus(cmbStatus.getValue());

            StudentDTO studentDTO = reservationBO.getStudent(cmbStudentId.getValue());
            reservationDTO.setStudent(studentDTO);

            RoomDTO roomDTO = reservationBO.getRoom(cmbRoomId.getValue());
            reservationDTO.setRoom(roomDTO);

            boolean isSaved = reservationBO.saveReservation(reservationDTO, roomDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Reservation updated successfully", ButtonType.OK).show();
                clearTextFields();
                populateReservationTable();
            } else {
                new Alert(Alert.AlertType.WARNING, "Failed to update the reservation").show();
            }
        }else {
            new Alert(Alert.AlertType.WARNING,"Reservation Id not exist").show();
        }
    }

    @FXML
    void cmbRoomIdOnAction(ActionEvent event) {
        if( cmbStudentId.getValue() == null) {
            return;
        }
        RoomDTO roomDTO = reservationBO.getRoom(cmbRoomId.getValue());
        txtType.setText(roomDTO.getType());
        txtQty.setText(String.valueOf(roomDTO.getQty()));
    }

    @FXML
    void cmbStatusOnAction(ActionEvent event) {
        if( cmbStatus.getValue() == null) {
            return;
        }
        dateReserve.requestFocus();
    }

    @FXML
    void cmbStudentIdOnAction(ActionEvent event) {
        if( cmbStudentId.getValue() == null || cmbStudentId.getValue().equals(" ") || cmbStudentId.getValue().equals("")) {
            return;
        }
        System.out.println(cmbStudentId.getValue());
        StudentDTO studentDTO = reservationBO.getStudent(cmbStudentId.getValue());
        txtName.setText(studentDTO.getName());
        txtGender.setText(studentDTO.getGender());
        cmbRoomId.requestFocus();

    }

    @FXML
    void dateReserveOnAction(ActionEvent event) {
        cmbStudentId.requestFocus();
    }

    @FXML
    void txtGenderOnAction(ActionEvent event) {

    }

    @FXML
    void txtNameOnAction(ActionEvent event) {

    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {

    }

    @FXML
    void txtReservationsIdOnAction(ActionEvent event) {
        boolean isExist = reservationBO.existReservation(txtReservationsId.getText());
        if (isExist){
            ReservationDTO reservation = reservationBO.getReservation(txtReservationsId.getText());
            cmbStatus.setValue(reservation.getStatus());
            dateReserve.setValue(reservation.getDate());
            cmbStudentId.setValue(reservation.getStudent().getStudentId());
            txtName.setText(reservation.getStudent().getName());
            txtGender.setText(reservation.getStudent().getGender());
            cmbRoomId.setValue(reservation.getRoom().getRoomTypeId());
            txtType.setText(reservation.getRoom().getType());
            txtQty.setText(reservation.getRoom().getType());
        }else {
            new Alert(Alert.AlertType.WARNING, "No Reservation Found").show();
        }
    }

    @FXML
    void txtTypeOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadStudentIds();
        loadRoomIds();
        loadStatus();
        setCellValue();
        populateReservationTable();
    }

    private void loadStudentIds() {
        List<StudentDTO> studentDTOS = reservationBO.getAllStudents();
        ObservableList<String> studentIds = FXCollections.observableArrayList();
        for (StudentDTO studentDTO : studentDTOS) {
            studentIds.add(studentDTO.getStudentId());
        }
        cmbStudentId.setItems(studentIds);
    }

    private void loadRoomIds() {
        List<RoomDTO> roomDTOS = reservationBO.getAllRooms();
        ObservableList<String> roomIds = FXCollections.observableArrayList();
        for (RoomDTO roomDTO : roomDTOS) {
            roomIds.add(roomDTO.getRoomTypeId());
        }
        cmbRoomId.setItems(roomIds);
    }

    private void setCellValue() {
        colRservationId.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void populateReservationTable() {
        List<ReservationDTO> reservationDTOS = reservationBO.getAllReservations();
        reservations.clear();
        for (ReservationDTO reservationDTO : reservationDTOS) {
            reservations.add(new ReservationTM(
                    reservationDTO.getReservationId(),reservationDTO.getDate(), reservationDTO.getStudent().getStudentId(), reservationDTO.getStudent().getName(), reservationDTO.getRoom().getRoomTypeId(), reservationDTO.getRoom().getType(), reservationDTO.getStatus()
            ));
        }
        tblReservations.setItems(reservations);
    }

    private void loadStatus() {
        ObservableList<String> status = FXCollections.observableArrayList();
        status.add("payment done");
        status.add("pending payment");
        cmbStatus.setItems(status);
    }
}
