package com.vvdn.nms.nms_backend.config;

import com.vvdn.nms.nms_backend.config.security.JwtFilter;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public FilterRegistrationBean<Filter> jwtFilterRegistration() {
        FilterRegistrationBean<Filter> reg = new FilterRegistrationBean<>();
        reg.setFilter(jwtFilter);
        reg.addUrlPatterns("/nms/test/v1/*", "/nms/user/v1/*"); // protect these routes
        return reg;
    }
}
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}


