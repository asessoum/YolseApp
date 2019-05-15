package fr.yolse.app.web.rest;

import fr.yolse.app.YolseApp;
import fr.yolse.app.domain.Transaction;
import fr.yolse.app.repository.TransactionRepository;
import fr.yolse.app.service.TransactionService;
import fr.yolse.app.service.dto.TransactionDTO;
import fr.yolse.app.service.mapper.TransactionMapper;
import fr.yolse.app.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

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
 * Integration tests for the {@Link TransactionResource} REST controller.
 */
@SpringBootTest(classes = YolseApp.class)
public class TransactionResourceIT {

    private static final Integer DEFAULT_TRANSACTION_ID = 1;
    private static final Integer UPDATED_TRANSACTION_ID = 2;

    private static final Integer DEFAULT_LOCAL_ID = 1;
    private static final Integer UPDATED_LOCAL_ID = 2;

    private static final Integer DEFAULT_REMOTE_ID = 1;
    private static final Integer UPDATED_REMOTE_ID = 2;

    private static final Integer DEFAULT_VENTE_ID = 1;
    private static final Integer UPDATED_VENTE_ID = 2;

    private static final Integer DEFAULT_VENDEUR_ID = 1;
    private static final Integer UPDATED_VENDEUR_ID = 2;

    private static final Double DEFAULT_QUANTITE_TOTAL = 1D;
    private static final Double UPDATED_QUANTITE_TOTAL = 2D;

    private static final Double DEFAULT_PRIX_TOTAL_TRANSACTION = 1D;
    private static final Double UPDATED_PRIX_TOTAL_TRANSACTION = 2D;

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
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restTransactionMockMvc;

