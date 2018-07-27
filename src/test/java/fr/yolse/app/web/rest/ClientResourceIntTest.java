package fr.yolse.app.web.rest;

import fr.yolse.app.YolseApp;

import fr.yolse.app.domain.Client;
import fr.yolse.app.repository.ClientRepository;
import fr.yolse.app.service.ClientService;
import fr.yolse.app.service.dto.ClientDTO;
import fr.yolse.app.service.mapper.ClientMapper;
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
 * Test class for the ClientResource REST controller.
 *
 * @see ClientResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YolseApp.class)
public class ClientResourceIntTest {

    private static final String DEFAULT_CLIENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final Instant DEFAULT_NAISSANCE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NAISSANCE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Genre DEFAULT_GENRE = Genre.HOMME;
    private static final Genre UPDATED_GENRE = Genre.FEMME;

    private static final Boolean DEFAULT_EST_MARIE = false;
    private static final Boolean UPDATED_EST_MARIE = true;

    private static final String DEFAULT_NUM_CARTE_CLI = "AAAAAAAAAA";
    private static final String UPDATED_NUM_CARTE_CLI = "BBBBBBBBBB";

    private static final Instant DEFAULT_D_CARTE_UTIL = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_D_CARTE_UTIL = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_VILLAGE = "AAAAAAAAAA";
    private static final String UPDATED_VILLAGE = "BBBBBBBBBB";

    private static final String DEFAULT_TEL = "AAAAAAAAAA";
    private static final String UPDATED_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_GROUPE = "AAAAAAAAAA";
    private static final String UPDATED_GROUPE = "BBBBBBBBBB";

    private static final String DEFAULT_PHOTO_ID = "AAAAAAAAAA";
    private static final String UPDATED_PHOTO_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_TAILLE_MENAGE = 1;
    private static final Integer UPDATED_TAILLE_MENAGE = 2;

    private static final Double DEFAULT_SUPERFICIE_POS = 1D;
    private static final Double UPDATED_SUPERFICIE_POS = 2D;

    private static final String DEFAULT_NOM_PAP = "AAAAAAAAAA";
    private static final String UPDATED_NOM_PAP = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM_PAP = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM_PAP = "BBBBBBBBBB";

    private static final String DEFAULT_TEL_PAP = "AAAAAAAAAA";
    private static final String UPDATED_TEL_PAP = "BBBBBBBBBB";

    private static final String DEFAULT_LIEN_PAP = "AAAAAAAAAA";
    private static final String UPDATED_LIEN_PAP = "BBBBBBBBBB";

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
    private ClientRepository clientRepository;


    @Autowired
    private ClientMapper clientMapper;
    

    @Autowired
    private ClientService clientService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restClientMockMvc;

    private Client client;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ClientResource clientResource = new ClientResource(clientService);
        this.restClientMockMvc = MockMvcBuilders.standaloneSetup(clientResource)
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
    public static Client createEntity(EntityManager em) {
        Client client = new Client()
            .clientID(DEFAULT_CLIENT_ID)
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .naissance(DEFAULT_NAISSANCE)
            .genre(DEFAULT_GENRE)
            .estMarie(DEFAULT_EST_MARIE)
            .numCarteCli(DEFAULT_NUM_CARTE_CLI)
            .dCarteUtil(DEFAULT_D_CARTE_UTIL)
            .village(DEFAULT_VILLAGE)
            .tel(DEFAULT_TEL)
            .email(DEFAULT_EMAIL)
            .groupe(DEFAULT_GROUPE)
            .photoID(DEFAULT_PHOTO_ID)
            .tailleMenage(DEFAULT_TAILLE_MENAGE)
            .superficiePos(DEFAULT_SUPERFICIE_POS)
            .nomPAP(DEFAULT_NOM_PAP)
            .prenomPAP(DEFAULT_PRENOM_PAP)
            .telPAP(DEFAULT_TEL_PAP)
            .lienPAP(DEFAULT_LIEN_PAP)
            .estActif(DEFAULT_EST_ACTIF)
            .creeLe(DEFAULT_CREE_LE)
            .creePar(DEFAULT_CREE_PAR)
            .modifLe(DEFAULT_MODIF_LE)
            .modifPar(DEFAULT_MODIF_PAR);
        return client;
    }

