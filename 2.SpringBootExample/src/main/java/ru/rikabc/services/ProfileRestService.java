package ru.rikabc.services;

import org.springframework.http.ResponseEntity;
import ru.rikabc.models.UserFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Roman Khayrullin on 27.04.2018
 * @Version 1.0
 */
public interface ProfileRestService {
    ResponseEntity<String> FindFile(Long id);

    ResponseEntity<String> SaveFile(UserFile file, HttpServletRequest request);

    ResponseEntity<String> updateFile(Long fileId, UserFile file, HttpServletRequest request);

    ResponseEntity<String> deleteFile(Long fileId);
}
