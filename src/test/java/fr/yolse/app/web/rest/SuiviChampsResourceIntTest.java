package fr.yolse.app.web.rest;

import fr.yolse.app.YolseApp;

import fr.yolse.app.domain.SuiviChamps;
import fr.yolse.app.repository.SuiviChampsRepository;
import fr.yolse.app.service.SuiviChampsService;
import fr.yolse.app.service.dto.SuiviChampsDTO;
import fr.yolse.app.service.mapper.SuiviChampsMapper;
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
 * Test class for the SuiviChampsResource REST controller.
 *
 * @see SuiviChampsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YolseApp.class)
public class SuiviChampsResourceIntTest {

    private static final Integer DEFAULT_SUIVI_ID = 1;
    private static final Integer UPDATED_SUIVI_ID = 2;

    private static final Instant DEFAULT_D_VISIT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_D_VISIT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_EMPLAC = "AAAAAAAAAA";
    private static final String UPDATED_EMPLAC = "BBBBBBBBBB";

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    private static final String DEFAULT_DOS_IMG = "AAAAAAAAAA";
    private static final String UPDATED_DOS_IMG = "BBBBBBBBBB";

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
    private SuiviChampsRepository suiviChampsRepository;


    @Autowired
    private SuiviChampsMapper suiviChampsMapper;
    

    @Autowired
    private SuiviChampsService suiviChampsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSuiviChampsMockMvc;

    private SuiviChamps suiviChamps;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SuiviChampsResource suiviChampsResource = new SuiviChampsResource(suiviChampsService);
        this.restSuiviChampsMockMvc = MockMvcBuilders.standaloneSetup(suiviChampsResource)
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
    public static SuiviChamps createEntity(EntityManager em) {
        SuiviChamps suiviChamps = new SuiviChamps()
            .suiviID(DEFAULT_SUIVI_ID)
            .dVisit(DEFAULT_D_VISIT)
            .emplac(DEFAULT_EMPLAC)
            .obs(DEFAULT_OBS)
            .dosImg(DEFAULT_DOS_IMG)
            .estActif(DEFAULT_EST_ACTIF)
            .creeLe(DEFAULT_CREE_LE)
            .creePar(DEFAULT_CREE_PAR)
            .modifLe(DEFAULT_MODIF_LE)
            .modifPar(DEFAULT_MODIF_PAR);
        return suiviChamps;
    }

    @Before
    public void initTest() {
        suiviChamps = createEntity(em);
    }

