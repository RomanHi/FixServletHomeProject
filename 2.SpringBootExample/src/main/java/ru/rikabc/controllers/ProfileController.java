package ru.rikabc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.rikabc.models.UserFile;
import ru.rikabc.services.ProfileService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @Author Roman Khayrullin on 19.04.2018
 * @Version 1.0
 */
@Controller
@RequestMapping("/profile/{userId}")
public class ProfileController {
    @Autowired
    private ProfileService service;

    @RequestMapping(method = GET)
    public String profileGetPage(@PathVariable("userId") Long userId, Model model, HttpServletRequest request) {
        List<UserFile> files = service.getAllUserFiles(userId);
        Cookie[] cookies = request.getCookies();
        String token = "";
        for (Cookie c : cookies) {
            if (c.getName().equals("Authorization")) {
                token = c.getValue();
            }
        }
        model.addAttribute("files", files);
        model.addAttribute("token", token);
        return "profile";
    }
}
