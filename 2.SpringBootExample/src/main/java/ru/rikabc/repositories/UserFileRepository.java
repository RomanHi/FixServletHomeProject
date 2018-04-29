package ru.rikabc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rikabc.models.UserFile;

import java.util.List;

/**
 * @Author Roman Khayrullin on 19.04.2018
 * @Version 1.0
 */
public interface UserFileRepository extends JpaRepository<UserFile, Long> {
    List<UserFile> findAllByUserId(Long userId);

    UserFile findByFileId(Long fileId);
}
