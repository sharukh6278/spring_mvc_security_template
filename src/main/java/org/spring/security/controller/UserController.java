package org.spring.security.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.security.beans.JWTToken;
import org.spring.security.beans.User;
import org.spring.security.model.FridayResponse;
import org.spring.security.model.LoginRequest;
import org.spring.security.model.UserModel;
import org.spring.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT}, path = "/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody UserModel userModel) {
        logger.info("UserController:register: {}", userModel);
        return userService.register(userModel);
    }

    @PostMapping(value = "/login")
    public JWTToken login(@RequestBody LoginRequest loginRequest, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        logger.info("UserController :login : {}", loginRequest);
        return userService.login(loginRequest, httpServletRequest, httpServletResponse);
    }

    @GetMapping("/getUser")
    public User getUser(HttpServletRequest httpServletRequest) {
        logger.info("UserController :login : {}", httpServletRequest);
        return userService.getUser(httpServletRequest);
    }


    @PostMapping(value = "/logout")
    public FridayResponse logOut(@RequestBody JWTToken jwtToken) {
        logger.info("UserController :logOut : {}", jwtToken);
        return userService.logOut(jwtToken);
    }

    @DeleteMapping("/deleteUerById")
    public FridayResponse deleteUerById(@RequestParam("id") long id) {
        return userService.deleteUserById(id);
    }


}
