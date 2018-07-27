package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.BesoinEngraisDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity BesoinEngrais and its DTO BesoinEngraisDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BesoinEngraisMapper extends EntityMapper<BesoinEngraisDTO, BesoinEngrais> {


    @Mapping(target = "besoinIntrants", ignore = true)
    @Mapping(target = "engrais", ignore = true)
    BesoinEngrais toEntity(BesoinEngraisDTO besoinEngraisDTO);

    default BesoinEngrais fromId(Long id) {
        if (id == null) {
            return null;
        }
        BesoinEngrais besoinEngrais = new BesoinEngrais();
        besoinEngrais.setId(id);
        return besoinEngrais;
    }
}
