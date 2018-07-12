package fr.yolse.app.web.rest;

import fr.yolse.app.YolseApp;

import fr.yolse.app.domain.EngraisClient;
import fr.yolse.app.repository.EngraisClientRepository;
import fr.yolse.app.service.EngraisClientService;
import fr.yolse.app.service.dto.EngraisClientDTO;
import fr.yolse.app.service.mapper.EngraisClientMapper;
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
 * Test class for the EngraisClientResource REST controller.
 *
 * @see EngraisClientResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YolseApp.class)
public class EngraisClientResourceIntTest {

    private static final Integer DEFAULT_ENG_CLI_ID = 1;
    private static final Integer UPDATED_ENG_CLI_ID = 2;

    private static final Double DEFAULT_Q_ENGRAIS = 1D;
    private static final Double UPDATED_Q_ENGRAIS = 2D;

    private static final Double DEFAULT_P_TOT_ENGR = 1D;
    private static final Double UPDATED_P_TOT_ENGR = 2D;

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
    private EngraisClientRepository engraisClientRepository;


    @Autowired
    private EngraisClientMapper engraisClientMapper;
    

    @Autowired
    private EngraisClientService engraisClientService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEngraisClientMockMvc;

    private EngraisClient engraisClient;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EngraisClientResource engraisClientResource = new EngraisClientResource(engraisClientService);
        this.restEngraisClientMockMvc = MockMvcBuilders.standaloneSetup(engraisClientResource)
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
    public static EngraisClient createEntity(EntityManager em) {
        EngraisClient engraisClient = new EngraisClient()
            .engCliID(DEFAULT_ENG_CLI_ID)
            .qEngrais(DEFAULT_Q_ENGRAIS)
            .pTotEngr(DEFAULT_P_TOT_ENGR)
            .estActif(DEFAULT_EST_ACTIF)
            .creeLe(DEFAULT_CREE_LE)
            .creePar(DEFAULT_CREE_PAR)
            .modifLe(DEFAULT_MODIF_LE)
            .modifPar(DEFAULT_MODIF_PAR);
        return engraisClient;
    }

    @Before
    public void initTest() {
        engraisClient = createEntity(em);
    }

