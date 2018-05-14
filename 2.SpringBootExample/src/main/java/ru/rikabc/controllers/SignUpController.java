package ru.rikabc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.rikabc.models.User;
import ru.rikabc.services.SignUpService;

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
    @Autowired
    SignUpService signUpService;

    @RequestMapping(method = GET)
    public String signUpGetMethod(Model model) {
        return "signUp";
    }

    @RequestMapping(method = POST)
    public String signUpPostMethod(User user, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        if (signUpService.signUpUser(user)) {
            request.getSession(true).setAttribute("userId", user.getId());
            return "redirect:/profile/" + user.getId();
        } else {
            redirectAttributes.addFlashAttribute("message"
                    , "user with the same username already exist");
            return "redirect:/signUp";
        }
    }

}
