package lk.ijse.orm.dao.custom.impl;

import lk.ijse.orm.dao.custom.QueryDAO;
import lk.ijse.orm.entity.Student;
import lk.ijse.orm.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public List<Student> getPendingPayments() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            Query<Student> query = session.createQuery("select s from Student s inner join s.reservations r where r.status = 'pending payment'",Student.class);
            return query.list();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }
    }
}