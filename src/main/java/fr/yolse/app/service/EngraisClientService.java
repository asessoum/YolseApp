package fr.yolse.app.service;

import fr.yolse.app.service.dto.EngraisClientDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EngraisClient.
 */
public interface EngraisClientService {

    /**
     * Save a engraisClient.
     *
     * @param engraisClientDTO the entity to save
     * @return the persisted entity
     */
    EngraisClientDTO save(EngraisClientDTO engraisClientDTO);

    /**
     * Get all the engraisClients.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EngraisClientDTO> findAll(Pageable pageable);


    /**
     * Get the "id" engraisClient.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EngraisClientDTO> findOne(Long id);

    /**
     * Delete the "id" engraisClient.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
