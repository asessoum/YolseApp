package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.EngraisDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Engrais and its DTO EngraisDTO.
 */
@Mapper(componentModel = "spring", uses = {BesoinEngraisMapper.class})
public interface EngraisMapper extends EntityMapper<EngraisDTO, Engrais> {

    @Mapping(source = "besoinEngrais.id", target = "besoinEngraisId")
    EngraisDTO toDto(Engrais engrais);

    @Mapping(source = "besoinEngraisId", target = "besoinEngrais")
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
