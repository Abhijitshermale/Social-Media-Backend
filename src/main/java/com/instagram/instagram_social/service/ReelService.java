package com.instagram.instagram_social.service;

import java.util.List;

import com.instagram.instagram_social.model.Reel;
import com.instagram.instagram_social.model.User;

public interface ReelService  {
    
    public Reel createReel(Reel reel, User user );
    
    public List<Reel> findAllReels();

    public List<Reel> findUserReel(Integer userId) throws Exception;


}