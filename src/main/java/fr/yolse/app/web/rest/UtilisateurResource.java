package fr.yolse.app.web.rest;

import fr.yolse.app.service.UtilisateurService;
import fr.yolse.app.web.rest.errors.BadRequestAlertException;
import fr.yolse.app.service.dto.UtilisateurDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link fr.yolse.app.domain.Utilisateur}.
 */
@RestController
@RequestMapping("/api")
public class UtilisateurResource {

    private final Logger log = LoggerFactory.getLogger(UtilisateurResource.class);

    private static final String ENTITY_NAME = "utilisateur";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UtilisateurService utilisateurService;

    public UtilisateurResource(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    /**
     * {@code POST  /utilisateurs} : Create a new utilisateur.
     *
     * @param utilisateurDTO the utilisateurDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new utilisateurDTO, or with status {@code 400 (Bad Request)} if the utilisateur has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/utilisateurs")
    public ResponseEntity<UtilisateurDTO> createUtilisateur(@Valid @RequestBody UtilisateurDTO utilisateurDTO) throws URISyntaxException {
        log.debug("REST request to save Utilisateur : {}", utilisateurDTO);
        if (utilisateurDTO.getId() != null) {
            throw new BadRequestAlertException("A new utilisateur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UtilisateurDTO result = utilisateurService.save(utilisateurDTO);
        return ResponseEntity.created(new URI("/api/utilisateurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /utilisateurs} : Updates an existing utilisateur.
     *
     * @param utilisateurDTO the utilisateurDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated utilisateurDTO,
     * or with status {@code 400 (Bad Request)} if the utilisateurDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the utilisateurDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/utilisateurs")
    public ResponseEntity<UtilisateurDTO> updateUtilisateur(@Valid @RequestBody UtilisateurDTO utilisateurDTO) throws URISyntaxException {
        log.debug("REST request to update Utilisateur : {}", utilisateurDTO);
        if (utilisateurDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UtilisateurDTO result = utilisateurService.save(utilisateurDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, utilisateurDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /utilisateurs} : get all the utilisateurs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of utilisateurs in body.
     */
    @GetMapping("/utilisateurs")
    public ResponseEntity<List<UtilisateurDTO>> getAllUtilisateurs(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Utilisateurs");
        Page<UtilisateurDTO> page = utilisateurService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /utilisateurs/:id} : get the "id" utilisateur.
     *
     * @param id the id of the utilisateurDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the utilisateurDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/utilisateurs/{id}")
    public ResponseEntity<UtilisateurDTO> getUtilisateur(@PathVariable Long id) {
        log.debug("REST request to get Utilisateur : {}", id);
        Optional<UtilisateurDTO> utilisateurDTO = utilisateurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(utilisateurDTO);
    }

    /**
     * {@code DELETE  /utilisateurs/:id} : delete the "id" utilisateur.
     *
     * @param id the id of the utilisateurDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/utilisateurs/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        log.debug("REST request to delete Utilisateur : {}", id);
        utilisateurService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
