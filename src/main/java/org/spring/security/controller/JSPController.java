package org.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT}, path = "/jsp")

public class JSPController {

    @GetMapping("/index")
    public String getIndex(){
        System.out.println("index page ***********");
        return "index";
    }

    @GetMapping("/{path}")
    public String getPath(@PathVariable String path){
        System.out.println("index page ***********");
        return path;
    }

}
