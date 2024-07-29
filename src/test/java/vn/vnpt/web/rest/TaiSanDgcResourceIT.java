package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.TaiSanDgcAsserts.*;
import static vn.vnpt.web.rest.TestUtil.createUpdateProxyForBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
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
import vn.vnpt.domain.TaiSanDgc;
import vn.vnpt.repository.TaiSanDgcRepository;
import vn.vnpt.service.dto.TaiSanDgcDTO;
import vn.vnpt.service.mapper.TaiSanDgcMapper;

/**
 * Integration tests for the {@link TaiSanDgcResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TaiSanDgcResourceIT {

    private static final Long DEFAULT_ID_TAI_SAN = 1L;
    private static final Long UPDATED_ID_TAI_SAN = 2L;
    private static final Long SMALLER_ID_TAI_SAN = 1L - 1L;

    private static final String DEFAULT_TEN_TAI_SAN = "AAAAAAAAAA";
    private static final String UPDATED_TEN_TAI_SAN = "BBBBBBBBBB";

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;
    private static final Long SMALLER_TRANG_THAI = 1L - 1L;

    private static final String DEFAULT_THONG_TIN_TS = "AAAAAAAAAA";
    private static final String UPDATED_THONG_TIN_TS = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_LOAI_TS = 1L;
    private static final Long UPDATED_ID_LOAI_TS = 2L;
    private static final Long SMALLER_ID_LOAI_TS = 1L - 1L;

    private static final String DEFAULT_GHI_CHU = "AAAAAAAAAA";
    private static final String UPDATED_GHI_CHU = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAY_THAO_TAC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_THAO_TAC = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAY_THAO_TAC = LocalDate.ofEpochDay(-1L);

    private static final Long DEFAULT_NGUOI_THAO_TAC = 1L;
    private static final Long UPDATED_NGUOI_THAO_TAC = 2L;
    private static final Long SMALLER_NGUOI_THAO_TAC = 1L - 1L;

    private static final Long DEFAULT_ID_DUONG_SU = 1L;
    private static final Long UPDATED_ID_DUONG_SU = 2L;
    private static final Long SMALLER_ID_DUONG_SU = 1L - 1L;

    private static final Long DEFAULT_ID_TS_GOC = 1L;
    private static final Long UPDATED_ID_TS_GOC = 2L;
    private static final Long SMALLER_ID_TS_GOC = 1L - 1L;

    private static final String DEFAULT_MA_TAI_SAN = "AAAAAAAAAA";
    private static final String UPDATED_MA_TAI_SAN = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_TINH_TRANG = 1L;
    private static final Long UPDATED_ID_TINH_TRANG = 2L;
    private static final Long SMALLER_ID_TINH_TRANG = 1L - 1L;

    private static final Long DEFAULT_ID_LOAI_NGAN_CHAN = 1L;
    private static final Long UPDATED_ID_LOAI_NGAN_CHAN = 2L;
    private static final Long SMALLER_ID_LOAI_NGAN_CHAN = 1L - 1L;

    private static final LocalDate DEFAULT_NGAY_BD_NGAN_CHAN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_BD_NGAN_CHAN = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAY_BD_NGAN_CHAN = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_NGAY_KT_NGAN_CHAN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_KT_NGAN_CHAN = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAY_KT_NGAN_CHAN = LocalDate.ofEpochDay(-1L);

    private static final Long DEFAULT_ID_MASTER = 1L;
    private static final Long UPDATED_ID_MASTER = 2L;
    private static final Long SMALLER_ID_MASTER = 1L - 1L;

    private static final String DEFAULT_STR_SEARCH = "AAAAAAAAAA";
    private static final String UPDATED_STR_SEARCH = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_DON_VI = 1L;
    private static final Long UPDATED_ID_DON_VI = 2L;
    private static final Long SMALLER_ID_DON_VI = 1L - 1L;

    private static final Long DEFAULT_SO_HS_CV = 1L;
    private static final Long UPDATED_SO_HS_CV = 2L;
    private static final Long SMALLER_SO_HS_CV = 1L - 1L;

    private static final Long DEFAULT_SO_CC = 1L;
    private static final Long UPDATED_SO_CC = 2L;
    private static final Long SMALLER_SO_CC = 1L - 1L;

    private static final Long DEFAULT_SO_VAO_SO = 1L;
    private static final Long UPDATED_SO_VAO_SO = 2L;
    private static final Long SMALLER_SO_VAO_SO = 1L - 1L;

    private static final String DEFAULT_MO_TA = "AAAAAAAAAA";
    private static final String UPDATED_MO_TA = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tai-san-dgcs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaiSanDgcRepository taiSanDgcRepository;

    @Autowired
    private TaiSanDgcMapper taiSanDgcMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTaiSanDgcMockMvc;

    private TaiSanDgc taiSanDgc;

    private TaiSanDgc insertedTaiSanDgc;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaiSanDgc createEntity(EntityManager em) {
        TaiSanDgc taiSanDgc = new TaiSanDgc()
            .idTaiSan(DEFAULT_ID_TAI_SAN)
            .tenTaiSan(DEFAULT_TEN_TAI_SAN)
            .trangThai(DEFAULT_TRANG_THAI)
            .thongTinTs(DEFAULT_THONG_TIN_TS)
            .idLoaiTs(DEFAULT_ID_LOAI_TS)
            .ghiChu(DEFAULT_GHI_CHU)
            .ngayThaoTac(DEFAULT_NGAY_THAO_TAC)
            .nguoiThaoTac(DEFAULT_NGUOI_THAO_TAC)
            .idDuongSu(DEFAULT_ID_DUONG_SU)
            .idTsGoc(DEFAULT_ID_TS_GOC)
            .maTaiSan(DEFAULT_MA_TAI_SAN)
            .idTinhTrang(DEFAULT_ID_TINH_TRANG)
            .idLoaiNganChan(DEFAULT_ID_LOAI_NGAN_CHAN)
            .ngayBdNganChan(DEFAULT_NGAY_BD_NGAN_CHAN)
            .ngayKtNganChan(DEFAULT_NGAY_KT_NGAN_CHAN)
            .idMaster(DEFAULT_ID_MASTER)
            .strSearch(DEFAULT_STR_SEARCH)
            .idDonVi(DEFAULT_ID_DON_VI)
            .soHsCv(DEFAULT_SO_HS_CV)
            .soCc(DEFAULT_SO_CC)
            .soVaoSo(DEFAULT_SO_VAO_SO)
            .moTa(DEFAULT_MO_TA);
        return taiSanDgc;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaiSanDgc createUpdatedEntity(EntityManager em) {
        TaiSanDgc taiSanDgc = new TaiSanDgc()
            .idTaiSan(UPDATED_ID_TAI_SAN)
            .tenTaiSan(UPDATED_TEN_TAI_SAN)
            .trangThai(UPDATED_TRANG_THAI)
            .thongTinTs(UPDATED_THONG_TIN_TS)
            .idLoaiTs(UPDATED_ID_LOAI_TS)
            .ghiChu(UPDATED_GHI_CHU)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDuongSu(UPDATED_ID_DUONG_SU)
            .idTsGoc(UPDATED_ID_TS_GOC)
            .maTaiSan(UPDATED_MA_TAI_SAN)
            .idTinhTrang(UPDATED_ID_TINH_TRANG)
            .idLoaiNganChan(UPDATED_ID_LOAI_NGAN_CHAN)
            .ngayBdNganChan(UPDATED_NGAY_BD_NGAN_CHAN)
            .ngayKtNganChan(UPDATED_NGAY_KT_NGAN_CHAN)
            .idMaster(UPDATED_ID_MASTER)
            .strSearch(UPDATED_STR_SEARCH)
            .idDonVi(UPDATED_ID_DON_VI)
            .soHsCv(UPDATED_SO_HS_CV)
            .soCc(UPDATED_SO_CC)
            .soVaoSo(UPDATED_SO_VAO_SO)
            .moTa(UPDATED_MO_TA);
        return taiSanDgc;
    }

    @BeforeEach
    public void initTest() {
        taiSanDgc = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedTaiSanDgc != null) {
            taiSanDgcRepository.delete(insertedTaiSanDgc);
            insertedTaiSanDgc = null;
        }
    }

    @Test
    @Transactional
    void createTaiSanDgc() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the TaiSanDgc
        TaiSanDgcDTO taiSanDgcDTO = taiSanDgcMapper.toDto(taiSanDgc);
        var returnedTaiSanDgcDTO = om.readValue(
            restTaiSanDgcMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(taiSanDgcDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            TaiSanDgcDTO.class
        );

        // Validate the TaiSanDgc in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedTaiSanDgc = taiSanDgcMapper.toEntity(returnedTaiSanDgcDTO);
        assertTaiSanDgcUpdatableFieldsEquals(returnedTaiSanDgc, getPersistedTaiSanDgc(returnedTaiSanDgc));

        insertedTaiSanDgc = returnedTaiSanDgc;
    }

    @Test
    @Transactional
    void createTaiSanDgcWithExistingId() throws Exception {
        // Create the TaiSanDgc with an existing ID
        taiSanDgc.setId(1L);
        TaiSanDgcDTO taiSanDgcDTO = taiSanDgcMapper.toDto(taiSanDgc);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaiSanDgcMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(taiSanDgcDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TaiSanDgc in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcs() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList
        restTaiSanDgcMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taiSanDgc.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTaiSan").value(hasItem(DEFAULT_ID_TAI_SAN.intValue())))
            .andExpect(jsonPath("$.[*].tenTaiSan").value(hasItem(DEFAULT_TEN_TAI_SAN)))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())))
            .andExpect(jsonPath("$.[*].thongTinTs").value(hasItem(DEFAULT_THONG_TIN_TS)))
            .andExpect(jsonPath("$.[*].idLoaiTs").value(hasItem(DEFAULT_ID_LOAI_TS.intValue())))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU)))
            .andExpect(jsonPath("$.[*].ngayThaoTac").value(hasItem(DEFAULT_NGAY_THAO_TAC.toString())))
            .andExpect(jsonPath("$.[*].nguoiThaoTac").value(hasItem(DEFAULT_NGUOI_THAO_TAC.intValue())))
            .andExpect(jsonPath("$.[*].idDuongSu").value(hasItem(DEFAULT_ID_DUONG_SU.intValue())))
            .andExpect(jsonPath("$.[*].idTsGoc").value(hasItem(DEFAULT_ID_TS_GOC.intValue())))
            .andExpect(jsonPath("$.[*].maTaiSan").value(hasItem(DEFAULT_MA_TAI_SAN)))
            .andExpect(jsonPath("$.[*].idTinhTrang").value(hasItem(DEFAULT_ID_TINH_TRANG.intValue())))
            .andExpect(jsonPath("$.[*].idLoaiNganChan").value(hasItem(DEFAULT_ID_LOAI_NGAN_CHAN.intValue())))
            .andExpect(jsonPath("$.[*].ngayBdNganChan").value(hasItem(DEFAULT_NGAY_BD_NGAN_CHAN.toString())))
            .andExpect(jsonPath("$.[*].ngayKtNganChan").value(hasItem(DEFAULT_NGAY_KT_NGAN_CHAN.toString())))
            .andExpect(jsonPath("$.[*].idMaster").value(hasItem(DEFAULT_ID_MASTER.intValue())))
            .andExpect(jsonPath("$.[*].strSearch").value(hasItem(DEFAULT_STR_SEARCH)))
            .andExpect(jsonPath("$.[*].idDonVi").value(hasItem(DEFAULT_ID_DON_VI.intValue())))
            .andExpect(jsonPath("$.[*].soHsCv").value(hasItem(DEFAULT_SO_HS_CV.intValue())))
            .andExpect(jsonPath("$.[*].soCc").value(hasItem(DEFAULT_SO_CC.intValue())))
            .andExpect(jsonPath("$.[*].soVaoSo").value(hasItem(DEFAULT_SO_VAO_SO.intValue())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)));
    }

    @Test
    @Transactional
    void getTaiSanDgc() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get the taiSanDgc
        restTaiSanDgcMockMvc
            .perform(get(ENTITY_API_URL_ID, taiSanDgc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(taiSanDgc.getId().intValue()))
            .andExpect(jsonPath("$.idTaiSan").value(DEFAULT_ID_TAI_SAN.intValue()))
            .andExpect(jsonPath("$.tenTaiSan").value(DEFAULT_TEN_TAI_SAN))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI.intValue()))
            .andExpect(jsonPath("$.thongTinTs").value(DEFAULT_THONG_TIN_TS))
            .andExpect(jsonPath("$.idLoaiTs").value(DEFAULT_ID_LOAI_TS.intValue()))
            .andExpect(jsonPath("$.ghiChu").value(DEFAULT_GHI_CHU))
            .andExpect(jsonPath("$.ngayThaoTac").value(DEFAULT_NGAY_THAO_TAC.toString()))
            .andExpect(jsonPath("$.nguoiThaoTac").value(DEFAULT_NGUOI_THAO_TAC.intValue()))
            .andExpect(jsonPath("$.idDuongSu").value(DEFAULT_ID_DUONG_SU.intValue()))
            .andExpect(jsonPath("$.idTsGoc").value(DEFAULT_ID_TS_GOC.intValue()))
            .andExpect(jsonPath("$.maTaiSan").value(DEFAULT_MA_TAI_SAN))
            .andExpect(jsonPath("$.idTinhTrang").value(DEFAULT_ID_TINH_TRANG.intValue()))
            .andExpect(jsonPath("$.idLoaiNganChan").value(DEFAULT_ID_LOAI_NGAN_CHAN.intValue()))
            .andExpect(jsonPath("$.ngayBdNganChan").value(DEFAULT_NGAY_BD_NGAN_CHAN.toString()))
            .andExpect(jsonPath("$.ngayKtNganChan").value(DEFAULT_NGAY_KT_NGAN_CHAN.toString()))
            .andExpect(jsonPath("$.idMaster").value(DEFAULT_ID_MASTER.intValue()))
            .andExpect(jsonPath("$.strSearch").value(DEFAULT_STR_SEARCH))
            .andExpect(jsonPath("$.idDonVi").value(DEFAULT_ID_DON_VI.intValue()))
            .andExpect(jsonPath("$.soHsCv").value(DEFAULT_SO_HS_CV.intValue()))
            .andExpect(jsonPath("$.soCc").value(DEFAULT_SO_CC.intValue()))
            .andExpect(jsonPath("$.soVaoSo").value(DEFAULT_SO_VAO_SO.intValue()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA));
    }

    @Test
    @Transactional
    void getTaiSanDgcsByIdFiltering() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        Long id = taiSanDgc.getId();

        defaultTaiSanDgcFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultTaiSanDgcFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultTaiSanDgcFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdTaiSanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idTaiSan equals to
        defaultTaiSanDgcFiltering("idTaiSan.equals=" + DEFAULT_ID_TAI_SAN, "idTaiSan.equals=" + UPDATED_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdTaiSanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idTaiSan in
        defaultTaiSanDgcFiltering("idTaiSan.in=" + DEFAULT_ID_TAI_SAN + "," + UPDATED_ID_TAI_SAN, "idTaiSan.in=" + UPDATED_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdTaiSanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idTaiSan is not null
        defaultTaiSanDgcFiltering("idTaiSan.specified=true", "idTaiSan.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdTaiSanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idTaiSan is greater than or equal to
        defaultTaiSanDgcFiltering("idTaiSan.greaterThanOrEqual=" + DEFAULT_ID_TAI_SAN, "idTaiSan.greaterThanOrEqual=" + UPDATED_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdTaiSanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idTaiSan is less than or equal to
        defaultTaiSanDgcFiltering("idTaiSan.lessThanOrEqual=" + DEFAULT_ID_TAI_SAN, "idTaiSan.lessThanOrEqual=" + SMALLER_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdTaiSanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idTaiSan is less than
        defaultTaiSanDgcFiltering("idTaiSan.lessThan=" + UPDATED_ID_TAI_SAN, "idTaiSan.lessThan=" + DEFAULT_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdTaiSanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idTaiSan is greater than
        defaultTaiSanDgcFiltering("idTaiSan.greaterThan=" + SMALLER_ID_TAI_SAN, "idTaiSan.greaterThan=" + DEFAULT_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByTenTaiSanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where tenTaiSan equals to
        defaultTaiSanDgcFiltering("tenTaiSan.equals=" + DEFAULT_TEN_TAI_SAN, "tenTaiSan.equals=" + UPDATED_TEN_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByTenTaiSanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where tenTaiSan in
        defaultTaiSanDgcFiltering("tenTaiSan.in=" + DEFAULT_TEN_TAI_SAN + "," + UPDATED_TEN_TAI_SAN, "tenTaiSan.in=" + UPDATED_TEN_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByTenTaiSanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where tenTaiSan is not null
        defaultTaiSanDgcFiltering("tenTaiSan.specified=true", "tenTaiSan.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByTenTaiSanContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where tenTaiSan contains
        defaultTaiSanDgcFiltering("tenTaiSan.contains=" + DEFAULT_TEN_TAI_SAN, "tenTaiSan.contains=" + UPDATED_TEN_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByTenTaiSanNotContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where tenTaiSan does not contain
        defaultTaiSanDgcFiltering("tenTaiSan.doesNotContain=" + UPDATED_TEN_TAI_SAN, "tenTaiSan.doesNotContain=" + DEFAULT_TEN_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByTrangThaiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where trangThai equals to
        defaultTaiSanDgcFiltering("trangThai.equals=" + DEFAULT_TRANG_THAI, "trangThai.equals=" + UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByTrangThaiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where trangThai in
        defaultTaiSanDgcFiltering("trangThai.in=" + DEFAULT_TRANG_THAI + "," + UPDATED_TRANG_THAI, "trangThai.in=" + UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByTrangThaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where trangThai is not null
        defaultTaiSanDgcFiltering("trangThai.specified=true", "trangThai.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByTrangThaiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where trangThai is greater than or equal to
        defaultTaiSanDgcFiltering(
            "trangThai.greaterThanOrEqual=" + DEFAULT_TRANG_THAI,
            "trangThai.greaterThanOrEqual=" + UPDATED_TRANG_THAI
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByTrangThaiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where trangThai is less than or equal to
        defaultTaiSanDgcFiltering("trangThai.lessThanOrEqual=" + DEFAULT_TRANG_THAI, "trangThai.lessThanOrEqual=" + SMALLER_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByTrangThaiIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where trangThai is less than
        defaultTaiSanDgcFiltering("trangThai.lessThan=" + UPDATED_TRANG_THAI, "trangThai.lessThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByTrangThaiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where trangThai is greater than
        defaultTaiSanDgcFiltering("trangThai.greaterThan=" + SMALLER_TRANG_THAI, "trangThai.greaterThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByThongTinTsIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where thongTinTs equals to
        defaultTaiSanDgcFiltering("thongTinTs.equals=" + DEFAULT_THONG_TIN_TS, "thongTinTs.equals=" + UPDATED_THONG_TIN_TS);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByThongTinTsIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where thongTinTs in
        defaultTaiSanDgcFiltering(
            "thongTinTs.in=" + DEFAULT_THONG_TIN_TS + "," + UPDATED_THONG_TIN_TS,
            "thongTinTs.in=" + UPDATED_THONG_TIN_TS
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByThongTinTsIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where thongTinTs is not null
        defaultTaiSanDgcFiltering("thongTinTs.specified=true", "thongTinTs.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByThongTinTsContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where thongTinTs contains
        defaultTaiSanDgcFiltering("thongTinTs.contains=" + DEFAULT_THONG_TIN_TS, "thongTinTs.contains=" + UPDATED_THONG_TIN_TS);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByThongTinTsNotContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where thongTinTs does not contain
        defaultTaiSanDgcFiltering("thongTinTs.doesNotContain=" + UPDATED_THONG_TIN_TS, "thongTinTs.doesNotContain=" + DEFAULT_THONG_TIN_TS);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdLoaiTsIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idLoaiTs equals to
        defaultTaiSanDgcFiltering("idLoaiTs.equals=" + DEFAULT_ID_LOAI_TS, "idLoaiTs.equals=" + UPDATED_ID_LOAI_TS);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdLoaiTsIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idLoaiTs in
        defaultTaiSanDgcFiltering("idLoaiTs.in=" + DEFAULT_ID_LOAI_TS + "," + UPDATED_ID_LOAI_TS, "idLoaiTs.in=" + UPDATED_ID_LOAI_TS);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdLoaiTsIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idLoaiTs is not null
        defaultTaiSanDgcFiltering("idLoaiTs.specified=true", "idLoaiTs.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdLoaiTsIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idLoaiTs is greater than or equal to
        defaultTaiSanDgcFiltering("idLoaiTs.greaterThanOrEqual=" + DEFAULT_ID_LOAI_TS, "idLoaiTs.greaterThanOrEqual=" + UPDATED_ID_LOAI_TS);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdLoaiTsIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idLoaiTs is less than or equal to
        defaultTaiSanDgcFiltering("idLoaiTs.lessThanOrEqual=" + DEFAULT_ID_LOAI_TS, "idLoaiTs.lessThanOrEqual=" + SMALLER_ID_LOAI_TS);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdLoaiTsIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idLoaiTs is less than
        defaultTaiSanDgcFiltering("idLoaiTs.lessThan=" + UPDATED_ID_LOAI_TS, "idLoaiTs.lessThan=" + DEFAULT_ID_LOAI_TS);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdLoaiTsIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idLoaiTs is greater than
        defaultTaiSanDgcFiltering("idLoaiTs.greaterThan=" + SMALLER_ID_LOAI_TS, "idLoaiTs.greaterThan=" + DEFAULT_ID_LOAI_TS);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByGhiChuIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where ghiChu equals to
        defaultTaiSanDgcFiltering("ghiChu.equals=" + DEFAULT_GHI_CHU, "ghiChu.equals=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByGhiChuIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where ghiChu in
        defaultTaiSanDgcFiltering("ghiChu.in=" + DEFAULT_GHI_CHU + "," + UPDATED_GHI_CHU, "ghiChu.in=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByGhiChuIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where ghiChu is not null
        defaultTaiSanDgcFiltering("ghiChu.specified=true", "ghiChu.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByGhiChuContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where ghiChu contains
        defaultTaiSanDgcFiltering("ghiChu.contains=" + DEFAULT_GHI_CHU, "ghiChu.contains=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByGhiChuNotContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where ghiChu does not contain
        defaultTaiSanDgcFiltering("ghiChu.doesNotContain=" + UPDATED_GHI_CHU, "ghiChu.doesNotContain=" + DEFAULT_GHI_CHU);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByNgayThaoTacIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where ngayThaoTac equals to
        defaultTaiSanDgcFiltering("ngayThaoTac.equals=" + DEFAULT_NGAY_THAO_TAC, "ngayThaoTac.equals=" + UPDATED_NGAY_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByNgayThaoTacIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where ngayThaoTac in
        defaultTaiSanDgcFiltering(
            "ngayThaoTac.in=" + DEFAULT_NGAY_THAO_TAC + "," + UPDATED_NGAY_THAO_TAC,
            "ngayThaoTac.in=" + UPDATED_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByNgayThaoTacIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where ngayThaoTac is not null
        defaultTaiSanDgcFiltering("ngayThaoTac.specified=true", "ngayThaoTac.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByNgayThaoTacIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where ngayThaoTac is greater than or equal to
        defaultTaiSanDgcFiltering(
            "ngayThaoTac.greaterThanOrEqual=" + DEFAULT_NGAY_THAO_TAC,
            "ngayThaoTac.greaterThanOrEqual=" + UPDATED_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByNgayThaoTacIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where ngayThaoTac is less than or equal to
        defaultTaiSanDgcFiltering(
            "ngayThaoTac.lessThanOrEqual=" + DEFAULT_NGAY_THAO_TAC,
            "ngayThaoTac.lessThanOrEqual=" + SMALLER_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByNgayThaoTacIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where ngayThaoTac is less than
        defaultTaiSanDgcFiltering("ngayThaoTac.lessThan=" + UPDATED_NGAY_THAO_TAC, "ngayThaoTac.lessThan=" + DEFAULT_NGAY_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByNgayThaoTacIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where ngayThaoTac is greater than
        defaultTaiSanDgcFiltering("ngayThaoTac.greaterThan=" + SMALLER_NGAY_THAO_TAC, "ngayThaoTac.greaterThan=" + DEFAULT_NGAY_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByNguoiThaoTacIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where nguoiThaoTac equals to
        defaultTaiSanDgcFiltering("nguoiThaoTac.equals=" + DEFAULT_NGUOI_THAO_TAC, "nguoiThaoTac.equals=" + UPDATED_NGUOI_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByNguoiThaoTacIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where nguoiThaoTac in
        defaultTaiSanDgcFiltering(
            "nguoiThaoTac.in=" + DEFAULT_NGUOI_THAO_TAC + "," + UPDATED_NGUOI_THAO_TAC,
            "nguoiThaoTac.in=" + UPDATED_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByNguoiThaoTacIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where nguoiThaoTac is not null
        defaultTaiSanDgcFiltering("nguoiThaoTac.specified=true", "nguoiThaoTac.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByNguoiThaoTacIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where nguoiThaoTac is greater than or equal to
        defaultTaiSanDgcFiltering(
            "nguoiThaoTac.greaterThanOrEqual=" + DEFAULT_NGUOI_THAO_TAC,
            "nguoiThaoTac.greaterThanOrEqual=" + UPDATED_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByNguoiThaoTacIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where nguoiThaoTac is less than or equal to
        defaultTaiSanDgcFiltering(
            "nguoiThaoTac.lessThanOrEqual=" + DEFAULT_NGUOI_THAO_TAC,
            "nguoiThaoTac.lessThanOrEqual=" + SMALLER_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByNguoiThaoTacIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where nguoiThaoTac is less than
        defaultTaiSanDgcFiltering("nguoiThaoTac.lessThan=" + UPDATED_NGUOI_THAO_TAC, "nguoiThaoTac.lessThan=" + DEFAULT_NGUOI_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByNguoiThaoTacIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where nguoiThaoTac is greater than
        defaultTaiSanDgcFiltering(
            "nguoiThaoTac.greaterThan=" + SMALLER_NGUOI_THAO_TAC,
            "nguoiThaoTac.greaterThan=" + DEFAULT_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdDuongSuIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idDuongSu equals to
        defaultTaiSanDgcFiltering("idDuongSu.equals=" + DEFAULT_ID_DUONG_SU, "idDuongSu.equals=" + UPDATED_ID_DUONG_SU);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdDuongSuIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idDuongSu in
        defaultTaiSanDgcFiltering("idDuongSu.in=" + DEFAULT_ID_DUONG_SU + "," + UPDATED_ID_DUONG_SU, "idDuongSu.in=" + UPDATED_ID_DUONG_SU);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdDuongSuIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idDuongSu is not null
        defaultTaiSanDgcFiltering("idDuongSu.specified=true", "idDuongSu.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdDuongSuIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idDuongSu is greater than or equal to
        defaultTaiSanDgcFiltering(
            "idDuongSu.greaterThanOrEqual=" + DEFAULT_ID_DUONG_SU,
            "idDuongSu.greaterThanOrEqual=" + UPDATED_ID_DUONG_SU
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdDuongSuIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idDuongSu is less than or equal to
        defaultTaiSanDgcFiltering("idDuongSu.lessThanOrEqual=" + DEFAULT_ID_DUONG_SU, "idDuongSu.lessThanOrEqual=" + SMALLER_ID_DUONG_SU);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdDuongSuIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idDuongSu is less than
        defaultTaiSanDgcFiltering("idDuongSu.lessThan=" + UPDATED_ID_DUONG_SU, "idDuongSu.lessThan=" + DEFAULT_ID_DUONG_SU);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdDuongSuIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idDuongSu is greater than
        defaultTaiSanDgcFiltering("idDuongSu.greaterThan=" + SMALLER_ID_DUONG_SU, "idDuongSu.greaterThan=" + DEFAULT_ID_DUONG_SU);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdTsGocIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idTsGoc equals to
        defaultTaiSanDgcFiltering("idTsGoc.equals=" + DEFAULT_ID_TS_GOC, "idTsGoc.equals=" + UPDATED_ID_TS_GOC);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdTsGocIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idTsGoc in
        defaultTaiSanDgcFiltering("idTsGoc.in=" + DEFAULT_ID_TS_GOC + "," + UPDATED_ID_TS_GOC, "idTsGoc.in=" + UPDATED_ID_TS_GOC);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdTsGocIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idTsGoc is not null
        defaultTaiSanDgcFiltering("idTsGoc.specified=true", "idTsGoc.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdTsGocIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idTsGoc is greater than or equal to
        defaultTaiSanDgcFiltering("idTsGoc.greaterThanOrEqual=" + DEFAULT_ID_TS_GOC, "idTsGoc.greaterThanOrEqual=" + UPDATED_ID_TS_GOC);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdTsGocIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idTsGoc is less than or equal to
        defaultTaiSanDgcFiltering("idTsGoc.lessThanOrEqual=" + DEFAULT_ID_TS_GOC, "idTsGoc.lessThanOrEqual=" + SMALLER_ID_TS_GOC);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdTsGocIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idTsGoc is less than
        defaultTaiSanDgcFiltering("idTsGoc.lessThan=" + UPDATED_ID_TS_GOC, "idTsGoc.lessThan=" + DEFAULT_ID_TS_GOC);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdTsGocIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idTsGoc is greater than
        defaultTaiSanDgcFiltering("idTsGoc.greaterThan=" + SMALLER_ID_TS_GOC, "idTsGoc.greaterThan=" + DEFAULT_ID_TS_GOC);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByMaTaiSanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where maTaiSan equals to
        defaultTaiSanDgcFiltering("maTaiSan.equals=" + DEFAULT_MA_TAI_SAN, "maTaiSan.equals=" + UPDATED_MA_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByMaTaiSanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where maTaiSan in
        defaultTaiSanDgcFiltering("maTaiSan.in=" + DEFAULT_MA_TAI_SAN + "," + UPDATED_MA_TAI_SAN, "maTaiSan.in=" + UPDATED_MA_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByMaTaiSanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where maTaiSan is not null
        defaultTaiSanDgcFiltering("maTaiSan.specified=true", "maTaiSan.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByMaTaiSanContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where maTaiSan contains
        defaultTaiSanDgcFiltering("maTaiSan.contains=" + DEFAULT_MA_TAI_SAN, "maTaiSan.contains=" + UPDATED_MA_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByMaTaiSanNotContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where maTaiSan does not contain
        defaultTaiSanDgcFiltering("maTaiSan.doesNotContain=" + UPDATED_MA_TAI_SAN, "maTaiSan.doesNotContain=" + DEFAULT_MA_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdTinhTrangIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idTinhTrang equals to
        defaultTaiSanDgcFiltering("idTinhTrang.equals=" + DEFAULT_ID_TINH_TRANG, "idTinhTrang.equals=" + UPDATED_ID_TINH_TRANG);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdTinhTrangIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idTinhTrang in
        defaultTaiSanDgcFiltering(
            "idTinhTrang.in=" + DEFAULT_ID_TINH_TRANG + "," + UPDATED_ID_TINH_TRANG,
            "idTinhTrang.in=" + UPDATED_ID_TINH_TRANG
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdTinhTrangIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idTinhTrang is not null
        defaultTaiSanDgcFiltering("idTinhTrang.specified=true", "idTinhTrang.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdTinhTrangIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idTinhTrang is greater than or equal to
        defaultTaiSanDgcFiltering(
            "idTinhTrang.greaterThanOrEqual=" + DEFAULT_ID_TINH_TRANG,
            "idTinhTrang.greaterThanOrEqual=" + UPDATED_ID_TINH_TRANG
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdTinhTrangIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idTinhTrang is less than or equal to
        defaultTaiSanDgcFiltering(
            "idTinhTrang.lessThanOrEqual=" + DEFAULT_ID_TINH_TRANG,
            "idTinhTrang.lessThanOrEqual=" + SMALLER_ID_TINH_TRANG
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdTinhTrangIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idTinhTrang is less than
        defaultTaiSanDgcFiltering("idTinhTrang.lessThan=" + UPDATED_ID_TINH_TRANG, "idTinhTrang.lessThan=" + DEFAULT_ID_TINH_TRANG);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdTinhTrangIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idTinhTrang is greater than
        defaultTaiSanDgcFiltering("idTinhTrang.greaterThan=" + SMALLER_ID_TINH_TRANG, "idTinhTrang.greaterThan=" + DEFAULT_ID_TINH_TRANG);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdLoaiNganChanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idLoaiNganChan equals to
        defaultTaiSanDgcFiltering(
            "idLoaiNganChan.equals=" + DEFAULT_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.equals=" + UPDATED_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdLoaiNganChanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idLoaiNganChan in
        defaultTaiSanDgcFiltering(
            "idLoaiNganChan.in=" + DEFAULT_ID_LOAI_NGAN_CHAN + "," + UPDATED_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.in=" + UPDATED_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdLoaiNganChanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idLoaiNganChan is not null
        defaultTaiSanDgcFiltering("idLoaiNganChan.specified=true", "idLoaiNganChan.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdLoaiNganChanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idLoaiNganChan is greater than or equal to
        defaultTaiSanDgcFiltering(
            "idLoaiNganChan.greaterThanOrEqual=" + DEFAULT_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.greaterThanOrEqual=" + UPDATED_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdLoaiNganChanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idLoaiNganChan is less than or equal to
        defaultTaiSanDgcFiltering(
            "idLoaiNganChan.lessThanOrEqual=" + DEFAULT_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.lessThanOrEqual=" + SMALLER_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdLoaiNganChanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idLoaiNganChan is less than
        defaultTaiSanDgcFiltering(
            "idLoaiNganChan.lessThan=" + UPDATED_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.lessThan=" + DEFAULT_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdLoaiNganChanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idLoaiNganChan is greater than
        defaultTaiSanDgcFiltering(
            "idLoaiNganChan.greaterThan=" + SMALLER_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.greaterThan=" + DEFAULT_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByNgayBdNganChanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where ngayBdNganChan equals to
        defaultTaiSanDgcFiltering(
            "ngayBdNganChan.equals=" + DEFAULT_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.equals=" + UPDATED_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByNgayBdNganChanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where ngayBdNganChan in
        defaultTaiSanDgcFiltering(
            "ngayBdNganChan.in=" + DEFAULT_NGAY_BD_NGAN_CHAN + "," + UPDATED_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.in=" + UPDATED_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByNgayBdNganChanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where ngayBdNganChan is not null
        defaultTaiSanDgcFiltering("ngayBdNganChan.specified=true", "ngayBdNganChan.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByNgayBdNganChanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where ngayBdNganChan is greater than or equal to
        defaultTaiSanDgcFiltering(
            "ngayBdNganChan.greaterThanOrEqual=" + DEFAULT_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.greaterThanOrEqual=" + UPDATED_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByNgayBdNganChanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where ngayBdNganChan is less than or equal to
        defaultTaiSanDgcFiltering(
            "ngayBdNganChan.lessThanOrEqual=" + DEFAULT_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.lessThanOrEqual=" + SMALLER_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByNgayBdNganChanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where ngayBdNganChan is less than
        defaultTaiSanDgcFiltering(
            "ngayBdNganChan.lessThan=" + UPDATED_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.lessThan=" + DEFAULT_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByNgayBdNganChanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where ngayBdNganChan is greater than
        defaultTaiSanDgcFiltering(
            "ngayBdNganChan.greaterThan=" + SMALLER_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.greaterThan=" + DEFAULT_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByNgayKtNganChanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where ngayKtNganChan equals to
        defaultTaiSanDgcFiltering(
            "ngayKtNganChan.equals=" + DEFAULT_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.equals=" + UPDATED_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByNgayKtNganChanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where ngayKtNganChan in
        defaultTaiSanDgcFiltering(
            "ngayKtNganChan.in=" + DEFAULT_NGAY_KT_NGAN_CHAN + "," + UPDATED_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.in=" + UPDATED_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByNgayKtNganChanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where ngayKtNganChan is not null
        defaultTaiSanDgcFiltering("ngayKtNganChan.specified=true", "ngayKtNganChan.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByNgayKtNganChanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where ngayKtNganChan is greater than or equal to
        defaultTaiSanDgcFiltering(
            "ngayKtNganChan.greaterThanOrEqual=" + DEFAULT_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.greaterThanOrEqual=" + UPDATED_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByNgayKtNganChanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where ngayKtNganChan is less than or equal to
        defaultTaiSanDgcFiltering(
            "ngayKtNganChan.lessThanOrEqual=" + DEFAULT_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.lessThanOrEqual=" + SMALLER_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByNgayKtNganChanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where ngayKtNganChan is less than
        defaultTaiSanDgcFiltering(
            "ngayKtNganChan.lessThan=" + UPDATED_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.lessThan=" + DEFAULT_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByNgayKtNganChanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where ngayKtNganChan is greater than
        defaultTaiSanDgcFiltering(
            "ngayKtNganChan.greaterThan=" + SMALLER_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.greaterThan=" + DEFAULT_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdMasterIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idMaster equals to
        defaultTaiSanDgcFiltering("idMaster.equals=" + DEFAULT_ID_MASTER, "idMaster.equals=" + UPDATED_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdMasterIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idMaster in
        defaultTaiSanDgcFiltering("idMaster.in=" + DEFAULT_ID_MASTER + "," + UPDATED_ID_MASTER, "idMaster.in=" + UPDATED_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdMasterIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idMaster is not null
        defaultTaiSanDgcFiltering("idMaster.specified=true", "idMaster.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdMasterIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idMaster is greater than or equal to
        defaultTaiSanDgcFiltering("idMaster.greaterThanOrEqual=" + DEFAULT_ID_MASTER, "idMaster.greaterThanOrEqual=" + UPDATED_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdMasterIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idMaster is less than or equal to
        defaultTaiSanDgcFiltering("idMaster.lessThanOrEqual=" + DEFAULT_ID_MASTER, "idMaster.lessThanOrEqual=" + SMALLER_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdMasterIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idMaster is less than
        defaultTaiSanDgcFiltering("idMaster.lessThan=" + UPDATED_ID_MASTER, "idMaster.lessThan=" + DEFAULT_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdMasterIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idMaster is greater than
        defaultTaiSanDgcFiltering("idMaster.greaterThan=" + SMALLER_ID_MASTER, "idMaster.greaterThan=" + DEFAULT_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByStrSearchIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where strSearch equals to
        defaultTaiSanDgcFiltering("strSearch.equals=" + DEFAULT_STR_SEARCH, "strSearch.equals=" + UPDATED_STR_SEARCH);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByStrSearchIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where strSearch in
        defaultTaiSanDgcFiltering("strSearch.in=" + DEFAULT_STR_SEARCH + "," + UPDATED_STR_SEARCH, "strSearch.in=" + UPDATED_STR_SEARCH);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByStrSearchIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where strSearch is not null
        defaultTaiSanDgcFiltering("strSearch.specified=true", "strSearch.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByStrSearchContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where strSearch contains
        defaultTaiSanDgcFiltering("strSearch.contains=" + DEFAULT_STR_SEARCH, "strSearch.contains=" + UPDATED_STR_SEARCH);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByStrSearchNotContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where strSearch does not contain
        defaultTaiSanDgcFiltering("strSearch.doesNotContain=" + UPDATED_STR_SEARCH, "strSearch.doesNotContain=" + DEFAULT_STR_SEARCH);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdDonViIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idDonVi equals to
        defaultTaiSanDgcFiltering("idDonVi.equals=" + DEFAULT_ID_DON_VI, "idDonVi.equals=" + UPDATED_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdDonViIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idDonVi in
        defaultTaiSanDgcFiltering("idDonVi.in=" + DEFAULT_ID_DON_VI + "," + UPDATED_ID_DON_VI, "idDonVi.in=" + UPDATED_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdDonViIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idDonVi is not null
        defaultTaiSanDgcFiltering("idDonVi.specified=true", "idDonVi.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdDonViIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idDonVi is greater than or equal to
        defaultTaiSanDgcFiltering("idDonVi.greaterThanOrEqual=" + DEFAULT_ID_DON_VI, "idDonVi.greaterThanOrEqual=" + UPDATED_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdDonViIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idDonVi is less than or equal to
        defaultTaiSanDgcFiltering("idDonVi.lessThanOrEqual=" + DEFAULT_ID_DON_VI, "idDonVi.lessThanOrEqual=" + SMALLER_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdDonViIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idDonVi is less than
        defaultTaiSanDgcFiltering("idDonVi.lessThan=" + UPDATED_ID_DON_VI, "idDonVi.lessThan=" + DEFAULT_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByIdDonViIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where idDonVi is greater than
        defaultTaiSanDgcFiltering("idDonVi.greaterThan=" + SMALLER_ID_DON_VI, "idDonVi.greaterThan=" + DEFAULT_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsBySoHsCvIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where soHsCv equals to
        defaultTaiSanDgcFiltering("soHsCv.equals=" + DEFAULT_SO_HS_CV, "soHsCv.equals=" + UPDATED_SO_HS_CV);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsBySoHsCvIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where soHsCv in
        defaultTaiSanDgcFiltering("soHsCv.in=" + DEFAULT_SO_HS_CV + "," + UPDATED_SO_HS_CV, "soHsCv.in=" + UPDATED_SO_HS_CV);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsBySoHsCvIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where soHsCv is not null
        defaultTaiSanDgcFiltering("soHsCv.specified=true", "soHsCv.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsBySoHsCvIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where soHsCv is greater than or equal to
        defaultTaiSanDgcFiltering("soHsCv.greaterThanOrEqual=" + DEFAULT_SO_HS_CV, "soHsCv.greaterThanOrEqual=" + UPDATED_SO_HS_CV);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsBySoHsCvIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where soHsCv is less than or equal to
        defaultTaiSanDgcFiltering("soHsCv.lessThanOrEqual=" + DEFAULT_SO_HS_CV, "soHsCv.lessThanOrEqual=" + SMALLER_SO_HS_CV);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsBySoHsCvIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where soHsCv is less than
        defaultTaiSanDgcFiltering("soHsCv.lessThan=" + UPDATED_SO_HS_CV, "soHsCv.lessThan=" + DEFAULT_SO_HS_CV);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsBySoHsCvIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where soHsCv is greater than
        defaultTaiSanDgcFiltering("soHsCv.greaterThan=" + SMALLER_SO_HS_CV, "soHsCv.greaterThan=" + DEFAULT_SO_HS_CV);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsBySoCcIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where soCc equals to
        defaultTaiSanDgcFiltering("soCc.equals=" + DEFAULT_SO_CC, "soCc.equals=" + UPDATED_SO_CC);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsBySoCcIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where soCc in
        defaultTaiSanDgcFiltering("soCc.in=" + DEFAULT_SO_CC + "," + UPDATED_SO_CC, "soCc.in=" + UPDATED_SO_CC);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsBySoCcIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where soCc is not null
        defaultTaiSanDgcFiltering("soCc.specified=true", "soCc.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsBySoCcIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where soCc is greater than or equal to
        defaultTaiSanDgcFiltering("soCc.greaterThanOrEqual=" + DEFAULT_SO_CC, "soCc.greaterThanOrEqual=" + UPDATED_SO_CC);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsBySoCcIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where soCc is less than or equal to
        defaultTaiSanDgcFiltering("soCc.lessThanOrEqual=" + DEFAULT_SO_CC, "soCc.lessThanOrEqual=" + SMALLER_SO_CC);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsBySoCcIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where soCc is less than
        defaultTaiSanDgcFiltering("soCc.lessThan=" + UPDATED_SO_CC, "soCc.lessThan=" + DEFAULT_SO_CC);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsBySoCcIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where soCc is greater than
        defaultTaiSanDgcFiltering("soCc.greaterThan=" + SMALLER_SO_CC, "soCc.greaterThan=" + DEFAULT_SO_CC);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsBySoVaoSoIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where soVaoSo equals to
        defaultTaiSanDgcFiltering("soVaoSo.equals=" + DEFAULT_SO_VAO_SO, "soVaoSo.equals=" + UPDATED_SO_VAO_SO);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsBySoVaoSoIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where soVaoSo in
        defaultTaiSanDgcFiltering("soVaoSo.in=" + DEFAULT_SO_VAO_SO + "," + UPDATED_SO_VAO_SO, "soVaoSo.in=" + UPDATED_SO_VAO_SO);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsBySoVaoSoIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where soVaoSo is not null
        defaultTaiSanDgcFiltering("soVaoSo.specified=true", "soVaoSo.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsBySoVaoSoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where soVaoSo is greater than or equal to
        defaultTaiSanDgcFiltering("soVaoSo.greaterThanOrEqual=" + DEFAULT_SO_VAO_SO, "soVaoSo.greaterThanOrEqual=" + UPDATED_SO_VAO_SO);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsBySoVaoSoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where soVaoSo is less than or equal to
        defaultTaiSanDgcFiltering("soVaoSo.lessThanOrEqual=" + DEFAULT_SO_VAO_SO, "soVaoSo.lessThanOrEqual=" + SMALLER_SO_VAO_SO);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsBySoVaoSoIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where soVaoSo is less than
        defaultTaiSanDgcFiltering("soVaoSo.lessThan=" + UPDATED_SO_VAO_SO, "soVaoSo.lessThan=" + DEFAULT_SO_VAO_SO);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsBySoVaoSoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where soVaoSo is greater than
        defaultTaiSanDgcFiltering("soVaoSo.greaterThan=" + SMALLER_SO_VAO_SO, "soVaoSo.greaterThan=" + DEFAULT_SO_VAO_SO);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByMoTaIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where moTa equals to
        defaultTaiSanDgcFiltering("moTa.equals=" + DEFAULT_MO_TA, "moTa.equals=" + UPDATED_MO_TA);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByMoTaIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where moTa in
        defaultTaiSanDgcFiltering("moTa.in=" + DEFAULT_MO_TA + "," + UPDATED_MO_TA, "moTa.in=" + UPDATED_MO_TA);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByMoTaIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where moTa is not null
        defaultTaiSanDgcFiltering("moTa.specified=true", "moTa.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByMoTaContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where moTa contains
        defaultTaiSanDgcFiltering("moTa.contains=" + DEFAULT_MO_TA, "moTa.contains=" + UPDATED_MO_TA);
    }

    @Test
    @Transactional
    void getAllTaiSanDgcsByMoTaNotContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        // Get all the taiSanDgcList where moTa does not contain
        defaultTaiSanDgcFiltering("moTa.doesNotContain=" + UPDATED_MO_TA, "moTa.doesNotContain=" + DEFAULT_MO_TA);
    }

    private void defaultTaiSanDgcFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultTaiSanDgcShouldBeFound(shouldBeFound);
        defaultTaiSanDgcShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultTaiSanDgcShouldBeFound(String filter) throws Exception {
        restTaiSanDgcMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taiSanDgc.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTaiSan").value(hasItem(DEFAULT_ID_TAI_SAN.intValue())))
            .andExpect(jsonPath("$.[*].tenTaiSan").value(hasItem(DEFAULT_TEN_TAI_SAN)))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())))
            .andExpect(jsonPath("$.[*].thongTinTs").value(hasItem(DEFAULT_THONG_TIN_TS)))
            .andExpect(jsonPath("$.[*].idLoaiTs").value(hasItem(DEFAULT_ID_LOAI_TS.intValue())))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU)))
            .andExpect(jsonPath("$.[*].ngayThaoTac").value(hasItem(DEFAULT_NGAY_THAO_TAC.toString())))
            .andExpect(jsonPath("$.[*].nguoiThaoTac").value(hasItem(DEFAULT_NGUOI_THAO_TAC.intValue())))
            .andExpect(jsonPath("$.[*].idDuongSu").value(hasItem(DEFAULT_ID_DUONG_SU.intValue())))
            .andExpect(jsonPath("$.[*].idTsGoc").value(hasItem(DEFAULT_ID_TS_GOC.intValue())))
            .andExpect(jsonPath("$.[*].maTaiSan").value(hasItem(DEFAULT_MA_TAI_SAN)))
            .andExpect(jsonPath("$.[*].idTinhTrang").value(hasItem(DEFAULT_ID_TINH_TRANG.intValue())))
            .andExpect(jsonPath("$.[*].idLoaiNganChan").value(hasItem(DEFAULT_ID_LOAI_NGAN_CHAN.intValue())))
            .andExpect(jsonPath("$.[*].ngayBdNganChan").value(hasItem(DEFAULT_NGAY_BD_NGAN_CHAN.toString())))
            .andExpect(jsonPath("$.[*].ngayKtNganChan").value(hasItem(DEFAULT_NGAY_KT_NGAN_CHAN.toString())))
            .andExpect(jsonPath("$.[*].idMaster").value(hasItem(DEFAULT_ID_MASTER.intValue())))
            .andExpect(jsonPath("$.[*].strSearch").value(hasItem(DEFAULT_STR_SEARCH)))
            .andExpect(jsonPath("$.[*].idDonVi").value(hasItem(DEFAULT_ID_DON_VI.intValue())))
            .andExpect(jsonPath("$.[*].soHsCv").value(hasItem(DEFAULT_SO_HS_CV.intValue())))
            .andExpect(jsonPath("$.[*].soCc").value(hasItem(DEFAULT_SO_CC.intValue())))
            .andExpect(jsonPath("$.[*].soVaoSo").value(hasItem(DEFAULT_SO_VAO_SO.intValue())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)));

        // Check, that the count call also returns 1
        restTaiSanDgcMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultTaiSanDgcShouldNotBeFound(String filter) throws Exception {
        restTaiSanDgcMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restTaiSanDgcMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingTaiSanDgc() throws Exception {
        // Get the taiSanDgc
        restTaiSanDgcMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTaiSanDgc() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the taiSanDgc
        TaiSanDgc updatedTaiSanDgc = taiSanDgcRepository.findById(taiSanDgc.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedTaiSanDgc are not directly saved in db
        em.detach(updatedTaiSanDgc);
        updatedTaiSanDgc
            .idTaiSan(UPDATED_ID_TAI_SAN)
            .tenTaiSan(UPDATED_TEN_TAI_SAN)
            .trangThai(UPDATED_TRANG_THAI)
            .thongTinTs(UPDATED_THONG_TIN_TS)
            .idLoaiTs(UPDATED_ID_LOAI_TS)
            .ghiChu(UPDATED_GHI_CHU)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDuongSu(UPDATED_ID_DUONG_SU)
            .idTsGoc(UPDATED_ID_TS_GOC)
            .maTaiSan(UPDATED_MA_TAI_SAN)
            .idTinhTrang(UPDATED_ID_TINH_TRANG)
            .idLoaiNganChan(UPDATED_ID_LOAI_NGAN_CHAN)
            .ngayBdNganChan(UPDATED_NGAY_BD_NGAN_CHAN)
            .ngayKtNganChan(UPDATED_NGAY_KT_NGAN_CHAN)
            .idMaster(UPDATED_ID_MASTER)
            .strSearch(UPDATED_STR_SEARCH)
            .idDonVi(UPDATED_ID_DON_VI)
            .soHsCv(UPDATED_SO_HS_CV)
            .soCc(UPDATED_SO_CC)
            .soVaoSo(UPDATED_SO_VAO_SO)
            .moTa(UPDATED_MO_TA);
        TaiSanDgcDTO taiSanDgcDTO = taiSanDgcMapper.toDto(updatedTaiSanDgc);

        restTaiSanDgcMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taiSanDgcDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(taiSanDgcDTO))
            )
            .andExpect(status().isOk());

        // Validate the TaiSanDgc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedTaiSanDgcToMatchAllProperties(updatedTaiSanDgc);
    }

    @Test
    @Transactional
    void putNonExistingTaiSanDgc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taiSanDgc.setId(longCount.incrementAndGet());

        // Create the TaiSanDgc
        TaiSanDgcDTO taiSanDgcDTO = taiSanDgcMapper.toDto(taiSanDgc);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaiSanDgcMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taiSanDgcDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(taiSanDgcDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaiSanDgc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTaiSanDgc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taiSanDgc.setId(longCount.incrementAndGet());

        // Create the TaiSanDgc
        TaiSanDgcDTO taiSanDgcDTO = taiSanDgcMapper.toDto(taiSanDgc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaiSanDgcMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(taiSanDgcDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaiSanDgc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTaiSanDgc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taiSanDgc.setId(longCount.incrementAndGet());

        // Create the TaiSanDgc
        TaiSanDgcDTO taiSanDgcDTO = taiSanDgcMapper.toDto(taiSanDgc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaiSanDgcMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(taiSanDgcDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaiSanDgc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTaiSanDgcWithPatch() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the taiSanDgc using partial update
        TaiSanDgc partialUpdatedTaiSanDgc = new TaiSanDgc();
        partialUpdatedTaiSanDgc.setId(taiSanDgc.getId());

        partialUpdatedTaiSanDgc
            .tenTaiSan(UPDATED_TEN_TAI_SAN)
            .thongTinTs(UPDATED_THONG_TIN_TS)
            .idLoaiTs(UPDATED_ID_LOAI_TS)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .idDuongSu(UPDATED_ID_DUONG_SU)
            .idTinhTrang(UPDATED_ID_TINH_TRANG)
            .idLoaiNganChan(UPDATED_ID_LOAI_NGAN_CHAN)
            .idMaster(UPDATED_ID_MASTER)
            .idDonVi(UPDATED_ID_DON_VI)
            .moTa(UPDATED_MO_TA);

        restTaiSanDgcMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaiSanDgc.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTaiSanDgc))
            )
            .andExpect(status().isOk());

        // Validate the TaiSanDgc in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTaiSanDgcUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedTaiSanDgc, taiSanDgc),
            getPersistedTaiSanDgc(taiSanDgc)
        );
    }

    @Test
    @Transactional
    void fullUpdateTaiSanDgcWithPatch() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the taiSanDgc using partial update
        TaiSanDgc partialUpdatedTaiSanDgc = new TaiSanDgc();
        partialUpdatedTaiSanDgc.setId(taiSanDgc.getId());

        partialUpdatedTaiSanDgc
            .idTaiSan(UPDATED_ID_TAI_SAN)
            .tenTaiSan(UPDATED_TEN_TAI_SAN)
            .trangThai(UPDATED_TRANG_THAI)
            .thongTinTs(UPDATED_THONG_TIN_TS)
            .idLoaiTs(UPDATED_ID_LOAI_TS)
            .ghiChu(UPDATED_GHI_CHU)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDuongSu(UPDATED_ID_DUONG_SU)
            .idTsGoc(UPDATED_ID_TS_GOC)
            .maTaiSan(UPDATED_MA_TAI_SAN)
            .idTinhTrang(UPDATED_ID_TINH_TRANG)
            .idLoaiNganChan(UPDATED_ID_LOAI_NGAN_CHAN)
            .ngayBdNganChan(UPDATED_NGAY_BD_NGAN_CHAN)
            .ngayKtNganChan(UPDATED_NGAY_KT_NGAN_CHAN)
            .idMaster(UPDATED_ID_MASTER)
            .strSearch(UPDATED_STR_SEARCH)
            .idDonVi(UPDATED_ID_DON_VI)
            .soHsCv(UPDATED_SO_HS_CV)
            .soCc(UPDATED_SO_CC)
            .soVaoSo(UPDATED_SO_VAO_SO)
            .moTa(UPDATED_MO_TA);

        restTaiSanDgcMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaiSanDgc.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTaiSanDgc))
            )
            .andExpect(status().isOk());

        // Validate the TaiSanDgc in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTaiSanDgcUpdatableFieldsEquals(partialUpdatedTaiSanDgc, getPersistedTaiSanDgc(partialUpdatedTaiSanDgc));
    }

    @Test
    @Transactional
    void patchNonExistingTaiSanDgc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taiSanDgc.setId(longCount.incrementAndGet());

        // Create the TaiSanDgc
        TaiSanDgcDTO taiSanDgcDTO = taiSanDgcMapper.toDto(taiSanDgc);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaiSanDgcMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, taiSanDgcDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(taiSanDgcDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaiSanDgc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTaiSanDgc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taiSanDgc.setId(longCount.incrementAndGet());

        // Create the TaiSanDgc
        TaiSanDgcDTO taiSanDgcDTO = taiSanDgcMapper.toDto(taiSanDgc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaiSanDgcMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(taiSanDgcDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaiSanDgc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTaiSanDgc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taiSanDgc.setId(longCount.incrementAndGet());

        // Create the TaiSanDgc
        TaiSanDgcDTO taiSanDgcDTO = taiSanDgcMapper.toDto(taiSanDgc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaiSanDgcMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(taiSanDgcDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaiSanDgc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTaiSanDgc() throws Exception {
        // Initialize the database
        insertedTaiSanDgc = taiSanDgcRepository.saveAndFlush(taiSanDgc);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the taiSanDgc
        restTaiSanDgcMockMvc
            .perform(delete(ENTITY_API_URL_ID, taiSanDgc.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return taiSanDgcRepository.count();
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

    protected TaiSanDgc getPersistedTaiSanDgc(TaiSanDgc taiSanDgc) {
        return taiSanDgcRepository.findById(taiSanDgc.getId()).orElseThrow();
    }

    protected void assertPersistedTaiSanDgcToMatchAllProperties(TaiSanDgc expectedTaiSanDgc) {
        assertTaiSanDgcAllPropertiesEquals(expectedTaiSanDgc, getPersistedTaiSanDgc(expectedTaiSanDgc));
    }

    protected void assertPersistedTaiSanDgcToMatchUpdatableProperties(TaiSanDgc expectedTaiSanDgc) {
        assertTaiSanDgcAllUpdatablePropertiesEquals(expectedTaiSanDgc, getPersistedTaiSanDgc(expectedTaiSanDgc));
    }
}
