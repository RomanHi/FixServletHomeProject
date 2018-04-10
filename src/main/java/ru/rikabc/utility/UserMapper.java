package ru.rikabc.utility;

import org.springframework.jdbc.core.RowMapper;
import ru.rikabc.Models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author Roman Khayrullin on 07.04.2018
 * @Version 1.0
 */
public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet result, int i) throws SQLException {
        User user = new User();
        user.setId(result.getLong("id"));
        user.setUsername(result.getString("username"));
        user.setPassword(result.getString("password"));
        user.setRole(result.getString("role"));
        return user;
    }
}