    @Test
    @Transactional
    public void createEngraisClient() throws Exception {
        int databaseSizeBeforeCreate = engraisClientRepository.findAll().size();

        // Create the EngraisClient
        EngraisClientDTO engraisClientDTO = engraisClientMapper.toDto(engraisClient);
        restEngraisClientMockMvc.perform(post("/api/engrais-clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(engraisClientDTO)))
            .andExpect(status().isCreated());

        // Validate the EngraisClient in the database
        List<EngraisClient> engraisClientList = engraisClientRepository.findAll();
        assertThat(engraisClientList).hasSize(databaseSizeBeforeCreate + 1);
        EngraisClient testEngraisClient = engraisClientList.get(engraisClientList.size() - 1);
        assertThat(testEngraisClient.getEngCliID()).isEqualTo(DEFAULT_ENG_CLI_ID);
        assertThat(testEngraisClient.getqEngrais()).isEqualTo(DEFAULT_Q_ENGRAIS);
        assertThat(testEngraisClient.getpTotEngr()).isEqualTo(DEFAULT_P_TOT_ENGR);
        assertThat(testEngraisClient.isEstActif()).isEqualTo(DEFAULT_EST_ACTIF);
        assertThat(testEngraisClient.getCreeLe()).isEqualTo(DEFAULT_CREE_LE);
        assertThat(testEngraisClient.getCreePar()).isEqualTo(DEFAULT_CREE_PAR);
        assertThat(testEngraisClient.getModifLe()).isEqualTo(DEFAULT_MODIF_LE);
        assertThat(testEngraisClient.getModifPar()).isEqualTo(DEFAULT_MODIF_PAR);
    }

    @Test
    @Transactional
    public void createEngraisClientWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = engraisClientRepository.findAll().size();

        // Create the EngraisClient with an existing ID
        engraisClient.setId(1L);
        EngraisClientDTO engraisClientDTO = engraisClientMapper.toDto(engraisClient);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEngraisClientMockMvc.perform(post("/api/engrais-clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(engraisClientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EngraisClient in the database
        List<EngraisClient> engraisClientList = engraisClientRepository.findAll();
        assertThat(engraisClientList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkEngCliIDIsRequired() throws Exception {
        int databaseSizeBeforeTest = engraisClientRepository.findAll().size();
        // set the field null
        engraisClient.setEngCliID(null);

        // Create the EngraisClient, which fails.
        EngraisClientDTO engraisClientDTO = engraisClientMapper.toDto(engraisClient);

        restEngraisClientMockMvc.perform(post("/api/engrais-clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(engraisClientDTO)))
            .andExpect(status().isBadRequest());

        List<EngraisClient> engraisClientList = engraisClientRepository.findAll();
        assertThat(engraisClientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkqEngraisIsRequired() throws Exception {
        int databaseSizeBeforeTest = engraisClientRepository.findAll().size();
        // set the field null
        engraisClient.setqEngrais(null);

        // Create the EngraisClient, which fails.
        EngraisClientDTO engraisClientDTO = engraisClientMapper.toDto(engraisClient);

        restEngraisClientMockMvc.perform(post("/api/engrais-clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(engraisClientDTO)))
            .andExpect(status().isBadRequest());

        List<EngraisClient> engraisClientList = engraisClientRepository.findAll();
        assertThat(engraisClientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkpTotEngrIsRequired() throws Exception {
        int databaseSizeBeforeTest = engraisClientRepository.findAll().size();
        // set the field null
        engraisClient.setpTotEngr(null);

        // Create the EngraisClient, which fails.
        EngraisClientDTO engraisClientDTO = engraisClientMapper.toDto(engraisClient);

        restEngraisClientMockMvc.perform(post("/api/engrais-clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(engraisClientDTO)))
            .andExpect(status().isBadRequest());

        List<EngraisClient> engraisClientList = engraisClientRepository.findAll();
        assertThat(engraisClientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEngraisClients() throws Exception {
        // Initialize the database
        engraisClientRepository.saveAndFlush(engraisClient);

        // Get all the engraisClientList
        restEngraisClientMockMvc.perform(get("/api/engrais-clients?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(engraisClient.getId().intValue())))
            .andExpect(jsonPath("$.[*].engCliID").value(hasItem(DEFAULT_ENG_CLI_ID)))
            .andExpect(jsonPath("$.[*].qEngrais").value(hasItem(DEFAULT_Q_ENGRAIS.doubleValue())))
            .andExpect(jsonPath("$.[*].pTotEngr").value(hasItem(DEFAULT_P_TOT_ENGR.doubleValue())))
            .andExpect(jsonPath("$.[*].estActif").value(hasItem(DEFAULT_EST_ACTIF.booleanValue())))
            .andExpect(jsonPath("$.[*].creeLe").value(hasItem(DEFAULT_CREE_LE.toString())))
            .andExpect(jsonPath("$.[*].creePar").value(hasItem(DEFAULT_CREE_PAR.toString())))
            .andExpect(jsonPath("$.[*].modifLe").value(hasItem(DEFAULT_MODIF_LE.toString())))
            .andExpect(jsonPath("$.[*].modifPar").value(hasItem(DEFAULT_MODIF_PAR.toString())));
    }
    

    @Test
    @Transactional
    public void getEngraisClient() throws Exception {
        // Initialize the database
        engraisClientRepository.saveAndFlush(engraisClient);

        // Get the engraisClient
        restEngraisClientMockMvc.perform(get("/api/engrais-clients/{id}", engraisClient.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(engraisClient.getId().intValue()))
            .andExpect(jsonPath("$.engCliID").value(DEFAULT_ENG_CLI_ID))
            .andExpect(jsonPath("$.qEngrais").value(DEFAULT_Q_ENGRAIS.doubleValue()))
            .andExpect(jsonPath("$.pTotEngr").value(DEFAULT_P_TOT_ENGR.doubleValue()))
            .andExpect(jsonPath("$.estActif").value(DEFAULT_EST_ACTIF.booleanValue()))
            .andExpect(jsonPath("$.creeLe").value(DEFAULT_CREE_LE.toString()))
            .andExpect(jsonPath("$.creePar").value(DEFAULT_CREE_PAR.toString()))
            .andExpect(jsonPath("$.modifLe").value(DEFAULT_MODIF_LE.toString()))
            .andExpect(jsonPath("$.modifPar").value(DEFAULT_MODIF_PAR.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingEngraisClient() throws Exception {
        // Get the engraisClient
        restEngraisClientMockMvc.perform(get("/api/engrais-clients/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEngraisClient() throws Exception {
        // Initialize the database
        engraisClientRepository.saveAndFlush(engraisClient);

        int databaseSizeBeforeUpdate = engraisClientRepository.findAll().size();

        // Update the engraisClient
        EngraisClient updatedEngraisClient = engraisClientRepository.findById(engraisClient.getId()).get();
        // Disconnect from session so that the updates on updatedEngraisClient are not directly saved in db
        em.detach(updatedEngraisClient);
        updatedEngraisClient
            .engCliID(UPDATED_ENG_CLI_ID)
            .qEngrais(UPDATED_Q_ENGRAIS)
            .pTotEngr(UPDATED_P_TOT_ENGR)
            .estActif(UPDATED_EST_ACTIF)
            .creeLe(UPDATED_CREE_LE)
            .creePar(UPDATED_CREE_PAR)
            .modifLe(UPDATED_MODIF_LE)
            .modifPar(UPDATED_MODIF_PAR);
        EngraisClientDTO engraisClientDTO = engraisClientMapper.toDto(updatedEngraisClient);

        restEngraisClientMockMvc.perform(put("/api/engrais-clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(engraisClientDTO)))
            .andExpect(status().isOk());

        // Validate the EngraisClient in the database
        List<EngraisClient> engraisClientList = engraisClientRepository.findAll();
        assertThat(engraisClientList).hasSize(databaseSizeBeforeUpdate);
        EngraisClient testEngraisClient = engraisClientList.get(engraisClientList.size() - 1);
        assertThat(testEngraisClient.getEngCliID()).isEqualTo(UPDATED_ENG_CLI_ID);
        assertThat(testEngraisClient.getqEngrais()).isEqualTo(UPDATED_Q_ENGRAIS);
        assertThat(testEngraisClient.getpTotEngr()).isEqualTo(UPDATED_P_TOT_ENGR);
        assertThat(testEngraisClient.isEstActif()).isEqualTo(UPDATED_EST_ACTIF);
        assertThat(testEngraisClient.getCreeLe()).isEqualTo(UPDATED_CREE_LE);
        assertThat(testEngraisClient.getCreePar()).isEqualTo(UPDATED_CREE_PAR);
        assertThat(testEngraisClient.getModifLe()).isEqualTo(UPDATED_MODIF_LE);
        assertThat(testEngraisClient.getModifPar()).isEqualTo(UPDATED_MODIF_PAR);
    }

    @Test
    @Transactional
    public void updateNonExistingEngraisClient() throws Exception {
        int databaseSizeBeforeUpdate = engraisClientRepository.findAll().size();

        // Create the EngraisClient
        EngraisClientDTO engraisClientDTO = engraisClientMapper.toDto(engraisClient);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEngraisClientMockMvc.perform(put("/api/engrais-clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(engraisClientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EngraisClient in the database
        List<EngraisClient> engraisClientList = engraisClientRepository.findAll();
        assertThat(engraisClientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEngraisClient() throws Exception {
        // Initialize the database
        engraisClientRepository.saveAndFlush(engraisClient);

        int databaseSizeBeforeDelete = engraisClientRepository.findAll().size();

        // Get the engraisClient
        restEngraisClientMockMvc.perform(delete("/api/engrais-clients/{id}", engraisClient.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EngraisClient> engraisClientList = engraisClientRepository.findAll();
        assertThat(engraisClientList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EngraisClient.class);
        EngraisClient engraisClient1 = new EngraisClient();
        engraisClient1.setId(1L);
        EngraisClient engraisClient2 = new EngraisClient();
        engraisClient2.setId(engraisClient1.getId());
        assertThat(engraisClient1).isEqualTo(engraisClient2);
        engraisClient2.setId(2L);
        assertThat(engraisClient1).isNotEqualTo(engraisClient2);
        engraisClient1.setId(null);
        assertThat(engraisClient1).isNotEqualTo(engraisClient2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EngraisClientDTO.class);
        EngraisClientDTO engraisClientDTO1 = new EngraisClientDTO();
        engraisClientDTO1.setId(1L);
        EngraisClientDTO engraisClientDTO2 = new EngraisClientDTO();
        assertThat(engraisClientDTO1).isNotEqualTo(engraisClientDTO2);
        engraisClientDTO2.setId(engraisClientDTO1.getId());
        assertThat(engraisClientDTO1).isEqualTo(engraisClientDTO2);
        engraisClientDTO2.setId(2L);
        assertThat(engraisClientDTO1).isNotEqualTo(engraisClientDTO2);
        engraisClientDTO1.setId(null);
        assertThat(engraisClientDTO1).isNotEqualTo(engraisClientDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(engraisClientMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(engraisClientMapper.fromId(null)).isNull();
    }
}
