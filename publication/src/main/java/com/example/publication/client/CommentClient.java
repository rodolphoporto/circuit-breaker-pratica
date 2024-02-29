package com.example.publication.client;

import com.example.publication.domain.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CommentClient", url = "${client.comments.url}")
public interface CommentClient {

    @GetMapping("/comments/{publicationId}")
    List<Comment> getComments(@PathVariable String publicationId);

}
