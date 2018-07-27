package fr.yolse.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.yolse.app.service.UtiProfileService;
import fr.yolse.app.web.rest.errors.BadRequestAlertException;
import fr.yolse.app.web.rest.util.HeaderUtil;
import fr.yolse.app.service.dto.UtiProfileDTO;
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
 * REST controller for managing UtiProfile.
 */
@RestController
@RequestMapping("/api")
public class UtiProfileResource {

    private final Logger log = LoggerFactory.getLogger(UtiProfileResource.class);

    private static final String ENTITY_NAME = "utiProfile";

    private final UtiProfileService utiProfileService;

    public UtiProfileResource(UtiProfileService utiProfileService) {
        this.utiProfileService = utiProfileService;
    }

    /**
     * POST  /uti-profiles : Create a new utiProfile.
     *
     * @param utiProfileDTO the utiProfileDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new utiProfileDTO, or with status 400 (Bad Request) if the utiProfile has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/uti-profiles")
    @Timed
    public ResponseEntity<UtiProfileDTO> createUtiProfile(@Valid @RequestBody UtiProfileDTO utiProfileDTO) throws URISyntaxException {
        log.debug("REST request to save UtiProfile : {}", utiProfileDTO);
        if (utiProfileDTO.getId() != null) {
            throw new BadRequestAlertException("A new utiProfile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UtiProfileDTO result = utiProfileService.save(utiProfileDTO);
        return ResponseEntity.created(new URI("/api/uti-profiles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /uti-profiles : Updates an existing utiProfile.
     *
     * @param utiProfileDTO the utiProfileDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated utiProfileDTO,
     * or with status 400 (Bad Request) if the utiProfileDTO is not valid,
     * or with status 500 (Internal Server Error) if the utiProfileDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/uti-profiles")
    @Timed
    public ResponseEntity<UtiProfileDTO> updateUtiProfile(@Valid @RequestBody UtiProfileDTO utiProfileDTO) throws URISyntaxException {
        log.debug("REST request to update UtiProfile : {}", utiProfileDTO);
        if (utiProfileDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UtiProfileDTO result = utiProfileService.save(utiProfileDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, utiProfileDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /uti-profiles : get all the utiProfiles.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of utiProfiles in body
     */
    @GetMapping("/uti-profiles")
    @Timed
    public List<UtiProfileDTO> getAllUtiProfiles() {
        log.debug("REST request to get all UtiProfiles");
        return utiProfileService.findAll();
    }

    /**
     * GET  /uti-profiles/:id : get the "id" utiProfile.
     *
     * @param id the id of the utiProfileDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the utiProfileDTO, or with status 404 (Not Found)
     */
    @GetMapping("/uti-profiles/{id}")
    @Timed
    public ResponseEntity<UtiProfileDTO> getUtiProfile(@PathVariable Long id) {
        log.debug("REST request to get UtiProfile : {}", id);
        Optional<UtiProfileDTO> utiProfileDTO = utiProfileService.findOne(id);
        return ResponseUtil.wrapOrNotFound(utiProfileDTO);
    }

    /**
     * DELETE  /uti-profiles/:id : delete the "id" utiProfile.
     *
     * @param id the id of the utiProfileDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/uti-profiles/{id}")
    @Timed
    public ResponseEntity<Void> deleteUtiProfile(@PathVariable Long id) {
        log.debug("REST request to delete UtiProfile : {}", id);
        utiProfileService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
