package com.example.config

class doc {


    String doc = "//import org.springframework.context.annotation.Configuration;\n" +
            "//import org.springframework.security.config.annotation.web.builders.HttpSecurity;\n" +
            "//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;\n" +
            "//import org.springframework.security.config.http.SessionCreationPolicy;\n" +
            "//\n" +
            "//@Configuration\n" +
            "//@EnableWebSecurity\n" +
            "//public class SecurityConfig {\n" +
            "//\n" +
            "//    protected void configure(HttpSecurity http) throws Exception {\n" +
            "//        http\n" +
            "//                .csrf().disable()  // ✅ Disable CSRF for DELETE to work\n" +
            "//                .authorizeRequests()\n" +
            "//                .antMatchers(\"/users/**\").permitAll()  // ✅ Allow all requests to /users\n" +
            "//                .anyRequest().authenticated()\n" +
            "//                .and()\n" +
            "//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)\n" +
            "//                .and()\n" +
            "//                .httpBasic();  // ✅ Basic authentication (if needed)\n" +
            "//    }\n" +
            "//}"

}
