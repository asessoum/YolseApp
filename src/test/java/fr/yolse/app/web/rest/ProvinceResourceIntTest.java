package fr.yolse.app.web.rest;

import fr.yolse.app.YolseApp;

import fr.yolse.app.domain.Province;
import fr.yolse.app.repository.ProvinceRepository;
import fr.yolse.app.service.ProvinceService;
import fr.yolse.app.service.dto.ProvinceDTO;
import fr.yolse.app.service.mapper.ProvinceMapper;
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
 * Test class for the ProvinceResource REST controller.
 *
 * @see ProvinceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YolseApp.class)
public class ProvinceResourceIntTest {

    private static final Integer DEFAULT_PROVINCE_ID = 1;
    private static final Integer UPDATED_PROVINCE_ID = 2;

    private static final String DEFAULT_NOM_PROVINCE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_PROVINCE = "BBBBBBBBBB";

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
    private ProvinceRepository provinceRepository;


    @Autowired
    private ProvinceMapper provinceMapper;
    

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProvinceMockMvc;

    private Province province;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProvinceResource provinceResource = new ProvinceResource(provinceService);
        this.restProvinceMockMvc = MockMvcBuilders.standaloneSetup(provinceResource)
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
    public static Province createEntity(EntityManager em) {
        Province province = new Province()
            .provinceID(DEFAULT_PROVINCE_ID)
            .nomProvince(DEFAULT_NOM_PROVINCE)
            .estActif(DEFAULT_EST_ACTIF)
            .creeLe(DEFAULT_CREE_LE)
            .creePar(DEFAULT_CREE_PAR)
            .modifLe(DEFAULT_MODIF_LE)
            .modifPar(DEFAULT_MODIF_PAR);
        return province;
    }

    @Before
    public void initTest() {
        province = createEntity(em);
    }

