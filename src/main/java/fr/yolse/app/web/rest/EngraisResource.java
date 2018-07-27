package fr.yolse.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.yolse.app.service.EngraisService;
import fr.yolse.app.web.rest.errors.BadRequestAlertException;
import fr.yolse.app.web.rest.util.HeaderUtil;
import fr.yolse.app.service.dto.EngraisDTO;
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
 * REST controller for managing Engrais.
 */
@RestController
@RequestMapping("/api")
public class EngraisResource {

    private final Logger log = LoggerFactory.getLogger(EngraisResource.class);

    private static final String ENTITY_NAME = "engrais";

    private final EngraisService engraisService;

    public EngraisResource(EngraisService engraisService) {
        this.engraisService = engraisService;
    }

    /**
     * POST  /engrais : Create a new engrais.
     *
     * @param engraisDTO the engraisDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new engraisDTO, or with status 400 (Bad Request) if the engrais has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/engrais")
    @Timed
    public ResponseEntity<EngraisDTO> createEngrais(@Valid @RequestBody EngraisDTO engraisDTO) throws URISyntaxException {
        log.debug("REST request to save Engrais : {}", engraisDTO);
        if (engraisDTO.getId() != null) {
            throw new BadRequestAlertException("A new engrais cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EngraisDTO result = engraisService.save(engraisDTO);
        return ResponseEntity.created(new URI("/api/engrais/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /engrais : Updates an existing engrais.
     *
     * @param engraisDTO the engraisDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated engraisDTO,
     * or with status 400 (Bad Request) if the engraisDTO is not valid,
     * or with status 500 (Internal Server Error) if the engraisDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/engrais")
    @Timed
    public ResponseEntity<EngraisDTO> updateEngrais(@Valid @RequestBody EngraisDTO engraisDTO) throws URISyntaxException {
        log.debug("REST request to update Engrais : {}", engraisDTO);
        if (engraisDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EngraisDTO result = engraisService.save(engraisDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, engraisDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /engrais : get all the engrais.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of engrais in body
     */
    @GetMapping("/engrais")
    @Timed
    public List<EngraisDTO> getAllEngrais() {
        log.debug("REST request to get all Engrais");
        return engraisService.findAll();
    }

    /**
     * GET  /engrais/:id : get the "id" engrais.
     *
     * @param id the id of the engraisDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the engraisDTO, or with status 404 (Not Found)
     */
    @GetMapping("/engrais/{id}")
    @Timed
    public ResponseEntity<EngraisDTO> getEngrais(@PathVariable Long id) {
        log.debug("REST request to get Engrais : {}", id);
        Optional<EngraisDTO> engraisDTO = engraisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(engraisDTO);
    }

    /**
     * DELETE  /engrais/:id : delete the "id" engrais.
     *
     * @param id the id of the engraisDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/engrais/{id}")
    @Timed
    public ResponseEntity<Void> deleteEngrais(@PathVariable Long id) {
        log.debug("REST request to delete Engrais : {}", id);
        engraisService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
