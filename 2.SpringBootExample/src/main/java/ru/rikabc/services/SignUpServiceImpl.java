package ru.rikabc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.rikabc.models.Role;
import ru.rikabc.models.Status;
import ru.rikabc.models.User;
import ru.rikabc.repositories.UserRepository;

/**
 * @Author Roman Khayrullin on 26.04.2018
 * @Version 1.0
 */
@Service
public class SignUpServiceImpl implements SignUpService {
    @Autowired
    private UserRepository repository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public boolean SignUpUser(User user) {
        ParseUserForSaveInDb(user);
        try {
            user.setId(repository.save(user).getId());
            return true;
        } catch (DataIntegrityViolationException e) {
            return false;
        }
    }

    private void ParseUserForSaveInDb(User user) {
        String hashPassword = encoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);

    }
}
