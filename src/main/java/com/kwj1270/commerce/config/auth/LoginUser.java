package com.kwj1270.commerce.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 매개변수에서 사용될 인터페이스다.
@Retention(RetentionPolicy.RUNTIME) // 컴파일 이후에도 JVM에 의해 참조가능
// @Retention(RetentionPolicy.CLASS) // 컴파일러가 클래스를 참조할 때까지 유효합니다.
// @Retention(RetentionPolicy.SOURCE) // 어노테이션 정보는 컴파일 이후 없어집니다.
// 이렇게 사용하는 이유는 Session 관련 어노테이션이므로 컴파일(java->class) 이후로도 계속 유지되어야 하니까
public @interface LoginUser {
}
