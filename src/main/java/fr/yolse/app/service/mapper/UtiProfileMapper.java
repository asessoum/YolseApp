package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.UtiProfileDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UtiProfile and its DTO UtiProfileDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UtiProfileMapper extends EntityMapper<UtiProfileDTO, UtiProfile> {


    @Mapping(target = "utilisateurs", ignore = true)
    @Mapping(target = "profiles", ignore = true)
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
