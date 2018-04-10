package ru.rikabc.repositories;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import ru.rikabc.Models.User;

import java.util.List;

/**
 * @Author Roman Khayrullin on 07.04.2018
 * @Version 1.0
 */
public class UserHibernateRepository implements UserRepository {
    private Session session;

    public UserHibernateRepository() {
        Configuration configuration = new Configuration();
        configuration.configure(getClass().getClassLoader().getResource("hibernate-user.cfg.xml"));
        this.session = configuration.buildSessionFactory().openSession();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findUserByUsername(String username) {
        return null;
    }

    @Override
    public boolean save(User user) {
        return false;
    }

    @Override
    public boolean isExist(User user, String password) {
        return false;
    }
}
