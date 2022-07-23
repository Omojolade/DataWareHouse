package com.example.datawarehouse.resource;


import com.example.datawarehouse.DataWarehouseApplication;
import com.example.datawarehouse.domain.FxDeal;
import com.example.datawarehouse.repository.FxDealRepository;
import com.example.datawarehouse.service.FxDealDTO;
import com.example.datawarehouse.service.mapper.FxDealMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(classes = DataWarehouseApplication.class)
public class FxDealResourceTest {

    @Autowired
    FxDealRepository fxDealRepository;

    @Autowired
    FxDealMapper fxDealMapper;

    @Autowired
    private MockMvc restFxDealMockMvc;

    private FxDeal fxDeal;

    private static final ObjectMapper mapper = createObjectMapper();


    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        return mapper.writeValueAsBytes(object);
    }

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }


    private final static String DEFAULT_UNIQUE_ID = "4";
    private final static BigDecimal DEFAULT_DEAL_AMOUNT = BigDecimal.ONE;
    private final static Currency DEFAULT_FROM = Currency.getInstance("NGN");
    private final static Currency DEFAULT_TO = Currency.getInstance("USD");
    private final static Instant DEFAULT_DEAL_TIMESTAMP = Instant.now();

    @BeforeEach
    public void initTest() {
        fxDeal = createDeal();
    }

    @AfterEach
    public void destroyTest() {
        fxDealRepository.deleteAll();
    }

    public static FxDeal createDeal() {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setId(DEFAULT_UNIQUE_ID);
        fxDeal.setDealAmount(DEFAULT_DEAL_AMOUNT);
        fxDeal.setFrom(DEFAULT_FROM);
        fxDeal.setTo(DEFAULT_TO);
        fxDeal.setDealTimestamp(DEFAULT_DEAL_TIMESTAMP);
        return fxDeal;
    }

    @Test
    @Transactional
    void createFxDeal() throws Exception {
        int databaseSizeBeforeCreate = fxDealRepository.findAll().size();
        // Create the FxDeal
        FxDealDTO fxDealDTO = fxDealMapper.toDto(fxDeal);
        restFxDealMockMvc
            .perform(post("/api/create/fx-deal").contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(fxDealDTO)))
            .andExpect(status().isCreated());

        // Validate the FxDeal in the database
        List<FxDeal> fxDealList = fxDealRepository.findAll();
        assertThat(fxDealList).hasSize(databaseSizeBeforeCreate + 1);
        FxDeal testFxDeal = fxDealList.get(fxDealList.size() - 1);
        assertThat(testFxDeal.getDealAmount()).isEqualTo(DEFAULT_DEAL_AMOUNT);
        assertThat(testFxDeal.getDealTimestamp()).isEqualTo(DEFAULT_DEAL_TIMESTAMP);
        assertThat(testFxDeal.getFrom()).isEqualTo(DEFAULT_FROM);
        assertThat(testFxDeal.getTo()).isEqualTo(DEFAULT_TO);
    }

    @Test
    @Transactional
    void getAllFxDeals() throws Exception {
        // Initialize the database
        fxDealRepository.saveAndFlush(fxDeal);

        // Get all the fx deals
        restFxDealMockMvc
            .perform(get("/api/fx-deals"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.data.[*].dealAmount").value(hasItem(DEFAULT_DEAL_AMOUNT.intValue())))
            .andExpect(jsonPath("$.data.[*].from").value(hasItem(DEFAULT_FROM.getCurrencyCode())))
            .andExpect(jsonPath("$.data.[*].to").value(hasItem(DEFAULT_TO.getCurrencyCode())));
    }


    @Test
    @Transactional
    void getAllFxDealById() throws Exception {
        // Initialize the database
        fxDealRepository.saveAndFlush(fxDeal);

        // Get all the fx deals
        restFxDealMockMvc
            .perform(get("/api/fx-deal/"+fxDeal .getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.data.dealAmount").value(DEFAULT_DEAL_AMOUNT.intValue()))
            .andExpect(jsonPath("$.data.from").value(DEFAULT_FROM.getCurrencyCode()))
            .andExpect(jsonPath("$.data.to").value(DEFAULT_TO.getCurrencyCode()));
    }

}
