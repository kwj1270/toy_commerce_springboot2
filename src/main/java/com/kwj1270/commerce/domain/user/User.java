package com.kwj1270.commerce.domain.user;

import com.kwj1270.commerce.domain.BaseTimeEntity;
import com.kwj1270.commerce.domain.enums.SocialType;
import com.kwj1270.commerce.domain.enums.UserStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity{

    @Id
    @Column(name = "USER_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "USER_NAME" , nullable = false)
    private String name;

    @Column(name = "USER_ID", unique = true)
    private String id;

    @Column(name = "USER_PASSWORD")
    private String password;

    @Column(name = "USER_EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "USER_PICTURE")
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_ROLE" ,nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_SOCIAL_TYPE")
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_STATUS")
    private UserStatus userStatus;

    @Builder
    public User(String name, String id,String password, String email, String picture, Role role, SocialType socialType, UserStatus userStatus){
        this.name = name;
        this.id = id;
        this.password = password;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.socialType = socialType;
        this.userStatus = userStatus;
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

    public User setInactive() {
        userStatus = UserStatus.INACTIVE;
        return this;
    }

    public User setActive() {
        userStatus = UserStatus.ACTIVE;
        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

}
