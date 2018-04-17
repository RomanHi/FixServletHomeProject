package ru.rikabc.utility;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.rikabc.repositories.ProductHibernateRepository;
import ru.rikabc.repositories.UserHibernateRepository;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Author Roman Khayrullin on 17.04.2018
 * @Version 1.0
 */
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfigure.class);
        sce.getServletContext().setAttribute("springContext", applicationContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("destroy context");
        UserHibernateRepository.closeSessionFactory();
        ProductHibernateRepository.closeSessionFactory();
    }
}
