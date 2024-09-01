package com.instagram.instagram_social.service;

import java.util.List;

import com.instagram.instagram_social.model.Chat;
import com.instagram.instagram_social.model.Message;
import com.instagram.instagram_social.model.User;

public interface MessageService {

    public Message createMessage(User user, Integer chatId, Message msg) throws Exception;

    public List<Message> findChatsMessages(Integer chatId) throws Exception;


    
}