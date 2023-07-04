package com.example.dolbom.global.config;

import com.example.dolbom.global.security.jwt.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ObjectMapper objectMapper;

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()

                .antMatchers(HttpMethod.POST, "/user/signup").permitAll()
                .antMatchers(HttpMethod.POST, "/user/signin").permitAll()
                .antMatchers(HttpMethod.PUT, "/user/password").permitAll()

                .antMatchers(HttpMethod.POST, "/auth/sms").permitAll()
                .antMatchers(HttpMethod.PUT, "/auth/refresh").authenticated()

                .antMatchers(HttpMethod.GET, "/mypage").authenticated()
                .antMatchers(HttpMethod.PUT, "/mypage").authenticated()

                .antMatchers(HttpMethod.GET, "/page").permitAll()
                .antMatchers(HttpMethod.GET, "/page/{id}").permitAll()
                .antMatchers(HttpMethod.POST, "/page").authenticated()
                .antMatchers(HttpMethod.PUT, "/page/{id}").authenticated()
                .antMatchers(HttpMethod.DELETE, "/page/{id}").authenticated()

                .antMatchers(HttpMethod.GET, "/offer").permitAll()
                .antMatchers(HttpMethod.GET, "/offer/{id}").permitAll()
                .antMatchers(HttpMethod.POST, "/offer").authenticated()
                .antMatchers(HttpMethod.PUT, "/offer/{id}").authenticated()
                .antMatchers(HttpMethod.DELETE, "/offer/{id}").authenticated()


                .and()
                .formLogin().disable()

                .apply(new FilterConfig(objectMapper, jwtTokenProvider));
    }
}