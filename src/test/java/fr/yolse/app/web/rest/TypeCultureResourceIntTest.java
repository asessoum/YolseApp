package fr.yolse.app.web.rest;

import fr.yolse.app.YolseApp;

import fr.yolse.app.domain.TypeCulture;
import fr.yolse.app.repository.TypeCultureRepository;
import fr.yolse.app.service.TypeCultureService;
import fr.yolse.app.service.dto.TypeCultureDTO;
import fr.yolse.app.service.mapper.TypeCultureMapper;
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
 * Test class for the TypeCultureResource REST controller.
 *
 * @see TypeCultureResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YolseApp.class)
public class TypeCultureResourceIntTest {

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
    private TypeCultureRepository typeCultureRepository;


    @Autowired
    private TypeCultureMapper typeCultureMapper;
    

    @Autowired
    private TypeCultureService typeCultureService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTypeCultureMockMvc;

    private TypeCulture typeCulture;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TypeCultureResource typeCultureResource = new TypeCultureResource(typeCultureService);
        this.restTypeCultureMockMvc = MockMvcBuilders.standaloneSetup(typeCultureResource)
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
    public static TypeCulture createEntity(EntityManager em) {
        TypeCulture typeCulture = new TypeCulture()
            .engraisID(DEFAULT_ENGRAIS_ID)
            .libelle(DEFAULT_LIBELLE)
            .prixCession(DEFAULT_PRIX_CESSION)
            .estActif(DEFAULT_EST_ACTIF)
            .creeLe(DEFAULT_CREE_LE)
            .creePar(DEFAULT_CREE_PAR)
            .modifLe(DEFAULT_MODIF_LE)
            .modifPar(DEFAULT_MODIF_PAR);
        return typeCulture;
    }

