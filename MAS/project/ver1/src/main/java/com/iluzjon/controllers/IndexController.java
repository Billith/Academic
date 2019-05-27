package com.iluzjon.controllers;

import com.iluzjon.entities.Client;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

@Controller
public class IndexController {

    @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping("/repertoire")
    public String repertoire() {
        return "repertoire";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(WebRequest request, Model model) {
        Client client = new Client();
        model.addAttribute("user", client);
        return "register";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/user")
    public String user() {
        return "user";
    }

    @RequestMapping("/tickets")
    public String tickets() {
        return "tickets";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }

}