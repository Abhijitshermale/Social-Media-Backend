package com.instagram.instagram_social.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.instagram_social.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
    
}