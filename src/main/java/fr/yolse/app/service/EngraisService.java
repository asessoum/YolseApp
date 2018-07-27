package fr.yolse.app.service;

import fr.yolse.app.service.dto.EngraisDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Engrais.
 */
public interface EngraisService {

    /**
     * Save a engrais.
     *
     * @param engraisDTO the entity to save
     * @return the persisted entity
     */
    EngraisDTO save(EngraisDTO engraisDTO);

    /**
     * Get all the engrais.
     *
     * @return the list of entities
     */
    List<EngraisDTO> findAll();


    /**
     * Get the "id" engrais.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EngraisDTO> findOne(Long id);

    /**
     * Delete the "id" engrais.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
