package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.ThuaTachAsserts.*;
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
import vn.vnpt.domain.ThuaTach;
import vn.vnpt.repository.ThuaTachRepository;
import vn.vnpt.service.dto.ThuaTachDTO;
import vn.vnpt.service.mapper.ThuaTachMapper;

/**
 * Integration tests for the {@link ThuaTachResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ThuaTachResourceIT {

    private static final Long DEFAULT_ID_THUA_TACH = 1L;
    private static final Long UPDATED_ID_THUA_TACH = 2L;
    private static final Long SMALLER_ID_THUA_TACH = 1L - 1L;

    private static final Long DEFAULT_ID_TAI_SAN = 1L;
    private static final Long UPDATED_ID_TAI_SAN = 2L;
    private static final Long SMALLER_ID_TAI_SAN = 1L - 1L;

    private static final String DEFAULT_THONG_TIN_THUA_TACH = "AAAAAAAAAA";
    private static final String UPDATED_THONG_TIN_THUA_TACH = "BBBBBBBBBB";

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;
    private static final Long SMALLER_TRANG_THAI = 1L - 1L;

    private static final String ENTITY_API_URL = "/api/thua-taches";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ThuaTachRepository thuaTachRepository;

    @Autowired
    private ThuaTachMapper thuaTachMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restThuaTachMockMvc;

    private ThuaTach thuaTach;

    private ThuaTach insertedThuaTach;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ThuaTach createEntity(EntityManager em) {
        ThuaTach thuaTach = new ThuaTach()
            .idThuaTach(DEFAULT_ID_THUA_TACH)
            .idTaiSan(DEFAULT_ID_TAI_SAN)
            .thongTinThuaTach(DEFAULT_THONG_TIN_THUA_TACH)
            .trangThai(DEFAULT_TRANG_THAI);
        return thuaTach;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ThuaTach createUpdatedEntity(EntityManager em) {
        ThuaTach thuaTach = new ThuaTach()
            .idThuaTach(UPDATED_ID_THUA_TACH)
            .idTaiSan(UPDATED_ID_TAI_SAN)
            .thongTinThuaTach(UPDATED_THONG_TIN_THUA_TACH)
            .trangThai(UPDATED_TRANG_THAI);
        return thuaTach;
    }

    @BeforeEach
    public void initTest() {
        thuaTach = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedThuaTach != null) {
            thuaTachRepository.delete(insertedThuaTach);
            insertedThuaTach = null;
        }
    }

    @Test
    @Transactional
    void createThuaTach() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ThuaTach
        ThuaTachDTO thuaTachDTO = thuaTachMapper.toDto(thuaTach);
        var returnedThuaTachDTO = om.readValue(
            restThuaTachMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(thuaTachDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ThuaTachDTO.class
        );

        // Validate the ThuaTach in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedThuaTach = thuaTachMapper.toEntity(returnedThuaTachDTO);
        assertThuaTachUpdatableFieldsEquals(returnedThuaTach, getPersistedThuaTach(returnedThuaTach));

        insertedThuaTach = returnedThuaTach;
    }

    @Test
    @Transactional
    void createThuaTachWithExistingId() throws Exception {
        // Create the ThuaTach with an existing ID
        thuaTach.setId(1L);
        ThuaTachDTO thuaTachDTO = thuaTachMapper.toDto(thuaTach);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restThuaTachMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(thuaTachDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ThuaTach in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllThuaTaches() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        // Get all the thuaTachList
        restThuaTachMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(thuaTach.getId().intValue())))
            .andExpect(jsonPath("$.[*].idThuaTach").value(hasItem(DEFAULT_ID_THUA_TACH.intValue())))
            .andExpect(jsonPath("$.[*].idTaiSan").value(hasItem(DEFAULT_ID_TAI_SAN.intValue())))
            .andExpect(jsonPath("$.[*].thongTinThuaTach").value(hasItem(DEFAULT_THONG_TIN_THUA_TACH)))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())));
    }

    @Test
    @Transactional
    void getThuaTach() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        // Get the thuaTach
        restThuaTachMockMvc
            .perform(get(ENTITY_API_URL_ID, thuaTach.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(thuaTach.getId().intValue()))
            .andExpect(jsonPath("$.idThuaTach").value(DEFAULT_ID_THUA_TACH.intValue()))
            .andExpect(jsonPath("$.idTaiSan").value(DEFAULT_ID_TAI_SAN.intValue()))
            .andExpect(jsonPath("$.thongTinThuaTach").value(DEFAULT_THONG_TIN_THUA_TACH))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI.intValue()));
    }

    @Test
    @Transactional
    void getThuaTachesByIdFiltering() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        Long id = thuaTach.getId();

        defaultThuaTachFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultThuaTachFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultThuaTachFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllThuaTachesByIdThuaTachIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        // Get all the thuaTachList where idThuaTach equals to
        defaultThuaTachFiltering("idThuaTach.equals=" + DEFAULT_ID_THUA_TACH, "idThuaTach.equals=" + UPDATED_ID_THUA_TACH);
    }

    @Test
    @Transactional
    void getAllThuaTachesByIdThuaTachIsInShouldWork() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        // Get all the thuaTachList where idThuaTach in
        defaultThuaTachFiltering(
            "idThuaTach.in=" + DEFAULT_ID_THUA_TACH + "," + UPDATED_ID_THUA_TACH,
            "idThuaTach.in=" + UPDATED_ID_THUA_TACH
        );
    }

    @Test
    @Transactional
    void getAllThuaTachesByIdThuaTachIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        // Get all the thuaTachList where idThuaTach is not null
        defaultThuaTachFiltering("idThuaTach.specified=true", "idThuaTach.specified=false");
    }

    @Test
    @Transactional
    void getAllThuaTachesByIdThuaTachIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        // Get all the thuaTachList where idThuaTach is greater than or equal to
        defaultThuaTachFiltering(
            "idThuaTach.greaterThanOrEqual=" + DEFAULT_ID_THUA_TACH,
            "idThuaTach.greaterThanOrEqual=" + UPDATED_ID_THUA_TACH
        );
    }

    @Test
    @Transactional
    void getAllThuaTachesByIdThuaTachIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        // Get all the thuaTachList where idThuaTach is less than or equal to
        defaultThuaTachFiltering(
            "idThuaTach.lessThanOrEqual=" + DEFAULT_ID_THUA_TACH,
            "idThuaTach.lessThanOrEqual=" + SMALLER_ID_THUA_TACH
        );
    }

    @Test
    @Transactional
    void getAllThuaTachesByIdThuaTachIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        // Get all the thuaTachList where idThuaTach is less than
        defaultThuaTachFiltering("idThuaTach.lessThan=" + UPDATED_ID_THUA_TACH, "idThuaTach.lessThan=" + DEFAULT_ID_THUA_TACH);
    }

    @Test
    @Transactional
    void getAllThuaTachesByIdThuaTachIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        // Get all the thuaTachList where idThuaTach is greater than
        defaultThuaTachFiltering("idThuaTach.greaterThan=" + SMALLER_ID_THUA_TACH, "idThuaTach.greaterThan=" + DEFAULT_ID_THUA_TACH);
    }

    @Test
    @Transactional
    void getAllThuaTachesByIdTaiSanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        // Get all the thuaTachList where idTaiSan equals to
        defaultThuaTachFiltering("idTaiSan.equals=" + DEFAULT_ID_TAI_SAN, "idTaiSan.equals=" + UPDATED_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllThuaTachesByIdTaiSanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        // Get all the thuaTachList where idTaiSan in
        defaultThuaTachFiltering("idTaiSan.in=" + DEFAULT_ID_TAI_SAN + "," + UPDATED_ID_TAI_SAN, "idTaiSan.in=" + UPDATED_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllThuaTachesByIdTaiSanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        // Get all the thuaTachList where idTaiSan is not null
        defaultThuaTachFiltering("idTaiSan.specified=true", "idTaiSan.specified=false");
    }

    @Test
    @Transactional
    void getAllThuaTachesByIdTaiSanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        // Get all the thuaTachList where idTaiSan is greater than or equal to
        defaultThuaTachFiltering("idTaiSan.greaterThanOrEqual=" + DEFAULT_ID_TAI_SAN, "idTaiSan.greaterThanOrEqual=" + UPDATED_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllThuaTachesByIdTaiSanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        // Get all the thuaTachList where idTaiSan is less than or equal to
        defaultThuaTachFiltering("idTaiSan.lessThanOrEqual=" + DEFAULT_ID_TAI_SAN, "idTaiSan.lessThanOrEqual=" + SMALLER_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllThuaTachesByIdTaiSanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        // Get all the thuaTachList where idTaiSan is less than
        defaultThuaTachFiltering("idTaiSan.lessThan=" + UPDATED_ID_TAI_SAN, "idTaiSan.lessThan=" + DEFAULT_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllThuaTachesByIdTaiSanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        // Get all the thuaTachList where idTaiSan is greater than
        defaultThuaTachFiltering("idTaiSan.greaterThan=" + SMALLER_ID_TAI_SAN, "idTaiSan.greaterThan=" + DEFAULT_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllThuaTachesByThongTinThuaTachIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        // Get all the thuaTachList where thongTinThuaTach equals to
        defaultThuaTachFiltering(
            "thongTinThuaTach.equals=" + DEFAULT_THONG_TIN_THUA_TACH,
            "thongTinThuaTach.equals=" + UPDATED_THONG_TIN_THUA_TACH
        );
    }

    @Test
    @Transactional
    void getAllThuaTachesByThongTinThuaTachIsInShouldWork() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        // Get all the thuaTachList where thongTinThuaTach in
        defaultThuaTachFiltering(
            "thongTinThuaTach.in=" + DEFAULT_THONG_TIN_THUA_TACH + "," + UPDATED_THONG_TIN_THUA_TACH,
            "thongTinThuaTach.in=" + UPDATED_THONG_TIN_THUA_TACH
        );
    }

    @Test
    @Transactional
    void getAllThuaTachesByThongTinThuaTachIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        // Get all the thuaTachList where thongTinThuaTach is not null
        defaultThuaTachFiltering("thongTinThuaTach.specified=true", "thongTinThuaTach.specified=false");
    }

    @Test
    @Transactional
    void getAllThuaTachesByThongTinThuaTachContainsSomething() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        // Get all the thuaTachList where thongTinThuaTach contains
        defaultThuaTachFiltering(
            "thongTinThuaTach.contains=" + DEFAULT_THONG_TIN_THUA_TACH,
            "thongTinThuaTach.contains=" + UPDATED_THONG_TIN_THUA_TACH
        );
    }

    @Test
    @Transactional
    void getAllThuaTachesByThongTinThuaTachNotContainsSomething() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        // Get all the thuaTachList where thongTinThuaTach does not contain
        defaultThuaTachFiltering(
            "thongTinThuaTach.doesNotContain=" + UPDATED_THONG_TIN_THUA_TACH,
            "thongTinThuaTach.doesNotContain=" + DEFAULT_THONG_TIN_THUA_TACH
        );
    }

    @Test
    @Transactional
    void getAllThuaTachesByTrangThaiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        // Get all the thuaTachList where trangThai equals to
        defaultThuaTachFiltering("trangThai.equals=" + DEFAULT_TRANG_THAI, "trangThai.equals=" + UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllThuaTachesByTrangThaiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        // Get all the thuaTachList where trangThai in
        defaultThuaTachFiltering("trangThai.in=" + DEFAULT_TRANG_THAI + "," + UPDATED_TRANG_THAI, "trangThai.in=" + UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllThuaTachesByTrangThaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        // Get all the thuaTachList where trangThai is not null
        defaultThuaTachFiltering("trangThai.specified=true", "trangThai.specified=false");
    }

    @Test
    @Transactional
    void getAllThuaTachesByTrangThaiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        // Get all the thuaTachList where trangThai is greater than or equal to
        defaultThuaTachFiltering(
            "trangThai.greaterThanOrEqual=" + DEFAULT_TRANG_THAI,
            "trangThai.greaterThanOrEqual=" + UPDATED_TRANG_THAI
        );
    }

    @Test
    @Transactional
    void getAllThuaTachesByTrangThaiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        // Get all the thuaTachList where trangThai is less than or equal to
        defaultThuaTachFiltering("trangThai.lessThanOrEqual=" + DEFAULT_TRANG_THAI, "trangThai.lessThanOrEqual=" + SMALLER_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllThuaTachesByTrangThaiIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        // Get all the thuaTachList where trangThai is less than
        defaultThuaTachFiltering("trangThai.lessThan=" + UPDATED_TRANG_THAI, "trangThai.lessThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllThuaTachesByTrangThaiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        // Get all the thuaTachList where trangThai is greater than
        defaultThuaTachFiltering("trangThai.greaterThan=" + SMALLER_TRANG_THAI, "trangThai.greaterThan=" + DEFAULT_TRANG_THAI);
    }

    private void defaultThuaTachFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultThuaTachShouldBeFound(shouldBeFound);
        defaultThuaTachShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultThuaTachShouldBeFound(String filter) throws Exception {
        restThuaTachMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(thuaTach.getId().intValue())))
            .andExpect(jsonPath("$.[*].idThuaTach").value(hasItem(DEFAULT_ID_THUA_TACH.intValue())))
            .andExpect(jsonPath("$.[*].idTaiSan").value(hasItem(DEFAULT_ID_TAI_SAN.intValue())))
            .andExpect(jsonPath("$.[*].thongTinThuaTach").value(hasItem(DEFAULT_THONG_TIN_THUA_TACH)))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())));

        // Check, that the count call also returns 1
        restThuaTachMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultThuaTachShouldNotBeFound(String filter) throws Exception {
        restThuaTachMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restThuaTachMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingThuaTach() throws Exception {
        // Get the thuaTach
        restThuaTachMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingThuaTach() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the thuaTach
        ThuaTach updatedThuaTach = thuaTachRepository.findById(thuaTach.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedThuaTach are not directly saved in db
        em.detach(updatedThuaTach);
        updatedThuaTach
            .idThuaTach(UPDATED_ID_THUA_TACH)
            .idTaiSan(UPDATED_ID_TAI_SAN)
            .thongTinThuaTach(UPDATED_THONG_TIN_THUA_TACH)
            .trangThai(UPDATED_TRANG_THAI);
        ThuaTachDTO thuaTachDTO = thuaTachMapper.toDto(updatedThuaTach);

        restThuaTachMockMvc
            .perform(
                put(ENTITY_API_URL_ID, thuaTachDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(thuaTachDTO))
            )
            .andExpect(status().isOk());

        // Validate the ThuaTach in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedThuaTachToMatchAllProperties(updatedThuaTach);
    }

    @Test
    @Transactional
    void putNonExistingThuaTach() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        thuaTach.setId(longCount.incrementAndGet());

        // Create the ThuaTach
        ThuaTachDTO thuaTachDTO = thuaTachMapper.toDto(thuaTach);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restThuaTachMockMvc
            .perform(
                put(ENTITY_API_URL_ID, thuaTachDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(thuaTachDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ThuaTach in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchThuaTach() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        thuaTach.setId(longCount.incrementAndGet());

        // Create the ThuaTach
        ThuaTachDTO thuaTachDTO = thuaTachMapper.toDto(thuaTach);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restThuaTachMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(thuaTachDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ThuaTach in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamThuaTach() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        thuaTach.setId(longCount.incrementAndGet());

        // Create the ThuaTach
        ThuaTachDTO thuaTachDTO = thuaTachMapper.toDto(thuaTach);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restThuaTachMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(thuaTachDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ThuaTach in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateThuaTachWithPatch() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the thuaTach using partial update
        ThuaTach partialUpdatedThuaTach = new ThuaTach();
        partialUpdatedThuaTach.setId(thuaTach.getId());

        partialUpdatedThuaTach.thongTinThuaTach(UPDATED_THONG_TIN_THUA_TACH).trangThai(UPDATED_TRANG_THAI);

        restThuaTachMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedThuaTach.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedThuaTach))
            )
            .andExpect(status().isOk());

        // Validate the ThuaTach in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertThuaTachUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedThuaTach, thuaTach), getPersistedThuaTach(thuaTach));
    }

    @Test
    @Transactional
    void fullUpdateThuaTachWithPatch() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the thuaTach using partial update
        ThuaTach partialUpdatedThuaTach = new ThuaTach();
        partialUpdatedThuaTach.setId(thuaTach.getId());

        partialUpdatedThuaTach
            .idThuaTach(UPDATED_ID_THUA_TACH)
            .idTaiSan(UPDATED_ID_TAI_SAN)
            .thongTinThuaTach(UPDATED_THONG_TIN_THUA_TACH)
            .trangThai(UPDATED_TRANG_THAI);

        restThuaTachMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedThuaTach.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedThuaTach))
            )
            .andExpect(status().isOk());

        // Validate the ThuaTach in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertThuaTachUpdatableFieldsEquals(partialUpdatedThuaTach, getPersistedThuaTach(partialUpdatedThuaTach));
    }

    @Test
    @Transactional
    void patchNonExistingThuaTach() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        thuaTach.setId(longCount.incrementAndGet());

        // Create the ThuaTach
        ThuaTachDTO thuaTachDTO = thuaTachMapper.toDto(thuaTach);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restThuaTachMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, thuaTachDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(thuaTachDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ThuaTach in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchThuaTach() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        thuaTach.setId(longCount.incrementAndGet());

        // Create the ThuaTach
        ThuaTachDTO thuaTachDTO = thuaTachMapper.toDto(thuaTach);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restThuaTachMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(thuaTachDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ThuaTach in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamThuaTach() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        thuaTach.setId(longCount.incrementAndGet());

        // Create the ThuaTach
        ThuaTachDTO thuaTachDTO = thuaTachMapper.toDto(thuaTach);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restThuaTachMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(thuaTachDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ThuaTach in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteThuaTach() throws Exception {
        // Initialize the database
        insertedThuaTach = thuaTachRepository.saveAndFlush(thuaTach);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the thuaTach
        restThuaTachMockMvc
            .perform(delete(ENTITY_API_URL_ID, thuaTach.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return thuaTachRepository.count();
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

    protected ThuaTach getPersistedThuaTach(ThuaTach thuaTach) {
        return thuaTachRepository.findById(thuaTach.getId()).orElseThrow();
    }

    protected void assertPersistedThuaTachToMatchAllProperties(ThuaTach expectedThuaTach) {
        assertThuaTachAllPropertiesEquals(expectedThuaTach, getPersistedThuaTach(expectedThuaTach));
    }

    protected void assertPersistedThuaTachToMatchUpdatableProperties(ThuaTach expectedThuaTach) {
        assertThuaTachAllUpdatablePropertiesEquals(expectedThuaTach, getPersistedThuaTach(expectedThuaTach));
    }
}
