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

    @Autowired
    private CommentClient commentClient;

    @CircuitBreaker(name = "comments", fallbackMethod = "findByIdFallback")
    public List<Comment> getComments(String id) {
        return commentClient.getComments(id);
    }

    private List<Comment> getCommentsFallback(String id, Throwable cause) {
        log.warn("[WARN] fallback with id {}", id);
        return List.of();
    }

}