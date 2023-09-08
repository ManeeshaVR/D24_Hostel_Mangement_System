package lk.ijse.orm.bo.custom;

import lk.ijse.orm.bo.SuperBo;
import lk.ijse.orm.dto.UserDTO;

public interface AccountBo extends SuperBo {
    boolean authenticateUser(String username, String password);

    boolean updateUser(UserDTO userDTO);
}