    @Before
    public void initTest() {
        client = createEntity(em);
    }

    @Test
    @Transactional
    public void createClient() throws Exception {
        int databaseSizeBeforeCreate = clientRepository.findAll().size();

        // Create the Client
        ClientDTO clientDTO = clientMapper.toDto(client);
        restClientMockMvc.perform(post("/api/clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isCreated());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeCreate + 1);
        Client testClient = clientList.get(clientList.size() - 1);
        assertThat(testClient.getClientID()).isEqualTo(DEFAULT_CLIENT_ID);
        assertThat(testClient.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testClient.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testClient.getNaissance()).isEqualTo(DEFAULT_NAISSANCE);
        assertThat(testClient.getGenre()).isEqualTo(DEFAULT_GENRE);
        assertThat(testClient.isEstMarie()).isEqualTo(DEFAULT_EST_MARIE);
        assertThat(testClient.getNumCarteCli()).isEqualTo(DEFAULT_NUM_CARTE_CLI);
        assertThat(testClient.getdCarteUtil()).isEqualTo(DEFAULT_D_CARTE_UTIL);
        assertThat(testClient.getVillage()).isEqualTo(DEFAULT_VILLAGE);
        assertThat(testClient.getTel()).isEqualTo(DEFAULT_TEL);
        assertThat(testClient.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testClient.getGroupe()).isEqualTo(DEFAULT_GROUPE);
        assertThat(testClient.getPhotoID()).isEqualTo(DEFAULT_PHOTO_ID);
        assertThat(testClient.getTailleMenage()).isEqualTo(DEFAULT_TAILLE_MENAGE);
        assertThat(testClient.getSuperficiePos()).isEqualTo(DEFAULT_SUPERFICIE_POS);
        assertThat(testClient.getNomPAP()).isEqualTo(DEFAULT_NOM_PAP);
        assertThat(testClient.getPrenomPAP()).isEqualTo(DEFAULT_PRENOM_PAP);
        assertThat(testClient.getTelPAP()).isEqualTo(DEFAULT_TEL_PAP);
        assertThat(testClient.getLienPAP()).isEqualTo(DEFAULT_LIEN_PAP);
        assertThat(testClient.isEstActif()).isEqualTo(DEFAULT_EST_ACTIF);
        assertThat(testClient.getCreeLe()).isEqualTo(DEFAULT_CREE_LE);
        assertThat(testClient.getCreePar()).isEqualTo(DEFAULT_CREE_PAR);
        assertThat(testClient.getModifLe()).isEqualTo(DEFAULT_MODIF_LE);
        assertThat(testClient.getModifPar()).isEqualTo(DEFAULT_MODIF_PAR);
    }

    @Test
    @Transactional
    public void createClientWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = clientRepository.findAll().size();

        // Create the Client with an existing ID
        client.setId(1L);
        ClientDTO clientDTO = clientMapper.toDto(client);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClientMockMvc.perform(post("/api/clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkClientIDIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientRepository.findAll().size();
        // set the field null
        client.setClientID(null);

        // Create the Client, which fails.
        ClientDTO clientDTO = clientMapper.toDto(client);

        restClientMockMvc.perform(post("/api/clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isBadRequest());

        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientRepository.findAll().size();
        // set the field null
        client.setNom(null);

        // Create the Client, which fails.
        ClientDTO clientDTO = clientMapper.toDto(client);

        restClientMockMvc.perform(post("/api/clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isBadRequest());

        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrenomIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientRepository.findAll().size();
        // set the field null
        client.setPrenom(null);

        // Create the Client, which fails.
        ClientDTO clientDTO = clientMapper.toDto(client);

        restClientMockMvc.perform(post("/api/clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isBadRequest());

        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNaissanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientRepository.findAll().size();
        // set the field null
        client.setNaissance(null);

        // Create the Client, which fails.
        ClientDTO clientDTO = clientMapper.toDto(client);

        restClientMockMvc.perform(post("/api/clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isBadRequest());

        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGenreIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientRepository.findAll().size();
        // set the field null
        client.setGenre(null);

        // Create the Client, which fails.
        ClientDTO clientDTO = clientMapper.toDto(client);

        restClientMockMvc.perform(post("/api/clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isBadRequest());

        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstMarieIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientRepository.findAll().size();
        // set the field null
        client.setEstMarie(null);

        // Create the Client, which fails.
        ClientDTO clientDTO = clientMapper.toDto(client);

        restClientMockMvc.perform(post("/api/clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isBadRequest());

        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumCarteCliIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientRepository.findAll().size();
        // set the field null
        client.setNumCarteCli(null);

        // Create the Client, which fails.
        ClientDTO clientDTO = clientMapper.toDto(client);

        restClientMockMvc.perform(post("/api/clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isBadRequest());

        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkdCarteUtilIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientRepository.findAll().size();
        // set the field null
        client.setdCarteUtil(null);

        // Create the Client, which fails.
        ClientDTO clientDTO = clientMapper.toDto(client);

        restClientMockMvc.perform(post("/api/clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isBadRequest());

        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVillageIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientRepository.findAll().size();
        // set the field null
        client.setVillage(null);

        // Create the Client, which fails.
        ClientDTO clientDTO = clientMapper.toDto(client);

        restClientMockMvc.perform(post("/api/clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isBadRequest());

        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTelIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientRepository.findAll().size();
        // set the field null
        client.setTel(null);

        // Create the Client, which fails.
        ClientDTO clientDTO = clientMapper.toDto(client);

        restClientMockMvc.perform(post("/api/clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isBadRequest());

        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTailleMenageIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientRepository.findAll().size();
        // set the field null
        client.setTailleMenage(null);

        // Create the Client, which fails.
        ClientDTO clientDTO = clientMapper.toDto(client);

        restClientMockMvc.perform(post("/api/clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isBadRequest());

        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSuperficiePosIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientRepository.findAll().size();
        // set the field null
        client.setSuperficiePos(null);

        // Create the Client, which fails.
        ClientDTO clientDTO = clientMapper.toDto(client);

        restClientMockMvc.perform(post("/api/clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isBadRequest());

        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllClients() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList
        restClientMockMvc.perform(get("/api/clients?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(client.getId().intValue())))
            .andExpect(jsonPath("$.[*].clientID").value(hasItem(DEFAULT_CLIENT_ID.toString())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM.toString())))
            .andExpect(jsonPath("$.[*].naissance").value(hasItem(DEFAULT_NAISSANCE.toString())))
            .andExpect(jsonPath("$.[*].genre").value(hasItem(DEFAULT_GENRE.toString())))
            .andExpect(jsonPath("$.[*].estMarie").value(hasItem(DEFAULT_EST_MARIE.booleanValue())))
            .andExpect(jsonPath("$.[*].numCarteCli").value(hasItem(DEFAULT_NUM_CARTE_CLI.toString())))
            .andExpect(jsonPath("$.[*].dCarteUtil").value(hasItem(DEFAULT_D_CARTE_UTIL.toString())))
            .andExpect(jsonPath("$.[*].village").value(hasItem(DEFAULT_VILLAGE.toString())))
            .andExpect(jsonPath("$.[*].tel").value(hasItem(DEFAULT_TEL.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].groupe").value(hasItem(DEFAULT_GROUPE.toString())))
            .andExpect(jsonPath("$.[*].photoID").value(hasItem(DEFAULT_PHOTO_ID.toString())))
            .andExpect(jsonPath("$.[*].tailleMenage").value(hasItem(DEFAULT_TAILLE_MENAGE)))
            .andExpect(jsonPath("$.[*].superficiePos").value(hasItem(DEFAULT_SUPERFICIE_POS.doubleValue())))
            .andExpect(jsonPath("$.[*].nomPAP").value(hasItem(DEFAULT_NOM_PAP.toString())))
            .andExpect(jsonPath("$.[*].prenomPAP").value(hasItem(DEFAULT_PRENOM_PAP.toString())))
            .andExpect(jsonPath("$.[*].telPAP").value(hasItem(DEFAULT_TEL_PAP.toString())))
            .andExpect(jsonPath("$.[*].lienPAP").value(hasItem(DEFAULT_LIEN_PAP.toString())))
            .andExpect(jsonPath("$.[*].estActif").value(hasItem(DEFAULT_EST_ACTIF.booleanValue())))
            .andExpect(jsonPath("$.[*].creeLe").value(hasItem(DEFAULT_CREE_LE.toString())))
            .andExpect(jsonPath("$.[*].creePar").value(hasItem(DEFAULT_CREE_PAR.toString())))
            .andExpect(jsonPath("$.[*].modifLe").value(hasItem(DEFAULT_MODIF_LE.toString())))
            .andExpect(jsonPath("$.[*].modifPar").value(hasItem(DEFAULT_MODIF_PAR.toString())));
    }
    

    @Test
    @Transactional
    public void getClient() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get the client
        restClientMockMvc.perform(get("/api/clients/{id}", client.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(client.getId().intValue()))
            .andExpect(jsonPath("$.clientID").value(DEFAULT_CLIENT_ID.toString()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM.toString()))
            .andExpect(jsonPath("$.naissance").value(DEFAULT_NAISSANCE.toString()))
            .andExpect(jsonPath("$.genre").value(DEFAULT_GENRE.toString()))
            .andExpect(jsonPath("$.estMarie").value(DEFAULT_EST_MARIE.booleanValue()))
            .andExpect(jsonPath("$.numCarteCli").value(DEFAULT_NUM_CARTE_CLI.toString()))
            .andExpect(jsonPath("$.dCarteUtil").value(DEFAULT_D_CARTE_UTIL.toString()))
            .andExpect(jsonPath("$.village").value(DEFAULT_VILLAGE.toString()))
            .andExpect(jsonPath("$.tel").value(DEFAULT_TEL.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.groupe").value(DEFAULT_GROUPE.toString()))
            .andExpect(jsonPath("$.photoID").value(DEFAULT_PHOTO_ID.toString()))
            .andExpect(jsonPath("$.tailleMenage").value(DEFAULT_TAILLE_MENAGE))
            .andExpect(jsonPath("$.superficiePos").value(DEFAULT_SUPERFICIE_POS.doubleValue()))
            .andExpect(jsonPath("$.nomPAP").value(DEFAULT_NOM_PAP.toString()))
            .andExpect(jsonPath("$.prenomPAP").value(DEFAULT_PRENOM_PAP.toString()))
            .andExpect(jsonPath("$.telPAP").value(DEFAULT_TEL_PAP.toString()))
            .andExpect(jsonPath("$.lienPAP").value(DEFAULT_LIEN_PAP.toString()))
            .andExpect(jsonPath("$.estActif").value(DEFAULT_EST_ACTIF.booleanValue()))
            .andExpect(jsonPath("$.creeLe").value(DEFAULT_CREE_LE.toString()))
            .andExpect(jsonPath("$.creePar").value(DEFAULT_CREE_PAR.toString()))
            .andExpect(jsonPath("$.modifLe").value(DEFAULT_MODIF_LE.toString()))
            .andExpect(jsonPath("$.modifPar").value(DEFAULT_MODIF_PAR.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingClient() throws Exception {
        // Get the client
        restClientMockMvc.perform(get("/api/clients/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClient() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        int databaseSizeBeforeUpdate = clientRepository.findAll().size();

        // Update the client
        Client updatedClient = clientRepository.findById(client.getId()).get();
        // Disconnect from session so that the updates on updatedClient are not directly saved in db
        em.detach(updatedClient);
        updatedClient
            .clientID(UPDATED_CLIENT_ID)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .naissance(UPDATED_NAISSANCE)
            .genre(UPDATED_GENRE)
            .estMarie(UPDATED_EST_MARIE)
            .numCarteCli(UPDATED_NUM_CARTE_CLI)
            .dCarteUtil(UPDATED_D_CARTE_UTIL)
            .village(UPDATED_VILLAGE)
            .tel(UPDATED_TEL)
            .email(UPDATED_EMAIL)
            .groupe(UPDATED_GROUPE)
            .photoID(UPDATED_PHOTO_ID)
            .tailleMenage(UPDATED_TAILLE_MENAGE)
            .superficiePos(UPDATED_SUPERFICIE_POS)
            .nomPAP(UPDATED_NOM_PAP)
            .prenomPAP(UPDATED_PRENOM_PAP)
            .telPAP(UPDATED_TEL_PAP)
            .lienPAP(UPDATED_LIEN_PAP)
            .estActif(UPDATED_EST_ACTIF)
            .creeLe(UPDATED_CREE_LE)
            .creePar(UPDATED_CREE_PAR)
            .modifLe(UPDATED_MODIF_LE)
            .modifPar(UPDATED_MODIF_PAR);
        ClientDTO clientDTO = clientMapper.toDto(updatedClient);

        restClientMockMvc.perform(put("/api/clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isOk());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
        Client testClient = clientList.get(clientList.size() - 1);
        assertThat(testClient.getClientID()).isEqualTo(UPDATED_CLIENT_ID);
        assertThat(testClient.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testClient.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testClient.getNaissance()).isEqualTo(UPDATED_NAISSANCE);
        assertThat(testClient.getGenre()).isEqualTo(UPDATED_GENRE);
        assertThat(testClient.isEstMarie()).isEqualTo(UPDATED_EST_MARIE);
        assertThat(testClient.getNumCarteCli()).isEqualTo(UPDATED_NUM_CARTE_CLI);
        assertThat(testClient.getdCarteUtil()).isEqualTo(UPDATED_D_CARTE_UTIL);
        assertThat(testClient.getVillage()).isEqualTo(UPDATED_VILLAGE);
        assertThat(testClient.getTel()).isEqualTo(UPDATED_TEL);
        assertThat(testClient.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testClient.getGroupe()).isEqualTo(UPDATED_GROUPE);
        assertThat(testClient.getPhotoID()).isEqualTo(UPDATED_PHOTO_ID);
        assertThat(testClient.getTailleMenage()).isEqualTo(UPDATED_TAILLE_MENAGE);
        assertThat(testClient.getSuperficiePos()).isEqualTo(UPDATED_SUPERFICIE_POS);
        assertThat(testClient.getNomPAP()).isEqualTo(UPDATED_NOM_PAP);
        assertThat(testClient.getPrenomPAP()).isEqualTo(UPDATED_PRENOM_PAP);
        assertThat(testClient.getTelPAP()).isEqualTo(UPDATED_TEL_PAP);
        assertThat(testClient.getLienPAP()).isEqualTo(UPDATED_LIEN_PAP);
        assertThat(testClient.isEstActif()).isEqualTo(UPDATED_EST_ACTIF);
        assertThat(testClient.getCreeLe()).isEqualTo(UPDATED_CREE_LE);
        assertThat(testClient.getCreePar()).isEqualTo(UPDATED_CREE_PAR);
        assertThat(testClient.getModifLe()).isEqualTo(UPDATED_MODIF_LE);
        assertThat(testClient.getModifPar()).isEqualTo(UPDATED_MODIF_PAR);
    }

    @Test
    @Transactional
    public void updateNonExistingClient() throws Exception {
        int databaseSizeBeforeUpdate = clientRepository.findAll().size();

        // Create the Client
        ClientDTO clientDTO = clientMapper.toDto(client);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restClientMockMvc.perform(put("/api/clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteClient() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        int databaseSizeBeforeDelete = clientRepository.findAll().size();

        // Get the client
        restClientMockMvc.perform(delete("/api/clients/{id}", client.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Client.class);
        Client client1 = new Client();
        client1.setId(1L);
        Client client2 = new Client();
        client2.setId(client1.getId());
        assertThat(client1).isEqualTo(client2);
        client2.setId(2L);
        assertThat(client1).isNotEqualTo(client2);
        client1.setId(null);
        assertThat(client1).isNotEqualTo(client2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClientDTO.class);
        ClientDTO clientDTO1 = new ClientDTO();
        clientDTO1.setId(1L);
        ClientDTO clientDTO2 = new ClientDTO();
        assertThat(clientDTO1).isNotEqualTo(clientDTO2);
        clientDTO2.setId(clientDTO1.getId());
        assertThat(clientDTO1).isEqualTo(clientDTO2);
        clientDTO2.setId(2L);
        assertThat(clientDTO1).isNotEqualTo(clientDTO2);
        clientDTO1.setId(null);
        assertThat(clientDTO1).isNotEqualTo(clientDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(clientMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(clientMapper.fromId(null)).isNull();
    }
}
