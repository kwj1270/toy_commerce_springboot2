package com.kwj1270.commerce.config.auth.dto;

import com.kwj1270.commerce.domain.user.enums.SocialType;
import com.kwj1270.commerce.domain.user.enums.Role;
import com.kwj1270.commerce.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import static com.kwj1270.commerce.domain.user.enums.SocialType.FACEBOOK;
import static com.kwj1270.commerce.domain.user.enums.SocialType.GOOGLE;
import static com.kwj1270.commerce.domain.user.enums.SocialType.NAVER;
import static com.kwj1270.commerce.domain.user.enums.SocialType.KAKAO;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey; // 사용자 이름은 key:value 형태로 되어있기에 이에 해당하는 키값을 구해야한다.
    private String name;
    private String email;
    private String picture;
    private SocialType socialType;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture, SocialType socialType){
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.socialType = socialType;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
        if("naver".equals(registrationId)){
            return ofNaver("id", attributes);
        } else if("kakao".equals(registrationId)){
            return ofKakao("id", attributes);
        } else if("facebook".equals(registrationId)){
            return ofFaceBook(userNameAttributeName, attributes);
        }
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes){
        return OAuthAttributes .builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .socialType(GOOGLE)
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofFaceBook(String userNameAttributeName, Map<String, Object> attributes){
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .socialType(FACEBOOK)
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes){
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .socialType(NAVER)
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String,Object> response = (Map<String, Object>)attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) response.get("profile");
        return OAuthAttributes.builder()
                .name((String)profile.get("nickname"))
                .email((String)response.get("email"))
                .picture((String)profile.get("profile_image_url"))
                .socialType(KAKAO)
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.USER)
                .socialType(socialType)
                .build();
    }
}