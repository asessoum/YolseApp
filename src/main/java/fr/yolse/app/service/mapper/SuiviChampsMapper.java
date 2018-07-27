package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.SuiviChampsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SuiviChamps and its DTO SuiviChampsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SuiviChampsMapper extends EntityMapper<SuiviChampsDTO, SuiviChamps> {


    @Mapping(target = "clients", ignore = true)
    @Mapping(target = "utilisateurs", ignore = true)
    @Mapping(target = "communes", ignore = true)
    SuiviChamps toEntity(SuiviChampsDTO suiviChampsDTO);

    default SuiviChamps fromId(Long id) {
        if (id == null) {
            return null;
        }
        SuiviChamps suiviChamps = new SuiviChamps();
        suiviChamps.setId(id);
        return suiviChamps;
    }
}
