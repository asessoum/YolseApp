package fr.yolse.app.service;

import fr.yolse.app.service.dto.CommuneDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Commune.
 */
public interface CommuneService {

    /**
     * Save a commune.
     *
     * @param communeDTO the entity to save
     * @return the persisted entity
     */
    CommuneDTO save(CommuneDTO communeDTO);

    /**
     * Get all the communes.
     *
     * @return the list of entities
     */
    List<CommuneDTO> findAll();


    /**
     * Get the "id" commune.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CommuneDTO> findOne(Long id);

    /**
     * Delete the "id" commune.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
