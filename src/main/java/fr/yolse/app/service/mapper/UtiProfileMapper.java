package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.UtiProfileDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UtiProfile and its DTO UtiProfileDTO.
 */
@Mapper(componentModel = "spring", uses = {UtilisateurMapper.class, ProfileMapper.class})
public interface UtiProfileMapper extends EntityMapper<UtiProfileDTO, UtiProfile> {

    @Mapping(source = "utilisateur.id", target = "utilisateurId")
    @Mapping(source = "profile.id", target = "profileId")
    UtiProfileDTO toDto(UtiProfile utiProfile);

    @Mapping(source = "utilisateurId", target = "utilisateur")
    @Mapping(source = "profileId", target = "profile")
    UtiProfile toEntity(UtiProfileDTO utiProfileDTO);

    default UtiProfile fromId(Long id) {
        if (id == null) {
            return null;
        }
        UtiProfile utiProfile = new UtiProfile();
        utiProfile.setId(id);
        return utiProfile;
    }
}
