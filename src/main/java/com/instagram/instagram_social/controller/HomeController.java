package com.instagram.instagram_social.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String homeController(){
        return "Hello this is Home Controller";
    }
    
    @GetMapping("/social")
    public String SocialMedia(){
        return "Hello this is Social Media Home Controller";
    }

}
