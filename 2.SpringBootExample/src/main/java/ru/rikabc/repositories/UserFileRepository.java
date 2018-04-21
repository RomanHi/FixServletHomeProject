package ru.rikabc.repositories;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.rikabc.models.UserFile;

import java.util.List;

/**
 * @Author Roman Khayrullin on 19.04.2018
 * @Version 1.0
 */
public interface UserFileRepository extends JpaRepository<UserFile,Long>{
    List<UserFile> findAllByUserId(Long userId);

    UserFile getUserFileByFileId(Long fileId);
}
