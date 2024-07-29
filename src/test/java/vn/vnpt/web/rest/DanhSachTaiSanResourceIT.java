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

    private static final String DEFAULT_TEN_TAI_SAN = "AAAAAAAAAA";
    private static final String UPDATED_TEN_TAI_SAN = "BBBBBBBBBB";

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;

    private static final String DEFAULT_GHI_CHU = "AAAAAAAAAA";
    private static final String UPDATED_GHI_CHU = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAY_THAO_TAC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_THAO_TAC = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_NGUOI_THAO_TAC = 1L;
    private static final Long UPDATED_NGUOI_THAO_TAC = 2L;

    private static final Long DEFAULT_ID_DUONG_SU = 1L;
    private static final Long UPDATED_ID_DUONG_SU = 2L;

    private static final Long DEFAULT_ID_TS_GOC = 1L;
    private static final Long UPDATED_ID_TS_GOC = 2L;

    private static final String DEFAULT_MA_TAI_SAN = "AAAAAAAAAA";
    private static final String UPDATED_MA_TAI_SAN = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_TINH_TRANG = 1L;
    private static final Long UPDATED_ID_TINH_TRANG = 2L;

    private static final Long DEFAULT_ID_LOAI_NGAN_CHAN = 1L;
    private static final Long UPDATED_ID_LOAI_NGAN_CHAN = 2L;

    private static final LocalDate DEFAULT_NGAY_BD_NGAN_CHAN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_BD_NGAN_CHAN = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_NGAY_KT_NGAN_CHAN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_KT_NGAN_CHAN = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_ID_MASTER = 1L;
    private static final Long UPDATED_ID_MASTER = 2L;

    private static final String DEFAULT_STR_SEARCH = "AAAAAAAAAA";
    private static final String UPDATED_STR_SEARCH = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_DON_VI = 1L;
    private static final Long UPDATED_ID_DON_VI = 2L;

    private static final Long DEFAULT_SO_HS_CV = 1L;
    private static final Long UPDATED_SO_HS_CV = 2L;

    private static final Long DEFAULT_SO_CC = 1L;
    private static final Long UPDATED_SO_CC = 2L;

    private static final Long DEFAULT_SO_VAO_SO = 1L;
    private static final Long UPDATED_SO_VAO_SO = 2L;

    private static final String DEFAULT_MO_TA = "AAAAAAAAAA";
    private static final String UPDATED_MO_TA = "BBBBBBBBBB";

    private static final Long DEFAULT_LOAI_NGAN_CHAN = 1L;
    private static final Long UPDATED_LOAI_NGAN_CHAN = 2L;

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
