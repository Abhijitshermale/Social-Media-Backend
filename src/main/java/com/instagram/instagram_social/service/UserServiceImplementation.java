package com.instagram.instagram_social.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.instagram_social.config.JWTProvider;
import com.instagram.instagram_social.model.User;
import com.instagram.instagram_social.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        User newUser = new User();
        
        newUser.setId(user.getId());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setGender(user.getGender());

        User saveUser = userRepository.save(newUser);

        return saveUser;
    }

    @Override
    public User findUserById(Integer id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        
        if(user.isPresent()){
            return user.get();
        }

        throw new Exception("User not found with " + id + "user id.") ;
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);

        return user;

    }

    @Override
    public User followUser(Integer userid1, Integer userid2) throws Exception {
        
        User user1 = findUserById(userid1);
        User user2 = findUserById(userid2);

        user2.getFollowers().add(user1.getId());
        user1.getFollowing().add(user2.getId());

        userRepository.save(user1);
        userRepository.save(user2);
        
        return user1;
    }

    @Override
    public User updatUser(User user, Integer id) throws Exception {
        Optional<User> user1 = userRepository.findById(id);

        if(user1.isEmpty()){
            throw new Exception("User not found with " + id + "user id.");
        }

        User oldUser = user1.get();

        if(user.getFirstName() != null){
            oldUser.setFirstName(user.getFirstName());
        }
        if(user.getLastName() != null){
            oldUser.setLastName(user.getLastName());
        }
        if(user.getEmail() != null){
            oldUser.setEmail(user.getEmail());
        }
        if(user.getPassword() != null){
            oldUser.setPassword(user.getPassword());
        }
        if(user.getGender() != null){
            oldUser.setGender(user.getGender());
        }

        User updatedUser = userRepository.save(oldUser);
        return updatedUser;
    }

    @Override
    public List<User> searchUser(String query) {
        
        List<User> users = userRepository.searchUser(query);

        return users;
    }

    @Override
    public User findUserByJwt(String jwt) {

        String email = JWTProvider.getEmailFromJWTToken(jwt);

        User user = userRepository.findByEmail(email);

        return user;
        

    }

}
