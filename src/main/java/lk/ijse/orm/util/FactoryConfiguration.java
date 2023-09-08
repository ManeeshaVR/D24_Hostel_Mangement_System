package lk.ijse.orm.util;

import lk.ijse.orm.entity.Reservation;
import lk.ijse.orm.entity.Room;
import lk.ijse.orm.entity.Student;
import lk.ijse.orm.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {

    private static FactoryConfiguration factoryConfiguration;

    private SessionFactory sessionFactory;

    private FactoryConfiguration(){

        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Student.class).addAnnotatedClass(Room.class).addAnnotatedClass(Reservation.class).addAnnotatedClass(User.class);

        sessionFactory=configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance(){

        return (factoryConfiguration==null)? factoryConfiguration=new FactoryConfiguration(): factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
