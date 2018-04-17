package ru.rikabc.utility;

import ru.rikabc.repositories.ProductHibernateRepository;
import ru.rikabc.repositories.UserHibernateRepository;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Author Roman Khayrullin on 17.04.2018
 * @Version 1.0
 */
public class ShutdownLisener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("destroy context");
        UserHibernateRepository.closeSessionFactory();
        ProductHibernateRepository.closeSessionFactory();
    }
}
