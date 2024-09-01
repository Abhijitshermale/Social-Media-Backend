package com.instagram.instagram_social.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.instagram_social.model.Reel;

public interface ReelRepository extends JpaRepository<Reel, Integer> {
    
    public List<Reel> findByUserId(Integer userId);
}