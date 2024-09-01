package com.instagram.instagram_social.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.instagram_social.model.Story;
import com.instagram.instagram_social.model.User;
import com.instagram.instagram_social.repository.StoryRepository;

@Service
public class StoryServiceImpl implements StoryService {

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private UserService userService;

    @Override
    public Story createStory(Story story, User user) {
        Story createdStory = new Story();

        createdStory.setCaption(story.getCaption());
        createdStory.setImage(story.getImage());
        createdStory.setTimeStamp(LocalDateTime.now());
        createdStory.setUser(user);

        return storyRepository.save(createdStory);
    }

    @Override
    public List<Story> findStoryByUserId(Integer userId) throws Exception {
        
        User user = userService.findUserById(userId);
        return storyRepository.findByUserId(user.getId());

        
    }
    
}