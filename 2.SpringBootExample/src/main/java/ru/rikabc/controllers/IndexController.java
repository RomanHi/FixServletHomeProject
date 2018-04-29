package ru.rikabc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.rikabc.services.SignInService;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @Author Roman Khayrullin on 18.04.2018
 * @Version 1.0
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    @Autowired
    SignInService service;

    @RequestMapping(method = GET)
    public String indexGetPage(HttpServletRequest request, Model model) {
        if (request.getParameterMap().containsKey("error")) {
            model.addAttribute("message", "invalid username or password.");
        }
        if (request.getParameterMap().containsKey("logout")) {
            model.addAttribute("message", "you have been successful logout");
        }
        return "index";
    }
}
