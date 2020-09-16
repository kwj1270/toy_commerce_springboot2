package com.kwj1270.commerce.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;


@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();

        http
                .addFilterBefore(filter, CsrfFilter.class).csrf().disable().headers().frameOptions().disable();

                /*
                .csrf().disable() -> 사이트간 위조 요청 금지 -> 개발 편의상 허용하는 개발자를 위해서 존재하는 메서드
                .headers().frameOptions().disable() -> X-frame-options 헤더 비활성화
                X-frame-options 헤더는 클릭재킹 어택 -> 즉, 버튼 클릭시 기존 버튼이 아닌 다른 요소를 클릭한 이벤트를 실행하게 하는 공격 수법
                */

        http    .authorizeRequests()
                    .antMatchers("/", "/oauth2/**", "/payment/**", "/login/**", "/css/**", "/images/**", "/js/**", "/console/**", "/h2-console/**", "/profile").permitAll()
                    .antMatchers("/main/**").permitAll()
                    .anyRequest().authenticated();

        http
                .exceptionHandling() // 예외사항을 설정
                    .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));
                    // 인증 실패시 해당 url을 실행하도록 합니다.

        http
                .logout() // logout 관련된 설정을 진행 알림 구문 -> 이후는 logout 진행하겠다.
                    .logoutUrl("/logout") // 로그아웃을 진행할 url 설정
                    .logoutSuccessUrl("/login") // 로그아웃 성공시 이동 페이지
                    .deleteCookies("JSESSIONID") // 로그아웃시 쿠키 지움 -> 세션 ID 쿠키 지움
                    .invalidateHttpSession(true); // 로그아웃시 인증정보 지우고 세션 종료

                /*
                .logoutSuccessHandler()
                logoutSuccessUrl 말고 LogoutSuccessHandler 인터페이스를 구현한
                다른 헨들러 사용 정함
                */

        http
                .oauth2Login() // oauth2 로그인 설정 진행 알림 구문 -> 이후는 oauth2 로그인설정이다.
                    .loginPage("/login") // 로그인 페이지 지정
                    .loginProcessingUrl("/login") // 로그인이 수행될 url 지정
                    .defaultSuccessUrl("/") // 로그인 성공후 이동 url 지정
                    .failureUrl("/login") // 로그인 실패시 이동 url 지정
                    .userInfoEndpoint() // OAuth2 로그인 성공 후 사용자 정보를 가져올 때의 설정들을 담당합니다.
                    .userService(customOAuth2UserService); // 가져온 oauth2 user 정보를 활용하는 service 삽입
                    // 해당 service 의 loadUser() 메서드를 실행시킨다.
    }
}