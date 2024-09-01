package com.instagram.instagram_social.service;


import java.util.List;

import com.instagram.instagram_social.model.User;

public interface UserService {
    
    public User registerUser(User user);

    public User findUserById(Integer id) throws Exception;

    public User findUserByEmail(String email);

    public User followUser(Integer userid1, Integer userid2) throws Exception;

    public User updatUser(User user, Integer id) throws Exception;

    public List<User> searchUser(String query);

    public User findUserByJwt(String jwt);

}