package com.seo.auto.web.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;

public class RequestHeaderAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestHeaderAuthenticationFilter.class);

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        return request.getHeader("S_LOGIN");
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        return request.getHeader("S_PASSWORD");
    }
}
