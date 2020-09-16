package com.kwj1270.commerce.controller;

import com.kwj1270.commerce.config.auth.LoginUser;
import com.kwj1270.commerce.config.auth.dto.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model, @LoginUser SessionUser user) {
        if(user != null) return "main";
        else return "login";
    }

    @GetMapping("/main")
    public String main(Model model, @LoginUser SessionUser user) {
        if(user != null) return "main";
        else return "login";
    }
}
