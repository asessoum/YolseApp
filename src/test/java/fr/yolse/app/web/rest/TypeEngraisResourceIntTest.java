package fr.yolse.app.web.rest;

import fr.yolse.app.YolseApp;

import fr.yolse.app.domain.TypeEngrais;
import fr.yolse.app.repository.TypeEngraisRepository;
import fr.yolse.app.service.TypeEngraisService;
import fr.yolse.app.service.dto.TypeEngraisDTO;
import fr.yolse.app.service.mapper.TypeEngraisMapper;
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
 * Test class for the TypeEngraisResource REST controller.
 *
 * @see TypeEngraisResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YolseApp.class)
public class TypeEngraisResourceIntTest {

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
    private TypeEngraisRepository typeEngraisRepository;


    @Autowired
    private TypeEngraisMapper typeEngraisMapper;
    

    @Autowired
    private TypeEngraisService typeEngraisService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTypeEngraisMockMvc;

    private TypeEngrais typeEngrais;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TypeEngraisResource typeEngraisResource = new TypeEngraisResource(typeEngraisService);
        this.restTypeEngraisMockMvc = MockMvcBuilders.standaloneSetup(typeEngraisResource)
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
    public static TypeEngrais createEntity(EntityManager em) {
        TypeEngrais typeEngrais = new TypeEngrais()
            .engraisID(DEFAULT_ENGRAIS_ID)
            .libelle(DEFAULT_LIBELLE)
            .prixUnitaire(DEFAULT_PRIX_UNITAIRE)
            .estActif(DEFAULT_EST_ACTIF)
            .creeLe(DEFAULT_CREE_LE)
            .creePar(DEFAULT_CREE_PAR)
            .modifLe(DEFAULT_MODIF_LE)
            .modifPar(DEFAULT_MODIF_PAR);
        return typeEngrais;
    }

