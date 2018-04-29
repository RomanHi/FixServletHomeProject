package ru.rikabc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.rikabc.models.UserFile;
import ru.rikabc.repositories.UserFileRepository;
import ru.rikabc.utility.UserInputHandler;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.*;

/**
 * @Author Roman Khayrullin on 27.04.2018
 * @Version 1.0
 */
@Service
public class ProfileRestServiceImpl implements ProfileRestService {
    @Autowired
    UserFileRepository repository;

    @Override
    public ResponseEntity<String> FindFile(Long id) {
        UserFile file = repository.findByFileId(id);
        if (file != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "text/plain");
            return new ResponseEntity<>(file.getFile(), headers, OK);
        }
        return new ResponseEntity<>("File not found", NOT_FOUND);
    }

    @Override
    public ResponseEntity<String> SaveFile(UserFile file, HttpServletRequest request) {
        String result = UserInputHandler.validateFile(file);

        if ("created".equals(result)) {
            HttpHeaders headers = new HttpHeaders();
            Long newFileId = repository.save(file).getFileId();
            headers.add("Location", "/file/" + newFileId);
            return new ResponseEntity<>(result, headers, CREATED);
        } else return new ResponseEntity<>(result, BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> updateFile(Long fileId, UserFile file, HttpServletRequest request) {
        UserFile fileCandidate = repository.findByFileId(fileId);
        String result = UserInputHandler.validateFile(file, fileCandidate);

        if ("created".equals(result)) {
            repository.save(file);
            return new ResponseEntity<>(result, OK);
        } else return new ResponseEntity<>(result, BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> deleteFile(Long fileId) {
        try {
            repository.delete(fileId);
            return new ResponseEntity<>("file deleted", OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
        }
    }
}
