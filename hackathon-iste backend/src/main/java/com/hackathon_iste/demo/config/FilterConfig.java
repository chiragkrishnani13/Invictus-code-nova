package com.hackathon_iste.demo.config;

import com.hackathon_iste.demo.security.JWTFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<JWTFilter> jwtFilter() {
        FilterRegistrationBean<JWTFilter> reg = new FilterRegistrationBean<>();
        reg.setFilter(new JWTFilter());
        reg.addUrlPatterns("/api/*"); // protect APIs
        return reg;
    }
}
