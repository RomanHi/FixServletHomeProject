package ru.rikabc.services;

import ru.rikabc.models.User;

/**
 * @Author Roman Khayrullin on 26.04.2018
 * @Version 1.0
 */
public interface SignUpService {
    boolean signUpUser(User user);
}
