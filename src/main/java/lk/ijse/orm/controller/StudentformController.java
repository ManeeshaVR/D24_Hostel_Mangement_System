package lk.ijse.orm.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import lk.ijse.orm.bo.custom.StudentBo;
import lk.ijse.orm.dto.StudentDTO;
import lk.ijse.orm.tm.StudentTM;
import lk.ijse.orm.util.RegExPatterns;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class StudentformController implements Initializable {

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
    private JFXComboBox<String> cmbGender;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colConNo;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colStuId;

    @FXML
    private DatePicker dateBirth;

    @FXML
    private TableView<StudentTM> tblStudent;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private TextField txtSearch;

    StudentBo studentBO = (StudentBo) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.STUDENT);

    ObservableList<StudentTM> students = FXCollections.observableArrayList();

    StudentDTO studentDTO = new StudentDTO();

    private void clearTextFields() {
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        dateBirth.setValue(null);
        cmbGender.setValue(null);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearTextFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No",ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove "+txtName.getText()+" student ?", yes, no).showAndWait();

        if (result.orElse(no) == yes){
            boolean isDeleted = studentBO.deleteStudent(txtId.getText());
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Student deleted successfully", ButtonType.OK).show();
                populateStudentTable();
                clearTextFields();
            }else{
                new Alert(Alert.AlertType.WARNING, "Failed to delete the student").show();
            }

        }
    }


    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (!(txtId.getText().isEmpty() || txtName.getText().isEmpty() || txtContact.getText().isEmpty() || txtAddress.getText().isEmpty() || dateBirth.getValue()==null || cmbGender.getValue()==null)){
            if (RegExPatterns.getStudentIdPattern().matcher(txtId.getText()).matches()) {
                txtId.setUnFocusColor(Paint.valueOf("#0077b6"));
                if (RegExPatterns.getNamePattern().matcher(txtName.getText()).matches()) {
                    txtName.setUnFocusColor(Paint.valueOf("#0077b6"));
                    if (RegExPatterns.getAddressPattern().matcher(txtAddress.getText()).matches()) {
                        txtAddress.setUnFocusColor(Paint.valueOf("#0077b6"));
                        if (RegExPatterns.getContactPattern().matcher(txtContact.getText()).matches()) {
                            txtContact.setUnFocusColor(Paint.valueOf("#0077b6"));

                            boolean isExists = studentBO.existStudent(txtId.getText());
                            if (!isExists) {

                                StudentDTO studentDTO = new StudentDTO();
                                studentDTO.setStudentId(txtId.getText());
                                studentDTO.setName(txtName.getText());
                                studentDTO.setAddress(txtAddress.getText());
                                studentDTO.setContactNo(txtContact.getText());
                                studentDTO.setDob(dateBirth.getValue());
                                studentDTO.setGender(cmbGender.getValue());

                                boolean isSaved = studentBO.saveStudent(studentDTO);

                                if (isSaved) {
                                    new Alert(Alert.AlertType.CONFIRMATION, "Student saved successfully", ButtonType.OK).show();
                                    clearTextFields();
                                    populateStudentTable();
                                } else {
                                    new Alert(Alert.AlertType.WARNING, "Failed to save the student").show();
                                }

                            } else {
                                new Alert(Alert.AlertType.WARNING, txtId.getText() + " already exists").show();
                            }
                        }else{
                            txtContact.setUnFocusColor(Paint.valueOf("#ff006e"));
                            new Alert(Alert.AlertType.WARNING,"Invalid Student Contact No").show();
                        }
                    }else{
                        txtAddress.setUnFocusColor(Paint.valueOf("#ff006e"));
                        new Alert(Alert.AlertType.WARNING,"Invalid Student Address").show();
                    }
                }else{
                    txtName.setUnFocusColor(Paint.valueOf("#ff006e"));
                    new Alert(Alert.AlertType.WARNING,"Invalid Student Name").show();
                }
            }else{
                txtId.setUnFocusColor(Paint.valueOf("#ff006e"));
                new Alert(Alert.AlertType.WARNING,"Invalid Student Id").show();
            }
        }else {
            new Alert(Alert.AlertType.WARNING, "Please fill all the details").show();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        if (!txtSearch.getText().isEmpty()){
            boolean isExists = studentBO.existStudent(txtSearch.getText());
            if (isExists){
                studentDTO = studentBO.getStudent(txtSearch.getText());
                students.clear();
                students.add(new StudentTM(studentDTO.getStudentId(), studentDTO.getName(), studentDTO.getAddress(), studentDTO.getContactNo(), studentDTO.getGender(), studentDTO.getDob()));
                tblStudent.setItems(students);
            }else {
                new Alert(Alert.AlertType.WARNING, "No Student Found").show();
            }
        }else {
            populateStudentTable();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (!(txtId.getText().isEmpty() || txtName.getText().isEmpty() || txtContact.getText().isEmpty() || txtAddress.getText().isEmpty() || dateBirth.getValue()==null || cmbGender.getValue()==null)){
            if (RegExPatterns.getStudentIdPattern().matcher(txtId.getText()).matches()) {
                txtId.setUnFocusColor(Paint.valueOf("#0077b6"));
                if (RegExPatterns.getNamePattern().matcher(txtName.getText()).matches()) {
                    txtName.setUnFocusColor(Paint.valueOf("#0077b6"));
                    if (RegExPatterns.getAddressPattern().matcher(txtAddress.getText()).matches()) {
                        txtAddress.setUnFocusColor(Paint.valueOf("#0077b6"));
                        if (RegExPatterns.getContactPattern().matcher(txtContact.getText()).matches()) {
                            txtContact.setUnFocusColor(Paint.valueOf("#0077b6"));

                            boolean isExists = studentBO.existStudent(txtId.getText());
                            if (isExists) {

                                StudentDTO studentDTO = new StudentDTO();
                                studentDTO.setStudentId(txtId.getText());
                                studentDTO.setName(txtName.getText());
                                studentDTO.setAddress(txtAddress.getText());
                                studentDTO.setContactNo(txtContact.getText());
                                studentDTO.setDob(dateBirth.getValue());
                                studentDTO.setGender(cmbGender.getValue());

                                boolean isUpdated = studentBO.updateStudent(studentDTO);

                                if (isUpdated) {
                                    new Alert(Alert.AlertType.CONFIRMATION, "Student updated successfully", ButtonType.OK).show();
                                    clearTextFields();
                                    populateStudentTable();
                                } else {
                                    new Alert(Alert.AlertType.WARNING, "Failed to update the student").show();
                                }

                            } else {
                                new Alert(Alert.AlertType.WARNING, txtId.getText() + " not exists").show();
                            }
                        }else{
                            txtContact.setUnFocusColor(Paint.valueOf("#ff006e"));
                            new Alert(Alert.AlertType.WARNING,"Invalid Student Contact No").show();
                        }
                    }else{
                        txtAddress.setUnFocusColor(Paint.valueOf("#ff006e"));
                        new Alert(Alert.AlertType.WARNING,"Invalid Student Address").show();
                    }
                }else{
                    txtName.setUnFocusColor(Paint.valueOf("#ff006e"));
                    new Alert(Alert.AlertType.WARNING,"Invalid Student Name").show();
                }
            }else{
                txtId.setUnFocusColor(Paint.valueOf("#ff006e"));
                new Alert(Alert.AlertType.WARNING,"Invalid Student Id").show();
            }
        }else {
            new Alert(Alert.AlertType.WARNING, "Please fill all the details").show();
        }
    }

    @FXML
    void cmbGenderOnAction(ActionEvent event) {
        if( cmbGender.getValue() == null) {
            return;
        }
        dateBirth.requestFocus();
    }

    @FXML
    void txtAddressOnAction(ActionEvent event) {
        txtContact.requestFocus();
    }

    @FXML
    void txtIdOnAction(ActionEvent event) {
        boolean isExists = studentBO.existStudent(txtId.getText());
        if (isExists) {
            studentDTO = studentBO.getStudent(txtId.getText());
            txtName.setText(studentDTO.getName());
            txtAddress.setText(studentDTO.getAddress());
            txtContact.setText(studentDTO.getContactNo());
            cmbGender.setValue(studentDTO.getGender());
            dateBirth.setValue(studentDTO.getDob());
        }else {
            new Alert(Alert.AlertType.WARNING, "No Student Found").show();
        }
    }

    @FXML
    void txtNameOnAction(ActionEvent event) {
        txtAddress.requestFocus();
    }

    @FXML
    void txtContactOnAction(ActionEvent event) {
        cmbGender.requestFocus();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValue();
        populateStudentTable();
        loadGenders();
    }

    private void setCellValue() {
        colStuId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colConNo.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
    }

    private void populateStudentTable() {
        List<StudentDTO> studentDTOS = studentBO.getAllStudents();
        students.clear();
        for (StudentDTO studentDTO : studentDTOS) {
            students.add(new StudentTM(studentDTO.getStudentId(),
                    studentDTO.getName(),studentDTO.getAddress(),
                    studentDTO.getContactNo(), studentDTO.getGender(),
                    studentDTO.getDob()
            ));
        }
        tblStudent.setItems(students);
    }

    private void loadGenders() {
        ObservableList<String> genders = FXCollections.observableArrayList();
        genders.add("Male");
        genders.add("Female");
        cmbGender.setItems(genders);
    }
}
