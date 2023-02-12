package com.javarush.country.service;

import com.javarush.country.dao.CountryLanguageDao;
import com.javarush.country.entity.City;
import com.javarush.country.entity.Country;
import com.javarush.country.entity.CountryLanguage;
import com.javarush.country.redis.CityCountry;
import com.javarush.country.redis.Language;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RedisService {
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
            CountryLanguageDao countryLanguageDao = new CountryLanguageDao(sessionFactory);
            List<CountryLanguage> countryLanguages = countryLanguageDao.findById(country.getId());
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
}
