package com.example.blog.controller;


import com.example.blog.model.Post;
import com.example.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.*;

@RestController
public class BlogController {

    @Autowired
    private PostService postService;

    @PostMapping(path = "/post", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateBlog(
            @RequestBody Post post){
        postService.updatePost(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/post", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> getPost(
            @RequestParam String postId){
        Future<Post> post = postService.getPost(postId);
        try {
            if(!ObjectUtils.isEmpty(post.get(3, TimeUnit.SECONDS))){
                return new ResponseEntity<>(post.get(),HttpStatus.OK);
            }
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
