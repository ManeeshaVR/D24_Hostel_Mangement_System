package lk.ijse.orm.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.orm.bo.BOFactory;
import lk.ijse.orm.bo.custom.HomeBo;
import lk.ijse.orm.dto.RoomDTO;
import lk.ijse.orm.dto.UserDTO;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class HomeformController implements Initializable {

    @FXML
    private AnchorPane anch;

    @FXML
    private static Label lblAdmin;

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
    private BarChart<String, Number> roomChart;

    HomeBo homeBo = (HomeBo) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.HOME);

    UserDTO userDTO = new UserDTO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadStudentCount();
        loadRoomsCount();
        loadAdminCount();
        loadReservationCount();
        loadDateAndTime();
        initializePieChart();
        initializeBarChart();
    }

    private void loadAdminCount() {
        String adminCount = homeBo.getAdminCount();
        lblMoney.setText(adminCount);
    }

    private void initializeBarChart() {
        XYChart.Series<String, Number>[] series1 = new XYChart.Series[4];

        int nonac = 0;
        int nonacfood = 0;
        int ac = 0;
        int acfood = 0;
        List<RoomDTO> allRooms = homeBo.getAllRooms();
        for (RoomDTO room : allRooms){
            if (room.getType().equals("Non-AC")){
                nonac=room.getQty();
            }else if (room.getType().equals("Non-AC/Food")){
                nonacfood=room.getQty();
            }else if (room.getType().equals("AC")){
                ac=room.getQty();
            }else {
                acfood=room.getQty();
            }
        }

        series1[0] = new XYChart.Series<>();
        series1[0].getData().add(new XYChart.Data<>("", nonac));
        series1[0].setName("Shares");

        series1[1] = new XYChart.Series<>();
        series1[1].getData().add(new XYChart.Data<>("", nonacfood));
        series1[1].setName("Compulsory Deposits");

        series1[2] = new XYChart.Series<>();
        series1[2].getData().add(new XYChart.Data<>("", ac));
        series1[2].setName("Special Deposits");

        series1[3] = new XYChart.Series<>();
        series1[3].getData().add(new XYChart.Data<>("", acfood));
        series1[3].setName("Pension Deposits");

        roomChart.getData().addAll(series1);
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
        String roomCount = homeBo.getRoomCount();
        lblRooms.setText(roomCount);
    }

    private void loadReservationCount() {
        String reservationCount = homeBo.getReservationCount();
        lblRents.setText("0"+reservationCount);
    }

    private void loadStudentCount() {
        String studentCount = homeBo.getStudentCount();
        lblStudents.setText("0"+studentCount);
    }

}
