package ru.rikabc.services;

import ru.rikabc.dto.UserInputResult;
import ru.rikabc.models.UserFile;

/**
 * @Author Roman Khayrullin on 27.04.2018
 * @Version 1.0
 */
public interface ProfileRestService {
    UserFile findFile(Long id);

    UserInputResult saveFile(UserFile file);

    UserInputResult updateFile(Long fileId, UserFile file);

    void deleteFile(Long fileId);
}
