package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.CultureDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Culture and its DTO CultureDTO.
 */
@Mapper(componentModel = "spring", uses = {BesoinIntrantMapper.class})
public interface CultureMapper extends EntityMapper<CultureDTO, Culture> {

    @Mapping(source = "besoinsIntrantsEsc.id", target = "besoinsIntrantsEscId")
    @Mapping(source = "besoinsIntrantsGar.id", target = "besoinsIntrantsGarId")
    CultureDTO toDto(Culture culture);

    @Mapping(source = "besoinsIntrantsEscId", target = "besoinsIntrantsEsc")
    @Mapping(source = "besoinsIntrantsGarId", target = "besoinsIntrantsGar")
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
