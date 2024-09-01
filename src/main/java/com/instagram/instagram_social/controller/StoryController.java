package com.instagram.instagram_social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instagram.instagram_social.model.Story;
import com.instagram.instagram_social.model.User;
import com.instagram.instagram_social.service.StoryService;
import com.instagram.instagram_social.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api")
public class StoryController {
    
    @Autowired
    private StoryService storyService;

    @Autowired
    private UserService userService;

    @PostMapping("/story")
    public Story createStory(@RequestBody Story story, @RequestHeader ("Authorization")String jwt ){

        User regUser = userService.findUserByJwt(jwt);
        Story createdStory = storyService.createStory(story, regUser);
        return createdStory;
    }

    @GetMapping("/story/user/{userId}")
    public List<Story> findUserStory(@PathVariable Integer userId, @RequestHeader ("Authorization")String jwt ) throws Exception{

        User regUser = userService.findUserByJwt(jwt);
        List<Story> stories = storyService.findStoryByUserId(userId);
        return stories;
    }
}