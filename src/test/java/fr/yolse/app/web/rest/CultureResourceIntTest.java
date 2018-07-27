package fr.yolse.app.web.rest;

import fr.yolse.app.YolseApp;

import fr.yolse.app.domain.Culture;
import fr.yolse.app.repository.CultureRepository;
import fr.yolse.app.service.CultureService;
import fr.yolse.app.service.dto.CultureDTO;
import fr.yolse.app.service.mapper.CultureMapper;
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
 * Test class for the CultureResource REST controller.
 *
 * @see CultureResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YolseApp.class)
public class CultureResourceIntTest {

    private static final Integer DEFAULT_ENGRAIS_ID = 1;
    private static final Integer UPDATED_ENGRAIS_ID = 2;

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final Double DEFAULT_PRIX_CESSION = 1D;
    private static final Double UPDATED_PRIX_CESSION = 2D;

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
    private CultureRepository cultureRepository;


    @Autowired
    private CultureMapper cultureMapper;
    

    @Autowired
    private CultureService cultureService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCultureMockMvc;

    private Culture culture;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CultureResource cultureResource = new CultureResource(cultureService);
        this.restCultureMockMvc = MockMvcBuilders.standaloneSetup(cultureResource)
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
    public static Culture createEntity(EntityManager em) {
        Culture culture = new Culture()
            .engraisID(DEFAULT_ENGRAIS_ID)
            .libelle(DEFAULT_LIBELLE)
            .prixCession(DEFAULT_PRIX_CESSION)
            .estActif(DEFAULT_EST_ACTIF)
            .creeLe(DEFAULT_CREE_LE)
            .creePar(DEFAULT_CREE_PAR)
            .modifLe(DEFAULT_MODIF_LE)
            .modifPar(DEFAULT_MODIF_PAR);
        return culture;
    }

    @Before
    public void initTest() {
        culture = createEntity(em);
    }

