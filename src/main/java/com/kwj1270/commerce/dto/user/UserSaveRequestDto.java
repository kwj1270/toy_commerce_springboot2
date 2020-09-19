package com.kwj1270.commerce.dto.user;

import com.kwj1270.commerce.domain.enums.SocialType;
import com.kwj1270.commerce.domain.enums.UserStatus;
import com.kwj1270.commerce.domain.user.Role;
import com.kwj1270.commerce.domain.user.User;
import lombok.Builder;

public class UserSaveRequestDto {
    private String name;
    private String id;
    private String password;
    private String email;
    private String picture;
    private final Role role = Role.GUEST;
    private final SocialType socialType = SocialType.NORMAL;
    private final UserStatus userStatus = UserStatus.ACTIVE;

    @Builder
    public UserSaveRequestDto(String name, String id, String password, String email, String picture){
        this.name = name;
        this.id = id;
        this.password = password;
        this.email = email;
        this.picture = picture;
    }

    public User toEntity(){
        return User.builder()
                .name(name)
                .id(id)
                .password(password)
                .email(email)
                .picture(picture)
                .role(role)
                .socialType(socialType)
                .userStatus(userStatus)
                .build();
    }
}
