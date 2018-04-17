package ru.rikabc.repositories;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.rikabc.Models.User;
import ru.rikabc.utility.UserMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @Author Roman Khayrullin on 06.04.2018
 * @Version 1.0
 */
public class UserJdbcTemplateRepository implements UserRepository {
    private static final int ROUNDS = 12;
    private final String INSERT_USER = "INSERT INTO fix_user " +
            "(username, password) VALUES (?,?) ON CONFLICT DO NOTHING;";
    private final String SELECT_USER = "SELECT * FROM fix_user WHERE username=?;";
    private JdbcTemplate jdbcTemplate;

    public UserJdbcTemplateRepository() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        Properties properties = new Properties();
        InputStream stream = getClass().getClassLoader().getResourceAsStream("application.properties");

        try {
            properties.load(stream);
            dataSource.setUrl(properties.getProperty("db.user.url"));
            dataSource.setUsername(properties.getProperty("db.username"));
            dataSource.setPassword(properties.getProperty("db.password"));
            dataSource.setDriverClassName(properties.getProperty("db.driver"));
            jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findUserByUsername(String username) {
        User user = new User();
        List<User> list = jdbcTemplate.query(SELECT_USER, new Object[]{username}, new UserMapper());
        if (!list.isEmpty())
            user = list.get(0);
        return user;
    }

    @Override
    public boolean save(User user) {
        String hasPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(ROUNDS));
        return jdbcTemplate.update(INSERT_USER, user.getUsername(), hasPassword) != 0;
    }

    @Override
    public boolean isExist(User user, String password) {
        return user.getId() != 0 && BCrypt.checkpw(password, user.getPassword());
    }
}
