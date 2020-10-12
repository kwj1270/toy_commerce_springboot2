package com.kwj1270.commerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 뷰 연결만 사용할 때 사용한다.
// 즉 모델값을 넣고자 하는 URL 이 있다면 Controller 에서 따로 처리해주자
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/main").setViewName("main");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/signup").setViewName("signup");

        // 제품 뷰
        registry.addViewController("/mac").setViewName("mac");
        registry.addViewController("/ipad").setViewName("ipad");
        registry.addViewController("/iphone").setViewName("iphone");
        registry.addViewController("/watch").setViewName("watch");
        registry.addViewController("/music").setViewName("music");
    }
}
