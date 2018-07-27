package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.CommuneDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Commune and its DTO CommuneDTO.
 */
@Mapper(componentModel = "spring", uses = {ProvinceMapper.class})
public interface CommuneMapper extends EntityMapper<CommuneDTO, Commune> {

    @Mapping(source = "province.id", target = "provinceId")
    CommuneDTO toDto(Commune commune);

    @Mapping(source = "provinceId", target = "province")
    @Mapping(target = "utilisateurs", ignore = true)
    @Mapping(target = "clients", ignore = true)
    @Mapping(target = "suiviChamps", ignore = true)
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
