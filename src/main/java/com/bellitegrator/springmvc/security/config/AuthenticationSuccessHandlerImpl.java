package com.bellitegrator.springmvc.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Collection;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger("AuthenticationSuccessHandlerImpl");

    SimpleUrlAuthenticationSuccessHandler managerSuccessHandler =
            new SimpleUrlAuthenticationSuccessHandler("/orders");
    SimpleUrlAuthenticationSuccessHandler customerSuccessHandler =
            new SimpleUrlAuthenticationSuccessHandler("/c_orders");
    SimpleUrlAuthenticationSuccessHandler deliverymanSuccessHandler =
            new SimpleUrlAuthenticationSuccessHandler("/d_orders");
    SimpleUrlAuthenticationSuccessHandler adminSuccessHandler =
            new SimpleUrlAuthenticationSuccessHandler("/users");

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {
            String authorityName = grantedAuthority.getAuthority();
            switch (authorityName) {
                case "MANAGER":
                    this.managerSuccessHandler.onAuthenticationSuccess(request, response, authentication);
                    LOGGER.info("Пользователь с ролью MANAGER залогинился");
                    return;
                case "DELIVERYMAN":
                    this.deliverymanSuccessHandler.onAuthenticationSuccess(request, response, authentication);
                    LOGGER.info("Пользователь с ролью DELIVERYMAN залогинился");
                    return;
                case "ADMIN":
                    this.adminSuccessHandler.onAuthenticationSuccess(request, response, authentication);
                    LOGGER.info("Пользователь с ролью ADMIN залогинился");
                    return;
                default:
                    this.customerSuccessHandler.onAuthenticationSuccess(request, response, authentication);
                    LOGGER.info("Пользователь с ролью CUSTOMER залогинился");
            }
        }
    }
}
