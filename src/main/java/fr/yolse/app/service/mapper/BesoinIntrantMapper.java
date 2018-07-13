package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.BesoinIntrantDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity BesoinIntrant and its DTO BesoinIntrantDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BesoinIntrantMapper extends EntityMapper<BesoinIntrantDTO, BesoinIntrant> {


    @Mapping(target = "engraisClients", ignore = true)
    @Mapping(target = "typeCultures", ignore = true)
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
