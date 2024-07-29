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
import vn.vnpt.domain.TaiSan;
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

    private static final String DEFAULT_TEN_TAI_SAN = "AAAAAAAAAA";
    private static final String UPDATED_TEN_TAI_SAN = "BBBBBBBBBB";

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;

    private static final String DEFAULT_THONG_TIN_TS = "AAAAAAAAAA";
    private static final String UPDATED_THONG_TIN_TS = "BBBBBBBBBB";

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

    private static final Long DEFAULT_SYNC_STATUS = 1L;
    private static final Long UPDATED_SYNC_STATUS = 2L;

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
