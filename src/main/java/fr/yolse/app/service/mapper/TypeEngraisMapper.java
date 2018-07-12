package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.TypeEngraisDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TypeEngrais and its DTO TypeEngraisDTO.
 */
@Mapper(componentModel = "spring", uses = {EngraisClientMapper.class})
public interface TypeEngraisMapper extends EntityMapper<TypeEngraisDTO, TypeEngrais> {

    @Mapping(source = "engraisClients.id", target = "engraisClientsId")
    TypeEngraisDTO toDto(TypeEngrais typeEngrais);

    @Mapping(source = "engraisClientsId", target = "engraisClients")
    TypeEngrais toEntity(TypeEngraisDTO typeEngraisDTO);

    default TypeEngrais fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeEngrais typeEngrais = new TypeEngrais();
        typeEngrais.setId(id);
        return typeEngrais;
    }
}
