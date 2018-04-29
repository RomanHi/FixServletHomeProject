package ru.rikabc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.rikabc.models.UserFile;
import ru.rikabc.repositories.UserFileRepository;

import java.util.List;

/**
 * @Author Roman Khayrullin on 27.04.2018
 * @Version 1.0
 */
@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private UserFileRepository repository;

    @Override
    public List<UserFile> getAllFiles(Long userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public void deleteFile(Long id) {
        try {
            repository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
    }
}
