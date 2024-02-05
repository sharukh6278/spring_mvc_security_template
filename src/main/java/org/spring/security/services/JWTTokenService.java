package org.spring.security.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.security.beans.JWTToken;
import org.spring.security.config.JwtTokenUtil;
import org.spring.security.exception.FridayException;
import org.spring.security.model.FridayResponse;
import org.spring.security.repository.JWTTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTTokenService {

    Logger logger = LoggerFactory.getLogger(JWTTokenService.class);

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    JWTTokenRepository jwtTokenRepository;


    public JWTToken validateJWTToken(JWTToken jwtToken) {
        logger.info("JWTTokenService : validateJWTToken():  jwtToken : {}",jwtToken);
        JWTToken savedJwtToken = jwtTokenRepository.findByEmailAndAccessToken(jwtToken.getEmail(), jwtToken.getAccessToken());

        if (savedJwtToken != null && jwtTokenUtil.validateToken(savedJwtToken.getAccessToken(), savedJwtToken.getEmail())) {
            return refreshToken(savedJwtToken);
        }
        throw new FridayException(403, new Date(), "Session got Expired please Login Again", "Session got Expired please Login Again");
    }

    public JWTToken generateToken(String email) {
        logger.info(" JWTTokenService: generateToken :email : {}", email);
        String token = jwtTokenUtil.generateToken(email);
        JWTToken jwtToken = new JWTToken();
        jwtToken.setAccessToken(token);
        jwtToken.setTokenCreationTime(new Date());
        jwtToken.setTokenExpireTime(jwtTokenUtil.getExpirationDateFromToken(jwtToken.getAccessToken()));
        jwtToken.setEmail(email);
        return jwtTokenRepository.save(jwtToken);
    }

    public JWTToken refreshToken(JWTToken jwtToken) {
        logger.info(" JWTTokenService: refreshToken :email : {}", jwtToken);
        String token = jwtTokenUtil.generateToken(jwtToken.getEmail());
        JWTToken savedJwtToken = jwtTokenRepository.findByEmailAndAccessToken(jwtToken.getEmail(), jwtToken.getAccessToken());
        savedJwtToken.setAccessToken(token);
        savedJwtToken.setTokenCreationTime(new Date());
        savedJwtToken.setTokenExpireTime(jwtTokenUtil.getExpirationDateFromToken(token));
        return jwtTokenRepository.save(savedJwtToken);
    }

    public FridayResponse invalidateToken(JWTToken jwtToken) {
        logger.info(" JWTTokenService: refreshToken :email : {}", jwtToken);
        jwtTokenRepository.deleteByEmail(jwtToken.getEmail());
        return new FridayResponse("Token is invalidated");
    }

}
