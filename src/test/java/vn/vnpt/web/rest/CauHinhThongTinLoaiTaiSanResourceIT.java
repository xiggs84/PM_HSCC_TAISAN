package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.CauHinhThongTinLoaiTaiSanAsserts.*;
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
import vn.vnpt.domain.CauHinhThongTinLoaiTaiSan;
import vn.vnpt.repository.CauHinhThongTinLoaiTaiSanRepository;
import vn.vnpt.service.dto.CauHinhThongTinLoaiTaiSanDTO;
import vn.vnpt.service.mapper.CauHinhThongTinLoaiTaiSanMapper;

/**
 * Integration tests for the {@link CauHinhThongTinLoaiTaiSanResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CauHinhThongTinLoaiTaiSanResourceIT {

    private static final Long DEFAULT_ID_CAU_HINH = 1L;
    private static final Long UPDATED_ID_CAU_HINH = 2L;

    private static final String DEFAULT_NOI_DUNG = "AAAAAAAAAA";
    private static final String UPDATED_NOI_DUNG = "BBBBBBBBBB";

    private static final String DEFAULT_JAVASCRIPT = "AAAAAAAAAA";
    private static final String UPDATED_JAVASCRIPT = "BBBBBBBBBB";

    private static final String DEFAULT_CSS = "AAAAAAAAAA";
    private static final String UPDATED_CSS = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_LOAI_TS = 1L;
    private static final Long UPDATED_ID_LOAI_TS = 2L;

    private static final Long DEFAULT_ID_DON_VI = 1L;
    private static final Long UPDATED_ID_DON_VI = 2L;

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;

    private static final String DEFAULT_XML = "AAAAAAAAAA";
    private static final String UPDATED_XML = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/cau-hinh-thong-tin-loai-tai-sans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CauHinhThongTinLoaiTaiSanRepository cauHinhThongTinLoaiTaiSanRepository;

    @Autowired
    private CauHinhThongTinLoaiTaiSanMapper cauHinhThongTinLoaiTaiSanMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCauHinhThongTinLoaiTaiSanMockMvc;

    private CauHinhThongTinLoaiTaiSan cauHinhThongTinLoaiTaiSan;

    private CauHinhThongTinLoaiTaiSan insertedCauHinhThongTinLoaiTaiSan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CauHinhThongTinLoaiTaiSan createEntity(EntityManager em) {
        CauHinhThongTinLoaiTaiSan cauHinhThongTinLoaiTaiSan = new CauHinhThongTinLoaiTaiSan()
            .idCauHinh(DEFAULT_ID_CAU_HINH)
            .noiDung(DEFAULT_NOI_DUNG)
            .javascript(DEFAULT_JAVASCRIPT)
            .css(DEFAULT_CSS)
            .idLoaiTs(DEFAULT_ID_LOAI_TS)
            .idDonVi(DEFAULT_ID_DON_VI)
            .trangThai(DEFAULT_TRANG_THAI)
            .xml(DEFAULT_XML);
        return cauHinhThongTinLoaiTaiSan;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CauHinhThongTinLoaiTaiSan createUpdatedEntity(EntityManager em) {
        CauHinhThongTinLoaiTaiSan cauHinhThongTinLoaiTaiSan = new CauHinhThongTinLoaiTaiSan()
            .idCauHinh(UPDATED_ID_CAU_HINH)
            .noiDung(UPDATED_NOI_DUNG)
            .javascript(UPDATED_JAVASCRIPT)
            .css(UPDATED_CSS)
            .idLoaiTs(UPDATED_ID_LOAI_TS)
            .idDonVi(UPDATED_ID_DON_VI)
            .trangThai(UPDATED_TRANG_THAI)
            .xml(UPDATED_XML);
        return cauHinhThongTinLoaiTaiSan;
    }

    @BeforeEach
    public void initTest() {
        cauHinhThongTinLoaiTaiSan = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedCauHinhThongTinLoaiTaiSan != null) {
            cauHinhThongTinLoaiTaiSanRepository.delete(insertedCauHinhThongTinLoaiTaiSan);
            insertedCauHinhThongTinLoaiTaiSan = null;
        }
    }

    @Test
    @Transactional
    void createCauHinhThongTinLoaiTaiSan() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the CauHinhThongTinLoaiTaiSan
        CauHinhThongTinLoaiTaiSanDTO cauHinhThongTinLoaiTaiSanDTO = cauHinhThongTinLoaiTaiSanMapper.toDto(cauHinhThongTinLoaiTaiSan);
        var returnedCauHinhThongTinLoaiTaiSanDTO = om.readValue(
            restCauHinhThongTinLoaiTaiSanMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cauHinhThongTinLoaiTaiSanDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CauHinhThongTinLoaiTaiSanDTO.class
        );

        // Validate the CauHinhThongTinLoaiTaiSan in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedCauHinhThongTinLoaiTaiSan = cauHinhThongTinLoaiTaiSanMapper.toEntity(returnedCauHinhThongTinLoaiTaiSanDTO);
        assertCauHinhThongTinLoaiTaiSanUpdatableFieldsEquals(
            returnedCauHinhThongTinLoaiTaiSan,
            getPersistedCauHinhThongTinLoaiTaiSan(returnedCauHinhThongTinLoaiTaiSan)
        );

        insertedCauHinhThongTinLoaiTaiSan = returnedCauHinhThongTinLoaiTaiSan;
    }

    @Test
    @Transactional
    void createCauHinhThongTinLoaiTaiSanWithExistingId() throws Exception {
        // Create the CauHinhThongTinLoaiTaiSan with an existing ID
        cauHinhThongTinLoaiTaiSan.setId(1L);
        CauHinhThongTinLoaiTaiSanDTO cauHinhThongTinLoaiTaiSanDTO = cauHinhThongTinLoaiTaiSanMapper.toDto(cauHinhThongTinLoaiTaiSan);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCauHinhThongTinLoaiTaiSanMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cauHinhThongTinLoaiTaiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CauHinhThongTinLoaiTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinLoaiTaiSans() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinLoaiTaiSan = cauHinhThongTinLoaiTaiSanRepository.saveAndFlush(cauHinhThongTinLoaiTaiSan);

        // Get all the cauHinhThongTinLoaiTaiSanList
        restCauHinhThongTinLoaiTaiSanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cauHinhThongTinLoaiTaiSan.getId().intValue())))
            .andExpect(jsonPath("$.[*].idCauHinh").value(hasItem(DEFAULT_ID_CAU_HINH.intValue())))
            .andExpect(jsonPath("$.[*].noiDung").value(hasItem(DEFAULT_NOI_DUNG)))
            .andExpect(jsonPath("$.[*].javascript").value(hasItem(DEFAULT_JAVASCRIPT)))
            .andExpect(jsonPath("$.[*].css").value(hasItem(DEFAULT_CSS)))
            .andExpect(jsonPath("$.[*].idLoaiTs").value(hasItem(DEFAULT_ID_LOAI_TS.intValue())))
            .andExpect(jsonPath("$.[*].idDonVi").value(hasItem(DEFAULT_ID_DON_VI.intValue())))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())))
            .andExpect(jsonPath("$.[*].xml").value(hasItem(DEFAULT_XML)));
    }

    @Test
    @Transactional
    void getCauHinhThongTinLoaiTaiSan() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinLoaiTaiSan = cauHinhThongTinLoaiTaiSanRepository.saveAndFlush(cauHinhThongTinLoaiTaiSan);

        // Get the cauHinhThongTinLoaiTaiSan
        restCauHinhThongTinLoaiTaiSanMockMvc
            .perform(get(ENTITY_API_URL_ID, cauHinhThongTinLoaiTaiSan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cauHinhThongTinLoaiTaiSan.getId().intValue()))
            .andExpect(jsonPath("$.idCauHinh").value(DEFAULT_ID_CAU_HINH.intValue()))
            .andExpect(jsonPath("$.noiDung").value(DEFAULT_NOI_DUNG))
            .andExpect(jsonPath("$.javascript").value(DEFAULT_JAVASCRIPT))
            .andExpect(jsonPath("$.css").value(DEFAULT_CSS))
            .andExpect(jsonPath("$.idLoaiTs").value(DEFAULT_ID_LOAI_TS.intValue()))
            .andExpect(jsonPath("$.idDonVi").value(DEFAULT_ID_DON_VI.intValue()))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI.intValue()))
            .andExpect(jsonPath("$.xml").value(DEFAULT_XML));
    }

    @Test
    @Transactional
    void getNonExistingCauHinhThongTinLoaiTaiSan() throws Exception {
        // Get the cauHinhThongTinLoaiTaiSan
        restCauHinhThongTinLoaiTaiSanMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCauHinhThongTinLoaiTaiSan() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinLoaiTaiSan = cauHinhThongTinLoaiTaiSanRepository.saveAndFlush(cauHinhThongTinLoaiTaiSan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cauHinhThongTinLoaiTaiSan
        CauHinhThongTinLoaiTaiSan updatedCauHinhThongTinLoaiTaiSan = cauHinhThongTinLoaiTaiSanRepository
            .findById(cauHinhThongTinLoaiTaiSan.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedCauHinhThongTinLoaiTaiSan are not directly saved in db
        em.detach(updatedCauHinhThongTinLoaiTaiSan);
        updatedCauHinhThongTinLoaiTaiSan
            .idCauHinh(UPDATED_ID_CAU_HINH)
            .noiDung(UPDATED_NOI_DUNG)
            .javascript(UPDATED_JAVASCRIPT)
            .css(UPDATED_CSS)
            .idLoaiTs(UPDATED_ID_LOAI_TS)
            .idDonVi(UPDATED_ID_DON_VI)
            .trangThai(UPDATED_TRANG_THAI)
            .xml(UPDATED_XML);
        CauHinhThongTinLoaiTaiSanDTO cauHinhThongTinLoaiTaiSanDTO = cauHinhThongTinLoaiTaiSanMapper.toDto(updatedCauHinhThongTinLoaiTaiSan);

        restCauHinhThongTinLoaiTaiSanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, cauHinhThongTinLoaiTaiSanDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(cauHinhThongTinLoaiTaiSanDTO))
            )
            .andExpect(status().isOk());

        // Validate the CauHinhThongTinLoaiTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCauHinhThongTinLoaiTaiSanToMatchAllProperties(updatedCauHinhThongTinLoaiTaiSan);
    }

    @Test
    @Transactional
    void putNonExistingCauHinhThongTinLoaiTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cauHinhThongTinLoaiTaiSan.setId(longCount.incrementAndGet());

        // Create the CauHinhThongTinLoaiTaiSan
        CauHinhThongTinLoaiTaiSanDTO cauHinhThongTinLoaiTaiSanDTO = cauHinhThongTinLoaiTaiSanMapper.toDto(cauHinhThongTinLoaiTaiSan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCauHinhThongTinLoaiTaiSanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, cauHinhThongTinLoaiTaiSanDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(cauHinhThongTinLoaiTaiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CauHinhThongTinLoaiTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCauHinhThongTinLoaiTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cauHinhThongTinLoaiTaiSan.setId(longCount.incrementAndGet());

        // Create the CauHinhThongTinLoaiTaiSan
        CauHinhThongTinLoaiTaiSanDTO cauHinhThongTinLoaiTaiSanDTO = cauHinhThongTinLoaiTaiSanMapper.toDto(cauHinhThongTinLoaiTaiSan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCauHinhThongTinLoaiTaiSanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(cauHinhThongTinLoaiTaiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CauHinhThongTinLoaiTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCauHinhThongTinLoaiTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cauHinhThongTinLoaiTaiSan.setId(longCount.incrementAndGet());

        // Create the CauHinhThongTinLoaiTaiSan
        CauHinhThongTinLoaiTaiSanDTO cauHinhThongTinLoaiTaiSanDTO = cauHinhThongTinLoaiTaiSanMapper.toDto(cauHinhThongTinLoaiTaiSan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCauHinhThongTinLoaiTaiSanMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cauHinhThongTinLoaiTaiSanDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CauHinhThongTinLoaiTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCauHinhThongTinLoaiTaiSanWithPatch() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinLoaiTaiSan = cauHinhThongTinLoaiTaiSanRepository.saveAndFlush(cauHinhThongTinLoaiTaiSan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cauHinhThongTinLoaiTaiSan using partial update
        CauHinhThongTinLoaiTaiSan partialUpdatedCauHinhThongTinLoaiTaiSan = new CauHinhThongTinLoaiTaiSan();
        partialUpdatedCauHinhThongTinLoaiTaiSan.setId(cauHinhThongTinLoaiTaiSan.getId());

        partialUpdatedCauHinhThongTinLoaiTaiSan.idLoaiTs(UPDATED_ID_LOAI_TS).xml(UPDATED_XML);

        restCauHinhThongTinLoaiTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCauHinhThongTinLoaiTaiSan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCauHinhThongTinLoaiTaiSan))
            )
            .andExpect(status().isOk());

        // Validate the CauHinhThongTinLoaiTaiSan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCauHinhThongTinLoaiTaiSanUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCauHinhThongTinLoaiTaiSan, cauHinhThongTinLoaiTaiSan),
            getPersistedCauHinhThongTinLoaiTaiSan(cauHinhThongTinLoaiTaiSan)
        );
    }

    @Test
    @Transactional
    void fullUpdateCauHinhThongTinLoaiTaiSanWithPatch() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinLoaiTaiSan = cauHinhThongTinLoaiTaiSanRepository.saveAndFlush(cauHinhThongTinLoaiTaiSan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cauHinhThongTinLoaiTaiSan using partial update
        CauHinhThongTinLoaiTaiSan partialUpdatedCauHinhThongTinLoaiTaiSan = new CauHinhThongTinLoaiTaiSan();
        partialUpdatedCauHinhThongTinLoaiTaiSan.setId(cauHinhThongTinLoaiTaiSan.getId());

        partialUpdatedCauHinhThongTinLoaiTaiSan
            .idCauHinh(UPDATED_ID_CAU_HINH)
            .noiDung(UPDATED_NOI_DUNG)
            .javascript(UPDATED_JAVASCRIPT)
            .css(UPDATED_CSS)
            .idLoaiTs(UPDATED_ID_LOAI_TS)
            .idDonVi(UPDATED_ID_DON_VI)
            .trangThai(UPDATED_TRANG_THAI)
            .xml(UPDATED_XML);

        restCauHinhThongTinLoaiTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCauHinhThongTinLoaiTaiSan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCauHinhThongTinLoaiTaiSan))
            )
            .andExpect(status().isOk());

        // Validate the CauHinhThongTinLoaiTaiSan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCauHinhThongTinLoaiTaiSanUpdatableFieldsEquals(
            partialUpdatedCauHinhThongTinLoaiTaiSan,
            getPersistedCauHinhThongTinLoaiTaiSan(partialUpdatedCauHinhThongTinLoaiTaiSan)
        );
    }

    @Test
    @Transactional
    void patchNonExistingCauHinhThongTinLoaiTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cauHinhThongTinLoaiTaiSan.setId(longCount.incrementAndGet());

        // Create the CauHinhThongTinLoaiTaiSan
        CauHinhThongTinLoaiTaiSanDTO cauHinhThongTinLoaiTaiSanDTO = cauHinhThongTinLoaiTaiSanMapper.toDto(cauHinhThongTinLoaiTaiSan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCauHinhThongTinLoaiTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, cauHinhThongTinLoaiTaiSanDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(cauHinhThongTinLoaiTaiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CauHinhThongTinLoaiTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCauHinhThongTinLoaiTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cauHinhThongTinLoaiTaiSan.setId(longCount.incrementAndGet());

        // Create the CauHinhThongTinLoaiTaiSan
        CauHinhThongTinLoaiTaiSanDTO cauHinhThongTinLoaiTaiSanDTO = cauHinhThongTinLoaiTaiSanMapper.toDto(cauHinhThongTinLoaiTaiSan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCauHinhThongTinLoaiTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(cauHinhThongTinLoaiTaiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CauHinhThongTinLoaiTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCauHinhThongTinLoaiTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cauHinhThongTinLoaiTaiSan.setId(longCount.incrementAndGet());

        // Create the CauHinhThongTinLoaiTaiSan
        CauHinhThongTinLoaiTaiSanDTO cauHinhThongTinLoaiTaiSanDTO = cauHinhThongTinLoaiTaiSanMapper.toDto(cauHinhThongTinLoaiTaiSan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCauHinhThongTinLoaiTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(cauHinhThongTinLoaiTaiSanDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CauHinhThongTinLoaiTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCauHinhThongTinLoaiTaiSan() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinLoaiTaiSan = cauHinhThongTinLoaiTaiSanRepository.saveAndFlush(cauHinhThongTinLoaiTaiSan);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the cauHinhThongTinLoaiTaiSan
        restCauHinhThongTinLoaiTaiSanMockMvc
            .perform(delete(ENTITY_API_URL_ID, cauHinhThongTinLoaiTaiSan.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return cauHinhThongTinLoaiTaiSanRepository.count();
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

    protected CauHinhThongTinLoaiTaiSan getPersistedCauHinhThongTinLoaiTaiSan(CauHinhThongTinLoaiTaiSan cauHinhThongTinLoaiTaiSan) {
        return cauHinhThongTinLoaiTaiSanRepository.findById(cauHinhThongTinLoaiTaiSan.getId()).orElseThrow();
    }

    protected void assertPersistedCauHinhThongTinLoaiTaiSanToMatchAllProperties(
        CauHinhThongTinLoaiTaiSan expectedCauHinhThongTinLoaiTaiSan
    ) {
        assertCauHinhThongTinLoaiTaiSanAllPropertiesEquals(
            expectedCauHinhThongTinLoaiTaiSan,
            getPersistedCauHinhThongTinLoaiTaiSan(expectedCauHinhThongTinLoaiTaiSan)
        );
    }

    protected void assertPersistedCauHinhThongTinLoaiTaiSanToMatchUpdatableProperties(
        CauHinhThongTinLoaiTaiSan expectedCauHinhThongTinLoaiTaiSan
    ) {
        assertCauHinhThongTinLoaiTaiSanAllUpdatablePropertiesEquals(
            expectedCauHinhThongTinLoaiTaiSan,
            getPersistedCauHinhThongTinLoaiTaiSan(expectedCauHinhThongTinLoaiTaiSan)
        );
    }
}
