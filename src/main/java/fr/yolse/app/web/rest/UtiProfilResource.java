package fr.yolse.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.yolse.app.service.UtiProfilService;
import fr.yolse.app.web.rest.errors.BadRequestAlertException;
import fr.yolse.app.web.rest.util.HeaderUtil;
import fr.yolse.app.service.dto.UtiProfilDTO;
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
 * REST controller for managing UtiProfil.
 */
@RestController
@RequestMapping("/api")
public class UtiProfilResource {

    private final Logger log = LoggerFactory.getLogger(UtiProfilResource.class);

    private static final String ENTITY_NAME = "utiProfil";

    private final UtiProfilService utiProfilService;

    public UtiProfilResource(UtiProfilService utiProfilService) {
        this.utiProfilService = utiProfilService;
    }

    /**
     * POST  /uti-profils : Create a new utiProfil.
     *
     * @param utiProfilDTO the utiProfilDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new utiProfilDTO, or with status 400 (Bad Request) if the utiProfil has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/uti-profils")
    @Timed
    public ResponseEntity<UtiProfilDTO> createUtiProfil(@Valid @RequestBody UtiProfilDTO utiProfilDTO) throws URISyntaxException {
        log.debug("REST request to save UtiProfil : {}", utiProfilDTO);
        if (utiProfilDTO.getId() != null) {
            throw new BadRequestAlertException("A new utiProfil cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UtiProfilDTO result = utiProfilService.save(utiProfilDTO);
        return ResponseEntity.created(new URI("/api/uti-profils/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /uti-profils : Updates an existing utiProfil.
     *
     * @param utiProfilDTO the utiProfilDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated utiProfilDTO,
     * or with status 400 (Bad Request) if the utiProfilDTO is not valid,
     * or with status 500 (Internal Server Error) if the utiProfilDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/uti-profils")
    @Timed
    public ResponseEntity<UtiProfilDTO> updateUtiProfil(@Valid @RequestBody UtiProfilDTO utiProfilDTO) throws URISyntaxException {
        log.debug("REST request to update UtiProfil : {}", utiProfilDTO);
        if (utiProfilDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UtiProfilDTO result = utiProfilService.save(utiProfilDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, utiProfilDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /uti-profils : get all the utiProfils.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of utiProfils in body
     */
    @GetMapping("/uti-profils")
    @Timed
    public List<UtiProfilDTO> getAllUtiProfils() {
        log.debug("REST request to get all UtiProfils");
        return utiProfilService.findAll();
    }

    /**
     * GET  /uti-profils/:id : get the "id" utiProfil.
     *
     * @param id the id of the utiProfilDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the utiProfilDTO, or with status 404 (Not Found)
     */
    @GetMapping("/uti-profils/{id}")
    @Timed
    public ResponseEntity<UtiProfilDTO> getUtiProfil(@PathVariable Long id) {
        log.debug("REST request to get UtiProfil : {}", id);
        Optional<UtiProfilDTO> utiProfilDTO = utiProfilService.findOne(id);
        return ResponseUtil.wrapOrNotFound(utiProfilDTO);
    }

    /**
     * DELETE  /uti-profils/:id : delete the "id" utiProfil.
     *
     * @param id the id of the utiProfilDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/uti-profils/{id}")
    @Timed
    public ResponseEntity<Void> deleteUtiProfil(@PathVariable Long id) {
        log.debug("REST request to delete UtiProfil : {}", id);
        utiProfilService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
