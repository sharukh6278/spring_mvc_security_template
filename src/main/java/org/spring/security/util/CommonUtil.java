package org.spring.security.util;

import io.jsonwebtoken.ExpiredJwtException;
import org.spring.security.config.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    public String decodIncomingJwt(String jwtToken) {

        try {
            if (jwtToken.startsWith("Bearer ")) {
                jwtToken = jwtToken.substring(7);
                System.out.println("date : " + jwtTokenUtil.getExpirationDateFromToken(jwtToken));
                if (jwtTokenUtil.isTokenExpired(jwtToken)) {
                    return null;
                }
                return jwtTokenUtil.getUsernameFromToken(jwtToken);
            }
        } catch (NullPointerException e) {
            throw new RuntimeException("No Token Found");
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("JWT Token is Expired");
        }
        return null;
    }
}
