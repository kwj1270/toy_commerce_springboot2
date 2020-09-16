package com.kwj1270.commerce.config.auth.dto;

import com.kwj1270.commerce.domain.enums.SocialType;
import com.kwj1270.commerce.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private String name; // 이름
    private String email; // 이메일
    private String picture; // 사진
    private SocialType socialType; // 소셜 타입

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
        this.socialType = user.getSocialType();
    }

    public boolean isLogin() {
        return this != null ? true : false;
    }
}