package fr.yolse.app.web.rest;

import fr.yolse.app.YolseApp;
import fr.yolse.app.domain.Pays;
import fr.yolse.app.repository.PaysRepository;
import fr.yolse.app.service.PaysService;
import fr.yolse.app.service.dto.PaysDTO;
import fr.yolse.app.service.mapper.PaysMapper;
import fr.yolse.app.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static fr.yolse.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link PaysResource} REST controller.
 */
@SpringBootTest(classes = YolseApp.class)
public class PaysResourceIT {

    private static final Integer DEFAULT_PAYS_ID = 1;
    private static final Integer UPDATED_PAYS_ID = 2;

    private static final String DEFAULT_NOM_PAYS = "AAAAAAAAAA";
    private static final String UPDATED_NOM_PAYS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_EST_ACTIF = false;
    private static final Boolean UPDATED_EST_ACTIF = true;

    private static final Instant DEFAULT_CREE_LE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREE_LE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREE_PAR = "AAAAAAAAAA";
    private static final String UPDATED_CREE_PAR = "BBBBBBBBBB";

    private static final Instant DEFAULT_MODIF_LE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIF_LE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_MODIF_PAR = "AAAAAAAAAA";
    private static final String UPDATED_MODIF_PAR = "BBBBBBBBBB";

    @Autowired
    private PaysRepository paysRepository;

    @Autowired
    private PaysMapper paysMapper;

    @Autowired
    private PaysService paysService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restPaysMockMvc;

    private Pays pays;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PaysResource paysResource = new PaysResource(paysService);
        this.restPaysMockMvc = MockMvcBuilders.standaloneSetup(paysResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pays createEntity(EntityManager em) {
        Pays pays = new Pays()
            .paysID(DEFAULT_PAYS_ID)
            .nomPays(DEFAULT_NOM_PAYS)
            .estActif(DEFAULT_EST_ACTIF)
            .creeLe(DEFAULT_CREE_LE)
            .creePar(DEFAULT_CREE_PAR)
            .modifLe(DEFAULT_MODIF_LE)
            .modifPar(DEFAULT_MODIF_PAR);
        return pays;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pays createUpdatedEntity(EntityManager em) {
        Pays pays = new Pays()
            .paysID(UPDATED_PAYS_ID)
            .nomPays(UPDATED_NOM_PAYS)
            .estActif(UPDATED_EST_ACTIF)
            .creeLe(UPDATED_CREE_LE)
            .creePar(UPDATED_CREE_PAR)
            .modifLe(UPDATED_MODIF_LE)
            .modifPar(UPDATED_MODIF_PAR);
        return pays;
    }

    @BeforeEach
    public void initTest() {
        pays = createEntity(em);
    }

    @Test
    @Transactional
    public void createPays() throws Exception {
        int databaseSizeBeforeCreate = paysRepository.findAll().size();

        // Create the Pays
        PaysDTO paysDTO = paysMapper.toDto(pays);
        restPaysMockMvc.perform(post("/api/pays")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paysDTO)))
            .andExpect(status().isCreated());

        // Validate the Pays in the database
        List<Pays> paysList = paysRepository.findAll();
        assertThat(paysList).hasSize(databaseSizeBeforeCreate + 1);
        Pays testPays = paysList.get(paysList.size() - 1);
        assertThat(testPays.getPaysID()).isEqualTo(DEFAULT_PAYS_ID);
        assertThat(testPays.getNomPays()).isEqualTo(DEFAULT_NOM_PAYS);
        assertThat(testPays.isEstActif()).isEqualTo(DEFAULT_EST_ACTIF);
        assertThat(testPays.getCreeLe()).isEqualTo(DEFAULT_CREE_LE);
        assertThat(testPays.getCreePar()).isEqualTo(DEFAULT_CREE_PAR);
        assertThat(testPays.getModifLe()).isEqualTo(DEFAULT_MODIF_LE);
        assertThat(testPays.getModifPar()).isEqualTo(DEFAULT_MODIF_PAR);
    }

    @Test
    @Transactional
    public void createPaysWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = paysRepository.findAll().size();

