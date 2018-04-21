package ru.rikabc.controllers;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @Author Roman Khayrullin on 18.04.2018
 * @Version 1.0
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = GET)
    public String indexGetPage(Model model) {
        return "index";
    }

    @RequestMapping(method = POST)
    public String indexPostPage(User user, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userIsExist(userFromDb,user.getPassword())) {
            Long id = userFromDb.getId();
            request.getSession(true).setAttribute("userId", id);
            return "redirect:/profile/" + id;
        } else {
            redirectAttributes.addFlashAttribute("message", "invalid username or password.");
            return "redirect:/index";
        }
    }

    private boolean userIsExist(User user,String password) {
        if (user == null) return false;
        return BCrypt.checkpw(password, user.getPassword());
    }
}
