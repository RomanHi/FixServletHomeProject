package ru.rikabc.services;

import ru.rikabc.models.User;

/**
 * @Author Roman Khayrullin on 27.04.2018
 * @Version 1.0
 */
public interface SignInService {
    boolean SignInUser(User user);
}
