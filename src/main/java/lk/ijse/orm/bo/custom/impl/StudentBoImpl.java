package lk.ijse.orm.bo.custom.impl;

import lk.ijse.orm.bo.Convertor;
import lk.ijse.orm.bo.custom.StudentBo;
import lk.ijse.orm.dao.DAOFactory;
import lk.ijse.orm.dao.custom.StudentDAO;
import lk.ijse.orm.dto.StudentDTO;
import lk.ijse.orm.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBoImpl implements StudentBo {

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public boolean saveStudent(StudentDTO studentDTO){
        Student student = Convertor.toStudent(studentDTO);
        return studentDAO.add(student);
    }

    @Override
    public List<StudentDTO> getAllStudents(){
        List<Student> students = studentDAO.getAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = Convertor.toStudentDTO(student);
            studentDTOS.add(studentDTO);
        }

        return studentDTOS;
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO){
        Student student = Convertor.toStudent(studentDTO);
        return studentDAO.update(student);
    }

    @Override
    public boolean deleteStudent(String studentId){
        return studentDAO.delete(studentId);
    }

    @Override
    public boolean existStudent(String studentId){
        return studentDAO.exists(studentId);
    }

    @Override
    public StudentDTO getStudent(String studentId){
        return Convertor.toStudentDTO(studentDAO.get(studentId));
    }
}
