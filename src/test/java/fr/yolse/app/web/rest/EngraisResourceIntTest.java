package fr.yolse.app.web.rest;

import fr.yolse.app.YolseApp;

import fr.yolse.app.domain.Engrais;
import fr.yolse.app.repository.EngraisRepository;
import fr.yolse.app.service.EngraisService;
import fr.yolse.app.service.dto.EngraisDTO;
import fr.yolse.app.service.mapper.EngraisMapper;
import fr.yolse.app.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

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
 * Test class for the EngraisResource REST controller.
 *
 * @see EngraisResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YolseApp.class)
public class EngraisResourceIntTest {

    private static final Integer DEFAULT_ENGRAIS_ID = 1;
    private static final Integer UPDATED_ENGRAIS_ID = 2;

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final Double DEFAULT_PRIX_UNITAIRE = 1D;
    private static final Double UPDATED_PRIX_UNITAIRE = 2D;

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
    private EngraisRepository engraisRepository;


    @Autowired
    private EngraisMapper engraisMapper;
    

    @Autowired
    private EngraisService engraisService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEngraisMockMvc;

    private Engrais engrais;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EngraisResource engraisResource = new EngraisResource(engraisService);
        this.restEngraisMockMvc = MockMvcBuilders.standaloneSetup(engraisResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Engrais createEntity(EntityManager em) {
        Engrais engrais = new Engrais()
            .engraisID(DEFAULT_ENGRAIS_ID)
            .libelle(DEFAULT_LIBELLE)
            .prixUnitaire(DEFAULT_PRIX_UNITAIRE)
            .estActif(DEFAULT_EST_ACTIF)
            .creeLe(DEFAULT_CREE_LE)
            .creePar(DEFAULT_CREE_PAR)
            .modifLe(DEFAULT_MODIF_LE)
            .modifPar(DEFAULT_MODIF_PAR);
        return engrais;
    }

    @Before
    public void initTest() {
        engrais = createEntity(em);
    }

    @Test
    @Transactional
    public void createEngrais() throws Exception {
        int databaseSizeBeforeCreate = engraisRepository.findAll().size();

        // Create the Engrais
        EngraisDTO engraisDTO = engraisMapper.toDto(engrais);
        restEngraisMockMvc.perform(post("/api/engrais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(engraisDTO)))
            .andExpect(status().isCreated());

        // Validate the Engrais in the database
        List<Engrais> engraisList = engraisRepository.findAll();
        assertThat(engraisList).hasSize(databaseSizeBeforeCreate + 1);
        Engrais testEngrais = engraisList.get(engraisList.size() - 1);
        assertThat(testEngrais.getEngraisID()).isEqualTo(DEFAULT_ENGRAIS_ID);
        assertThat(testEngrais.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testEngrais.getPrixUnitaire()).isEqualTo(DEFAULT_PRIX_UNITAIRE);
        assertThat(testEngrais.isEstActif()).isEqualTo(DEFAULT_EST_ACTIF);
        assertThat(testEngrais.getCreeLe()).isEqualTo(DEFAULT_CREE_LE);
        assertThat(testEngrais.getCreePar()).isEqualTo(DEFAULT_CREE_PAR);
        assertThat(testEngrais.getModifLe()).isEqualTo(DEFAULT_MODIF_LE);
        assertThat(testEngrais.getModifPar()).isEqualTo(DEFAULT_MODIF_PAR);
    }

    @Test
    @Transactional
    public void createEngraisWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = engraisRepository.findAll().size();

        // Create the Engrais with an existing ID
        engrais.setId(1L);
        EngraisDTO engraisDTO = engraisMapper.toDto(engrais);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEngraisMockMvc.perform(post("/api/engrais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(engraisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Engrais in the database
        List<Engrais> engraisList = engraisRepository.findAll();
        assertThat(engraisList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkEngraisIDIsRequired() throws Exception {
        int databaseSizeBeforeTest = engraisRepository.findAll().size();
        // set the field null
        engrais.setEngraisID(null);

        // Create the Engrais, which fails.
        EngraisDTO engraisDTO = engraisMapper.toDto(engrais);

        restEngraisMockMvc.perform(post("/api/engrais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(engraisDTO)))
            .andExpect(status().isBadRequest());

        List<Engrais> engraisList = engraisRepository.findAll();
        assertThat(engraisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = engraisRepository.findAll().size();
        // set the field null
        engrais.setLibelle(null);

        // Create the Engrais, which fails.
        EngraisDTO engraisDTO = engraisMapper.toDto(engrais);

        restEngraisMockMvc.perform(post("/api/engrais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(engraisDTO)))
            .andExpect(status().isBadRequest());

        List<Engrais> engraisList = engraisRepository.findAll();
        assertThat(engraisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEngrais() throws Exception {
        // Initialize the database
        engraisRepository.saveAndFlush(engrais);

        // Get all the engraisList
        restEngraisMockMvc.perform(get("/api/engrais?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(engrais.getId().intValue())))
            .andExpect(jsonPath("$.[*].engraisID").value(hasItem(DEFAULT_ENGRAIS_ID)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())))
            .andExpect(jsonPath("$.[*].prixUnitaire").value(hasItem(DEFAULT_PRIX_UNITAIRE.doubleValue())))
            .andExpect(jsonPath("$.[*].estActif").value(hasItem(DEFAULT_EST_ACTIF.booleanValue())))
            .andExpect(jsonPath("$.[*].creeLe").value(hasItem(DEFAULT_CREE_LE.toString())))
            .andExpect(jsonPath("$.[*].creePar").value(hasItem(DEFAULT_CREE_PAR.toString())))
            .andExpect(jsonPath("$.[*].modifLe").value(hasItem(DEFAULT_MODIF_LE.toString())))
            .andExpect(jsonPath("$.[*].modifPar").value(hasItem(DEFAULT_MODIF_PAR.toString())));
    }
    

    @Test
    @Transactional
    public void getEngrais() throws Exception {
        // Initialize the database
        engraisRepository.saveAndFlush(engrais);

        // Get the engrais
        restEngraisMockMvc.perform(get("/api/engrais/{id}", engrais.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(engrais.getId().intValue()))
            .andExpect(jsonPath("$.engraisID").value(DEFAULT_ENGRAIS_ID))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()))
            .andExpect(jsonPath("$.prixUnitaire").value(DEFAULT_PRIX_UNITAIRE.doubleValue()))
            .andExpect(jsonPath("$.estActif").value(DEFAULT_EST_ACTIF.booleanValue()))
            .andExpect(jsonPath("$.creeLe").value(DEFAULT_CREE_LE.toString()))
            .andExpect(jsonPath("$.creePar").value(DEFAULT_CREE_PAR.toString()))
            .andExpect(jsonPath("$.modifLe").value(DEFAULT_MODIF_LE.toString()))
            .andExpect(jsonPath("$.modifPar").value(DEFAULT_MODIF_PAR.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingEngrais() throws Exception {
        // Get the engrais
        restEngraisMockMvc.perform(get("/api/engrais/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEngrais() throws Exception {
        // Initialize the database
        engraisRepository.saveAndFlush(engrais);

        int databaseSizeBeforeUpdate = engraisRepository.findAll().size();

        // Update the engrais
        Engrais updatedEngrais = engraisRepository.findById(engrais.getId()).get();
        // Disconnect from session so that the updates on updatedEngrais are not directly saved in db
        em.detach(updatedEngrais);
        updatedEngrais
            .engraisID(UPDATED_ENGRAIS_ID)
            .libelle(UPDATED_LIBELLE)
            .prixUnitaire(UPDATED_PRIX_UNITAIRE)
            .estActif(UPDATED_EST_ACTIF)
            .creeLe(UPDATED_CREE_LE)
            .creePar(UPDATED_CREE_PAR)
            .modifLe(UPDATED_MODIF_LE)
            .modifPar(UPDATED_MODIF_PAR);
        EngraisDTO engraisDTO = engraisMapper.toDto(updatedEngrais);

        restEngraisMockMvc.perform(put("/api/engrais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(engraisDTO)))
            .andExpect(status().isOk());

        // Validate the Engrais in the database
        List<Engrais> engraisList = engraisRepository.findAll();
        assertThat(engraisList).hasSize(databaseSizeBeforeUpdate);
        Engrais testEngrais = engraisList.get(engraisList.size() - 1);
        assertThat(testEngrais.getEngraisID()).isEqualTo(UPDATED_ENGRAIS_ID);
        assertThat(testEngrais.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testEngrais.getPrixUnitaire()).isEqualTo(UPDATED_PRIX_UNITAIRE);
        assertThat(testEngrais.isEstActif()).isEqualTo(UPDATED_EST_ACTIF);
        assertThat(testEngrais.getCreeLe()).isEqualTo(UPDATED_CREE_LE);
        assertThat(testEngrais.getCreePar()).isEqualTo(UPDATED_CREE_PAR);
        assertThat(testEngrais.getModifLe()).isEqualTo(UPDATED_MODIF_LE);
        assertThat(testEngrais.getModifPar()).isEqualTo(UPDATED_MODIF_PAR);
    }

    @Test
    @Transactional
    public void updateNonExistingEngrais() throws Exception {
        int databaseSizeBeforeUpdate = engraisRepository.findAll().size();

        // Create the Engrais
        EngraisDTO engraisDTO = engraisMapper.toDto(engrais);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEngraisMockMvc.perform(put("/api/engrais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(engraisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Engrais in the database
        List<Engrais> engraisList = engraisRepository.findAll();
        assertThat(engraisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEngrais() throws Exception {
        // Initialize the database
        engraisRepository.saveAndFlush(engrais);

        int databaseSizeBeforeDelete = engraisRepository.findAll().size();

        // Get the engrais
        restEngraisMockMvc.perform(delete("/api/engrais/{id}", engrais.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Engrais> engraisList = engraisRepository.findAll();
        assertThat(engraisList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Engrais.class);
        Engrais engrais1 = new Engrais();
        engrais1.setId(1L);
        Engrais engrais2 = new Engrais();
        engrais2.setId(engrais1.getId());
        assertThat(engrais1).isEqualTo(engrais2);
        engrais2.setId(2L);
        assertThat(engrais1).isNotEqualTo(engrais2);
        engrais1.setId(null);
        assertThat(engrais1).isNotEqualTo(engrais2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EngraisDTO.class);
        EngraisDTO engraisDTO1 = new EngraisDTO();
        engraisDTO1.setId(1L);
        EngraisDTO engraisDTO2 = new EngraisDTO();
        assertThat(engraisDTO1).isNotEqualTo(engraisDTO2);
        engraisDTO2.setId(engraisDTO1.getId());
        assertThat(engraisDTO1).isEqualTo(engraisDTO2);
        engraisDTO2.setId(2L);
        assertThat(engraisDTO1).isNotEqualTo(engraisDTO2);
        engraisDTO1.setId(null);
        assertThat(engraisDTO1).isNotEqualTo(engraisDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(engraisMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(engraisMapper.fromId(null)).isNull();
    }
}
