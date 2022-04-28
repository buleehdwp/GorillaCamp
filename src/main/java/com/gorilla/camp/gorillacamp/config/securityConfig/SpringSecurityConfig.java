package com.gorilla.camp.gorillacamp.config.securityConfig;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    // 비밀번호 암호화를 위한 서비스 호출

    // 정적 자원에 대해서는 Security 설정을 적용하지 않음.
    @Override
    public void configure(WebSecurity web) {
        // 해당 경로의 파일들은 security가 무시되도록합니다.
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf().disable() // csrf를 사용할 지 여부
                .authorizeRequests() // HttpServletRequest에 따라 접근 제한
                // view 요청
                .antMatchers("/*_admin").authenticated() // 인증필요
                .antMatchers("/*_view").permitAll() // 허용
                //api 요청
                .antMatchers("/api/v1/m/*").authenticated() // 인증필요
                .antMatchers("/api/v1/v/*").permitAll() // 허용
                .antMatchers("/css/**").permitAll()
                .and()
                .formLogin() // form 기반 로그인 인증 관련하여 HttpSession 이용
                .loginPage("/gorilla/login/login_view") // 지정하고 싶은 로그인 페이지 url
                .defaultSuccessUrl("/gorilla/main/main_view") // 로그인 성공 시 이동 페이지
                .permitAll() // 모두 접근 허용
                .and().
                logout() // 로그아웃
                .logoutRequestMatcher(new AntPathRequestMatcher(("/gorilla/login/logout_view"))) // 지정하고 싶은 로그아웃 url
                .logoutSuccessUrl("/gorilla/main/main_view") // 로그아웃 성공 시 이동 페이지
                .invalidateHttpSession(true) // 세션 초기회
                .and()
                .exceptionHandling() // 에러처리
                .accessDeniedPage("/gorilla/error/error_view"); // 에러 발생 시 이동 페이지


    }
}
