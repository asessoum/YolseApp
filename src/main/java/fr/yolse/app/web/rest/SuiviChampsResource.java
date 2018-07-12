package fr.yolse.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.yolse.app.service.SuiviChampsService;
import fr.yolse.app.web.rest.errors.BadRequestAlertException;
import fr.yolse.app.web.rest.util.HeaderUtil;
import fr.yolse.app.web.rest.util.PaginationUtil;
import fr.yolse.app.service.dto.SuiviChampsDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing SuiviChamps.
 */
@RestController
@RequestMapping("/api")
public class SuiviChampsResource {

    private final Logger log = LoggerFactory.getLogger(SuiviChampsResource.class);

    private static final String ENTITY_NAME = "suiviChamps";

    private final SuiviChampsService suiviChampsService;

    public SuiviChampsResource(SuiviChampsService suiviChampsService) {
        this.suiviChampsService = suiviChampsService;
    }

    /**
     * POST  /suivi-champs : Create a new suiviChamps.
     *
     * @param suiviChampsDTO the suiviChampsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new suiviChampsDTO, or with status 400 (Bad Request) if the suiviChamps has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/suivi-champs")
    @Timed
    public ResponseEntity<SuiviChampsDTO> createSuiviChamps(@Valid @RequestBody SuiviChampsDTO suiviChampsDTO) throws URISyntaxException {
        log.debug("REST request to save SuiviChamps : {}", suiviChampsDTO);
        if (suiviChampsDTO.getId() != null) {
            throw new BadRequestAlertException("A new suiviChamps cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SuiviChampsDTO result = suiviChampsService.save(suiviChampsDTO);
        return ResponseEntity.created(new URI("/api/suivi-champs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /suivi-champs : Updates an existing suiviChamps.
     *
     * @param suiviChampsDTO the suiviChampsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated suiviChampsDTO,
     * or with status 400 (Bad Request) if the suiviChampsDTO is not valid,
     * or with status 500 (Internal Server Error) if the suiviChampsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/suivi-champs")
    @Timed
    public ResponseEntity<SuiviChampsDTO> updateSuiviChamps(@Valid @RequestBody SuiviChampsDTO suiviChampsDTO) throws URISyntaxException {
        log.debug("REST request to update SuiviChamps : {}", suiviChampsDTO);
        if (suiviChampsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SuiviChampsDTO result = suiviChampsService.save(suiviChampsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, suiviChampsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /suivi-champs : get all the suiviChamps.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of suiviChamps in body
     */
    @GetMapping("/suivi-champs")
    @Timed
    public ResponseEntity<List<SuiviChampsDTO>> getAllSuiviChamps(Pageable pageable) {
        log.debug("REST request to get a page of SuiviChamps");
        Page<SuiviChampsDTO> page = suiviChampsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/suivi-champs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /suivi-champs/:id : get the "id" suiviChamps.
     *
     * @param id the id of the suiviChampsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the suiviChampsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/suivi-champs/{id}")
    @Timed
    public ResponseEntity<SuiviChampsDTO> getSuiviChamps(@PathVariable Long id) {
        log.debug("REST request to get SuiviChamps : {}", id);
        Optional<SuiviChampsDTO> suiviChampsDTO = suiviChampsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(suiviChampsDTO);
    }

    /**
     * DELETE  /suivi-champs/:id : delete the "id" suiviChamps.
     *
     * @param id the id of the suiviChampsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/suivi-champs/{id}")
    @Timed
    public ResponseEntity<Void> deleteSuiviChamps(@PathVariable Long id) {
        log.debug("REST request to delete SuiviChamps : {}", id);
        suiviChampsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
