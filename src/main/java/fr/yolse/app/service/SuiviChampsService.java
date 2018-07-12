package fr.yolse.app.service;

import fr.yolse.app.service.dto.SuiviChampsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing SuiviChamps.
 */
public interface SuiviChampsService {

    /**
     * Save a suiviChamps.
     *
     * @param suiviChampsDTO the entity to save
     * @return the persisted entity
     */
    SuiviChampsDTO save(SuiviChampsDTO suiviChampsDTO);

    /**
     * Get all the suiviChamps.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SuiviChampsDTO> findAll(Pageable pageable);


    /**
     * Get the "id" suiviChamps.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SuiviChampsDTO> findOne(Long id);

    /**
     * Delete the "id" suiviChamps.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
