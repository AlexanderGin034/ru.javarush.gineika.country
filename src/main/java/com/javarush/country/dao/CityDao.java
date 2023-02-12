package com.javarush.country.dao;

import com.javarush.country.entity.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CityDao extends ParentDao<City> {
    public CityDao(SessionFactory sessionFactory) {
        super(sessionFactory, City.class);
    }

//    private List<City> fetchData() {
//        try (Session session = getSession()) {
//
//        }
//    }
}
