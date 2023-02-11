package com.javarush.country.dao;

import com.javarush.country.entity.City;
import org.hibernate.SessionFactory;

public class CityDao extends ParentDao<City> {
    public CityDao(SessionFactory sessionFactory) {
        super(sessionFactory, City.class);
    }
}
