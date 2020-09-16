package com.kwj1270.commerce.config.auth;

import com.kwj1270.commerce.config.auth.dto.OAuthAttributes;
import com.kwj1270.commerce.config.auth.dto.SessionUser;
import com.kwj1270.commerce.domain.user.User;
import com.kwj1270.commerce.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor // final 인스턴스 변수를 위한 생성자 추가.
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository; // User dao
    private final HttpSession httpSession; // java session

    @Override // http.oauth2Login().userService(customOAuth2UserService)시 실행될 메서드
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // http.oauth2Login().userInfoEndpoint() 로 인해
        // SpringContainer 에는 OAuth2 설정 관련 인스턴스들이 생성됨 -> OAuth2UserRequest userRequest
        OAuth2UserService delegate = new DefaultOAuth2UserService(); // 일반적인 userService 생성
        // 일반적인 UserService 의 loadUser 사용하여 OAuth2User 즉, Oauth2 유저 정보를 가져옴
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes){
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}
