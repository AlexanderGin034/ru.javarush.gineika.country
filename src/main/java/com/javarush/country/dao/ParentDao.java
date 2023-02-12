package com.javarush.country.dao;

import com.javarush.country.service.providerService.PropertiesSessionFactoryProviderImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ParentDao<T> {

    private final Class<T> tClass;
    private final SessionFactory sessionFactory;
    
    ParentDao(SessionFactory sessionFactory, Class<T> tClass) {
        this.sessionFactory = sessionFactory;
        this.tClass = tClass;
    }
    
    public List<T> findAll(){
        String hql = "select c from " + tClass.getName() + " c";
        return getSession()
                .createQuery(hql, tClass)
                .list();
    }
    public List<T> getItems(int offset, int limit) {
        String hql = "select c from " + tClass.getName() + " c";
        Query<T> query = getSession().createQuery(hql, tClass);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    public int getTotalCount() {
        String hql = "select count(c) from " + tClass.getName() + " c";
        Query<Long> query = getSession()
                .createQuery(hql, Long.class);
        return Math.toIntExact(query.uniqueResult());
    }
    protected Session getSession() {
        return sessionFactory.openSession();
    }
}
