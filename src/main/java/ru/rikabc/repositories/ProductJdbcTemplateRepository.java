package ru.rikabc.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.rikabc.Models.Product;
import ru.rikabc.utility.ProductMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @Author Roman Khayrullin on 07.04.2018
 * @Version 1.0
 */
public class ProductJdbcTemplateRepository implements ProductRepository {
    private static final String SELECT_ALL = "SELECT * FROM product";
    private static final String INSERT_DRODUCT = "INSERT INTO product (name, price, description)" +
            " VALUES (?,?,?) ON CONFLICT DO NOTHING";
    private static final String DELETE_ROW = "DELETE FROM product WHERE id=?";
    private JdbcTemplate jdbcTemplate;

    public ProductJdbcTemplateRepository() {
        Properties properties = new Properties();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        InputStream stream = getClass().getClassLoader().getResourceAsStream("application.properties");

        try {
            properties.load(stream);
            dataSource.setDriverClassName(properties.getProperty("db.driver"));
            dataSource.setUrl(properties.getProperty("db.product.url"));
            dataSource.setUsername(properties.getProperty("db.username"));
            dataSource.setPassword(properties.getProperty("db.password"));
            jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query(SELECT_ALL, new ProductMapper());
    }

    @Override
    public boolean save(Product product) {
        return jdbcTemplate.update(INSERT_DRODUCT, product.getName(), product.getPrice(), product.getDescription()) != 0;
    }

    @Override
    public boolean delete(Integer id) {
        return jdbcTemplate.update(DELETE_ROW, id) != 0;
    }

}
