package lk.sliit.TropicoMushrooms.bo;

import lk.sliit.TropicoMushrooms.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;

    }

    public enum BOTypes {
        CUSTOMER, ITEM, USER, PURCHASE, ORDER, DEFAULT
    }

    public <T extends SuperBO> T getBO(BOTypes types) {
        switch (types) {
            case CUSTOMER:
                return (T) new CustomerBOImpl();
            case ITEM:
                return (T) new ItemBOImpl();
            case USER:
                return (T) new UserBOImpl();
            case PURCHASE:
                return (T) new PurchaseBOImpl();
            case ORDER:
                return (T) new OrderBOImpl();
            case DEFAULT:
                return (T) new DefaultBOImpl();
            default:
                return null;

        }
    }
}
