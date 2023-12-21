package com.example.pos_system.dao;

import org.hibernate.Session;

import java.util.List;

public interface CrudDAO <T> extends SuperDAO {
    public boolean add(T entity , Session session) throws Exception;
    public T search(String id , Session session) throws Exception;
    public boolean update(T entity , Session session) throws Exception;
    public boolean delete(String id , Session session) throws Exception;
    public List<T> getAll(Session session) throws Exception;

}
