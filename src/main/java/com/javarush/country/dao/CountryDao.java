package com.javarush.country.dao;

import com.javarush.country.entity.Country;
import org.hibernate.SessionFactory;

import java.util.List;

public class CountryDao extends ParentDao<Country> {
    public CountryDao(SessionFactory sessionFactory) {
        super(sessionFactory, Country.class);
    }

    @Override
    public List<Country> findAll() {
        String hql = "select c from Country c join fetch c.languages";
        return getSession()
                .createQuery(hql, Country.class)
                .list();
    }
}
