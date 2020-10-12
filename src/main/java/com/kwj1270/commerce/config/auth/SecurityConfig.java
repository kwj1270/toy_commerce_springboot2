package com.kwj1270.commerce.config.auth;

import com.kwj1270.commerce.domain.user.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public PasswordEncoder passwordEncoder() { // 4
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/resources/**")
                .antMatchers("/css/**")
                .antMatchers("/vendor/**")
                .antMatchers("/js/**")
                .antMatchers("/favicon*/**")
                .antMatchers("/h2-console/**")
                .antMatchers("/images/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();

        /*
        * .csrf().disable() -> 사이트간 위조 요청 금지 -> 개발 편의상 허용하는 개발자를 위해서 존재하는 메서드
        * .headers().frameOptions().disable() -> X-frame-options 헤더 비활성화
        * X-frame-options 헤더는 클릭재킹 어택 -> 즉, 버튼 클릭시 기존 버튼이 아닌 다른 요소를 클릭한 이벤트를 실행하게 하는 공격 수법
        * */
        http
                .addFilterBefore(filter, CsrfFilter.class).csrf().disable().headers().frameOptions().disable();

        /*
        * .antMatchers("url주소").permitAll() 해당 주소 허용
        * .antMatchers("url주소").hasRole(Role.USER.name()) GUEST, USER, ADMIN 검사하여 사용
        * .anyRequest().authenticated(); 그외에는 비허용
        * */
        http
                .authorizeRequests()
                    .antMatchers("/", "/index/**","/login/**", "/signup/**").permitAll()
                    .antMatchers("/mac/**", "/ipad/**","/iphone/**", "/watch/**", "/music/**").permitAll()
                    .antMatchers("/main/**").hasAnyRole(Role.GUEST.name(), Role.USER.name(), Role.ADMIN.name())
                    .antMatchers("/pay/**").hasAnyRole(Role.GUEST.name(), Role.USER.name(), Role.ADMIN.name())
                    .antMatchers("/admin").hasRole(Role.ADMIN.name())
                    .anyRequest().authenticated();

        /*
        * 인증 실패시 로그인 url을 실행하도록 합니다.
        * */
        http
                .exceptionHandling()
                    .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));
        /*
        * OAuth2 로그인 진행
        * 로그인 페이지 지정
        * 로그인 성공시 forward url 지정
        * 로그인 실패시 forward url 지정
        * 유저 정보 가져오기 -> endPoint 는 마지막 정점으로 모든 계산이 끝난이후 결과 리턴
        * 사용자가 정의한 userService 실행, 해당 service 의 loadUser() 메서드를 실행시킨다.
        * */
        http
                .oauth2Login()
                .loginPage("/login")
                .defaultSuccessUrl("/main",true)
                .failureUrl("/login")
                .userInfoEndpoint()
                .userService(customOAuth2UserService);

        /*
        * 로그아웃 진행
        * 세션 쿠키인 JSESSIONID 삭제
        * httpSession 비우기
        * */
        http
                .logout()
                    .logoutSuccessUrl("/login")
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true);
    }

}