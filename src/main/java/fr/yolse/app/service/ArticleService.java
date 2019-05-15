package fr.yolse.app.service;

import fr.yolse.app.service.dto.ArticleDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.yolse.app.domain.Article}.
 */
public interface ArticleService {

    /**
     * Save a article.
     *
     * @param articleDTO the entity to save.
     * @return the persisted entity.
     */
    ArticleDTO save(ArticleDTO articleDTO);

    /**
     * Get all the articles.
     *
     * @return the list of entities.
     */
    List<ArticleDTO> findAll();


    /**
     * Get the "id" article.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ArticleDTO> findOne(Long id);

    /**
     * Delete the "id" article.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
