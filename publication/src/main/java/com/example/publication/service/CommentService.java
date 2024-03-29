package com.example.publication.service;

import com.example.publication.client.CommentClient;
import com.example.publication.domain.Comment;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CommentService {

    private final CommentClient commentClient;
    private final RedisService redisService;

    @Autowired
    public CommentService(CommentClient commentClient, RedisService redisService) {
        this.commentClient = commentClient;
        this.redisService = redisService;
    }

    @CircuitBreaker(name = "comments", fallbackMethod = "getCommentsFallback")
    public List<Comment> getComments(String id) {
        var comments = commentClient.getComments(id);
        redisService.save(comments, id);
        return comments;
    }

    @SuppressWarnings("unused")
    private List<Comment> getCommentsFallback(String id, Throwable cause) {
        log.warn("[WARN] fallback with id {}", id);
        return redisService.findById(id);
    }

}