package com.example.pos_system.dao.custom.impl;

import com.example.pos_system.dao.custom.OrderDAO;
import com.example.pos_system.dto.CustomerDTO;
import com.example.pos_system.dto.ItemDTO;
import com.example.pos_system.entity.Customer;
import com.example.pos_system.entity.Item;
import com.example.pos_system.entity.Order_t;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean add(Order_t order, Session session) throws Exception {
        session.persist(order);
        return true;

    }

    @Override
    public Order_t search(String id, Session session) throws Exception {
        return session.get(Order_t.class, id);

    }

    @Override
    public boolean update(Order_t order, Session session) throws Exception {
        session.update(order);
        return true;

    }

    @Override
    public boolean delete(String id, Session session) throws Exception {
        Order_t order = session.get(Order_t.class, id);
        session.delete(order);
        return true;

    }

    @Override
    public List<Order_t> getAll(Session session) throws Exception {
        NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM order_t");
        nativeQuery.addEntity(Order_t.class);

        return nativeQuery.list();

    }

}
