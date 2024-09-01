package com.instagram.instagram_social.model;

import java.util.ArrayList;
// import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String firstName;
    private String lastName;
    private String email;
    @JsonIgnore
    private String password;

    private String gender;
    private List<Integer> followers = new ArrayList<>();
    private List<Integer> following = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    private List<Post> savedPosts = new ArrayList<>();
    
    // private Date createdDate;
    // private Date updatedDate;

    public User() {
        
    }

    public User(Integer id, String firstName, String lastName, String email, String password, String gender,
            List<Integer> followers, List<Integer> following, List<Post> savedPosts) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.followers = followers;
        this.following = following;
        this.savedPosts = savedPosts;
    }

    /**
     * @return Long return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return String return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return String return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return String return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * @return String return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return List<Integer> return the followers
     */
    public List<Integer> getFollowers() {
        return followers;
    }

    /**
     * @param followers the followers to set
     */
    public void setFollowers(List<Integer> followers) {
        this.followers = followers;
    }

    /**
     * @return List<Integer> return the following
     */
    public List<Integer> getFollowing() {
        return following;
    }

    /**
     * @param following the following to set
     */
    public void setFollowing(List<Integer> following) {
        this.following = following;
    }

    /**
     * @return List<Post> return the savedPosts
     */
    public List<Post> getSavedPosts() {
        return savedPosts;
    }

    /**
     * @param savedPosts the savedPosts to set
     */
    public void setSavedPosts(List<Post> savedPosts) {
        this.savedPosts = savedPosts;
    }

}
