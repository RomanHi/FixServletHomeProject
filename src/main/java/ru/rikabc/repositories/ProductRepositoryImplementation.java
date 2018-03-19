package ru.rikabc.repositories;

import ru.rikabc.Models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Roman Khayrullin on 17.03.2018
 * @Version 1.0
 */
public class ProductRepositoryImplementation implements ProductRepository {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/productDb";
    private static final String NAME = "postgres";
    private static final String PASSWORD = "root";
    private static final String GETALLPRODUCT = "SELECT * FROM product";
    private static final String INSERTPRODUCT = "INSERT INTO public.product (name, price, description) " +
            "VALUES (?, ?, ?) ON CONFLICT DO NOTHING;";

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();

        try (Connection connect = getConnetion()) {
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery(GETALLPRODUCT);
            while (result.next()) {
                products.add(new Product(result.getLong("id"),
                        result.getString("name"), result.getDouble("price"),
                        result.getString("description")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            return products;
        }
    }

    @Override
    public boolean save(Product product) {
        int responce = 0;

        try (Connection connect = getConnetion()) {
            PreparedStatement preparedStatement = connect.prepareStatement(INSERTPRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getDescription());
            responce = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return responce != 0;
    }

    private Connection getConnetion() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, NAME, PASSWORD);
    }
}
