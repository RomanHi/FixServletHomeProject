package ru.rikabc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rikabc.dto.UserInputResult;
import ru.rikabc.models.UserFile;
import ru.rikabc.services.ProfileRestService;

import static org.springframework.http.HttpStatus.*;
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

    @RequestMapping(value = "/file/{fileId}", method = GET)
    public ResponseEntity<String> getFile(@PathVariable("fileId") Long fileId) {
        try {
            UserFile file = service.findFile(fileId);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "text/plain");
            return new ResponseEntity<>(file.getFile(), headers, OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>("File not found", NOT_FOUND);
        }
    }


    @RequestMapping(value = "/file/create", method = POST)
    public ResponseEntity<String> createFile(@PathVariable("userId") Long userId,
                                             @RequestBody UserFile concreteFile) {
        concreteFile.setUserId(userId);
        UserInputResult result = service.saveFile(concreteFile);
        if (result.isValid()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/file/" + concreteFile.getFileId());
            return new ResponseEntity<>(result.getMessage(), headers, CREATED);

        }
        return new ResponseEntity<>(result.getMessage(), BAD_REQUEST);
    }


    @RequestMapping(value = "/file/{fileId}", method = PUT)
    public ResponseEntity<String> updateFile(@PathVariable Long fileId,
                                             @PathVariable("userId") Long userId, @RequestBody UserFile file) {
        file.setUserId(userId);
        UserInputResult result = service.updateFile(fileId, file);
        if (result.isValid()) {
            return new ResponseEntity<>(result.getMessage(), OK);
        } else return new ResponseEntity<>(result.getMessage(), BAD_REQUEST);
    }

    @RequestMapping(value = "/file/{fileId}", method = DELETE)
    public ResponseEntity<String> deleteFile(@PathVariable Long fileId) {
        try {
            service.deleteFile(fileId);
            return new ResponseEntity<>("file deleted", OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
        }
    }
}
