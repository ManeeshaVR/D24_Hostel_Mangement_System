package lk.ijse.orm.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.orm.bo.BOFactory;
import lk.ijse.orm.bo.custom.HomeBo;
import lk.ijse.orm.dto.UserDTO;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class HomeformController implements Initializable {

    @FXML
    private AnchorPane anch;

    @FXML
    private Label lblAdmin;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblMoney;

    @FXML
    private Label lblRents;

    @FXML
    private Label lblRooms;

    @FXML
    private Label lblStudents;

    @FXML
    private Label lblTime;

    @FXML
    private PieChart pieChart;

    @FXML
    private BarChart<?, ?> roomChart;

    HomeBo homeBo = (HomeBo) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.HOME);

    UserDTO userDTO = new UserDTO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadStudentCount();
        loadRoomsCount();
        loadMoneyCount();
        loadReservationCount();
        loadDateAndTime();
        initializePieChart();
        initializeBarChart();
    }

    private void loadMoneyCount() {
    }

    private void initializeBarChart() {
    }

    private void initializePieChart() {

    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(format.format(date));

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e ->{
            DateTimeFormatter format2 = DateTimeFormatter.ofPattern("HH:mm:ss");
            lblTime.setText(LocalTime.now().format(format2));
        }), new KeyFrame(Duration.seconds(1))
        );

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void loadRoomsCount() {
        
    }

    private void loadReservationCount() {
    }

    private void loadStudentCount() {
    }

    public void addUser(UserDTO user){
        lblAdmin.setText(user.getUserName());
    }
}
