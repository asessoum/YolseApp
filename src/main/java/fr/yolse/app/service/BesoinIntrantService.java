package fr.yolse.app.service;

import fr.yolse.app.service.dto.BesoinIntrantDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing BesoinIntrant.
 */
public interface BesoinIntrantService {

    /**
     * Save a besoinIntrant.
     *
     * @param besoinIntrantDTO the entity to save
     * @return the persisted entity
     */
    BesoinIntrantDTO save(BesoinIntrantDTO besoinIntrantDTO);

    /**
     * Get all the besoinIntrants.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<BesoinIntrantDTO> findAll(Pageable pageable);


    /**
     * Get the "id" besoinIntrant.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BesoinIntrantDTO> findOne(Long id);

    /**
     * Delete the "id" besoinIntrant.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
