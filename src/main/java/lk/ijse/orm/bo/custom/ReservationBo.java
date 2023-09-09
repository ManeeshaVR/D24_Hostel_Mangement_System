package lk.ijse.orm.bo.custom;

import lk.ijse.orm.bo.SuperBo;
import lk.ijse.orm.dto.ReservationDTO;
import lk.ijse.orm.dto.RoomDTO;
import lk.ijse.orm.dto.StudentDTO;

import java.util.List;

public interface ReservationBo extends SuperBo {
    List<ReservationDTO> getAllReservations();

    List<StudentDTO> getAllStudents();

    List<RoomDTO> getAllRooms();

    StudentDTO getStudent(String studentId);

    RoomDTO getRoom(String roomId);

    ReservationDTO getReservation(String reservationId);

    boolean saveReservation(ReservationDTO reservationDTO);

    boolean updateReservation(ReservationDTO reservationDTO);

    boolean existReservation(String reservationId);

    boolean updateRoom(RoomDTO roomDTO);

    boolean deleteReservation(String reservationId, RoomDTO roomDTO);

    List<ReservationDTO> getPendingPayments();
}
