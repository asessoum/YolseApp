package fr.yolse.app.web.rest;

import fr.yolse.app.YolseApp;

import fr.yolse.app.domain.BesoinEngrais;
import fr.yolse.app.repository.BesoinEngraisRepository;
import fr.yolse.app.service.BesoinEngraisService;
import fr.yolse.app.service.dto.BesoinEngraisDTO;
import fr.yolse.app.service.mapper.BesoinEngraisMapper;
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
 * Test class for the BesoinEngraisResource REST controller.
 *
 * @see BesoinEngraisResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YolseApp.class)
public class BesoinEngraisResourceIntTest {

    private static final Double DEFAULT_Q_ENGRAIS = 1D;
    private static final Double UPDATED_Q_ENGRAIS = 2D;

    private static final Double DEFAULT_P_TOT_ENGR = 1D;
    private static final Double UPDATED_P_TOT_ENGR = 2D;

    private static final Instant DEFAULT_CREE_LE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREE_LE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREE_PAR = "AAAAAAAAAA";
    private static final String UPDATED_CREE_PAR = "BBBBBBBBBB";

    private static final Instant DEFAULT_MODIF_LE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIF_LE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_MODIF_PAR = "AAAAAAAAAA";
    private static final String UPDATED_MODIF_PAR = "BBBBBBBBBB";

    @Autowired
    private BesoinEngraisRepository besoinEngraisRepository;


    @Autowired
    private BesoinEngraisMapper besoinEngraisMapper;
    

    @Autowired
    private BesoinEngraisService besoinEngraisService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBesoinEngraisMockMvc;

    private BesoinEngrais besoinEngrais;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BesoinEngraisResource besoinEngraisResource = new BesoinEngraisResource(besoinEngraisService);
        this.restBesoinEngraisMockMvc = MockMvcBuilders.standaloneSetup(besoinEngraisResource)
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
    public static BesoinEngrais createEntity(EntityManager em) {
        BesoinEngrais besoinEngrais = new BesoinEngrais()
            .qEngrais(DEFAULT_Q_ENGRAIS)
            .pTotEngr(DEFAULT_P_TOT_ENGR)
            .creeLe(DEFAULT_CREE_LE)
            .creePar(DEFAULT_CREE_PAR)
            .modifLe(DEFAULT_MODIF_LE)
            .modifPar(DEFAULT_MODIF_PAR);
        return besoinEngrais;
    }

    @Before
    public void initTest() {
        besoinEngrais = createEntity(em);
    }

