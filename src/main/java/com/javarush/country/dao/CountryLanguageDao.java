package com.javarush.country.dao;

import com.javarush.country.entity.CountryLanguage;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class CountryLanguageDao extends ParentDao<CountryLanguage>{
    public CountryLanguageDao(SessionFactory sessionFactory) {
        super(sessionFactory, CountryLanguage.class);
    }

    public List<CountryLanguage> findById(Integer id) {
        String hql = "select o from CountryLanguage o where o.country=:country";
        Query<CountryLanguage> query = getSession().createQuery(hql, CountryLanguage.class);
        query.setParameter("country", id);
        return query.list();
    }
}
