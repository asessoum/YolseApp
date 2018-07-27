package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.BesoinEngraisDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity BesoinEngrais and its DTO BesoinEngraisDTO.
 */
@Mapper(componentModel = "spring", uses = {BesoinIntrantMapper.class, EngraisMapper.class})
public interface BesoinEngraisMapper extends EntityMapper<BesoinEngraisDTO, BesoinEngrais> {

    @Mapping(source = "besoinIntrant.id", target = "besoinIntrantId")
    @Mapping(source = "engrais.id", target = "engraisId")
    BesoinEngraisDTO toDto(BesoinEngrais besoinEngrais);

    @Mapping(source = "besoinIntrantId", target = "besoinIntrant")
    @Mapping(source = "engraisId", target = "engrais")
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
