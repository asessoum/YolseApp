package fr.yolse.app.web.rest;

import fr.yolse.app.YolseApp;

import fr.yolse.app.domain.Profil;
import fr.yolse.app.repository.ProfilRepository;
import fr.yolse.app.service.ProfilService;
import fr.yolse.app.service.dto.ProfilDTO;
import fr.yolse.app.service.mapper.ProfilMapper;
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
 * Test class for the ProfilResource REST controller.
 *
 * @see ProfilResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YolseApp.class)
public class ProfilResourceIntTest {

    private static final Integer DEFAULT_PROFIL_ID = 1;
    private static final Integer UPDATED_PROFIL_ID = 2;

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

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
    private ProfilRepository profilRepository;


    @Autowired
    private ProfilMapper profilMapper;
    

    @Autowired
    private ProfilService profilService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProfilMockMvc;

    private Profil profil;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProfilResource profilResource = new ProfilResource(profilService);
        this.restProfilMockMvc = MockMvcBuilders.standaloneSetup(profilResource)
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
    public static Profil createEntity(EntityManager em) {
        Profil profil = new Profil()
            .profilID(DEFAULT_PROFIL_ID)
            .libelle(DEFAULT_LIBELLE)
            .estActif(DEFAULT_EST_ACTIF)
            .creeLe(DEFAULT_CREE_LE)
            .creePar(DEFAULT_CREE_PAR)
            .modifLe(DEFAULT_MODIF_LE)
            .modifPar(DEFAULT_MODIF_PAR);
        return profil;
    }

    @Before
    public void initTest() {
        profil = createEntity(em);
    }

