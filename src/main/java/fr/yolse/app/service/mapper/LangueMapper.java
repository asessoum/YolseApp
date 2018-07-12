package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.LangueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Langue and its DTO LangueDTO.
 */
@Mapper(componentModel = "spring", uses = {UtilisateurMapper.class})
public interface LangueMapper extends EntityMapper<LangueDTO, Langue> {

    @Mapping(source = "utilisateurs.id", target = "utilisateursId")
    LangueDTO toDto(Langue langue);

    @Mapping(source = "utilisateursId", target = "utilisateurs")
    Langue toEntity(LangueDTO langueDTO);

    default Langue fromId(Long id) {
        if (id == null) {
            return null;
        }
        Langue langue = new Langue();
        langue.setId(id);
        return langue;
    }
}
