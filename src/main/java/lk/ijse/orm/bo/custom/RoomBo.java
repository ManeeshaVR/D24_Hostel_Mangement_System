package lk.ijse.orm.bo.custom;

import lk.ijse.orm.bo.SuperBo;
import lk.ijse.orm.dto.RoomDTO;

import java.util.List;

public interface RoomBo extends SuperBo {
    List<RoomDTO> getAllRooms();

    boolean saveRoom(RoomDTO roomDTO);

    boolean updateRoom(RoomDTO roomDTO);

    boolean deleteRoom(String roomTypeId);

    boolean existRoom(String text);

    RoomDTO getRoom(String roomId);
}
