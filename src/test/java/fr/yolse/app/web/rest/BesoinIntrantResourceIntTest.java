package fr.yolse.app.web.rest;

import fr.yolse.app.YolseApp;

import fr.yolse.app.domain.BesoinIntrant;
import fr.yolse.app.repository.BesoinIntrantRepository;
import fr.yolse.app.service.BesoinIntrantService;
import fr.yolse.app.service.dto.BesoinIntrantDTO;
import fr.yolse.app.service.mapper.BesoinIntrantMapper;
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
 * Test class for the BesoinIntrantResource REST controller.
 *
 * @see BesoinIntrantResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YolseApp.class)
public class BesoinIntrantResourceIntTest {

    private static final Integer DEFAULT_BES_INT_ID = 1;
    private static final Integer UPDATED_BES_INT_ID = 2;

    private static final Double DEFAULT_SUPERFICIE_ESC = 1D;
    private static final Double UPDATED_SUPERFICIE_ESC = 2D;

    private static final Double DEFAULT_Q_SEMENCE = 1D;
    private static final Double UPDATED_Q_SEMENCE = 2D;

    private static final Double DEFAULT_VALEUR_TOT = 1D;
    private static final Double UPDATED_VALEUR_TOT = 2D;

    private static final Double DEFAULT_M_ADHESION = 1D;
    private static final Double UPDATED_M_ADHESION = 2D;

    private static final Double DEFAULT_M_ASSUR = 1D;
    private static final Double UPDATED_M_ASSUR = 2D;

    private static final Double DEFAULT_M_GARAN = 1D;
    private static final Double UPDATED_M_GARAN = 2D;

    private static final Double DEFAULT_Q_STOCK_GAR = 1D;
    private static final Double UPDATED_Q_STOCK_GAR = 2D;

    private static final String DEFAULT_MAGASIN_STOCK = "AAAAAAAAAA";
    private static final String UPDATED_MAGASIN_STOCK = "BBBBBBBBBB";

    private static final String DEFAULT_SFD = "AAAAAAAAAA";
    private static final String UPDATED_SFD = "BBBBBBBBBB";

    private static final Double DEFAULT_M_UNI_GES = 1D;
    private static final Double UPDATED_M_UNI_GES = 2D;

    private static final Double DEFAULT_M_ADMIN = 1D;
    private static final Double UPDATED_M_ADMIN = 2D;

    private static final Double DEFAULT_M_EXPLOI = 1D;
    private static final Double UPDATED_M_EXPLOI = 2D;

    private static final Boolean DEFAULT_VALID_SUP = false;
    private static final Boolean UPDATED_VALID_SUP = true;

    private static final Boolean DEFAULT_VALID_RES = false;
    private static final Boolean UPDATED_VALID_RES = true;

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
    private BesoinIntrantRepository besoinIntrantRepository;


    @Autowired
    private BesoinIntrantMapper besoinIntrantMapper;
    

    @Autowired
    private BesoinIntrantService besoinIntrantService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBesoinIntrantMockMvc;

    private BesoinIntrant besoinIntrant;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BesoinIntrantResource besoinIntrantResource = new BesoinIntrantResource(besoinIntrantService);
        this.restBesoinIntrantMockMvc = MockMvcBuilders.standaloneSetup(besoinIntrantResource)
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
    public static BesoinIntrant createEntity(EntityManager em) {
        BesoinIntrant besoinIntrant = new BesoinIntrant()
            .besIntID(DEFAULT_BES_INT_ID)
            .superficieEsc(DEFAULT_SUPERFICIE_ESC)
            .qSemence(DEFAULT_Q_SEMENCE)
            .valeurTot(DEFAULT_VALEUR_TOT)
            .mAdhesion(DEFAULT_M_ADHESION)
            .mAssur(DEFAULT_M_ASSUR)
            .mGaran(DEFAULT_M_GARAN)
            .qStockGar(DEFAULT_Q_STOCK_GAR)
            .magasinStock(DEFAULT_MAGASIN_STOCK)
            .sfd(DEFAULT_SFD)
            .mUniGes(DEFAULT_M_UNI_GES)
            .mAdmin(DEFAULT_M_ADMIN)
            .mExploi(DEFAULT_M_EXPLOI)
            .validSup(DEFAULT_VALID_SUP)
            .validRes(DEFAULT_VALID_RES)
            .estActif(DEFAULT_EST_ACTIF)
            .creeLe(DEFAULT_CREE_LE)
            .creePar(DEFAULT_CREE_PAR)
            .modifLe(DEFAULT_MODIF_LE)
            .modifPar(DEFAULT_MODIF_PAR);
        return besoinIntrant;
    }

