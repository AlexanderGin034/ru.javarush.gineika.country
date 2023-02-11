package com.javarush.country.dao;

import com.javarush.country.entity.Country;
import org.hibernate.SessionFactory;

public class CountryDao extends ParentDao<Country> {
    public CountryDao(SessionFactory sessionFactory) {
        super(sessionFactory, Country.class);
    }
}
