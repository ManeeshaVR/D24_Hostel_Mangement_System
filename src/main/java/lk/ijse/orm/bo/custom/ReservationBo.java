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

    boolean saveReservation(ReservationDTO reservationDTO);

    boolean updateRoom(RoomDTO roomDTO);

    boolean cancelReservation(ReservationDTO reservationDTO);
}
