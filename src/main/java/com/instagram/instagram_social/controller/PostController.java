package com.instagram.instagram_social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.instagram.instagram_social.model.Post;
import com.instagram.instagram_social.model.User;
import com.instagram.instagram_social.response.ApiResponse;
import com.instagram.instagram_social.service.PostService;
import com.instagram.instagram_social.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    PostService postService;
    @Autowired
    UserService userService;

    @PostMapping("/post/user")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @RequestHeader ("Authorization")String jwt) throws Exception{

        User regUser = userService.findUserByJwt(jwt);

        Post cretedPost = postService.createNewPost(post, regUser.getId());

        return new ResponseEntity<>(cretedPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @RequestHeader ("Authorization")String jwt) throws Exception{
        
        User regUser = userService.findUserByJwt(jwt);

        String message = postService.deletePost(postId, regUser.getId());
        ApiResponse res = new ApiResponse(message, true);

        return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception{
        
        Post post = postService.findPostById(postId);

        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

    @GetMapping("posts/user")
    public ResponseEntity<List<Post>> findusersPost(@RequestHeader ("Authorization")String jwt){
        
        User user = userService.findUserByJwt(jwt);

        List<Post> posts = postService.findPostByUserId(user.getId());
        
        return new ResponseEntity<List<Post>>(posts, HttpStatus.ACCEPTED);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> findAllPost(){

        List<Post> posts = postService.findAllPost();

        return new ResponseEntity<List<Post>>(posts, HttpStatus.ACCEPTED);
    }

    @PutMapping("/post/save/{postId}")
    public ResponseEntity<Post> savedPostHandler(@PathVariable Integer postId, @RequestHeader ("Authorization")String jwt) throws Exception{

        User user = userService.findUserByJwt(jwt);

        Post post = postService.savedPost(postId, user.getId());

        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

    @PutMapping("/post/like/{postId}")
    public ResponseEntity<Post> likedPostHandler(@PathVariable Integer postId, @RequestHeader ("Authorization")String jwt) throws Exception{

        User user = userService.findUserByJwt(jwt);

        Post post = postService.likePost(postId, user.getId());

        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }
    
}