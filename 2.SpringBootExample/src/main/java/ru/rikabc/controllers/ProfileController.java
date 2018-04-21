package ru.rikabc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.rikabc.models.UserFile;
import ru.rikabc.repositories.UserFileRepository;
import ru.rikabc.utility.UserInputHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @Author Roman Khayrullin on 19.04.2018
 * @Version 1.0
 */
@Controller
@RequestMapping("/profile/{userId}")
public class ProfileController {
    @Autowired
    private UserFileRepository repository;

    @RequestMapping(method = GET)
    public String profileGetPage(@PathVariable Long userId, Model model) {
        List<UserFile> files = repository.findAllByUserId(userId);
        model.addAttribute("files", files);
        return "profile";
    }

    @RequestMapping(value = "/file/{fileId}", method = GET)
    @ResponseBody
    public ResponseEntity<String> getFile(@PathVariable Long fileId) {
        UserFile file = repository.getUserFileByFileId(fileId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/plain");
        return new ResponseEntity<>(file.getFile(), headers, OK);

    }

    @RequestMapping(value = "/file/create", method = POST)
    @ResponseBody
    public ResponseEntity<String> createFile(@ModelAttribute UserFile file, HttpServletRequest request) {
        Long id = (Long) request.getSession().getAttribute("userId");
        String result = UserInputHandler.validateFile(file, id);

        if ("created".equals(result)) {
            HttpHeaders headers = new HttpHeaders();
            Long newFileId = repository.save(file).getFileId();
            headers.add("Location", "/file/" + newFileId);
            return new ResponseEntity<>(result, headers, CREATED);
        } else return new ResponseEntity<>(result, BAD_REQUEST);
    }

    @RequestMapping(value = "/file/{fileId}", method = PUT)
    @ResponseBody
    public ResponseEntity<String> updateFile(@PathVariable Long fileId, @ModelAttribute UserFile file,
                                             HttpServletRequest request) {
        UserFile currentFile = repository.getUserFileByFileId(fileId);
        Long id = (Long) request.getSession().getAttribute("userId");
        String result = UserInputHandler.validateFile(file,currentFile, id);

        if ("created".equals(result)) {
            repository.save(file);
            return new ResponseEntity<>(result, OK);
        } else return new ResponseEntity<>(result, BAD_REQUEST);
    }

    @RequestMapping(value = "/file/{fileId}", method = DELETE)
    @ResponseBody
    public  ResponseEntity<String> deleteFileWithDeleteMethod(@PathVariable Long fileId) {
        try {
            repository.delete(fileId);
            return new ResponseEntity<>("file deleted",OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(e.getMessage(),BAD_REQUEST);

        }
    }

    @RequestMapping(value = "/delete/{fileId}", method = GET)
    public String deleteFileWithGetMethod(@PathVariable Long fileId, HttpServletRequest request) {
        try {
            repository.delete(fileId);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return "redirect:/profile/" + request.getSession().getAttribute("userId");
    }
}
