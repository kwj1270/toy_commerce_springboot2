package com.kwj1270.commerce.controller;

import com.kwj1270.commerce.config.auth.LoginUser;
import com.kwj1270.commerce.config.auth.dto.SessionUser;
import com.kwj1270.commerce.domain.user.User;
import com.kwj1270.commerce.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@Controller
public class WebController {

    private final UserService userService;

    @RequestMapping("/login")
    public String login(@LoginUser SessionUser user) {
        return "login";
    }

    @RequestMapping("/main2")
    public String main2(@LoginUser SessionUser user) {
        return "main2";
    }

    /* security가 알아서 해줄거다.
    @GetMapping("/loginProcess")
    public String loginProcess(@LoginUser SessionUser user, String id, String password) {
        if(user != null) return "main";
        userService.login(id, password);
        return "main";
    }
    */

    /*
    @GetMapping("/main")
    public String main(Model model, @LoginUser SessionUser user) {
        if(user != null) return "main";
        return "redirect:login";
    }
    */
}
@RestController
class SecurityController {

    @GetMapping("/username")
    public String currentUserName() {
        if(hasUserRole()) return "true";
        return "false";
    }

    public boolean hasUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority>  authorities = authentication.getAuthorities();
        return authorities.stream().filter(o -> o.getAuthority().equals("ROLE_USER")).findAny().isPresent();
    }
}

