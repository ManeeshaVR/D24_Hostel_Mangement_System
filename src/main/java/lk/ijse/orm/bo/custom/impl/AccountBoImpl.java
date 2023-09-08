package lk.ijse.orm.bo.custom.impl;

import lk.ijse.orm.bo.Convertor;
import lk.ijse.orm.bo.custom.AccountBo;
import lk.ijse.orm.dao.DAOFactory;
import lk.ijse.orm.dao.custom.UserDAO;
import lk.ijse.orm.dto.UserDTO;
import lk.ijse.orm.entity.User;

public class AccountBoImpl implements AccountBo {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean authenticateUser(String username, String password) {
        User user = userDAO.authenticate(username, password);
        return user != null;
    }

    @Override
    public boolean updateUser(UserDTO userDTO) {
        return userDAO.update(Convertor.toUser(userDTO));
    }
}
