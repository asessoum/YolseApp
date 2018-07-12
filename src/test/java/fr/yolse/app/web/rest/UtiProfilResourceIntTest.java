package fr.yolse.app.web.rest;

import fr.yolse.app.YolseApp;

import fr.yolse.app.domain.UtiProfil;
import fr.yolse.app.repository.UtiProfilRepository;
import fr.yolse.app.service.UtiProfilService;
import fr.yolse.app.service.dto.UtiProfilDTO;
import fr.yolse.app.service.mapper.UtiProfilMapper;
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
 * Test class for the UtiProfilResource REST controller.
 *
 * @see UtiProfilResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YolseApp.class)
public class UtiProfilResourceIntTest {

    private static final Integer DEFAULT_UTI_PRO_ID = 1;
    private static final Integer UPDATED_UTI_PRO_ID = 2;

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
    private UtiProfilRepository utiProfilRepository;


    @Autowired
    private UtiProfilMapper utiProfilMapper;
    

    @Autowired
    private UtiProfilService utiProfilService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUtiProfilMockMvc;

    private UtiProfil utiProfil;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UtiProfilResource utiProfilResource = new UtiProfilResource(utiProfilService);
        this.restUtiProfilMockMvc = MockMvcBuilders.standaloneSetup(utiProfilResource)
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
    public static UtiProfil createEntity(EntityManager em) {
        UtiProfil utiProfil = new UtiProfil()
            .utiProID(DEFAULT_UTI_PRO_ID)
            .estActif(DEFAULT_EST_ACTIF)
            .creeLe(DEFAULT_CREE_LE)
            .creePar(DEFAULT_CREE_PAR)
            .modifLe(DEFAULT_MODIF_LE)
            .modifPar(DEFAULT_MODIF_PAR);
        return utiProfil;
    }

    @Before
    public void initTest() {
        utiProfil = createEntity(em);
    }

