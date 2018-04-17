package ru.rikabc.repositories;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.rikabc.Models.User;
import ru.rikabc.utility.UserMapper;

import javax.sql.DataSource;
import java.util.List;

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

    public UserJdbcTemplateRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
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
