package com.mantoljak.myrestapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // Authenticate all requests.
        httpSecurity.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        );

        // If NOT authenticated, usually web page is shown, now there is a popup.
        httpSecurity.httpBasic(Customizer.withDefaults());

        // Disable CSRF for POST, PUT requests.
        httpSecurity.csrf(csrf -> csrf.disable());

        return httpSecurity.build();
    }
}
