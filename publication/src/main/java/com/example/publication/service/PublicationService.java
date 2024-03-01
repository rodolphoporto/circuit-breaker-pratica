package com.example.publication.service;

import com.example.publication.domain.Publication;
import com.example.publication.mapper.PublicationMapper;
import com.example.publication.repository.PublicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PublicationService {

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private PublicationMapper publicationMapper;

    @Autowired
    private CommentService commentService;

    public void insert(Publication publication) {
        var publicationEntity = publicationMapper.toPublicationEntity(publication);
        publicationRepository.save(publicationEntity);
    }

    public List<Publication> findAll() {
        var publicationEntities = publicationRepository.findAll();
        return publicationEntities.stream()
                .map(publicationMapper::toPublication).toList();
    }

    public Publication findById(String id) {
        var publication = publicationRepository.findById(id)
                .map(publicationMapper::toPublication)
                .orElseThrow(() -> new RuntimeException("Publication not found"));

        var comments = commentService.getComments(id);
        publication.setComments(comments);
        return publication;
    }

}