        // Create the Pays with an existing ID
        pays.setId(1L);
        PaysDTO paysDTO = paysMapper.toDto(pays);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPaysMockMvc.perform(post("/api/pays")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paysDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Pays in the database
        List<Pays> paysList = paysRepository.findAll();
        assertThat(paysList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPaysIDIsRequired() throws Exception {
        int databaseSizeBeforeTest = paysRepository.findAll().size();
        // set the field null
        pays.setPaysID(null);

        // Create the Pays, which fails.
        PaysDTO paysDTO = paysMapper.toDto(pays);

        restPaysMockMvc.perform(post("/api/pays")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paysDTO)))
            .andExpect(status().isBadRequest());

        List<Pays> paysList = paysRepository.findAll();
        assertThat(paysList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomPaysIsRequired() throws Exception {
        int databaseSizeBeforeTest = paysRepository.findAll().size();
        // set the field null
        pays.setNomPays(null);

        // Create the Pays, which fails.
        PaysDTO paysDTO = paysMapper.toDto(pays);

        restPaysMockMvc.perform(post("/api/pays")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paysDTO)))
            .andExpect(status().isBadRequest());

        List<Pays> paysList = paysRepository.findAll();
        assertThat(paysList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPays() throws Exception {
        // Initialize the database
        paysRepository.saveAndFlush(pays);

        // Get all the paysList
        restPaysMockMvc.perform(get("/api/pays?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pays.getId().intValue())))
            .andExpect(jsonPath("$.[*].paysID").value(hasItem(DEFAULT_PAYS_ID)))
            .andExpect(jsonPath("$.[*].nomPays").value(hasItem(DEFAULT_NOM_PAYS.toString())))
            .andExpect(jsonPath("$.[*].estActif").value(hasItem(DEFAULT_EST_ACTIF.booleanValue())))
            .andExpect(jsonPath("$.[*].creeLe").value(hasItem(DEFAULT_CREE_LE.toString())))
            .andExpect(jsonPath("$.[*].creePar").value(hasItem(DEFAULT_CREE_PAR.toString())))
            .andExpect(jsonPath("$.[*].modifLe").value(hasItem(DEFAULT_MODIF_LE.toString())))
            .andExpect(jsonPath("$.[*].modifPar").value(hasItem(DEFAULT_MODIF_PAR.toString())));
    }
    
    @Test
    @Transactional
    public void getPays() throws Exception {
        // Initialize the database
        paysRepository.saveAndFlush(pays);

        // Get the pays
        restPaysMockMvc.perform(get("/api/pays/{id}", pays.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pays.getId().intValue()))
            .andExpect(jsonPath("$.paysID").value(DEFAULT_PAYS_ID))
            .andExpect(jsonPath("$.nomPays").value(DEFAULT_NOM_PAYS.toString()))
            .andExpect(jsonPath("$.estActif").value(DEFAULT_EST_ACTIF.booleanValue()))
            .andExpect(jsonPath("$.creeLe").value(DEFAULT_CREE_LE.toString()))
            .andExpect(jsonPath("$.creePar").value(DEFAULT_CREE_PAR.toString()))
            .andExpect(jsonPath("$.modifLe").value(DEFAULT_MODIF_LE.toString()))
            .andExpect(jsonPath("$.modifPar").value(DEFAULT_MODIF_PAR.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPays() throws Exception {
        // Get the pays
        restPaysMockMvc.perform(get("/api/pays/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePays() throws Exception {
        // Initialize the database
        paysRepository.saveAndFlush(pays);

        int databaseSizeBeforeUpdate = paysRepository.findAll().size();

        // Update the pays
        Pays updatedPays = paysRepository.findById(pays.getId()).get();
        // Disconnect from session so that the updates on updatedPays are not directly saved in db
        em.detach(updatedPays);
        updatedPays
            .paysID(UPDATED_PAYS_ID)
            .nomPays(UPDATED_NOM_PAYS)
            .estActif(UPDATED_EST_ACTIF)
            .creeLe(UPDATED_CREE_LE)
            .creePar(UPDATED_CREE_PAR)
            .modifLe(UPDATED_MODIF_LE)
            .modifPar(UPDATED_MODIF_PAR);
        PaysDTO paysDTO = paysMapper.toDto(updatedPays);

        restPaysMockMvc.perform(put("/api/pays")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paysDTO)))
            .andExpect(status().isOk());

        // Validate the Pays in the database
        List<Pays> paysList = paysRepository.findAll();
        assertThat(paysList).hasSize(databaseSizeBeforeUpdate);
        Pays testPays = paysList.get(paysList.size() - 1);
        assertThat(testPays.getPaysID()).isEqualTo(UPDATED_PAYS_ID);
        assertThat(testPays.getNomPays()).isEqualTo(UPDATED_NOM_PAYS);
        assertThat(testPays.isEstActif()).isEqualTo(UPDATED_EST_ACTIF);
        assertThat(testPays.getCreeLe()).isEqualTo(UPDATED_CREE_LE);
        assertThat(testPays.getCreePar()).isEqualTo(UPDATED_CREE_PAR);
        assertThat(testPays.getModifLe()).isEqualTo(UPDATED_MODIF_LE);
        assertThat(testPays.getModifPar()).isEqualTo(UPDATED_MODIF_PAR);
    }

    @Test
    @Transactional
    public void updateNonExistingPays() throws Exception {
        int databaseSizeBeforeUpdate = paysRepository.findAll().size();

        // Create the Pays
        PaysDTO paysDTO = paysMapper.toDto(pays);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaysMockMvc.perform(put("/api/pays")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paysDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Pays in the database
        List<Pays> paysList = paysRepository.findAll();
        assertThat(paysList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePays() throws Exception {
        // Initialize the database
        paysRepository.saveAndFlush(pays);

        int databaseSizeBeforeDelete = paysRepository.findAll().size();

        // Delete the pays
        restPaysMockMvc.perform(delete("/api/pays/{id}", pays.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Pays> paysList = paysRepository.findAll();
        assertThat(paysList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Pays.class);
        Pays pays1 = new Pays();
        pays1.setId(1L);
        Pays pays2 = new Pays();
        pays2.setId(pays1.getId());
        assertThat(pays1).isEqualTo(pays2);
        pays2.setId(2L);
        assertThat(pays1).isNotEqualTo(pays2);
        pays1.setId(null);
        assertThat(pays1).isNotEqualTo(pays2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaysDTO.class);
        PaysDTO paysDTO1 = new PaysDTO();
        paysDTO1.setId(1L);
        PaysDTO paysDTO2 = new PaysDTO();
        assertThat(paysDTO1).isNotEqualTo(paysDTO2);
        paysDTO2.setId(paysDTO1.getId());
        assertThat(paysDTO1).isEqualTo(paysDTO2);
        paysDTO2.setId(2L);
        assertThat(paysDTO1).isNotEqualTo(paysDTO2);
        paysDTO1.setId(null);
        assertThat(paysDTO1).isNotEqualTo(paysDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(paysMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(paysMapper.fromId(null)).isNull();
    }
}
