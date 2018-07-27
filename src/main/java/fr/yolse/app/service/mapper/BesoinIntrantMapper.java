package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.BesoinIntrantDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity BesoinIntrant and its DTO BesoinIntrantDTO.
 */
@Mapper(componentModel = "spring", uses = {BesoinEngraisMapper.class})
public interface BesoinIntrantMapper extends EntityMapper<BesoinIntrantDTO, BesoinIntrant> {

    @Mapping(source = "besoinEngrais.id", target = "besoinEngraisId")
    BesoinIntrantDTO toDto(BesoinIntrant besoinIntrant);

    @Mapping(target = "clients", ignore = true)
    @Mapping(target = "cultureEscs", ignore = true)
    @Mapping(target = "cultureGars", ignore = true)
    @Mapping(source = "besoinEngraisId", target = "besoinEngrais")
    BesoinIntrant toEntity(BesoinIntrantDTO besoinIntrantDTO);

    default BesoinIntrant fromId(Long id) {
        if (id == null) {
            return null;
        }
        BesoinIntrant besoinIntrant = new BesoinIntrant();
        besoinIntrant.setId(id);
        return besoinIntrant;
    }
}
