package lk.ijse.orm.bo.custom;

import lk.ijse.orm.bo.SuperBo;
import lk.ijse.orm.dto.StudentDTO;
import lk.ijse.orm.dto.UserDTO;

public interface SignupBo extends SuperBo {
    boolean saveUser(UserDTO userDTO);
}
