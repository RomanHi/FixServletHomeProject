package ru.rikabc.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author Roman Khayrullin on 18.03.2018
 * @Version 1.0
 */
@Entity
@Table(name = "fix_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long id;
    private String username;
    private String password;
    private String role;
}
