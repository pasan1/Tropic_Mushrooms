package lk.sliit.TropicoMushrooms.dao;

import lk.sliit.TropicoMushrooms.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER, ITEM, ORDERDETAIL, ORDERS, PURCHASE, RETURNS, SQ, USER, QUERY
    }

    public <T extends SuperDAO> T getDAO(DAOTypes types) {
        switch (types) {
            case CUSTOMER:
                return (T) new CustomerDAOImpl();
            case ITEM:
                return (T) new ItemDAOImpl();
            case ORDERDETAIL:
                return (T) new OrderDetailDAOImpl();
            case ORDERS:
                return (T) new OrdersDAOImpl();
            case PURCHASE:
                return (T) new PurchaseDAOImpl();
            case RETURNS:
                return (T) new ReturnsDAOImpl();
            case SQ:
                return (T) new SqDAOImpl();
            case USER:
                return (T) new UserDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            default:
                return null;
        }
    }
}
