package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.TaiSanAsserts.*;
import static vn.vnpt.web.rest.TestUtil.createUpdateProxyForBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.IntegrationTest;
import vn.vnpt.domain.DanhMucLoaiTaiSan;
import vn.vnpt.domain.TaiSan;
import vn.vnpt.domain.TaiSan;
import vn.vnpt.domain.TinhTrangTaiSan;
import vn.vnpt.repository.TaiSanRepository;
import vn.vnpt.service.TaiSanService;
import vn.vnpt.service.dto.TaiSanDTO;
import vn.vnpt.service.mapper.TaiSanMapper;

/**
 * Integration tests for the {@link TaiSanResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class TaiSanResourceIT {

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

    private static final Long DEFAULT_SYNC_STATUS = 1L;
    private static final Long UPDATED_SYNC_STATUS = 2L;
    private static final Long SMALLER_SYNC_STATUS = 1L - 1L;

    private static final String ENTITY_API_URL = "/api/tai-sans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaiSanRepository taiSanRepository;

    @Mock
    private TaiSanRepository taiSanRepositoryMock;

    @Autowired
    private TaiSanMapper taiSanMapper;

    @Mock
    private TaiSanService taiSanServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTaiSanMockMvc;

    private TaiSan taiSan;

    private TaiSan insertedTaiSan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaiSan createEntity(EntityManager em) {
        TaiSan taiSan = new TaiSan()
            .idTaiSan(DEFAULT_ID_TAI_SAN)
            .tenTaiSan(DEFAULT_TEN_TAI_SAN)
            .trangThai(DEFAULT_TRANG_THAI)
            .thongTinTs(DEFAULT_THONG_TIN_TS)
            .ghiChu(DEFAULT_GHI_CHU)
            .ngayThaoTac(DEFAULT_NGAY_THAO_TAC)
            .nguoiThaoTac(DEFAULT_NGUOI_THAO_TAC)
            .idDuongSu(DEFAULT_ID_DUONG_SU)
            .idTsGoc(DEFAULT_ID_TS_GOC)
            .maTaiSan(DEFAULT_MA_TAI_SAN)
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
            .syncStatus(DEFAULT_SYNC_STATUS);
        return taiSan;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaiSan createUpdatedEntity(EntityManager em) {
        TaiSan taiSan = new TaiSan()
            .idTaiSan(UPDATED_ID_TAI_SAN)
            .tenTaiSan(UPDATED_TEN_TAI_SAN)
            .trangThai(UPDATED_TRANG_THAI)
            .thongTinTs(UPDATED_THONG_TIN_TS)
            .ghiChu(UPDATED_GHI_CHU)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDuongSu(UPDATED_ID_DUONG_SU)
            .idTsGoc(UPDATED_ID_TS_GOC)
            .maTaiSan(UPDATED_MA_TAI_SAN)
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
            .syncStatus(UPDATED_SYNC_STATUS);
        return taiSan;
    }

    @BeforeEach
    public void initTest() {
        taiSan = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedTaiSan != null) {
            taiSanRepository.delete(insertedTaiSan);
            insertedTaiSan = null;
        }
    }

    @Test
    @Transactional
    void createTaiSan() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the TaiSan
        TaiSanDTO taiSanDTO = taiSanMapper.toDto(taiSan);
        var returnedTaiSanDTO = om.readValue(
            restTaiSanMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(taiSanDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            TaiSanDTO.class
        );

        // Validate the TaiSan in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedTaiSan = taiSanMapper.toEntity(returnedTaiSanDTO);
        assertTaiSanUpdatableFieldsEquals(returnedTaiSan, getPersistedTaiSan(returnedTaiSan));

        insertedTaiSan = returnedTaiSan;
    }

    @Test
    @Transactional
    void createTaiSanWithExistingId() throws Exception {
        // Create the TaiSan with an existing ID
        taiSan.setId(1L);
        TaiSanDTO taiSanDTO = taiSanMapper.toDto(taiSan);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaiSanMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(taiSanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTaiSans() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList
        restTaiSanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taiSan.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTaiSan").value(hasItem(DEFAULT_ID_TAI_SAN.intValue())))
            .andExpect(jsonPath("$.[*].tenTaiSan").value(hasItem(DEFAULT_TEN_TAI_SAN)))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())))
            .andExpect(jsonPath("$.[*].thongTinTs").value(hasItem(DEFAULT_THONG_TIN_TS)))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU)))
            .andExpect(jsonPath("$.[*].ngayThaoTac").value(hasItem(DEFAULT_NGAY_THAO_TAC.toString())))
            .andExpect(jsonPath("$.[*].nguoiThaoTac").value(hasItem(DEFAULT_NGUOI_THAO_TAC.intValue())))
            .andExpect(jsonPath("$.[*].idDuongSu").value(hasItem(DEFAULT_ID_DUONG_SU.intValue())))
            .andExpect(jsonPath("$.[*].idTsGoc").value(hasItem(DEFAULT_ID_TS_GOC.intValue())))
            .andExpect(jsonPath("$.[*].maTaiSan").value(hasItem(DEFAULT_MA_TAI_SAN)))
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
            .andExpect(jsonPath("$.[*].syncStatus").value(hasItem(DEFAULT_SYNC_STATUS.intValue())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllTaiSansWithEagerRelationshipsIsEnabled() throws Exception {
        when(taiSanServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restTaiSanMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(taiSanServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllTaiSansWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(taiSanServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restTaiSanMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(taiSanRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getTaiSan() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get the taiSan
        restTaiSanMockMvc
            .perform(get(ENTITY_API_URL_ID, taiSan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(taiSan.getId().intValue()))
            .andExpect(jsonPath("$.idTaiSan").value(DEFAULT_ID_TAI_SAN.intValue()))
            .andExpect(jsonPath("$.tenTaiSan").value(DEFAULT_TEN_TAI_SAN))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI.intValue()))
            .andExpect(jsonPath("$.thongTinTs").value(DEFAULT_THONG_TIN_TS))
            .andExpect(jsonPath("$.ghiChu").value(DEFAULT_GHI_CHU))
            .andExpect(jsonPath("$.ngayThaoTac").value(DEFAULT_NGAY_THAO_TAC.toString()))
            .andExpect(jsonPath("$.nguoiThaoTac").value(DEFAULT_NGUOI_THAO_TAC.intValue()))
            .andExpect(jsonPath("$.idDuongSu").value(DEFAULT_ID_DUONG_SU.intValue()))
            .andExpect(jsonPath("$.idTsGoc").value(DEFAULT_ID_TS_GOC.intValue()))
            .andExpect(jsonPath("$.maTaiSan").value(DEFAULT_MA_TAI_SAN))
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
            .andExpect(jsonPath("$.syncStatus").value(DEFAULT_SYNC_STATUS.intValue()));
    }

    @Test
    @Transactional
    void getTaiSansByIdFiltering() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        Long id = taiSan.getId();

        defaultTaiSanFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultTaiSanFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultTaiSanFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllTaiSansByIdTaiSanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idTaiSan equals to
        defaultTaiSanFiltering("idTaiSan.equals=" + DEFAULT_ID_TAI_SAN, "idTaiSan.equals=" + UPDATED_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSansByIdTaiSanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idTaiSan in
        defaultTaiSanFiltering("idTaiSan.in=" + DEFAULT_ID_TAI_SAN + "," + UPDATED_ID_TAI_SAN, "idTaiSan.in=" + UPDATED_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSansByIdTaiSanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idTaiSan is not null
        defaultTaiSanFiltering("idTaiSan.specified=true", "idTaiSan.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSansByIdTaiSanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idTaiSan is greater than or equal to
        defaultTaiSanFiltering("idTaiSan.greaterThanOrEqual=" + DEFAULT_ID_TAI_SAN, "idTaiSan.greaterThanOrEqual=" + UPDATED_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSansByIdTaiSanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idTaiSan is less than or equal to
        defaultTaiSanFiltering("idTaiSan.lessThanOrEqual=" + DEFAULT_ID_TAI_SAN, "idTaiSan.lessThanOrEqual=" + SMALLER_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSansByIdTaiSanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idTaiSan is less than
        defaultTaiSanFiltering("idTaiSan.lessThan=" + UPDATED_ID_TAI_SAN, "idTaiSan.lessThan=" + DEFAULT_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSansByIdTaiSanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idTaiSan is greater than
        defaultTaiSanFiltering("idTaiSan.greaterThan=" + SMALLER_ID_TAI_SAN, "idTaiSan.greaterThan=" + DEFAULT_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSansByTenTaiSanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where tenTaiSan equals to
        defaultTaiSanFiltering("tenTaiSan.equals=" + DEFAULT_TEN_TAI_SAN, "tenTaiSan.equals=" + UPDATED_TEN_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSansByTenTaiSanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where tenTaiSan in
        defaultTaiSanFiltering("tenTaiSan.in=" + DEFAULT_TEN_TAI_SAN + "," + UPDATED_TEN_TAI_SAN, "tenTaiSan.in=" + UPDATED_TEN_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSansByTenTaiSanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where tenTaiSan is not null
        defaultTaiSanFiltering("tenTaiSan.specified=true", "tenTaiSan.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSansByTenTaiSanContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where tenTaiSan contains
        defaultTaiSanFiltering("tenTaiSan.contains=" + DEFAULT_TEN_TAI_SAN, "tenTaiSan.contains=" + UPDATED_TEN_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSansByTenTaiSanNotContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where tenTaiSan does not contain
        defaultTaiSanFiltering("tenTaiSan.doesNotContain=" + UPDATED_TEN_TAI_SAN, "tenTaiSan.doesNotContain=" + DEFAULT_TEN_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSansByTrangThaiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where trangThai equals to
        defaultTaiSanFiltering("trangThai.equals=" + DEFAULT_TRANG_THAI, "trangThai.equals=" + UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllTaiSansByTrangThaiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where trangThai in
        defaultTaiSanFiltering("trangThai.in=" + DEFAULT_TRANG_THAI + "," + UPDATED_TRANG_THAI, "trangThai.in=" + UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllTaiSansByTrangThaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where trangThai is not null
        defaultTaiSanFiltering("trangThai.specified=true", "trangThai.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSansByTrangThaiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where trangThai is greater than or equal to
        defaultTaiSanFiltering("trangThai.greaterThanOrEqual=" + DEFAULT_TRANG_THAI, "trangThai.greaterThanOrEqual=" + UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllTaiSansByTrangThaiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where trangThai is less than or equal to
        defaultTaiSanFiltering("trangThai.lessThanOrEqual=" + DEFAULT_TRANG_THAI, "trangThai.lessThanOrEqual=" + SMALLER_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllTaiSansByTrangThaiIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where trangThai is less than
        defaultTaiSanFiltering("trangThai.lessThan=" + UPDATED_TRANG_THAI, "trangThai.lessThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllTaiSansByTrangThaiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where trangThai is greater than
        defaultTaiSanFiltering("trangThai.greaterThan=" + SMALLER_TRANG_THAI, "trangThai.greaterThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllTaiSansByThongTinTsIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where thongTinTs equals to
        defaultTaiSanFiltering("thongTinTs.equals=" + DEFAULT_THONG_TIN_TS, "thongTinTs.equals=" + UPDATED_THONG_TIN_TS);
    }

    @Test
    @Transactional
    void getAllTaiSansByThongTinTsIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where thongTinTs in
        defaultTaiSanFiltering(
            "thongTinTs.in=" + DEFAULT_THONG_TIN_TS + "," + UPDATED_THONG_TIN_TS,
            "thongTinTs.in=" + UPDATED_THONG_TIN_TS
        );
    }

    @Test
    @Transactional
    void getAllTaiSansByThongTinTsIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where thongTinTs is not null
        defaultTaiSanFiltering("thongTinTs.specified=true", "thongTinTs.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSansByThongTinTsContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where thongTinTs contains
        defaultTaiSanFiltering("thongTinTs.contains=" + DEFAULT_THONG_TIN_TS, "thongTinTs.contains=" + UPDATED_THONG_TIN_TS);
    }

    @Test
    @Transactional
    void getAllTaiSansByThongTinTsNotContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where thongTinTs does not contain
        defaultTaiSanFiltering("thongTinTs.doesNotContain=" + UPDATED_THONG_TIN_TS, "thongTinTs.doesNotContain=" + DEFAULT_THONG_TIN_TS);
    }

    @Test
    @Transactional
    void getAllTaiSansByGhiChuIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where ghiChu equals to
        defaultTaiSanFiltering("ghiChu.equals=" + DEFAULT_GHI_CHU, "ghiChu.equals=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    void getAllTaiSansByGhiChuIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where ghiChu in
        defaultTaiSanFiltering("ghiChu.in=" + DEFAULT_GHI_CHU + "," + UPDATED_GHI_CHU, "ghiChu.in=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    void getAllTaiSansByGhiChuIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where ghiChu is not null
        defaultTaiSanFiltering("ghiChu.specified=true", "ghiChu.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSansByGhiChuContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where ghiChu contains
        defaultTaiSanFiltering("ghiChu.contains=" + DEFAULT_GHI_CHU, "ghiChu.contains=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    void getAllTaiSansByGhiChuNotContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where ghiChu does not contain
        defaultTaiSanFiltering("ghiChu.doesNotContain=" + UPDATED_GHI_CHU, "ghiChu.doesNotContain=" + DEFAULT_GHI_CHU);
    }

    @Test
    @Transactional
    void getAllTaiSansByNgayThaoTacIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where ngayThaoTac equals to
        defaultTaiSanFiltering("ngayThaoTac.equals=" + DEFAULT_NGAY_THAO_TAC, "ngayThaoTac.equals=" + UPDATED_NGAY_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllTaiSansByNgayThaoTacIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where ngayThaoTac in
        defaultTaiSanFiltering(
            "ngayThaoTac.in=" + DEFAULT_NGAY_THAO_TAC + "," + UPDATED_NGAY_THAO_TAC,
            "ngayThaoTac.in=" + UPDATED_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllTaiSansByNgayThaoTacIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where ngayThaoTac is not null
        defaultTaiSanFiltering("ngayThaoTac.specified=true", "ngayThaoTac.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSansByNgayThaoTacIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where ngayThaoTac is greater than or equal to
        defaultTaiSanFiltering(
            "ngayThaoTac.greaterThanOrEqual=" + DEFAULT_NGAY_THAO_TAC,
            "ngayThaoTac.greaterThanOrEqual=" + UPDATED_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllTaiSansByNgayThaoTacIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where ngayThaoTac is less than or equal to
        defaultTaiSanFiltering(
            "ngayThaoTac.lessThanOrEqual=" + DEFAULT_NGAY_THAO_TAC,
            "ngayThaoTac.lessThanOrEqual=" + SMALLER_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllTaiSansByNgayThaoTacIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where ngayThaoTac is less than
        defaultTaiSanFiltering("ngayThaoTac.lessThan=" + UPDATED_NGAY_THAO_TAC, "ngayThaoTac.lessThan=" + DEFAULT_NGAY_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllTaiSansByNgayThaoTacIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where ngayThaoTac is greater than
        defaultTaiSanFiltering("ngayThaoTac.greaterThan=" + SMALLER_NGAY_THAO_TAC, "ngayThaoTac.greaterThan=" + DEFAULT_NGAY_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllTaiSansByNguoiThaoTacIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where nguoiThaoTac equals to
        defaultTaiSanFiltering("nguoiThaoTac.equals=" + DEFAULT_NGUOI_THAO_TAC, "nguoiThaoTac.equals=" + UPDATED_NGUOI_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllTaiSansByNguoiThaoTacIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where nguoiThaoTac in
        defaultTaiSanFiltering(
            "nguoiThaoTac.in=" + DEFAULT_NGUOI_THAO_TAC + "," + UPDATED_NGUOI_THAO_TAC,
            "nguoiThaoTac.in=" + UPDATED_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllTaiSansByNguoiThaoTacIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where nguoiThaoTac is not null
        defaultTaiSanFiltering("nguoiThaoTac.specified=true", "nguoiThaoTac.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSansByNguoiThaoTacIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where nguoiThaoTac is greater than or equal to
        defaultTaiSanFiltering(
            "nguoiThaoTac.greaterThanOrEqual=" + DEFAULT_NGUOI_THAO_TAC,
            "nguoiThaoTac.greaterThanOrEqual=" + UPDATED_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllTaiSansByNguoiThaoTacIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where nguoiThaoTac is less than or equal to
        defaultTaiSanFiltering(
            "nguoiThaoTac.lessThanOrEqual=" + DEFAULT_NGUOI_THAO_TAC,
            "nguoiThaoTac.lessThanOrEqual=" + SMALLER_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllTaiSansByNguoiThaoTacIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where nguoiThaoTac is less than
        defaultTaiSanFiltering("nguoiThaoTac.lessThan=" + UPDATED_NGUOI_THAO_TAC, "nguoiThaoTac.lessThan=" + DEFAULT_NGUOI_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllTaiSansByNguoiThaoTacIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where nguoiThaoTac is greater than
        defaultTaiSanFiltering("nguoiThaoTac.greaterThan=" + SMALLER_NGUOI_THAO_TAC, "nguoiThaoTac.greaterThan=" + DEFAULT_NGUOI_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllTaiSansByIdDuongSuIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idDuongSu equals to
        defaultTaiSanFiltering("idDuongSu.equals=" + DEFAULT_ID_DUONG_SU, "idDuongSu.equals=" + UPDATED_ID_DUONG_SU);
    }

    @Test
    @Transactional
    void getAllTaiSansByIdDuongSuIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idDuongSu in
        defaultTaiSanFiltering("idDuongSu.in=" + DEFAULT_ID_DUONG_SU + "," + UPDATED_ID_DUONG_SU, "idDuongSu.in=" + UPDATED_ID_DUONG_SU);
    }

    @Test
    @Transactional
    void getAllTaiSansByIdDuongSuIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idDuongSu is not null
        defaultTaiSanFiltering("idDuongSu.specified=true", "idDuongSu.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSansByIdDuongSuIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idDuongSu is greater than or equal to
        defaultTaiSanFiltering(
            "idDuongSu.greaterThanOrEqual=" + DEFAULT_ID_DUONG_SU,
            "idDuongSu.greaterThanOrEqual=" + UPDATED_ID_DUONG_SU
        );
    }

    @Test
    @Transactional
    void getAllTaiSansByIdDuongSuIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idDuongSu is less than or equal to
        defaultTaiSanFiltering("idDuongSu.lessThanOrEqual=" + DEFAULT_ID_DUONG_SU, "idDuongSu.lessThanOrEqual=" + SMALLER_ID_DUONG_SU);
    }

    @Test
    @Transactional
    void getAllTaiSansByIdDuongSuIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idDuongSu is less than
        defaultTaiSanFiltering("idDuongSu.lessThan=" + UPDATED_ID_DUONG_SU, "idDuongSu.lessThan=" + DEFAULT_ID_DUONG_SU);
    }

    @Test
    @Transactional
    void getAllTaiSansByIdDuongSuIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idDuongSu is greater than
        defaultTaiSanFiltering("idDuongSu.greaterThan=" + SMALLER_ID_DUONG_SU, "idDuongSu.greaterThan=" + DEFAULT_ID_DUONG_SU);
    }

    @Test
    @Transactional
    void getAllTaiSansByIdTsGocIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idTsGoc equals to
        defaultTaiSanFiltering("idTsGoc.equals=" + DEFAULT_ID_TS_GOC, "idTsGoc.equals=" + UPDATED_ID_TS_GOC);
    }

    @Test
    @Transactional
    void getAllTaiSansByIdTsGocIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idTsGoc in
        defaultTaiSanFiltering("idTsGoc.in=" + DEFAULT_ID_TS_GOC + "," + UPDATED_ID_TS_GOC, "idTsGoc.in=" + UPDATED_ID_TS_GOC);
    }

    @Test
    @Transactional
    void getAllTaiSansByIdTsGocIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idTsGoc is not null
        defaultTaiSanFiltering("idTsGoc.specified=true", "idTsGoc.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSansByIdTsGocIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idTsGoc is greater than or equal to
        defaultTaiSanFiltering("idTsGoc.greaterThanOrEqual=" + DEFAULT_ID_TS_GOC, "idTsGoc.greaterThanOrEqual=" + UPDATED_ID_TS_GOC);
    }

    @Test
    @Transactional
    void getAllTaiSansByIdTsGocIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idTsGoc is less than or equal to
        defaultTaiSanFiltering("idTsGoc.lessThanOrEqual=" + DEFAULT_ID_TS_GOC, "idTsGoc.lessThanOrEqual=" + SMALLER_ID_TS_GOC);
    }

    @Test
    @Transactional
    void getAllTaiSansByIdTsGocIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idTsGoc is less than
        defaultTaiSanFiltering("idTsGoc.lessThan=" + UPDATED_ID_TS_GOC, "idTsGoc.lessThan=" + DEFAULT_ID_TS_GOC);
    }

    @Test
    @Transactional
    void getAllTaiSansByIdTsGocIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idTsGoc is greater than
        defaultTaiSanFiltering("idTsGoc.greaterThan=" + SMALLER_ID_TS_GOC, "idTsGoc.greaterThan=" + DEFAULT_ID_TS_GOC);
    }

    @Test
    @Transactional
    void getAllTaiSansByMaTaiSanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where maTaiSan equals to
        defaultTaiSanFiltering("maTaiSan.equals=" + DEFAULT_MA_TAI_SAN, "maTaiSan.equals=" + UPDATED_MA_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSansByMaTaiSanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where maTaiSan in
        defaultTaiSanFiltering("maTaiSan.in=" + DEFAULT_MA_TAI_SAN + "," + UPDATED_MA_TAI_SAN, "maTaiSan.in=" + UPDATED_MA_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSansByMaTaiSanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where maTaiSan is not null
        defaultTaiSanFiltering("maTaiSan.specified=true", "maTaiSan.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSansByMaTaiSanContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where maTaiSan contains
        defaultTaiSanFiltering("maTaiSan.contains=" + DEFAULT_MA_TAI_SAN, "maTaiSan.contains=" + UPDATED_MA_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSansByMaTaiSanNotContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where maTaiSan does not contain
        defaultTaiSanFiltering("maTaiSan.doesNotContain=" + UPDATED_MA_TAI_SAN, "maTaiSan.doesNotContain=" + DEFAULT_MA_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSansByIdLoaiNganChanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idLoaiNganChan equals to
        defaultTaiSanFiltering("idLoaiNganChan.equals=" + DEFAULT_ID_LOAI_NGAN_CHAN, "idLoaiNganChan.equals=" + UPDATED_ID_LOAI_NGAN_CHAN);
    }

    @Test
    @Transactional
    void getAllTaiSansByIdLoaiNganChanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idLoaiNganChan in
        defaultTaiSanFiltering(
            "idLoaiNganChan.in=" + DEFAULT_ID_LOAI_NGAN_CHAN + "," + UPDATED_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.in=" + UPDATED_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSansByIdLoaiNganChanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idLoaiNganChan is not null
        defaultTaiSanFiltering("idLoaiNganChan.specified=true", "idLoaiNganChan.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSansByIdLoaiNganChanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idLoaiNganChan is greater than or equal to
        defaultTaiSanFiltering(
            "idLoaiNganChan.greaterThanOrEqual=" + DEFAULT_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.greaterThanOrEqual=" + UPDATED_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSansByIdLoaiNganChanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idLoaiNganChan is less than or equal to
        defaultTaiSanFiltering(
            "idLoaiNganChan.lessThanOrEqual=" + DEFAULT_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.lessThanOrEqual=" + SMALLER_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSansByIdLoaiNganChanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idLoaiNganChan is less than
        defaultTaiSanFiltering(
            "idLoaiNganChan.lessThan=" + UPDATED_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.lessThan=" + DEFAULT_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSansByIdLoaiNganChanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idLoaiNganChan is greater than
        defaultTaiSanFiltering(
            "idLoaiNganChan.greaterThan=" + SMALLER_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.greaterThan=" + DEFAULT_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSansByNgayBdNganChanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where ngayBdNganChan equals to
        defaultTaiSanFiltering("ngayBdNganChan.equals=" + DEFAULT_NGAY_BD_NGAN_CHAN, "ngayBdNganChan.equals=" + UPDATED_NGAY_BD_NGAN_CHAN);
    }

    @Test
    @Transactional
    void getAllTaiSansByNgayBdNganChanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where ngayBdNganChan in
        defaultTaiSanFiltering(
            "ngayBdNganChan.in=" + DEFAULT_NGAY_BD_NGAN_CHAN + "," + UPDATED_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.in=" + UPDATED_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSansByNgayBdNganChanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where ngayBdNganChan is not null
        defaultTaiSanFiltering("ngayBdNganChan.specified=true", "ngayBdNganChan.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSansByNgayBdNganChanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where ngayBdNganChan is greater than or equal to
        defaultTaiSanFiltering(
            "ngayBdNganChan.greaterThanOrEqual=" + DEFAULT_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.greaterThanOrEqual=" + UPDATED_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSansByNgayBdNganChanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where ngayBdNganChan is less than or equal to
        defaultTaiSanFiltering(
            "ngayBdNganChan.lessThanOrEqual=" + DEFAULT_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.lessThanOrEqual=" + SMALLER_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSansByNgayBdNganChanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where ngayBdNganChan is less than
        defaultTaiSanFiltering(
            "ngayBdNganChan.lessThan=" + UPDATED_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.lessThan=" + DEFAULT_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSansByNgayBdNganChanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where ngayBdNganChan is greater than
        defaultTaiSanFiltering(
            "ngayBdNganChan.greaterThan=" + SMALLER_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.greaterThan=" + DEFAULT_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSansByNgayKtNganChanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where ngayKtNganChan equals to
        defaultTaiSanFiltering("ngayKtNganChan.equals=" + DEFAULT_NGAY_KT_NGAN_CHAN, "ngayKtNganChan.equals=" + UPDATED_NGAY_KT_NGAN_CHAN);
    }

    @Test
    @Transactional
    void getAllTaiSansByNgayKtNganChanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where ngayKtNganChan in
        defaultTaiSanFiltering(
            "ngayKtNganChan.in=" + DEFAULT_NGAY_KT_NGAN_CHAN + "," + UPDATED_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.in=" + UPDATED_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSansByNgayKtNganChanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where ngayKtNganChan is not null
        defaultTaiSanFiltering("ngayKtNganChan.specified=true", "ngayKtNganChan.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSansByNgayKtNganChanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where ngayKtNganChan is greater than or equal to
        defaultTaiSanFiltering(
            "ngayKtNganChan.greaterThanOrEqual=" + DEFAULT_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.greaterThanOrEqual=" + UPDATED_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSansByNgayKtNganChanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where ngayKtNganChan is less than or equal to
        defaultTaiSanFiltering(
            "ngayKtNganChan.lessThanOrEqual=" + DEFAULT_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.lessThanOrEqual=" + SMALLER_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSansByNgayKtNganChanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where ngayKtNganChan is less than
        defaultTaiSanFiltering(
            "ngayKtNganChan.lessThan=" + UPDATED_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.lessThan=" + DEFAULT_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSansByNgayKtNganChanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where ngayKtNganChan is greater than
        defaultTaiSanFiltering(
            "ngayKtNganChan.greaterThan=" + SMALLER_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.greaterThan=" + DEFAULT_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSansByIdMasterIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idMaster equals to
        defaultTaiSanFiltering("idMaster.equals=" + DEFAULT_ID_MASTER, "idMaster.equals=" + UPDATED_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllTaiSansByIdMasterIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idMaster in
        defaultTaiSanFiltering("idMaster.in=" + DEFAULT_ID_MASTER + "," + UPDATED_ID_MASTER, "idMaster.in=" + UPDATED_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllTaiSansByIdMasterIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idMaster is not null
        defaultTaiSanFiltering("idMaster.specified=true", "idMaster.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSansByIdMasterIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idMaster is greater than or equal to
        defaultTaiSanFiltering("idMaster.greaterThanOrEqual=" + DEFAULT_ID_MASTER, "idMaster.greaterThanOrEqual=" + UPDATED_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllTaiSansByIdMasterIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idMaster is less than or equal to
        defaultTaiSanFiltering("idMaster.lessThanOrEqual=" + DEFAULT_ID_MASTER, "idMaster.lessThanOrEqual=" + SMALLER_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllTaiSansByIdMasterIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idMaster is less than
        defaultTaiSanFiltering("idMaster.lessThan=" + UPDATED_ID_MASTER, "idMaster.lessThan=" + DEFAULT_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllTaiSansByIdMasterIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idMaster is greater than
        defaultTaiSanFiltering("idMaster.greaterThan=" + SMALLER_ID_MASTER, "idMaster.greaterThan=" + DEFAULT_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllTaiSansByStrSearchIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where strSearch equals to
        defaultTaiSanFiltering("strSearch.equals=" + DEFAULT_STR_SEARCH, "strSearch.equals=" + UPDATED_STR_SEARCH);
    }

    @Test
    @Transactional
    void getAllTaiSansByStrSearchIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where strSearch in
        defaultTaiSanFiltering("strSearch.in=" + DEFAULT_STR_SEARCH + "," + UPDATED_STR_SEARCH, "strSearch.in=" + UPDATED_STR_SEARCH);
    }

    @Test
    @Transactional
    void getAllTaiSansByStrSearchIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where strSearch is not null
        defaultTaiSanFiltering("strSearch.specified=true", "strSearch.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSansByStrSearchContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where strSearch contains
        defaultTaiSanFiltering("strSearch.contains=" + DEFAULT_STR_SEARCH, "strSearch.contains=" + UPDATED_STR_SEARCH);
    }

    @Test
    @Transactional
    void getAllTaiSansByStrSearchNotContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where strSearch does not contain
        defaultTaiSanFiltering("strSearch.doesNotContain=" + UPDATED_STR_SEARCH, "strSearch.doesNotContain=" + DEFAULT_STR_SEARCH);
    }

    @Test
    @Transactional
    void getAllTaiSansByIdDonViIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idDonVi equals to
        defaultTaiSanFiltering("idDonVi.equals=" + DEFAULT_ID_DON_VI, "idDonVi.equals=" + UPDATED_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllTaiSansByIdDonViIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idDonVi in
        defaultTaiSanFiltering("idDonVi.in=" + DEFAULT_ID_DON_VI + "," + UPDATED_ID_DON_VI, "idDonVi.in=" + UPDATED_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllTaiSansByIdDonViIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idDonVi is not null
        defaultTaiSanFiltering("idDonVi.specified=true", "idDonVi.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSansByIdDonViIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idDonVi is greater than or equal to
        defaultTaiSanFiltering("idDonVi.greaterThanOrEqual=" + DEFAULT_ID_DON_VI, "idDonVi.greaterThanOrEqual=" + UPDATED_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllTaiSansByIdDonViIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idDonVi is less than or equal to
        defaultTaiSanFiltering("idDonVi.lessThanOrEqual=" + DEFAULT_ID_DON_VI, "idDonVi.lessThanOrEqual=" + SMALLER_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllTaiSansByIdDonViIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idDonVi is less than
        defaultTaiSanFiltering("idDonVi.lessThan=" + UPDATED_ID_DON_VI, "idDonVi.lessThan=" + DEFAULT_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllTaiSansByIdDonViIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where idDonVi is greater than
        defaultTaiSanFiltering("idDonVi.greaterThan=" + SMALLER_ID_DON_VI, "idDonVi.greaterThan=" + DEFAULT_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllTaiSansBySoHsCvIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where soHsCv equals to
        defaultTaiSanFiltering("soHsCv.equals=" + DEFAULT_SO_HS_CV, "soHsCv.equals=" + UPDATED_SO_HS_CV);
    }

    @Test
    @Transactional
    void getAllTaiSansBySoHsCvIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where soHsCv in
        defaultTaiSanFiltering("soHsCv.in=" + DEFAULT_SO_HS_CV + "," + UPDATED_SO_HS_CV, "soHsCv.in=" + UPDATED_SO_HS_CV);
    }

    @Test
    @Transactional
    void getAllTaiSansBySoHsCvIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where soHsCv is not null
        defaultTaiSanFiltering("soHsCv.specified=true", "soHsCv.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSansBySoHsCvIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where soHsCv is greater than or equal to
        defaultTaiSanFiltering("soHsCv.greaterThanOrEqual=" + DEFAULT_SO_HS_CV, "soHsCv.greaterThanOrEqual=" + UPDATED_SO_HS_CV);
    }

    @Test
    @Transactional
    void getAllTaiSansBySoHsCvIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where soHsCv is less than or equal to
        defaultTaiSanFiltering("soHsCv.lessThanOrEqual=" + DEFAULT_SO_HS_CV, "soHsCv.lessThanOrEqual=" + SMALLER_SO_HS_CV);
    }

    @Test
    @Transactional
    void getAllTaiSansBySoHsCvIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where soHsCv is less than
        defaultTaiSanFiltering("soHsCv.lessThan=" + UPDATED_SO_HS_CV, "soHsCv.lessThan=" + DEFAULT_SO_HS_CV);
    }

    @Test
    @Transactional
    void getAllTaiSansBySoHsCvIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where soHsCv is greater than
        defaultTaiSanFiltering("soHsCv.greaterThan=" + SMALLER_SO_HS_CV, "soHsCv.greaterThan=" + DEFAULT_SO_HS_CV);
    }

    @Test
    @Transactional
    void getAllTaiSansBySoCcIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where soCc equals to
        defaultTaiSanFiltering("soCc.equals=" + DEFAULT_SO_CC, "soCc.equals=" + UPDATED_SO_CC);
    }

    @Test
    @Transactional
    void getAllTaiSansBySoCcIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where soCc in
        defaultTaiSanFiltering("soCc.in=" + DEFAULT_SO_CC + "," + UPDATED_SO_CC, "soCc.in=" + UPDATED_SO_CC);
    }

    @Test
    @Transactional
    void getAllTaiSansBySoCcIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where soCc is not null
        defaultTaiSanFiltering("soCc.specified=true", "soCc.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSansBySoCcIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where soCc is greater than or equal to
        defaultTaiSanFiltering("soCc.greaterThanOrEqual=" + DEFAULT_SO_CC, "soCc.greaterThanOrEqual=" + UPDATED_SO_CC);
    }

    @Test
    @Transactional
    void getAllTaiSansBySoCcIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where soCc is less than or equal to
        defaultTaiSanFiltering("soCc.lessThanOrEqual=" + DEFAULT_SO_CC, "soCc.lessThanOrEqual=" + SMALLER_SO_CC);
    }

    @Test
    @Transactional
    void getAllTaiSansBySoCcIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where soCc is less than
        defaultTaiSanFiltering("soCc.lessThan=" + UPDATED_SO_CC, "soCc.lessThan=" + DEFAULT_SO_CC);
    }

    @Test
    @Transactional
    void getAllTaiSansBySoCcIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where soCc is greater than
        defaultTaiSanFiltering("soCc.greaterThan=" + SMALLER_SO_CC, "soCc.greaterThan=" + DEFAULT_SO_CC);
    }

    @Test
    @Transactional
    void getAllTaiSansBySoVaoSoIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where soVaoSo equals to
        defaultTaiSanFiltering("soVaoSo.equals=" + DEFAULT_SO_VAO_SO, "soVaoSo.equals=" + UPDATED_SO_VAO_SO);
    }

    @Test
    @Transactional
    void getAllTaiSansBySoVaoSoIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where soVaoSo in
        defaultTaiSanFiltering("soVaoSo.in=" + DEFAULT_SO_VAO_SO + "," + UPDATED_SO_VAO_SO, "soVaoSo.in=" + UPDATED_SO_VAO_SO);
    }

    @Test
    @Transactional
    void getAllTaiSansBySoVaoSoIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where soVaoSo is not null
        defaultTaiSanFiltering("soVaoSo.specified=true", "soVaoSo.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSansBySoVaoSoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where soVaoSo is greater than or equal to
        defaultTaiSanFiltering("soVaoSo.greaterThanOrEqual=" + DEFAULT_SO_VAO_SO, "soVaoSo.greaterThanOrEqual=" + UPDATED_SO_VAO_SO);
    }

    @Test
    @Transactional
    void getAllTaiSansBySoVaoSoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where soVaoSo is less than or equal to
        defaultTaiSanFiltering("soVaoSo.lessThanOrEqual=" + DEFAULT_SO_VAO_SO, "soVaoSo.lessThanOrEqual=" + SMALLER_SO_VAO_SO);
    }

    @Test
    @Transactional
    void getAllTaiSansBySoVaoSoIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where soVaoSo is less than
        defaultTaiSanFiltering("soVaoSo.lessThan=" + UPDATED_SO_VAO_SO, "soVaoSo.lessThan=" + DEFAULT_SO_VAO_SO);
    }

    @Test
    @Transactional
    void getAllTaiSansBySoVaoSoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where soVaoSo is greater than
        defaultTaiSanFiltering("soVaoSo.greaterThan=" + SMALLER_SO_VAO_SO, "soVaoSo.greaterThan=" + DEFAULT_SO_VAO_SO);
    }

    @Test
    @Transactional
    void getAllTaiSansByMoTaIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where moTa equals to
        defaultTaiSanFiltering("moTa.equals=" + DEFAULT_MO_TA, "moTa.equals=" + UPDATED_MO_TA);
    }

    @Test
    @Transactional
    void getAllTaiSansByMoTaIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where moTa in
        defaultTaiSanFiltering("moTa.in=" + DEFAULT_MO_TA + "," + UPDATED_MO_TA, "moTa.in=" + UPDATED_MO_TA);
    }

    @Test
    @Transactional
    void getAllTaiSansByMoTaIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where moTa is not null
        defaultTaiSanFiltering("moTa.specified=true", "moTa.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSansByMoTaContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where moTa contains
        defaultTaiSanFiltering("moTa.contains=" + DEFAULT_MO_TA, "moTa.contains=" + UPDATED_MO_TA);
    }

    @Test
    @Transactional
    void getAllTaiSansByMoTaNotContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where moTa does not contain
        defaultTaiSanFiltering("moTa.doesNotContain=" + UPDATED_MO_TA, "moTa.doesNotContain=" + DEFAULT_MO_TA);
    }

    @Test
    @Transactional
    void getAllTaiSansByLoaiNganChanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where loaiNganChan equals to
        defaultTaiSanFiltering("loaiNganChan.equals=" + DEFAULT_LOAI_NGAN_CHAN, "loaiNganChan.equals=" + UPDATED_LOAI_NGAN_CHAN);
    }

    @Test
    @Transactional
    void getAllTaiSansByLoaiNganChanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where loaiNganChan in
        defaultTaiSanFiltering(
            "loaiNganChan.in=" + DEFAULT_LOAI_NGAN_CHAN + "," + UPDATED_LOAI_NGAN_CHAN,
            "loaiNganChan.in=" + UPDATED_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSansByLoaiNganChanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where loaiNganChan is not null
        defaultTaiSanFiltering("loaiNganChan.specified=true", "loaiNganChan.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSansByLoaiNganChanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where loaiNganChan is greater than or equal to
        defaultTaiSanFiltering(
            "loaiNganChan.greaterThanOrEqual=" + DEFAULT_LOAI_NGAN_CHAN,
            "loaiNganChan.greaterThanOrEqual=" + UPDATED_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSansByLoaiNganChanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where loaiNganChan is less than or equal to
        defaultTaiSanFiltering(
            "loaiNganChan.lessThanOrEqual=" + DEFAULT_LOAI_NGAN_CHAN,
            "loaiNganChan.lessThanOrEqual=" + SMALLER_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSansByLoaiNganChanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where loaiNganChan is less than
        defaultTaiSanFiltering("loaiNganChan.lessThan=" + UPDATED_LOAI_NGAN_CHAN, "loaiNganChan.lessThan=" + DEFAULT_LOAI_NGAN_CHAN);
    }

    @Test
    @Transactional
    void getAllTaiSansByLoaiNganChanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where loaiNganChan is greater than
        defaultTaiSanFiltering("loaiNganChan.greaterThan=" + SMALLER_LOAI_NGAN_CHAN, "loaiNganChan.greaterThan=" + DEFAULT_LOAI_NGAN_CHAN);
    }

    @Test
    @Transactional
    void getAllTaiSansBySyncStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where syncStatus equals to
        defaultTaiSanFiltering("syncStatus.equals=" + DEFAULT_SYNC_STATUS, "syncStatus.equals=" + UPDATED_SYNC_STATUS);
    }

    @Test
    @Transactional
    void getAllTaiSansBySyncStatusIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where syncStatus in
        defaultTaiSanFiltering("syncStatus.in=" + DEFAULT_SYNC_STATUS + "," + UPDATED_SYNC_STATUS, "syncStatus.in=" + UPDATED_SYNC_STATUS);
    }

    @Test
    @Transactional
    void getAllTaiSansBySyncStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where syncStatus is not null
        defaultTaiSanFiltering("syncStatus.specified=true", "syncStatus.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSansBySyncStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where syncStatus is greater than or equal to
        defaultTaiSanFiltering(
            "syncStatus.greaterThanOrEqual=" + DEFAULT_SYNC_STATUS,
            "syncStatus.greaterThanOrEqual=" + UPDATED_SYNC_STATUS
        );
    }

    @Test
    @Transactional
    void getAllTaiSansBySyncStatusIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where syncStatus is less than or equal to
        defaultTaiSanFiltering("syncStatus.lessThanOrEqual=" + DEFAULT_SYNC_STATUS, "syncStatus.lessThanOrEqual=" + SMALLER_SYNC_STATUS);
    }

    @Test
    @Transactional
    void getAllTaiSansBySyncStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where syncStatus is less than
        defaultTaiSanFiltering("syncStatus.lessThan=" + UPDATED_SYNC_STATUS, "syncStatus.lessThan=" + DEFAULT_SYNC_STATUS);
    }

    @Test
    @Transactional
    void getAllTaiSansBySyncStatusIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        // Get all the taiSanList where syncStatus is greater than
        defaultTaiSanFiltering("syncStatus.greaterThan=" + SMALLER_SYNC_STATUS, "syncStatus.greaterThan=" + DEFAULT_SYNC_STATUS);
    }

    @Test
    @Transactional
    void getAllTaiSansByIdTsGocIsEqualToSomething() throws Exception {
        TaiSan idTsGoc;
        if (TestUtil.findAll(em, TaiSan.class).isEmpty()) {
            taiSanRepository.saveAndFlush(taiSan);
            idTsGoc = TaiSanResourceIT.createEntity(em);
        } else {
            idTsGoc = TestUtil.findAll(em, TaiSan.class).get(0);
        }
        em.persist(idTsGoc);
        em.flush();
        taiSan.addIdTsGoc(idTsGoc);
        taiSanRepository.saveAndFlush(taiSan);
        Long idTsGocId = idTsGoc.getId();
        // Get all the taiSanList where idTsGoc equals to idTsGocId
        defaultTaiSanShouldBeFound("idTsGocId.equals=" + idTsGocId);

        // Get all the taiSanList where idTsGoc equals to (idTsGocId + 1)
        defaultTaiSanShouldNotBeFound("idTsGocId.equals=" + (idTsGocId + 1));
    }

    @Test
    @Transactional
    void getAllTaiSansByIdLoaiTsIsEqualToSomething() throws Exception {
        DanhMucLoaiTaiSan idLoaiTs;
        if (TestUtil.findAll(em, DanhMucLoaiTaiSan.class).isEmpty()) {
            taiSanRepository.saveAndFlush(taiSan);
            idLoaiTs = DanhMucLoaiTaiSanResourceIT.createEntity(em);
        } else {
            idLoaiTs = TestUtil.findAll(em, DanhMucLoaiTaiSan.class).get(0);
        }
        em.persist(idLoaiTs);
        em.flush();
        taiSan.setIdLoaiTs(idLoaiTs);
        taiSanRepository.saveAndFlush(taiSan);
        Long idLoaiTsId = idLoaiTs.getId();
        // Get all the taiSanList where idLoaiTs equals to idLoaiTsId
        defaultTaiSanShouldBeFound("idLoaiTsId.equals=" + idLoaiTsId);

        // Get all the taiSanList where idLoaiTs equals to (idLoaiTsId + 1)
        defaultTaiSanShouldNotBeFound("idLoaiTsId.equals=" + (idLoaiTsId + 1));
    }

    @Test
    @Transactional
    void getAllTaiSansByIdTinhTrangIsEqualToSomething() throws Exception {
        TinhTrangTaiSan idTinhTrang;
        if (TestUtil.findAll(em, TinhTrangTaiSan.class).isEmpty()) {
            taiSanRepository.saveAndFlush(taiSan);
            idTinhTrang = TinhTrangTaiSanResourceIT.createEntity(em);
        } else {
            idTinhTrang = TestUtil.findAll(em, TinhTrangTaiSan.class).get(0);
        }
        em.persist(idTinhTrang);
        em.flush();
        taiSan.setIdTinhTrang(idTinhTrang);
        taiSanRepository.saveAndFlush(taiSan);
        Long idTinhTrangId = idTinhTrang.getId();
        // Get all the taiSanList where idTinhTrang equals to idTinhTrangId
        defaultTaiSanShouldBeFound("idTinhTrangId.equals=" + idTinhTrangId);

        // Get all the taiSanList where idTinhTrang equals to (idTinhTrangId + 1)
        defaultTaiSanShouldNotBeFound("idTinhTrangId.equals=" + (idTinhTrangId + 1));
    }

    @Test
    @Transactional
    void getAllTaiSansByTaiSanIsEqualToSomething() throws Exception {
        TaiSan taiSan;
        if (TestUtil.findAll(em, TaiSan.class).isEmpty()) {
            taiSanRepository.saveAndFlush(taiSan);
            taiSan = TaiSanResourceIT.createEntity(em);
        } else {
            taiSan = TestUtil.findAll(em, TaiSan.class).get(0);
        }
        em.persist(taiSan);
        em.flush();
        taiSan.addTaiSan(taiSan);
        taiSanRepository.saveAndFlush(taiSan);
        Long taiSanId = taiSan.getId();
        // Get all the taiSanList where taiSan equals to taiSanId
        defaultTaiSanShouldBeFound("taiSanId.equals=" + taiSanId);

        // Get all the taiSanList where taiSan equals to (taiSanId + 1)
        defaultTaiSanShouldNotBeFound("taiSanId.equals=" + (taiSanId + 1));
    }

    private void defaultTaiSanFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultTaiSanShouldBeFound(shouldBeFound);
        defaultTaiSanShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultTaiSanShouldBeFound(String filter) throws Exception {
        restTaiSanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taiSan.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTaiSan").value(hasItem(DEFAULT_ID_TAI_SAN.intValue())))
            .andExpect(jsonPath("$.[*].tenTaiSan").value(hasItem(DEFAULT_TEN_TAI_SAN)))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())))
            .andExpect(jsonPath("$.[*].thongTinTs").value(hasItem(DEFAULT_THONG_TIN_TS)))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU)))
            .andExpect(jsonPath("$.[*].ngayThaoTac").value(hasItem(DEFAULT_NGAY_THAO_TAC.toString())))
            .andExpect(jsonPath("$.[*].nguoiThaoTac").value(hasItem(DEFAULT_NGUOI_THAO_TAC.intValue())))
            .andExpect(jsonPath("$.[*].idDuongSu").value(hasItem(DEFAULT_ID_DUONG_SU.intValue())))
            .andExpect(jsonPath("$.[*].idTsGoc").value(hasItem(DEFAULT_ID_TS_GOC.intValue())))
            .andExpect(jsonPath("$.[*].maTaiSan").value(hasItem(DEFAULT_MA_TAI_SAN)))
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
            .andExpect(jsonPath("$.[*].syncStatus").value(hasItem(DEFAULT_SYNC_STATUS.intValue())));

        // Check, that the count call also returns 1
        restTaiSanMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultTaiSanShouldNotBeFound(String filter) throws Exception {
        restTaiSanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restTaiSanMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingTaiSan() throws Exception {
        // Get the taiSan
        restTaiSanMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTaiSan() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the taiSan
        TaiSan updatedTaiSan = taiSanRepository.findById(taiSan.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedTaiSan are not directly saved in db
        em.detach(updatedTaiSan);
        updatedTaiSan
            .idTaiSan(UPDATED_ID_TAI_SAN)
            .tenTaiSan(UPDATED_TEN_TAI_SAN)
            .trangThai(UPDATED_TRANG_THAI)
            .thongTinTs(UPDATED_THONG_TIN_TS)
            .ghiChu(UPDATED_GHI_CHU)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDuongSu(UPDATED_ID_DUONG_SU)
            .idTsGoc(UPDATED_ID_TS_GOC)
            .maTaiSan(UPDATED_MA_TAI_SAN)
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
            .syncStatus(UPDATED_SYNC_STATUS);
        TaiSanDTO taiSanDTO = taiSanMapper.toDto(updatedTaiSan);

        restTaiSanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taiSanDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(taiSanDTO))
            )
            .andExpect(status().isOk());

        // Validate the TaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedTaiSanToMatchAllProperties(updatedTaiSan);
    }

    @Test
    @Transactional
    void putNonExistingTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taiSan.setId(longCount.incrementAndGet());

        // Create the TaiSan
        TaiSanDTO taiSanDTO = taiSanMapper.toDto(taiSan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaiSanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taiSanDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(taiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taiSan.setId(longCount.incrementAndGet());

        // Create the TaiSan
        TaiSanDTO taiSanDTO = taiSanMapper.toDto(taiSan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaiSanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(taiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taiSan.setId(longCount.incrementAndGet());

        // Create the TaiSan
        TaiSanDTO taiSanDTO = taiSanMapper.toDto(taiSan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaiSanMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(taiSanDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTaiSanWithPatch() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the taiSan using partial update
        TaiSan partialUpdatedTaiSan = new TaiSan();
        partialUpdatedTaiSan.setId(taiSan.getId());

        partialUpdatedTaiSan
            .tenTaiSan(UPDATED_TEN_TAI_SAN)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDuongSu(UPDATED_ID_DUONG_SU)
            .ngayBdNganChan(UPDATED_NGAY_BD_NGAN_CHAN)
            .soHsCv(UPDATED_SO_HS_CV)
            .soVaoSo(UPDATED_SO_VAO_SO)
            .moTa(UPDATED_MO_TA)
            .loaiNganChan(UPDATED_LOAI_NGAN_CHAN)
            .syncStatus(UPDATED_SYNC_STATUS);

        restTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaiSan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTaiSan))
            )
            .andExpect(status().isOk());

        // Validate the TaiSan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTaiSanUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedTaiSan, taiSan), getPersistedTaiSan(taiSan));
    }

    @Test
    @Transactional
    void fullUpdateTaiSanWithPatch() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the taiSan using partial update
        TaiSan partialUpdatedTaiSan = new TaiSan();
        partialUpdatedTaiSan.setId(taiSan.getId());

        partialUpdatedTaiSan
            .idTaiSan(UPDATED_ID_TAI_SAN)
            .tenTaiSan(UPDATED_TEN_TAI_SAN)
            .trangThai(UPDATED_TRANG_THAI)
            .thongTinTs(UPDATED_THONG_TIN_TS)
            .ghiChu(UPDATED_GHI_CHU)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDuongSu(UPDATED_ID_DUONG_SU)
            .idTsGoc(UPDATED_ID_TS_GOC)
            .maTaiSan(UPDATED_MA_TAI_SAN)
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
            .syncStatus(UPDATED_SYNC_STATUS);

        restTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaiSan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTaiSan))
            )
            .andExpect(status().isOk());

        // Validate the TaiSan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTaiSanUpdatableFieldsEquals(partialUpdatedTaiSan, getPersistedTaiSan(partialUpdatedTaiSan));
    }

    @Test
    @Transactional
    void patchNonExistingTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taiSan.setId(longCount.incrementAndGet());

        // Create the TaiSan
        TaiSanDTO taiSanDTO = taiSanMapper.toDto(taiSan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, taiSanDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(taiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taiSan.setId(longCount.incrementAndGet());

        // Create the TaiSan
        TaiSanDTO taiSanDTO = taiSanMapper.toDto(taiSan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(taiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taiSan.setId(longCount.incrementAndGet());

        // Create the TaiSan
        TaiSanDTO taiSanDTO = taiSanMapper.toDto(taiSan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaiSanMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(taiSanDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTaiSan() throws Exception {
        // Initialize the database
        insertedTaiSan = taiSanRepository.saveAndFlush(taiSan);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the taiSan
        restTaiSanMockMvc
            .perform(delete(ENTITY_API_URL_ID, taiSan.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return taiSanRepository.count();
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

    protected TaiSan getPersistedTaiSan(TaiSan taiSan) {
        return taiSanRepository.findById(taiSan.getId()).orElseThrow();
    }

    protected void assertPersistedTaiSanToMatchAllProperties(TaiSan expectedTaiSan) {
        assertTaiSanAllPropertiesEquals(expectedTaiSan, getPersistedTaiSan(expectedTaiSan));
    }

    protected void assertPersistedTaiSanToMatchUpdatableProperties(TaiSan expectedTaiSan) {
        assertTaiSanAllUpdatablePropertiesEquals(expectedTaiSan, getPersistedTaiSan(expectedTaiSan));
    }
}
