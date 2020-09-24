package com.kwj1270.commerce.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String name;
    private String userId;
    private String password;
    private String picture;

    @Builder
    public UserUpdateRequestDto(String name, String userId, String password, String picture){
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.picture = picture;
    }

}