    private Transaction transaction;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TransactionResource transactionResource = new TransactionResource(transactionService);
        this.restTransactionMockMvc = MockMvcBuilders.standaloneSetup(transactionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Transaction createEntity(EntityManager em) {
        Transaction transaction = new Transaction()
            .transactionID(DEFAULT_TRANSACTION_ID)
            .localID(DEFAULT_LOCAL_ID)
            .remoteID(DEFAULT_REMOTE_ID)
            .venteID(DEFAULT_VENTE_ID)
            .vendeurID(DEFAULT_VENDEUR_ID)
            .quantiteTotal(DEFAULT_QUANTITE_TOTAL)
            .prixTotalTransaction(DEFAULT_PRIX_TOTAL_TRANSACTION)
            .validSup(DEFAULT_VALID_SUP)
            .validRes(DEFAULT_VALID_RES)
            .estActif(DEFAULT_EST_ACTIF)
            .creeLe(DEFAULT_CREE_LE)
            .creePar(DEFAULT_CREE_PAR)
            .modifLe(DEFAULT_MODIF_LE)
            .modifPar(DEFAULT_MODIF_PAR);
        return transaction;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Transaction createUpdatedEntity(EntityManager em) {
        Transaction transaction = new Transaction()
            .transactionID(UPDATED_TRANSACTION_ID)
            .localID(UPDATED_LOCAL_ID)
            .remoteID(UPDATED_REMOTE_ID)
            .venteID(UPDATED_VENTE_ID)
            .vendeurID(UPDATED_VENDEUR_ID)
            .quantiteTotal(UPDATED_QUANTITE_TOTAL)
            .prixTotalTransaction(UPDATED_PRIX_TOTAL_TRANSACTION)
            .validSup(UPDATED_VALID_SUP)
            .validRes(UPDATED_VALID_RES)
            .estActif(UPDATED_EST_ACTIF)
            .creeLe(UPDATED_CREE_LE)
            .creePar(UPDATED_CREE_PAR)
            .modifLe(UPDATED_MODIF_LE)
            .modifPar(UPDATED_MODIF_PAR);
        return transaction;
    }

    @BeforeEach
    public void initTest() {
        transaction = createEntity(em);
    }

    @Test
    @Transactional
    public void createTransaction() throws Exception {
        int databaseSizeBeforeCreate = transactionRepository.findAll().size();

        // Create the Transaction
        TransactionDTO transactionDTO = transactionMapper.toDto(transaction);
        restTransactionMockMvc.perform(post("/api/transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionDTO)))
            .andExpect(status().isCreated());

        // Validate the Transaction in the database
        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeCreate + 1);
        Transaction testTransaction = transactionList.get(transactionList.size() - 1);
        assertThat(testTransaction.getTransactionID()).isEqualTo(DEFAULT_TRANSACTION_ID);
        assertThat(testTransaction.getLocalID()).isEqualTo(DEFAULT_LOCAL_ID);
        assertThat(testTransaction.getRemoteID()).isEqualTo(DEFAULT_REMOTE_ID);
        assertThat(testTransaction.getVenteID()).isEqualTo(DEFAULT_VENTE_ID);
        assertThat(testTransaction.getVendeurID()).isEqualTo(DEFAULT_VENDEUR_ID);
        assertThat(testTransaction.getQuantiteTotal()).isEqualTo(DEFAULT_QUANTITE_TOTAL);
        assertThat(testTransaction.getPrixTotalTransaction()).isEqualTo(DEFAULT_PRIX_TOTAL_TRANSACTION);
        assertThat(testTransaction.isValidSup()).isEqualTo(DEFAULT_VALID_SUP);
        assertThat(testTransaction.isValidRes()).isEqualTo(DEFAULT_VALID_RES);
        assertThat(testTransaction.isEstActif()).isEqualTo(DEFAULT_EST_ACTIF);
        assertThat(testTransaction.getCreeLe()).isEqualTo(DEFAULT_CREE_LE);
        assertThat(testTransaction.getCreePar()).isEqualTo(DEFAULT_CREE_PAR);
        assertThat(testTransaction.getModifLe()).isEqualTo(DEFAULT_MODIF_LE);
        assertThat(testTransaction.getModifPar()).isEqualTo(DEFAULT_MODIF_PAR);
    }

    @Test
    @Transactional
    public void createTransactionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = transactionRepository.findAll().size();

        // Create the Transaction with an existing ID
        transaction.setId(1L);
        TransactionDTO transactionDTO = transactionMapper.toDto(transaction);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransactionMockMvc.perform(post("/api/transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Transaction in the database
        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTransactionIDIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRepository.findAll().size();
        // set the field null
        transaction.setTransactionID(null);

        // Create the Transaction, which fails.
        TransactionDTO transactionDTO = transactionMapper.toDto(transaction);

        restTransactionMockMvc.perform(post("/api/transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionDTO)))
            .andExpect(status().isBadRequest());

        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLocalIDIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRepository.findAll().size();
        // set the field null
        transaction.setLocalID(null);

        // Create the Transaction, which fails.
        TransactionDTO transactionDTO = transactionMapper.toDto(transaction);

        restTransactionMockMvc.perform(post("/api/transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionDTO)))
            .andExpect(status().isBadRequest());

        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRemoteIDIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRepository.findAll().size();
        // set the field null
        transaction.setRemoteID(null);

        // Create the Transaction, which fails.
        TransactionDTO transactionDTO = transactionMapper.toDto(transaction);

        restTransactionMockMvc.perform(post("/api/transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionDTO)))
            .andExpect(status().isBadRequest());

        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVenteIDIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRepository.findAll().size();
        // set the field null
        transaction.setVenteID(null);

        // Create the Transaction, which fails.
        TransactionDTO transactionDTO = transactionMapper.toDto(transaction);

        restTransactionMockMvc.perform(post("/api/transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionDTO)))
            .andExpect(status().isBadRequest());

        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVendeurIDIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRepository.findAll().size();
        // set the field null
        transaction.setVendeurID(null);

        // Create the Transaction, which fails.
        TransactionDTO transactionDTO = transactionMapper.toDto(transaction);

        restTransactionMockMvc.perform(post("/api/transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionDTO)))
            .andExpect(status().isBadRequest());

        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQuantiteTotalIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRepository.findAll().size();
        // set the field null
        transaction.setQuantiteTotal(null);

        // Create the Transaction, which fails.
        TransactionDTO transactionDTO = transactionMapper.toDto(transaction);

        restTransactionMockMvc.perform(post("/api/transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionDTO)))
            .andExpect(status().isBadRequest());

        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrixTotalTransactionIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRepository.findAll().size();
        // set the field null
        transaction.setPrixTotalTransaction(null);

        // Create the Transaction, which fails.
        TransactionDTO transactionDTO = transactionMapper.toDto(transaction);

        restTransactionMockMvc.perform(post("/api/transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionDTO)))
            .andExpect(status().isBadRequest());

        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTransactions() throws Exception {
        // Initialize the database
        transactionRepository.saveAndFlush(transaction);

        // Get all the transactionList
        restTransactionMockMvc.perform(get("/api/transactions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transaction.getId().intValue())))
            .andExpect(jsonPath("$.[*].transactionID").value(hasItem(DEFAULT_TRANSACTION_ID)))
            .andExpect(jsonPath("$.[*].localID").value(hasItem(DEFAULT_LOCAL_ID)))
            .andExpect(jsonPath("$.[*].remoteID").value(hasItem(DEFAULT_REMOTE_ID)))
            .andExpect(jsonPath("$.[*].venteID").value(hasItem(DEFAULT_VENTE_ID)))
            .andExpect(jsonPath("$.[*].vendeurID").value(hasItem(DEFAULT_VENDEUR_ID)))
            .andExpect(jsonPath("$.[*].quantiteTotal").value(hasItem(DEFAULT_QUANTITE_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].prixTotalTransaction").value(hasItem(DEFAULT_PRIX_TOTAL_TRANSACTION.doubleValue())))
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
    public void getTransaction() throws Exception {
        // Initialize the database
        transactionRepository.saveAndFlush(transaction);

        // Get the transaction
        restTransactionMockMvc.perform(get("/api/transactions/{id}", transaction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(transaction.getId().intValue()))
            .andExpect(jsonPath("$.transactionID").value(DEFAULT_TRANSACTION_ID))
            .andExpect(jsonPath("$.localID").value(DEFAULT_LOCAL_ID))
            .andExpect(jsonPath("$.remoteID").value(DEFAULT_REMOTE_ID))
            .andExpect(jsonPath("$.venteID").value(DEFAULT_VENTE_ID))
            .andExpect(jsonPath("$.vendeurID").value(DEFAULT_VENDEUR_ID))
            .andExpect(jsonPath("$.quantiteTotal").value(DEFAULT_QUANTITE_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.prixTotalTransaction").value(DEFAULT_PRIX_TOTAL_TRANSACTION.doubleValue()))
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
    public void getNonExistingTransaction() throws Exception {
        // Get the transaction
        restTransactionMockMvc.perform(get("/api/transactions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTransaction() throws Exception {
        // Initialize the database
        transactionRepository.saveAndFlush(transaction);

        int databaseSizeBeforeUpdate = transactionRepository.findAll().size();

        // Update the transaction
        Transaction updatedTransaction = transactionRepository.findById(transaction.getId()).get();
        // Disconnect from session so that the updates on updatedTransaction are not directly saved in db
        em.detach(updatedTransaction);
        updatedTransaction
            .transactionID(UPDATED_TRANSACTION_ID)
            .localID(UPDATED_LOCAL_ID)
            .remoteID(UPDATED_REMOTE_ID)
            .venteID(UPDATED_VENTE_ID)
            .vendeurID(UPDATED_VENDEUR_ID)
            .quantiteTotal(UPDATED_QUANTITE_TOTAL)
            .prixTotalTransaction(UPDATED_PRIX_TOTAL_TRANSACTION)
            .validSup(UPDATED_VALID_SUP)
            .validRes(UPDATED_VALID_RES)
            .estActif(UPDATED_EST_ACTIF)
            .creeLe(UPDATED_CREE_LE)
            .creePar(UPDATED_CREE_PAR)
            .modifLe(UPDATED_MODIF_LE)
            .modifPar(UPDATED_MODIF_PAR);
        TransactionDTO transactionDTO = transactionMapper.toDto(updatedTransaction);

        restTransactionMockMvc.perform(put("/api/transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionDTO)))
            .andExpect(status().isOk());

        // Validate the Transaction in the database
        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeUpdate);
        Transaction testTransaction = transactionList.get(transactionList.size() - 1);
        assertThat(testTransaction.getTransactionID()).isEqualTo(UPDATED_TRANSACTION_ID);
        assertThat(testTransaction.getLocalID()).isEqualTo(UPDATED_LOCAL_ID);
        assertThat(testTransaction.getRemoteID()).isEqualTo(UPDATED_REMOTE_ID);
        assertThat(testTransaction.getVenteID()).isEqualTo(UPDATED_VENTE_ID);
        assertThat(testTransaction.getVendeurID()).isEqualTo(UPDATED_VENDEUR_ID);
        assertThat(testTransaction.getQuantiteTotal()).isEqualTo(UPDATED_QUANTITE_TOTAL);
        assertThat(testTransaction.getPrixTotalTransaction()).isEqualTo(UPDATED_PRIX_TOTAL_TRANSACTION);
        assertThat(testTransaction.isValidSup()).isEqualTo(UPDATED_VALID_SUP);
        assertThat(testTransaction.isValidRes()).isEqualTo(UPDATED_VALID_RES);
        assertThat(testTransaction.isEstActif()).isEqualTo(UPDATED_EST_ACTIF);
        assertThat(testTransaction.getCreeLe()).isEqualTo(UPDATED_CREE_LE);
        assertThat(testTransaction.getCreePar()).isEqualTo(UPDATED_CREE_PAR);
        assertThat(testTransaction.getModifLe()).isEqualTo(UPDATED_MODIF_LE);
        assertThat(testTransaction.getModifPar()).isEqualTo(UPDATED_MODIF_PAR);
    }

    @Test
    @Transactional
    public void updateNonExistingTransaction() throws Exception {
        int databaseSizeBeforeUpdate = transactionRepository.findAll().size();

        // Create the Transaction
        TransactionDTO transactionDTO = transactionMapper.toDto(transaction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransactionMockMvc.perform(put("/api/transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Transaction in the database
        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTransaction() throws Exception {
        // Initialize the database
        transactionRepository.saveAndFlush(transaction);

        int databaseSizeBeforeDelete = transactionRepository.findAll().size();

        // Delete the transaction
        restTransactionMockMvc.perform(delete("/api/transactions/{id}", transaction.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Transaction.class);
        Transaction transaction1 = new Transaction();
        transaction1.setId(1L);
        Transaction transaction2 = new Transaction();
        transaction2.setId(transaction1.getId());
        assertThat(transaction1).isEqualTo(transaction2);
        transaction2.setId(2L);
        assertThat(transaction1).isNotEqualTo(transaction2);
        transaction1.setId(null);
        assertThat(transaction1).isNotEqualTo(transaction2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransactionDTO.class);
        TransactionDTO transactionDTO1 = new TransactionDTO();
        transactionDTO1.setId(1L);
        TransactionDTO transactionDTO2 = new TransactionDTO();
        assertThat(transactionDTO1).isNotEqualTo(transactionDTO2);
        transactionDTO2.setId(transactionDTO1.getId());
        assertThat(transactionDTO1).isEqualTo(transactionDTO2);
        transactionDTO2.setId(2L);
        assertThat(transactionDTO1).isNotEqualTo(transactionDTO2);
        transactionDTO1.setId(null);
        assertThat(transactionDTO1).isNotEqualTo(transactionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(transactionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(transactionMapper.fromId(null)).isNull();
    }
}
