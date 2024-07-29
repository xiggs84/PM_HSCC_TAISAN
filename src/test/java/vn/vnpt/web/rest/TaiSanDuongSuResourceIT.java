package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.TaiSanDuongSuAsserts.*;
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
import vn.vnpt.domain.TaiSanDuongSu;
import vn.vnpt.repository.TaiSanDuongSuRepository;
import vn.vnpt.service.dto.TaiSanDuongSuDTO;
import vn.vnpt.service.mapper.TaiSanDuongSuMapper;

/**
 * Integration tests for the {@link TaiSanDuongSuResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TaiSanDuongSuResourceIT {

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;

    private static final Long DEFAULT_ID_DUONG_SU = 1L;
    private static final Long UPDATED_ID_DUONG_SU = 2L;

    private static final LocalDate DEFAULT_NGAY_THAO_TAC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_THAO_TAC = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_ID_LOAI_HOP_DONG = 1L;
    private static final Long UPDATED_ID_LOAI_HOP_DONG = 2L;

    private static final Long DEFAULT_ID_CHUNG_THUC = 1L;
    private static final Long UPDATED_ID_CHUNG_THUC = 2L;

    private static final String ENTITY_API_URL = "/api/tai-san-duong-sus";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaiSanDuongSuRepository taiSanDuongSuRepository;

    @Autowired
    private TaiSanDuongSuMapper taiSanDuongSuMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTaiSanDuongSuMockMvc;

    private TaiSanDuongSu taiSanDuongSu;

    private TaiSanDuongSu insertedTaiSanDuongSu;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaiSanDuongSu createEntity(EntityManager em) {
        TaiSanDuongSu taiSanDuongSu = new TaiSanDuongSu()
            .trangThai(DEFAULT_TRANG_THAI)
            .idDuongSu(DEFAULT_ID_DUONG_SU)
            .ngayThaoTac(DEFAULT_NGAY_THAO_TAC)
            .idLoaiHopDong(DEFAULT_ID_LOAI_HOP_DONG)
            .idChungThuc(DEFAULT_ID_CHUNG_THUC);
        return taiSanDuongSu;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaiSanDuongSu createUpdatedEntity(EntityManager em) {
        TaiSanDuongSu taiSanDuongSu = new TaiSanDuongSu()
            .trangThai(UPDATED_TRANG_THAI)
            .idDuongSu(UPDATED_ID_DUONG_SU)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .idLoaiHopDong(UPDATED_ID_LOAI_HOP_DONG)
            .idChungThuc(UPDATED_ID_CHUNG_THUC);
        return taiSanDuongSu;
    }

    @BeforeEach
    public void initTest() {
        taiSanDuongSu = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedTaiSanDuongSu != null) {
            taiSanDuongSuRepository.delete(insertedTaiSanDuongSu);
            insertedTaiSanDuongSu = null;
        }
    }

    @Test
    @Transactional
    void createTaiSanDuongSu() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the TaiSanDuongSu
        TaiSanDuongSuDTO taiSanDuongSuDTO = taiSanDuongSuMapper.toDto(taiSanDuongSu);
        var returnedTaiSanDuongSuDTO = om.readValue(
            restTaiSanDuongSuMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(taiSanDuongSuDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            TaiSanDuongSuDTO.class
        );

        // Validate the TaiSanDuongSu in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedTaiSanDuongSu = taiSanDuongSuMapper.toEntity(returnedTaiSanDuongSuDTO);
        assertTaiSanDuongSuUpdatableFieldsEquals(returnedTaiSanDuongSu, getPersistedTaiSanDuongSu(returnedTaiSanDuongSu));

        insertedTaiSanDuongSu = returnedTaiSanDuongSu;
    }

    @Test
    @Transactional
    void createTaiSanDuongSuWithExistingId() throws Exception {
        // Create the TaiSanDuongSu with an existing ID
        taiSanDuongSu.setId(1L);
        TaiSanDuongSuDTO taiSanDuongSuDTO = taiSanDuongSuMapper.toDto(taiSanDuongSu);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaiSanDuongSuMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(taiSanDuongSuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TaiSanDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSus() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList
        restTaiSanDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taiSanDuongSu.getId().intValue())))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())))
            .andExpect(jsonPath("$.[*].idDuongSu").value(hasItem(DEFAULT_ID_DUONG_SU.intValue())))
            .andExpect(jsonPath("$.[*].ngayThaoTac").value(hasItem(DEFAULT_NGAY_THAO_TAC.toString())))
            .andExpect(jsonPath("$.[*].idLoaiHopDong").value(hasItem(DEFAULT_ID_LOAI_HOP_DONG.intValue())))
            .andExpect(jsonPath("$.[*].idChungThuc").value(hasItem(DEFAULT_ID_CHUNG_THUC.intValue())));
    }

    @Test
    @Transactional
    void getTaiSanDuongSu() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get the taiSanDuongSu
        restTaiSanDuongSuMockMvc
            .perform(get(ENTITY_API_URL_ID, taiSanDuongSu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(taiSanDuongSu.getId().intValue()))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI.intValue()))
            .andExpect(jsonPath("$.idDuongSu").value(DEFAULT_ID_DUONG_SU.intValue()))
            .andExpect(jsonPath("$.ngayThaoTac").value(DEFAULT_NGAY_THAO_TAC.toString()))
            .andExpect(jsonPath("$.idLoaiHopDong").value(DEFAULT_ID_LOAI_HOP_DONG.intValue()))
            .andExpect(jsonPath("$.idChungThuc").value(DEFAULT_ID_CHUNG_THUC.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTaiSanDuongSu() throws Exception {
        // Get the taiSanDuongSu
        restTaiSanDuongSuMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTaiSanDuongSu() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the taiSanDuongSu
        TaiSanDuongSu updatedTaiSanDuongSu = taiSanDuongSuRepository.findById(taiSanDuongSu.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedTaiSanDuongSu are not directly saved in db
        em.detach(updatedTaiSanDuongSu);
        updatedTaiSanDuongSu
            .trangThai(UPDATED_TRANG_THAI)
            .idDuongSu(UPDATED_ID_DUONG_SU)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .idLoaiHopDong(UPDATED_ID_LOAI_HOP_DONG)
            .idChungThuc(UPDATED_ID_CHUNG_THUC);
        TaiSanDuongSuDTO taiSanDuongSuDTO = taiSanDuongSuMapper.toDto(updatedTaiSanDuongSu);

        restTaiSanDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taiSanDuongSuDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(taiSanDuongSuDTO))
            )
            .andExpect(status().isOk());

        // Validate the TaiSanDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedTaiSanDuongSuToMatchAllProperties(updatedTaiSanDuongSu);
    }

    @Test
    @Transactional
    void putNonExistingTaiSanDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taiSanDuongSu.setId(longCount.incrementAndGet());

        // Create the TaiSanDuongSu
        TaiSanDuongSuDTO taiSanDuongSuDTO = taiSanDuongSuMapper.toDto(taiSanDuongSu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaiSanDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taiSanDuongSuDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(taiSanDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaiSanDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTaiSanDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taiSanDuongSu.setId(longCount.incrementAndGet());

        // Create the TaiSanDuongSu
        TaiSanDuongSuDTO taiSanDuongSuDTO = taiSanDuongSuMapper.toDto(taiSanDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaiSanDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(taiSanDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaiSanDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTaiSanDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taiSanDuongSu.setId(longCount.incrementAndGet());

        // Create the TaiSanDuongSu
        TaiSanDuongSuDTO taiSanDuongSuDTO = taiSanDuongSuMapper.toDto(taiSanDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaiSanDuongSuMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(taiSanDuongSuDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaiSanDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTaiSanDuongSuWithPatch() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the taiSanDuongSu using partial update
        TaiSanDuongSu partialUpdatedTaiSanDuongSu = new TaiSanDuongSu();
        partialUpdatedTaiSanDuongSu.setId(taiSanDuongSu.getId());

        partialUpdatedTaiSanDuongSu.idDuongSu(UPDATED_ID_DUONG_SU).idLoaiHopDong(UPDATED_ID_LOAI_HOP_DONG);

        restTaiSanDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaiSanDuongSu.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTaiSanDuongSu))
            )
            .andExpect(status().isOk());

        // Validate the TaiSanDuongSu in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTaiSanDuongSuUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedTaiSanDuongSu, taiSanDuongSu),
            getPersistedTaiSanDuongSu(taiSanDuongSu)
        );
    }

    @Test
    @Transactional
    void fullUpdateTaiSanDuongSuWithPatch() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the taiSanDuongSu using partial update
        TaiSanDuongSu partialUpdatedTaiSanDuongSu = new TaiSanDuongSu();
        partialUpdatedTaiSanDuongSu.setId(taiSanDuongSu.getId());

        partialUpdatedTaiSanDuongSu
            .trangThai(UPDATED_TRANG_THAI)
            .idDuongSu(UPDATED_ID_DUONG_SU)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .idLoaiHopDong(UPDATED_ID_LOAI_HOP_DONG)
            .idChungThuc(UPDATED_ID_CHUNG_THUC);

        restTaiSanDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaiSanDuongSu.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTaiSanDuongSu))
            )
            .andExpect(status().isOk());

        // Validate the TaiSanDuongSu in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTaiSanDuongSuUpdatableFieldsEquals(partialUpdatedTaiSanDuongSu, getPersistedTaiSanDuongSu(partialUpdatedTaiSanDuongSu));
    }

    @Test
    @Transactional
    void patchNonExistingTaiSanDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taiSanDuongSu.setId(longCount.incrementAndGet());

        // Create the TaiSanDuongSu
        TaiSanDuongSuDTO taiSanDuongSuDTO = taiSanDuongSuMapper.toDto(taiSanDuongSu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaiSanDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, taiSanDuongSuDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(taiSanDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaiSanDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTaiSanDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taiSanDuongSu.setId(longCount.incrementAndGet());

        // Create the TaiSanDuongSu
        TaiSanDuongSuDTO taiSanDuongSuDTO = taiSanDuongSuMapper.toDto(taiSanDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaiSanDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(taiSanDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaiSanDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTaiSanDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taiSanDuongSu.setId(longCount.incrementAndGet());

        // Create the TaiSanDuongSu
        TaiSanDuongSuDTO taiSanDuongSuDTO = taiSanDuongSuMapper.toDto(taiSanDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaiSanDuongSuMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(taiSanDuongSuDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaiSanDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTaiSanDuongSu() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the taiSanDuongSu
        restTaiSanDuongSuMockMvc
            .perform(delete(ENTITY_API_URL_ID, taiSanDuongSu.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return taiSanDuongSuRepository.count();
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

    protected TaiSanDuongSu getPersistedTaiSanDuongSu(TaiSanDuongSu taiSanDuongSu) {
        return taiSanDuongSuRepository.findById(taiSanDuongSu.getId()).orElseThrow();
    }

    protected void assertPersistedTaiSanDuongSuToMatchAllProperties(TaiSanDuongSu expectedTaiSanDuongSu) {
        assertTaiSanDuongSuAllPropertiesEquals(expectedTaiSanDuongSu, getPersistedTaiSanDuongSu(expectedTaiSanDuongSu));
    }

    protected void assertPersistedTaiSanDuongSuToMatchUpdatableProperties(TaiSanDuongSu expectedTaiSanDuongSu) {
        assertTaiSanDuongSuAllUpdatablePropertiesEquals(expectedTaiSanDuongSu, getPersistedTaiSanDuongSu(expectedTaiSanDuongSu));
    }
}
