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
    public boolean add(Item item, Session session) throws Exception {
        session.persist(item);
        return true;

    }

    @Override
    public Item search(String id, Session session) throws Exception {
        Item item = session.get(Item.class, id);

        if (item == null) {
            NativeQuery nativeQuery = session.createNativeQuery("SELECT * From item WHERE iName = ?1");
            nativeQuery.addEntity(Item.class);
            nativeQuery.setParameter(1, id);

            item = (Item) nativeQuery.uniqueResult();

        }

        return item;

    }

    @Override
    public boolean update(Item item, Session session) throws Exception {
        session.update(item);
        return true;
    }

    @Override
    public boolean delete(String id, Session session) throws Exception {
        Item item = session.get(Item.class, id);
        session.delete(item);
        return true;

    }

    @Override
    public List<Item> getAll(Session session) throws Exception {
        NativeQuery nativeQuery = session.createNativeQuery("SELECT  * FROM item");
        nativeQuery.addEntity(Item.class);

        return nativeQuery.list();
    }

}
