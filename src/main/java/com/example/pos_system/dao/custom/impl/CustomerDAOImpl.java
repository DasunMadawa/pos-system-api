package com.example.pos_system.dao.custom.impl;

import com.example.pos_system.dao.custom.CustomerDAO;
import com.example.pos_system.entity.Customer;
import com.example.pos_system.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean add(Customer customer, Session session) throws Exception {
        session.persist(customer);
        return true;

    }

    @Override
    public Customer search(String id, Session session) throws Exception {
        Customer customer = session.get(Customer.class, id);

        if (customer == null) {
            NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM customer WHERE cName = ?1");
            nativeQuery.addEntity(Customer.class);
            nativeQuery.setParameter(1, id);

            customer = (Customer) nativeQuery.uniqueResult();
        }

        return customer;

    }

    @Override
    public boolean update(Customer customer, Session session) throws Exception {
        session.update(customer);
        return true;

    }

    @Override
    public boolean delete(String id, Session session) throws Exception {
        Customer customer = session.get(Customer.class, id);
        session.delete(customer);
        return true;

    }

    @Override
    public List<Customer> getAll(Session session) throws Exception {
        NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM customer");
        nativeQuery.addEntity(Customer.class);

        return nativeQuery.list();

    }

}
