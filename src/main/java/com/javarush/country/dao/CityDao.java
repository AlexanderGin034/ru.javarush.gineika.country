package com.javarush.country.dao;

import com.javarush.country.entity.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CityDao extends AbstractDao<City> {
    private static final int STEP = 500;
    public CityDao(SessionFactory sessionFactory) {
        super(sessionFactory, City.class);
    }

    public Optional<City> getById(Integer id) {
        Query<City> query = getSession().createQuery("select c from City c join fetch c.countryId where c.id = :ID", City.class);
        query.setParameter("ID", id);
        return Optional.ofNullable(query.getSingleResult());
    }

    public List<City> fetchData() {
        try (Session session = getSession()) {
            List<City> allCities = new ArrayList<>();
            session.beginTransaction();

            int totalCount = getTotalCount();
            for (int i = 0; i < totalCount; i += STEP) {
                allCities.addAll(findAll(i, STEP));
            }
            session.getTransaction().commit();
            return allCities;
        }
    }
}
