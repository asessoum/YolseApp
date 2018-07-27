package fr.yolse.app.service;

import fr.yolse.app.service.dto.UtiProfileDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing UtiProfile.
 */
public interface UtiProfileService {

    /**
     * Save a utiProfile.
     *
     * @param utiProfileDTO the entity to save
     * @return the persisted entity
     */
    UtiProfileDTO save(UtiProfileDTO utiProfileDTO);

    /**
     * Get all the utiProfiles.
     *
     * @return the list of entities
     */
    List<UtiProfileDTO> findAll();


    /**
     * Get the "id" utiProfile.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<UtiProfileDTO> findOne(Long id);

    /**
     * Delete the "id" utiProfile.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
