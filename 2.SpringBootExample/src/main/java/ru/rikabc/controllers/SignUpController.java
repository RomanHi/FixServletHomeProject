package ru.rikabc.controllers;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.rikabc.models.User;
import ru.rikabc.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @Author Roman Khayrullin on 20.04.2018
 * @Version 1.0
 */
@Controller
@RequestMapping("/signUp")
public class SignUpController {
    private static final int ROUNDS = 12;
    @Autowired
    UserRepository repository;

    @RequestMapping(method = GET)
    public String signUpGetMethod(Model model) {
        return "signUp";
    }

    @RequestMapping(method = POST)
    public String signUpPostMethod(User user, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        ParseUserForSaveInDb(user);
        try {
            Long id = repository.save(user).getId();
            request.getSession(true).setAttribute("userId", id);
            return "redirect:/profile/" + id;
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("message", "user with the same username already exist");
            return "redirect:/signUp";
        }
    }

    public static void ParseUserForSaveInDb(User user) {
        String hashPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(ROUNDS));
        user.setPassword(hashPassword);
        user.setRole("USER");

    }
}