    @Before
    public void initTest() {
        besoinIntrant = createEntity(em);
    }

    @Test
    @Transactional
    public void createBesoinIntrant() throws Exception {
        int databaseSizeBeforeCreate = besoinIntrantRepository.findAll().size();

        // Create the BesoinIntrant
        BesoinIntrantDTO besoinIntrantDTO = besoinIntrantMapper.toDto(besoinIntrant);
        restBesoinIntrantMockMvc.perform(post("/api/besoin-intrants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(besoinIntrantDTO)))
            .andExpect(status().isCreated());

        // Validate the BesoinIntrant in the database
        List<BesoinIntrant> besoinIntrantList = besoinIntrantRepository.findAll();
        assertThat(besoinIntrantList).hasSize(databaseSizeBeforeCreate + 1);
        BesoinIntrant testBesoinIntrant = besoinIntrantList.get(besoinIntrantList.size() - 1);
        assertThat(testBesoinIntrant.getBesIntID()).isEqualTo(DEFAULT_BES_INT_ID);
        assertThat(testBesoinIntrant.getSuperficieEsc()).isEqualTo(DEFAULT_SUPERFICIE_ESC);
        assertThat(testBesoinIntrant.getqSemence()).isEqualTo(DEFAULT_Q_SEMENCE);
        assertThat(testBesoinIntrant.getValeurTot()).isEqualTo(DEFAULT_VALEUR_TOT);
        assertThat(testBesoinIntrant.getmAdhesion()).isEqualTo(DEFAULT_M_ADHESION);
        assertThat(testBesoinIntrant.getmAssur()).isEqualTo(DEFAULT_M_ASSUR);
        assertThat(testBesoinIntrant.getmGaran()).isEqualTo(DEFAULT_M_GARAN);
        assertThat(testBesoinIntrant.getqStockGar()).isEqualTo(DEFAULT_Q_STOCK_GAR);
        assertThat(testBesoinIntrant.getMagasinStock()).isEqualTo(DEFAULT_MAGASIN_STOCK);
        assertThat(testBesoinIntrant.getSfd()).isEqualTo(DEFAULT_SFD);
        assertThat(testBesoinIntrant.getmUniGes()).isEqualTo(DEFAULT_M_UNI_GES);
        assertThat(testBesoinIntrant.getmAdmin()).isEqualTo(DEFAULT_M_ADMIN);
        assertThat(testBesoinIntrant.getmExploi()).isEqualTo(DEFAULT_M_EXPLOI);
        assertThat(testBesoinIntrant.isValidSup()).isEqualTo(DEFAULT_VALID_SUP);
        assertThat(testBesoinIntrant.isValidRes()).isEqualTo(DEFAULT_VALID_RES);
        assertThat(testBesoinIntrant.isEstActif()).isEqualTo(DEFAULT_EST_ACTIF);
        assertThat(testBesoinIntrant.getCreeLe()).isEqualTo(DEFAULT_CREE_LE);
        assertThat(testBesoinIntrant.getCreePar()).isEqualTo(DEFAULT_CREE_PAR);
        assertThat(testBesoinIntrant.getModifLe()).isEqualTo(DEFAULT_MODIF_LE);
        assertThat(testBesoinIntrant.getModifPar()).isEqualTo(DEFAULT_MODIF_PAR);
    }

    @Test
    @Transactional
    public void createBesoinIntrantWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = besoinIntrantRepository.findAll().size();

        // Create the BesoinIntrant with an existing ID
        besoinIntrant.setId(1L);
        BesoinIntrantDTO besoinIntrantDTO = besoinIntrantMapper.toDto(besoinIntrant);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBesoinIntrantMockMvc.perform(post("/api/besoin-intrants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(besoinIntrantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BesoinIntrant in the database
        List<BesoinIntrant> besoinIntrantList = besoinIntrantRepository.findAll();
        assertThat(besoinIntrantList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkBesIntIDIsRequired() throws Exception {
        int databaseSizeBeforeTest = besoinIntrantRepository.findAll().size();
        // set the field null
        besoinIntrant.setBesIntID(null);

        // Create the BesoinIntrant, which fails.
        BesoinIntrantDTO besoinIntrantDTO = besoinIntrantMapper.toDto(besoinIntrant);

        restBesoinIntrantMockMvc.perform(post("/api/besoin-intrants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(besoinIntrantDTO)))
            .andExpect(status().isBadRequest());

        List<BesoinIntrant> besoinIntrantList = besoinIntrantRepository.findAll();
        assertThat(besoinIntrantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSuperficieEscIsRequired() throws Exception {
        int databaseSizeBeforeTest = besoinIntrantRepository.findAll().size();
        // set the field null
        besoinIntrant.setSuperficieEsc(null);

        // Create the BesoinIntrant, which fails.
        BesoinIntrantDTO besoinIntrantDTO = besoinIntrantMapper.toDto(besoinIntrant);

        restBesoinIntrantMockMvc.perform(post("/api/besoin-intrants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(besoinIntrantDTO)))
            .andExpect(status().isBadRequest());

        List<BesoinIntrant> besoinIntrantList = besoinIntrantRepository.findAll();
        assertThat(besoinIntrantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkqSemenceIsRequired() throws Exception {
        int databaseSizeBeforeTest = besoinIntrantRepository.findAll().size();
        // set the field null
        besoinIntrant.setqSemence(null);

        // Create the BesoinIntrant, which fails.
        BesoinIntrantDTO besoinIntrantDTO = besoinIntrantMapper.toDto(besoinIntrant);

        restBesoinIntrantMockMvc.perform(post("/api/besoin-intrants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(besoinIntrantDTO)))
            .andExpect(status().isBadRequest());

        List<BesoinIntrant> besoinIntrantList = besoinIntrantRepository.findAll();
        assertThat(besoinIntrantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValeurTotIsRequired() throws Exception {
        int databaseSizeBeforeTest = besoinIntrantRepository.findAll().size();
        // set the field null
        besoinIntrant.setValeurTot(null);

        // Create the BesoinIntrant, which fails.
        BesoinIntrantDTO besoinIntrantDTO = besoinIntrantMapper.toDto(besoinIntrant);

        restBesoinIntrantMockMvc.perform(post("/api/besoin-intrants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(besoinIntrantDTO)))
            .andExpect(status().isBadRequest());

        List<BesoinIntrant> besoinIntrantList = besoinIntrantRepository.findAll();
        assertThat(besoinIntrantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkmAdhesionIsRequired() throws Exception {
        int databaseSizeBeforeTest = besoinIntrantRepository.findAll().size();
        // set the field null
        besoinIntrant.setmAdhesion(null);

        // Create the BesoinIntrant, which fails.
        BesoinIntrantDTO besoinIntrantDTO = besoinIntrantMapper.toDto(besoinIntrant);

        restBesoinIntrantMockMvc.perform(post("/api/besoin-intrants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(besoinIntrantDTO)))
            .andExpect(status().isBadRequest());

        List<BesoinIntrant> besoinIntrantList = besoinIntrantRepository.findAll();
        assertThat(besoinIntrantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkmAssurIsRequired() throws Exception {
        int databaseSizeBeforeTest = besoinIntrantRepository.findAll().size();
        // set the field null
        besoinIntrant.setmAssur(null);

        // Create the BesoinIntrant, which fails.
        BesoinIntrantDTO besoinIntrantDTO = besoinIntrantMapper.toDto(besoinIntrant);

        restBesoinIntrantMockMvc.perform(post("/api/besoin-intrants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(besoinIntrantDTO)))
            .andExpect(status().isBadRequest());

        List<BesoinIntrant> besoinIntrantList = besoinIntrantRepository.findAll();
        assertThat(besoinIntrantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkmGaranIsRequired() throws Exception {
        int databaseSizeBeforeTest = besoinIntrantRepository.findAll().size();
        // set the field null
        besoinIntrant.setmGaran(null);

        // Create the BesoinIntrant, which fails.
        BesoinIntrantDTO besoinIntrantDTO = besoinIntrantMapper.toDto(besoinIntrant);

        restBesoinIntrantMockMvc.perform(post("/api/besoin-intrants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(besoinIntrantDTO)))
            .andExpect(status().isBadRequest());

        List<BesoinIntrant> besoinIntrantList = besoinIntrantRepository.findAll();
        assertThat(besoinIntrantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkqStockGarIsRequired() throws Exception {
        int databaseSizeBeforeTest = besoinIntrantRepository.findAll().size();
        // set the field null
        besoinIntrant.setqStockGar(null);

        // Create the BesoinIntrant, which fails.
        BesoinIntrantDTO besoinIntrantDTO = besoinIntrantMapper.toDto(besoinIntrant);

        restBesoinIntrantMockMvc.perform(post("/api/besoin-intrants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(besoinIntrantDTO)))
            .andExpect(status().isBadRequest());

        List<BesoinIntrant> besoinIntrantList = besoinIntrantRepository.findAll();
        assertThat(besoinIntrantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMagasinStockIsRequired() throws Exception {
        int databaseSizeBeforeTest = besoinIntrantRepository.findAll().size();
        // set the field null
        besoinIntrant.setMagasinStock(null);

        // Create the BesoinIntrant, which fails.
        BesoinIntrantDTO besoinIntrantDTO = besoinIntrantMapper.toDto(besoinIntrant);

        restBesoinIntrantMockMvc.perform(post("/api/besoin-intrants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(besoinIntrantDTO)))
            .andExpect(status().isBadRequest());

        List<BesoinIntrant> besoinIntrantList = besoinIntrantRepository.findAll();
        assertThat(besoinIntrantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSfdIsRequired() throws Exception {
        int databaseSizeBeforeTest = besoinIntrantRepository.findAll().size();
        // set the field null
        besoinIntrant.setSfd(null);

        // Create the BesoinIntrant, which fails.
        BesoinIntrantDTO besoinIntrantDTO = besoinIntrantMapper.toDto(besoinIntrant);

        restBesoinIntrantMockMvc.perform(post("/api/besoin-intrants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(besoinIntrantDTO)))
            .andExpect(status().isBadRequest());

        List<BesoinIntrant> besoinIntrantList = besoinIntrantRepository.findAll();
        assertThat(besoinIntrantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkmUniGesIsRequired() throws Exception {
        int databaseSizeBeforeTest = besoinIntrantRepository.findAll().size();
        // set the field null
        besoinIntrant.setmUniGes(null);

        // Create the BesoinIntrant, which fails.
        BesoinIntrantDTO besoinIntrantDTO = besoinIntrantMapper.toDto(besoinIntrant);

        restBesoinIntrantMockMvc.perform(post("/api/besoin-intrants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(besoinIntrantDTO)))
            .andExpect(status().isBadRequest());

        List<BesoinIntrant> besoinIntrantList = besoinIntrantRepository.findAll();
        assertThat(besoinIntrantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkmAdminIsRequired() throws Exception {
        int databaseSizeBeforeTest = besoinIntrantRepository.findAll().size();
        // set the field null
        besoinIntrant.setmAdmin(null);

        // Create the BesoinIntrant, which fails.
        BesoinIntrantDTO besoinIntrantDTO = besoinIntrantMapper.toDto(besoinIntrant);

        restBesoinIntrantMockMvc.perform(post("/api/besoin-intrants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(besoinIntrantDTO)))
            .andExpect(status().isBadRequest());

        List<BesoinIntrant> besoinIntrantList = besoinIntrantRepository.findAll();
        assertThat(besoinIntrantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkmExploiIsRequired() throws Exception {
        int databaseSizeBeforeTest = besoinIntrantRepository.findAll().size();
        // set the field null
        besoinIntrant.setmExploi(null);

        // Create the BesoinIntrant, which fails.
        BesoinIntrantDTO besoinIntrantDTO = besoinIntrantMapper.toDto(besoinIntrant);

        restBesoinIntrantMockMvc.perform(post("/api/besoin-intrants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(besoinIntrantDTO)))
            .andExpect(status().isBadRequest());

        List<BesoinIntrant> besoinIntrantList = besoinIntrantRepository.findAll();
        assertThat(besoinIntrantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBesoinIntrants() throws Exception {
        // Initialize the database
        besoinIntrantRepository.saveAndFlush(besoinIntrant);

        // Get all the besoinIntrantList
        restBesoinIntrantMockMvc.perform(get("/api/besoin-intrants?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(besoinIntrant.getId().intValue())))
            .andExpect(jsonPath("$.[*].besIntID").value(hasItem(DEFAULT_BES_INT_ID)))
            .andExpect(jsonPath("$.[*].superficieEsc").value(hasItem(DEFAULT_SUPERFICIE_ESC.doubleValue())))
            .andExpect(jsonPath("$.[*].qSemence").value(hasItem(DEFAULT_Q_SEMENCE.doubleValue())))
            .andExpect(jsonPath("$.[*].valeurTot").value(hasItem(DEFAULT_VALEUR_TOT.doubleValue())))
            .andExpect(jsonPath("$.[*].mAdhesion").value(hasItem(DEFAULT_M_ADHESION.doubleValue())))
            .andExpect(jsonPath("$.[*].mAssur").value(hasItem(DEFAULT_M_ASSUR.doubleValue())))
            .andExpect(jsonPath("$.[*].mGaran").value(hasItem(DEFAULT_M_GARAN.doubleValue())))
            .andExpect(jsonPath("$.[*].qStockGar").value(hasItem(DEFAULT_Q_STOCK_GAR.doubleValue())))
            .andExpect(jsonPath("$.[*].magasinStock").value(hasItem(DEFAULT_MAGASIN_STOCK.toString())))
            .andExpect(jsonPath("$.[*].sfd").value(hasItem(DEFAULT_SFD.toString())))
            .andExpect(jsonPath("$.[*].mUniGes").value(hasItem(DEFAULT_M_UNI_GES.doubleValue())))
            .andExpect(jsonPath("$.[*].mAdmin").value(hasItem(DEFAULT_M_ADMIN.doubleValue())))
            .andExpect(jsonPath("$.[*].mExploi").value(hasItem(DEFAULT_M_EXPLOI.doubleValue())))
            .andExpect(jsonPath("$.[*].validSup").value(hasItem(DEFAULT_VALID_SUP.booleanValue())))
            .andExpect(jsonPath("$.[*].validRes").value(hasItem(DEFAULT_VALID_RES.booleanValue())))
            .andExpect(jsonPath("$.[*].estActif").value(hasItem(DEFAULT_EST_ACTIF.booleanValue())))
            .andExpect(jsonPath("$.[*].creeLe").value(hasItem(DEFAULT_CREE_LE.toString())))
            .andExpect(jsonPath("$.[*].creePar").value(hasItem(DEFAULT_CREE_PAR.toString())))
            .andExpect(jsonPath("$.[*].modifLe").value(hasItem(DEFAULT_MODIF_LE.toString())))
            .andExpect(jsonPath("$.[*].modifPar").value(hasItem(DEFAULT_MODIF_PAR.toString())));
    }
    

    @Test
    @Transactional
    public void getBesoinIntrant() throws Exception {
        // Initialize the database
        besoinIntrantRepository.saveAndFlush(besoinIntrant);

        // Get the besoinIntrant
        restBesoinIntrantMockMvc.perform(get("/api/besoin-intrants/{id}", besoinIntrant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(besoinIntrant.getId().intValue()))
            .andExpect(jsonPath("$.besIntID").value(DEFAULT_BES_INT_ID))
            .andExpect(jsonPath("$.superficieEsc").value(DEFAULT_SUPERFICIE_ESC.doubleValue()))
            .andExpect(jsonPath("$.qSemence").value(DEFAULT_Q_SEMENCE.doubleValue()))
            .andExpect(jsonPath("$.valeurTot").value(DEFAULT_VALEUR_TOT.doubleValue()))
            .andExpect(jsonPath("$.mAdhesion").value(DEFAULT_M_ADHESION.doubleValue()))
            .andExpect(jsonPath("$.mAssur").value(DEFAULT_M_ASSUR.doubleValue()))
            .andExpect(jsonPath("$.mGaran").value(DEFAULT_M_GARAN.doubleValue()))
            .andExpect(jsonPath("$.qStockGar").value(DEFAULT_Q_STOCK_GAR.doubleValue()))
            .andExpect(jsonPath("$.magasinStock").value(DEFAULT_MAGASIN_STOCK.toString()))
            .andExpect(jsonPath("$.sfd").value(DEFAULT_SFD.toString()))
            .andExpect(jsonPath("$.mUniGes").value(DEFAULT_M_UNI_GES.doubleValue()))
            .andExpect(jsonPath("$.mAdmin").value(DEFAULT_M_ADMIN.doubleValue()))
            .andExpect(jsonPath("$.mExploi").value(DEFAULT_M_EXPLOI.doubleValue()))
            .andExpect(jsonPath("$.validSup").value(DEFAULT_VALID_SUP.booleanValue()))
            .andExpect(jsonPath("$.validRes").value(DEFAULT_VALID_RES.booleanValue()))
            .andExpect(jsonPath("$.estActif").value(DEFAULT_EST_ACTIF.booleanValue()))
            .andExpect(jsonPath("$.creeLe").value(DEFAULT_CREE_LE.toString()))
            .andExpect(jsonPath("$.creePar").value(DEFAULT_CREE_PAR.toString()))
            .andExpect(jsonPath("$.modifLe").value(DEFAULT_MODIF_LE.toString()))
            .andExpect(jsonPath("$.modifPar").value(DEFAULT_MODIF_PAR.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingBesoinIntrant() throws Exception {
        // Get the besoinIntrant
        restBesoinIntrantMockMvc.perform(get("/api/besoin-intrants/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBesoinIntrant() throws Exception {
        // Initialize the database
        besoinIntrantRepository.saveAndFlush(besoinIntrant);

        int databaseSizeBeforeUpdate = besoinIntrantRepository.findAll().size();

        // Update the besoinIntrant
        BesoinIntrant updatedBesoinIntrant = besoinIntrantRepository.findById(besoinIntrant.getId()).get();
        // Disconnect from session so that the updates on updatedBesoinIntrant are not directly saved in db
        em.detach(updatedBesoinIntrant);
        updatedBesoinIntrant
            .besIntID(UPDATED_BES_INT_ID)
            .superficieEsc(UPDATED_SUPERFICIE_ESC)
            .qSemence(UPDATED_Q_SEMENCE)
            .valeurTot(UPDATED_VALEUR_TOT)
            .mAdhesion(UPDATED_M_ADHESION)
            .mAssur(UPDATED_M_ASSUR)
            .mGaran(UPDATED_M_GARAN)
            .qStockGar(UPDATED_Q_STOCK_GAR)
            .magasinStock(UPDATED_MAGASIN_STOCK)
            .sfd(UPDATED_SFD)
            .mUniGes(UPDATED_M_UNI_GES)
            .mAdmin(UPDATED_M_ADMIN)
            .mExploi(UPDATED_M_EXPLOI)
            .validSup(UPDATED_VALID_SUP)
            .validRes(UPDATED_VALID_RES)
            .estActif(UPDATED_EST_ACTIF)
            .creeLe(UPDATED_CREE_LE)
            .creePar(UPDATED_CREE_PAR)
            .modifLe(UPDATED_MODIF_LE)
            .modifPar(UPDATED_MODIF_PAR);
        BesoinIntrantDTO besoinIntrantDTO = besoinIntrantMapper.toDto(updatedBesoinIntrant);

        restBesoinIntrantMockMvc.perform(put("/api/besoin-intrants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(besoinIntrantDTO)))
            .andExpect(status().isOk());

        // Validate the BesoinIntrant in the database
        List<BesoinIntrant> besoinIntrantList = besoinIntrantRepository.findAll();
        assertThat(besoinIntrantList).hasSize(databaseSizeBeforeUpdate);
        BesoinIntrant testBesoinIntrant = besoinIntrantList.get(besoinIntrantList.size() - 1);
        assertThat(testBesoinIntrant.getBesIntID()).isEqualTo(UPDATED_BES_INT_ID);
        assertThat(testBesoinIntrant.getSuperficieEsc()).isEqualTo(UPDATED_SUPERFICIE_ESC);
        assertThat(testBesoinIntrant.getqSemence()).isEqualTo(UPDATED_Q_SEMENCE);
        assertThat(testBesoinIntrant.getValeurTot()).isEqualTo(UPDATED_VALEUR_TOT);
        assertThat(testBesoinIntrant.getmAdhesion()).isEqualTo(UPDATED_M_ADHESION);
        assertThat(testBesoinIntrant.getmAssur()).isEqualTo(UPDATED_M_ASSUR);
        assertThat(testBesoinIntrant.getmGaran()).isEqualTo(UPDATED_M_GARAN);
        assertThat(testBesoinIntrant.getqStockGar()).isEqualTo(UPDATED_Q_STOCK_GAR);
        assertThat(testBesoinIntrant.getMagasinStock()).isEqualTo(UPDATED_MAGASIN_STOCK);
        assertThat(testBesoinIntrant.getSfd()).isEqualTo(UPDATED_SFD);
        assertThat(testBesoinIntrant.getmUniGes()).isEqualTo(UPDATED_M_UNI_GES);
        assertThat(testBesoinIntrant.getmAdmin()).isEqualTo(UPDATED_M_ADMIN);
        assertThat(testBesoinIntrant.getmExploi()).isEqualTo(UPDATED_M_EXPLOI);
        assertThat(testBesoinIntrant.isValidSup()).isEqualTo(UPDATED_VALID_SUP);
        assertThat(testBesoinIntrant.isValidRes()).isEqualTo(UPDATED_VALID_RES);
        assertThat(testBesoinIntrant.isEstActif()).isEqualTo(UPDATED_EST_ACTIF);
        assertThat(testBesoinIntrant.getCreeLe()).isEqualTo(UPDATED_CREE_LE);
        assertThat(testBesoinIntrant.getCreePar()).isEqualTo(UPDATED_CREE_PAR);
        assertThat(testBesoinIntrant.getModifLe()).isEqualTo(UPDATED_MODIF_LE);
        assertThat(testBesoinIntrant.getModifPar()).isEqualTo(UPDATED_MODIF_PAR);
    }

    @Test
    @Transactional
    public void updateNonExistingBesoinIntrant() throws Exception {
        int databaseSizeBeforeUpdate = besoinIntrantRepository.findAll().size();

        // Create the BesoinIntrant
        BesoinIntrantDTO besoinIntrantDTO = besoinIntrantMapper.toDto(besoinIntrant);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBesoinIntrantMockMvc.perform(put("/api/besoin-intrants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(besoinIntrantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BesoinIntrant in the database
        List<BesoinIntrant> besoinIntrantList = besoinIntrantRepository.findAll();
        assertThat(besoinIntrantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBesoinIntrant() throws Exception {
        // Initialize the database
        besoinIntrantRepository.saveAndFlush(besoinIntrant);

        int databaseSizeBeforeDelete = besoinIntrantRepository.findAll().size();

        // Get the besoinIntrant
        restBesoinIntrantMockMvc.perform(delete("/api/besoin-intrants/{id}", besoinIntrant.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BesoinIntrant> besoinIntrantList = besoinIntrantRepository.findAll();
        assertThat(besoinIntrantList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BesoinIntrant.class);
        BesoinIntrant besoinIntrant1 = new BesoinIntrant();
        besoinIntrant1.setId(1L);
        BesoinIntrant besoinIntrant2 = new BesoinIntrant();
        besoinIntrant2.setId(besoinIntrant1.getId());
        assertThat(besoinIntrant1).isEqualTo(besoinIntrant2);
        besoinIntrant2.setId(2L);
        assertThat(besoinIntrant1).isNotEqualTo(besoinIntrant2);
        besoinIntrant1.setId(null);
        assertThat(besoinIntrant1).isNotEqualTo(besoinIntrant2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BesoinIntrantDTO.class);
        BesoinIntrantDTO besoinIntrantDTO1 = new BesoinIntrantDTO();
        besoinIntrantDTO1.setId(1L);
        BesoinIntrantDTO besoinIntrantDTO2 = new BesoinIntrantDTO();
        assertThat(besoinIntrantDTO1).isNotEqualTo(besoinIntrantDTO2);
        besoinIntrantDTO2.setId(besoinIntrantDTO1.getId());
        assertThat(besoinIntrantDTO1).isEqualTo(besoinIntrantDTO2);
        besoinIntrantDTO2.setId(2L);
        assertThat(besoinIntrantDTO1).isNotEqualTo(besoinIntrantDTO2);
        besoinIntrantDTO1.setId(null);
        assertThat(besoinIntrantDTO1).isNotEqualTo(besoinIntrantDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(besoinIntrantMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(besoinIntrantMapper.fromId(null)).isNull();
    }
}
