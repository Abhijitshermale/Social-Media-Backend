package com.instagram.instagram_social.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.instagram_social.model.Story;

public interface StoryRepository extends JpaRepository<Story, Integer>{
    
    public List<Story> findByUserId(Integer userId);
    
}