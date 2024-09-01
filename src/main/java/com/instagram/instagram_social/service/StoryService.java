package com.instagram.instagram_social.service;

import java.util.List;

import com.instagram.instagram_social.model.Story;
import com.instagram.instagram_social.model.User;

public interface StoryService {

    public Story createStory(Story story, User user);

    public List<Story> findStoryByUserId(Integer userId) throws Exception;
    
}