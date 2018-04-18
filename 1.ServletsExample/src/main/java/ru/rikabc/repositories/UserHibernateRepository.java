package ru.rikabc.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import org.mindrot.jbcrypt.BCrypt;
import ru.rikabc.Models.User;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * @Author Roman Khayrullin on 07.04.2018
 * @Version 1.0
 */
public class UserHibernateRepository implements UserRepository {
    private static final int ROUNDS = 12;
    private final String SELECT_USER = " from User u WHERE u.username=:username";
    private static SessionFactory sessionFactory;

    public UserHibernateRepository() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.configure(getClass().getClassLoader().getResource("hibernate-users.cfg.xml"));
            sessionFactory = configuration.buildSessionFactory();
        }
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findUserByUsername(String username) {
        User user = null;
        Session session = sessionFactory.openSession();
        try {
            user = session.createQuery(SELECT_USER, User.class).setParameter("username", username).getSingleResult();
        } catch (NoResultException ex) {

        }
        session.close();
        return user != null ? user : new User();
    }

    @Override
    public boolean save(User user) {
        String hasPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(ROUNDS));
        user.setPassword(hasPassword);
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            session.close();
            return true;

        } catch (ConstraintViolationException ex) {
            session.close();
            return false;
        }
    }

    @Override
    public boolean isExist(User user, String password) {
        return user.getId() != 0 && BCrypt.checkpw(password, user.getPassword());
    }

    public static void closeSessionFactory() {
        if (sessionFactory != null)
            sessionFactory.close();
    }
}
