package com.kwj1270.commerce;

import com.kwj1270.commerce.config.auth.LoginUserArgumentResolver;
import com.kwj1270.commerce.domain.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

import java.util.List;

@SpringBootApplication // @SpringBootApplication 있는 클래스가 가장 최상단 디렉토리에 위치해야 한다.
public class RunApplication {
    public static void main(String[] args) { SpringApplication.run(RunApplication.class,args); }

    @Autowired
    private LoginUserArgumentResolver LoginUserArgumentResolver;

    @Bean
    public CommandLineRunner runner(OrderRepository orderRepository)
            throws Exception{
        return (args) -> {

        };
    }

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
        argumentResolvers.add(LoginUserArgumentResolver);
    }

}