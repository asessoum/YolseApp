package fr.yolse.app.service;

import fr.yolse.app.service.dto.UtilisateurDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Utilisateur.
 */
public interface UtilisateurService {

    /**
     * Save a utilisateur.
     *
     * @param utilisateurDTO the entity to save
     * @return the persisted entity
     */
    UtilisateurDTO save(UtilisateurDTO utilisateurDTO);

    /**
     * Get all the utilisateurs.
     *
     * @return the list of entities
     */
    List<UtilisateurDTO> findAll();


    /**
     * Get the "id" utilisateur.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<UtilisateurDTO> findOne(Long id);

    /**
     * Delete the "id" utilisateur.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
