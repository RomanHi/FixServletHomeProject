package ru.rikabc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @Author Roman Khayrullin on 18.04.2018
 * @Version 1.0
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/index", method = GET)
    public String indexPage() {
        return "index";
    }
}
