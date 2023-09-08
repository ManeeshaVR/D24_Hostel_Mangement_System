package lk.ijse.orm.dao.custom;

import lk.ijse.orm.dao.CrudDAO;
import lk.ijse.orm.entity.Reservation;
import lk.ijse.orm.entity.Student;

import java.util.List;

public interface ReservationDAO extends CrudDAO<Reservation, String> {
    List <Reservation> getPendingPayments();

    Reservation get(String reservationId);

}
