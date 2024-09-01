package com.instagram.instagram_social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instagram.instagram_social.model.Reel;
import com.instagram.instagram_social.model.User;
import com.instagram.instagram_social.service.ReelService;
import com.instagram.instagram_social.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api")
public class ReelController {

    @Autowired
    private ReelService reelService;

    @Autowired
    private UserService userService;

    @PostMapping("/reels")
    public Reel createReel(@RequestBody Reel reel, @RequestHeader ("Authorization")String jwt){
      
        User user = userService.findUserByJwt(jwt);

        Reel createdReel = reelService.createReel(reel, user);
      
        return createdReel;  
    } 

    @GetMapping("/reels")
    public List<Reel> findAllreels(){
      
        List<Reel> reels = reelService.findAllReels();
      
        return reels;  
    }
    
    @GetMapping("/reels/user/{userId}")
    public List<Reel> createReel(@PathVariable Integer userId) throws Exception{
      
        List<Reel> reels = reelService.findUserReel(userId);
      
        return reels;  
    } 
    
}