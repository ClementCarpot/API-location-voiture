package com.accenture.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@Slf4j
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        final String ADMIN = "ADMIN";
        final String CLIENT = "CLIENT";

        http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> {
                    authorize
                            .requestMatchers(HttpMethod.GET, "/").permitAll()
                            .requestMatchers("/clients/*").hasAnyRole(ADMIN, CLIENT)
                            .requestMatchers("/clients").hasAnyRole(ADMIN, CLIENT)
                            .requestMatchers("/admins/*").hasRole(ADMIN)
                            .requestMatchers("/admins").hasRole(ADMIN)
                            .requestMatchers("/voitures").hasAnyRole(ADMIN, CLIENT)
                            .requestMatchers("/voitures/*").hasAnyRole(ADMIN, CLIENT)
                            .requestMatchers("/connexion/*").permitAll()
                            .requestMatchers("/swagger-ui/*", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**").permitAll();

                    authorize.requestMatchers("/public").permitAll();
                    authorize.anyRequest().denyAll();
                });

        return http.build();
    }

    @Bean
    UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("SELECT email, mot_de_passe, 1 FROM utilisateurs WHERE email = ?");
        userDetailsManager.setAuthoritiesByUsernameQuery("SELECT email, CONCAT('ROLE_',role) FROM utilisateurs WHERE email = ?");

        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
