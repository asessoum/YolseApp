package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.EngraisDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Engrais and its DTO EngraisDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EngraisMapper extends EntityMapper<EngraisDTO, Engrais> {


    @Mapping(target = "besoinEngrais", ignore = true)
    Engrais toEntity(EngraisDTO engraisDTO);

    default Engrais fromId(Long id) {
        if (id == null) {
            return null;
        }
        Engrais engrais = new Engrais();
        engrais.setId(id);
        return engrais;
    }
}
