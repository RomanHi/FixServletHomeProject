package ru.rikabc.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Roman Khayrullin on 17.03.2018
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private long id;
    private String name;
    private double price;
    private String description;

}
