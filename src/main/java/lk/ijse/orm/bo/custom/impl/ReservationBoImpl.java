package lk.ijse.orm.bo.custom.impl;

import lk.ijse.orm.bo.Convertor;
import lk.ijse.orm.bo.custom.ReservationBo;
import lk.ijse.orm.dao.DAOFactory;
import lk.ijse.orm.dao.custom.ReservationDAO;
import lk.ijse.orm.dao.custom.RoomDAO;
import lk.ijse.orm.dao.custom.StudentDAO;
import lk.ijse.orm.dto.ReservationDTO;
import lk.ijse.orm.dto.RoomDTO;
import lk.ijse.orm.dto.StudentDTO;
import lk.ijse.orm.entity.Reservation;
import lk.ijse.orm.entity.Room;
import lk.ijse.orm.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class ReservationBoImpl implements ReservationBo {

    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public List<ReservationDTO> getAllReservations(){
        List<Reservation> reservationList = reservationDAO.getAll();
        List<ReservationDTO> reservationDTOS = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            reservationDTOS.add(Convertor.toReservationDTO(reservation));
        }
        return reservationDTOS;
    }

    @Override
    public List<StudentDTO> getAllStudents(){
        List<Student> studentList = studentDAO.getAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : studentList) {
            studentDTOS.add(Convertor.toStudentDTO(student));
        }
        return studentDTOS;
    }

    @Override
    public List<RoomDTO> getAllRooms(){
        List<Room> roomList = roomDAO.getAll();
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room room : roomList) {
            roomDTOS.add(Convertor.toRoomDTO(room));
        }
        return roomDTOS;
    }

    @Override
    public StudentDTO getStudent(String studentId){
        return Convertor.toStudentDTO(studentDAO.get(studentId));
    }

    @Override
    public RoomDTO getRoom(String roomId) {
        return Convertor.toRoomDTO(roomDAO.get(roomId));
    }

    @Override
    public boolean saveReservation(ReservationDTO reservationDTO) {
        return reservationDAO.add(Convertor.toReservation(reservationDTO));
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) {
        return roomDAO.update(Convertor.toRoom(roomDTO));
    }

    @Override
    public boolean cancelReservation(ReservationDTO reservationDTO) {
        return reservationDAO.update(Convertor.toReservation(reservationDTO));
    }
}
