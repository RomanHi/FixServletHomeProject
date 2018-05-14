package ru.rikabc.services;

import ru.rikabc.models.UserFile;

import java.util.List;

/**
 * @Author Roman Khayrullin on 27.04.2018
 * @Version 1.0
 */
public interface ProfileService {
    List<UserFile> getAllUserFiles(Long userId);
}
