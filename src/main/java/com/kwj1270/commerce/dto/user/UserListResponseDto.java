package com.kwj1270.commerce.dto.user;

import com.kwj1270.commerce.domain.enums.SocialType;
import com.kwj1270.commerce.domain.enums.UserStatus;
import com.kwj1270.commerce.domain.user.Role;
import com.kwj1270.commerce.domain.user.User;

import java.time.LocalDateTime;

public class UserListResponseDto {
    private String name;
    private String id;
    private String email;
    private String picture;
    private Role role;
    private SocialType socialType;
    private UserStatus userStatus;
    private LocalDateTime modifiedDate;

    public UserListResponseDto(User user){
        this.name = user.getName();
        this.id = user.getId();
        this.email = user.getEmail();
        this.picture = user.getPicture();
        this.role = user.getRole();
        this.socialType = user.getSocialType();
        this.userStatus = user.getUserStatus();
        this.modifiedDate = user.getModifiedDate();
    }

}
