package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DanhSachTaiSanAsserts.*;
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
import vn.vnpt.domain.DanhMucLoaiTaiSan;
import vn.vnpt.domain.DanhSachTaiSan;
import vn.vnpt.repository.DanhSachTaiSanRepository;
import vn.vnpt.service.dto.DanhSachTaiSanDTO;
import vn.vnpt.service.mapper.DanhSachTaiSanMapper;

/**
 * Integration tests for the {@link DanhSachTaiSanResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DanhSachTaiSanResourceIT {

    private static final Long DEFAULT_ID_TAI_SAN = 1L;
    private static final Long UPDATED_ID_TAI_SAN = 2L;
    private static final Long SMALLER_ID_TAI_SAN = 1L - 1L;

    private static final String DEFAULT_TEN_TAI_SAN = "AAAAAAAAAA";
    private static final String UPDATED_TEN_TAI_SAN = "BBBBBBBBBB";

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;
    private static final Long SMALLER_TRANG_THAI = 1L - 1L;

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

    private static final Long DEFAULT_LOAI_NGAN_CHAN = 1L;
    private static final Long UPDATED_LOAI_NGAN_CHAN = 2L;
    private static final Long SMALLER_LOAI_NGAN_CHAN = 1L - 1L;

    private static final String DEFAULT_MA_XA = "AAAAAAAAAA";
    private static final String UPDATED_MA_XA = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/danh-sach-tai-sans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DanhSachTaiSanRepository danhSachTaiSanRepository;

    @Autowired
    private DanhSachTaiSanMapper danhSachTaiSanMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDanhSachTaiSanMockMvc;

    private DanhSachTaiSan danhSachTaiSan;

    private DanhSachTaiSan insertedDanhSachTaiSan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhSachTaiSan createEntity(EntityManager em) {
        DanhSachTaiSan danhSachTaiSan = new DanhSachTaiSan()
            .idTaiSan(DEFAULT_ID_TAI_SAN)
            .tenTaiSan(DEFAULT_TEN_TAI_SAN)
            .trangThai(DEFAULT_TRANG_THAI)
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
            .moTa(DEFAULT_MO_TA)
            .loaiNganChan(DEFAULT_LOAI_NGAN_CHAN)
            .maXa(DEFAULT_MA_XA);
        return danhSachTaiSan;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhSachTaiSan createUpdatedEntity(EntityManager em) {
        DanhSachTaiSan danhSachTaiSan = new DanhSachTaiSan()
            .idTaiSan(UPDATED_ID_TAI_SAN)
            .tenTaiSan(UPDATED_TEN_TAI_SAN)
            .trangThai(UPDATED_TRANG_THAI)
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
            .moTa(UPDATED_MO_TA)
            .loaiNganChan(UPDATED_LOAI_NGAN_CHAN)
            .maXa(UPDATED_MA_XA);
        return danhSachTaiSan;
    }

    @BeforeEach
    public void initTest() {
        danhSachTaiSan = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedDanhSachTaiSan != null) {
            danhSachTaiSanRepository.delete(insertedDanhSachTaiSan);
            insertedDanhSachTaiSan = null;
        }
    }

    @Test
    @Transactional
    void createDanhSachTaiSan() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DanhSachTaiSan
        DanhSachTaiSanDTO danhSachTaiSanDTO = danhSachTaiSanMapper.toDto(danhSachTaiSan);
        var returnedDanhSachTaiSanDTO = om.readValue(
            restDanhSachTaiSanMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhSachTaiSanDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DanhSachTaiSanDTO.class
        );

        // Validate the DanhSachTaiSan in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDanhSachTaiSan = danhSachTaiSanMapper.toEntity(returnedDanhSachTaiSanDTO);
        assertDanhSachTaiSanUpdatableFieldsEquals(returnedDanhSachTaiSan, getPersistedDanhSachTaiSan(returnedDanhSachTaiSan));

        insertedDanhSachTaiSan = returnedDanhSachTaiSan;
    }

    @Test
    @Transactional
    void createDanhSachTaiSanWithExistingId() throws Exception {
        // Create the DanhSachTaiSan with an existing ID
        danhSachTaiSan.setId(1L);
        DanhSachTaiSanDTO danhSachTaiSanDTO = danhSachTaiSanMapper.toDto(danhSachTaiSan);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanhSachTaiSanMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhSachTaiSanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DanhSachTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSans() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList
        restDanhSachTaiSanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danhSachTaiSan.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTaiSan").value(hasItem(DEFAULT_ID_TAI_SAN.intValue())))
            .andExpect(jsonPath("$.[*].tenTaiSan").value(hasItem(DEFAULT_TEN_TAI_SAN)))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())))
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
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].loaiNganChan").value(hasItem(DEFAULT_LOAI_NGAN_CHAN.intValue())))
            .andExpect(jsonPath("$.[*].maXa").value(hasItem(DEFAULT_MA_XA)));
    }

    @Test
    @Transactional
    void getDanhSachTaiSan() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get the danhSachTaiSan
        restDanhSachTaiSanMockMvc
            .perform(get(ENTITY_API_URL_ID, danhSachTaiSan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(danhSachTaiSan.getId().intValue()))
            .andExpect(jsonPath("$.idTaiSan").value(DEFAULT_ID_TAI_SAN.intValue()))
            .andExpect(jsonPath("$.tenTaiSan").value(DEFAULT_TEN_TAI_SAN))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI.intValue()))
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
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA))
            .andExpect(jsonPath("$.loaiNganChan").value(DEFAULT_LOAI_NGAN_CHAN.intValue()))
            .andExpect(jsonPath("$.maXa").value(DEFAULT_MA_XA));
    }

    @Test
    @Transactional
    void getDanhSachTaiSansByIdFiltering() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        Long id = danhSachTaiSan.getId();

        defaultDanhSachTaiSanFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultDanhSachTaiSanFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultDanhSachTaiSanFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdTaiSanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idTaiSan equals to
        defaultDanhSachTaiSanFiltering("idTaiSan.equals=" + DEFAULT_ID_TAI_SAN, "idTaiSan.equals=" + UPDATED_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdTaiSanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idTaiSan in
        defaultDanhSachTaiSanFiltering("idTaiSan.in=" + DEFAULT_ID_TAI_SAN + "," + UPDATED_ID_TAI_SAN, "idTaiSan.in=" + UPDATED_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdTaiSanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idTaiSan is not null
        defaultDanhSachTaiSanFiltering("idTaiSan.specified=true", "idTaiSan.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdTaiSanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idTaiSan is greater than or equal to
        defaultDanhSachTaiSanFiltering(
            "idTaiSan.greaterThanOrEqual=" + DEFAULT_ID_TAI_SAN,
            "idTaiSan.greaterThanOrEqual=" + UPDATED_ID_TAI_SAN
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdTaiSanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idTaiSan is less than or equal to
        defaultDanhSachTaiSanFiltering("idTaiSan.lessThanOrEqual=" + DEFAULT_ID_TAI_SAN, "idTaiSan.lessThanOrEqual=" + SMALLER_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdTaiSanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idTaiSan is less than
        defaultDanhSachTaiSanFiltering("idTaiSan.lessThan=" + UPDATED_ID_TAI_SAN, "idTaiSan.lessThan=" + DEFAULT_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdTaiSanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idTaiSan is greater than
        defaultDanhSachTaiSanFiltering("idTaiSan.greaterThan=" + SMALLER_ID_TAI_SAN, "idTaiSan.greaterThan=" + DEFAULT_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByTenTaiSanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where tenTaiSan equals to
        defaultDanhSachTaiSanFiltering("tenTaiSan.equals=" + DEFAULT_TEN_TAI_SAN, "tenTaiSan.equals=" + UPDATED_TEN_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByTenTaiSanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where tenTaiSan in
        defaultDanhSachTaiSanFiltering(
            "tenTaiSan.in=" + DEFAULT_TEN_TAI_SAN + "," + UPDATED_TEN_TAI_SAN,
            "tenTaiSan.in=" + UPDATED_TEN_TAI_SAN
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByTenTaiSanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where tenTaiSan is not null
        defaultDanhSachTaiSanFiltering("tenTaiSan.specified=true", "tenTaiSan.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByTenTaiSanContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where tenTaiSan contains
        defaultDanhSachTaiSanFiltering("tenTaiSan.contains=" + DEFAULT_TEN_TAI_SAN, "tenTaiSan.contains=" + UPDATED_TEN_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByTenTaiSanNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where tenTaiSan does not contain
        defaultDanhSachTaiSanFiltering(
            "tenTaiSan.doesNotContain=" + UPDATED_TEN_TAI_SAN,
            "tenTaiSan.doesNotContain=" + DEFAULT_TEN_TAI_SAN
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByTrangThaiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where trangThai equals to
        defaultDanhSachTaiSanFiltering("trangThai.equals=" + DEFAULT_TRANG_THAI, "trangThai.equals=" + UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByTrangThaiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where trangThai in
        defaultDanhSachTaiSanFiltering(
            "trangThai.in=" + DEFAULT_TRANG_THAI + "," + UPDATED_TRANG_THAI,
            "trangThai.in=" + UPDATED_TRANG_THAI
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByTrangThaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where trangThai is not null
        defaultDanhSachTaiSanFiltering("trangThai.specified=true", "trangThai.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByTrangThaiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where trangThai is greater than or equal to
        defaultDanhSachTaiSanFiltering(
            "trangThai.greaterThanOrEqual=" + DEFAULT_TRANG_THAI,
            "trangThai.greaterThanOrEqual=" + UPDATED_TRANG_THAI
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByTrangThaiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where trangThai is less than or equal to
        defaultDanhSachTaiSanFiltering(
            "trangThai.lessThanOrEqual=" + DEFAULT_TRANG_THAI,
            "trangThai.lessThanOrEqual=" + SMALLER_TRANG_THAI
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByTrangThaiIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where trangThai is less than
        defaultDanhSachTaiSanFiltering("trangThai.lessThan=" + UPDATED_TRANG_THAI, "trangThai.lessThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByTrangThaiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where trangThai is greater than
        defaultDanhSachTaiSanFiltering("trangThai.greaterThan=" + SMALLER_TRANG_THAI, "trangThai.greaterThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByGhiChuIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where ghiChu equals to
        defaultDanhSachTaiSanFiltering("ghiChu.equals=" + DEFAULT_GHI_CHU, "ghiChu.equals=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByGhiChuIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where ghiChu in
        defaultDanhSachTaiSanFiltering("ghiChu.in=" + DEFAULT_GHI_CHU + "," + UPDATED_GHI_CHU, "ghiChu.in=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByGhiChuIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where ghiChu is not null
        defaultDanhSachTaiSanFiltering("ghiChu.specified=true", "ghiChu.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByGhiChuContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where ghiChu contains
        defaultDanhSachTaiSanFiltering("ghiChu.contains=" + DEFAULT_GHI_CHU, "ghiChu.contains=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByGhiChuNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where ghiChu does not contain
        defaultDanhSachTaiSanFiltering("ghiChu.doesNotContain=" + UPDATED_GHI_CHU, "ghiChu.doesNotContain=" + DEFAULT_GHI_CHU);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByNgayThaoTacIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where ngayThaoTac equals to
        defaultDanhSachTaiSanFiltering("ngayThaoTac.equals=" + DEFAULT_NGAY_THAO_TAC, "ngayThaoTac.equals=" + UPDATED_NGAY_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByNgayThaoTacIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where ngayThaoTac in
        defaultDanhSachTaiSanFiltering(
            "ngayThaoTac.in=" + DEFAULT_NGAY_THAO_TAC + "," + UPDATED_NGAY_THAO_TAC,
            "ngayThaoTac.in=" + UPDATED_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByNgayThaoTacIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where ngayThaoTac is not null
        defaultDanhSachTaiSanFiltering("ngayThaoTac.specified=true", "ngayThaoTac.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByNgayThaoTacIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where ngayThaoTac is greater than or equal to
        defaultDanhSachTaiSanFiltering(
            "ngayThaoTac.greaterThanOrEqual=" + DEFAULT_NGAY_THAO_TAC,
            "ngayThaoTac.greaterThanOrEqual=" + UPDATED_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByNgayThaoTacIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where ngayThaoTac is less than or equal to
        defaultDanhSachTaiSanFiltering(
            "ngayThaoTac.lessThanOrEqual=" + DEFAULT_NGAY_THAO_TAC,
            "ngayThaoTac.lessThanOrEqual=" + SMALLER_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByNgayThaoTacIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where ngayThaoTac is less than
        defaultDanhSachTaiSanFiltering("ngayThaoTac.lessThan=" + UPDATED_NGAY_THAO_TAC, "ngayThaoTac.lessThan=" + DEFAULT_NGAY_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByNgayThaoTacIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where ngayThaoTac is greater than
        defaultDanhSachTaiSanFiltering(
            "ngayThaoTac.greaterThan=" + SMALLER_NGAY_THAO_TAC,
            "ngayThaoTac.greaterThan=" + DEFAULT_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByNguoiThaoTacIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where nguoiThaoTac equals to
        defaultDanhSachTaiSanFiltering("nguoiThaoTac.equals=" + DEFAULT_NGUOI_THAO_TAC, "nguoiThaoTac.equals=" + UPDATED_NGUOI_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByNguoiThaoTacIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where nguoiThaoTac in
        defaultDanhSachTaiSanFiltering(
            "nguoiThaoTac.in=" + DEFAULT_NGUOI_THAO_TAC + "," + UPDATED_NGUOI_THAO_TAC,
            "nguoiThaoTac.in=" + UPDATED_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByNguoiThaoTacIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where nguoiThaoTac is not null
        defaultDanhSachTaiSanFiltering("nguoiThaoTac.specified=true", "nguoiThaoTac.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByNguoiThaoTacIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where nguoiThaoTac is greater than or equal to
        defaultDanhSachTaiSanFiltering(
            "nguoiThaoTac.greaterThanOrEqual=" + DEFAULT_NGUOI_THAO_TAC,
            "nguoiThaoTac.greaterThanOrEqual=" + UPDATED_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByNguoiThaoTacIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where nguoiThaoTac is less than or equal to
        defaultDanhSachTaiSanFiltering(
            "nguoiThaoTac.lessThanOrEqual=" + DEFAULT_NGUOI_THAO_TAC,
            "nguoiThaoTac.lessThanOrEqual=" + SMALLER_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByNguoiThaoTacIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where nguoiThaoTac is less than
        defaultDanhSachTaiSanFiltering(
            "nguoiThaoTac.lessThan=" + UPDATED_NGUOI_THAO_TAC,
            "nguoiThaoTac.lessThan=" + DEFAULT_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByNguoiThaoTacIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where nguoiThaoTac is greater than
        defaultDanhSachTaiSanFiltering(
            "nguoiThaoTac.greaterThan=" + SMALLER_NGUOI_THAO_TAC,
            "nguoiThaoTac.greaterThan=" + DEFAULT_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdDuongSuIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idDuongSu equals to
        defaultDanhSachTaiSanFiltering("idDuongSu.equals=" + DEFAULT_ID_DUONG_SU, "idDuongSu.equals=" + UPDATED_ID_DUONG_SU);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdDuongSuIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idDuongSu in
        defaultDanhSachTaiSanFiltering(
            "idDuongSu.in=" + DEFAULT_ID_DUONG_SU + "," + UPDATED_ID_DUONG_SU,
            "idDuongSu.in=" + UPDATED_ID_DUONG_SU
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdDuongSuIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idDuongSu is not null
        defaultDanhSachTaiSanFiltering("idDuongSu.specified=true", "idDuongSu.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdDuongSuIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idDuongSu is greater than or equal to
        defaultDanhSachTaiSanFiltering(
            "idDuongSu.greaterThanOrEqual=" + DEFAULT_ID_DUONG_SU,
            "idDuongSu.greaterThanOrEqual=" + UPDATED_ID_DUONG_SU
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdDuongSuIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idDuongSu is less than or equal to
        defaultDanhSachTaiSanFiltering(
            "idDuongSu.lessThanOrEqual=" + DEFAULT_ID_DUONG_SU,
            "idDuongSu.lessThanOrEqual=" + SMALLER_ID_DUONG_SU
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdDuongSuIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idDuongSu is less than
        defaultDanhSachTaiSanFiltering("idDuongSu.lessThan=" + UPDATED_ID_DUONG_SU, "idDuongSu.lessThan=" + DEFAULT_ID_DUONG_SU);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdDuongSuIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idDuongSu is greater than
        defaultDanhSachTaiSanFiltering("idDuongSu.greaterThan=" + SMALLER_ID_DUONG_SU, "idDuongSu.greaterThan=" + DEFAULT_ID_DUONG_SU);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdTsGocIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idTsGoc equals to
        defaultDanhSachTaiSanFiltering("idTsGoc.equals=" + DEFAULT_ID_TS_GOC, "idTsGoc.equals=" + UPDATED_ID_TS_GOC);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdTsGocIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idTsGoc in
        defaultDanhSachTaiSanFiltering("idTsGoc.in=" + DEFAULT_ID_TS_GOC + "," + UPDATED_ID_TS_GOC, "idTsGoc.in=" + UPDATED_ID_TS_GOC);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdTsGocIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idTsGoc is not null
        defaultDanhSachTaiSanFiltering("idTsGoc.specified=true", "idTsGoc.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdTsGocIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idTsGoc is greater than or equal to
        defaultDanhSachTaiSanFiltering(
            "idTsGoc.greaterThanOrEqual=" + DEFAULT_ID_TS_GOC,
            "idTsGoc.greaterThanOrEqual=" + UPDATED_ID_TS_GOC
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdTsGocIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idTsGoc is less than or equal to
        defaultDanhSachTaiSanFiltering("idTsGoc.lessThanOrEqual=" + DEFAULT_ID_TS_GOC, "idTsGoc.lessThanOrEqual=" + SMALLER_ID_TS_GOC);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdTsGocIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idTsGoc is less than
        defaultDanhSachTaiSanFiltering("idTsGoc.lessThan=" + UPDATED_ID_TS_GOC, "idTsGoc.lessThan=" + DEFAULT_ID_TS_GOC);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdTsGocIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idTsGoc is greater than
        defaultDanhSachTaiSanFiltering("idTsGoc.greaterThan=" + SMALLER_ID_TS_GOC, "idTsGoc.greaterThan=" + DEFAULT_ID_TS_GOC);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByMaTaiSanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where maTaiSan equals to
        defaultDanhSachTaiSanFiltering("maTaiSan.equals=" + DEFAULT_MA_TAI_SAN, "maTaiSan.equals=" + UPDATED_MA_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByMaTaiSanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where maTaiSan in
        defaultDanhSachTaiSanFiltering("maTaiSan.in=" + DEFAULT_MA_TAI_SAN + "," + UPDATED_MA_TAI_SAN, "maTaiSan.in=" + UPDATED_MA_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByMaTaiSanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where maTaiSan is not null
        defaultDanhSachTaiSanFiltering("maTaiSan.specified=true", "maTaiSan.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByMaTaiSanContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where maTaiSan contains
        defaultDanhSachTaiSanFiltering("maTaiSan.contains=" + DEFAULT_MA_TAI_SAN, "maTaiSan.contains=" + UPDATED_MA_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByMaTaiSanNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where maTaiSan does not contain
        defaultDanhSachTaiSanFiltering("maTaiSan.doesNotContain=" + UPDATED_MA_TAI_SAN, "maTaiSan.doesNotContain=" + DEFAULT_MA_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdTinhTrangIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idTinhTrang equals to
        defaultDanhSachTaiSanFiltering("idTinhTrang.equals=" + DEFAULT_ID_TINH_TRANG, "idTinhTrang.equals=" + UPDATED_ID_TINH_TRANG);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdTinhTrangIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idTinhTrang in
        defaultDanhSachTaiSanFiltering(
            "idTinhTrang.in=" + DEFAULT_ID_TINH_TRANG + "," + UPDATED_ID_TINH_TRANG,
            "idTinhTrang.in=" + UPDATED_ID_TINH_TRANG
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdTinhTrangIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idTinhTrang is not null
        defaultDanhSachTaiSanFiltering("idTinhTrang.specified=true", "idTinhTrang.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdTinhTrangIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idTinhTrang is greater than or equal to
        defaultDanhSachTaiSanFiltering(
            "idTinhTrang.greaterThanOrEqual=" + DEFAULT_ID_TINH_TRANG,
            "idTinhTrang.greaterThanOrEqual=" + UPDATED_ID_TINH_TRANG
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdTinhTrangIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idTinhTrang is less than or equal to
        defaultDanhSachTaiSanFiltering(
            "idTinhTrang.lessThanOrEqual=" + DEFAULT_ID_TINH_TRANG,
            "idTinhTrang.lessThanOrEqual=" + SMALLER_ID_TINH_TRANG
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdTinhTrangIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idTinhTrang is less than
        defaultDanhSachTaiSanFiltering("idTinhTrang.lessThan=" + UPDATED_ID_TINH_TRANG, "idTinhTrang.lessThan=" + DEFAULT_ID_TINH_TRANG);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdTinhTrangIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idTinhTrang is greater than
        defaultDanhSachTaiSanFiltering(
            "idTinhTrang.greaterThan=" + SMALLER_ID_TINH_TRANG,
            "idTinhTrang.greaterThan=" + DEFAULT_ID_TINH_TRANG
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdLoaiNganChanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idLoaiNganChan equals to
        defaultDanhSachTaiSanFiltering(
            "idLoaiNganChan.equals=" + DEFAULT_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.equals=" + UPDATED_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdLoaiNganChanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idLoaiNganChan in
        defaultDanhSachTaiSanFiltering(
            "idLoaiNganChan.in=" + DEFAULT_ID_LOAI_NGAN_CHAN + "," + UPDATED_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.in=" + UPDATED_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdLoaiNganChanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idLoaiNganChan is not null
        defaultDanhSachTaiSanFiltering("idLoaiNganChan.specified=true", "idLoaiNganChan.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdLoaiNganChanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idLoaiNganChan is greater than or equal to
        defaultDanhSachTaiSanFiltering(
            "idLoaiNganChan.greaterThanOrEqual=" + DEFAULT_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.greaterThanOrEqual=" + UPDATED_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdLoaiNganChanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idLoaiNganChan is less than or equal to
        defaultDanhSachTaiSanFiltering(
            "idLoaiNganChan.lessThanOrEqual=" + DEFAULT_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.lessThanOrEqual=" + SMALLER_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdLoaiNganChanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idLoaiNganChan is less than
        defaultDanhSachTaiSanFiltering(
            "idLoaiNganChan.lessThan=" + UPDATED_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.lessThan=" + DEFAULT_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdLoaiNganChanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idLoaiNganChan is greater than
        defaultDanhSachTaiSanFiltering(
            "idLoaiNganChan.greaterThan=" + SMALLER_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.greaterThan=" + DEFAULT_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByNgayBdNganChanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where ngayBdNganChan equals to
        defaultDanhSachTaiSanFiltering(
            "ngayBdNganChan.equals=" + DEFAULT_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.equals=" + UPDATED_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByNgayBdNganChanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where ngayBdNganChan in
        defaultDanhSachTaiSanFiltering(
            "ngayBdNganChan.in=" + DEFAULT_NGAY_BD_NGAN_CHAN + "," + UPDATED_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.in=" + UPDATED_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByNgayBdNganChanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where ngayBdNganChan is not null
        defaultDanhSachTaiSanFiltering("ngayBdNganChan.specified=true", "ngayBdNganChan.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByNgayBdNganChanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where ngayBdNganChan is greater than or equal to
        defaultDanhSachTaiSanFiltering(
            "ngayBdNganChan.greaterThanOrEqual=" + DEFAULT_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.greaterThanOrEqual=" + UPDATED_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByNgayBdNganChanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where ngayBdNganChan is less than or equal to
        defaultDanhSachTaiSanFiltering(
            "ngayBdNganChan.lessThanOrEqual=" + DEFAULT_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.lessThanOrEqual=" + SMALLER_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByNgayBdNganChanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where ngayBdNganChan is less than
        defaultDanhSachTaiSanFiltering(
            "ngayBdNganChan.lessThan=" + UPDATED_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.lessThan=" + DEFAULT_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByNgayBdNganChanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where ngayBdNganChan is greater than
        defaultDanhSachTaiSanFiltering(
            "ngayBdNganChan.greaterThan=" + SMALLER_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.greaterThan=" + DEFAULT_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByNgayKtNganChanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where ngayKtNganChan equals to
        defaultDanhSachTaiSanFiltering(
            "ngayKtNganChan.equals=" + DEFAULT_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.equals=" + UPDATED_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByNgayKtNganChanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where ngayKtNganChan in
        defaultDanhSachTaiSanFiltering(
            "ngayKtNganChan.in=" + DEFAULT_NGAY_KT_NGAN_CHAN + "," + UPDATED_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.in=" + UPDATED_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByNgayKtNganChanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where ngayKtNganChan is not null
        defaultDanhSachTaiSanFiltering("ngayKtNganChan.specified=true", "ngayKtNganChan.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByNgayKtNganChanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where ngayKtNganChan is greater than or equal to
        defaultDanhSachTaiSanFiltering(
            "ngayKtNganChan.greaterThanOrEqual=" + DEFAULT_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.greaterThanOrEqual=" + UPDATED_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByNgayKtNganChanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where ngayKtNganChan is less than or equal to
        defaultDanhSachTaiSanFiltering(
            "ngayKtNganChan.lessThanOrEqual=" + DEFAULT_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.lessThanOrEqual=" + SMALLER_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByNgayKtNganChanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where ngayKtNganChan is less than
        defaultDanhSachTaiSanFiltering(
            "ngayKtNganChan.lessThan=" + UPDATED_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.lessThan=" + DEFAULT_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByNgayKtNganChanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where ngayKtNganChan is greater than
        defaultDanhSachTaiSanFiltering(
            "ngayKtNganChan.greaterThan=" + SMALLER_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.greaterThan=" + DEFAULT_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdMasterIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idMaster equals to
        defaultDanhSachTaiSanFiltering("idMaster.equals=" + DEFAULT_ID_MASTER, "idMaster.equals=" + UPDATED_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdMasterIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idMaster in
        defaultDanhSachTaiSanFiltering("idMaster.in=" + DEFAULT_ID_MASTER + "," + UPDATED_ID_MASTER, "idMaster.in=" + UPDATED_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdMasterIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idMaster is not null
        defaultDanhSachTaiSanFiltering("idMaster.specified=true", "idMaster.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdMasterIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idMaster is greater than or equal to
        defaultDanhSachTaiSanFiltering(
            "idMaster.greaterThanOrEqual=" + DEFAULT_ID_MASTER,
            "idMaster.greaterThanOrEqual=" + UPDATED_ID_MASTER
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdMasterIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idMaster is less than or equal to
        defaultDanhSachTaiSanFiltering("idMaster.lessThanOrEqual=" + DEFAULT_ID_MASTER, "idMaster.lessThanOrEqual=" + SMALLER_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdMasterIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idMaster is less than
        defaultDanhSachTaiSanFiltering("idMaster.lessThan=" + UPDATED_ID_MASTER, "idMaster.lessThan=" + DEFAULT_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdMasterIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idMaster is greater than
        defaultDanhSachTaiSanFiltering("idMaster.greaterThan=" + SMALLER_ID_MASTER, "idMaster.greaterThan=" + DEFAULT_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByStrSearchIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where strSearch equals to
        defaultDanhSachTaiSanFiltering("strSearch.equals=" + DEFAULT_STR_SEARCH, "strSearch.equals=" + UPDATED_STR_SEARCH);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByStrSearchIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where strSearch in
        defaultDanhSachTaiSanFiltering(
            "strSearch.in=" + DEFAULT_STR_SEARCH + "," + UPDATED_STR_SEARCH,
            "strSearch.in=" + UPDATED_STR_SEARCH
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByStrSearchIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where strSearch is not null
        defaultDanhSachTaiSanFiltering("strSearch.specified=true", "strSearch.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByStrSearchContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where strSearch contains
        defaultDanhSachTaiSanFiltering("strSearch.contains=" + DEFAULT_STR_SEARCH, "strSearch.contains=" + UPDATED_STR_SEARCH);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByStrSearchNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where strSearch does not contain
        defaultDanhSachTaiSanFiltering("strSearch.doesNotContain=" + UPDATED_STR_SEARCH, "strSearch.doesNotContain=" + DEFAULT_STR_SEARCH);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdDonViIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idDonVi equals to
        defaultDanhSachTaiSanFiltering("idDonVi.equals=" + DEFAULT_ID_DON_VI, "idDonVi.equals=" + UPDATED_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdDonViIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idDonVi in
        defaultDanhSachTaiSanFiltering("idDonVi.in=" + DEFAULT_ID_DON_VI + "," + UPDATED_ID_DON_VI, "idDonVi.in=" + UPDATED_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdDonViIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idDonVi is not null
        defaultDanhSachTaiSanFiltering("idDonVi.specified=true", "idDonVi.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdDonViIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idDonVi is greater than or equal to
        defaultDanhSachTaiSanFiltering(
            "idDonVi.greaterThanOrEqual=" + DEFAULT_ID_DON_VI,
            "idDonVi.greaterThanOrEqual=" + UPDATED_ID_DON_VI
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdDonViIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idDonVi is less than or equal to
        defaultDanhSachTaiSanFiltering("idDonVi.lessThanOrEqual=" + DEFAULT_ID_DON_VI, "idDonVi.lessThanOrEqual=" + SMALLER_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdDonViIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idDonVi is less than
        defaultDanhSachTaiSanFiltering("idDonVi.lessThan=" + UPDATED_ID_DON_VI, "idDonVi.lessThan=" + DEFAULT_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdDonViIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where idDonVi is greater than
        defaultDanhSachTaiSanFiltering("idDonVi.greaterThan=" + SMALLER_ID_DON_VI, "idDonVi.greaterThan=" + DEFAULT_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansBySoHsCvIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where soHsCv equals to
        defaultDanhSachTaiSanFiltering("soHsCv.equals=" + DEFAULT_SO_HS_CV, "soHsCv.equals=" + UPDATED_SO_HS_CV);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansBySoHsCvIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where soHsCv in
        defaultDanhSachTaiSanFiltering("soHsCv.in=" + DEFAULT_SO_HS_CV + "," + UPDATED_SO_HS_CV, "soHsCv.in=" + UPDATED_SO_HS_CV);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansBySoHsCvIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where soHsCv is not null
        defaultDanhSachTaiSanFiltering("soHsCv.specified=true", "soHsCv.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansBySoHsCvIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where soHsCv is greater than or equal to
        defaultDanhSachTaiSanFiltering("soHsCv.greaterThanOrEqual=" + DEFAULT_SO_HS_CV, "soHsCv.greaterThanOrEqual=" + UPDATED_SO_HS_CV);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansBySoHsCvIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where soHsCv is less than or equal to
        defaultDanhSachTaiSanFiltering("soHsCv.lessThanOrEqual=" + DEFAULT_SO_HS_CV, "soHsCv.lessThanOrEqual=" + SMALLER_SO_HS_CV);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansBySoHsCvIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where soHsCv is less than
        defaultDanhSachTaiSanFiltering("soHsCv.lessThan=" + UPDATED_SO_HS_CV, "soHsCv.lessThan=" + DEFAULT_SO_HS_CV);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansBySoHsCvIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where soHsCv is greater than
        defaultDanhSachTaiSanFiltering("soHsCv.greaterThan=" + SMALLER_SO_HS_CV, "soHsCv.greaterThan=" + DEFAULT_SO_HS_CV);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansBySoCcIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where soCc equals to
        defaultDanhSachTaiSanFiltering("soCc.equals=" + DEFAULT_SO_CC, "soCc.equals=" + UPDATED_SO_CC);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansBySoCcIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where soCc in
        defaultDanhSachTaiSanFiltering("soCc.in=" + DEFAULT_SO_CC + "," + UPDATED_SO_CC, "soCc.in=" + UPDATED_SO_CC);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansBySoCcIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where soCc is not null
        defaultDanhSachTaiSanFiltering("soCc.specified=true", "soCc.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansBySoCcIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where soCc is greater than or equal to
        defaultDanhSachTaiSanFiltering("soCc.greaterThanOrEqual=" + DEFAULT_SO_CC, "soCc.greaterThanOrEqual=" + UPDATED_SO_CC);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansBySoCcIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where soCc is less than or equal to
        defaultDanhSachTaiSanFiltering("soCc.lessThanOrEqual=" + DEFAULT_SO_CC, "soCc.lessThanOrEqual=" + SMALLER_SO_CC);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansBySoCcIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where soCc is less than
        defaultDanhSachTaiSanFiltering("soCc.lessThan=" + UPDATED_SO_CC, "soCc.lessThan=" + DEFAULT_SO_CC);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansBySoCcIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where soCc is greater than
        defaultDanhSachTaiSanFiltering("soCc.greaterThan=" + SMALLER_SO_CC, "soCc.greaterThan=" + DEFAULT_SO_CC);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansBySoVaoSoIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where soVaoSo equals to
        defaultDanhSachTaiSanFiltering("soVaoSo.equals=" + DEFAULT_SO_VAO_SO, "soVaoSo.equals=" + UPDATED_SO_VAO_SO);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansBySoVaoSoIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where soVaoSo in
        defaultDanhSachTaiSanFiltering("soVaoSo.in=" + DEFAULT_SO_VAO_SO + "," + UPDATED_SO_VAO_SO, "soVaoSo.in=" + UPDATED_SO_VAO_SO);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansBySoVaoSoIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where soVaoSo is not null
        defaultDanhSachTaiSanFiltering("soVaoSo.specified=true", "soVaoSo.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansBySoVaoSoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where soVaoSo is greater than or equal to
        defaultDanhSachTaiSanFiltering(
            "soVaoSo.greaterThanOrEqual=" + DEFAULT_SO_VAO_SO,
            "soVaoSo.greaterThanOrEqual=" + UPDATED_SO_VAO_SO
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansBySoVaoSoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where soVaoSo is less than or equal to
        defaultDanhSachTaiSanFiltering("soVaoSo.lessThanOrEqual=" + DEFAULT_SO_VAO_SO, "soVaoSo.lessThanOrEqual=" + SMALLER_SO_VAO_SO);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansBySoVaoSoIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where soVaoSo is less than
        defaultDanhSachTaiSanFiltering("soVaoSo.lessThan=" + UPDATED_SO_VAO_SO, "soVaoSo.lessThan=" + DEFAULT_SO_VAO_SO);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansBySoVaoSoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where soVaoSo is greater than
        defaultDanhSachTaiSanFiltering("soVaoSo.greaterThan=" + SMALLER_SO_VAO_SO, "soVaoSo.greaterThan=" + DEFAULT_SO_VAO_SO);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByMoTaIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where moTa equals to
        defaultDanhSachTaiSanFiltering("moTa.equals=" + DEFAULT_MO_TA, "moTa.equals=" + UPDATED_MO_TA);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByMoTaIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where moTa in
        defaultDanhSachTaiSanFiltering("moTa.in=" + DEFAULT_MO_TA + "," + UPDATED_MO_TA, "moTa.in=" + UPDATED_MO_TA);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByMoTaIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where moTa is not null
        defaultDanhSachTaiSanFiltering("moTa.specified=true", "moTa.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByMoTaContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where moTa contains
        defaultDanhSachTaiSanFiltering("moTa.contains=" + DEFAULT_MO_TA, "moTa.contains=" + UPDATED_MO_TA);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByMoTaNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where moTa does not contain
        defaultDanhSachTaiSanFiltering("moTa.doesNotContain=" + UPDATED_MO_TA, "moTa.doesNotContain=" + DEFAULT_MO_TA);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByLoaiNganChanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where loaiNganChan equals to
        defaultDanhSachTaiSanFiltering("loaiNganChan.equals=" + DEFAULT_LOAI_NGAN_CHAN, "loaiNganChan.equals=" + UPDATED_LOAI_NGAN_CHAN);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByLoaiNganChanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where loaiNganChan in
        defaultDanhSachTaiSanFiltering(
            "loaiNganChan.in=" + DEFAULT_LOAI_NGAN_CHAN + "," + UPDATED_LOAI_NGAN_CHAN,
            "loaiNganChan.in=" + UPDATED_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByLoaiNganChanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where loaiNganChan is not null
        defaultDanhSachTaiSanFiltering("loaiNganChan.specified=true", "loaiNganChan.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByLoaiNganChanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where loaiNganChan is greater than or equal to
        defaultDanhSachTaiSanFiltering(
            "loaiNganChan.greaterThanOrEqual=" + DEFAULT_LOAI_NGAN_CHAN,
            "loaiNganChan.greaterThanOrEqual=" + UPDATED_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByLoaiNganChanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where loaiNganChan is less than or equal to
        defaultDanhSachTaiSanFiltering(
            "loaiNganChan.lessThanOrEqual=" + DEFAULT_LOAI_NGAN_CHAN,
            "loaiNganChan.lessThanOrEqual=" + SMALLER_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByLoaiNganChanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where loaiNganChan is less than
        defaultDanhSachTaiSanFiltering(
            "loaiNganChan.lessThan=" + UPDATED_LOAI_NGAN_CHAN,
            "loaiNganChan.lessThan=" + DEFAULT_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByLoaiNganChanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where loaiNganChan is greater than
        defaultDanhSachTaiSanFiltering(
            "loaiNganChan.greaterThan=" + SMALLER_LOAI_NGAN_CHAN,
            "loaiNganChan.greaterThan=" + DEFAULT_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByMaXaIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where maXa equals to
        defaultDanhSachTaiSanFiltering("maXa.equals=" + DEFAULT_MA_XA, "maXa.equals=" + UPDATED_MA_XA);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByMaXaIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where maXa in
        defaultDanhSachTaiSanFiltering("maXa.in=" + DEFAULT_MA_XA + "," + UPDATED_MA_XA, "maXa.in=" + UPDATED_MA_XA);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByMaXaIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where maXa is not null
        defaultDanhSachTaiSanFiltering("maXa.specified=true", "maXa.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByMaXaContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where maXa contains
        defaultDanhSachTaiSanFiltering("maXa.contains=" + DEFAULT_MA_XA, "maXa.contains=" + UPDATED_MA_XA);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByMaXaNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        // Get all the danhSachTaiSanList where maXa does not contain
        defaultDanhSachTaiSanFiltering("maXa.doesNotContain=" + UPDATED_MA_XA, "maXa.doesNotContain=" + DEFAULT_MA_XA);
    }

    @Test
    @Transactional
    void getAllDanhSachTaiSansByIdLoaiTsIsEqualToSomething() throws Exception {
        DanhMucLoaiTaiSan idLoaiTs;
        if (TestUtil.findAll(em, DanhMucLoaiTaiSan.class).isEmpty()) {
            danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);
            idLoaiTs = DanhMucLoaiTaiSanResourceIT.createEntity(em);
        } else {
            idLoaiTs = TestUtil.findAll(em, DanhMucLoaiTaiSan.class).get(0);
        }
        em.persist(idLoaiTs);
        em.flush();
        danhSachTaiSan.setIdLoaiTs(idLoaiTs);
        danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);
        Long idLoaiTsId = idLoaiTs.getId();
        // Get all the danhSachTaiSanList where idLoaiTs equals to idLoaiTsId
        defaultDanhSachTaiSanShouldBeFound("idLoaiTsId.equals=" + idLoaiTsId);

        // Get all the danhSachTaiSanList where idLoaiTs equals to (idLoaiTsId + 1)
        defaultDanhSachTaiSanShouldNotBeFound("idLoaiTsId.equals=" + (idLoaiTsId + 1));
    }

    private void defaultDanhSachTaiSanFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultDanhSachTaiSanShouldBeFound(shouldBeFound);
        defaultDanhSachTaiSanShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDanhSachTaiSanShouldBeFound(String filter) throws Exception {
        restDanhSachTaiSanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danhSachTaiSan.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTaiSan").value(hasItem(DEFAULT_ID_TAI_SAN.intValue())))
            .andExpect(jsonPath("$.[*].tenTaiSan").value(hasItem(DEFAULT_TEN_TAI_SAN)))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())))
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
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].loaiNganChan").value(hasItem(DEFAULT_LOAI_NGAN_CHAN.intValue())))
            .andExpect(jsonPath("$.[*].maXa").value(hasItem(DEFAULT_MA_XA)));

        // Check, that the count call also returns 1
        restDanhSachTaiSanMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDanhSachTaiSanShouldNotBeFound(String filter) throws Exception {
        restDanhSachTaiSanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDanhSachTaiSanMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingDanhSachTaiSan() throws Exception {
        // Get the danhSachTaiSan
        restDanhSachTaiSanMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDanhSachTaiSan() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhSachTaiSan
        DanhSachTaiSan updatedDanhSachTaiSan = danhSachTaiSanRepository.findById(danhSachTaiSan.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDanhSachTaiSan are not directly saved in db
        em.detach(updatedDanhSachTaiSan);
        updatedDanhSachTaiSan
            .idTaiSan(UPDATED_ID_TAI_SAN)
            .tenTaiSan(UPDATED_TEN_TAI_SAN)
            .trangThai(UPDATED_TRANG_THAI)
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
            .moTa(UPDATED_MO_TA)
            .loaiNganChan(UPDATED_LOAI_NGAN_CHAN)
            .maXa(UPDATED_MA_XA);
        DanhSachTaiSanDTO danhSachTaiSanDTO = danhSachTaiSanMapper.toDto(updatedDanhSachTaiSan);

        restDanhSachTaiSanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhSachTaiSanDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhSachTaiSanDTO))
            )
            .andExpect(status().isOk());

        // Validate the DanhSachTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDanhSachTaiSanToMatchAllProperties(updatedDanhSachTaiSan);
    }

    @Test
    @Transactional
    void putNonExistingDanhSachTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhSachTaiSan.setId(longCount.incrementAndGet());

        // Create the DanhSachTaiSan
        DanhSachTaiSanDTO danhSachTaiSanDTO = danhSachTaiSanMapper.toDto(danhSachTaiSan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhSachTaiSanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhSachTaiSanDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhSachTaiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhSachTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDanhSachTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhSachTaiSan.setId(longCount.incrementAndGet());

        // Create the DanhSachTaiSan
        DanhSachTaiSanDTO danhSachTaiSanDTO = danhSachTaiSanMapper.toDto(danhSachTaiSan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhSachTaiSanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhSachTaiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhSachTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDanhSachTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhSachTaiSan.setId(longCount.incrementAndGet());

        // Create the DanhSachTaiSan
        DanhSachTaiSanDTO danhSachTaiSanDTO = danhSachTaiSanMapper.toDto(danhSachTaiSan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhSachTaiSanMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhSachTaiSanDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhSachTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDanhSachTaiSanWithPatch() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhSachTaiSan using partial update
        DanhSachTaiSan partialUpdatedDanhSachTaiSan = new DanhSachTaiSan();
        partialUpdatedDanhSachTaiSan.setId(danhSachTaiSan.getId());

        partialUpdatedDanhSachTaiSan
            .idTaiSan(UPDATED_ID_TAI_SAN)
            .tenTaiSan(UPDATED_TEN_TAI_SAN)
            .ghiChu(UPDATED_GHI_CHU)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDuongSu(UPDATED_ID_DUONG_SU)
            .idTsGoc(UPDATED_ID_TS_GOC)
            .maTaiSan(UPDATED_MA_TAI_SAN)
            .ngayBdNganChan(UPDATED_NGAY_BD_NGAN_CHAN)
            .idMaster(UPDATED_ID_MASTER)
            .strSearch(UPDATED_STR_SEARCH)
            .idDonVi(UPDATED_ID_DON_VI)
            .soHsCv(UPDATED_SO_HS_CV)
            .soVaoSo(UPDATED_SO_VAO_SO)
            .moTa(UPDATED_MO_TA)
            .loaiNganChan(UPDATED_LOAI_NGAN_CHAN);

        restDanhSachTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhSachTaiSan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhSachTaiSan))
            )
            .andExpect(status().isOk());

        // Validate the DanhSachTaiSan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhSachTaiSanUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDanhSachTaiSan, danhSachTaiSan),
            getPersistedDanhSachTaiSan(danhSachTaiSan)
        );
    }

    @Test
    @Transactional
    void fullUpdateDanhSachTaiSanWithPatch() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhSachTaiSan using partial update
        DanhSachTaiSan partialUpdatedDanhSachTaiSan = new DanhSachTaiSan();
        partialUpdatedDanhSachTaiSan.setId(danhSachTaiSan.getId());

        partialUpdatedDanhSachTaiSan
            .idTaiSan(UPDATED_ID_TAI_SAN)
            .tenTaiSan(UPDATED_TEN_TAI_SAN)
            .trangThai(UPDATED_TRANG_THAI)
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
            .moTa(UPDATED_MO_TA)
            .loaiNganChan(UPDATED_LOAI_NGAN_CHAN)
            .maXa(UPDATED_MA_XA);

        restDanhSachTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhSachTaiSan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhSachTaiSan))
            )
            .andExpect(status().isOk());

        // Validate the DanhSachTaiSan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhSachTaiSanUpdatableFieldsEquals(partialUpdatedDanhSachTaiSan, getPersistedDanhSachTaiSan(partialUpdatedDanhSachTaiSan));
    }

    @Test
    @Transactional
    void patchNonExistingDanhSachTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhSachTaiSan.setId(longCount.incrementAndGet());

        // Create the DanhSachTaiSan
        DanhSachTaiSanDTO danhSachTaiSanDTO = danhSachTaiSanMapper.toDto(danhSachTaiSan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhSachTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, danhSachTaiSanDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhSachTaiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhSachTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDanhSachTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhSachTaiSan.setId(longCount.incrementAndGet());

        // Create the DanhSachTaiSan
        DanhSachTaiSanDTO danhSachTaiSanDTO = danhSachTaiSanMapper.toDto(danhSachTaiSan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhSachTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhSachTaiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhSachTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDanhSachTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhSachTaiSan.setId(longCount.incrementAndGet());

        // Create the DanhSachTaiSan
        DanhSachTaiSanDTO danhSachTaiSanDTO = danhSachTaiSanMapper.toDto(danhSachTaiSan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhSachTaiSanMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(danhSachTaiSanDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhSachTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDanhSachTaiSan() throws Exception {
        // Initialize the database
        insertedDanhSachTaiSan = danhSachTaiSanRepository.saveAndFlush(danhSachTaiSan);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the danhSachTaiSan
        restDanhSachTaiSanMockMvc
            .perform(delete(ENTITY_API_URL_ID, danhSachTaiSan.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return danhSachTaiSanRepository.count();
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

    protected DanhSachTaiSan getPersistedDanhSachTaiSan(DanhSachTaiSan danhSachTaiSan) {
        return danhSachTaiSanRepository.findById(danhSachTaiSan.getId()).orElseThrow();
    }

    protected void assertPersistedDanhSachTaiSanToMatchAllProperties(DanhSachTaiSan expectedDanhSachTaiSan) {
        assertDanhSachTaiSanAllPropertiesEquals(expectedDanhSachTaiSan, getPersistedDanhSachTaiSan(expectedDanhSachTaiSan));
    }

    protected void assertPersistedDanhSachTaiSanToMatchUpdatableProperties(DanhSachTaiSan expectedDanhSachTaiSan) {
        assertDanhSachTaiSanAllUpdatablePropertiesEquals(expectedDanhSachTaiSan, getPersistedDanhSachTaiSan(expectedDanhSachTaiSan));
    }
}
