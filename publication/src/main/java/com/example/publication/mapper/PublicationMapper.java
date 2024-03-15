package com.example.publication.mapper;

import com.example.publication.controller.request.PublicationRequest;
import com.example.publication.domain.Publication;
import com.example.publication.repository.entity.PublicationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PublicationMapper {

    @Mapping(source = "id", target = "id")
    PublicationEntity toPublicationEntity(Publication publication);

    @Mapping(source = "id", target = "id")
    Publication toPublication(PublicationEntity publicationEntity);

    Publication toPublication(PublicationRequest publicationRequest);

}
