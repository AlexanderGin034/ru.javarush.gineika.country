package com.javarush.country.service.providerService;

import com.javarush.country.dao.CityDao;
import com.javarush.country.dao.CountryDao;
import com.javarush.country.entity.City;
import com.javarush.country.entity.Country;
import io.lettuce.core.RedisClient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new PropertiesSessionFactoryProviderImpl().getSessionFactory();
        CityDao cityDao = new CityDao(sessionFactory);
        CountryDao countryDao = new CountryDao(sessionFactory);
        List<Country> countries = countryDao.findAll();
//        List<City> allCities = cityDao.findAll();
//        System.out.println("cityDao.getTotalCount() = " + cityDao.getTotalCount());
//        List<City> allCities  = cityDao.findAll();


//        try (Session session = sessionFactory.openSession()) {
//            Query<City> query = session.createQuery("select c from City c", City.class);
//            query.stream().forEach(System.out::println);
//        }
    }
}
