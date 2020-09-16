package com.kwj1270.commerce.controller;

import com.kwj1270.commerce.config.auth.LoginUser;
import com.kwj1270.commerce.config.auth.dto.SessionUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class UserController {

    @GetMapping("/user")
    public String login(Model model, @LoginUser SessionUser user) {
        if(user!= null) return "main";
        else return "login";
    }

}
