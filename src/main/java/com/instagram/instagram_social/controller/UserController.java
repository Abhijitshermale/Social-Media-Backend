package com.instagram.instagram_social.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.instagram.instagram_social.model.User;
import com.instagram.instagram_social.repository.UserRepository;
import com.instagram.instagram_social.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public User createUser(@RequestBody User user){

        User saveUser = userService.registerUser(user);

        return saveUser;
    }
    
    @GetMapping("/user/{userId}")
    public User getUserById(@PathVariable("userId") Integer id) throws Exception {

        User user = userService.findUserById(id);

        return user; 
    }

    @PutMapping("/user")
    public User updateUser(@RequestHeader ("Authorization")String jwt, @RequestBody User user) throws Exception {
        //TODO: process PUT request

        User regUser = userService.findUserByJwt(jwt);

        User user1 = userService.updatUser(user, regUser.getId());
        return user1;
    }

    @PutMapping("/users/follow/{userId2}")
    public User followUserHandler(@RequestHeader ("Authorization")String jwt, @PathVariable Integer userId2) throws Exception{

        User regUser = userService.findUserByJwt(jwt);

        User user = userService.followUser(regUser.getId(), userId2);

        return user;
    }

    @GetMapping("/users/search")
    public List<User> searchUser(@RequestParam("query") String query){

        List<User> users = userService.searchUser(query);
        return users;
    }

    // @DeleteMapping("user/{id}")
    // public String deleteUser(@PathVariable("id") Integer id) throws Exception{

    //     Optional<User> user = userRepository.findById(id);

    //     if(user.isEmpty()){
    //         throw new Exception("User not found with " + id + "user id.");
    //     }

    //     userRepository.delete(user.get());

    //     return "User deleted successfully with id " + id;

    // }
    @GetMapping("/users")
    public List<User> getUsers(){
        List<User> users = userRepository.findAll();
        return users;
    }
    @GetMapping("/user/profile")
    public User getUserFromToken(@RequestHeader ("Authorization")String jwt) {

        User user = userService.findUserByJwt(jwt);

        return user;
    }
    
}