    @Test
    @Transactional
    public void createCulture() throws Exception {
        int databaseSizeBeforeCreate = cultureRepository.findAll().size();

        // Create the Culture
        CultureDTO cultureDTO = cultureMapper.toDto(culture);
        restCultureMockMvc.perform(post("/api/cultures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cultureDTO)))
            .andExpect(status().isCreated());

        // Validate the Culture in the database
        List<Culture> cultureList = cultureRepository.findAll();
        assertThat(cultureList).hasSize(databaseSizeBeforeCreate + 1);
        Culture testCulture = cultureList.get(cultureList.size() - 1);
        assertThat(testCulture.getEngraisID()).isEqualTo(DEFAULT_ENGRAIS_ID);
        assertThat(testCulture.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testCulture.getPrixCession()).isEqualTo(DEFAULT_PRIX_CESSION);
        assertThat(testCulture.isEstActif()).isEqualTo(DEFAULT_EST_ACTIF);
        assertThat(testCulture.getCreeLe()).isEqualTo(DEFAULT_CREE_LE);
        assertThat(testCulture.getCreePar()).isEqualTo(DEFAULT_CREE_PAR);
        assertThat(testCulture.getModifLe()).isEqualTo(DEFAULT_MODIF_LE);
        assertThat(testCulture.getModifPar()).isEqualTo(DEFAULT_MODIF_PAR);
    }

    @Test
    @Transactional
    public void createCultureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cultureRepository.findAll().size();

        // Create the Culture with an existing ID
        culture.setId(1L);
        CultureDTO cultureDTO = cultureMapper.toDto(culture);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCultureMockMvc.perform(post("/api/cultures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cultureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Culture in the database
        List<Culture> cultureList = cultureRepository.findAll();
        assertThat(cultureList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkEngraisIDIsRequired() throws Exception {
        int databaseSizeBeforeTest = cultureRepository.findAll().size();
        // set the field null
        culture.setEngraisID(null);

        // Create the Culture, which fails.
        CultureDTO cultureDTO = cultureMapper.toDto(culture);

        restCultureMockMvc.perform(post("/api/cultures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cultureDTO)))
            .andExpect(status().isBadRequest());

        List<Culture> cultureList = cultureRepository.findAll();
        assertThat(cultureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = cultureRepository.findAll().size();
        // set the field null
        culture.setLibelle(null);

        // Create the Culture, which fails.
        CultureDTO cultureDTO = cultureMapper.toDto(culture);

        restCultureMockMvc.perform(post("/api/cultures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cultureDTO)))
            .andExpect(status().isBadRequest());

        List<Culture> cultureList = cultureRepository.findAll();
        assertThat(cultureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCultures() throws Exception {
        // Initialize the database
        cultureRepository.saveAndFlush(culture);

        // Get all the cultureList
        restCultureMockMvc.perform(get("/api/cultures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(culture.getId().intValue())))
            .andExpect(jsonPath("$.[*].engraisID").value(hasItem(DEFAULT_ENGRAIS_ID)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())))
            .andExpect(jsonPath("$.[*].prixCession").value(hasItem(DEFAULT_PRIX_CESSION.doubleValue())))
            .andExpect(jsonPath("$.[*].estActif").value(hasItem(DEFAULT_EST_ACTIF.booleanValue())))
            .andExpect(jsonPath("$.[*].creeLe").value(hasItem(DEFAULT_CREE_LE.toString())))
            .andExpect(jsonPath("$.[*].creePar").value(hasItem(DEFAULT_CREE_PAR.toString())))
            .andExpect(jsonPath("$.[*].modifLe").value(hasItem(DEFAULT_MODIF_LE.toString())))
            .andExpect(jsonPath("$.[*].modifPar").value(hasItem(DEFAULT_MODIF_PAR.toString())));
    }
    

    @Test
    @Transactional
    public void getCulture() throws Exception {
        // Initialize the database
        cultureRepository.saveAndFlush(culture);

        // Get the culture
        restCultureMockMvc.perform(get("/api/cultures/{id}", culture.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(culture.getId().intValue()))
            .andExpect(jsonPath("$.engraisID").value(DEFAULT_ENGRAIS_ID))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()))
            .andExpect(jsonPath("$.prixCession").value(DEFAULT_PRIX_CESSION.doubleValue()))
            .andExpect(jsonPath("$.estActif").value(DEFAULT_EST_ACTIF.booleanValue()))
            .andExpect(jsonPath("$.creeLe").value(DEFAULT_CREE_LE.toString()))
            .andExpect(jsonPath("$.creePar").value(DEFAULT_CREE_PAR.toString()))
            .andExpect(jsonPath("$.modifLe").value(DEFAULT_MODIF_LE.toString()))
            .andExpect(jsonPath("$.modifPar").value(DEFAULT_MODIF_PAR.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCulture() throws Exception {
        // Get the culture
        restCultureMockMvc.perform(get("/api/cultures/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCulture() throws Exception {
        // Initialize the database
        cultureRepository.saveAndFlush(culture);

        int databaseSizeBeforeUpdate = cultureRepository.findAll().size();

        // Update the culture
        Culture updatedCulture = cultureRepository.findById(culture.getId()).get();
        // Disconnect from session so that the updates on updatedCulture are not directly saved in db
        em.detach(updatedCulture);
        updatedCulture
            .engraisID(UPDATED_ENGRAIS_ID)
            .libelle(UPDATED_LIBELLE)
            .prixCession(UPDATED_PRIX_CESSION)
            .estActif(UPDATED_EST_ACTIF)
            .creeLe(UPDATED_CREE_LE)
            .creePar(UPDATED_CREE_PAR)
            .modifLe(UPDATED_MODIF_LE)
            .modifPar(UPDATED_MODIF_PAR);
        CultureDTO cultureDTO = cultureMapper.toDto(updatedCulture);

        restCultureMockMvc.perform(put("/api/cultures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cultureDTO)))
            .andExpect(status().isOk());

        // Validate the Culture in the database
        List<Culture> cultureList = cultureRepository.findAll();
        assertThat(cultureList).hasSize(databaseSizeBeforeUpdate);
        Culture testCulture = cultureList.get(cultureList.size() - 1);
        assertThat(testCulture.getEngraisID()).isEqualTo(UPDATED_ENGRAIS_ID);
        assertThat(testCulture.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testCulture.getPrixCession()).isEqualTo(UPDATED_PRIX_CESSION);
        assertThat(testCulture.isEstActif()).isEqualTo(UPDATED_EST_ACTIF);
        assertThat(testCulture.getCreeLe()).isEqualTo(UPDATED_CREE_LE);
        assertThat(testCulture.getCreePar()).isEqualTo(UPDATED_CREE_PAR);
        assertThat(testCulture.getModifLe()).isEqualTo(UPDATED_MODIF_LE);
        assertThat(testCulture.getModifPar()).isEqualTo(UPDATED_MODIF_PAR);
    }

    @Test
    @Transactional
    public void updateNonExistingCulture() throws Exception {
        int databaseSizeBeforeUpdate = cultureRepository.findAll().size();

        // Create the Culture
        CultureDTO cultureDTO = cultureMapper.toDto(culture);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCultureMockMvc.perform(put("/api/cultures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cultureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Culture in the database
        List<Culture> cultureList = cultureRepository.findAll();
        assertThat(cultureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCulture() throws Exception {
        // Initialize the database
        cultureRepository.saveAndFlush(culture);

        int databaseSizeBeforeDelete = cultureRepository.findAll().size();

        // Get the culture
        restCultureMockMvc.perform(delete("/api/cultures/{id}", culture.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Culture> cultureList = cultureRepository.findAll();
        assertThat(cultureList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Culture.class);
        Culture culture1 = new Culture();
        culture1.setId(1L);
        Culture culture2 = new Culture();
        culture2.setId(culture1.getId());
        assertThat(culture1).isEqualTo(culture2);
        culture2.setId(2L);
        assertThat(culture1).isNotEqualTo(culture2);
        culture1.setId(null);
        assertThat(culture1).isNotEqualTo(culture2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CultureDTO.class);
        CultureDTO cultureDTO1 = new CultureDTO();
        cultureDTO1.setId(1L);
        CultureDTO cultureDTO2 = new CultureDTO();
        assertThat(cultureDTO1).isNotEqualTo(cultureDTO2);
        cultureDTO2.setId(cultureDTO1.getId());
        assertThat(cultureDTO1).isEqualTo(cultureDTO2);
        cultureDTO2.setId(2L);
        assertThat(cultureDTO1).isNotEqualTo(cultureDTO2);
        cultureDTO1.setId(null);
        assertThat(cultureDTO1).isNotEqualTo(cultureDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(cultureMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(cultureMapper.fromId(null)).isNull();
    }
}
