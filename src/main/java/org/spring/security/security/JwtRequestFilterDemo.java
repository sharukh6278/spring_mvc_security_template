
package org.spring.security.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.security.beans.User;
import org.spring.security.config.JwtTokenUtil;
import org.spring.security.exception.FridayException;
import org.spring.security.repository.UserRepository;
import org.spring.security.util.FridayConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilterDemo extends OncePerRequestFilter {
    Logger logger1 = LoggerFactory.getLogger(JwtRequestFilterDemo.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        logger1.info("request full url  :  {} ", request.getRequestURL());
        logger1.info("request.getRequestURI()  : {} ",request.getRequestURI() );

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        final String requestTokenHeader = request.getHeader("Authorization");

        String email = null;
        String jwtToken = null;
        if(request.getRequestURI().equals("/")){
            chain.doFilter(request, response);
        }
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                email = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                logger1.error("Unable to get JWT Token : {}", e.getMessage());
                throw new FridayException(403, new Date(), FridayConstant.NOT_VALID_TOKEN, "Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                throw new FridayException(403, new Date(), FridayConstant.NOT_VALID_TOKEN, "Token has expired");

            } catch (Exception e) {
                throw new FridayException(403, new Date(), e.getMessage(), e.toString());
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
            throw new FridayException(403, new Date(), FridayConstant.NOT_VALID_TOKEN, "Token does not begin with Bearer String");
        }

        // Once we get the token validate it.
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            User user = this.userRepository.findByEmail(email);

            // if token is valid configure Spring Security to manually set
            // authentication
            if (jwtTokenUtil.validateToken(jwtToken, user.getEmail())) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        user, null, user.getRoles());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // After setting the Authentication in the context, we specify
                // that the current user is authenticated. So it passes the
                // Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        logger1.info("token is valid passing to api call");
        chain.doFilter(request, response);
    }

}
