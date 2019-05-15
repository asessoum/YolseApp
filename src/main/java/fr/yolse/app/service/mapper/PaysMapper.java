package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.PaysDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Pays} and its DTO {@link PaysDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PaysMapper extends EntityMapper<PaysDTO, Pays> {


    @Mapping(target = "communes", ignore = true)
    Pays toEntity(PaysDTO paysDTO);

    default Pays fromId(Long id) {
        if (id == null) {
            return null;
        }
        Pays pays = new Pays();
        pays.setId(id);
        return pays;
    }
}
