package fr.yolse.app.service;

import fr.yolse.app.service.dto.PaysDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.yolse.app.domain.Pays}.
 */
public interface PaysService {

    /**
     * Save a pays.
     *
     * @param paysDTO the entity to save.
     * @return the persisted entity.
     */
    PaysDTO save(PaysDTO paysDTO);

    /**
     * Get all the pays.
     *
     * @return the list of entities.
     */
    List<PaysDTO> findAll();


    /**
     * Get the "id" pays.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PaysDTO> findOne(Long id);

    /**
     * Delete the "id" pays.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
