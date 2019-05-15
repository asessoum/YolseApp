package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.UtilisateurDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Utilisateur} and its DTO {@link UtilisateurDTO}.
 */
@Mapper(componentModel = "spring", uses = {LangueMapper.class, CommuneMapper.class})
public interface UtilisateurMapper extends EntityMapper<UtilisateurDTO, Utilisateur> {

    @Mapping(source = "langue.id", target = "langueId")
    @Mapping(source = "commune.id", target = "communeId")
    @Mapping(source = "responsable.id", target = "responsableId")
    UtilisateurDTO toDto(Utilisateur utilisateur);

    @Mapping(source = "langueId", target = "langue")
    @Mapping(source = "communeId", target = "commune")
    @Mapping(source = "responsableId", target = "responsable")
    @Mapping(target = "employes", ignore = true)
    @Mapping(target = "profiles", ignore = true)
    @Mapping(target = "clients", ignore = true)
    @Mapping(target = "transactions", ignore = true)
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
