package com.kwj1270.commerce.config;

import com.kwj1270.commerce.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor // final 인스턴스 변수 초기화 생성자 사용
@Configuration // bean 등록 및 @Bean 사용시 리턴 객체 싱글톤 보장
public class WebConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver loginUserArgumentResolver; // 리졸버 가져옴

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
        argumentResolvers.add(loginUserArgumentResolver); // 리졸버 등록
    }
}
