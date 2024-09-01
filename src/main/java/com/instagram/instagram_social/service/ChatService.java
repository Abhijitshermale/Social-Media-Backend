package com.instagram.instagram_social.service;

import java.util.List;

import com.instagram.instagram_social.model.Chat;
import com.instagram.instagram_social.model.User;

public interface ChatService {

    public Chat createChat(User regUser, User user2);

    public Chat findChatById(Integer chatId) throws Exception;

    public List<Chat> findUsersChat(Integer userId);
    
}