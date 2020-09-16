package com.kwj1270.commerce.controller;

import com.kwj1270.commerce.config.auth.LoginUser;
import com.kwj1270.commerce.config.auth.dto.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PayController {
    @GetMapping("/payment")
    public String payment() {
        return "payment";
    }

}
