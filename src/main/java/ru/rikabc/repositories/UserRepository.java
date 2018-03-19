package ru.rikabc.repositories;

import ru.rikabc.Models.User;

/**
 * @Author Roman Khayrullin on 18.03.2018
 * @Version 1.0
 */
public interface UserRepository {
    User findUserByUsername(String username);

    boolean save(User user);

    boolean isExist(User user, String password);
}
