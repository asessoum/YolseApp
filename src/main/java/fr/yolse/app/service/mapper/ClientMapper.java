package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.ClientDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Client} and its DTO {@link ClientDTO}.
 */
@Mapper(componentModel = "spring", uses = {LangueMapper.class, UtilisateurMapper.class, CommuneMapper.class})
public interface ClientMapper extends EntityMapper<ClientDTO, Client> {

    @Mapping(source = "langue.id", target = "langueId")
    @Mapping(source = "commercial.id", target = "commercialId")
    @Mapping(source = "commune.id", target = "communeId")
    ClientDTO toDto(Client client);

    @Mapping(source = "langueId", target = "langue")
    @Mapping(source = "commercialId", target = "commercial")
    @Mapping(source = "communeId", target = "commune")
    @Mapping(target = "transactions", ignore = true)
    Client toEntity(ClientDTO clientDTO);

    default Client fromId(Long id) {
        if (id == null) {
            return null;
        }
        Client client = new Client();
        client.setId(id);
        return client;
    }
}
