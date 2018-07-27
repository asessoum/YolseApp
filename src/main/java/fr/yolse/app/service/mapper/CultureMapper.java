package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.CultureDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Culture and its DTO CultureDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CultureMapper extends EntityMapper<CultureDTO, Culture> {


    @Mapping(target = "besoinsIntrantsEscs", ignore = true)
    @Mapping(target = "besoinsIntrantsGars", ignore = true)
    Culture toEntity(CultureDTO cultureDTO);

    default Culture fromId(Long id) {
        if (id == null) {
            return null;
        }
        Culture culture = new Culture();
        culture.setId(id);
        return culture;
    }
}
