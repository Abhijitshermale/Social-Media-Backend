package com.instagram.instagram_social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instagram.instagram_social.model.Chat;
import com.instagram.instagram_social.model.User;
import com.instagram.instagram_social.request.ChatRequest;
import com.instagram.instagram_social.service.ChatService;
import com.instagram.instagram_social.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api")
public class ChatController {
    
    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @PostMapping("/chats")
    private Chat createChat(@RequestHeader ("Authorization")String jwt, @RequestBody ChatRequest req) throws Exception{
        
        User regUser = userService.findUserByJwt(jwt);

        User user2 = userService.findUserById(req.getUserId());

        Chat chat = chatService.createChat(regUser, user2);
        return chat;

    }

    @GetMapping("/chats")
    private List<Chat> findUsersChat(@RequestHeader ("Authorization")String jwt){

        User regUser = userService.findUserByJwt(jwt);
        
        List<Chat> chats = chatService.findUsersChat(regUser.getId());
        return chats;

    }
}