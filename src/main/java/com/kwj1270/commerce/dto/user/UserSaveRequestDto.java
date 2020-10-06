package com.kwj1270.commerce.dto.user;

import com.kwj1270.commerce.domain.user.enums.SocialType;
import com.kwj1270.commerce.domain.user.enums.UserStatus;
import com.kwj1270.commerce.domain.user.enums.Role;
import com.kwj1270.commerce.domain.user.User;
import lombok.Builder;

public class UserSaveRequestDto {
    private String name;
    private String userId;
    private String password;
    private String email;
    private String picture;
    private final Role role = Role.USER;
    private final SocialType socialType = SocialType.NORMAL;
    private final UserStatus userStatus = UserStatus.ACTIVE;

    @Builder
    public UserSaveRequestDto(String name, String userId, String password, String email, String picture){
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.picture = picture;
    }

    public User toEntity(){
        return User.builder()
                .name(name)
                .userId(userId)
                .password(password)
                .email(email)
                .picture(picture)
                .role(role)
                .socialType(socialType)
                .userStatus(userStatus)
                .build();
    }
}