    @Before
    public void initTest() {
        typeEngrais = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeEngrais() throws Exception {
        int databaseSizeBeforeCreate = typeEngraisRepository.findAll().size();

        // Create the TypeEngrais
        TypeEngraisDTO typeEngraisDTO = typeEngraisMapper.toDto(typeEngrais);
        restTypeEngraisMockMvc.perform(post("/api/type-engrais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeEngraisDTO)))
            .andExpect(status().isCreated());

        // Validate the TypeEngrais in the database
        List<TypeEngrais> typeEngraisList = typeEngraisRepository.findAll();
        assertThat(typeEngraisList).hasSize(databaseSizeBeforeCreate + 1);
        TypeEngrais testTypeEngrais = typeEngraisList.get(typeEngraisList.size() - 1);
        assertThat(testTypeEngrais.getEngraisID()).isEqualTo(DEFAULT_ENGRAIS_ID);
        assertThat(testTypeEngrais.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testTypeEngrais.getPrixUnitaire()).isEqualTo(DEFAULT_PRIX_UNITAIRE);
        assertThat(testTypeEngrais.isEstActif()).isEqualTo(DEFAULT_EST_ACTIF);
        assertThat(testTypeEngrais.getCreeLe()).isEqualTo(DEFAULT_CREE_LE);
        assertThat(testTypeEngrais.getCreePar()).isEqualTo(DEFAULT_CREE_PAR);
        assertThat(testTypeEngrais.getModifLe()).isEqualTo(DEFAULT_MODIF_LE);
        assertThat(testTypeEngrais.getModifPar()).isEqualTo(DEFAULT_MODIF_PAR);
    }

    @Test
    @Transactional
    public void createTypeEngraisWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeEngraisRepository.findAll().size();

        // Create the TypeEngrais with an existing ID
        typeEngrais.setId(1L);
        TypeEngraisDTO typeEngraisDTO = typeEngraisMapper.toDto(typeEngrais);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeEngraisMockMvc.perform(post("/api/type-engrais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeEngraisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeEngrais in the database
        List<TypeEngrais> typeEngraisList = typeEngraisRepository.findAll();
        assertThat(typeEngraisList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkEngraisIDIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeEngraisRepository.findAll().size();
        // set the field null
        typeEngrais.setEngraisID(null);

        // Create the TypeEngrais, which fails.
        TypeEngraisDTO typeEngraisDTO = typeEngraisMapper.toDto(typeEngrais);

        restTypeEngraisMockMvc.perform(post("/api/type-engrais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeEngraisDTO)))
            .andExpect(status().isBadRequest());

        List<TypeEngrais> typeEngraisList = typeEngraisRepository.findAll();
        assertThat(typeEngraisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeEngraisRepository.findAll().size();
        // set the field null
        typeEngrais.setLibelle(null);

        // Create the TypeEngrais, which fails.
        TypeEngraisDTO typeEngraisDTO = typeEngraisMapper.toDto(typeEngrais);

        restTypeEngraisMockMvc.perform(post("/api/type-engrais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeEngraisDTO)))
            .andExpect(status().isBadRequest());

        List<TypeEngrais> typeEngraisList = typeEngraisRepository.findAll();
        assertThat(typeEngraisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTypeEngrais() throws Exception {
        // Initialize the database
        typeEngraisRepository.saveAndFlush(typeEngrais);

        // Get all the typeEngraisList
        restTypeEngraisMockMvc.perform(get("/api/type-engrais?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeEngrais.getId().intValue())))
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
    public void getTypeEngrais() throws Exception {
        // Initialize the database
        typeEngraisRepository.saveAndFlush(typeEngrais);

        // Get the typeEngrais
        restTypeEngraisMockMvc.perform(get("/api/type-engrais/{id}", typeEngrais.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(typeEngrais.getId().intValue()))
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
    public void getNonExistingTypeEngrais() throws Exception {
        // Get the typeEngrais
        restTypeEngraisMockMvc.perform(get("/api/type-engrais/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeEngrais() throws Exception {
        // Initialize the database
        typeEngraisRepository.saveAndFlush(typeEngrais);

        int databaseSizeBeforeUpdate = typeEngraisRepository.findAll().size();

        // Update the typeEngrais
        TypeEngrais updatedTypeEngrais = typeEngraisRepository.findById(typeEngrais.getId()).get();
        // Disconnect from session so that the updates on updatedTypeEngrais are not directly saved in db
        em.detach(updatedTypeEngrais);
        updatedTypeEngrais
            .engraisID(UPDATED_ENGRAIS_ID)
            .libelle(UPDATED_LIBELLE)
            .prixUnitaire(UPDATED_PRIX_UNITAIRE)
            .estActif(UPDATED_EST_ACTIF)
            .creeLe(UPDATED_CREE_LE)
            .creePar(UPDATED_CREE_PAR)
            .modifLe(UPDATED_MODIF_LE)
            .modifPar(UPDATED_MODIF_PAR);
        TypeEngraisDTO typeEngraisDTO = typeEngraisMapper.toDto(updatedTypeEngrais);

        restTypeEngraisMockMvc.perform(put("/api/type-engrais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeEngraisDTO)))
            .andExpect(status().isOk());

        // Validate the TypeEngrais in the database
        List<TypeEngrais> typeEngraisList = typeEngraisRepository.findAll();
        assertThat(typeEngraisList).hasSize(databaseSizeBeforeUpdate);
        TypeEngrais testTypeEngrais = typeEngraisList.get(typeEngraisList.size() - 1);
        assertThat(testTypeEngrais.getEngraisID()).isEqualTo(UPDATED_ENGRAIS_ID);
        assertThat(testTypeEngrais.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testTypeEngrais.getPrixUnitaire()).isEqualTo(UPDATED_PRIX_UNITAIRE);
        assertThat(testTypeEngrais.isEstActif()).isEqualTo(UPDATED_EST_ACTIF);
        assertThat(testTypeEngrais.getCreeLe()).isEqualTo(UPDATED_CREE_LE);
        assertThat(testTypeEngrais.getCreePar()).isEqualTo(UPDATED_CREE_PAR);
        assertThat(testTypeEngrais.getModifLe()).isEqualTo(UPDATED_MODIF_LE);
        assertThat(testTypeEngrais.getModifPar()).isEqualTo(UPDATED_MODIF_PAR);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeEngrais() throws Exception {
        int databaseSizeBeforeUpdate = typeEngraisRepository.findAll().size();

        // Create the TypeEngrais
        TypeEngraisDTO typeEngraisDTO = typeEngraisMapper.toDto(typeEngrais);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTypeEngraisMockMvc.perform(put("/api/type-engrais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeEngraisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeEngrais in the database
        List<TypeEngrais> typeEngraisList = typeEngraisRepository.findAll();
        assertThat(typeEngraisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeEngrais() throws Exception {
        // Initialize the database
        typeEngraisRepository.saveAndFlush(typeEngrais);

        int databaseSizeBeforeDelete = typeEngraisRepository.findAll().size();

        // Get the typeEngrais
        restTypeEngraisMockMvc.perform(delete("/api/type-engrais/{id}", typeEngrais.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TypeEngrais> typeEngraisList = typeEngraisRepository.findAll();
        assertThat(typeEngraisList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeEngrais.class);
        TypeEngrais typeEngrais1 = new TypeEngrais();
        typeEngrais1.setId(1L);
        TypeEngrais typeEngrais2 = new TypeEngrais();
        typeEngrais2.setId(typeEngrais1.getId());
        assertThat(typeEngrais1).isEqualTo(typeEngrais2);
        typeEngrais2.setId(2L);
        assertThat(typeEngrais1).isNotEqualTo(typeEngrais2);
        typeEngrais1.setId(null);
        assertThat(typeEngrais1).isNotEqualTo(typeEngrais2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeEngraisDTO.class);
        TypeEngraisDTO typeEngraisDTO1 = new TypeEngraisDTO();
        typeEngraisDTO1.setId(1L);
        TypeEngraisDTO typeEngraisDTO2 = new TypeEngraisDTO();
        assertThat(typeEngraisDTO1).isNotEqualTo(typeEngraisDTO2);
        typeEngraisDTO2.setId(typeEngraisDTO1.getId());
        assertThat(typeEngraisDTO1).isEqualTo(typeEngraisDTO2);
        typeEngraisDTO2.setId(2L);
        assertThat(typeEngraisDTO1).isNotEqualTo(typeEngraisDTO2);
        typeEngraisDTO1.setId(null);
        assertThat(typeEngraisDTO1).isNotEqualTo(typeEngraisDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(typeEngraisMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(typeEngraisMapper.fromId(null)).isNull();
    }
}
