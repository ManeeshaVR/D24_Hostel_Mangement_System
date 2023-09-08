package lk.ijse.orm.dao.custom.impl;

import lk.ijse.orm.bo.custom.ReservationBo;
import lk.ijse.orm.dao.custom.ReservationDAO;
import lk.ijse.orm.entity.Reservation;
import lk.ijse.orm.entity.Student;
import lk.ijse.orm.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public boolean add(Reservation entity){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(String s){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Reservation reservation = new Reservation();
            reservation.setReservationId(s);
            session.delete(reservation);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(Reservation entity){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean exists(String s){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("select reservationId from Reservation where reservationId = ?1");
            query.setParameter(1, s);
            String reservationId = (String) query.uniqueResult();
            transaction.commit();
            return reservationId != null;
        }catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public List<Reservation> getAll(){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            Query query = session.createQuery("from Reservation");
            return (List<Reservation>) query.list();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public String getCount() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Long count = (Long) session.createQuery("select COUNT(*) from Reservation").getSingleResult();
            transaction.commit();
            return String.valueOf(count);
        }catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<Reservation> getPendingPayments() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            Query<Reservation> query = session.createQuery("select r from Reservation r where r.status = 'pending payment'",Reservation.class);
            return query.list();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public Reservation get(String reservationId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Reservation reservation = session.get(Reservation.class,reservationId);
            transaction.commit();
            return reservation;
        }catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
        }
        return null;
    }
}
