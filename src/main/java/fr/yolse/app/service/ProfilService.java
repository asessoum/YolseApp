package fr.yolse.app.service;

import fr.yolse.app.service.dto.ProfilDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Profil.
 */
public interface ProfilService {

    /**
     * Save a profil.
     *
     * @param profilDTO the entity to save
     * @return the persisted entity
     */
    ProfilDTO save(ProfilDTO profilDTO);

    /**
     * Get all the profils.
     *
     * @return the list of entities
     */
    List<ProfilDTO> findAll();


    /**
     * Get the "id" profil.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ProfilDTO> findOne(Long id);

    /**
     * Delete the "id" profil.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
