package com.example.pos_system.dao;

import com.example.pos_system.dao.custom.impl.CustomerDAOImpl;
import com.example.pos_system.dao.custom.impl.ItemDAOImpl;
import com.example.pos_system.dao.custom.impl.OrderDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDAOFactory() {
        return daoFactory == null ? daoFactory = new DAOFactory():daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER , ITEM , ORDER
    }

    public SuperDAO getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case CUSTOMER: return new CustomerDAOImpl();
            case ITEM: return new ItemDAOImpl();
            case ORDER: return new OrderDAOImpl();
            default: return null;
        }

    }


}
