package lk.ijse.orm.bo.custom.impl;

import lk.ijse.orm.bo.Convertor;
import lk.ijse.orm.bo.custom.HomeBo;
import lk.ijse.orm.dao.DAOFactory;
import lk.ijse.orm.dao.custom.ReservationDAO;
import lk.ijse.orm.dao.custom.RoomDAO;
import lk.ijse.orm.dao.custom.StudentDAO;
import lk.ijse.orm.dto.ReservationDTO;
import lk.ijse.orm.dto.RoomDTO;
import lk.ijse.orm.dto.StudentDTO;
import lk.ijse.orm.entity.Reservation;
import lk.ijse.orm.entity.Room;

import java.util.ArrayList;
import java.util.List;

public class HomeBoImpl implements HomeBo {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);

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
    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservationList = reservationDAO.getAll();
        List<ReservationDTO> reservationDTOS = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            reservationDTOS.add(Convertor.toReservationDTO(reservation));
        }
        return reservationDTOS;
    }

    @Override
    public String getStudentCount() {
        return studentDAO.getCount();
    }

    @Override
    public String getRoomCount() {
        return roomDAO.getCount();
    }

    @Override
    public String getReservationCount() {
        return reservationDAO.getCount();
    }

    @Override
    public String getAdminCount() {
        return "02";
    }


}
