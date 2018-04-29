package ru.rikabc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rikabc.models.UserFile;
import ru.rikabc.repositories.UserFileRepository;
import ru.rikabc.services.ProfileRestService;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @Author Roman Khayrullin on 26.04.2018
 * @Version 1.0
 */

@RestController
@RequestMapping("/profile/{userId}")
public class ProfileRestController {
    @Autowired
    ProfileRestService service;

    @Autowired
    UserFileRepository repository;

    @RequestMapping(value = "/file/{fileId}", method = GET)
    public ResponseEntity<String> getFile(@PathVariable("fileId") Long fileId) {
        return service.FindFile(fileId);
    }

    @RequestMapping(value = "/file/create", method = POST)
    public ResponseEntity<String> createFile(@PathVariable("userId") Long userId,
                                             @RequestBody UserFile ConcreteFile, HttpServletRequest request) {
        ConcreteFile.setUserId(userId);
        return service.SaveFile(ConcreteFile, request);
    }

    @RequestMapping(value = "/file/{fileId}", method = PUT)
    public ResponseEntity<String> updateFile(@PathVariable Long fileId,
                                             @PathVariable("userId") Long userId, @RequestBody UserFile file,
                                             HttpServletRequest request) {
        file.setUserId(userId);
        return service.updateFile(fileId, file, request);
    }

    @RequestMapping(value = "/file/{fileId}", method = DELETE)
    public ResponseEntity<String> deleteFile(@PathVariable Long fileId) {
        return service.deleteFile(fileId);
    }
}
