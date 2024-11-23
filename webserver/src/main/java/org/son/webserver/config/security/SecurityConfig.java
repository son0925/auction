package org.son.webserver.config.security;

import lombok.RequiredArgsConstructor;
import org.son.webserver.filter.JwtAuthenticationFilter;
import org.son.webserver.filter.LoggerFilter;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final LoggerFilter loggerFilter;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    // HttpSecurity 설정
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)  // CSRF 보호 비활성화 (API 서버에서 주로 비활성화)
//                .cors(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(authz ->
//                        authz
//                                .requestMatchers("/**").permitAll()  // 인증 없이 접근 가능한 경로
//                                .anyRequest().authenticated()  // 나머지 경로는 인증 필수
//                )
//                .formLogin(Customizer.withDefaults())
//                .addFilterBefore(loggerFilter, OncePerRequestFilter.class)
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);  // JWT 필터 추가
//    }
//
//    // AuthenticationManager 설정
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder =
//                http.getSharedObject(AuthenticationManagerBuilder.class);
//        return authenticationManagerBuilder.build();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(it -> {
                    it.requestMatchers(
                                    PathRequest.toStaticResources().atCommonLocations()
                            ).permitAll()     // 모든 resource 요청에 대해 허용

                            // SWAGGER 는 인증 없이 통과
                            .requestMatchers(
                                    "/**"
                            ).permitAll()


                            // 그 외 모든 요청은 인증 사용
                            .anyRequest().authenticated()
                    ;
                })
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(
                                new AntPathRequestMatcher("/**"//CSRF 보호 비활성화
                                )))
                .formLogin(Customizer.withDefaults())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        ;

        return httpSecurity.build();
    }
}
