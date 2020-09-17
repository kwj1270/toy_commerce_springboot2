package com.kwj1270.commerce.domain.user;

import com.kwj1270.commerce.domain.BaseTimeEntity;
import com.kwj1270.commerce.domain.enums.SocialType;
import com.kwj1270.commerce.dto.user.UserUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Builder
    public User(String name, String password, String email, String picture, Role role, SocialType socialType){
        this.name = name;
        this.password = password;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.socialType = socialType;
    }

    public User update(String name, String picture){
        this.name = name;
        this.picture = picture;
        return this;
    }

    public User update(String name, String password, String picture){
        this.name = name;
        this.password = password;
        this.picture = picture;
        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

}
