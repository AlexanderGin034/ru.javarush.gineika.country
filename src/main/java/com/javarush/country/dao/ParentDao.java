package com.javarush.country.dao;

import com.javarush.country.service.providerService.PropertiesSessionFactoryProviderImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ParentDao<T> {

    private final Class<T> tClass;
    private final SessionFactory sessionFactory;
    
    ParentDao(SessionFactory sessionFactory, Class<T> tClass) {
        this.sessionFactory = sessionFactory;
        this.tClass = tClass;
    }
    
    public List<T> findAll(){
        String query = "select c from " + tClass.getName() + " c";
        return getSession()
                .createQuery(query, tClass)
                .list();
    }
    
    private Session getSession() {
        return sessionFactory.openSession();
    }
}
