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

        // 요청할때 보낸 registrationId 값 가져오
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        // 사용자의 name은 key:value로 되어있는데 key값은 우리가 정한 값이 아니라 이를 구하는 작업을 처리를 해준다.
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        // 알마는 SNS를 통해 저장하거나 수정될 OAuth2 유저 데이터 객체를 얻어온다.
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        // 저장 or 수정후 해당 User 값을 얻어서 객체로 만든다.
        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user)); // session에 등록

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    // Optional 클래스를 이용한 null 유무에 따른 처리
    // Optional<User> 이다
    private User saveOrUpdate(OAuthAttributes attributes){
        User user = userRepository.findByEmail(attributes.getEmail()) // 해당 이메일을 가진 User 검색
                // 기존 유저 데이터가 있었을 경우 update 메서드 실행 -> 해당 값을 넣어준다.
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity()); // 없었을 경우 해당 속성 객체로 User 객체 생성
        return userRepository.save(user); // 저장
    }
}
