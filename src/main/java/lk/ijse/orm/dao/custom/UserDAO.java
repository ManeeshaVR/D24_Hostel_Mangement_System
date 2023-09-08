package lk.ijse.orm.dao.custom;

import lk.ijse.orm.dao.CrudDAO;
import lk.ijse.orm.entity.Reservation;
import lk.ijse.orm.entity.User;

public interface UserDAO extends CrudDAO<User, String> {
    User authenticate(String username, String password);

    User get(String userId);
}
