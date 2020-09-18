package com.kwj1270.commerce.controller;

import com.kwj1270.commerce.config.auth.LoginUser;
import com.kwj1270.commerce.config.auth.dto.SessionUser;
import com.kwj1270.commerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(@LoginUser SessionUser user) {
        if(user != null) return "main";
        return "login";
    }

    @GetMapping("/loginProcess")
    public String loginProcess(@LoginUser SessionUser user, String id, String password) {
        if(user != null) return "main";
        userService.login(id, password);
        return "main";
    }

    @GetMapping("/main")
    public String main(Model model, @LoginUser SessionUser user) {
        if(user != null) return "main";
        return "login";
    }

}
