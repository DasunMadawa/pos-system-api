package com.example.pos_system.dao.custom.impl;

import com.example.pos_system.dao.custom.ItemDAO;
import com.example.pos_system.entity.Item;
import com.example.pos_system.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean add(Item item) throws Exception {
        Transaction transaction = null;

        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            transaction = session.beginTransaction();

            session.persist(item);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }

    }

    @Override
    public Item search(String id) throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.get(Item.class, id);
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public boolean update(Item item) throws Exception {
        Transaction transaction = null;

        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            transaction = session.beginTransaction();

            session.update(item);
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

            Item item = session.get(Item.class, id);
            session.delete(item);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }

    }

    @Override
    public List<Item> getAll() throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            NativeQuery nativeQuery = session.createNativeQuery("SELECT  * FROM item");
            nativeQuery.addEntity(Item.class);

            return nativeQuery.list();
        } catch (Exception e) {
            throw e;
        }

    }

}
