package com.javarush.country.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.country.dao.CityDao;
import com.javarush.country.entity.City;
import com.javarush.country.entity.Country;
import com.javarush.country.entity.CountryLanguage;
import com.javarush.country.redis.CityCountry;
import com.javarush.country.redis.Language;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RedisService {
    private final ObjectMapper mapper = new ObjectMapper();
    public List<CityCountry> transformData(List<City> cities, SessionFactory sessionFactory) {
        return cities.stream().map(city -> {
            CityCountry cityCountry = new CityCountry();
            // city
            cityCountry.setId(city.getId());
            cityCountry.setName(city.getName());
            cityCountry.setDistrict(city.getDistrict());
            cityCountry.setPopulation(city.getPopulation());

            // country
            Country country = city.getCountryId();
            cityCountry.setCountryCode(country.getCode());
            cityCountry.setAlternativeCountryCode(country.getExtraCode());
            cityCountry.setCountryName(country.getName());
            cityCountry.setContinent(country.getContinent());
            cityCountry.setCountryRegion(country.getRegion());
            cityCountry.setCountrySurfaceArea(country.getSurfaceArea());
            cityCountry.setCountryPopulation(country.getPopulation());

            // countryLanguage
            Set<CountryLanguage> countryLanguages = country.getLanguages();
            Set<Language> languages = countryLanguages.stream().map(cl -> {
                Language language = new Language();
                language.setLanguage(cl.getLanguage());
                language.setIsOfficial(cl.isOfficial());
                language.setPercentage(cl.getPercentage());
                return language;
            }).collect(Collectors.toSet());

            cityCountry.setLanguages(languages);
            return cityCountry;

        }).collect(Collectors.toList());
    }

    public void pushToRedis(List<CityCountry> data) {
        try(StatefulRedisConnection<String, String> connect = prepareRedisClient().connect()) {
            RedisCommands<String, String> sync = connect.sync();
            for (CityCountry cityCountry: data) {
                sync.set(String.valueOf(cityCountry.getId()), mapper.writeValueAsString(cityCountry));
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    private RedisClient prepareRedisClient() {
        RedisClient redisClient = RedisClient.create(RedisURI.create("localhost", 6379));
        try (StatefulRedisConnection<String, String> connection = redisClient.connect()) {
            System.out.println("\nConnected to Redis\n");
        }
        return redisClient;
    }

    public void testRedisData(List<Integer> ids) {
        try(StatefulRedisConnection<String, String> connect = prepareRedisClient().connect()) {
            RedisCommands<String, String> sync = connect.sync();
            for (Integer id : ids) {
                String value = sync.get(String.valueOf(id));
                try {
                    mapper.readValue(value, CityCountry.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void testSqlData(List<Integer> ids, SessionFactory sessionFactory, CityDao cityDao) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            for (Integer id : ids) {
                City city = cityDao.getById(id);
                Set<CountryLanguage> languages = city.getCountryId().getLanguages();
            }
            session.getTransaction().commit();
        }
    }
}
