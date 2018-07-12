package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.CommuneDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Commune and its DTO CommuneDTO.
 */
@Mapper(componentModel = "spring", uses = {UtilisateurMapper.class})
public interface CommuneMapper extends EntityMapper<CommuneDTO, Commune> {

    @Mapping(source = "utilisateurs.id", target = "utilisateursId")
    CommuneDTO toDto(Commune commune);

    @Mapping(target = "provinces", ignore = true)
    @Mapping(source = "utilisateursId", target = "utilisateurs")
    Commune toEntity(CommuneDTO communeDTO);

    default Commune fromId(Long id) {
        if (id == null) {
            return null;
        }
        Commune commune = new Commune();
        commune.setId(id);
        return commune;
    }
}
