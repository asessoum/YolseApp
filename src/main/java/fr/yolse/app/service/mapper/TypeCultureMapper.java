package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.TypeCultureDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TypeCulture and its DTO TypeCultureDTO.
 */
@Mapper(componentModel = "spring", uses = {BesoinIntrantMapper.class, UtilisateurMapper.class})
public interface TypeCultureMapper extends EntityMapper<TypeCultureDTO, TypeCulture> {

    @Mapping(source = "engraisClients.id", target = "engraisClientsId")
    @Mapping(source = "utilisateurs.id", target = "utilisateursId")
    TypeCultureDTO toDto(TypeCulture typeCulture);

    @Mapping(source = "engraisClientsId", target = "engraisClients")
    @Mapping(source = "utilisateursId", target = "utilisateurs")
    TypeCulture toEntity(TypeCultureDTO typeCultureDTO);

    default TypeCulture fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeCulture typeCulture = new TypeCulture();
        typeCulture.setId(id);
        return typeCulture;
    }
}
