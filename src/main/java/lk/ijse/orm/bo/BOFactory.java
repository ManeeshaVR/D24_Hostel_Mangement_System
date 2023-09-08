package lk.ijse.orm.bo;

import lk.ijse.orm.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }

    public enum BOTypes {
        STUDENT,
        ROOM,
        RESERVATION,
        LOGIN,
        SIGNUP,
        ACCOUNT,
        HOME
    }

    public static BOFactory getBOFactory(){
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public SuperBo getBO(BOTypes types){
        switch (types){
            case STUDENT:
                return new StudentBoImpl();
            case ROOM:
                return new RoomBoImpl();
            case RESERVATION:
                return new ReservationBoImpl();
            case LOGIN:
                return new LoginBoImpl();
            case HOME:
                return new HomeBoImpl();
            case ACCOUNT:
                return new AccountBoImpl();
            default:
                return null;
        }
    }
}
