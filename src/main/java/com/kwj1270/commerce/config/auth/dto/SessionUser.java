package com.kwj1270.commerce.config.auth.dto;

import com.kwj1270.commerce.domain.user.enums.SocialType;
import com.kwj1270.commerce.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

// 게시판, 댓글 아이디랑 동일한지 비교 만들어야함
@Getter
public class SessionUser implements Serializable {

    private String name; // 이름
    private String userId; // 이름
    private String email; // 이메일
    private String picture; // 사진
    private SocialType socialType; // 소셜 타입

    public SessionUser(User user){
        this.name = user.getName();
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.picture = user.getPicture();
        this.socialType = user.getSocialType();
    }

    public boolean isLogin() {
        return this != null ? true : false;
    }
}