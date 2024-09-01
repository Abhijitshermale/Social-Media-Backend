package com.instagram.instagram_social.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.instagram_social.model.Reel;
import com.instagram.instagram_social.model.User;
import com.instagram.instagram_social.repository.ReelRepository;

@Service
public class ReelServiceImpl implements ReelService {

    @Autowired
    ReelRepository reelRepository;

    @Autowired
    UserService userService;

    @Override
    public Reel createReel(Reel reel, User user) {

        Reel createReel = new Reel();
        createReel.setTitle(reel.getTitle());
        createReel.setUser(user);
        createReel.setVideo(reel.getVideo());

        return reelRepository.save(createReel);
    }

    @Override
    public List<Reel> findAllReels() {
        return reelRepository.findAll();
    }

    @Override
    public List<Reel> findUserReel(Integer userId) throws Exception {

        userService.findUserById(userId);

        return reelRepository.findByUserId(userId);
    }
    
}