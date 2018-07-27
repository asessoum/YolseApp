package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.LangueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Langue and its DTO LangueDTO.
 */
@Mapper(componentModel = "spring", uses = {UtilisateurMapper.class, ClientMapper.class})
public interface LangueMapper extends EntityMapper<LangueDTO, Langue> {

    @Mapping(source = "utilisateurs.id", target = "utilisateursId")
    @Mapping(source = "clients.id", target = "clientsId")
    LangueDTO toDto(Langue langue);

    @Mapping(source = "utilisateursId", target = "utilisateurs")
    @Mapping(source = "clientsId", target = "clients")
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
