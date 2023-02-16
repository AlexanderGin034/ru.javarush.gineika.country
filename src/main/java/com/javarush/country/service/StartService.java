package com.javarush.country.service;

import com.javarush.country.entity.PropertiesSessionFactoryProviderImpl;
import com.javarush.country.dao.CityDao;
import com.javarush.country.entity.City;
import com.javarush.country.redis.CityCountry;
import org.hibernate.SessionFactory;

import java.util.List;

public class StartService {
    private final SessionFactory sessionFactory;
    private final RedisService redisService;
    private final CityDao cityDao;
    private final List<City> cities;
    private final List<CityCountry> cityCountries;
    private final List<Integer> ids;

    public StartService() {
        this.sessionFactory = new PropertiesSessionFactoryProviderImpl().getSessionFactory();
        this.redisService = new RedisService();
        this.cityDao = new CityDao(sessionFactory);
        this.cities = cityDao.fetchData();
        this.cityCountries = redisService.transformData(cities, sessionFactory);
        redisService.pushToRedis(cityCountries);
        this.ids = List.of(3, 2545, 123, 4, 189, 89, 3458, 1189, 10, 102);
    }

    public long getRedisTime() {
        long startRedis = System.currentTimeMillis();
        redisService.testRedisData(ids);
        long stopRedis = System.currentTimeMillis();
        return stopRedis - startRedis;
    }

    public long geMysqlTime() {
        long startMysql = System.currentTimeMillis();
        redisService.testSqlData(ids, sessionFactory, cityDao);
        long stopMysql = System.currentTimeMillis();
        return stopMysql - startMysql;
    }
}
