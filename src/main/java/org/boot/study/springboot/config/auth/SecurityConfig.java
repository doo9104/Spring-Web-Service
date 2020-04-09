package org.boot.study.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.boot.study.springboot.domain.user.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.userinfo.CustomUserTypesOAuth2UserService;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()// h2 콘솔을 위해 비활성화
                .and()
                    .authorizeRequests() //URL별 권한설정을 위한 옵션의 시작점
                    .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    // andMatchers : 권한 관리 대상 , URL,HTTP 메소드별로 관리 가능
                    .anyRequest().authenticated() // 설정 외 나머지 URL 권한 설정 authenticated() 인증된 사용자만
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()//OAuth2 로그인 성공 후 사용자 정보를 가져올때의 설정
                            .userService(customOAuth2UserService);
    }
}
