package fr.yolse.app.service;

import fr.yolse.app.service.dto.BesoinEngraisDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing BesoinEngrais.
 */
public interface BesoinEngraisService {

    /**
     * Save a besoinEngrais.
     *
     * @param besoinEngraisDTO the entity to save
     * @return the persisted entity
     */
    BesoinEngraisDTO save(BesoinEngraisDTO besoinEngraisDTO);

    /**
     * Get all the besoinEngrais.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<BesoinEngraisDTO> findAll(Pageable pageable);


    /**
     * Get the "id" besoinEngrais.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BesoinEngraisDTO> findOne(Long id);

    /**
     * Delete the "id" besoinEngrais.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
