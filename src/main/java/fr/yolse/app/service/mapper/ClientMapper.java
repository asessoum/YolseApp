package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.ClientDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Client and its DTO ClientDTO.
 */
@Mapper(componentModel = "spring", uses = {SuiviChampsMapper.class, EngraisClientMapper.class, UtilisateurMapper.class})
public interface ClientMapper extends EntityMapper<ClientDTO, Client> {

    @Mapping(source = "suiviChamps.id", target = "suiviChampsId")
    @Mapping(source = "engraisClients.id", target = "engraisClientsId")
    @Mapping(source = "utilisateurs.id", target = "utilisateursId")
    ClientDTO toDto(Client client);

    @Mapping(source = "suiviChampsId", target = "suiviChamps")
    @Mapping(source = "engraisClientsId", target = "engraisClients")
    @Mapping(source = "utilisateursId", target = "utilisateurs")
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
