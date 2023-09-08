package lk.ijse.orm.bo.custom.impl;

import lk.ijse.orm.bo.Convertor;
import lk.ijse.orm.bo.custom.RoomBo;
import lk.ijse.orm.dao.DAOFactory;
import lk.ijse.orm.dao.custom.RoomDAO;
import lk.ijse.orm.dto.RoomDTO;
import lk.ijse.orm.entity.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomBoImpl implements RoomBo {

    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);

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
    public boolean saveRoom(RoomDTO roomDTO){
        return roomDAO.add(Convertor.toRoom(roomDTO));
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO){
        return roomDAO.update(Convertor.toRoom(roomDTO));
    }

    @Override
    public boolean deleteRoom(String roomTypeId){
        return roomDAO.delete(roomTypeId);
    }

    @Override
    public boolean existRoom(String roomId){
        return roomDAO.exists(roomId);
    }

    @Override
    public RoomDTO getRoom(String roomId) {
        return Convertor.toRoomDTO(roomDAO.get(roomId));
    }
}
