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

    private static final String DEFAULT_DIEN_GIAI = "AAAAAAAAAA";
    private static final String UPDATED_DIEN_GIAI = "BBBBBBBBBB";

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;

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
