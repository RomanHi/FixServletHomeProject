package ru.rikabc.utility;

import org.springframework.jdbc.core.RowMapper;
import ru.rikabc.Models.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author Roman Khayrullin on 07.04.2018
 * @Version 1.0
 */
public class ProductMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet result, int i) throws SQLException {
        Product product = new Product();
        product.setId(result.getLong("id"));
        product.setName(result.getString("name"));
        product.setPrice(result.getDouble("price"));
        product.setDescription(result.getString("description"));
        return product;
    }
}