    @Before
    public void initTest() {
        typeCulture = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeCulture() throws Exception {
        int databaseSizeBeforeCreate = typeCultureRepository.findAll().size();

        // Create the TypeCulture
        TypeCultureDTO typeCultureDTO = typeCultureMapper.toDto(typeCulture);
        restTypeCultureMockMvc.perform(post("/api/type-cultures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeCultureDTO)))
            .andExpect(status().isCreated());

        // Validate the TypeCulture in the database
        List<TypeCulture> typeCultureList = typeCultureRepository.findAll();
        assertThat(typeCultureList).hasSize(databaseSizeBeforeCreate + 1);
        TypeCulture testTypeCulture = typeCultureList.get(typeCultureList.size() - 1);
        assertThat(testTypeCulture.getEngraisID()).isEqualTo(DEFAULT_ENGRAIS_ID);
        assertThat(testTypeCulture.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testTypeCulture.getPrixCession()).isEqualTo(DEFAULT_PRIX_CESSION);
        assertThat(testTypeCulture.isEstActif()).isEqualTo(DEFAULT_EST_ACTIF);
        assertThat(testTypeCulture.getCreeLe()).isEqualTo(DEFAULT_CREE_LE);
        assertThat(testTypeCulture.getCreePar()).isEqualTo(DEFAULT_CREE_PAR);
        assertThat(testTypeCulture.getModifLe()).isEqualTo(DEFAULT_MODIF_LE);
        assertThat(testTypeCulture.getModifPar()).isEqualTo(DEFAULT_MODIF_PAR);
    }

    @Test
    @Transactional
    public void createTypeCultureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeCultureRepository.findAll().size();

        // Create the TypeCulture with an existing ID
        typeCulture.setId(1L);
        TypeCultureDTO typeCultureDTO = typeCultureMapper.toDto(typeCulture);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeCultureMockMvc.perform(post("/api/type-cultures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeCultureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeCulture in the database
        List<TypeCulture> typeCultureList = typeCultureRepository.findAll();
        assertThat(typeCultureList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkEngraisIDIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeCultureRepository.findAll().size();
        // set the field null
        typeCulture.setEngraisID(null);

        // Create the TypeCulture, which fails.
        TypeCultureDTO typeCultureDTO = typeCultureMapper.toDto(typeCulture);

        restTypeCultureMockMvc.perform(post("/api/type-cultures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeCultureDTO)))
            .andExpect(status().isBadRequest());

        List<TypeCulture> typeCultureList = typeCultureRepository.findAll();
        assertThat(typeCultureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeCultureRepository.findAll().size();
        // set the field null
        typeCulture.setLibelle(null);

        // Create the TypeCulture, which fails.
        TypeCultureDTO typeCultureDTO = typeCultureMapper.toDto(typeCulture);

        restTypeCultureMockMvc.perform(post("/api/type-cultures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeCultureDTO)))
            .andExpect(status().isBadRequest());

        List<TypeCulture> typeCultureList = typeCultureRepository.findAll();
        assertThat(typeCultureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTypeCultures() throws Exception {
        // Initialize the database
        typeCultureRepository.saveAndFlush(typeCulture);

        // Get all the typeCultureList
        restTypeCultureMockMvc.perform(get("/api/type-cultures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeCulture.getId().intValue())))
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
    public void getTypeCulture() throws Exception {
        // Initialize the database
        typeCultureRepository.saveAndFlush(typeCulture);

        // Get the typeCulture
        restTypeCultureMockMvc.perform(get("/api/type-cultures/{id}", typeCulture.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(typeCulture.getId().intValue()))
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
    public void getNonExistingTypeCulture() throws Exception {
        // Get the typeCulture
        restTypeCultureMockMvc.perform(get("/api/type-cultures/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeCulture() throws Exception {
        // Initialize the database
        typeCultureRepository.saveAndFlush(typeCulture);

        int databaseSizeBeforeUpdate = typeCultureRepository.findAll().size();

        // Update the typeCulture
        TypeCulture updatedTypeCulture = typeCultureRepository.findById(typeCulture.getId()).get();
        // Disconnect from session so that the updates on updatedTypeCulture are not directly saved in db
        em.detach(updatedTypeCulture);
        updatedTypeCulture
            .engraisID(UPDATED_ENGRAIS_ID)
            .libelle(UPDATED_LIBELLE)
            .prixCession(UPDATED_PRIX_CESSION)
            .estActif(UPDATED_EST_ACTIF)
            .creeLe(UPDATED_CREE_LE)
            .creePar(UPDATED_CREE_PAR)
            .modifLe(UPDATED_MODIF_LE)
            .modifPar(UPDATED_MODIF_PAR);
        TypeCultureDTO typeCultureDTO = typeCultureMapper.toDto(updatedTypeCulture);

        restTypeCultureMockMvc.perform(put("/api/type-cultures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeCultureDTO)))
            .andExpect(status().isOk());

        // Validate the TypeCulture in the database
        List<TypeCulture> typeCultureList = typeCultureRepository.findAll();
        assertThat(typeCultureList).hasSize(databaseSizeBeforeUpdate);
        TypeCulture testTypeCulture = typeCultureList.get(typeCultureList.size() - 1);
        assertThat(testTypeCulture.getEngraisID()).isEqualTo(UPDATED_ENGRAIS_ID);
        assertThat(testTypeCulture.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testTypeCulture.getPrixCession()).isEqualTo(UPDATED_PRIX_CESSION);
        assertThat(testTypeCulture.isEstActif()).isEqualTo(UPDATED_EST_ACTIF);
        assertThat(testTypeCulture.getCreeLe()).isEqualTo(UPDATED_CREE_LE);
        assertThat(testTypeCulture.getCreePar()).isEqualTo(UPDATED_CREE_PAR);
        assertThat(testTypeCulture.getModifLe()).isEqualTo(UPDATED_MODIF_LE);
        assertThat(testTypeCulture.getModifPar()).isEqualTo(UPDATED_MODIF_PAR);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeCulture() throws Exception {
        int databaseSizeBeforeUpdate = typeCultureRepository.findAll().size();

        // Create the TypeCulture
        TypeCultureDTO typeCultureDTO = typeCultureMapper.toDto(typeCulture);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTypeCultureMockMvc.perform(put("/api/type-cultures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeCultureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeCulture in the database
        List<TypeCulture> typeCultureList = typeCultureRepository.findAll();
        assertThat(typeCultureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeCulture() throws Exception {
        // Initialize the database
        typeCultureRepository.saveAndFlush(typeCulture);

        int databaseSizeBeforeDelete = typeCultureRepository.findAll().size();

        // Get the typeCulture
        restTypeCultureMockMvc.perform(delete("/api/type-cultures/{id}", typeCulture.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TypeCulture> typeCultureList = typeCultureRepository.findAll();
        assertThat(typeCultureList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeCulture.class);
        TypeCulture typeCulture1 = new TypeCulture();
        typeCulture1.setId(1L);
        TypeCulture typeCulture2 = new TypeCulture();
        typeCulture2.setId(typeCulture1.getId());
        assertThat(typeCulture1).isEqualTo(typeCulture2);
        typeCulture2.setId(2L);
        assertThat(typeCulture1).isNotEqualTo(typeCulture2);
        typeCulture1.setId(null);
        assertThat(typeCulture1).isNotEqualTo(typeCulture2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeCultureDTO.class);
        TypeCultureDTO typeCultureDTO1 = new TypeCultureDTO();
        typeCultureDTO1.setId(1L);
        TypeCultureDTO typeCultureDTO2 = new TypeCultureDTO();
        assertThat(typeCultureDTO1).isNotEqualTo(typeCultureDTO2);
        typeCultureDTO2.setId(typeCultureDTO1.getId());
        assertThat(typeCultureDTO1).isEqualTo(typeCultureDTO2);
        typeCultureDTO2.setId(2L);
        assertThat(typeCultureDTO1).isNotEqualTo(typeCultureDTO2);
        typeCultureDTO1.setId(null);
        assertThat(typeCultureDTO1).isNotEqualTo(typeCultureDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(typeCultureMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(typeCultureMapper.fromId(null)).isNull();
    }
}
