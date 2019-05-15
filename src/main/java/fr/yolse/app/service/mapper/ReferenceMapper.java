package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.ReferenceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Reference} and its DTO {@link ReferenceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ReferenceMapper extends EntityMapper<ReferenceDTO, Reference> {



    default Reference fromId(Long id) {
        if (id == null) {
            return null;
        }
        Reference reference = new Reference();
        reference.setId(id);
        return reference;
    }
}
