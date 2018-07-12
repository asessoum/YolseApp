package fr.yolse.app.service;

import fr.yolse.app.service.dto.UtiProfilDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing UtiProfil.
 */
public interface UtiProfilService {

    /**
     * Save a utiProfil.
     *
     * @param utiProfilDTO the entity to save
     * @return the persisted entity
     */
    UtiProfilDTO save(UtiProfilDTO utiProfilDTO);

    /**
     * Get all the utiProfils.
     *
     * @return the list of entities
     */
    List<UtiProfilDTO> findAll();


    /**
     * Get the "id" utiProfil.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<UtiProfilDTO> findOne(Long id);

    /**
     * Delete the "id" utiProfil.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
