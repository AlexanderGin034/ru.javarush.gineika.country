package com.javarush.country.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.country.dao.PropertiesSessionFactoryProviderImpl;
import com.javarush.country.dao.CityDao;
import com.javarush.country.entity.City;
import com.javarush.country.redis.CityCountry;
import org.hibernate.SessionFactory;

import java.util.List;

public class StartTest {
    private final SessionFactory sessionFactory;
    private final RedisService redisService;
    private final CityDao cityDao;
    private final List<City> cities;
    private final List<CityCountry> cityCountries;
    private final List<Integer> ids;

    public StartTest() {
        this.sessionFactory = new PropertiesSessionFactoryProviderImpl().getSessionFactory();
        this.redisService = new RedisService(new ObjectMapper());
        this.cityDao = new CityDao(sessionFactory);
        this.cities = cityDao.fetchData();
        this.cityCountries = redisService.transformData(cities, sessionFactory);
        redisService.pushToRedis(cityCountries);
        this.ids = List.of(3, 2545, 123, 4, 189, 89, 3458, 1189, 10, 102);
    }

    public long getRedisTime() {
        long startRedis = System.currentTimeMillis();
        redisService.getRedisData(ids);
        long stopRedis = System.currentTimeMillis();
        return stopRedis - startRedis;
    }

    public long geMysqlTime() {
        long startMysql = System.currentTimeMillis();
        redisService.getSqlData(ids, sessionFactory, cityDao);
        long stopMysql = System.currentTimeMillis();
        return stopMysql - startMysql;
    }
}
