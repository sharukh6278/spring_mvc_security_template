package org.spring.security.services;

import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.security.beans.JWTToken;
import org.spring.security.beans.User;
import org.spring.security.config.JwtTokenUtil;
import org.spring.security.exception.FridayException;
import org.spring.security.mappers.CustomObjectMapper;
import org.spring.security.model.FridayResponse;
import org.spring.security.model.LoginRequest;
import org.spring.security.model.UserModel;
import org.spring.security.repository.UserRepository;
import org.spring.security.util.FridayConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserRepository userRepository;

    @Autowired
    JWTTokenService jwtTokenService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;


    public User register(UserModel userModel) {
        logger.info("UserService:register: {}", userModel);
        User user = CustomObjectMapper.customObjectMapper.getUserFromUserModel(userModel);
        userRepository.save(user);
        user.setPassword(null);
        return user;
    }

    public JWTToken login(LoginRequest loginRequest, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        logger.info("UserService:login: {}", loginRequest);
        User user = userRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        if (user != null) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getRoles());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            JWTToken jwtToken = jwtTokenService.generateToken(loginRequest.getEmail());
            httpServletResponse.setHeader("token", jwtToken.getAccessToken());
            logger.info("request authenticated and jwt send as response");
            return jwtToken;
        }
        throw new FridayException(403, new Date(), "Invalid Credential", "Invalid Credential");
    }

    public User getUser(HttpServletRequest httpServletRequest) {
        logger.info("UserService:getUser: {}", httpServletRequest);
        final String requestTokenHeader = httpServletRequest.getHeader("Authorization");

        String email = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                email = jwtTokenUtil.getUsernameFromToken(jwtToken);
                return userRepository.findByEmail(email);
            } catch (IllegalArgumentException e) {
                logger.error("Unable to get JWT Token : {}", e.getMessage());
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
    }

    public FridayResponse logOut(JWTToken jwtToken) {
        logger.info("UserService:logOut: {}", jwtToken);
        SecurityContextHolder.getContext().setAuthentication(null);
        return jwtTokenService.invalidateToken(jwtToken);
    }

    public FridayResponse deleteUserById(long id) {
        userRepository.deleteById(id);
        return new FridayResponse("User is Deleted SuccessFully");
    }

}