    @Test
    @Transactional
    public void createSuiviChamps() throws Exception {
        int databaseSizeBeforeCreate = suiviChampsRepository.findAll().size();

        // Create the SuiviChamps
        SuiviChampsDTO suiviChampsDTO = suiviChampsMapper.toDto(suiviChamps);
        restSuiviChampsMockMvc.perform(post("/api/suivi-champs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suiviChampsDTO)))
            .andExpect(status().isCreated());

        // Validate the SuiviChamps in the database
        List<SuiviChamps> suiviChampsList = suiviChampsRepository.findAll();
        assertThat(suiviChampsList).hasSize(databaseSizeBeforeCreate + 1);
        SuiviChamps testSuiviChamps = suiviChampsList.get(suiviChampsList.size() - 1);
        assertThat(testSuiviChamps.getSuiviID()).isEqualTo(DEFAULT_SUIVI_ID);
        assertThat(testSuiviChamps.getdVisit()).isEqualTo(DEFAULT_D_VISIT);
        assertThat(testSuiviChamps.getEmplac()).isEqualTo(DEFAULT_EMPLAC);
        assertThat(testSuiviChamps.getObs()).isEqualTo(DEFAULT_OBS);
        assertThat(testSuiviChamps.getDosImg()).isEqualTo(DEFAULT_DOS_IMG);
        assertThat(testSuiviChamps.isEstActif()).isEqualTo(DEFAULT_EST_ACTIF);
        assertThat(testSuiviChamps.getCreeLe()).isEqualTo(DEFAULT_CREE_LE);
        assertThat(testSuiviChamps.getCreePar()).isEqualTo(DEFAULT_CREE_PAR);
        assertThat(testSuiviChamps.getModifLe()).isEqualTo(DEFAULT_MODIF_LE);
        assertThat(testSuiviChamps.getModifPar()).isEqualTo(DEFAULT_MODIF_PAR);
    }

    @Test
    @Transactional
    public void createSuiviChampsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = suiviChampsRepository.findAll().size();

        // Create the SuiviChamps with an existing ID
        suiviChamps.setId(1L);
        SuiviChampsDTO suiviChampsDTO = suiviChampsMapper.toDto(suiviChamps);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSuiviChampsMockMvc.perform(post("/api/suivi-champs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suiviChampsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SuiviChamps in the database
        List<SuiviChamps> suiviChampsList = suiviChampsRepository.findAll();
        assertThat(suiviChampsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkSuiviIDIsRequired() throws Exception {
        int databaseSizeBeforeTest = suiviChampsRepository.findAll().size();
        // set the field null
        suiviChamps.setSuiviID(null);

        // Create the SuiviChamps, which fails.
        SuiviChampsDTO suiviChampsDTO = suiviChampsMapper.toDto(suiviChamps);

        restSuiviChampsMockMvc.perform(post("/api/suivi-champs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suiviChampsDTO)))
            .andExpect(status().isBadRequest());

        List<SuiviChamps> suiviChampsList = suiviChampsRepository.findAll();
        assertThat(suiviChampsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkdVisitIsRequired() throws Exception {
        int databaseSizeBeforeTest = suiviChampsRepository.findAll().size();
        // set the field null
        suiviChamps.setdVisit(null);

        // Create the SuiviChamps, which fails.
        SuiviChampsDTO suiviChampsDTO = suiviChampsMapper.toDto(suiviChamps);

        restSuiviChampsMockMvc.perform(post("/api/suivi-champs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suiviChampsDTO)))
            .andExpect(status().isBadRequest());

        List<SuiviChamps> suiviChampsList = suiviChampsRepository.findAll();
        assertThat(suiviChampsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmplacIsRequired() throws Exception {
        int databaseSizeBeforeTest = suiviChampsRepository.findAll().size();
        // set the field null
        suiviChamps.setEmplac(null);

        // Create the SuiviChamps, which fails.
        SuiviChampsDTO suiviChampsDTO = suiviChampsMapper.toDto(suiviChamps);

        restSuiviChampsMockMvc.perform(post("/api/suivi-champs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suiviChampsDTO)))
            .andExpect(status().isBadRequest());

        List<SuiviChamps> suiviChampsList = suiviChampsRepository.findAll();
        assertThat(suiviChampsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSuiviChamps() throws Exception {
        // Initialize the database
        suiviChampsRepository.saveAndFlush(suiviChamps);

        // Get all the suiviChampsList
        restSuiviChampsMockMvc.perform(get("/api/suivi-champs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(suiviChamps.getId().intValue())))
            .andExpect(jsonPath("$.[*].suiviID").value(hasItem(DEFAULT_SUIVI_ID)))
            .andExpect(jsonPath("$.[*].dVisit").value(hasItem(DEFAULT_D_VISIT.toString())))
            .andExpect(jsonPath("$.[*].emplac").value(hasItem(DEFAULT_EMPLAC.toString())))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS.toString())))
            .andExpect(jsonPath("$.[*].dosImg").value(hasItem(DEFAULT_DOS_IMG.toString())))
            .andExpect(jsonPath("$.[*].estActif").value(hasItem(DEFAULT_EST_ACTIF.booleanValue())))
            .andExpect(jsonPath("$.[*].creeLe").value(hasItem(DEFAULT_CREE_LE.toString())))
            .andExpect(jsonPath("$.[*].creePar").value(hasItem(DEFAULT_CREE_PAR.toString())))
            .andExpect(jsonPath("$.[*].modifLe").value(hasItem(DEFAULT_MODIF_LE.toString())))
            .andExpect(jsonPath("$.[*].modifPar").value(hasItem(DEFAULT_MODIF_PAR.toString())));
    }
    

    @Test
    @Transactional
    public void getSuiviChamps() throws Exception {
        // Initialize the database
        suiviChampsRepository.saveAndFlush(suiviChamps);

        // Get the suiviChamps
        restSuiviChampsMockMvc.perform(get("/api/suivi-champs/{id}", suiviChamps.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(suiviChamps.getId().intValue()))
            .andExpect(jsonPath("$.suiviID").value(DEFAULT_SUIVI_ID))
            .andExpect(jsonPath("$.dVisit").value(DEFAULT_D_VISIT.toString()))
            .andExpect(jsonPath("$.emplac").value(DEFAULT_EMPLAC.toString()))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS.toString()))
            .andExpect(jsonPath("$.dosImg").value(DEFAULT_DOS_IMG.toString()))
            .andExpect(jsonPath("$.estActif").value(DEFAULT_EST_ACTIF.booleanValue()))
            .andExpect(jsonPath("$.creeLe").value(DEFAULT_CREE_LE.toString()))
            .andExpect(jsonPath("$.creePar").value(DEFAULT_CREE_PAR.toString()))
            .andExpect(jsonPath("$.modifLe").value(DEFAULT_MODIF_LE.toString()))
            .andExpect(jsonPath("$.modifPar").value(DEFAULT_MODIF_PAR.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSuiviChamps() throws Exception {
        // Get the suiviChamps
        restSuiviChampsMockMvc.perform(get("/api/suivi-champs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSuiviChamps() throws Exception {
        // Initialize the database
        suiviChampsRepository.saveAndFlush(suiviChamps);

        int databaseSizeBeforeUpdate = suiviChampsRepository.findAll().size();

        // Update the suiviChamps
        SuiviChamps updatedSuiviChamps = suiviChampsRepository.findById(suiviChamps.getId()).get();
        // Disconnect from session so that the updates on updatedSuiviChamps are not directly saved in db
        em.detach(updatedSuiviChamps);
        updatedSuiviChamps
            .suiviID(UPDATED_SUIVI_ID)
            .dVisit(UPDATED_D_VISIT)
            .emplac(UPDATED_EMPLAC)
            .obs(UPDATED_OBS)
            .dosImg(UPDATED_DOS_IMG)
            .estActif(UPDATED_EST_ACTIF)
            .creeLe(UPDATED_CREE_LE)
            .creePar(UPDATED_CREE_PAR)
            .modifLe(UPDATED_MODIF_LE)
            .modifPar(UPDATED_MODIF_PAR);
        SuiviChampsDTO suiviChampsDTO = suiviChampsMapper.toDto(updatedSuiviChamps);

        restSuiviChampsMockMvc.perform(put("/api/suivi-champs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suiviChampsDTO)))
            .andExpect(status().isOk());

        // Validate the SuiviChamps in the database
        List<SuiviChamps> suiviChampsList = suiviChampsRepository.findAll();
        assertThat(suiviChampsList).hasSize(databaseSizeBeforeUpdate);
        SuiviChamps testSuiviChamps = suiviChampsList.get(suiviChampsList.size() - 1);
        assertThat(testSuiviChamps.getSuiviID()).isEqualTo(UPDATED_SUIVI_ID);
        assertThat(testSuiviChamps.getdVisit()).isEqualTo(UPDATED_D_VISIT);
        assertThat(testSuiviChamps.getEmplac()).isEqualTo(UPDATED_EMPLAC);
        assertThat(testSuiviChamps.getObs()).isEqualTo(UPDATED_OBS);
        assertThat(testSuiviChamps.getDosImg()).isEqualTo(UPDATED_DOS_IMG);
        assertThat(testSuiviChamps.isEstActif()).isEqualTo(UPDATED_EST_ACTIF);
        assertThat(testSuiviChamps.getCreeLe()).isEqualTo(UPDATED_CREE_LE);
        assertThat(testSuiviChamps.getCreePar()).isEqualTo(UPDATED_CREE_PAR);
        assertThat(testSuiviChamps.getModifLe()).isEqualTo(UPDATED_MODIF_LE);
        assertThat(testSuiviChamps.getModifPar()).isEqualTo(UPDATED_MODIF_PAR);
    }

    @Test
    @Transactional
    public void updateNonExistingSuiviChamps() throws Exception {
        int databaseSizeBeforeUpdate = suiviChampsRepository.findAll().size();

        // Create the SuiviChamps
        SuiviChampsDTO suiviChampsDTO = suiviChampsMapper.toDto(suiviChamps);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSuiviChampsMockMvc.perform(put("/api/suivi-champs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suiviChampsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SuiviChamps in the database
        List<SuiviChamps> suiviChampsList = suiviChampsRepository.findAll();
        assertThat(suiviChampsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSuiviChamps() throws Exception {
        // Initialize the database
        suiviChampsRepository.saveAndFlush(suiviChamps);

        int databaseSizeBeforeDelete = suiviChampsRepository.findAll().size();

        // Get the suiviChamps
        restSuiviChampsMockMvc.perform(delete("/api/suivi-champs/{id}", suiviChamps.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SuiviChamps> suiviChampsList = suiviChampsRepository.findAll();
        assertThat(suiviChampsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SuiviChamps.class);
        SuiviChamps suiviChamps1 = new SuiviChamps();
        suiviChamps1.setId(1L);
        SuiviChamps suiviChamps2 = new SuiviChamps();
        suiviChamps2.setId(suiviChamps1.getId());
        assertThat(suiviChamps1).isEqualTo(suiviChamps2);
        suiviChamps2.setId(2L);
        assertThat(suiviChamps1).isNotEqualTo(suiviChamps2);
        suiviChamps1.setId(null);
        assertThat(suiviChamps1).isNotEqualTo(suiviChamps2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SuiviChampsDTO.class);
        SuiviChampsDTO suiviChampsDTO1 = new SuiviChampsDTO();
        suiviChampsDTO1.setId(1L);
        SuiviChampsDTO suiviChampsDTO2 = new SuiviChampsDTO();
        assertThat(suiviChampsDTO1).isNotEqualTo(suiviChampsDTO2);
        suiviChampsDTO2.setId(suiviChampsDTO1.getId());
        assertThat(suiviChampsDTO1).isEqualTo(suiviChampsDTO2);
        suiviChampsDTO2.setId(2L);
        assertThat(suiviChampsDTO1).isNotEqualTo(suiviChampsDTO2);
        suiviChampsDTO1.setId(null);
        assertThat(suiviChampsDTO1).isNotEqualTo(suiviChampsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(suiviChampsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(suiviChampsMapper.fromId(null)).isNull();
    }
}
