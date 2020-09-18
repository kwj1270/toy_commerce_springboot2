package com.kwj1270.commerce.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String name;
    private String id;
    private String password;
    private String picture;

    @Builder
    public UserUpdateRequestDto(String name, String id, String password, String picture){
        this.name = name;
        this.id = id;
        this.password = password;
        this.picture = picture;
    }

}
