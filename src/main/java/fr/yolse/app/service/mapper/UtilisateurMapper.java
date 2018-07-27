package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.UtilisateurDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Utilisateur and its DTO UtilisateurDTO.
 */
@Mapper(componentModel = "spring", uses = {UtiProfileMapper.class, ClientMapper.class, SuiviChampsMapper.class})
public interface UtilisateurMapper extends EntityMapper<UtilisateurDTO, Utilisateur> {

    @Mapping(source = "agents.id", target = "agentsId")
    @Mapping(source = "profiles.id", target = "profilesId")
    @Mapping(source = "clients.id", target = "clientsId")
    @Mapping(source = "suiviChamps.id", target = "suiviChampsId")
    UtilisateurDTO toDto(Utilisateur utilisateur);

    @Mapping(target = "langues", ignore = true)
    @Mapping(target = "communes", ignore = true)
    @Mapping(target = "responsables", ignore = true)
    @Mapping(source = "agentsId", target = "agents")
    @Mapping(source = "profilesId", target = "profiles")
    @Mapping(source = "clientsId", target = "clients")
    @Mapping(source = "suiviChampsId", target = "suiviChamps")
    Utilisateur toEntity(UtilisateurDTO utilisateurDTO);

    default Utilisateur fromId(Long id) {
        if (id == null) {
            return null;
        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(id);
        return utilisateur;
    }
}
