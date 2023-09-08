package lk.ijse.orm.dao.custom;

import lk.ijse.orm.dao.CrudDAO;
import lk.ijse.orm.entity.Reservation;
import lk.ijse.orm.entity.Student;

public interface StudentDAO extends CrudDAO<Student, String> {
    Student get(String studentId);
}
