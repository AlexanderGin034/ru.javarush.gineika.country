package com.javarush.country.service.providerService;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PropertiesSessionFactoryProviderImpl implements SessionFactoryProvider {
    @Override
    public SessionFactory getSessionFactory() {
        return new Configuration().buildSessionFactory();
    }
}
