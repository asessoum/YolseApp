package fr.yolse.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.yolse.app.service.BesoinEngraisService;
import fr.yolse.app.web.rest.errors.BadRequestAlertException;
import fr.yolse.app.web.rest.util.HeaderUtil;
import fr.yolse.app.web.rest.util.PaginationUtil;
import fr.yolse.app.service.dto.BesoinEngraisDTO;
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
 * REST controller for managing BesoinEngrais.
 */
@RestController
@RequestMapping("/api")
public class BesoinEngraisResource {

    private final Logger log = LoggerFactory.getLogger(BesoinEngraisResource.class);

    private static final String ENTITY_NAME = "besoinEngrais";

    private final BesoinEngraisService besoinEngraisService;

    public BesoinEngraisResource(BesoinEngraisService besoinEngraisService) {
        this.besoinEngraisService = besoinEngraisService;
    }

    /**
     * POST  /besoin-engrais : Create a new besoinEngrais.
     *
     * @param besoinEngraisDTO the besoinEngraisDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new besoinEngraisDTO, or with status 400 (Bad Request) if the besoinEngrais has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/besoin-engrais")
    @Timed
    public ResponseEntity<BesoinEngraisDTO> createBesoinEngrais(@Valid @RequestBody BesoinEngraisDTO besoinEngraisDTO) throws URISyntaxException {
        log.debug("REST request to save BesoinEngrais : {}", besoinEngraisDTO);
        if (besoinEngraisDTO.getId() != null) {
            throw new BadRequestAlertException("A new besoinEngrais cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BesoinEngraisDTO result = besoinEngraisService.save(besoinEngraisDTO);
        return ResponseEntity.created(new URI("/api/besoin-engrais/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /besoin-engrais : Updates an existing besoinEngrais.
     *
     * @param besoinEngraisDTO the besoinEngraisDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated besoinEngraisDTO,
     * or with status 400 (Bad Request) if the besoinEngraisDTO is not valid,
     * or with status 500 (Internal Server Error) if the besoinEngraisDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/besoin-engrais")
    @Timed
    public ResponseEntity<BesoinEngraisDTO> updateBesoinEngrais(@Valid @RequestBody BesoinEngraisDTO besoinEngraisDTO) throws URISyntaxException {
        log.debug("REST request to update BesoinEngrais : {}", besoinEngraisDTO);
        if (besoinEngraisDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BesoinEngraisDTO result = besoinEngraisService.save(besoinEngraisDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, besoinEngraisDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /besoin-engrais : get all the besoinEngrais.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of besoinEngrais in body
     */
    @GetMapping("/besoin-engrais")
    @Timed
    public ResponseEntity<List<BesoinEngraisDTO>> getAllBesoinEngrais(Pageable pageable) {
        log.debug("REST request to get a page of BesoinEngrais");
        Page<BesoinEngraisDTO> page = besoinEngraisService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/besoin-engrais");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /besoin-engrais/:id : get the "id" besoinEngrais.
     *
     * @param id the id of the besoinEngraisDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the besoinEngraisDTO, or with status 404 (Not Found)
     */
    @GetMapping("/besoin-engrais/{id}")
    @Timed
    public ResponseEntity<BesoinEngraisDTO> getBesoinEngrais(@PathVariable Long id) {
        log.debug("REST request to get BesoinEngrais : {}", id);
        Optional<BesoinEngraisDTO> besoinEngraisDTO = besoinEngraisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(besoinEngraisDTO);
    }

    /**
     * DELETE  /besoin-engrais/:id : delete the "id" besoinEngrais.
     *
     * @param id the id of the besoinEngraisDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/besoin-engrais/{id}")
    @Timed
    public ResponseEntity<Void> deleteBesoinEngrais(@PathVariable Long id) {
        log.debug("REST request to delete BesoinEngrais : {}", id);
        besoinEngraisService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
