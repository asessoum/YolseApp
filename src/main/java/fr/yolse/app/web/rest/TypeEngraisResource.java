package fr.yolse.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.yolse.app.service.TypeEngraisService;
import fr.yolse.app.web.rest.errors.BadRequestAlertException;
import fr.yolse.app.web.rest.util.HeaderUtil;
import fr.yolse.app.service.dto.TypeEngraisDTO;
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
 * REST controller for managing TypeEngrais.
 */
@RestController
@RequestMapping("/api")
public class TypeEngraisResource {

    private final Logger log = LoggerFactory.getLogger(TypeEngraisResource.class);

    private static final String ENTITY_NAME = "typeEngrais";

    private final TypeEngraisService typeEngraisService;

    public TypeEngraisResource(TypeEngraisService typeEngraisService) {
        this.typeEngraisService = typeEngraisService;
    }

    /**
     * POST  /type-engrais : Create a new typeEngrais.
     *
     * @param typeEngraisDTO the typeEngraisDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new typeEngraisDTO, or with status 400 (Bad Request) if the typeEngrais has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/type-engrais")
    @Timed
    public ResponseEntity<TypeEngraisDTO> createTypeEngrais(@Valid @RequestBody TypeEngraisDTO typeEngraisDTO) throws URISyntaxException {
        log.debug("REST request to save TypeEngrais : {}", typeEngraisDTO);
        if (typeEngraisDTO.getId() != null) {
            throw new BadRequestAlertException("A new typeEngrais cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeEngraisDTO result = typeEngraisService.save(typeEngraisDTO);
        return ResponseEntity.created(new URI("/api/type-engrais/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /type-engrais : Updates an existing typeEngrais.
     *
     * @param typeEngraisDTO the typeEngraisDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated typeEngraisDTO,
     * or with status 400 (Bad Request) if the typeEngraisDTO is not valid,
     * or with status 500 (Internal Server Error) if the typeEngraisDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/type-engrais")
    @Timed
    public ResponseEntity<TypeEngraisDTO> updateTypeEngrais(@Valid @RequestBody TypeEngraisDTO typeEngraisDTO) throws URISyntaxException {
        log.debug("REST request to update TypeEngrais : {}", typeEngraisDTO);
        if (typeEngraisDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeEngraisDTO result = typeEngraisService.save(typeEngraisDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, typeEngraisDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /type-engrais : get all the typeEngrais.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of typeEngrais in body
     */
    @GetMapping("/type-engrais")
    @Timed
    public List<TypeEngraisDTO> getAllTypeEngrais() {
        log.debug("REST request to get all TypeEngrais");
        return typeEngraisService.findAll();
    }

    /**
     * GET  /type-engrais/:id : get the "id" typeEngrais.
     *
     * @param id the id of the typeEngraisDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the typeEngraisDTO, or with status 404 (Not Found)
     */
    @GetMapping("/type-engrais/{id}")
    @Timed
    public ResponseEntity<TypeEngraisDTO> getTypeEngrais(@PathVariable Long id) {
        log.debug("REST request to get TypeEngrais : {}", id);
        Optional<TypeEngraisDTO> typeEngraisDTO = typeEngraisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeEngraisDTO);
    }

    /**
     * DELETE  /type-engrais/:id : delete the "id" typeEngrais.
     *
     * @param id the id of the typeEngraisDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/type-engrais/{id}")
    @Timed
    public ResponseEntity<Void> deleteTypeEngrais(@PathVariable Long id) {
        log.debug("REST request to delete TypeEngrais : {}", id);
        typeEngraisService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
