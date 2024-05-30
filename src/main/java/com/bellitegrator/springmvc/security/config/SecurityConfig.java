package com.bellitegrator.springmvc.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsServiceImpl;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity, UserDetailsService userDetailsServiceImpl, PasswordEncoder passwordEncoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsServiceImpl)
                                    .passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authz) -> authz
                    .requestMatchers("/users/**").hasAuthority("ADMIN")
                    .requestMatchers("/index").permitAll()
                    .requestMatchers("/signup").permitAll()
                    .requestMatchers("/signin").permitAll()
                    .requestMatchers("/c_profile").hasAuthority("CUSTOMER")
                    .requestMatchers("/d_profile").hasAuthority("DELIVERYMAN")
                    .requestMatchers("/c_orders").hasAuthority("CUSTOMER")
                    .requestMatchers("/d_orders").hasAuthority("DELIVERYMAN")
                    .requestMatchers("/new_order").hasAuthority("CUSTOMER")
                    .requestMatchers("/free_orders").hasAuthority("DELIVERYMAN")
                    .anyRequest()
                    .authenticated()
                )
                .formLogin(formLogin -> formLogin
                    .loginPage("/signin")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .successHandler(new AuthenticationSuccessHandlerImpl()));

        return http.build();
    }
}
