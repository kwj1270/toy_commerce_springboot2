package com.kwj1270.commerce.dto.user;

import com.kwj1270.commerce.domain.enums.SocialType;
import com.kwj1270.commerce.domain.user.Role;
import com.kwj1270.commerce.domain.user.User;
import lombok.Builder;

public class UserSaveRequestDto {

    private String name;
    private String password;
    private String email;
    private String picture;
    private final Role role = Role.GUEST;
    private final SocialType socialType = SocialType.NORMAL;

    @Builder
    public UserSaveRequestDto(String name, String password, String email, String picture){
        this.name = name;
        this.password = password;
        this.email = email;
        this.picture = picture;
    }

    public User toEntity(){
        return User.builder()
                .name(name)
                .password(password)
                .email(email)
                .picture(picture)
                .socialType(socialType)
                .role(role)
                .build();
    }
}
