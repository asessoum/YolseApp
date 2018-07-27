package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.ClientDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Client and its DTO ClientDTO.
 */
@Mapper(componentModel = "spring", uses = {LangueMapper.class, UtilisateurMapper.class, CommuneMapper.class})
public interface ClientMapper extends EntityMapper<ClientDTO, Client> {

    @Mapping(source = "langue.id", target = "langueId")
    @Mapping(source = "utilisateur.id", target = "utilisateurId")
    @Mapping(source = "commune.id", target = "communeId")
    ClientDTO toDto(Client client);

    @Mapping(source = "langueId", target = "langue")
    @Mapping(source = "utilisateurId", target = "utilisateur")
    @Mapping(source = "communeId", target = "commune")
    @Mapping(target = "besoinIntrants", ignore = true)
    @Mapping(target = "suiviChamps", ignore = true)
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
