package ru.rikabc.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import ru.rikabc.Models.Product;

import java.util.List;

/**
 * @Author Roman Khayrullin on 16.04.2018
 * @Version 1.0
 */
public class ProductHibernateRepository implements ProductRepository {
    private static SessionFactory sessionFactory;

    public ProductHibernateRepository() {
        if (sessionFactory==null) {
            Configuration configuration = new Configuration();
            configuration.configure(getClass().getClassLoader().getResource("hibernate-products.cfg.xml"));
            sessionFactory = configuration.buildSessionFactory();
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> products;
        Session session = sessionFactory.openSession();
        products = session.createQuery("from Product product", Product.class).getResultList();
        session.close();
        return products;
    }

    @Override
    public boolean save(Product product) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (ConstraintViolationException e) {
            session.close();
            return false;
        }
    }
    public static void closeSessionFactory(){
        if(sessionFactory!=null)
            sessionFactory.close();
    }
}
