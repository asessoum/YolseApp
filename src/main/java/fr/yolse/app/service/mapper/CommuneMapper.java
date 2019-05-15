package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.CommuneDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Commune} and its DTO {@link CommuneDTO}.
 */
@Mapper(componentModel = "spring", uses = {PaysMapper.class})
public interface CommuneMapper extends EntityMapper<CommuneDTO, Commune> {

    @Mapping(source = "pays.id", target = "paysId")
    CommuneDTO toDto(Commune commune);

    @Mapping(source = "paysId", target = "pays")
    @Mapping(target = "utilisateurs", ignore = true)
    @Mapping(target = "clients", ignore = true)
    Commune toEntity(CommuneDTO communeDTO);

    default Commune fromId(Long id) {
        if (id == null) {
            return null;
        }
        Commune commune = new Commune();
        commune.setId(id);
        return commune;
    }
}
