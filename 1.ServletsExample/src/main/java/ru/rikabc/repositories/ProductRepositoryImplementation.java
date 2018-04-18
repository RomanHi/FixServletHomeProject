package ru.rikabc.repositories;

import ru.rikabc.Models.Product;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Author Roman Khayrullin on 17.03.2018
 * @Version 1.0
 */
public class ProductRepositoryImplementation implements ProductRepository {
    private static final String SELECT_ALL_PRODUCT = "SELECT * FROM product";
    private static final String INSERT_PRODUCT = "INSERT INTO public.product (name, price, description) " +
            "VALUES (?, ?, ?) ON CONFLICT DO NOTHING;";

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();

        try (Connection connect = getConnetion()) {
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery(SELECT_ALL_PRODUCT);
            while (result.next()) {
                products.add(new Product(result.getLong("id"),
                        result.getString("name"), result.getDouble("price"),
                        result.getString("description")));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            return products;
        }
    }

    @Override
    public boolean save(Product product) {
        int responce = 0;

        try (Connection connect = getConnetion()) {
            PreparedStatement preparedStatement = connect.prepareStatement(INSERT_PRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getDescription());
            responce = preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return responce != 0;
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
        return DriverManager.getConnection(properties.getProperty("db.product.url"),
                properties.getProperty("db.username"), properties.getProperty("db.password"));
    }
}
