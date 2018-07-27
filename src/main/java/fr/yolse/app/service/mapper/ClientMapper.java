package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.ClientDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Client and its DTO ClientDTO.
 */
@Mapper(componentModel = "spring", uses = {BesoinIntrantMapper.class, SuiviChampsMapper.class})
public interface ClientMapper extends EntityMapper<ClientDTO, Client> {

    @Mapping(source = "besoinIntrants.id", target = "besoinIntrantsId")
    @Mapping(source = "suiviChamps.id", target = "suiviChampsId")
    ClientDTO toDto(Client client);

    @Mapping(target = "langues", ignore = true)
    @Mapping(target = "utilisateurs", ignore = true)
    @Mapping(target = "communes", ignore = true)
    @Mapping(source = "besoinIntrantsId", target = "besoinIntrants")
    @Mapping(source = "suiviChampsId", target = "suiviChamps")
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
