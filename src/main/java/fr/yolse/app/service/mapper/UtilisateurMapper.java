package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.UtilisateurDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Utilisateur and its DTO UtilisateurDTO.
 */
@Mapper(componentModel = "spring", uses = {UtiProfilMapper.class})
public interface UtilisateurMapper extends EntityMapper<UtilisateurDTO, Utilisateur> {

    @Mapping(source = "profils.id", target = "profilsId")
    UtilisateurDTO toDto(Utilisateur utilisateur);

    @Mapping(target = "typeCultures", ignore = true)
    @Mapping(target = "clients", ignore = true)
    @Mapping(target = "langues", ignore = true)
    @Mapping(target = "communes", ignore = true)
    @Mapping(source = "profilsId", target = "profils")
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
