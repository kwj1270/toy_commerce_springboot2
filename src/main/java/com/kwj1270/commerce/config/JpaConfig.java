package com.kwj1270.commerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration // bean 등록 및 @Bean 사용시 리턴 객체 싱글톤 보장
@EnableJpaAuditing // jpa 실행시 감시 로그 남김
public class JpaConfig {
}
