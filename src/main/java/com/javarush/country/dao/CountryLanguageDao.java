package com.javarush.country.dao;

import com.javarush.country.entity.CountryLanguage;
import org.hibernate.SessionFactory;

public class CountryLanguageDao extends AbstractDao<CountryLanguage> {
    public CountryLanguageDao(SessionFactory sessionFactory) {
        super(sessionFactory, CountryLanguage.class);
    }
}
