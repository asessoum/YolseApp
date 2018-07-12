package fr.yolse.app.web.rest;

import fr.yolse.app.YolseApp;

import fr.yolse.app.domain.Utilisateur;
import fr.yolse.app.repository.UtilisateurRepository;
import fr.yolse.app.service.UtilisateurService;
import fr.yolse.app.service.dto.UtilisateurDTO;
import fr.yolse.app.service.mapper.UtilisateurMapper;
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

import fr.yolse.app.domain.enumeration.Genre;
/**
 * Test class for the UtilisateurResource REST controller.
 *
 * @see UtilisateurResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YolseApp.class)
public class UtilisateurResourceIntTest {

    private static final Integer DEFAULT_USER_ID = 1;
    private static final Integer UPDATED_USER_ID = 2;

    private static final String DEFAULT_LOGIN = "AAAAAA";
    private static final String UPDATED_LOGIN = "BBBBBB";

    private static final String DEFAULT_MDP = "AAAAAAAA";
    private static final String UPDATED_MDP = "BBBBBBBB";

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final Instant DEFAULT_NAISSANCE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NAISSANCE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Genre DEFAULT_GENRE = Genre.HOMME;
    private static final Genre UPDATED_GENRE = Genre.FEMME;

    private static final Integer DEFAULT_TEL_1 = 1;
    private static final Integer UPDATED_TEL_1 = 2;

    private static final Integer DEFAULT_TEL_2 = 1;
    private static final Integer UPDATED_TEL_2 = 2;

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

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
    private UtilisateurRepository utilisateurRepository;


    @Autowired
    private UtilisateurMapper utilisateurMapper;
    

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUtilisateurMockMvc;

    private Utilisateur utilisateur;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UtilisateurResource utilisateurResource = new UtilisateurResource(utilisateurService);
        this.restUtilisateurMockMvc = MockMvcBuilders.standaloneSetup(utilisateurResource)
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
    public static Utilisateur createEntity(EntityManager em) {
        Utilisateur utilisateur = new Utilisateur()
            .userID(DEFAULT_USER_ID)
            .login(DEFAULT_LOGIN)
            .mdp(DEFAULT_MDP)
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .naissance(DEFAULT_NAISSANCE)
            .genre(DEFAULT_GENRE)
            .tel1(DEFAULT_TEL_1)
            .tel2(DEFAULT_TEL_2)
            .email(DEFAULT_EMAIL)
            .estActif(DEFAULT_EST_ACTIF)
            .creeLe(DEFAULT_CREE_LE)
            .creePar(DEFAULT_CREE_PAR)
            .modifLe(DEFAULT_MODIF_LE)
            .modifPar(DEFAULT_MODIF_PAR);
        return utilisateur;
    }

    @Before
    public void initTest() {
        utilisateur = createEntity(em);
    }

    @Test
    @Transactional
    public void createUtilisateur() throws Exception {
        int databaseSizeBeforeCreate = utilisateurRepository.findAll().size();

        // Create the Utilisateur
        UtilisateurDTO utilisateurDTO = utilisateurMapper.toDto(utilisateur);
        restUtilisateurMockMvc.perform(post("/api/utilisateurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurDTO)))
            .andExpect(status().isCreated());

        // Validate the Utilisateur in the database
        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        assertThat(utilisateurList).hasSize(databaseSizeBeforeCreate + 1);
        Utilisateur testUtilisateur = utilisateurList.get(utilisateurList.size() - 1);
        assertThat(testUtilisateur.getUserID()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testUtilisateur.getLogin()).isEqualTo(DEFAULT_LOGIN);
        assertThat(testUtilisateur.getMdp()).isEqualTo(DEFAULT_MDP);
        assertThat(testUtilisateur.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testUtilisateur.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testUtilisateur.getNaissance()).isEqualTo(DEFAULT_NAISSANCE);
        assertThat(testUtilisateur.getGenre()).isEqualTo(DEFAULT_GENRE);
        assertThat(testUtilisateur.getTel1()).isEqualTo(DEFAULT_TEL_1);
        assertThat(testUtilisateur.getTel2()).isEqualTo(DEFAULT_TEL_2);
        assertThat(testUtilisateur.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testUtilisateur.isEstActif()).isEqualTo(DEFAULT_EST_ACTIF);
        assertThat(testUtilisateur.getCreeLe()).isEqualTo(DEFAULT_CREE_LE);
        assertThat(testUtilisateur.getCreePar()).isEqualTo(DEFAULT_CREE_PAR);
        assertThat(testUtilisateur.getModifLe()).isEqualTo(DEFAULT_MODIF_LE);
        assertThat(testUtilisateur.getModifPar()).isEqualTo(DEFAULT_MODIF_PAR);
    }

    @Test
    @Transactional
    public void createUtilisateurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = utilisateurRepository.findAll().size();

        // Create the Utilisateur with an existing ID
        utilisateur.setId(1L);
        UtilisateurDTO utilisateurDTO = utilisateurMapper.toDto(utilisateur);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUtilisateurMockMvc.perform(post("/api/utilisateurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Utilisateur in the database
        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        assertThat(utilisateurList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkUserIDIsRequired() throws Exception {
        int databaseSizeBeforeTest = utilisateurRepository.findAll().size();
        // set the field null
        utilisateur.setUserID(null);

        // Create the Utilisateur, which fails.
        UtilisateurDTO utilisateurDTO = utilisateurMapper.toDto(utilisateur);

        restUtilisateurMockMvc.perform(post("/api/utilisateurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurDTO)))
            .andExpect(status().isBadRequest());

        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        assertThat(utilisateurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLoginIsRequired() throws Exception {
        int databaseSizeBeforeTest = utilisateurRepository.findAll().size();
        // set the field null
        utilisateur.setLogin(null);

        // Create the Utilisateur, which fails.
        UtilisateurDTO utilisateurDTO = utilisateurMapper.toDto(utilisateur);

        restUtilisateurMockMvc.perform(post("/api/utilisateurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurDTO)))
            .andExpect(status().isBadRequest());

        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        assertThat(utilisateurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMdpIsRequired() throws Exception {
        int databaseSizeBeforeTest = utilisateurRepository.findAll().size();
        // set the field null
        utilisateur.setMdp(null);

        // Create the Utilisateur, which fails.
        UtilisateurDTO utilisateurDTO = utilisateurMapper.toDto(utilisateur);

        restUtilisateurMockMvc.perform(post("/api/utilisateurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurDTO)))
            .andExpect(status().isBadRequest());

        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        assertThat(utilisateurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = utilisateurRepository.findAll().size();
        // set the field null
        utilisateur.setNom(null);

        // Create the Utilisateur, which fails.
        UtilisateurDTO utilisateurDTO = utilisateurMapper.toDto(utilisateur);

        restUtilisateurMockMvc.perform(post("/api/utilisateurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurDTO)))
            .andExpect(status().isBadRequest());

        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        assertThat(utilisateurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrenomIsRequired() throws Exception {
        int databaseSizeBeforeTest = utilisateurRepository.findAll().size();
        // set the field null
        utilisateur.setPrenom(null);

        // Create the Utilisateur, which fails.
        UtilisateurDTO utilisateurDTO = utilisateurMapper.toDto(utilisateur);

        restUtilisateurMockMvc.perform(post("/api/utilisateurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurDTO)))
            .andExpect(status().isBadRequest());

        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        assertThat(utilisateurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGenreIsRequired() throws Exception {
        int databaseSizeBeforeTest = utilisateurRepository.findAll().size();
        // set the field null
        utilisateur.setGenre(null);

        // Create the Utilisateur, which fails.
        UtilisateurDTO utilisateurDTO = utilisateurMapper.toDto(utilisateur);

        restUtilisateurMockMvc.perform(post("/api/utilisateurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurDTO)))
            .andExpect(status().isBadRequest());

        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        assertThat(utilisateurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUtilisateurs() throws Exception {
        // Initialize the database
        utilisateurRepository.saveAndFlush(utilisateur);

        // Get all the utilisateurList
        restUtilisateurMockMvc.perform(get("/api/utilisateurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(utilisateur.getId().intValue())))
            .andExpect(jsonPath("$.[*].userID").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].login").value(hasItem(DEFAULT_LOGIN.toString())))
            .andExpect(jsonPath("$.[*].mdp").value(hasItem(DEFAULT_MDP.toString())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM.toString())))
            .andExpect(jsonPath("$.[*].naissance").value(hasItem(DEFAULT_NAISSANCE.toString())))
            .andExpect(jsonPath("$.[*].genre").value(hasItem(DEFAULT_GENRE.toString())))
            .andExpect(jsonPath("$.[*].tel1").value(hasItem(DEFAULT_TEL_1)))
            .andExpect(jsonPath("$.[*].tel2").value(hasItem(DEFAULT_TEL_2)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].estActif").value(hasItem(DEFAULT_EST_ACTIF.booleanValue())))
            .andExpect(jsonPath("$.[*].creeLe").value(hasItem(DEFAULT_CREE_LE.toString())))
            .andExpect(jsonPath("$.[*].creePar").value(hasItem(DEFAULT_CREE_PAR.toString())))
            .andExpect(jsonPath("$.[*].modifLe").value(hasItem(DEFAULT_MODIF_LE.toString())))
            .andExpect(jsonPath("$.[*].modifPar").value(hasItem(DEFAULT_MODIF_PAR.toString())));
    }
    

    @Test
    @Transactional
    public void getUtilisateur() throws Exception {
        // Initialize the database
        utilisateurRepository.saveAndFlush(utilisateur);

        // Get the utilisateur
        restUtilisateurMockMvc.perform(get("/api/utilisateurs/{id}", utilisateur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(utilisateur.getId().intValue()))
            .andExpect(jsonPath("$.userID").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.login").value(DEFAULT_LOGIN.toString()))
            .andExpect(jsonPath("$.mdp").value(DEFAULT_MDP.toString()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM.toString()))
            .andExpect(jsonPath("$.naissance").value(DEFAULT_NAISSANCE.toString()))
            .andExpect(jsonPath("$.genre").value(DEFAULT_GENRE.toString()))
            .andExpect(jsonPath("$.tel1").value(DEFAULT_TEL_1))
            .andExpect(jsonPath("$.tel2").value(DEFAULT_TEL_2))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.estActif").value(DEFAULT_EST_ACTIF.booleanValue()))
            .andExpect(jsonPath("$.creeLe").value(DEFAULT_CREE_LE.toString()))
            .andExpect(jsonPath("$.creePar").value(DEFAULT_CREE_PAR.toString()))
            .andExpect(jsonPath("$.modifLe").value(DEFAULT_MODIF_LE.toString()))
            .andExpect(jsonPath("$.modifPar").value(DEFAULT_MODIF_PAR.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingUtilisateur() throws Exception {
        // Get the utilisateur
        restUtilisateurMockMvc.perform(get("/api/utilisateurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUtilisateur() throws Exception {
        // Initialize the database
        utilisateurRepository.saveAndFlush(utilisateur);

        int databaseSizeBeforeUpdate = utilisateurRepository.findAll().size();

        // Update the utilisateur
        Utilisateur updatedUtilisateur = utilisateurRepository.findById(utilisateur.getId()).get();
        // Disconnect from session so that the updates on updatedUtilisateur are not directly saved in db
        em.detach(updatedUtilisateur);
        updatedUtilisateur
            .userID(UPDATED_USER_ID)
            .login(UPDATED_LOGIN)
            .mdp(UPDATED_MDP)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .naissance(UPDATED_NAISSANCE)
            .genre(UPDATED_GENRE)
            .tel1(UPDATED_TEL_1)
            .tel2(UPDATED_TEL_2)
            .email(UPDATED_EMAIL)
            .estActif(UPDATED_EST_ACTIF)
            .creeLe(UPDATED_CREE_LE)
            .creePar(UPDATED_CREE_PAR)
            .modifLe(UPDATED_MODIF_LE)
            .modifPar(UPDATED_MODIF_PAR);
        UtilisateurDTO utilisateurDTO = utilisateurMapper.toDto(updatedUtilisateur);

        restUtilisateurMockMvc.perform(put("/api/utilisateurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurDTO)))
            .andExpect(status().isOk());

        // Validate the Utilisateur in the database
        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        assertThat(utilisateurList).hasSize(databaseSizeBeforeUpdate);
        Utilisateur testUtilisateur = utilisateurList.get(utilisateurList.size() - 1);
        assertThat(testUtilisateur.getUserID()).isEqualTo(UPDATED_USER_ID);
        assertThat(testUtilisateur.getLogin()).isEqualTo(UPDATED_LOGIN);
        assertThat(testUtilisateur.getMdp()).isEqualTo(UPDATED_MDP);
        assertThat(testUtilisateur.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testUtilisateur.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testUtilisateur.getNaissance()).isEqualTo(UPDATED_NAISSANCE);
        assertThat(testUtilisateur.getGenre()).isEqualTo(UPDATED_GENRE);
        assertThat(testUtilisateur.getTel1()).isEqualTo(UPDATED_TEL_1);
        assertThat(testUtilisateur.getTel2()).isEqualTo(UPDATED_TEL_2);
        assertThat(testUtilisateur.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testUtilisateur.isEstActif()).isEqualTo(UPDATED_EST_ACTIF);
        assertThat(testUtilisateur.getCreeLe()).isEqualTo(UPDATED_CREE_LE);
        assertThat(testUtilisateur.getCreePar()).isEqualTo(UPDATED_CREE_PAR);
        assertThat(testUtilisateur.getModifLe()).isEqualTo(UPDATED_MODIF_LE);
        assertThat(testUtilisateur.getModifPar()).isEqualTo(UPDATED_MODIF_PAR);
    }

    @Test
    @Transactional
    public void updateNonExistingUtilisateur() throws Exception {
        int databaseSizeBeforeUpdate = utilisateurRepository.findAll().size();

        // Create the Utilisateur
        UtilisateurDTO utilisateurDTO = utilisateurMapper.toDto(utilisateur);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUtilisateurMockMvc.perform(put("/api/utilisateurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Utilisateur in the database
        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        assertThat(utilisateurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUtilisateur() throws Exception {
        // Initialize the database
        utilisateurRepository.saveAndFlush(utilisateur);

        int databaseSizeBeforeDelete = utilisateurRepository.findAll().size();

        // Get the utilisateur
        restUtilisateurMockMvc.perform(delete("/api/utilisateurs/{id}", utilisateur.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        assertThat(utilisateurList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Utilisateur.class);
        Utilisateur utilisateur1 = new Utilisateur();
        utilisateur1.setId(1L);
        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setId(utilisateur1.getId());
        assertThat(utilisateur1).isEqualTo(utilisateur2);
        utilisateur2.setId(2L);
        assertThat(utilisateur1).isNotEqualTo(utilisateur2);
        utilisateur1.setId(null);
        assertThat(utilisateur1).isNotEqualTo(utilisateur2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UtilisateurDTO.class);
        UtilisateurDTO utilisateurDTO1 = new UtilisateurDTO();
        utilisateurDTO1.setId(1L);
        UtilisateurDTO utilisateurDTO2 = new UtilisateurDTO();
        assertThat(utilisateurDTO1).isNotEqualTo(utilisateurDTO2);
        utilisateurDTO2.setId(utilisateurDTO1.getId());
        assertThat(utilisateurDTO1).isEqualTo(utilisateurDTO2);
        utilisateurDTO2.setId(2L);
        assertThat(utilisateurDTO1).isNotEqualTo(utilisateurDTO2);
        utilisateurDTO1.setId(null);
        assertThat(utilisateurDTO1).isNotEqualTo(utilisateurDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(utilisateurMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(utilisateurMapper.fromId(null)).isNull();
    }
}