    @Test
    @Transactional
    public void createProfil() throws Exception {
        int databaseSizeBeforeCreate = profilRepository.findAll().size();

        // Create the Profil
        ProfilDTO profilDTO = profilMapper.toDto(profil);
        restProfilMockMvc.perform(post("/api/profils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilDTO)))
            .andExpect(status().isCreated());

        // Validate the Profil in the database
        List<Profil> profilList = profilRepository.findAll();
        assertThat(profilList).hasSize(databaseSizeBeforeCreate + 1);
        Profil testProfil = profilList.get(profilList.size() - 1);
        assertThat(testProfil.getProfilID()).isEqualTo(DEFAULT_PROFIL_ID);
        assertThat(testProfil.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testProfil.isEstActif()).isEqualTo(DEFAULT_EST_ACTIF);
        assertThat(testProfil.getCreeLe()).isEqualTo(DEFAULT_CREE_LE);
        assertThat(testProfil.getCreePar()).isEqualTo(DEFAULT_CREE_PAR);
        assertThat(testProfil.getModifLe()).isEqualTo(DEFAULT_MODIF_LE);
        assertThat(testProfil.getModifPar()).isEqualTo(DEFAULT_MODIF_PAR);
    }

    @Test
    @Transactional
    public void createProfilWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = profilRepository.findAll().size();

        // Create the Profil with an existing ID
        profil.setId(1L);
        ProfilDTO profilDTO = profilMapper.toDto(profil);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProfilMockMvc.perform(post("/api/profils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Profil in the database
        List<Profil> profilList = profilRepository.findAll();
        assertThat(profilList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkProfilIDIsRequired() throws Exception {
        int databaseSizeBeforeTest = profilRepository.findAll().size();
        // set the field null
        profil.setProfilID(null);

        // Create the Profil, which fails.
        ProfilDTO profilDTO = profilMapper.toDto(profil);

        restProfilMockMvc.perform(post("/api/profils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilDTO)))
            .andExpect(status().isBadRequest());

        List<Profil> profilList = profilRepository.findAll();
        assertThat(profilList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = profilRepository.findAll().size();
        // set the field null
        profil.setLibelle(null);

        // Create the Profil, which fails.
        ProfilDTO profilDTO = profilMapper.toDto(profil);

        restProfilMockMvc.perform(post("/api/profils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilDTO)))
            .andExpect(status().isBadRequest());

        List<Profil> profilList = profilRepository.findAll();
        assertThat(profilList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProfils() throws Exception {
        // Initialize the database
        profilRepository.saveAndFlush(profil);

        // Get all the profilList
        restProfilMockMvc.perform(get("/api/profils?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(profil.getId().intValue())))
            .andExpect(jsonPath("$.[*].profilID").value(hasItem(DEFAULT_PROFIL_ID)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())))
            .andExpect(jsonPath("$.[*].estActif").value(hasItem(DEFAULT_EST_ACTIF.booleanValue())))
            .andExpect(jsonPath("$.[*].creeLe").value(hasItem(DEFAULT_CREE_LE.toString())))
            .andExpect(jsonPath("$.[*].creePar").value(hasItem(DEFAULT_CREE_PAR.toString())))
            .andExpect(jsonPath("$.[*].modifLe").value(hasItem(DEFAULT_MODIF_LE.toString())))
            .andExpect(jsonPath("$.[*].modifPar").value(hasItem(DEFAULT_MODIF_PAR.toString())));
    }
    

    @Test
    @Transactional
    public void getProfil() throws Exception {
        // Initialize the database
        profilRepository.saveAndFlush(profil);

        // Get the profil
        restProfilMockMvc.perform(get("/api/profils/{id}", profil.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(profil.getId().intValue()))
            .andExpect(jsonPath("$.profilID").value(DEFAULT_PROFIL_ID))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()))
            .andExpect(jsonPath("$.estActif").value(DEFAULT_EST_ACTIF.booleanValue()))
            .andExpect(jsonPath("$.creeLe").value(DEFAULT_CREE_LE.toString()))
            .andExpect(jsonPath("$.creePar").value(DEFAULT_CREE_PAR.toString()))
            .andExpect(jsonPath("$.modifLe").value(DEFAULT_MODIF_LE.toString()))
            .andExpect(jsonPath("$.modifPar").value(DEFAULT_MODIF_PAR.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingProfil() throws Exception {
        // Get the profil
        restProfilMockMvc.perform(get("/api/profils/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProfil() throws Exception {
        // Initialize the database
        profilRepository.saveAndFlush(profil);

        int databaseSizeBeforeUpdate = profilRepository.findAll().size();

        // Update the profil
        Profil updatedProfil = profilRepository.findById(profil.getId()).get();
        // Disconnect from session so that the updates on updatedProfil are not directly saved in db
        em.detach(updatedProfil);
        updatedProfil
            .profilID(UPDATED_PROFIL_ID)
            .libelle(UPDATED_LIBELLE)
            .estActif(UPDATED_EST_ACTIF)
            .creeLe(UPDATED_CREE_LE)
            .creePar(UPDATED_CREE_PAR)
            .modifLe(UPDATED_MODIF_LE)
            .modifPar(UPDATED_MODIF_PAR);
        ProfilDTO profilDTO = profilMapper.toDto(updatedProfil);

        restProfilMockMvc.perform(put("/api/profils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilDTO)))
            .andExpect(status().isOk());

        // Validate the Profil in the database
        List<Profil> profilList = profilRepository.findAll();
        assertThat(profilList).hasSize(databaseSizeBeforeUpdate);
        Profil testProfil = profilList.get(profilList.size() - 1);
        assertThat(testProfil.getProfilID()).isEqualTo(UPDATED_PROFIL_ID);
        assertThat(testProfil.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testProfil.isEstActif()).isEqualTo(UPDATED_EST_ACTIF);
        assertThat(testProfil.getCreeLe()).isEqualTo(UPDATED_CREE_LE);
        assertThat(testProfil.getCreePar()).isEqualTo(UPDATED_CREE_PAR);
        assertThat(testProfil.getModifLe()).isEqualTo(UPDATED_MODIF_LE);
        assertThat(testProfil.getModifPar()).isEqualTo(UPDATED_MODIF_PAR);
    }

    @Test
    @Transactional
    public void updateNonExistingProfil() throws Exception {
        int databaseSizeBeforeUpdate = profilRepository.findAll().size();

        // Create the Profil
        ProfilDTO profilDTO = profilMapper.toDto(profil);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProfilMockMvc.perform(put("/api/profils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Profil in the database
        List<Profil> profilList = profilRepository.findAll();
        assertThat(profilList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProfil() throws Exception {
        // Initialize the database
        profilRepository.saveAndFlush(profil);

        int databaseSizeBeforeDelete = profilRepository.findAll().size();

        // Get the profil
        restProfilMockMvc.perform(delete("/api/profils/{id}", profil.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Profil> profilList = profilRepository.findAll();
        assertThat(profilList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Profil.class);
        Profil profil1 = new Profil();
        profil1.setId(1L);
        Profil profil2 = new Profil();
        profil2.setId(profil1.getId());
        assertThat(profil1).isEqualTo(profil2);
        profil2.setId(2L);
        assertThat(profil1).isNotEqualTo(profil2);
        profil1.setId(null);
        assertThat(profil1).isNotEqualTo(profil2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProfilDTO.class);
        ProfilDTO profilDTO1 = new ProfilDTO();
        profilDTO1.setId(1L);
        ProfilDTO profilDTO2 = new ProfilDTO();
        assertThat(profilDTO1).isNotEqualTo(profilDTO2);
        profilDTO2.setId(profilDTO1.getId());
        assertThat(profilDTO1).isEqualTo(profilDTO2);
        profilDTO2.setId(2L);
        assertThat(profilDTO1).isNotEqualTo(profilDTO2);
        profilDTO1.setId(null);
        assertThat(profilDTO1).isNotEqualTo(profilDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(profilMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(profilMapper.fromId(null)).isNull();
    }
}
