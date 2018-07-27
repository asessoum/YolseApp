package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.SuiviChampsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SuiviChamps and its DTO SuiviChampsDTO.
 */
@Mapper(componentModel = "spring", uses = {ClientMapper.class, UtilisateurMapper.class, CommuneMapper.class})
public interface SuiviChampsMapper extends EntityMapper<SuiviChampsDTO, SuiviChamps> {

    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "utilisateur.id", target = "utilisateurId")
    @Mapping(source = "commune.id", target = "communeId")
    SuiviChampsDTO toDto(SuiviChamps suiviChamps);

    @Mapping(source = "clientId", target = "client")
    @Mapping(source = "utilisateurId", target = "utilisateur")
    @Mapping(source = "communeId", target = "commune")
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
