package com.javarush.country.dao;

import com.javarush.country.entity.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CityDao extends ParentDao<City> {
    public CityDao(SessionFactory sessionFactory) {
        super(sessionFactory, City.class);
    }

    public City getById(Integer id) {
        Query<City> query = getSession().createQuery("select c from City c join fetch c.countryId where c.id = :ID", City.class);
        query.setParameter("ID", id);
        return query.getSingleResult();
    }

    public List<City> fetchData() {
        try (Session session = getSession()) {
            List<City> allCities = new ArrayList<>();
            session.beginTransaction();

            int totalCount = getTotalCount();
            int step = 500;
            for (int i = 0; i < totalCount; i += step) {
                allCities.addAll(getItems(i, step));
            }
            session.getTransaction().commit();
            return allCities;
        }
    }
}
