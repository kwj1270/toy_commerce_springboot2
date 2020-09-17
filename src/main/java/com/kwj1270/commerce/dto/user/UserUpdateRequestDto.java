package com.kwj1270.commerce.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String name;
    private String password;
    private String picture;

    @Builder
    public UserUpdateRequestDto(String name, String password, String picture){
        this.name = name;
        this.password = password;
        this.picture = picture;
    }

}
