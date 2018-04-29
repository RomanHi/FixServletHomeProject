package ru.rikabc.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.rikabc.models.User;
import ru.rikabc.repositories.UserRepository;

/**
 * @Author Roman Khayrullin on 28.04.2018
 * @Version 1.0
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found");
        return new UserDetailsImpl(user);
    }
}
