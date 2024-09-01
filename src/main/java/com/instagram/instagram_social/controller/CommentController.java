package com.instagram.instagram_social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.instagram.instagram_social.model.Comment;
import com.instagram.instagram_social.model.User;
import com.instagram.instagram_social.service.CommentService;
import com.instagram.instagram_social.service.UserService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api")
public class CommentController {
    
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @PostMapping("/comments/post/{postId}")
    public Comment createComment(@RequestBody Comment comment, @RequestHeader ("Authorization")String jwt, @PathVariable Integer postId) throws Exception{

        User user = userService.findUserByJwt(jwt);

        Comment comment2 = commentService.createComment(comment, postId, user.getId());

        return comment2;

    }

    @PutExchange("/comments/like/{commentId}")
    public Comment likeComment(@RequestHeader ("Authorization")String jwt, @PathVariable Integer commentId) throws Exception{

        User user = userService.findUserByJwt(jwt);

        Comment likedComment = commentService.likeComment(commentId, user.getId());

        return likedComment;

    }
}