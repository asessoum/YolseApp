package fr.yolse.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.yolse.app.service.LangueService;
import fr.yolse.app.web.rest.errors.BadRequestAlertException;
import fr.yolse.app.web.rest.util.HeaderUtil;
import fr.yolse.app.service.dto.LangueDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Langue.
 */
@RestController
@RequestMapping("/api")
public class LangueResource {

    private final Logger log = LoggerFactory.getLogger(LangueResource.class);

    private static final String ENTITY_NAME = "langue";

    private final LangueService langueService;

    public LangueResource(LangueService langueService) {
        this.langueService = langueService;
    }

    /**
     * POST  /langues : Create a new langue.
     *
     * @param langueDTO the langueDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new langueDTO, or with status 400 (Bad Request) if the langue has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/langues")
    @Timed
    public ResponseEntity<LangueDTO> createLangue(@Valid @RequestBody LangueDTO langueDTO) throws URISyntaxException {
        log.debug("REST request to save Langue : {}", langueDTO);
        if (langueDTO.getId() != null) {
            throw new BadRequestAlertException("A new langue cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LangueDTO result = langueService.save(langueDTO);
        return ResponseEntity.created(new URI("/api/langues/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /langues : Updates an existing langue.
     *
     * @param langueDTO the langueDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated langueDTO,
     * or with status 400 (Bad Request) if the langueDTO is not valid,
     * or with status 500 (Internal Server Error) if the langueDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/langues")
    @Timed
    public ResponseEntity<LangueDTO> updateLangue(@Valid @RequestBody LangueDTO langueDTO) throws URISyntaxException {
        log.debug("REST request to update Langue : {}", langueDTO);
        if (langueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LangueDTO result = langueService.save(langueDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, langueDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /langues : get all the langues.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of langues in body
     */
    @GetMapping("/langues")
    @Timed
    public List<LangueDTO> getAllLangues() {
        log.debug("REST request to get all Langues");
        return langueService.findAll();
    }

    /**
     * GET  /langues/:id : get the "id" langue.
     *
     * @param id the id of the langueDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the langueDTO, or with status 404 (Not Found)
     */
    @GetMapping("/langues/{id}")
    @Timed
    public ResponseEntity<LangueDTO> getLangue(@PathVariable Long id) {
        log.debug("REST request to get Langue : {}", id);
        Optional<LangueDTO> langueDTO = langueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(langueDTO);
    }

    /**
     * DELETE  /langues/:id : delete the "id" langue.
     *
     * @param id the id of the langueDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/langues/{id}")
    @Timed
    public ResponseEntity<Void> deleteLangue(@PathVariable Long id) {
        log.debug("REST request to delete Langue : {}", id);
        langueService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
