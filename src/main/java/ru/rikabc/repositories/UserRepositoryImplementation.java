package ru.rikabc.repositories;

import org.mindrot.jbcrypt.BCrypt;
import ru.rikabc.Models.User;

import java.sql.*;

/**
 * @Author Roman Khayrullin on 18.03.2018
 * @Version 1.0
 */
public class UserRepositoryImplementation implements UserRepository {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/fix_users";
    private static final String NAME = "postgres";
    private static final String PASSWORD = "root";
    private static final int ROUNDS = 12;
    private final String INSERTUSER = "INSERT INTO fix_user " +
            "(username, password) VALUES (?,?) ON CONFLICT DO NOTHING;";
    private final String SELECTUSER = "SELECT * FROM fix_user WHERE username=?;";

    @Override
    public User findUserByUsername(String username) {
        User user = new User();

        try (Connection connect = getConnetion()) {
            PreparedStatement preparedStatement = connect.prepareStatement(SELECTUSER);
            preparedStatement.setString(1, username);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                user.setId(result.getLong("id"));
                user.setUsername(result.getString("username"));
                user.setPassword(result.getString("password"));
                user.setRole(result.getString("role"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            return user;
        }
    }

    @Override
    public boolean save(User user) {
        String hasPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(ROUNDS));
        int responce = 0;

        try (Connection connect = getConnetion()) {
            PreparedStatement preparedStatement = connect.prepareStatement(INSERTUSER);
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
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, NAME, PASSWORD);
    }
}
