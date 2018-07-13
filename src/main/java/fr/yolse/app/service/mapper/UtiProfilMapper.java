package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.UtiProfilDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UtiProfil and its DTO UtiProfilDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UtiProfilMapper extends EntityMapper<UtiProfilDTO, UtiProfil> {


    @Mapping(target = "utilisateurs", ignore = true)
    @Mapping(target = "profils", ignore = true)
    UtiProfil toEntity(UtiProfilDTO utiProfilDTO);

    default UtiProfil fromId(Long id) {
        if (id == null) {
            return null;
        }
        UtiProfil utiProfil = new UtiProfil();
        utiProfil.setId(id);
        return utiProfil;
    }
}
