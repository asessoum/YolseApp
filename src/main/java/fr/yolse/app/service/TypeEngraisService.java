package fr.yolse.app.service;

import fr.yolse.app.service.dto.TypeEngraisDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing TypeEngrais.
 */
public interface TypeEngraisService {

    /**
     * Save a typeEngrais.
     *
     * @param typeEngraisDTO the entity to save
     * @return the persisted entity
     */
    TypeEngraisDTO save(TypeEngraisDTO typeEngraisDTO);

    /**
     * Get all the typeEngrais.
     *
     * @return the list of entities
     */
    List<TypeEngraisDTO> findAll();


    /**
     * Get the "id" typeEngrais.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TypeEngraisDTO> findOne(Long id);

    /**
     * Delete the "id" typeEngrais.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
