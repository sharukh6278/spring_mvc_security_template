package org.spring.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.security.beans.JWTToken;
import org.spring.security.model.FridayResponse;
import org.spring.security.services.JWTTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT}, path = "/jwtToken")
public class JWTTokenRestController {

    Logger logger = LoggerFactory.getLogger(JWTTokenRestController.class);

    @Autowired
    JWTTokenService jwtTokenService;

    @PostMapping(value = "/validateJWTToken")
    public JWTToken validateJWTToken(@RequestBody JWTToken jwtToken) {
        logger.info("JWTTokenRestController :validateJWTToken : {}", jwtToken);
        return jwtTokenService.validateJWTToken(jwtToken);
    }

    @GetMapping(value = "/generateToken")
    public JWTToken generateToken(@RequestParam("email") String email) {
        logger.info("JWTTokenRestController :generateToken : {}", email);
        return jwtTokenService.generateToken(email);
    }

    @PostMapping(value = "/invalidateToken")
    public FridayResponse invalidateToken(@RequestBody JWTToken jwtToken) {
        logger.info("JWTTokenRestController :invalidateToken : {}", jwtToken);
        return jwtTokenService.invalidateToken(jwtToken);
    }

}