    @Test
    @Transactional
    public void createUtiProfil() throws Exception {
        int databaseSizeBeforeCreate = utiProfilRepository.findAll().size();

        // Create the UtiProfil
        UtiProfilDTO utiProfilDTO = utiProfilMapper.toDto(utiProfil);
        restUtiProfilMockMvc.perform(post("/api/uti-profils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utiProfilDTO)))
            .andExpect(status().isCreated());

        // Validate the UtiProfil in the database
        List<UtiProfil> utiProfilList = utiProfilRepository.findAll();
        assertThat(utiProfilList).hasSize(databaseSizeBeforeCreate + 1);
        UtiProfil testUtiProfil = utiProfilList.get(utiProfilList.size() - 1);
        assertThat(testUtiProfil.getUtiProID()).isEqualTo(DEFAULT_UTI_PRO_ID);
        assertThat(testUtiProfil.isEstActif()).isEqualTo(DEFAULT_EST_ACTIF);
        assertThat(testUtiProfil.getCreeLe()).isEqualTo(DEFAULT_CREE_LE);
        assertThat(testUtiProfil.getCreePar()).isEqualTo(DEFAULT_CREE_PAR);
        assertThat(testUtiProfil.getModifLe()).isEqualTo(DEFAULT_MODIF_LE);
        assertThat(testUtiProfil.getModifPar()).isEqualTo(DEFAULT_MODIF_PAR);
    }

    @Test
    @Transactional
    public void createUtiProfilWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = utiProfilRepository.findAll().size();

        // Create the UtiProfil with an existing ID
        utiProfil.setId(1L);
        UtiProfilDTO utiProfilDTO = utiProfilMapper.toDto(utiProfil);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUtiProfilMockMvc.perform(post("/api/uti-profils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utiProfilDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UtiProfil in the database
        List<UtiProfil> utiProfilList = utiProfilRepository.findAll();
        assertThat(utiProfilList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkUtiProIDIsRequired() throws Exception {
        int databaseSizeBeforeTest = utiProfilRepository.findAll().size();
        // set the field null
        utiProfil.setUtiProID(null);

        // Create the UtiProfil, which fails.
        UtiProfilDTO utiProfilDTO = utiProfilMapper.toDto(utiProfil);

        restUtiProfilMockMvc.perform(post("/api/uti-profils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utiProfilDTO)))
            .andExpect(status().isBadRequest());

        List<UtiProfil> utiProfilList = utiProfilRepository.findAll();
        assertThat(utiProfilList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUtiProfils() throws Exception {
        // Initialize the database
        utiProfilRepository.saveAndFlush(utiProfil);

        // Get all the utiProfilList
        restUtiProfilMockMvc.perform(get("/api/uti-profils?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(utiProfil.getId().intValue())))
            .andExpect(jsonPath("$.[*].utiProID").value(hasItem(DEFAULT_UTI_PRO_ID)))
            .andExpect(jsonPath("$.[*].estActif").value(hasItem(DEFAULT_EST_ACTIF.booleanValue())))
            .andExpect(jsonPath("$.[*].creeLe").value(hasItem(DEFAULT_CREE_LE.toString())))
            .andExpect(jsonPath("$.[*].creePar").value(hasItem(DEFAULT_CREE_PAR.toString())))
            .andExpect(jsonPath("$.[*].modifLe").value(hasItem(DEFAULT_MODIF_LE.toString())))
            .andExpect(jsonPath("$.[*].modifPar").value(hasItem(DEFAULT_MODIF_PAR.toString())));
    }
    

    @Test
    @Transactional
    public void getUtiProfil() throws Exception {
        // Initialize the database
        utiProfilRepository.saveAndFlush(utiProfil);

        // Get the utiProfil
        restUtiProfilMockMvc.perform(get("/api/uti-profils/{id}", utiProfil.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(utiProfil.getId().intValue()))
            .andExpect(jsonPath("$.utiProID").value(DEFAULT_UTI_PRO_ID))
            .andExpect(jsonPath("$.estActif").value(DEFAULT_EST_ACTIF.booleanValue()))
            .andExpect(jsonPath("$.creeLe").value(DEFAULT_CREE_LE.toString()))
            .andExpect(jsonPath("$.creePar").value(DEFAULT_CREE_PAR.toString()))
            .andExpect(jsonPath("$.modifLe").value(DEFAULT_MODIF_LE.toString()))
            .andExpect(jsonPath("$.modifPar").value(DEFAULT_MODIF_PAR.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingUtiProfil() throws Exception {
        // Get the utiProfil
        restUtiProfilMockMvc.perform(get("/api/uti-profils/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUtiProfil() throws Exception {
        // Initialize the database
        utiProfilRepository.saveAndFlush(utiProfil);

        int databaseSizeBeforeUpdate = utiProfilRepository.findAll().size();

        // Update the utiProfil
        UtiProfil updatedUtiProfil = utiProfilRepository.findById(utiProfil.getId()).get();
        // Disconnect from session so that the updates on updatedUtiProfil are not directly saved in db
        em.detach(updatedUtiProfil);
        updatedUtiProfil
            .utiProID(UPDATED_UTI_PRO_ID)
            .estActif(UPDATED_EST_ACTIF)
            .creeLe(UPDATED_CREE_LE)
            .creePar(UPDATED_CREE_PAR)
            .modifLe(UPDATED_MODIF_LE)
            .modifPar(UPDATED_MODIF_PAR);
        UtiProfilDTO utiProfilDTO = utiProfilMapper.toDto(updatedUtiProfil);

        restUtiProfilMockMvc.perform(put("/api/uti-profils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utiProfilDTO)))
            .andExpect(status().isOk());

        // Validate the UtiProfil in the database
        List<UtiProfil> utiProfilList = utiProfilRepository.findAll();
        assertThat(utiProfilList).hasSize(databaseSizeBeforeUpdate);
        UtiProfil testUtiProfil = utiProfilList.get(utiProfilList.size() - 1);
        assertThat(testUtiProfil.getUtiProID()).isEqualTo(UPDATED_UTI_PRO_ID);
        assertThat(testUtiProfil.isEstActif()).isEqualTo(UPDATED_EST_ACTIF);
        assertThat(testUtiProfil.getCreeLe()).isEqualTo(UPDATED_CREE_LE);
        assertThat(testUtiProfil.getCreePar()).isEqualTo(UPDATED_CREE_PAR);
        assertThat(testUtiProfil.getModifLe()).isEqualTo(UPDATED_MODIF_LE);
        assertThat(testUtiProfil.getModifPar()).isEqualTo(UPDATED_MODIF_PAR);
    }

    @Test
    @Transactional
    public void updateNonExistingUtiProfil() throws Exception {
        int databaseSizeBeforeUpdate = utiProfilRepository.findAll().size();

        // Create the UtiProfil
        UtiProfilDTO utiProfilDTO = utiProfilMapper.toDto(utiProfil);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUtiProfilMockMvc.perform(put("/api/uti-profils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utiProfilDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UtiProfil in the database
        List<UtiProfil> utiProfilList = utiProfilRepository.findAll();
        assertThat(utiProfilList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUtiProfil() throws Exception {
        // Initialize the database
        utiProfilRepository.saveAndFlush(utiProfil);

        int databaseSizeBeforeDelete = utiProfilRepository.findAll().size();

        // Get the utiProfil
        restUtiProfilMockMvc.perform(delete("/api/uti-profils/{id}", utiProfil.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UtiProfil> utiProfilList = utiProfilRepository.findAll();
        assertThat(utiProfilList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UtiProfil.class);
        UtiProfil utiProfil1 = new UtiProfil();
        utiProfil1.setId(1L);
        UtiProfil utiProfil2 = new UtiProfil();
        utiProfil2.setId(utiProfil1.getId());
        assertThat(utiProfil1).isEqualTo(utiProfil2);
        utiProfil2.setId(2L);
        assertThat(utiProfil1).isNotEqualTo(utiProfil2);
        utiProfil1.setId(null);
        assertThat(utiProfil1).isNotEqualTo(utiProfil2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UtiProfilDTO.class);
        UtiProfilDTO utiProfilDTO1 = new UtiProfilDTO();
        utiProfilDTO1.setId(1L);
        UtiProfilDTO utiProfilDTO2 = new UtiProfilDTO();
        assertThat(utiProfilDTO1).isNotEqualTo(utiProfilDTO2);
        utiProfilDTO2.setId(utiProfilDTO1.getId());
        assertThat(utiProfilDTO1).isEqualTo(utiProfilDTO2);
        utiProfilDTO2.setId(2L);
        assertThat(utiProfilDTO1).isNotEqualTo(utiProfilDTO2);
        utiProfilDTO1.setId(null);
        assertThat(utiProfilDTO1).isNotEqualTo(utiProfilDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(utiProfilMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(utiProfilMapper.fromId(null)).isNull();
    }
}