    @Test
    @Transactional
    public void createProvince() throws Exception {
        int databaseSizeBeforeCreate = provinceRepository.findAll().size();

        // Create the Province
        ProvinceDTO provinceDTO = provinceMapper.toDto(province);
        restProvinceMockMvc.perform(post("/api/provinces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(provinceDTO)))
            .andExpect(status().isCreated());

        // Validate the Province in the database
        List<Province> provinceList = provinceRepository.findAll();
        assertThat(provinceList).hasSize(databaseSizeBeforeCreate + 1);
        Province testProvince = provinceList.get(provinceList.size() - 1);
        assertThat(testProvince.getProvinceID()).isEqualTo(DEFAULT_PROVINCE_ID);
        assertThat(testProvince.getNomProvince()).isEqualTo(DEFAULT_NOM_PROVINCE);
        assertThat(testProvince.isEstActif()).isEqualTo(DEFAULT_EST_ACTIF);
        assertThat(testProvince.getCreeLe()).isEqualTo(DEFAULT_CREE_LE);
        assertThat(testProvince.getCreePar()).isEqualTo(DEFAULT_CREE_PAR);
        assertThat(testProvince.getModifLe()).isEqualTo(DEFAULT_MODIF_LE);
        assertThat(testProvince.getModifPar()).isEqualTo(DEFAULT_MODIF_PAR);
    }

    @Test
    @Transactional
    public void createProvinceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = provinceRepository.findAll().size();

        // Create the Province with an existing ID
        province.setId(1L);
        ProvinceDTO provinceDTO = provinceMapper.toDto(province);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProvinceMockMvc.perform(post("/api/provinces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(provinceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Province in the database
        List<Province> provinceList = provinceRepository.findAll();
        assertThat(provinceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkProvinceIDIsRequired() throws Exception {
        int databaseSizeBeforeTest = provinceRepository.findAll().size();
        // set the field null
        province.setProvinceID(null);

        // Create the Province, which fails.
        ProvinceDTO provinceDTO = provinceMapper.toDto(province);

        restProvinceMockMvc.perform(post("/api/provinces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(provinceDTO)))
            .andExpect(status().isBadRequest());

        List<Province> provinceList = provinceRepository.findAll();
        assertThat(provinceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomProvinceIsRequired() throws Exception {
        int databaseSizeBeforeTest = provinceRepository.findAll().size();
        // set the field null
        province.setNomProvince(null);

        // Create the Province, which fails.
        ProvinceDTO provinceDTO = provinceMapper.toDto(province);

        restProvinceMockMvc.perform(post("/api/provinces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(provinceDTO)))
            .andExpect(status().isBadRequest());

        List<Province> provinceList = provinceRepository.findAll();
        assertThat(provinceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProvinces() throws Exception {
        // Initialize the database
        provinceRepository.saveAndFlush(province);

        // Get all the provinceList
        restProvinceMockMvc.perform(get("/api/provinces?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(province.getId().intValue())))
            .andExpect(jsonPath("$.[*].provinceID").value(hasItem(DEFAULT_PROVINCE_ID)))
            .andExpect(jsonPath("$.[*].nomProvince").value(hasItem(DEFAULT_NOM_PROVINCE.toString())))
            .andExpect(jsonPath("$.[*].estActif").value(hasItem(DEFAULT_EST_ACTIF.booleanValue())))
            .andExpect(jsonPath("$.[*].creeLe").value(hasItem(DEFAULT_CREE_LE.toString())))
            .andExpect(jsonPath("$.[*].creePar").value(hasItem(DEFAULT_CREE_PAR.toString())))
            .andExpect(jsonPath("$.[*].modifLe").value(hasItem(DEFAULT_MODIF_LE.toString())))
            .andExpect(jsonPath("$.[*].modifPar").value(hasItem(DEFAULT_MODIF_PAR.toString())));
    }
    

    @Test
    @Transactional
    public void getProvince() throws Exception {
        // Initialize the database
        provinceRepository.saveAndFlush(province);

        // Get the province
        restProvinceMockMvc.perform(get("/api/provinces/{id}", province.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(province.getId().intValue()))
            .andExpect(jsonPath("$.provinceID").value(DEFAULT_PROVINCE_ID))
            .andExpect(jsonPath("$.nomProvince").value(DEFAULT_NOM_PROVINCE.toString()))
            .andExpect(jsonPath("$.estActif").value(DEFAULT_EST_ACTIF.booleanValue()))
            .andExpect(jsonPath("$.creeLe").value(DEFAULT_CREE_LE.toString()))
            .andExpect(jsonPath("$.creePar").value(DEFAULT_CREE_PAR.toString()))
            .andExpect(jsonPath("$.modifLe").value(DEFAULT_MODIF_LE.toString()))
            .andExpect(jsonPath("$.modifPar").value(DEFAULT_MODIF_PAR.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingProvince() throws Exception {
        // Get the province
        restProvinceMockMvc.perform(get("/api/provinces/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProvince() throws Exception {
        // Initialize the database
        provinceRepository.saveAndFlush(province);

        int databaseSizeBeforeUpdate = provinceRepository.findAll().size();

        // Update the province
        Province updatedProvince = provinceRepository.findById(province.getId()).get();
        // Disconnect from session so that the updates on updatedProvince are not directly saved in db
        em.detach(updatedProvince);
        updatedProvince
            .provinceID(UPDATED_PROVINCE_ID)
            .nomProvince(UPDATED_NOM_PROVINCE)
            .estActif(UPDATED_EST_ACTIF)
            .creeLe(UPDATED_CREE_LE)
            .creePar(UPDATED_CREE_PAR)
            .modifLe(UPDATED_MODIF_LE)
            .modifPar(UPDATED_MODIF_PAR);
        ProvinceDTO provinceDTO = provinceMapper.toDto(updatedProvince);

        restProvinceMockMvc.perform(put("/api/provinces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(provinceDTO)))
            .andExpect(status().isOk());

        // Validate the Province in the database
        List<Province> provinceList = provinceRepository.findAll();
        assertThat(provinceList).hasSize(databaseSizeBeforeUpdate);
        Province testProvince = provinceList.get(provinceList.size() - 1);
        assertThat(testProvince.getProvinceID()).isEqualTo(UPDATED_PROVINCE_ID);
        assertThat(testProvince.getNomProvince()).isEqualTo(UPDATED_NOM_PROVINCE);
        assertThat(testProvince.isEstActif()).isEqualTo(UPDATED_EST_ACTIF);
        assertThat(testProvince.getCreeLe()).isEqualTo(UPDATED_CREE_LE);
        assertThat(testProvince.getCreePar()).isEqualTo(UPDATED_CREE_PAR);
        assertThat(testProvince.getModifLe()).isEqualTo(UPDATED_MODIF_LE);
        assertThat(testProvince.getModifPar()).isEqualTo(UPDATED_MODIF_PAR);
    }

    @Test
    @Transactional
    public void updateNonExistingProvince() throws Exception {
        int databaseSizeBeforeUpdate = provinceRepository.findAll().size();

        // Create the Province
        ProvinceDTO provinceDTO = provinceMapper.toDto(province);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProvinceMockMvc.perform(put("/api/provinces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(provinceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Province in the database
        List<Province> provinceList = provinceRepository.findAll();
        assertThat(provinceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProvince() throws Exception {
        // Initialize the database
        provinceRepository.saveAndFlush(province);

        int databaseSizeBeforeDelete = provinceRepository.findAll().size();

        // Get the province
        restProvinceMockMvc.perform(delete("/api/provinces/{id}", province.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Province> provinceList = provinceRepository.findAll();
        assertThat(provinceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Province.class);
        Province province1 = new Province();
        province1.setId(1L);
        Province province2 = new Province();
        province2.setId(province1.getId());
        assertThat(province1).isEqualTo(province2);
        province2.setId(2L);
        assertThat(province1).isNotEqualTo(province2);
        province1.setId(null);
        assertThat(province1).isNotEqualTo(province2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProvinceDTO.class);
        ProvinceDTO provinceDTO1 = new ProvinceDTO();
        provinceDTO1.setId(1L);
        ProvinceDTO provinceDTO2 = new ProvinceDTO();
        assertThat(provinceDTO1).isNotEqualTo(provinceDTO2);
        provinceDTO2.setId(provinceDTO1.getId());
        assertThat(provinceDTO1).isEqualTo(provinceDTO2);
        provinceDTO2.setId(2L);
        assertThat(provinceDTO1).isNotEqualTo(provinceDTO2);
        provinceDTO1.setId(null);
        assertThat(provinceDTO1).isNotEqualTo(provinceDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(provinceMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(provinceMapper.fromId(null)).isNull();
    }
}
