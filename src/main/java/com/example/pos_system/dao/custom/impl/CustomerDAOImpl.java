package com.example.pos_system.dao.custom.impl;

import com.example.pos_system.dao.custom.CustomerDAO;
import com.example.pos_system.entity.Customer;
import com.example.pos_system.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean add(Customer customer) throws Exception {
        Transaction transaction = null;

        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            transaction = session.beginTransaction();

            session.persist(customer);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }

    }

    @Override
    public Customer search(String id) throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.get(Customer.class, id);
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public boolean update(Customer customer) throws Exception {
        Transaction transaction = null;

        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            transaction = session.beginTransaction();

            session.update(customer);
            transaction.commit();

            return true;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }

    }

    @Override
    public boolean delete(String id) throws Exception {
        Transaction transaction = null;

        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            transaction = session.beginTransaction();

            Customer customer = session.get(Customer.class, id);
            session.delete(customer);
            transaction.commit();

            return true;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }

    }

    @Override
    public List<Customer> getAll() throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM customer");
            nativeQuery.addEntity(Customer.class);

            return nativeQuery.list();
        } catch (Exception e) {
            throw e;
        }

    }

}
