package lk.ijse.orm.bo.custom;

import lk.ijse.orm.bo.SuperBo;
import lk.ijse.orm.dto.StudentDTO;

import java.util.List;

public interface HomeBo extends SuperBo {
    String getStudentCount();

    String getRoomCount();

    String getReservationCount();

    String getMoneyCount();

    List<StudentDTO> getPendingPayments();
}
