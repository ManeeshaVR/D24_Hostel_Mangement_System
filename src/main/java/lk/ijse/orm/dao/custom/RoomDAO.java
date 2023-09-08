package lk.ijse.orm.dao.custom;

import lk.ijse.orm.dao.CrudDAO;
import lk.ijse.orm.entity.Reservation;
import lk.ijse.orm.entity.Room;

public interface RoomDAO extends CrudDAO<Room, String> {
    Room get(String roomId);
}
