package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.TinhTrangTaiSanAsserts.*;
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
import vn.vnpt.domain.TinhTrangTaiSan;
import vn.vnpt.repository.TinhTrangTaiSanRepository;
import vn.vnpt.service.dto.TinhTrangTaiSanDTO;
import vn.vnpt.service.mapper.TinhTrangTaiSanMapper;

/**
 * Integration tests for the {@link TinhTrangTaiSanResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TinhTrangTaiSanResourceIT {

    private static final Long DEFAULT_ID_TINH_TRANG = 1L;
    private static final Long UPDATED_ID_TINH_TRANG = 2L;

    private static final String DEFAULT_DIEN_GIAI = "AAAAAAAAAA";
    private static final String UPDATED_DIEN_GIAI = "BBBBBBBBBB";

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;

    private static final String ENTITY_API_URL = "/api/tinh-trang-tai-sans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TinhTrangTaiSanRepository tinhTrangTaiSanRepository;

    @Autowired
    private TinhTrangTaiSanMapper tinhTrangTaiSanMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTinhTrangTaiSanMockMvc;

    private TinhTrangTaiSan tinhTrangTaiSan;

    private TinhTrangTaiSan insertedTinhTrangTaiSan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TinhTrangTaiSan createEntity(EntityManager em) {
        TinhTrangTaiSan tinhTrangTaiSan = new TinhTrangTaiSan()
            .idTinhTrang(DEFAULT_ID_TINH_TRANG)
            .dienGiai(DEFAULT_DIEN_GIAI)
            .trangThai(DEFAULT_TRANG_THAI);
        return tinhTrangTaiSan;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TinhTrangTaiSan createUpdatedEntity(EntityManager em) {
        TinhTrangTaiSan tinhTrangTaiSan = new TinhTrangTaiSan()
            .idTinhTrang(UPDATED_ID_TINH_TRANG)
            .dienGiai(UPDATED_DIEN_GIAI)
            .trangThai(UPDATED_TRANG_THAI);
        return tinhTrangTaiSan;
    }

    @BeforeEach
    public void initTest() {
        tinhTrangTaiSan = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedTinhTrangTaiSan != null) {
            tinhTrangTaiSanRepository.delete(insertedTinhTrangTaiSan);
            insertedTinhTrangTaiSan = null;
        }
    }

    @Test
    @Transactional
    void createTinhTrangTaiSan() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the TinhTrangTaiSan
        TinhTrangTaiSanDTO tinhTrangTaiSanDTO = tinhTrangTaiSanMapper.toDto(tinhTrangTaiSan);
        var returnedTinhTrangTaiSanDTO = om.readValue(
            restTinhTrangTaiSanMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(tinhTrangTaiSanDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            TinhTrangTaiSanDTO.class
        );

        // Validate the TinhTrangTaiSan in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedTinhTrangTaiSan = tinhTrangTaiSanMapper.toEntity(returnedTinhTrangTaiSanDTO);
        assertTinhTrangTaiSanUpdatableFieldsEquals(returnedTinhTrangTaiSan, getPersistedTinhTrangTaiSan(returnedTinhTrangTaiSan));

        insertedTinhTrangTaiSan = returnedTinhTrangTaiSan;
    }

    @Test
    @Transactional
    void createTinhTrangTaiSanWithExistingId() throws Exception {
        // Create the TinhTrangTaiSan with an existing ID
        tinhTrangTaiSan.setId(1L);
        TinhTrangTaiSanDTO tinhTrangTaiSanDTO = tinhTrangTaiSanMapper.toDto(tinhTrangTaiSan);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTinhTrangTaiSanMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(tinhTrangTaiSanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TinhTrangTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTinhTrangTaiSans() throws Exception {
        // Initialize the database
        insertedTinhTrangTaiSan = tinhTrangTaiSanRepository.saveAndFlush(tinhTrangTaiSan);

        // Get all the tinhTrangTaiSanList
        restTinhTrangTaiSanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tinhTrangTaiSan.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTinhTrang").value(hasItem(DEFAULT_ID_TINH_TRANG.intValue())))
            .andExpect(jsonPath("$.[*].dienGiai").value(hasItem(DEFAULT_DIEN_GIAI)))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())));
    }

    @Test
    @Transactional
    void getTinhTrangTaiSan() throws Exception {
        // Initialize the database
        insertedTinhTrangTaiSan = tinhTrangTaiSanRepository.saveAndFlush(tinhTrangTaiSan);

        // Get the tinhTrangTaiSan
        restTinhTrangTaiSanMockMvc
            .perform(get(ENTITY_API_URL_ID, tinhTrangTaiSan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tinhTrangTaiSan.getId().intValue()))
            .andExpect(jsonPath("$.idTinhTrang").value(DEFAULT_ID_TINH_TRANG.intValue()))
            .andExpect(jsonPath("$.dienGiai").value(DEFAULT_DIEN_GIAI))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTinhTrangTaiSan() throws Exception {
        // Get the tinhTrangTaiSan
        restTinhTrangTaiSanMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTinhTrangTaiSan() throws Exception {
        // Initialize the database
        insertedTinhTrangTaiSan = tinhTrangTaiSanRepository.saveAndFlush(tinhTrangTaiSan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the tinhTrangTaiSan
        TinhTrangTaiSan updatedTinhTrangTaiSan = tinhTrangTaiSanRepository.findById(tinhTrangTaiSan.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedTinhTrangTaiSan are not directly saved in db
        em.detach(updatedTinhTrangTaiSan);
        updatedTinhTrangTaiSan.idTinhTrang(UPDATED_ID_TINH_TRANG).dienGiai(UPDATED_DIEN_GIAI).trangThai(UPDATED_TRANG_THAI);
        TinhTrangTaiSanDTO tinhTrangTaiSanDTO = tinhTrangTaiSanMapper.toDto(updatedTinhTrangTaiSan);

        restTinhTrangTaiSanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tinhTrangTaiSanDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(tinhTrangTaiSanDTO))
            )
            .andExpect(status().isOk());

        // Validate the TinhTrangTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedTinhTrangTaiSanToMatchAllProperties(updatedTinhTrangTaiSan);
    }

    @Test
    @Transactional
    void putNonExistingTinhTrangTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        tinhTrangTaiSan.setId(longCount.incrementAndGet());

        // Create the TinhTrangTaiSan
        TinhTrangTaiSanDTO tinhTrangTaiSanDTO = tinhTrangTaiSanMapper.toDto(tinhTrangTaiSan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTinhTrangTaiSanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tinhTrangTaiSanDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(tinhTrangTaiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TinhTrangTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTinhTrangTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        tinhTrangTaiSan.setId(longCount.incrementAndGet());

        // Create the TinhTrangTaiSan
        TinhTrangTaiSanDTO tinhTrangTaiSanDTO = tinhTrangTaiSanMapper.toDto(tinhTrangTaiSan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTinhTrangTaiSanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(tinhTrangTaiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TinhTrangTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTinhTrangTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        tinhTrangTaiSan.setId(longCount.incrementAndGet());

        // Create the TinhTrangTaiSan
        TinhTrangTaiSanDTO tinhTrangTaiSanDTO = tinhTrangTaiSanMapper.toDto(tinhTrangTaiSan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTinhTrangTaiSanMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(tinhTrangTaiSanDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TinhTrangTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTinhTrangTaiSanWithPatch() throws Exception {
        // Initialize the database
        insertedTinhTrangTaiSan = tinhTrangTaiSanRepository.saveAndFlush(tinhTrangTaiSan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the tinhTrangTaiSan using partial update
        TinhTrangTaiSan partialUpdatedTinhTrangTaiSan = new TinhTrangTaiSan();
        partialUpdatedTinhTrangTaiSan.setId(tinhTrangTaiSan.getId());

        partialUpdatedTinhTrangTaiSan.idTinhTrang(UPDATED_ID_TINH_TRANG).trangThai(UPDATED_TRANG_THAI);

        restTinhTrangTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTinhTrangTaiSan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTinhTrangTaiSan))
            )
            .andExpect(status().isOk());

        // Validate the TinhTrangTaiSan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTinhTrangTaiSanUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedTinhTrangTaiSan, tinhTrangTaiSan),
            getPersistedTinhTrangTaiSan(tinhTrangTaiSan)
        );
    }

    @Test
    @Transactional
    void fullUpdateTinhTrangTaiSanWithPatch() throws Exception {
        // Initialize the database
        insertedTinhTrangTaiSan = tinhTrangTaiSanRepository.saveAndFlush(tinhTrangTaiSan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the tinhTrangTaiSan using partial update
        TinhTrangTaiSan partialUpdatedTinhTrangTaiSan = new TinhTrangTaiSan();
        partialUpdatedTinhTrangTaiSan.setId(tinhTrangTaiSan.getId());

        partialUpdatedTinhTrangTaiSan.idTinhTrang(UPDATED_ID_TINH_TRANG).dienGiai(UPDATED_DIEN_GIAI).trangThai(UPDATED_TRANG_THAI);

        restTinhTrangTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTinhTrangTaiSan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTinhTrangTaiSan))
            )
            .andExpect(status().isOk());

        // Validate the TinhTrangTaiSan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTinhTrangTaiSanUpdatableFieldsEquals(
            partialUpdatedTinhTrangTaiSan,
            getPersistedTinhTrangTaiSan(partialUpdatedTinhTrangTaiSan)
        );
    }

    @Test
    @Transactional
    void patchNonExistingTinhTrangTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        tinhTrangTaiSan.setId(longCount.incrementAndGet());

        // Create the TinhTrangTaiSan
        TinhTrangTaiSanDTO tinhTrangTaiSanDTO = tinhTrangTaiSanMapper.toDto(tinhTrangTaiSan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTinhTrangTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tinhTrangTaiSanDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(tinhTrangTaiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TinhTrangTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTinhTrangTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        tinhTrangTaiSan.setId(longCount.incrementAndGet());

        // Create the TinhTrangTaiSan
        TinhTrangTaiSanDTO tinhTrangTaiSanDTO = tinhTrangTaiSanMapper.toDto(tinhTrangTaiSan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTinhTrangTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(tinhTrangTaiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TinhTrangTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTinhTrangTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        tinhTrangTaiSan.setId(longCount.incrementAndGet());

        // Create the TinhTrangTaiSan
        TinhTrangTaiSanDTO tinhTrangTaiSanDTO = tinhTrangTaiSanMapper.toDto(tinhTrangTaiSan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTinhTrangTaiSanMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(tinhTrangTaiSanDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TinhTrangTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTinhTrangTaiSan() throws Exception {
        // Initialize the database
        insertedTinhTrangTaiSan = tinhTrangTaiSanRepository.saveAndFlush(tinhTrangTaiSan);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the tinhTrangTaiSan
        restTinhTrangTaiSanMockMvc
            .perform(delete(ENTITY_API_URL_ID, tinhTrangTaiSan.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return tinhTrangTaiSanRepository.count();
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

    protected TinhTrangTaiSan getPersistedTinhTrangTaiSan(TinhTrangTaiSan tinhTrangTaiSan) {
        return tinhTrangTaiSanRepository.findById(tinhTrangTaiSan.getId()).orElseThrow();
    }

    protected void assertPersistedTinhTrangTaiSanToMatchAllProperties(TinhTrangTaiSan expectedTinhTrangTaiSan) {
        assertTinhTrangTaiSanAllPropertiesEquals(expectedTinhTrangTaiSan, getPersistedTinhTrangTaiSan(expectedTinhTrangTaiSan));
    }

    protected void assertPersistedTinhTrangTaiSanToMatchUpdatableProperties(TinhTrangTaiSan expectedTinhTrangTaiSan) {
        assertTinhTrangTaiSanAllUpdatablePropertiesEquals(expectedTinhTrangTaiSan, getPersistedTinhTrangTaiSan(expectedTinhTrangTaiSan));
    }
}
