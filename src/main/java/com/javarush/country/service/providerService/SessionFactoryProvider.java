package com.javarush.country.service.providerService;

import org.hibernate.SessionFactory;

public interface SessionFactoryProvider {
    SessionFactory getSessionFactory();
}
