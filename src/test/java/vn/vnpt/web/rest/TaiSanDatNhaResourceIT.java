package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.TaiSanDatNhaAsserts.*;
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
import vn.vnpt.domain.TaiSanDatNha;
import vn.vnpt.repository.TaiSanDatNhaRepository;
import vn.vnpt.service.dto.TaiSanDatNhaDTO;
import vn.vnpt.service.mapper.TaiSanDatNhaMapper;

/**
 * Integration tests for the {@link TaiSanDatNhaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TaiSanDatNhaResourceIT {

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

    private static final Long DEFAULT_LOAI_NGAN_CHAN = 1L;
    private static final Long UPDATED_LOAI_NGAN_CHAN = 2L;
    private static final Long SMALLER_LOAI_NGAN_CHAN = 1L - 1L;

    private static final String ENTITY_API_URL = "/api/tai-san-dat-nhas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaiSanDatNhaRepository taiSanDatNhaRepository;

    @Autowired
    private TaiSanDatNhaMapper taiSanDatNhaMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTaiSanDatNhaMockMvc;

    private TaiSanDatNha taiSanDatNha;

    private TaiSanDatNha insertedTaiSanDatNha;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaiSanDatNha createEntity(EntityManager em) {
        TaiSanDatNha taiSanDatNha = new TaiSanDatNha()
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
            .moTa(DEFAULT_MO_TA)
            .loaiNganChan(DEFAULT_LOAI_NGAN_CHAN);
        return taiSanDatNha;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaiSanDatNha createUpdatedEntity(EntityManager em) {
        TaiSanDatNha taiSanDatNha = new TaiSanDatNha()
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
            .moTa(UPDATED_MO_TA)
            .loaiNganChan(UPDATED_LOAI_NGAN_CHAN);
        return taiSanDatNha;
    }

    @BeforeEach
    public void initTest() {
        taiSanDatNha = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedTaiSanDatNha != null) {
            taiSanDatNhaRepository.delete(insertedTaiSanDatNha);
            insertedTaiSanDatNha = null;
        }
    }

    @Test
    @Transactional
    void createTaiSanDatNha() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the TaiSanDatNha
        TaiSanDatNhaDTO taiSanDatNhaDTO = taiSanDatNhaMapper.toDto(taiSanDatNha);
        var returnedTaiSanDatNhaDTO = om.readValue(
            restTaiSanDatNhaMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(taiSanDatNhaDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            TaiSanDatNhaDTO.class
        );

        // Validate the TaiSanDatNha in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedTaiSanDatNha = taiSanDatNhaMapper.toEntity(returnedTaiSanDatNhaDTO);
        assertTaiSanDatNhaUpdatableFieldsEquals(returnedTaiSanDatNha, getPersistedTaiSanDatNha(returnedTaiSanDatNha));

        insertedTaiSanDatNha = returnedTaiSanDatNha;
    }

    @Test
    @Transactional
    void createTaiSanDatNhaWithExistingId() throws Exception {
        // Create the TaiSanDatNha with an existing ID
        taiSanDatNha.setId(1L);
        TaiSanDatNhaDTO taiSanDatNhaDTO = taiSanDatNhaMapper.toDto(taiSanDatNha);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaiSanDatNhaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(taiSanDatNhaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TaiSanDatNha in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhas() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList
        restTaiSanDatNhaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taiSanDatNha.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].loaiNganChan").value(hasItem(DEFAULT_LOAI_NGAN_CHAN.intValue())));
    }

    @Test
    @Transactional
    void getTaiSanDatNha() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get the taiSanDatNha
        restTaiSanDatNhaMockMvc
            .perform(get(ENTITY_API_URL_ID, taiSanDatNha.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(taiSanDatNha.getId().intValue()))
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
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA))
            .andExpect(jsonPath("$.loaiNganChan").value(DEFAULT_LOAI_NGAN_CHAN.intValue()));
    }

    @Test
    @Transactional
    void getTaiSanDatNhasByIdFiltering() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        Long id = taiSanDatNha.getId();

        defaultTaiSanDatNhaFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultTaiSanDatNhaFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultTaiSanDatNhaFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdTaiSanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idTaiSan equals to
        defaultTaiSanDatNhaFiltering("idTaiSan.equals=" + DEFAULT_ID_TAI_SAN, "idTaiSan.equals=" + UPDATED_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdTaiSanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idTaiSan in
        defaultTaiSanDatNhaFiltering("idTaiSan.in=" + DEFAULT_ID_TAI_SAN + "," + UPDATED_ID_TAI_SAN, "idTaiSan.in=" + UPDATED_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdTaiSanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idTaiSan is not null
        defaultTaiSanDatNhaFiltering("idTaiSan.specified=true", "idTaiSan.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdTaiSanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idTaiSan is greater than or equal to
        defaultTaiSanDatNhaFiltering(
            "idTaiSan.greaterThanOrEqual=" + DEFAULT_ID_TAI_SAN,
            "idTaiSan.greaterThanOrEqual=" + UPDATED_ID_TAI_SAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdTaiSanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idTaiSan is less than or equal to
        defaultTaiSanDatNhaFiltering("idTaiSan.lessThanOrEqual=" + DEFAULT_ID_TAI_SAN, "idTaiSan.lessThanOrEqual=" + SMALLER_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdTaiSanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idTaiSan is less than
        defaultTaiSanDatNhaFiltering("idTaiSan.lessThan=" + UPDATED_ID_TAI_SAN, "idTaiSan.lessThan=" + DEFAULT_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdTaiSanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idTaiSan is greater than
        defaultTaiSanDatNhaFiltering("idTaiSan.greaterThan=" + SMALLER_ID_TAI_SAN, "idTaiSan.greaterThan=" + DEFAULT_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByTenTaiSanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where tenTaiSan equals to
        defaultTaiSanDatNhaFiltering("tenTaiSan.equals=" + DEFAULT_TEN_TAI_SAN, "tenTaiSan.equals=" + UPDATED_TEN_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByTenTaiSanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where tenTaiSan in
        defaultTaiSanDatNhaFiltering(
            "tenTaiSan.in=" + DEFAULT_TEN_TAI_SAN + "," + UPDATED_TEN_TAI_SAN,
            "tenTaiSan.in=" + UPDATED_TEN_TAI_SAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByTenTaiSanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where tenTaiSan is not null
        defaultTaiSanDatNhaFiltering("tenTaiSan.specified=true", "tenTaiSan.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByTenTaiSanContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where tenTaiSan contains
        defaultTaiSanDatNhaFiltering("tenTaiSan.contains=" + DEFAULT_TEN_TAI_SAN, "tenTaiSan.contains=" + UPDATED_TEN_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByTenTaiSanNotContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where tenTaiSan does not contain
        defaultTaiSanDatNhaFiltering("tenTaiSan.doesNotContain=" + UPDATED_TEN_TAI_SAN, "tenTaiSan.doesNotContain=" + DEFAULT_TEN_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByTrangThaiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where trangThai equals to
        defaultTaiSanDatNhaFiltering("trangThai.equals=" + DEFAULT_TRANG_THAI, "trangThai.equals=" + UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByTrangThaiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where trangThai in
        defaultTaiSanDatNhaFiltering("trangThai.in=" + DEFAULT_TRANG_THAI + "," + UPDATED_TRANG_THAI, "trangThai.in=" + UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByTrangThaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where trangThai is not null
        defaultTaiSanDatNhaFiltering("trangThai.specified=true", "trangThai.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByTrangThaiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where trangThai is greater than or equal to
        defaultTaiSanDatNhaFiltering(
            "trangThai.greaterThanOrEqual=" + DEFAULT_TRANG_THAI,
            "trangThai.greaterThanOrEqual=" + UPDATED_TRANG_THAI
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByTrangThaiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where trangThai is less than or equal to
        defaultTaiSanDatNhaFiltering("trangThai.lessThanOrEqual=" + DEFAULT_TRANG_THAI, "trangThai.lessThanOrEqual=" + SMALLER_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByTrangThaiIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where trangThai is less than
        defaultTaiSanDatNhaFiltering("trangThai.lessThan=" + UPDATED_TRANG_THAI, "trangThai.lessThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByTrangThaiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where trangThai is greater than
        defaultTaiSanDatNhaFiltering("trangThai.greaterThan=" + SMALLER_TRANG_THAI, "trangThai.greaterThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByThongTinTsIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where thongTinTs equals to
        defaultTaiSanDatNhaFiltering("thongTinTs.equals=" + DEFAULT_THONG_TIN_TS, "thongTinTs.equals=" + UPDATED_THONG_TIN_TS);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByThongTinTsIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where thongTinTs in
        defaultTaiSanDatNhaFiltering(
            "thongTinTs.in=" + DEFAULT_THONG_TIN_TS + "," + UPDATED_THONG_TIN_TS,
            "thongTinTs.in=" + UPDATED_THONG_TIN_TS
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByThongTinTsIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where thongTinTs is not null
        defaultTaiSanDatNhaFiltering("thongTinTs.specified=true", "thongTinTs.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByThongTinTsContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where thongTinTs contains
        defaultTaiSanDatNhaFiltering("thongTinTs.contains=" + DEFAULT_THONG_TIN_TS, "thongTinTs.contains=" + UPDATED_THONG_TIN_TS);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByThongTinTsNotContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where thongTinTs does not contain
        defaultTaiSanDatNhaFiltering(
            "thongTinTs.doesNotContain=" + UPDATED_THONG_TIN_TS,
            "thongTinTs.doesNotContain=" + DEFAULT_THONG_TIN_TS
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdLoaiTsIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idLoaiTs equals to
        defaultTaiSanDatNhaFiltering("idLoaiTs.equals=" + DEFAULT_ID_LOAI_TS, "idLoaiTs.equals=" + UPDATED_ID_LOAI_TS);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdLoaiTsIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idLoaiTs in
        defaultTaiSanDatNhaFiltering("idLoaiTs.in=" + DEFAULT_ID_LOAI_TS + "," + UPDATED_ID_LOAI_TS, "idLoaiTs.in=" + UPDATED_ID_LOAI_TS);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdLoaiTsIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idLoaiTs is not null
        defaultTaiSanDatNhaFiltering("idLoaiTs.specified=true", "idLoaiTs.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdLoaiTsIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idLoaiTs is greater than or equal to
        defaultTaiSanDatNhaFiltering(
            "idLoaiTs.greaterThanOrEqual=" + DEFAULT_ID_LOAI_TS,
            "idLoaiTs.greaterThanOrEqual=" + UPDATED_ID_LOAI_TS
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdLoaiTsIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idLoaiTs is less than or equal to
        defaultTaiSanDatNhaFiltering("idLoaiTs.lessThanOrEqual=" + DEFAULT_ID_LOAI_TS, "idLoaiTs.lessThanOrEqual=" + SMALLER_ID_LOAI_TS);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdLoaiTsIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idLoaiTs is less than
        defaultTaiSanDatNhaFiltering("idLoaiTs.lessThan=" + UPDATED_ID_LOAI_TS, "idLoaiTs.lessThan=" + DEFAULT_ID_LOAI_TS);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdLoaiTsIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idLoaiTs is greater than
        defaultTaiSanDatNhaFiltering("idLoaiTs.greaterThan=" + SMALLER_ID_LOAI_TS, "idLoaiTs.greaterThan=" + DEFAULT_ID_LOAI_TS);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByGhiChuIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where ghiChu equals to
        defaultTaiSanDatNhaFiltering("ghiChu.equals=" + DEFAULT_GHI_CHU, "ghiChu.equals=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByGhiChuIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where ghiChu in
        defaultTaiSanDatNhaFiltering("ghiChu.in=" + DEFAULT_GHI_CHU + "," + UPDATED_GHI_CHU, "ghiChu.in=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByGhiChuIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where ghiChu is not null
        defaultTaiSanDatNhaFiltering("ghiChu.specified=true", "ghiChu.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByGhiChuContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where ghiChu contains
        defaultTaiSanDatNhaFiltering("ghiChu.contains=" + DEFAULT_GHI_CHU, "ghiChu.contains=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByGhiChuNotContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where ghiChu does not contain
        defaultTaiSanDatNhaFiltering("ghiChu.doesNotContain=" + UPDATED_GHI_CHU, "ghiChu.doesNotContain=" + DEFAULT_GHI_CHU);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByNgayThaoTacIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where ngayThaoTac equals to
        defaultTaiSanDatNhaFiltering("ngayThaoTac.equals=" + DEFAULT_NGAY_THAO_TAC, "ngayThaoTac.equals=" + UPDATED_NGAY_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByNgayThaoTacIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where ngayThaoTac in
        defaultTaiSanDatNhaFiltering(
            "ngayThaoTac.in=" + DEFAULT_NGAY_THAO_TAC + "," + UPDATED_NGAY_THAO_TAC,
            "ngayThaoTac.in=" + UPDATED_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByNgayThaoTacIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where ngayThaoTac is not null
        defaultTaiSanDatNhaFiltering("ngayThaoTac.specified=true", "ngayThaoTac.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByNgayThaoTacIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where ngayThaoTac is greater than or equal to
        defaultTaiSanDatNhaFiltering(
            "ngayThaoTac.greaterThanOrEqual=" + DEFAULT_NGAY_THAO_TAC,
            "ngayThaoTac.greaterThanOrEqual=" + UPDATED_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByNgayThaoTacIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where ngayThaoTac is less than or equal to
        defaultTaiSanDatNhaFiltering(
            "ngayThaoTac.lessThanOrEqual=" + DEFAULT_NGAY_THAO_TAC,
            "ngayThaoTac.lessThanOrEqual=" + SMALLER_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByNgayThaoTacIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where ngayThaoTac is less than
        defaultTaiSanDatNhaFiltering("ngayThaoTac.lessThan=" + UPDATED_NGAY_THAO_TAC, "ngayThaoTac.lessThan=" + DEFAULT_NGAY_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByNgayThaoTacIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where ngayThaoTac is greater than
        defaultTaiSanDatNhaFiltering(
            "ngayThaoTac.greaterThan=" + SMALLER_NGAY_THAO_TAC,
            "ngayThaoTac.greaterThan=" + DEFAULT_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByNguoiThaoTacIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where nguoiThaoTac equals to
        defaultTaiSanDatNhaFiltering("nguoiThaoTac.equals=" + DEFAULT_NGUOI_THAO_TAC, "nguoiThaoTac.equals=" + UPDATED_NGUOI_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByNguoiThaoTacIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where nguoiThaoTac in
        defaultTaiSanDatNhaFiltering(
            "nguoiThaoTac.in=" + DEFAULT_NGUOI_THAO_TAC + "," + UPDATED_NGUOI_THAO_TAC,
            "nguoiThaoTac.in=" + UPDATED_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByNguoiThaoTacIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where nguoiThaoTac is not null
        defaultTaiSanDatNhaFiltering("nguoiThaoTac.specified=true", "nguoiThaoTac.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByNguoiThaoTacIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where nguoiThaoTac is greater than or equal to
        defaultTaiSanDatNhaFiltering(
            "nguoiThaoTac.greaterThanOrEqual=" + DEFAULT_NGUOI_THAO_TAC,
            "nguoiThaoTac.greaterThanOrEqual=" + UPDATED_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByNguoiThaoTacIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where nguoiThaoTac is less than or equal to
        defaultTaiSanDatNhaFiltering(
            "nguoiThaoTac.lessThanOrEqual=" + DEFAULT_NGUOI_THAO_TAC,
            "nguoiThaoTac.lessThanOrEqual=" + SMALLER_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByNguoiThaoTacIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where nguoiThaoTac is less than
        defaultTaiSanDatNhaFiltering("nguoiThaoTac.lessThan=" + UPDATED_NGUOI_THAO_TAC, "nguoiThaoTac.lessThan=" + DEFAULT_NGUOI_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByNguoiThaoTacIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where nguoiThaoTac is greater than
        defaultTaiSanDatNhaFiltering(
            "nguoiThaoTac.greaterThan=" + SMALLER_NGUOI_THAO_TAC,
            "nguoiThaoTac.greaterThan=" + DEFAULT_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdDuongSuIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idDuongSu equals to
        defaultTaiSanDatNhaFiltering("idDuongSu.equals=" + DEFAULT_ID_DUONG_SU, "idDuongSu.equals=" + UPDATED_ID_DUONG_SU);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdDuongSuIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idDuongSu in
        defaultTaiSanDatNhaFiltering(
            "idDuongSu.in=" + DEFAULT_ID_DUONG_SU + "," + UPDATED_ID_DUONG_SU,
            "idDuongSu.in=" + UPDATED_ID_DUONG_SU
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdDuongSuIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idDuongSu is not null
        defaultTaiSanDatNhaFiltering("idDuongSu.specified=true", "idDuongSu.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdDuongSuIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idDuongSu is greater than or equal to
        defaultTaiSanDatNhaFiltering(
            "idDuongSu.greaterThanOrEqual=" + DEFAULT_ID_DUONG_SU,
            "idDuongSu.greaterThanOrEqual=" + UPDATED_ID_DUONG_SU
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdDuongSuIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idDuongSu is less than or equal to
        defaultTaiSanDatNhaFiltering(
            "idDuongSu.lessThanOrEqual=" + DEFAULT_ID_DUONG_SU,
            "idDuongSu.lessThanOrEqual=" + SMALLER_ID_DUONG_SU
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdDuongSuIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idDuongSu is less than
        defaultTaiSanDatNhaFiltering("idDuongSu.lessThan=" + UPDATED_ID_DUONG_SU, "idDuongSu.lessThan=" + DEFAULT_ID_DUONG_SU);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdDuongSuIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idDuongSu is greater than
        defaultTaiSanDatNhaFiltering("idDuongSu.greaterThan=" + SMALLER_ID_DUONG_SU, "idDuongSu.greaterThan=" + DEFAULT_ID_DUONG_SU);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdTsGocIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idTsGoc equals to
        defaultTaiSanDatNhaFiltering("idTsGoc.equals=" + DEFAULT_ID_TS_GOC, "idTsGoc.equals=" + UPDATED_ID_TS_GOC);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdTsGocIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idTsGoc in
        defaultTaiSanDatNhaFiltering("idTsGoc.in=" + DEFAULT_ID_TS_GOC + "," + UPDATED_ID_TS_GOC, "idTsGoc.in=" + UPDATED_ID_TS_GOC);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdTsGocIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idTsGoc is not null
        defaultTaiSanDatNhaFiltering("idTsGoc.specified=true", "idTsGoc.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdTsGocIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idTsGoc is greater than or equal to
        defaultTaiSanDatNhaFiltering("idTsGoc.greaterThanOrEqual=" + DEFAULT_ID_TS_GOC, "idTsGoc.greaterThanOrEqual=" + UPDATED_ID_TS_GOC);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdTsGocIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idTsGoc is less than or equal to
        defaultTaiSanDatNhaFiltering("idTsGoc.lessThanOrEqual=" + DEFAULT_ID_TS_GOC, "idTsGoc.lessThanOrEqual=" + SMALLER_ID_TS_GOC);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdTsGocIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idTsGoc is less than
        defaultTaiSanDatNhaFiltering("idTsGoc.lessThan=" + UPDATED_ID_TS_GOC, "idTsGoc.lessThan=" + DEFAULT_ID_TS_GOC);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdTsGocIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idTsGoc is greater than
        defaultTaiSanDatNhaFiltering("idTsGoc.greaterThan=" + SMALLER_ID_TS_GOC, "idTsGoc.greaterThan=" + DEFAULT_ID_TS_GOC);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByMaTaiSanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where maTaiSan equals to
        defaultTaiSanDatNhaFiltering("maTaiSan.equals=" + DEFAULT_MA_TAI_SAN, "maTaiSan.equals=" + UPDATED_MA_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByMaTaiSanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where maTaiSan in
        defaultTaiSanDatNhaFiltering("maTaiSan.in=" + DEFAULT_MA_TAI_SAN + "," + UPDATED_MA_TAI_SAN, "maTaiSan.in=" + UPDATED_MA_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByMaTaiSanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where maTaiSan is not null
        defaultTaiSanDatNhaFiltering("maTaiSan.specified=true", "maTaiSan.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByMaTaiSanContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where maTaiSan contains
        defaultTaiSanDatNhaFiltering("maTaiSan.contains=" + DEFAULT_MA_TAI_SAN, "maTaiSan.contains=" + UPDATED_MA_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByMaTaiSanNotContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where maTaiSan does not contain
        defaultTaiSanDatNhaFiltering("maTaiSan.doesNotContain=" + UPDATED_MA_TAI_SAN, "maTaiSan.doesNotContain=" + DEFAULT_MA_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdTinhTrangIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idTinhTrang equals to
        defaultTaiSanDatNhaFiltering("idTinhTrang.equals=" + DEFAULT_ID_TINH_TRANG, "idTinhTrang.equals=" + UPDATED_ID_TINH_TRANG);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdTinhTrangIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idTinhTrang in
        defaultTaiSanDatNhaFiltering(
            "idTinhTrang.in=" + DEFAULT_ID_TINH_TRANG + "," + UPDATED_ID_TINH_TRANG,
            "idTinhTrang.in=" + UPDATED_ID_TINH_TRANG
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdTinhTrangIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idTinhTrang is not null
        defaultTaiSanDatNhaFiltering("idTinhTrang.specified=true", "idTinhTrang.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdTinhTrangIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idTinhTrang is greater than or equal to
        defaultTaiSanDatNhaFiltering(
            "idTinhTrang.greaterThanOrEqual=" + DEFAULT_ID_TINH_TRANG,
            "idTinhTrang.greaterThanOrEqual=" + UPDATED_ID_TINH_TRANG
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdTinhTrangIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idTinhTrang is less than or equal to
        defaultTaiSanDatNhaFiltering(
            "idTinhTrang.lessThanOrEqual=" + DEFAULT_ID_TINH_TRANG,
            "idTinhTrang.lessThanOrEqual=" + SMALLER_ID_TINH_TRANG
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdTinhTrangIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idTinhTrang is less than
        defaultTaiSanDatNhaFiltering("idTinhTrang.lessThan=" + UPDATED_ID_TINH_TRANG, "idTinhTrang.lessThan=" + DEFAULT_ID_TINH_TRANG);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdTinhTrangIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idTinhTrang is greater than
        defaultTaiSanDatNhaFiltering(
            "idTinhTrang.greaterThan=" + SMALLER_ID_TINH_TRANG,
            "idTinhTrang.greaterThan=" + DEFAULT_ID_TINH_TRANG
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdLoaiNganChanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idLoaiNganChan equals to
        defaultTaiSanDatNhaFiltering(
            "idLoaiNganChan.equals=" + DEFAULT_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.equals=" + UPDATED_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdLoaiNganChanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idLoaiNganChan in
        defaultTaiSanDatNhaFiltering(
            "idLoaiNganChan.in=" + DEFAULT_ID_LOAI_NGAN_CHAN + "," + UPDATED_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.in=" + UPDATED_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdLoaiNganChanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idLoaiNganChan is not null
        defaultTaiSanDatNhaFiltering("idLoaiNganChan.specified=true", "idLoaiNganChan.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdLoaiNganChanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idLoaiNganChan is greater than or equal to
        defaultTaiSanDatNhaFiltering(
            "idLoaiNganChan.greaterThanOrEqual=" + DEFAULT_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.greaterThanOrEqual=" + UPDATED_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdLoaiNganChanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idLoaiNganChan is less than or equal to
        defaultTaiSanDatNhaFiltering(
            "idLoaiNganChan.lessThanOrEqual=" + DEFAULT_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.lessThanOrEqual=" + SMALLER_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdLoaiNganChanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idLoaiNganChan is less than
        defaultTaiSanDatNhaFiltering(
            "idLoaiNganChan.lessThan=" + UPDATED_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.lessThan=" + DEFAULT_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdLoaiNganChanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idLoaiNganChan is greater than
        defaultTaiSanDatNhaFiltering(
            "idLoaiNganChan.greaterThan=" + SMALLER_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.greaterThan=" + DEFAULT_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByNgayBdNganChanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where ngayBdNganChan equals to
        defaultTaiSanDatNhaFiltering(
            "ngayBdNganChan.equals=" + DEFAULT_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.equals=" + UPDATED_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByNgayBdNganChanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where ngayBdNganChan in
        defaultTaiSanDatNhaFiltering(
            "ngayBdNganChan.in=" + DEFAULT_NGAY_BD_NGAN_CHAN + "," + UPDATED_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.in=" + UPDATED_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByNgayBdNganChanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where ngayBdNganChan is not null
        defaultTaiSanDatNhaFiltering("ngayBdNganChan.specified=true", "ngayBdNganChan.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByNgayBdNganChanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where ngayBdNganChan is greater than or equal to
        defaultTaiSanDatNhaFiltering(
            "ngayBdNganChan.greaterThanOrEqual=" + DEFAULT_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.greaterThanOrEqual=" + UPDATED_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByNgayBdNganChanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where ngayBdNganChan is less than or equal to
        defaultTaiSanDatNhaFiltering(
            "ngayBdNganChan.lessThanOrEqual=" + DEFAULT_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.lessThanOrEqual=" + SMALLER_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByNgayBdNganChanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where ngayBdNganChan is less than
        defaultTaiSanDatNhaFiltering(
            "ngayBdNganChan.lessThan=" + UPDATED_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.lessThan=" + DEFAULT_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByNgayBdNganChanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where ngayBdNganChan is greater than
        defaultTaiSanDatNhaFiltering(
            "ngayBdNganChan.greaterThan=" + SMALLER_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.greaterThan=" + DEFAULT_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByNgayKtNganChanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where ngayKtNganChan equals to
        defaultTaiSanDatNhaFiltering(
            "ngayKtNganChan.equals=" + DEFAULT_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.equals=" + UPDATED_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByNgayKtNganChanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where ngayKtNganChan in
        defaultTaiSanDatNhaFiltering(
            "ngayKtNganChan.in=" + DEFAULT_NGAY_KT_NGAN_CHAN + "," + UPDATED_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.in=" + UPDATED_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByNgayKtNganChanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where ngayKtNganChan is not null
        defaultTaiSanDatNhaFiltering("ngayKtNganChan.specified=true", "ngayKtNganChan.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByNgayKtNganChanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where ngayKtNganChan is greater than or equal to
        defaultTaiSanDatNhaFiltering(
            "ngayKtNganChan.greaterThanOrEqual=" + DEFAULT_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.greaterThanOrEqual=" + UPDATED_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByNgayKtNganChanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where ngayKtNganChan is less than or equal to
        defaultTaiSanDatNhaFiltering(
            "ngayKtNganChan.lessThanOrEqual=" + DEFAULT_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.lessThanOrEqual=" + SMALLER_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByNgayKtNganChanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where ngayKtNganChan is less than
        defaultTaiSanDatNhaFiltering(
            "ngayKtNganChan.lessThan=" + UPDATED_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.lessThan=" + DEFAULT_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByNgayKtNganChanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where ngayKtNganChan is greater than
        defaultTaiSanDatNhaFiltering(
            "ngayKtNganChan.greaterThan=" + SMALLER_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.greaterThan=" + DEFAULT_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdMasterIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idMaster equals to
        defaultTaiSanDatNhaFiltering("idMaster.equals=" + DEFAULT_ID_MASTER, "idMaster.equals=" + UPDATED_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdMasterIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idMaster in
        defaultTaiSanDatNhaFiltering("idMaster.in=" + DEFAULT_ID_MASTER + "," + UPDATED_ID_MASTER, "idMaster.in=" + UPDATED_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdMasterIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idMaster is not null
        defaultTaiSanDatNhaFiltering("idMaster.specified=true", "idMaster.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdMasterIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idMaster is greater than or equal to
        defaultTaiSanDatNhaFiltering(
            "idMaster.greaterThanOrEqual=" + DEFAULT_ID_MASTER,
            "idMaster.greaterThanOrEqual=" + UPDATED_ID_MASTER
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdMasterIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idMaster is less than or equal to
        defaultTaiSanDatNhaFiltering("idMaster.lessThanOrEqual=" + DEFAULT_ID_MASTER, "idMaster.lessThanOrEqual=" + SMALLER_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdMasterIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idMaster is less than
        defaultTaiSanDatNhaFiltering("idMaster.lessThan=" + UPDATED_ID_MASTER, "idMaster.lessThan=" + DEFAULT_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdMasterIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idMaster is greater than
        defaultTaiSanDatNhaFiltering("idMaster.greaterThan=" + SMALLER_ID_MASTER, "idMaster.greaterThan=" + DEFAULT_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByStrSearchIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where strSearch equals to
        defaultTaiSanDatNhaFiltering("strSearch.equals=" + DEFAULT_STR_SEARCH, "strSearch.equals=" + UPDATED_STR_SEARCH);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByStrSearchIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where strSearch in
        defaultTaiSanDatNhaFiltering("strSearch.in=" + DEFAULT_STR_SEARCH + "," + UPDATED_STR_SEARCH, "strSearch.in=" + UPDATED_STR_SEARCH);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByStrSearchIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where strSearch is not null
        defaultTaiSanDatNhaFiltering("strSearch.specified=true", "strSearch.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByStrSearchContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where strSearch contains
        defaultTaiSanDatNhaFiltering("strSearch.contains=" + DEFAULT_STR_SEARCH, "strSearch.contains=" + UPDATED_STR_SEARCH);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByStrSearchNotContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where strSearch does not contain
        defaultTaiSanDatNhaFiltering("strSearch.doesNotContain=" + UPDATED_STR_SEARCH, "strSearch.doesNotContain=" + DEFAULT_STR_SEARCH);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdDonViIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idDonVi equals to
        defaultTaiSanDatNhaFiltering("idDonVi.equals=" + DEFAULT_ID_DON_VI, "idDonVi.equals=" + UPDATED_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdDonViIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idDonVi in
        defaultTaiSanDatNhaFiltering("idDonVi.in=" + DEFAULT_ID_DON_VI + "," + UPDATED_ID_DON_VI, "idDonVi.in=" + UPDATED_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdDonViIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idDonVi is not null
        defaultTaiSanDatNhaFiltering("idDonVi.specified=true", "idDonVi.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdDonViIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idDonVi is greater than or equal to
        defaultTaiSanDatNhaFiltering("idDonVi.greaterThanOrEqual=" + DEFAULT_ID_DON_VI, "idDonVi.greaterThanOrEqual=" + UPDATED_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdDonViIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idDonVi is less than or equal to
        defaultTaiSanDatNhaFiltering("idDonVi.lessThanOrEqual=" + DEFAULT_ID_DON_VI, "idDonVi.lessThanOrEqual=" + SMALLER_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdDonViIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idDonVi is less than
        defaultTaiSanDatNhaFiltering("idDonVi.lessThan=" + UPDATED_ID_DON_VI, "idDonVi.lessThan=" + DEFAULT_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByIdDonViIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where idDonVi is greater than
        defaultTaiSanDatNhaFiltering("idDonVi.greaterThan=" + SMALLER_ID_DON_VI, "idDonVi.greaterThan=" + DEFAULT_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasBySoHsCvIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where soHsCv equals to
        defaultTaiSanDatNhaFiltering("soHsCv.equals=" + DEFAULT_SO_HS_CV, "soHsCv.equals=" + UPDATED_SO_HS_CV);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasBySoHsCvIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where soHsCv in
        defaultTaiSanDatNhaFiltering("soHsCv.in=" + DEFAULT_SO_HS_CV + "," + UPDATED_SO_HS_CV, "soHsCv.in=" + UPDATED_SO_HS_CV);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasBySoHsCvIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where soHsCv is not null
        defaultTaiSanDatNhaFiltering("soHsCv.specified=true", "soHsCv.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasBySoHsCvIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where soHsCv is greater than or equal to
        defaultTaiSanDatNhaFiltering("soHsCv.greaterThanOrEqual=" + DEFAULT_SO_HS_CV, "soHsCv.greaterThanOrEqual=" + UPDATED_SO_HS_CV);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasBySoHsCvIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where soHsCv is less than or equal to
        defaultTaiSanDatNhaFiltering("soHsCv.lessThanOrEqual=" + DEFAULT_SO_HS_CV, "soHsCv.lessThanOrEqual=" + SMALLER_SO_HS_CV);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasBySoHsCvIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where soHsCv is less than
        defaultTaiSanDatNhaFiltering("soHsCv.lessThan=" + UPDATED_SO_HS_CV, "soHsCv.lessThan=" + DEFAULT_SO_HS_CV);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasBySoHsCvIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where soHsCv is greater than
        defaultTaiSanDatNhaFiltering("soHsCv.greaterThan=" + SMALLER_SO_HS_CV, "soHsCv.greaterThan=" + DEFAULT_SO_HS_CV);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasBySoCcIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where soCc equals to
        defaultTaiSanDatNhaFiltering("soCc.equals=" + DEFAULT_SO_CC, "soCc.equals=" + UPDATED_SO_CC);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasBySoCcIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where soCc in
        defaultTaiSanDatNhaFiltering("soCc.in=" + DEFAULT_SO_CC + "," + UPDATED_SO_CC, "soCc.in=" + UPDATED_SO_CC);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasBySoCcIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where soCc is not null
        defaultTaiSanDatNhaFiltering("soCc.specified=true", "soCc.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasBySoCcIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where soCc is greater than or equal to
        defaultTaiSanDatNhaFiltering("soCc.greaterThanOrEqual=" + DEFAULT_SO_CC, "soCc.greaterThanOrEqual=" + UPDATED_SO_CC);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasBySoCcIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where soCc is less than or equal to
        defaultTaiSanDatNhaFiltering("soCc.lessThanOrEqual=" + DEFAULT_SO_CC, "soCc.lessThanOrEqual=" + SMALLER_SO_CC);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasBySoCcIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where soCc is less than
        defaultTaiSanDatNhaFiltering("soCc.lessThan=" + UPDATED_SO_CC, "soCc.lessThan=" + DEFAULT_SO_CC);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasBySoCcIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where soCc is greater than
        defaultTaiSanDatNhaFiltering("soCc.greaterThan=" + SMALLER_SO_CC, "soCc.greaterThan=" + DEFAULT_SO_CC);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasBySoVaoSoIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where soVaoSo equals to
        defaultTaiSanDatNhaFiltering("soVaoSo.equals=" + DEFAULT_SO_VAO_SO, "soVaoSo.equals=" + UPDATED_SO_VAO_SO);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasBySoVaoSoIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where soVaoSo in
        defaultTaiSanDatNhaFiltering("soVaoSo.in=" + DEFAULT_SO_VAO_SO + "," + UPDATED_SO_VAO_SO, "soVaoSo.in=" + UPDATED_SO_VAO_SO);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasBySoVaoSoIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where soVaoSo is not null
        defaultTaiSanDatNhaFiltering("soVaoSo.specified=true", "soVaoSo.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasBySoVaoSoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where soVaoSo is greater than or equal to
        defaultTaiSanDatNhaFiltering("soVaoSo.greaterThanOrEqual=" + DEFAULT_SO_VAO_SO, "soVaoSo.greaterThanOrEqual=" + UPDATED_SO_VAO_SO);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasBySoVaoSoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where soVaoSo is less than or equal to
        defaultTaiSanDatNhaFiltering("soVaoSo.lessThanOrEqual=" + DEFAULT_SO_VAO_SO, "soVaoSo.lessThanOrEqual=" + SMALLER_SO_VAO_SO);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasBySoVaoSoIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where soVaoSo is less than
        defaultTaiSanDatNhaFiltering("soVaoSo.lessThan=" + UPDATED_SO_VAO_SO, "soVaoSo.lessThan=" + DEFAULT_SO_VAO_SO);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasBySoVaoSoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where soVaoSo is greater than
        defaultTaiSanDatNhaFiltering("soVaoSo.greaterThan=" + SMALLER_SO_VAO_SO, "soVaoSo.greaterThan=" + DEFAULT_SO_VAO_SO);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByMoTaIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where moTa equals to
        defaultTaiSanDatNhaFiltering("moTa.equals=" + DEFAULT_MO_TA, "moTa.equals=" + UPDATED_MO_TA);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByMoTaIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where moTa in
        defaultTaiSanDatNhaFiltering("moTa.in=" + DEFAULT_MO_TA + "," + UPDATED_MO_TA, "moTa.in=" + UPDATED_MO_TA);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByMoTaIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where moTa is not null
        defaultTaiSanDatNhaFiltering("moTa.specified=true", "moTa.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByMoTaContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where moTa contains
        defaultTaiSanDatNhaFiltering("moTa.contains=" + DEFAULT_MO_TA, "moTa.contains=" + UPDATED_MO_TA);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByMoTaNotContainsSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where moTa does not contain
        defaultTaiSanDatNhaFiltering("moTa.doesNotContain=" + UPDATED_MO_TA, "moTa.doesNotContain=" + DEFAULT_MO_TA);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByLoaiNganChanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where loaiNganChan equals to
        defaultTaiSanDatNhaFiltering("loaiNganChan.equals=" + DEFAULT_LOAI_NGAN_CHAN, "loaiNganChan.equals=" + UPDATED_LOAI_NGAN_CHAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByLoaiNganChanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where loaiNganChan in
        defaultTaiSanDatNhaFiltering(
            "loaiNganChan.in=" + DEFAULT_LOAI_NGAN_CHAN + "," + UPDATED_LOAI_NGAN_CHAN,
            "loaiNganChan.in=" + UPDATED_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByLoaiNganChanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where loaiNganChan is not null
        defaultTaiSanDatNhaFiltering("loaiNganChan.specified=true", "loaiNganChan.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByLoaiNganChanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where loaiNganChan is greater than or equal to
        defaultTaiSanDatNhaFiltering(
            "loaiNganChan.greaterThanOrEqual=" + DEFAULT_LOAI_NGAN_CHAN,
            "loaiNganChan.greaterThanOrEqual=" + UPDATED_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByLoaiNganChanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where loaiNganChan is less than or equal to
        defaultTaiSanDatNhaFiltering(
            "loaiNganChan.lessThanOrEqual=" + DEFAULT_LOAI_NGAN_CHAN,
            "loaiNganChan.lessThanOrEqual=" + SMALLER_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByLoaiNganChanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where loaiNganChan is less than
        defaultTaiSanDatNhaFiltering("loaiNganChan.lessThan=" + UPDATED_LOAI_NGAN_CHAN, "loaiNganChan.lessThan=" + DEFAULT_LOAI_NGAN_CHAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDatNhasByLoaiNganChanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        // Get all the taiSanDatNhaList where loaiNganChan is greater than
        defaultTaiSanDatNhaFiltering(
            "loaiNganChan.greaterThan=" + SMALLER_LOAI_NGAN_CHAN,
            "loaiNganChan.greaterThan=" + DEFAULT_LOAI_NGAN_CHAN
        );
    }

    private void defaultTaiSanDatNhaFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultTaiSanDatNhaShouldBeFound(shouldBeFound);
        defaultTaiSanDatNhaShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultTaiSanDatNhaShouldBeFound(String filter) throws Exception {
        restTaiSanDatNhaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taiSanDatNha.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].loaiNganChan").value(hasItem(DEFAULT_LOAI_NGAN_CHAN.intValue())));

        // Check, that the count call also returns 1
        restTaiSanDatNhaMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultTaiSanDatNhaShouldNotBeFound(String filter) throws Exception {
        restTaiSanDatNhaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restTaiSanDatNhaMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingTaiSanDatNha() throws Exception {
        // Get the taiSanDatNha
        restTaiSanDatNhaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTaiSanDatNha() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the taiSanDatNha
        TaiSanDatNha updatedTaiSanDatNha = taiSanDatNhaRepository.findById(taiSanDatNha.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedTaiSanDatNha are not directly saved in db
        em.detach(updatedTaiSanDatNha);
        updatedTaiSanDatNha
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
            .moTa(UPDATED_MO_TA)
            .loaiNganChan(UPDATED_LOAI_NGAN_CHAN);
        TaiSanDatNhaDTO taiSanDatNhaDTO = taiSanDatNhaMapper.toDto(updatedTaiSanDatNha);

        restTaiSanDatNhaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taiSanDatNhaDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(taiSanDatNhaDTO))
            )
            .andExpect(status().isOk());

        // Validate the TaiSanDatNha in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedTaiSanDatNhaToMatchAllProperties(updatedTaiSanDatNha);
    }

    @Test
    @Transactional
    void putNonExistingTaiSanDatNha() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taiSanDatNha.setId(longCount.incrementAndGet());

        // Create the TaiSanDatNha
        TaiSanDatNhaDTO taiSanDatNhaDTO = taiSanDatNhaMapper.toDto(taiSanDatNha);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaiSanDatNhaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taiSanDatNhaDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(taiSanDatNhaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaiSanDatNha in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTaiSanDatNha() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taiSanDatNha.setId(longCount.incrementAndGet());

        // Create the TaiSanDatNha
        TaiSanDatNhaDTO taiSanDatNhaDTO = taiSanDatNhaMapper.toDto(taiSanDatNha);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaiSanDatNhaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(taiSanDatNhaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaiSanDatNha in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTaiSanDatNha() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taiSanDatNha.setId(longCount.incrementAndGet());

        // Create the TaiSanDatNha
        TaiSanDatNhaDTO taiSanDatNhaDTO = taiSanDatNhaMapper.toDto(taiSanDatNha);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaiSanDatNhaMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(taiSanDatNhaDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaiSanDatNha in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTaiSanDatNhaWithPatch() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the taiSanDatNha using partial update
        TaiSanDatNha partialUpdatedTaiSanDatNha = new TaiSanDatNha();
        partialUpdatedTaiSanDatNha.setId(taiSanDatNha.getId());

        partialUpdatedTaiSanDatNha
            .idTaiSan(UPDATED_ID_TAI_SAN)
            .ghiChu(UPDATED_GHI_CHU)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .idDuongSu(UPDATED_ID_DUONG_SU)
            .idTsGoc(UPDATED_ID_TS_GOC)
            .idTinhTrang(UPDATED_ID_TINH_TRANG)
            .ngayKtNganChan(UPDATED_NGAY_KT_NGAN_CHAN)
            .strSearch(UPDATED_STR_SEARCH)
            .soHsCv(UPDATED_SO_HS_CV)
            .soVaoSo(UPDATED_SO_VAO_SO);

        restTaiSanDatNhaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaiSanDatNha.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTaiSanDatNha))
            )
            .andExpect(status().isOk());

        // Validate the TaiSanDatNha in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTaiSanDatNhaUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedTaiSanDatNha, taiSanDatNha),
            getPersistedTaiSanDatNha(taiSanDatNha)
        );
    }

    @Test
    @Transactional
    void fullUpdateTaiSanDatNhaWithPatch() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the taiSanDatNha using partial update
        TaiSanDatNha partialUpdatedTaiSanDatNha = new TaiSanDatNha();
        partialUpdatedTaiSanDatNha.setId(taiSanDatNha.getId());

        partialUpdatedTaiSanDatNha
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
            .moTa(UPDATED_MO_TA)
            .loaiNganChan(UPDATED_LOAI_NGAN_CHAN);

        restTaiSanDatNhaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaiSanDatNha.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTaiSanDatNha))
            )
            .andExpect(status().isOk());

        // Validate the TaiSanDatNha in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTaiSanDatNhaUpdatableFieldsEquals(partialUpdatedTaiSanDatNha, getPersistedTaiSanDatNha(partialUpdatedTaiSanDatNha));
    }

    @Test
    @Transactional
    void patchNonExistingTaiSanDatNha() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taiSanDatNha.setId(longCount.incrementAndGet());

        // Create the TaiSanDatNha
        TaiSanDatNhaDTO taiSanDatNhaDTO = taiSanDatNhaMapper.toDto(taiSanDatNha);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaiSanDatNhaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, taiSanDatNhaDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(taiSanDatNhaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaiSanDatNha in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTaiSanDatNha() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taiSanDatNha.setId(longCount.incrementAndGet());

        // Create the TaiSanDatNha
        TaiSanDatNhaDTO taiSanDatNhaDTO = taiSanDatNhaMapper.toDto(taiSanDatNha);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaiSanDatNhaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(taiSanDatNhaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaiSanDatNha in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTaiSanDatNha() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taiSanDatNha.setId(longCount.incrementAndGet());

        // Create the TaiSanDatNha
        TaiSanDatNhaDTO taiSanDatNhaDTO = taiSanDatNhaMapper.toDto(taiSanDatNha);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaiSanDatNhaMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(taiSanDatNhaDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaiSanDatNha in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTaiSanDatNha() throws Exception {
        // Initialize the database
        insertedTaiSanDatNha = taiSanDatNhaRepository.saveAndFlush(taiSanDatNha);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the taiSanDatNha
        restTaiSanDatNhaMockMvc
            .perform(delete(ENTITY_API_URL_ID, taiSanDatNha.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return taiSanDatNhaRepository.count();
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

    protected TaiSanDatNha getPersistedTaiSanDatNha(TaiSanDatNha taiSanDatNha) {
        return taiSanDatNhaRepository.findById(taiSanDatNha.getId()).orElseThrow();
    }

    protected void assertPersistedTaiSanDatNhaToMatchAllProperties(TaiSanDatNha expectedTaiSanDatNha) {
        assertTaiSanDatNhaAllPropertiesEquals(expectedTaiSanDatNha, getPersistedTaiSanDatNha(expectedTaiSanDatNha));
    }

    protected void assertPersistedTaiSanDatNhaToMatchUpdatableProperties(TaiSanDatNha expectedTaiSanDatNha) {
        assertTaiSanDatNhaAllUpdatablePropertiesEquals(expectedTaiSanDatNha, getPersistedTaiSanDatNha(expectedTaiSanDatNha));
    }
}
