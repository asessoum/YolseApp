package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.LangueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Langue} and its DTO {@link LangueDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LangueMapper extends EntityMapper<LangueDTO, Langue> {


    @Mapping(target = "utilisateurs", ignore = true)
    @Mapping(target = "clients", ignore = true)
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
