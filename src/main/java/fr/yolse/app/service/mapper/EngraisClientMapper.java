package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.EngraisClientDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EngraisClient and its DTO EngraisClientDTO.
 */
@Mapper(componentModel = "spring", uses = {BesoinIntrantMapper.class})
public interface EngraisClientMapper extends EntityMapper<EngraisClientDTO, EngraisClient> {

    @Mapping(source = "besoinIntrants.id", target = "besoinIntrantsId")
    EngraisClientDTO toDto(EngraisClient engraisClient);

    @Mapping(target = "clients", ignore = true)
    @Mapping(target = "typeEngrais", ignore = true)
    @Mapping(source = "besoinIntrantsId", target = "besoinIntrants")
    EngraisClient toEntity(EngraisClientDTO engraisClientDTO);

    default EngraisClient fromId(Long id) {
        if (id == null) {
            return null;
        }
        EngraisClient engraisClient = new EngraisClient();
        engraisClient.setId(id);
        return engraisClient;
    }
}
