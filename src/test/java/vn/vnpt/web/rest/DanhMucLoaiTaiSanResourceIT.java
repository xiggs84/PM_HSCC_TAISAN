package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DanhMucLoaiTaiSanAsserts.*;
import static vn.vnpt.web.rest.TestUtil.createUpdateProxyForBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.IntegrationTest;
import vn.vnpt.domain.DanhMucLoaiTaiSan;
import vn.vnpt.repository.DanhMucLoaiTaiSanRepository;
import vn.vnpt.service.dto.DanhMucLoaiTaiSanDTO;
import vn.vnpt.service.mapper.DanhMucLoaiTaiSanMapper;

/**
 * Integration tests for the {@link DanhMucLoaiTaiSanResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DanhMucLoaiTaiSanResourceIT {

    private static final Long DEFAULT_ID_LOAI_TS = 1L;
    private static final Long UPDATED_ID_LOAI_TS = 2L;
    private static final Long SMALLER_ID_LOAI_TS = 1L - 1L;

    private static final String DEFAULT_DIEN_GIAI = "AAAAAAAAAA";
    private static final String UPDATED_DIEN_GIAI = "BBBBBBBBBB";

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;
    private static final Long SMALLER_TRANG_THAI = 1L - 1L;

    private static final String DEFAULT_SEARCH_STR = "AAAAAAAAAA";
    private static final String UPDATED_SEARCH_STR = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/danh-muc-loai-tai-sans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DanhMucLoaiTaiSanRepository danhMucLoaiTaiSanRepository;

    @Autowired
    private DanhMucLoaiTaiSanMapper danhMucLoaiTaiSanMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDanhMucLoaiTaiSanMockMvc;

    private DanhMucLoaiTaiSan danhMucLoaiTaiSan;

    private DanhMucLoaiTaiSan insertedDanhMucLoaiTaiSan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucLoaiTaiSan createEntity(EntityManager em) {
        DanhMucLoaiTaiSan danhMucLoaiTaiSan = new DanhMucLoaiTaiSan()
            .idLoaiTs(DEFAULT_ID_LOAI_TS)
            .dienGiai(DEFAULT_DIEN_GIAI)
            .trangThai(DEFAULT_TRANG_THAI)
            .searchStr(DEFAULT_SEARCH_STR);
        return danhMucLoaiTaiSan;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucLoaiTaiSan createUpdatedEntity(EntityManager em) {
        DanhMucLoaiTaiSan danhMucLoaiTaiSan = new DanhMucLoaiTaiSan()
            .idLoaiTs(UPDATED_ID_LOAI_TS)
            .dienGiai(UPDATED_DIEN_GIAI)
            .trangThai(UPDATED_TRANG_THAI)
            .searchStr(UPDATED_SEARCH_STR);
        return danhMucLoaiTaiSan;
    }

    @BeforeEach
    public void initTest() {
        danhMucLoaiTaiSan = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedDanhMucLoaiTaiSan != null) {
            danhMucLoaiTaiSanRepository.delete(insertedDanhMucLoaiTaiSan);
            insertedDanhMucLoaiTaiSan = null;
        }
    }

    @Test
    @Transactional
    void createDanhMucLoaiTaiSan() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DanhMucLoaiTaiSan
        DanhMucLoaiTaiSanDTO danhMucLoaiTaiSanDTO = danhMucLoaiTaiSanMapper.toDto(danhMucLoaiTaiSan);
        var returnedDanhMucLoaiTaiSanDTO = om.readValue(
            restDanhMucLoaiTaiSanMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucLoaiTaiSanDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DanhMucLoaiTaiSanDTO.class
        );

        // Validate the DanhMucLoaiTaiSan in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDanhMucLoaiTaiSan = danhMucLoaiTaiSanMapper.toEntity(returnedDanhMucLoaiTaiSanDTO);
        assertDanhMucLoaiTaiSanUpdatableFieldsEquals(returnedDanhMucLoaiTaiSan, getPersistedDanhMucLoaiTaiSan(returnedDanhMucLoaiTaiSan));

        insertedDanhMucLoaiTaiSan = returnedDanhMucLoaiTaiSan;
    }

    @Test
    @Transactional
    void createDanhMucLoaiTaiSanWithExistingId() throws Exception {
        // Create the DanhMucLoaiTaiSan with an existing ID
        danhMucLoaiTaiSan.setId(1L);
        DanhMucLoaiTaiSanDTO danhMucLoaiTaiSanDTO = danhMucLoaiTaiSanMapper.toDto(danhMucLoaiTaiSan);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanhMucLoaiTaiSanMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucLoaiTaiSanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DanhMucLoaiTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDanhMucLoaiTaiSans() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.saveAndFlush(danhMucLoaiTaiSan);

        // Get all the danhMucLoaiTaiSanList
        restDanhMucLoaiTaiSanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danhMucLoaiTaiSan.getId().intValue())))
            .andExpect(jsonPath("$.[*].idLoaiTs").value(hasItem(DEFAULT_ID_LOAI_TS.intValue())))
            .andExpect(jsonPath("$.[*].dienGiai").value(hasItem(DEFAULT_DIEN_GIAI)))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())))
            .andExpect(jsonPath("$.[*].searchStr").value(hasItem(DEFAULT_SEARCH_STR)));
    }

    @Test
    @Transactional
    void getDanhMucLoaiTaiSan() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.saveAndFlush(danhMucLoaiTaiSan);

        // Get the danhMucLoaiTaiSan
        restDanhMucLoaiTaiSanMockMvc
            .perform(get(ENTITY_API_URL_ID, danhMucLoaiTaiSan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(danhMucLoaiTaiSan.getId().intValue()))
            .andExpect(jsonPath("$.idLoaiTs").value(DEFAULT_ID_LOAI_TS.intValue()))
            .andExpect(jsonPath("$.dienGiai").value(DEFAULT_DIEN_GIAI))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI.intValue()))
            .andExpect(jsonPath("$.searchStr").value(DEFAULT_SEARCH_STR));
    }

    @Test
    @Transactional
    void getDanhMucLoaiTaiSansByIdFiltering() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.saveAndFlush(danhMucLoaiTaiSan);

        Long id = danhMucLoaiTaiSan.getId();

        defaultDanhMucLoaiTaiSanFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultDanhMucLoaiTaiSanFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultDanhMucLoaiTaiSanFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllDanhMucLoaiTaiSansByIdLoaiTsIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.saveAndFlush(danhMucLoaiTaiSan);

        // Get all the danhMucLoaiTaiSanList where idLoaiTs equals to
        defaultDanhMucLoaiTaiSanFiltering("idLoaiTs.equals=" + DEFAULT_ID_LOAI_TS, "idLoaiTs.equals=" + UPDATED_ID_LOAI_TS);
    }

    @Test
    @Transactional
    void getAllDanhMucLoaiTaiSansByIdLoaiTsIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.saveAndFlush(danhMucLoaiTaiSan);

        // Get all the danhMucLoaiTaiSanList where idLoaiTs in
        defaultDanhMucLoaiTaiSanFiltering(
            "idLoaiTs.in=" + DEFAULT_ID_LOAI_TS + "," + UPDATED_ID_LOAI_TS,
            "idLoaiTs.in=" + UPDATED_ID_LOAI_TS
        );
    }

    @Test
    @Transactional
    void getAllDanhMucLoaiTaiSansByIdLoaiTsIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.saveAndFlush(danhMucLoaiTaiSan);

        // Get all the danhMucLoaiTaiSanList where idLoaiTs is not null
        defaultDanhMucLoaiTaiSanFiltering("idLoaiTs.specified=true", "idLoaiTs.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucLoaiTaiSansByIdLoaiTsIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.saveAndFlush(danhMucLoaiTaiSan);

        // Get all the danhMucLoaiTaiSanList where idLoaiTs is greater than or equal to
        defaultDanhMucLoaiTaiSanFiltering(
            "idLoaiTs.greaterThanOrEqual=" + DEFAULT_ID_LOAI_TS,
            "idLoaiTs.greaterThanOrEqual=" + UPDATED_ID_LOAI_TS
        );
    }

    @Test
    @Transactional
    void getAllDanhMucLoaiTaiSansByIdLoaiTsIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.saveAndFlush(danhMucLoaiTaiSan);

        // Get all the danhMucLoaiTaiSanList where idLoaiTs is less than or equal to
        defaultDanhMucLoaiTaiSanFiltering(
            "idLoaiTs.lessThanOrEqual=" + DEFAULT_ID_LOAI_TS,
            "idLoaiTs.lessThanOrEqual=" + SMALLER_ID_LOAI_TS
        );
    }

    @Test
    @Transactional
    void getAllDanhMucLoaiTaiSansByIdLoaiTsIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.saveAndFlush(danhMucLoaiTaiSan);

        // Get all the danhMucLoaiTaiSanList where idLoaiTs is less than
        defaultDanhMucLoaiTaiSanFiltering("idLoaiTs.lessThan=" + UPDATED_ID_LOAI_TS, "idLoaiTs.lessThan=" + DEFAULT_ID_LOAI_TS);
    }

    @Test
    @Transactional
    void getAllDanhMucLoaiTaiSansByIdLoaiTsIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.saveAndFlush(danhMucLoaiTaiSan);

        // Get all the danhMucLoaiTaiSanList where idLoaiTs is greater than
        defaultDanhMucLoaiTaiSanFiltering("idLoaiTs.greaterThan=" + SMALLER_ID_LOAI_TS, "idLoaiTs.greaterThan=" + DEFAULT_ID_LOAI_TS);
    }

    @Test
    @Transactional
    void getAllDanhMucLoaiTaiSansByDienGiaiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.saveAndFlush(danhMucLoaiTaiSan);

        // Get all the danhMucLoaiTaiSanList where dienGiai equals to
        defaultDanhMucLoaiTaiSanFiltering("dienGiai.equals=" + DEFAULT_DIEN_GIAI, "dienGiai.equals=" + UPDATED_DIEN_GIAI);
    }

    @Test
    @Transactional
    void getAllDanhMucLoaiTaiSansByDienGiaiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.saveAndFlush(danhMucLoaiTaiSan);

        // Get all the danhMucLoaiTaiSanList where dienGiai in
        defaultDanhMucLoaiTaiSanFiltering("dienGiai.in=" + DEFAULT_DIEN_GIAI + "," + UPDATED_DIEN_GIAI, "dienGiai.in=" + UPDATED_DIEN_GIAI);
    }

    @Test
    @Transactional
    void getAllDanhMucLoaiTaiSansByDienGiaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.saveAndFlush(danhMucLoaiTaiSan);

        // Get all the danhMucLoaiTaiSanList where dienGiai is not null
        defaultDanhMucLoaiTaiSanFiltering("dienGiai.specified=true", "dienGiai.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucLoaiTaiSansByDienGiaiContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.saveAndFlush(danhMucLoaiTaiSan);

        // Get all the danhMucLoaiTaiSanList where dienGiai contains
        defaultDanhMucLoaiTaiSanFiltering("dienGiai.contains=" + DEFAULT_DIEN_GIAI, "dienGiai.contains=" + UPDATED_DIEN_GIAI);
    }

    @Test
    @Transactional
    void getAllDanhMucLoaiTaiSansByDienGiaiNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.saveAndFlush(danhMucLoaiTaiSan);

        // Get all the danhMucLoaiTaiSanList where dienGiai does not contain
        defaultDanhMucLoaiTaiSanFiltering("dienGiai.doesNotContain=" + UPDATED_DIEN_GIAI, "dienGiai.doesNotContain=" + DEFAULT_DIEN_GIAI);
    }

    @Test
    @Transactional
    void getAllDanhMucLoaiTaiSansByTrangThaiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.saveAndFlush(danhMucLoaiTaiSan);

        // Get all the danhMucLoaiTaiSanList where trangThai equals to
        defaultDanhMucLoaiTaiSanFiltering("trangThai.equals=" + DEFAULT_TRANG_THAI, "trangThai.equals=" + UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDanhMucLoaiTaiSansByTrangThaiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.saveAndFlush(danhMucLoaiTaiSan);

        // Get all the danhMucLoaiTaiSanList where trangThai in
        defaultDanhMucLoaiTaiSanFiltering(
            "trangThai.in=" + DEFAULT_TRANG_THAI + "," + UPDATED_TRANG_THAI,
            "trangThai.in=" + UPDATED_TRANG_THAI
        );
    }

    @Test
    @Transactional
    void getAllDanhMucLoaiTaiSansByTrangThaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.saveAndFlush(danhMucLoaiTaiSan);

        // Get all the danhMucLoaiTaiSanList where trangThai is not null
        defaultDanhMucLoaiTaiSanFiltering("trangThai.specified=true", "trangThai.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucLoaiTaiSansByTrangThaiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.saveAndFlush(danhMucLoaiTaiSan);

        // Get all the danhMucLoaiTaiSanList where trangThai is greater than or equal to
        defaultDanhMucLoaiTaiSanFiltering(
            "trangThai.greaterThanOrEqual=" + DEFAULT_TRANG_THAI,
            "trangThai.greaterThanOrEqual=" + UPDATED_TRANG_THAI
        );
    }

    @Test
    @Transactional
    void getAllDanhMucLoaiTaiSansByTrangThaiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.saveAndFlush(danhMucLoaiTaiSan);

        // Get all the danhMucLoaiTaiSanList where trangThai is less than or equal to
        defaultDanhMucLoaiTaiSanFiltering(
            "trangThai.lessThanOrEqual=" + DEFAULT_TRANG_THAI,
            "trangThai.lessThanOrEqual=" + SMALLER_TRANG_THAI
        );
    }

    @Test
    @Transactional
    void getAllDanhMucLoaiTaiSansByTrangThaiIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.saveAndFlush(danhMucLoaiTaiSan);

        // Get all the danhMucLoaiTaiSanList where trangThai is less than
        defaultDanhMucLoaiTaiSanFiltering("trangThai.lessThan=" + UPDATED_TRANG_THAI, "trangThai.lessThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDanhMucLoaiTaiSansByTrangThaiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.saveAndFlush(danhMucLoaiTaiSan);

        // Get all the danhMucLoaiTaiSanList where trangThai is greater than
        defaultDanhMucLoaiTaiSanFiltering("trangThai.greaterThan=" + SMALLER_TRANG_THAI, "trangThai.greaterThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDanhMucLoaiTaiSansBySearchStrIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.saveAndFlush(danhMucLoaiTaiSan);

        // Get all the danhMucLoaiTaiSanList where searchStr equals to
        defaultDanhMucLoaiTaiSanFiltering("searchStr.equals=" + DEFAULT_SEARCH_STR, "searchStr.equals=" + UPDATED_SEARCH_STR);
    }

    @Test
    @Transactional
    void getAllDanhMucLoaiTaiSansBySearchStrIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.saveAndFlush(danhMucLoaiTaiSan);

        // Get all the danhMucLoaiTaiSanList where searchStr in
        defaultDanhMucLoaiTaiSanFiltering(
            "searchStr.in=" + DEFAULT_SEARCH_STR + "," + UPDATED_SEARCH_STR,
            "searchStr.in=" + UPDATED_SEARCH_STR
        );
    }

    @Test
    @Transactional
    void getAllDanhMucLoaiTaiSansBySearchStrIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.saveAndFlush(danhMucLoaiTaiSan);

        // Get all the danhMucLoaiTaiSanList where searchStr is not null
        defaultDanhMucLoaiTaiSanFiltering("searchStr.specified=true", "searchStr.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucLoaiTaiSansBySearchStrContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.saveAndFlush(danhMucLoaiTaiSan);

        // Get all the danhMucLoaiTaiSanList where searchStr contains
        defaultDanhMucLoaiTaiSanFiltering("searchStr.contains=" + DEFAULT_SEARCH_STR, "searchStr.contains=" + UPDATED_SEARCH_STR);
    }

    @Test
    @Transactional
    void getAllDanhMucLoaiTaiSansBySearchStrNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.saveAndFlush(danhMucLoaiTaiSan);

        // Get all the danhMucLoaiTaiSanList where searchStr does not contain
        defaultDanhMucLoaiTaiSanFiltering(
            "searchStr.doesNotContain=" + UPDATED_SEARCH_STR,
            "searchStr.doesNotContain=" + DEFAULT_SEARCH_STR
        );
    }

    private void defaultDanhMucLoaiTaiSanFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultDanhMucLoaiTaiSanShouldBeFound(shouldBeFound);
        defaultDanhMucLoaiTaiSanShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDanhMucLoaiTaiSanShouldBeFound(String filter) throws Exception {
        restDanhMucLoaiTaiSanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danhMucLoaiTaiSan.getId().intValue())))
            .andExpect(jsonPath("$.[*].idLoaiTs").value(hasItem(DEFAULT_ID_LOAI_TS.intValue())))
            .andExpect(jsonPath("$.[*].dienGiai").value(hasItem(DEFAULT_DIEN_GIAI)))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())))
            .andExpect(jsonPath("$.[*].searchStr").value(hasItem(DEFAULT_SEARCH_STR)));

        // Check, that the count call also returns 1
        restDanhMucLoaiTaiSanMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDanhMucLoaiTaiSanShouldNotBeFound(String filter) throws Exception {
        restDanhMucLoaiTaiSanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDanhMucLoaiTaiSanMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingDanhMucLoaiTaiSan() throws Exception {
        // Get the danhMucLoaiTaiSan
        restDanhMucLoaiTaiSanMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDanhMucLoaiTaiSan() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.saveAndFlush(danhMucLoaiTaiSan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucLoaiTaiSan
        DanhMucLoaiTaiSan updatedDanhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.findById(danhMucLoaiTaiSan.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDanhMucLoaiTaiSan are not directly saved in db
        em.detach(updatedDanhMucLoaiTaiSan);
        updatedDanhMucLoaiTaiSan
            .idLoaiTs(UPDATED_ID_LOAI_TS)
            .dienGiai(UPDATED_DIEN_GIAI)
            .trangThai(UPDATED_TRANG_THAI)
            .searchStr(UPDATED_SEARCH_STR);
        DanhMucLoaiTaiSanDTO danhMucLoaiTaiSanDTO = danhMucLoaiTaiSanMapper.toDto(updatedDanhMucLoaiTaiSan);

        restDanhMucLoaiTaiSanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucLoaiTaiSanDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucLoaiTaiSanDTO))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucLoaiTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDanhMucLoaiTaiSanToMatchAllProperties(updatedDanhMucLoaiTaiSan);
    }

    @Test
    @Transactional
    void putNonExistingDanhMucLoaiTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiTaiSan.setId(longCount.incrementAndGet());

        // Create the DanhMucLoaiTaiSan
        DanhMucLoaiTaiSanDTO danhMucLoaiTaiSanDTO = danhMucLoaiTaiSanMapper.toDto(danhMucLoaiTaiSan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucLoaiTaiSanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucLoaiTaiSanDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucLoaiTaiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucLoaiTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDanhMucLoaiTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiTaiSan.setId(longCount.incrementAndGet());

        // Create the DanhMucLoaiTaiSan
        DanhMucLoaiTaiSanDTO danhMucLoaiTaiSanDTO = danhMucLoaiTaiSanMapper.toDto(danhMucLoaiTaiSan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucLoaiTaiSanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucLoaiTaiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucLoaiTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDanhMucLoaiTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiTaiSan.setId(longCount.incrementAndGet());

        // Create the DanhMucLoaiTaiSan
        DanhMucLoaiTaiSanDTO danhMucLoaiTaiSanDTO = danhMucLoaiTaiSanMapper.toDto(danhMucLoaiTaiSan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucLoaiTaiSanMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucLoaiTaiSanDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucLoaiTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDanhMucLoaiTaiSanWithPatch() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.saveAndFlush(danhMucLoaiTaiSan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucLoaiTaiSan using partial update
        DanhMucLoaiTaiSan partialUpdatedDanhMucLoaiTaiSan = new DanhMucLoaiTaiSan();
        partialUpdatedDanhMucLoaiTaiSan.setId(danhMucLoaiTaiSan.getId());

        partialUpdatedDanhMucLoaiTaiSan.idLoaiTs(UPDATED_ID_LOAI_TS).trangThai(UPDATED_TRANG_THAI).searchStr(UPDATED_SEARCH_STR);

        restDanhMucLoaiTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucLoaiTaiSan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucLoaiTaiSan))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucLoaiTaiSan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucLoaiTaiSanUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDanhMucLoaiTaiSan, danhMucLoaiTaiSan),
            getPersistedDanhMucLoaiTaiSan(danhMucLoaiTaiSan)
        );
    }

    @Test
    @Transactional
    void fullUpdateDanhMucLoaiTaiSanWithPatch() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.saveAndFlush(danhMucLoaiTaiSan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucLoaiTaiSan using partial update
        DanhMucLoaiTaiSan partialUpdatedDanhMucLoaiTaiSan = new DanhMucLoaiTaiSan();
        partialUpdatedDanhMucLoaiTaiSan.setId(danhMucLoaiTaiSan.getId());

        partialUpdatedDanhMucLoaiTaiSan
            .idLoaiTs(UPDATED_ID_LOAI_TS)
            .dienGiai(UPDATED_DIEN_GIAI)
            .trangThai(UPDATED_TRANG_THAI)
            .searchStr(UPDATED_SEARCH_STR);

        restDanhMucLoaiTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucLoaiTaiSan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucLoaiTaiSan))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucLoaiTaiSan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucLoaiTaiSanUpdatableFieldsEquals(
            partialUpdatedDanhMucLoaiTaiSan,
            getPersistedDanhMucLoaiTaiSan(partialUpdatedDanhMucLoaiTaiSan)
        );
    }

    @Test
    @Transactional
    void patchNonExistingDanhMucLoaiTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiTaiSan.setId(longCount.incrementAndGet());

        // Create the DanhMucLoaiTaiSan
        DanhMucLoaiTaiSanDTO danhMucLoaiTaiSanDTO = danhMucLoaiTaiSanMapper.toDto(danhMucLoaiTaiSan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucLoaiTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, danhMucLoaiTaiSanDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucLoaiTaiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucLoaiTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDanhMucLoaiTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiTaiSan.setId(longCount.incrementAndGet());

        // Create the DanhMucLoaiTaiSan
        DanhMucLoaiTaiSanDTO danhMucLoaiTaiSanDTO = danhMucLoaiTaiSanMapper.toDto(danhMucLoaiTaiSan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucLoaiTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucLoaiTaiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucLoaiTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDanhMucLoaiTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiTaiSan.setId(longCount.incrementAndGet());

        // Create the DanhMucLoaiTaiSan
        DanhMucLoaiTaiSanDTO danhMucLoaiTaiSanDTO = danhMucLoaiTaiSanMapper.toDto(danhMucLoaiTaiSan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucLoaiTaiSanMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(danhMucLoaiTaiSanDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucLoaiTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDanhMucLoaiTaiSan() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.saveAndFlush(danhMucLoaiTaiSan);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the danhMucLoaiTaiSan
        restDanhMucLoaiTaiSanMockMvc
            .perform(delete(ENTITY_API_URL_ID, danhMucLoaiTaiSan.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return danhMucLoaiTaiSanRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected DanhMucLoaiTaiSan getPersistedDanhMucLoaiTaiSan(DanhMucLoaiTaiSan danhMucLoaiTaiSan) {
        return danhMucLoaiTaiSanRepository.findById(danhMucLoaiTaiSan.getId()).orElseThrow();
    }

    protected void assertPersistedDanhMucLoaiTaiSanToMatchAllProperties(DanhMucLoaiTaiSan expectedDanhMucLoaiTaiSan) {
        assertDanhMucLoaiTaiSanAllPropertiesEquals(expectedDanhMucLoaiTaiSan, getPersistedDanhMucLoaiTaiSan(expectedDanhMucLoaiTaiSan));
    }

    protected void assertPersistedDanhMucLoaiTaiSanToMatchUpdatableProperties(DanhMucLoaiTaiSan expectedDanhMucLoaiTaiSan) {
        assertDanhMucLoaiTaiSanAllUpdatablePropertiesEquals(
            expectedDanhMucLoaiTaiSan,
            getPersistedDanhMucLoaiTaiSan(expectedDanhMucLoaiTaiSan)
        );
    }
}
