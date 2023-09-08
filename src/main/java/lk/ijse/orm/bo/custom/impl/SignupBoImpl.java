package lk.ijse.orm.bo.custom.impl;

import lk.ijse.orm.bo.Convertor;
import lk.ijse.orm.bo.custom.SignupBo;
import lk.ijse.orm.dao.DAOFactory;
import lk.ijse.orm.dao.custom.UserDAO;
import lk.ijse.orm.dto.UserDTO;

public class SignupBoImpl implements SignupBo {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean saveUser(UserDTO userDTO) {
        return userDAO.add(Convertor.toUser(userDTO));
    }
}
