package ru.rikabc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rikabc.dto.UserInputResult;
import ru.rikabc.models.UserFile;
import ru.rikabc.repositories.UserFileRepository;
import ru.rikabc.utility.UserInputHandler;

/**
 * @Author Roman Khayrullin on 27.04.2018
 * @Version 1.0
 */
@Service
public class ProfileRestServiceImpl implements ProfileRestService {
    @Autowired
    UserFileRepository repository;

    public UserFile findFile(Long id) {
        UserFile file = repository.findByFileId(id);
        if (file == null) {
            throw new IllegalStateException();
        }
        return file;
    }

    @Override
    public UserInputResult saveFile(UserFile file) {
        UserInputResult result = UserInputHandler.validateFile(file);
        if (result.isValid()) {
            Long fileId = repository.save(file).getFileId();
            file.setFileId(fileId);
            return result;
        }
        return result;
    }

    @Override
    public UserInputResult updateFile(Long fileId, UserFile file) {
        UserFile fileCandidate = repository.findByFileId(fileId);
        UserInputResult result = UserInputHandler.validateFile(file, fileCandidate);

        if (result.isValid()) {
            repository.save(file);
            return result;
        }
        return result;
    }

    @Override
    public void deleteFile(Long fileId) {
        repository.delete(fileId);
    }
}
