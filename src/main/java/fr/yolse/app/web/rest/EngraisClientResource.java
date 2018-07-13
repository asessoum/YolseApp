package fr.yolse.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.yolse.app.service.EngraisClientService;
import fr.yolse.app.web.rest.errors.BadRequestAlertException;
import fr.yolse.app.web.rest.util.HeaderUtil;
import fr.yolse.app.web.rest.util.PaginationUtil;
import fr.yolse.app.service.dto.EngraisClientDTO;
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
 * REST controller for managing EngraisClient.
 */
@RestController
@RequestMapping("/api")
public class EngraisClientResource {

    private final Logger log = LoggerFactory.getLogger(EngraisClientResource.class);

    private static final String ENTITY_NAME = "engraisClient";

    private final EngraisClientService engraisClientService;

    public EngraisClientResource(EngraisClientService engraisClientService) {
        this.engraisClientService = engraisClientService;
    }

    /**
     * POST  /engrais-clients : Create a new engraisClient.
     *
     * @param engraisClientDTO the engraisClientDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new engraisClientDTO, or with status 400 (Bad Request) if the engraisClient has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/engrais-clients")
    @Timed
    public ResponseEntity<EngraisClientDTO> createEngraisClient(@Valid @RequestBody EngraisClientDTO engraisClientDTO) throws URISyntaxException {
        log.debug("REST request to save EngraisClient : {}", engraisClientDTO);
        if (engraisClientDTO.getId() != null) {
            throw new BadRequestAlertException("A new engraisClient cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EngraisClientDTO result = engraisClientService.save(engraisClientDTO);
        return ResponseEntity.created(new URI("/api/engrais-clients/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /engrais-clients : Updates an existing engraisClient.
     *
     * @param engraisClientDTO the engraisClientDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated engraisClientDTO,
     * or with status 400 (Bad Request) if the engraisClientDTO is not valid,
     * or with status 500 (Internal Server Error) if the engraisClientDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/engrais-clients")
    @Timed
    public ResponseEntity<EngraisClientDTO> updateEngraisClient(@Valid @RequestBody EngraisClientDTO engraisClientDTO) throws URISyntaxException {
        log.debug("REST request to update EngraisClient : {}", engraisClientDTO);
        if (engraisClientDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EngraisClientDTO result = engraisClientService.save(engraisClientDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, engraisClientDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /engrais-clients : get all the engraisClients.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of engraisClients in body
     */
    @GetMapping("/engrais-clients")
    @Timed
    public ResponseEntity<List<EngraisClientDTO>> getAllEngraisClients(Pageable pageable) {
        log.debug("REST request to get a page of EngraisClients");
        Page<EngraisClientDTO> page = engraisClientService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/engrais-clients");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /engrais-clients/:id : get the "id" engraisClient.
     *
     * @param id the id of the engraisClientDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the engraisClientDTO, or with status 404 (Not Found)
     */
    @GetMapping("/engrais-clients/{id}")
    @Timed
    public ResponseEntity<EngraisClientDTO> getEngraisClient(@PathVariable Long id) {
        log.debug("REST request to get EngraisClient : {}", id);
        Optional<EngraisClientDTO> engraisClientDTO = engraisClientService.findOne(id);
        return ResponseUtil.wrapOrNotFound(engraisClientDTO);
    }

    /**
     * DELETE  /engrais-clients/:id : delete the "id" engraisClient.
     *
     * @param id the id of the engraisClientDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/engrais-clients/{id}")
    @Timed
    public ResponseEntity<Void> deleteEngraisClient(@PathVariable Long id) {
        log.debug("REST request to delete EngraisClient : {}", id);
        engraisClientService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
