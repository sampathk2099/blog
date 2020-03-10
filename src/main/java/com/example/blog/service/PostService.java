package com.example.blog.service;


import com.example.blog.model.Post;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

@Service
public class PostService {
    private ConcurrentHashMap<String, Post> postMap = new ConcurrentHashMap<>();

    @Async
    public Future<Post> getPost(String id){
        if(postMap.containsKey(id)){
            return new AsyncResult<>(postMap.get(id));
        }
        return null;
    }

    @Async
    public void updatePost(Post post){
        post.setDate(Date.from(Instant.now()));
        postMap.put(post.getId(),post);
    }
}
