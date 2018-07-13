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

    private static final Integer DEFAULT_NUM_CIN = 1;
    private static final Integer UPDATED_NUM_CIN = 2;

    private static final Instant DEFAULT_VALID_CIN = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_VALID_CIN = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_VILLAGE = "AAAAAAAAAA";
    private static final String UPDATED_VILLAGE = "BBBBBBBBBB";

    private static final Integer DEFAULT_TEL_1 = 1;
    private static final Integer UPDATED_TEL_1 = 2;

    private static final Integer DEFAULT_TEL_2 = 1;
    private static final Integer UPDATED_TEL_2 = 2;

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_ORGA_PROD = "AAAAAAAAAA";
    private static final String UPDATED_ORGA_PROD = "BBBBBBBBBB";

    private static final String DEFAULT_PHOTO_ID = "AAAAAAAAAA";
    private static final String UPDATED_PHOTO_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_T_MENAGE = 1;
    private static final Integer UPDATED_T_MENAGE = 2;

    private static final String DEFAULT_NOM_PER_A_PR = "AAAAAAAAAA";
    private static final String UPDATED_NOM_PER_A_PR = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM_PER_A_PR = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM_PER_A_PR = "BBBBBBBBBB";

    private static final Integer DEFAULT_TEL_PER_A_PR = 1;
    private static final Integer UPDATED_TEL_PER_A_PR = 2;

    private static final Double DEFAULT_SUPERFICE = 1D;
    private static final Double UPDATED_SUPERFICE = 2D;

    private static final Double DEFAULT_Q_SEMENCE = 1D;
    private static final Double UPDATED_Q_SEMENCE = 2D;

    private static final Double DEFAULT_M_SIMM = 1D;
    private static final Double UPDATED_M_SIMM = 2D;

    private static final Double DEFAULT_VAL_TOTAL = 1D;
    private static final Double UPDATED_VAL_TOTAL = 2D;

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
            .numCin(DEFAULT_NUM_CIN)
            .validCin(DEFAULT_VALID_CIN)
            .village(DEFAULT_VILLAGE)
            .tel1(DEFAULT_TEL_1)
            .tel2(DEFAULT_TEL_2)
            .email(DEFAULT_EMAIL)
            .orgaProd(DEFAULT_ORGA_PROD)
            .photoID(DEFAULT_PHOTO_ID)
            .tMenage(DEFAULT_T_MENAGE)
            .nomPerAPr(DEFAULT_NOM_PER_A_PR)
            .prenomPerAPr(DEFAULT_PRENOM_PER_A_PR)
            .telPerAPr(DEFAULT_TEL_PER_A_PR)
            .superfice(DEFAULT_SUPERFICE)
            .qSemence(DEFAULT_Q_SEMENCE)
            .mSimm(DEFAULT_M_SIMM)
            .valTotal(DEFAULT_VAL_TOTAL)
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
        assertThat(testClient.getNumCin()).isEqualTo(DEFAULT_NUM_CIN);
        assertThat(testClient.getValidCin()).isEqualTo(DEFAULT_VALID_CIN);
        assertThat(testClient.getVillage()).isEqualTo(DEFAULT_VILLAGE);
        assertThat(testClient.getTel1()).isEqualTo(DEFAULT_TEL_1);
        assertThat(testClient.getTel2()).isEqualTo(DEFAULT_TEL_2);
        assertThat(testClient.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testClient.getOrgaProd()).isEqualTo(DEFAULT_ORGA_PROD);
        assertThat(testClient.getPhotoID()).isEqualTo(DEFAULT_PHOTO_ID);
        assertThat(testClient.gettMenage()).isEqualTo(DEFAULT_T_MENAGE);
        assertThat(testClient.getNomPerAPr()).isEqualTo(DEFAULT_NOM_PER_A_PR);
        assertThat(testClient.getPrenomPerAPr()).isEqualTo(DEFAULT_PRENOM_PER_A_PR);
        assertThat(testClient.getTelPerAPr()).isEqualTo(DEFAULT_TEL_PER_A_PR);
        assertThat(testClient.getSuperfice()).isEqualTo(DEFAULT_SUPERFICE);
        assertThat(testClient.getqSemence()).isEqualTo(DEFAULT_Q_SEMENCE);
        assertThat(testClient.getmSimm()).isEqualTo(DEFAULT_M_SIMM);
        assertThat(testClient.getValTotal()).isEqualTo(DEFAULT_VAL_TOTAL);
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
    public void checkNumCinIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientRepository.findAll().size();
        // set the field null
        client.setNumCin(null);

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
    public void checkValidCinIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientRepository.findAll().size();
        // set the field null
        client.setValidCin(null);

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
    public void checkTel1IsRequired() throws Exception {
        int databaseSizeBeforeTest = clientRepository.findAll().size();
        // set the field null
        client.setTel1(null);

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
    public void checkSuperficeIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientRepository.findAll().size();
        // set the field null
        client.setSuperfice(null);

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
    public void checkqSemenceIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientRepository.findAll().size();
        // set the field null
        client.setqSemence(null);

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
    public void checkmSimmIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientRepository.findAll().size();
        // set the field null
        client.setmSimm(null);

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
    public void checkValTotalIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientRepository.findAll().size();
        // set the field null
        client.setValTotal(null);

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
            .andExpect(jsonPath("$.[*].numCin").value(hasItem(DEFAULT_NUM_CIN)))
            .andExpect(jsonPath("$.[*].validCin").value(hasItem(DEFAULT_VALID_CIN.toString())))
            .andExpect(jsonPath("$.[*].village").value(hasItem(DEFAULT_VILLAGE.toString())))
            .andExpect(jsonPath("$.[*].tel1").value(hasItem(DEFAULT_TEL_1)))
            .andExpect(jsonPath("$.[*].tel2").value(hasItem(DEFAULT_TEL_2)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].orgaProd").value(hasItem(DEFAULT_ORGA_PROD.toString())))
            .andExpect(jsonPath("$.[*].photoID").value(hasItem(DEFAULT_PHOTO_ID.toString())))
            .andExpect(jsonPath("$.[*].tMenage").value(hasItem(DEFAULT_T_MENAGE)))
            .andExpect(jsonPath("$.[*].nomPerAPr").value(hasItem(DEFAULT_NOM_PER_A_PR.toString())))
            .andExpect(jsonPath("$.[*].prenomPerAPr").value(hasItem(DEFAULT_PRENOM_PER_A_PR.toString())))
            .andExpect(jsonPath("$.[*].telPerAPr").value(hasItem(DEFAULT_TEL_PER_A_PR)))
            .andExpect(jsonPath("$.[*].superfice").value(hasItem(DEFAULT_SUPERFICE.doubleValue())))
            .andExpect(jsonPath("$.[*].qSemence").value(hasItem(DEFAULT_Q_SEMENCE.doubleValue())))
            .andExpect(jsonPath("$.[*].mSimm").value(hasItem(DEFAULT_M_SIMM.doubleValue())))
            .andExpect(jsonPath("$.[*].valTotal").value(hasItem(DEFAULT_VAL_TOTAL.doubleValue())))
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
            .andExpect(jsonPath("$.numCin").value(DEFAULT_NUM_CIN))
            .andExpect(jsonPath("$.validCin").value(DEFAULT_VALID_CIN.toString()))
            .andExpect(jsonPath("$.village").value(DEFAULT_VILLAGE.toString()))
            .andExpect(jsonPath("$.tel1").value(DEFAULT_TEL_1))
            .andExpect(jsonPath("$.tel2").value(DEFAULT_TEL_2))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.orgaProd").value(DEFAULT_ORGA_PROD.toString()))
            .andExpect(jsonPath("$.photoID").value(DEFAULT_PHOTO_ID.toString()))
            .andExpect(jsonPath("$.tMenage").value(DEFAULT_T_MENAGE))
            .andExpect(jsonPath("$.nomPerAPr").value(DEFAULT_NOM_PER_A_PR.toString()))
            .andExpect(jsonPath("$.prenomPerAPr").value(DEFAULT_PRENOM_PER_A_PR.toString()))
            .andExpect(jsonPath("$.telPerAPr").value(DEFAULT_TEL_PER_A_PR))
            .andExpect(jsonPath("$.superfice").value(DEFAULT_SUPERFICE.doubleValue()))
            .andExpect(jsonPath("$.qSemence").value(DEFAULT_Q_SEMENCE.doubleValue()))
            .andExpect(jsonPath("$.mSimm").value(DEFAULT_M_SIMM.doubleValue()))
            .andExpect(jsonPath("$.valTotal").value(DEFAULT_VAL_TOTAL.doubleValue()))
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
            .numCin(UPDATED_NUM_CIN)
            .validCin(UPDATED_VALID_CIN)
            .village(UPDATED_VILLAGE)
            .tel1(UPDATED_TEL_1)
            .tel2(UPDATED_TEL_2)
            .email(UPDATED_EMAIL)
            .orgaProd(UPDATED_ORGA_PROD)
            .photoID(UPDATED_PHOTO_ID)
            .tMenage(UPDATED_T_MENAGE)
            .nomPerAPr(UPDATED_NOM_PER_A_PR)
            .prenomPerAPr(UPDATED_PRENOM_PER_A_PR)
            .telPerAPr(UPDATED_TEL_PER_A_PR)
            .superfice(UPDATED_SUPERFICE)
            .qSemence(UPDATED_Q_SEMENCE)
            .mSimm(UPDATED_M_SIMM)
            .valTotal(UPDATED_VAL_TOTAL)
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
        assertThat(testClient.getNumCin()).isEqualTo(UPDATED_NUM_CIN);
        assertThat(testClient.getValidCin()).isEqualTo(UPDATED_VALID_CIN);
        assertThat(testClient.getVillage()).isEqualTo(UPDATED_VILLAGE);
        assertThat(testClient.getTel1()).isEqualTo(UPDATED_TEL_1);
        assertThat(testClient.getTel2()).isEqualTo(UPDATED_TEL_2);
        assertThat(testClient.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testClient.getOrgaProd()).isEqualTo(UPDATED_ORGA_PROD);
        assertThat(testClient.getPhotoID()).isEqualTo(UPDATED_PHOTO_ID);
        assertThat(testClient.gettMenage()).isEqualTo(UPDATED_T_MENAGE);
        assertThat(testClient.getNomPerAPr()).isEqualTo(UPDATED_NOM_PER_A_PR);
        assertThat(testClient.getPrenomPerAPr()).isEqualTo(UPDATED_PRENOM_PER_A_PR);
        assertThat(testClient.getTelPerAPr()).isEqualTo(UPDATED_TEL_PER_A_PR);
        assertThat(testClient.getSuperfice()).isEqualTo(UPDATED_SUPERFICE);
        assertThat(testClient.getqSemence()).isEqualTo(UPDATED_Q_SEMENCE);
        assertThat(testClient.getmSimm()).isEqualTo(UPDATED_M_SIMM);
        assertThat(testClient.getValTotal()).isEqualTo(UPDATED_VAL_TOTAL);
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
