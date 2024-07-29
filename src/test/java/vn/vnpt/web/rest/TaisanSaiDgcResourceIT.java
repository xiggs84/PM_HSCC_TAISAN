package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.TaisanSaiDgcAsserts.*;
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
import vn.vnpt.domain.TaisanSaiDgc;
import vn.vnpt.repository.TaisanSaiDgcRepository;
import vn.vnpt.service.dto.TaisanSaiDgcDTO;
import vn.vnpt.service.mapper.TaisanSaiDgcMapper;

/**
 * Integration tests for the {@link TaisanSaiDgcResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TaisanSaiDgcResourceIT {

    private static final Long DEFAULT_ID_MASTER = 1L;
    private static final Long UPDATED_ID_MASTER = 2L;

    private static final String DEFAULT_THONG_TIN_TS = "AAAAAAAAAA";
    private static final String UPDATED_THONG_TIN_TS = "BBBBBBBBBB";

    private static final String DEFAULT_THONG_TIN_TS_DUNG = "AAAAAAAAAA";
    private static final String UPDATED_THONG_TIN_TS_DUNG = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/taisan-sai-dgcs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaisanSaiDgcRepository taisanSaiDgcRepository;

    @Autowired
    private TaisanSaiDgcMapper taisanSaiDgcMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTaisanSaiDgcMockMvc;

    private TaisanSaiDgc taisanSaiDgc;

    private TaisanSaiDgc insertedTaisanSaiDgc;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaisanSaiDgc createEntity(EntityManager em) {
        TaisanSaiDgc taisanSaiDgc = new TaisanSaiDgc()
            .idMaster(DEFAULT_ID_MASTER)
            .thongTinTs(DEFAULT_THONG_TIN_TS)
            .thongTinTsDung(DEFAULT_THONG_TIN_TS_DUNG);
        return taisanSaiDgc;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaisanSaiDgc createUpdatedEntity(EntityManager em) {
        TaisanSaiDgc taisanSaiDgc = new TaisanSaiDgc()
            .idMaster(UPDATED_ID_MASTER)
            .thongTinTs(UPDATED_THONG_TIN_TS)
            .thongTinTsDung(UPDATED_THONG_TIN_TS_DUNG);
        return taisanSaiDgc;
    }

    @BeforeEach
    public void initTest() {
        taisanSaiDgc = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedTaisanSaiDgc != null) {
            taisanSaiDgcRepository.delete(insertedTaisanSaiDgc);
            insertedTaisanSaiDgc = null;
        }
    }

    @Test
    @Transactional
    void createTaisanSaiDgc() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the TaisanSaiDgc
        TaisanSaiDgcDTO taisanSaiDgcDTO = taisanSaiDgcMapper.toDto(taisanSaiDgc);
        var returnedTaisanSaiDgcDTO = om.readValue(
            restTaisanSaiDgcMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(taisanSaiDgcDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            TaisanSaiDgcDTO.class
        );

        // Validate the TaisanSaiDgc in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedTaisanSaiDgc = taisanSaiDgcMapper.toEntity(returnedTaisanSaiDgcDTO);
        assertTaisanSaiDgcUpdatableFieldsEquals(returnedTaisanSaiDgc, getPersistedTaisanSaiDgc(returnedTaisanSaiDgc));

        insertedTaisanSaiDgc = returnedTaisanSaiDgc;
    }

    @Test
    @Transactional
    void createTaisanSaiDgcWithExistingId() throws Exception {
        // Create the TaisanSaiDgc with an existing ID
        taisanSaiDgc.setId(1L);
        TaisanSaiDgcDTO taisanSaiDgcDTO = taisanSaiDgcMapper.toDto(taisanSaiDgc);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaisanSaiDgcMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(taisanSaiDgcDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TaisanSaiDgc in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTaisanSaiDgcs() throws Exception {
        // Initialize the database
        insertedTaisanSaiDgc = taisanSaiDgcRepository.saveAndFlush(taisanSaiDgc);

        // Get all the taisanSaiDgcList
        restTaisanSaiDgcMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taisanSaiDgc.getId().intValue())))
            .andExpect(jsonPath("$.[*].idMaster").value(hasItem(DEFAULT_ID_MASTER.intValue())))
            .andExpect(jsonPath("$.[*].thongTinTs").value(hasItem(DEFAULT_THONG_TIN_TS)))
            .andExpect(jsonPath("$.[*].thongTinTsDung").value(hasItem(DEFAULT_THONG_TIN_TS_DUNG)));
    }

    @Test
    @Transactional
    void getTaisanSaiDgc() throws Exception {
        // Initialize the database
        insertedTaisanSaiDgc = taisanSaiDgcRepository.saveAndFlush(taisanSaiDgc);

        // Get the taisanSaiDgc
        restTaisanSaiDgcMockMvc
            .perform(get(ENTITY_API_URL_ID, taisanSaiDgc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(taisanSaiDgc.getId().intValue()))
            .andExpect(jsonPath("$.idMaster").value(DEFAULT_ID_MASTER.intValue()))
            .andExpect(jsonPath("$.thongTinTs").value(DEFAULT_THONG_TIN_TS))
            .andExpect(jsonPath("$.thongTinTsDung").value(DEFAULT_THONG_TIN_TS_DUNG));
    }

    @Test
    @Transactional
    void getNonExistingTaisanSaiDgc() throws Exception {
        // Get the taisanSaiDgc
        restTaisanSaiDgcMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTaisanSaiDgc() throws Exception {
        // Initialize the database
        insertedTaisanSaiDgc = taisanSaiDgcRepository.saveAndFlush(taisanSaiDgc);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the taisanSaiDgc
        TaisanSaiDgc updatedTaisanSaiDgc = taisanSaiDgcRepository.findById(taisanSaiDgc.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedTaisanSaiDgc are not directly saved in db
        em.detach(updatedTaisanSaiDgc);
        updatedTaisanSaiDgc.idMaster(UPDATED_ID_MASTER).thongTinTs(UPDATED_THONG_TIN_TS).thongTinTsDung(UPDATED_THONG_TIN_TS_DUNG);
        TaisanSaiDgcDTO taisanSaiDgcDTO = taisanSaiDgcMapper.toDto(updatedTaisanSaiDgc);

        restTaisanSaiDgcMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taisanSaiDgcDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(taisanSaiDgcDTO))
            )
            .andExpect(status().isOk());

        // Validate the TaisanSaiDgc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedTaisanSaiDgcToMatchAllProperties(updatedTaisanSaiDgc);
    }

    @Test
    @Transactional
    void putNonExistingTaisanSaiDgc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taisanSaiDgc.setId(longCount.incrementAndGet());

        // Create the TaisanSaiDgc
        TaisanSaiDgcDTO taisanSaiDgcDTO = taisanSaiDgcMapper.toDto(taisanSaiDgc);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaisanSaiDgcMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taisanSaiDgcDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(taisanSaiDgcDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaisanSaiDgc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTaisanSaiDgc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taisanSaiDgc.setId(longCount.incrementAndGet());

        // Create the TaisanSaiDgc
        TaisanSaiDgcDTO taisanSaiDgcDTO = taisanSaiDgcMapper.toDto(taisanSaiDgc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaisanSaiDgcMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(taisanSaiDgcDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaisanSaiDgc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTaisanSaiDgc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taisanSaiDgc.setId(longCount.incrementAndGet());

        // Create the TaisanSaiDgc
        TaisanSaiDgcDTO taisanSaiDgcDTO = taisanSaiDgcMapper.toDto(taisanSaiDgc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaisanSaiDgcMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(taisanSaiDgcDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaisanSaiDgc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTaisanSaiDgcWithPatch() throws Exception {
        // Initialize the database
        insertedTaisanSaiDgc = taisanSaiDgcRepository.saveAndFlush(taisanSaiDgc);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the taisanSaiDgc using partial update
        TaisanSaiDgc partialUpdatedTaisanSaiDgc = new TaisanSaiDgc();
        partialUpdatedTaisanSaiDgc.setId(taisanSaiDgc.getId());

        partialUpdatedTaisanSaiDgc.thongTinTs(UPDATED_THONG_TIN_TS);

        restTaisanSaiDgcMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaisanSaiDgc.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTaisanSaiDgc))
            )
            .andExpect(status().isOk());

        // Validate the TaisanSaiDgc in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTaisanSaiDgcUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedTaisanSaiDgc, taisanSaiDgc),
            getPersistedTaisanSaiDgc(taisanSaiDgc)
        );
    }

    @Test
    @Transactional
    void fullUpdateTaisanSaiDgcWithPatch() throws Exception {
        // Initialize the database
        insertedTaisanSaiDgc = taisanSaiDgcRepository.saveAndFlush(taisanSaiDgc);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the taisanSaiDgc using partial update
        TaisanSaiDgc partialUpdatedTaisanSaiDgc = new TaisanSaiDgc();
        partialUpdatedTaisanSaiDgc.setId(taisanSaiDgc.getId());

        partialUpdatedTaisanSaiDgc.idMaster(UPDATED_ID_MASTER).thongTinTs(UPDATED_THONG_TIN_TS).thongTinTsDung(UPDATED_THONG_TIN_TS_DUNG);

        restTaisanSaiDgcMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaisanSaiDgc.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTaisanSaiDgc))
            )
            .andExpect(status().isOk());

        // Validate the TaisanSaiDgc in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTaisanSaiDgcUpdatableFieldsEquals(partialUpdatedTaisanSaiDgc, getPersistedTaisanSaiDgc(partialUpdatedTaisanSaiDgc));
    }

    @Test
    @Transactional
    void patchNonExistingTaisanSaiDgc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taisanSaiDgc.setId(longCount.incrementAndGet());

        // Create the TaisanSaiDgc
        TaisanSaiDgcDTO taisanSaiDgcDTO = taisanSaiDgcMapper.toDto(taisanSaiDgc);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaisanSaiDgcMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, taisanSaiDgcDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(taisanSaiDgcDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaisanSaiDgc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTaisanSaiDgc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taisanSaiDgc.setId(longCount.incrementAndGet());

        // Create the TaisanSaiDgc
        TaisanSaiDgcDTO taisanSaiDgcDTO = taisanSaiDgcMapper.toDto(taisanSaiDgc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaisanSaiDgcMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(taisanSaiDgcDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaisanSaiDgc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTaisanSaiDgc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taisanSaiDgc.setId(longCount.incrementAndGet());

        // Create the TaisanSaiDgc
        TaisanSaiDgcDTO taisanSaiDgcDTO = taisanSaiDgcMapper.toDto(taisanSaiDgc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaisanSaiDgcMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(taisanSaiDgcDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaisanSaiDgc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTaisanSaiDgc() throws Exception {
        // Initialize the database
        insertedTaisanSaiDgc = taisanSaiDgcRepository.saveAndFlush(taisanSaiDgc);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the taisanSaiDgc
        restTaisanSaiDgcMockMvc
            .perform(delete(ENTITY_API_URL_ID, taisanSaiDgc.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return taisanSaiDgcRepository.count();
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

    protected TaisanSaiDgc getPersistedTaisanSaiDgc(TaisanSaiDgc taisanSaiDgc) {
        return taisanSaiDgcRepository.findById(taisanSaiDgc.getId()).orElseThrow();
    }

    protected void assertPersistedTaisanSaiDgcToMatchAllProperties(TaisanSaiDgc expectedTaisanSaiDgc) {
        assertTaisanSaiDgcAllPropertiesEquals(expectedTaisanSaiDgc, getPersistedTaisanSaiDgc(expectedTaisanSaiDgc));
    }

    protected void assertPersistedTaisanSaiDgcToMatchUpdatableProperties(TaisanSaiDgc expectedTaisanSaiDgc) {
        assertTaisanSaiDgcAllUpdatablePropertiesEquals(expectedTaisanSaiDgc, getPersistedTaisanSaiDgc(expectedTaisanSaiDgc));
    }
}
