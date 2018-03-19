package ru.rikabc.repositories;

import ru.rikabc.Models.Product;

import java.util.List;

/**
 * @Author Roman Khayrullin on 17.03.2018
 * @Version 1.0
 */
public interface ProductRepository {
    List<Product> findAll();

    boolean save(Product product);
}
