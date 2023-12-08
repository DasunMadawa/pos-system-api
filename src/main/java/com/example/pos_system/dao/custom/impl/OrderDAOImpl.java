package com.example.pos_system.dao.custom.impl;

import com.example.pos_system.dao.custom.OrderDAO;
import com.example.pos_system.entity.Order_t;
import com.example.pos_system.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean add(Order_t order) throws Exception {
        Transaction transaction = null;

        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            transaction = session.beginTransaction();

            session.persist(order);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }

    }

    @Override
    public Order_t search(String id) throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {

            return session.get(Order_t.class, id);
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public boolean update(Order_t order) throws Exception {
        Transaction transaction = null;

        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            transaction = session.beginTransaction();

            session.update(order);
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

            Order_t order = session.get(Order_t.class, id);
            session.delete(order);
            return true;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }

    }

    @Override
    public List<Order_t> getAll() throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM order_t");
            nativeQuery.addEntity(Order_t.class);

            return nativeQuery.list();
        } catch (Exception e) {
            throw e;
        }
    }

}
