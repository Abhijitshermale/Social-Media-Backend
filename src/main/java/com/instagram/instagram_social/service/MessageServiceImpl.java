package com.instagram.instagram_social.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.instagram_social.model.Chat;
import com.instagram.instagram_social.model.Message;
import com.instagram.instagram_social.model.User;
import com.instagram.instagram_social.repository.ChatRepository;
import com.instagram.instagram_social.repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Message createMessage(User user, Integer chatId, Message msg) throws Exception {

        Message creatMessage = new Message();

        Chat chat = chatService.findChatById(chatId);

        creatMessage.setChat(chat);
        creatMessage.setContent(msg.getContent());
        creatMessage.setImage(msg.getImage());
        creatMessage.setUser(user);
        creatMessage.setTimeStamp(LocalDateTime.now());
        Message saveMessage =  messageRepository.save(creatMessage);
        chat.getMessages().add(saveMessage);
        chatRepository.save(chat);

        return saveMessage;
        
    }

    @Override
    public List<Message> findChatsMessages(Integer chatId) throws Exception {

        Chat chat = chatService.findChatById(chatId);

        return messageRepository.findByChatId(chatId);
        
    }
    
}