package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.ProfilDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Profil and its DTO ProfilDTO.
 */
@Mapper(componentModel = "spring", uses = {UtiProfilMapper.class})
public interface ProfilMapper extends EntityMapper<ProfilDTO, Profil> {

    @Mapping(source = "utilisateurs.id", target = "utilisateursId")
    ProfilDTO toDto(Profil profil);

    @Mapping(source = "utilisateursId", target = "utilisateurs")
    Profil toEntity(ProfilDTO profilDTO);

    default Profil fromId(Long id) {
        if (id == null) {
            return null;
        }
        Profil profil = new Profil();
        profil.setId(id);
        return profil;
    }
}
