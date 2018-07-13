package fr.yolse.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.yolse.app.service.BesoinIntrantService;
import fr.yolse.app.web.rest.errors.BadRequestAlertException;
import fr.yolse.app.web.rest.util.HeaderUtil;
import fr.yolse.app.web.rest.util.PaginationUtil;
import fr.yolse.app.service.dto.BesoinIntrantDTO;
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
 * REST controller for managing BesoinIntrant.
 */
@RestController
@RequestMapping("/api")
public class BesoinIntrantResource {

    private final Logger log = LoggerFactory.getLogger(BesoinIntrantResource.class);

    private static final String ENTITY_NAME = "besoinIntrant";

    private final BesoinIntrantService besoinIntrantService;

    public BesoinIntrantResource(BesoinIntrantService besoinIntrantService) {
        this.besoinIntrantService = besoinIntrantService;
    }

    /**
     * POST  /besoin-intrants : Create a new besoinIntrant.
     *
     * @param besoinIntrantDTO the besoinIntrantDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new besoinIntrantDTO, or with status 400 (Bad Request) if the besoinIntrant has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/besoin-intrants")
    @Timed
    public ResponseEntity<BesoinIntrantDTO> createBesoinIntrant(@Valid @RequestBody BesoinIntrantDTO besoinIntrantDTO) throws URISyntaxException {
        log.debug("REST request to save BesoinIntrant : {}", besoinIntrantDTO);
        if (besoinIntrantDTO.getId() != null) {
            throw new BadRequestAlertException("A new besoinIntrant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BesoinIntrantDTO result = besoinIntrantService.save(besoinIntrantDTO);
        return ResponseEntity.created(new URI("/api/besoin-intrants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /besoin-intrants : Updates an existing besoinIntrant.
     *
     * @param besoinIntrantDTO the besoinIntrantDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated besoinIntrantDTO,
     * or with status 400 (Bad Request) if the besoinIntrantDTO is not valid,
     * or with status 500 (Internal Server Error) if the besoinIntrantDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/besoin-intrants")
    @Timed
    public ResponseEntity<BesoinIntrantDTO> updateBesoinIntrant(@Valid @RequestBody BesoinIntrantDTO besoinIntrantDTO) throws URISyntaxException {
        log.debug("REST request to update BesoinIntrant : {}", besoinIntrantDTO);
        if (besoinIntrantDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BesoinIntrantDTO result = besoinIntrantService.save(besoinIntrantDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, besoinIntrantDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /besoin-intrants : get all the besoinIntrants.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of besoinIntrants in body
     */
    @GetMapping("/besoin-intrants")
    @Timed
    public ResponseEntity<List<BesoinIntrantDTO>> getAllBesoinIntrants(Pageable pageable) {
        log.debug("REST request to get a page of BesoinIntrants");
        Page<BesoinIntrantDTO> page = besoinIntrantService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/besoin-intrants");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /besoin-intrants/:id : get the "id" besoinIntrant.
     *
     * @param id the id of the besoinIntrantDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the besoinIntrantDTO, or with status 404 (Not Found)
     */
    @GetMapping("/besoin-intrants/{id}")
    @Timed
    public ResponseEntity<BesoinIntrantDTO> getBesoinIntrant(@PathVariable Long id) {
        log.debug("REST request to get BesoinIntrant : {}", id);
        Optional<BesoinIntrantDTO> besoinIntrantDTO = besoinIntrantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(besoinIntrantDTO);
    }

    /**
     * DELETE  /besoin-intrants/:id : delete the "id" besoinIntrant.
     *
     * @param id the id of the besoinIntrantDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/besoin-intrants/{id}")
    @Timed
    public ResponseEntity<Void> deleteBesoinIntrant(@PathVariable Long id) {
        log.debug("REST request to delete BesoinIntrant : {}", id);
        besoinIntrantService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
