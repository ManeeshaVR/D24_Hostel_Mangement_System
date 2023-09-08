package lk.ijse.orm.bo.custom;

import lk.ijse.orm.bo.SuperBo;
import lk.ijse.orm.dto.UserDTO;

public interface LoginBo extends SuperBo {
    boolean authenticateUser(String username, String password);

    UserDTO getUser(String username, String password);
}
