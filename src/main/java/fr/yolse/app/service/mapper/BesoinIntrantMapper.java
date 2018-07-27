package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.BesoinIntrantDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity BesoinIntrant and its DTO BesoinIntrantDTO.
 */
@Mapper(componentModel = "spring", uses = {ClientMapper.class, CultureMapper.class})
public interface BesoinIntrantMapper extends EntityMapper<BesoinIntrantDTO, BesoinIntrant> {

    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "cultureEsc.id", target = "cultureEscId")
    @Mapping(source = "cultureGar.id", target = "cultureGarId")
    BesoinIntrantDTO toDto(BesoinIntrant besoinIntrant);

    @Mapping(source = "clientId", target = "client")
    @Mapping(source = "cultureEscId", target = "cultureEsc")
    @Mapping(source = "cultureGarId", target = "cultureGar")
    @Mapping(target = "besoinEngrais", ignore = true)
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
