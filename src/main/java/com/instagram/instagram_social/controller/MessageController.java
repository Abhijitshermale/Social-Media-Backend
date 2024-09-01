package com.instagram.instagram_social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instagram.instagram_social.model.Message;
import com.instagram.instagram_social.model.User;
import com.instagram.instagram_social.service.MessageService;
import com.instagram.instagram_social.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @PostMapping("/message/chat/{chatId}")
    public Message cretaMessage(@RequestBody Message msg, @RequestHeader ("Authorization")String jwt, @PathVariable Integer chatId ) throws Exception{
        
        User regUser = userService.findUserByJwt(jwt);

        Message message = messageService.createMessage(regUser, chatId, msg);
        
        return message;
    }

    @GetMapping("/message/chat/{chatId}")
    public List<Message> findChatMessages(@RequestHeader ("Authorization")String jwt, @PathVariable Integer chatId ) throws Exception{
        
        User regUser = userService.findUserByJwt(jwt);

        List<Message> messages = messageService.findChatsMessages( chatId);
        
        return messages;
    }
    
}