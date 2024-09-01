package com.instagram.instagram_social.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.instagram_social.model.Chat;
import com.instagram.instagram_social.model.User;
import com.instagram.instagram_social.repository.ChatRepository;

@Service
public class ChatServiceImpl implements ChatService{

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Chat createChat(User regUser, User user2) {

        Chat isChat = chatRepository.findChatByUsersId(user2, regUser);

        if(isChat != null){
            return isChat;
        }

        Chat chat = new Chat();

        chat.getUsers().add(user2);
        chat.getUsers().add(regUser);
        chat.setTimeStamp(LocalDateTime.now());

        return chatRepository.save(chat);
        
    }

    @Override
    public Chat findChatById(Integer chatId) throws Exception {

        Optional<Chat> opt = chatRepository.findById(chatId);

        if(opt.isEmpty()){
            throw new Exception("Chat not found with id "+ chatId);
        }

        return opt.get();
    }

    @Override
    public List<Chat> findUsersChat(Integer userId) {

        return chatRepository.findByUsersId(userId);
        
    }
    
}