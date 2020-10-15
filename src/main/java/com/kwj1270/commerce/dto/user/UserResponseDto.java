package com.kwj1270.commerce.dto.user;

import com.kwj1270.commerce.domain.user.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private String name;
    private String userId;
    private String email;
    private String picture;
    private String role;
    private String userStatus;

    public UserResponseDto(User entity){
        name = entity.getName();
        userId = entity.getUserId();
        email = entity.getEmail();
        picture = entity.getPicture();
        role = entity.getRole().getTitle();
        userStatus = entity.getUserStatus().toString();
    }

}
