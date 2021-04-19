package com.schkv.smarthome.web.services.impl;

import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;
import java.time.LocalDate;
import com.schkv.smarthome.web.models.Weather;
import com.schkv.smarthome.web.services.DbService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DbServiceImpl implements DbService {

    final SessionFactory sessionFactory;

    @Autowired
    public DbServiceImpl(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Weather getCurrentWeather() {
        return null;
    }

    @Override
    public void setWeather(LocalDate currentDate) {
        EntityManager em = sessionFactory.createEntityManager();

        em.persist(new Weather());
        em.close();
    }
}
