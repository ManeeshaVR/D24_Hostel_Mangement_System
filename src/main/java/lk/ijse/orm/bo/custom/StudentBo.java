package lk.ijse.orm.bo.custom;

import lk.ijse.orm.bo.SuperBo;
import lk.ijse.orm.dto.StudentDTO;

import java.util.List;

public interface StudentBo extends SuperBo {
    boolean saveStudent(StudentDTO studentDTO);

    List<StudentDTO> getAllStudents();

    boolean updateStudent(StudentDTO studentDTO);

    boolean deleteStudent(String studentId);

    boolean existStudent(String studentId);

    StudentDTO getStudent(String studentId);
}
