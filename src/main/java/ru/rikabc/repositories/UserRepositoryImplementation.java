package ru.rikabc.repositories;

import org.mindrot.jbcrypt.BCrypt;
import ru.rikabc.Models.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * @Author Roman Khayrullin on 18.03.2018
 * @Version 1.0
 */
public class UserRepositoryImplementation implements UserRepository {
    private static final int ROUNDS = 12;
    private final String INSERT_USER = "INSERT INTO fix_user " +
            "(username, password) VALUES (?,?) ON CONFLICT DO NOTHING;";
    private final String SELECT_USER = "SELECT * FROM fix_user WHERE username=?;";

    @Override
    public User findUserByUsername(String username) {
        User user = new User();

        try (Connection connect = getConnetion()) {
            PreparedStatement preparedStatement = connect.prepareStatement(SELECT_USER);
            preparedStatement.setString(1, username);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                user.setId(result.getLong("id"));
                user.setUsername(result.getString("username"));
                user.setPassword(result.getString("password"));
                user.setRole(result.getString("role"));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public boolean save(User user) {
        String hasPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(ROUNDS));
        int responce = 0;

        try (Connection connect = getConnetion()) {
            PreparedStatement preparedStatement = connect.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, hasPassword);
            responce = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return responce != 0;
    }

    @Override
    public boolean isExist(User user, String password) {
        return user.getId() != 0 && BCrypt.checkpw(password, user.getPassword());
    }

    private Connection getConnetion() throws SQLException, ClassNotFoundException {
        Properties properties = new Properties();
        InputStream stream = getClass().getClassLoader().getResourceAsStream("application.properties");
        try {
            properties.load(stream);
            Class.forName(properties.getProperty("db.driver"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(properties.getProperty("db.user.url"),
                properties.getProperty("db.username"), properties.getProperty("db.password"));
    }
}
