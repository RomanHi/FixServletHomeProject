package ru.rikabc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rikabc.models.User;

/**
 * @Author Roman Khayrullin on 19.04.2018
 * @Version 1.0
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