    @Test
    @Transactional
    public void createBesoinEngrais() throws Exception {
        int databaseSizeBeforeCreate = besoinEngraisRepository.findAll().size();

        // Create the BesoinEngrais
        BesoinEngraisDTO besoinEngraisDTO = besoinEngraisMapper.toDto(besoinEngrais);
        restBesoinEngraisMockMvc.perform(post("/api/besoin-engrais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(besoinEngraisDTO)))
            .andExpect(status().isCreated());

        // Validate the BesoinEngrais in the database
        List<BesoinEngrais> besoinEngraisList = besoinEngraisRepository.findAll();
        assertThat(besoinEngraisList).hasSize(databaseSizeBeforeCreate + 1);
        BesoinEngrais testBesoinEngrais = besoinEngraisList.get(besoinEngraisList.size() - 1);
        assertThat(testBesoinEngrais.getqEngrais()).isEqualTo(DEFAULT_Q_ENGRAIS);
        assertThat(testBesoinEngrais.getpTotEngr()).isEqualTo(DEFAULT_P_TOT_ENGR);
        assertThat(testBesoinEngrais.getCreeLe()).isEqualTo(DEFAULT_CREE_LE);
        assertThat(testBesoinEngrais.getCreePar()).isEqualTo(DEFAULT_CREE_PAR);
        assertThat(testBesoinEngrais.getModifLe()).isEqualTo(DEFAULT_MODIF_LE);
        assertThat(testBesoinEngrais.getModifPar()).isEqualTo(DEFAULT_MODIF_PAR);
    }

    @Test
    @Transactional
    public void createBesoinEngraisWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = besoinEngraisRepository.findAll().size();

        // Create the BesoinEngrais with an existing ID
        besoinEngrais.setId(1L);
        BesoinEngraisDTO besoinEngraisDTO = besoinEngraisMapper.toDto(besoinEngrais);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBesoinEngraisMockMvc.perform(post("/api/besoin-engrais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(besoinEngraisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BesoinEngrais in the database
        List<BesoinEngrais> besoinEngraisList = besoinEngraisRepository.findAll();
        assertThat(besoinEngraisList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkqEngraisIsRequired() throws Exception {
        int databaseSizeBeforeTest = besoinEngraisRepository.findAll().size();
        // set the field null
        besoinEngrais.setqEngrais(null);

        // Create the BesoinEngrais, which fails.
        BesoinEngraisDTO besoinEngraisDTO = besoinEngraisMapper.toDto(besoinEngrais);

        restBesoinEngraisMockMvc.perform(post("/api/besoin-engrais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(besoinEngraisDTO)))
            .andExpect(status().isBadRequest());

        List<BesoinEngrais> besoinEngraisList = besoinEngraisRepository.findAll();
        assertThat(besoinEngraisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkpTotEngrIsRequired() throws Exception {
        int databaseSizeBeforeTest = besoinEngraisRepository.findAll().size();
        // set the field null
        besoinEngrais.setpTotEngr(null);

        // Create the BesoinEngrais, which fails.
        BesoinEngraisDTO besoinEngraisDTO = besoinEngraisMapper.toDto(besoinEngrais);

        restBesoinEngraisMockMvc.perform(post("/api/besoin-engrais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(besoinEngraisDTO)))
            .andExpect(status().isBadRequest());

        List<BesoinEngrais> besoinEngraisList = besoinEngraisRepository.findAll();
        assertThat(besoinEngraisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBesoinEngrais() throws Exception {
        // Initialize the database
        besoinEngraisRepository.saveAndFlush(besoinEngrais);

        // Get all the besoinEngraisList
        restBesoinEngraisMockMvc.perform(get("/api/besoin-engrais?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(besoinEngrais.getId().intValue())))
            .andExpect(jsonPath("$.[*].qEngrais").value(hasItem(DEFAULT_Q_ENGRAIS.doubleValue())))
            .andExpect(jsonPath("$.[*].pTotEngr").value(hasItem(DEFAULT_P_TOT_ENGR.doubleValue())))
            .andExpect(jsonPath("$.[*].creeLe").value(hasItem(DEFAULT_CREE_LE.toString())))
            .andExpect(jsonPath("$.[*].creePar").value(hasItem(DEFAULT_CREE_PAR.toString())))
            .andExpect(jsonPath("$.[*].modifLe").value(hasItem(DEFAULT_MODIF_LE.toString())))
            .andExpect(jsonPath("$.[*].modifPar").value(hasItem(DEFAULT_MODIF_PAR.toString())));
    }
    

    @Test
    @Transactional
    public void getBesoinEngrais() throws Exception {
        // Initialize the database
        besoinEngraisRepository.saveAndFlush(besoinEngrais);

        // Get the besoinEngrais
        restBesoinEngraisMockMvc.perform(get("/api/besoin-engrais/{id}", besoinEngrais.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(besoinEngrais.getId().intValue()))
            .andExpect(jsonPath("$.qEngrais").value(DEFAULT_Q_ENGRAIS.doubleValue()))
            .andExpect(jsonPath("$.pTotEngr").value(DEFAULT_P_TOT_ENGR.doubleValue()))
            .andExpect(jsonPath("$.creeLe").value(DEFAULT_CREE_LE.toString()))
            .andExpect(jsonPath("$.creePar").value(DEFAULT_CREE_PAR.toString()))
            .andExpect(jsonPath("$.modifLe").value(DEFAULT_MODIF_LE.toString()))
            .andExpect(jsonPath("$.modifPar").value(DEFAULT_MODIF_PAR.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingBesoinEngrais() throws Exception {
        // Get the besoinEngrais
        restBesoinEngraisMockMvc.perform(get("/api/besoin-engrais/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBesoinEngrais() throws Exception {
        // Initialize the database
        besoinEngraisRepository.saveAndFlush(besoinEngrais);

        int databaseSizeBeforeUpdate = besoinEngraisRepository.findAll().size();

        // Update the besoinEngrais
        BesoinEngrais updatedBesoinEngrais = besoinEngraisRepository.findById(besoinEngrais.getId()).get();
        // Disconnect from session so that the updates on updatedBesoinEngrais are not directly saved in db
        em.detach(updatedBesoinEngrais);
        updatedBesoinEngrais
            .qEngrais(UPDATED_Q_ENGRAIS)
            .pTotEngr(UPDATED_P_TOT_ENGR)
            .creeLe(UPDATED_CREE_LE)
            .creePar(UPDATED_CREE_PAR)
            .modifLe(UPDATED_MODIF_LE)
            .modifPar(UPDATED_MODIF_PAR);
        BesoinEngraisDTO besoinEngraisDTO = besoinEngraisMapper.toDto(updatedBesoinEngrais);

        restBesoinEngraisMockMvc.perform(put("/api/besoin-engrais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(besoinEngraisDTO)))
            .andExpect(status().isOk());

        // Validate the BesoinEngrais in the database
        List<BesoinEngrais> besoinEngraisList = besoinEngraisRepository.findAll();
        assertThat(besoinEngraisList).hasSize(databaseSizeBeforeUpdate);
        BesoinEngrais testBesoinEngrais = besoinEngraisList.get(besoinEngraisList.size() - 1);
        assertThat(testBesoinEngrais.getqEngrais()).isEqualTo(UPDATED_Q_ENGRAIS);
        assertThat(testBesoinEngrais.getpTotEngr()).isEqualTo(UPDATED_P_TOT_ENGR);
        assertThat(testBesoinEngrais.getCreeLe()).isEqualTo(UPDATED_CREE_LE);
        assertThat(testBesoinEngrais.getCreePar()).isEqualTo(UPDATED_CREE_PAR);
        assertThat(testBesoinEngrais.getModifLe()).isEqualTo(UPDATED_MODIF_LE);
        assertThat(testBesoinEngrais.getModifPar()).isEqualTo(UPDATED_MODIF_PAR);
    }

    @Test
    @Transactional
    public void updateNonExistingBesoinEngrais() throws Exception {
        int databaseSizeBeforeUpdate = besoinEngraisRepository.findAll().size();

        // Create the BesoinEngrais
        BesoinEngraisDTO besoinEngraisDTO = besoinEngraisMapper.toDto(besoinEngrais);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBesoinEngraisMockMvc.perform(put("/api/besoin-engrais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(besoinEngraisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BesoinEngrais in the database
        List<BesoinEngrais> besoinEngraisList = besoinEngraisRepository.findAll();
        assertThat(besoinEngraisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBesoinEngrais() throws Exception {
        // Initialize the database
        besoinEngraisRepository.saveAndFlush(besoinEngrais);

        int databaseSizeBeforeDelete = besoinEngraisRepository.findAll().size();

        // Get the besoinEngrais
        restBesoinEngraisMockMvc.perform(delete("/api/besoin-engrais/{id}", besoinEngrais.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BesoinEngrais> besoinEngraisList = besoinEngraisRepository.findAll();
        assertThat(besoinEngraisList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BesoinEngrais.class);
        BesoinEngrais besoinEngrais1 = new BesoinEngrais();
        besoinEngrais1.setId(1L);
        BesoinEngrais besoinEngrais2 = new BesoinEngrais();
        besoinEngrais2.setId(besoinEngrais1.getId());
        assertThat(besoinEngrais1).isEqualTo(besoinEngrais2);
        besoinEngrais2.setId(2L);
        assertThat(besoinEngrais1).isNotEqualTo(besoinEngrais2);
        besoinEngrais1.setId(null);
        assertThat(besoinEngrais1).isNotEqualTo(besoinEngrais2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BesoinEngraisDTO.class);
        BesoinEngraisDTO besoinEngraisDTO1 = new BesoinEngraisDTO();
        besoinEngraisDTO1.setId(1L);
        BesoinEngraisDTO besoinEngraisDTO2 = new BesoinEngraisDTO();
        assertThat(besoinEngraisDTO1).isNotEqualTo(besoinEngraisDTO2);
        besoinEngraisDTO2.setId(besoinEngraisDTO1.getId());
        assertThat(besoinEngraisDTO1).isEqualTo(besoinEngraisDTO2);
        besoinEngraisDTO2.setId(2L);
        assertThat(besoinEngraisDTO1).isNotEqualTo(besoinEngraisDTO2);
        besoinEngraisDTO1.setId(null);
        assertThat(besoinEngraisDTO1).isNotEqualTo(besoinEngraisDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(besoinEngraisMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(besoinEngraisMapper.fromId(null)).isNull();
    }
}
