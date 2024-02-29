package com.example.publication.mapper;

import com.example.publication.controller.request.PublicationRequest;
import com.example.publication.domain.Publication;
import com.example.publication.repository.entity.PublicationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublicationMapper {

    PublicationEntity toPublicationEntity(Publication publication);

    Publication toPublication(PublicationEntity publicationEntity);

    Publication toPublication(PublicationRequest publicationRequest);

}
