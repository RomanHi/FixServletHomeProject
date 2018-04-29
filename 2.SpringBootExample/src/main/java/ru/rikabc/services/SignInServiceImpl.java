package ru.rikabc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.rikabc.models.User;
import ru.rikabc.repositories.UserRepository;

/**
 * @Author Roman Khayrullin on 27.04.2018
 * @Version 1.0
 */
@Service
public class SignInServiceImpl implements SignInService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public boolean SignInUser(User user) {
        User userFromDb = repository.findByUsername(user.getUsername());
        if (userFromDb == null) return false;
        user.setId(userFromDb.getId());
        return encoder.matches(user.getPassword(), userFromDb.getPassword());

    }
}
