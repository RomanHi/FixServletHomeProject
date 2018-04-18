package ru.rikabc.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.rikabc.Models.Product;
import ru.rikabc.utility.ProductMapper;

import javax.sql.DataSource;
import java.util.List;

/**
 * @Author Roman Khayrullin on 07.04.2018
 * @Version 1.0
 */
public class ProductJdbcTemplateRepository implements ProductRepository {
    private static final String SELECT_ALL = "SELECT * FROM product";
    private static final String INSERT_PRODUCT = "INSERT INTO product (name, price, description)" +
            " VALUES (?,?,?) ON CONFLICT DO NOTHING";
    private static final String DELETE_PRODUCT = "DELETE FROM product WHERE id=?";
    private JdbcTemplate jdbcTemplate;

    public ProductJdbcTemplateRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query(SELECT_ALL, new ProductMapper());
    }

    @Override
    public boolean save(Product product) {
        return jdbcTemplate.update(INSERT_PRODUCT, product.getName(), product.getPrice(), product.getDescription()) != 0;
    }

    @Override
    public boolean delete(Integer id) {
        return jdbcTemplate.update(DELETE_PRODUCT, id) != 0;
    }

}
