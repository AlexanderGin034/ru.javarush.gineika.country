package com.javarush.country.dao;

import com.javarush.country.entity.CountryLanguage;
import org.hibernate.SessionFactory;

public class CountryLanguageDao extends ParentDao<CountryLanguage>{
    public CountryLanguageDao(SessionFactory sessionFactory) {
        super(sessionFactory, CountryLanguage.class);
    }
}
