package com.projeto2.mecanica_diesel.config;

import com.projeto2.mecanica_diesel.service.TokenService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<JwtFilter> loggingFilter(TokenService tokenService) {
        FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new JwtFilter(tokenService));
        
        registrationBean.addUrlPatterns("/api/*");

        return registrationBean;
    }
}