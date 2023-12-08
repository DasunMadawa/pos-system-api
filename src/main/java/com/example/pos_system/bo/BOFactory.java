package com.example.pos_system.bo;

import com.example.pos_system.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getBOFactory() {
        return boFactory == null ? boFactory = new BOFactory():boFactory;
    }

    public enum BOTypes {
        CUSTOMER , ITEM , ORDER , HOME , ORDER_HISTORY
    }

    public SuperBO getBO(BOTypes boTypes) {
        switch (boTypes) {
            case CUSTOMER: return new CustomerBOImpl();
            case ITEM: return new ItemBOImpl();
            case ORDER: return new OrderBOImpl();
            case HOME: return new HomeBOImpl();
            case ORDER_HISTORY: return new OrderHisBOImpl();
            default: return null;
        }

    }

}
