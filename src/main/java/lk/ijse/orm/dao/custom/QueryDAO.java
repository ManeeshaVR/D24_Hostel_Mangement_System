package lk.ijse.orm.dao.custom;

import lk.ijse.orm.dao.SuperDAO;
import lk.ijse.orm.entity.Student;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<Student> getPendingPayments();
}
