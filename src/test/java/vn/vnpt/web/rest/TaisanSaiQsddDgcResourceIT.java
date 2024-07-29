package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.TaisanSaiQsddDgcAsserts.*;
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
import vn.vnpt.domain.TaisanSaiQsddDgc;
import vn.vnpt.repository.TaisanSaiQsddDgcRepository;
import vn.vnpt.service.dto.TaisanSaiQsddDgcDTO;
import vn.vnpt.service.mapper.TaisanSaiQsddDgcMapper;

/**
 * Integration tests for the {@link TaisanSaiQsddDgcResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TaisanSaiQsddDgcResourceIT {

    private static final Long DEFAULT_ID_MASTER = 1L;
    private static final Long UPDATED_ID_MASTER = 2L;

    private static final String DEFAULT_NOI_CAP_QSDD = "AAAAAAAAAA";
    private static final String UPDATED_NOI_CAP_QSDD = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/taisan-sai-qsdd-dgcs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaisanSaiQsddDgcRepository taisanSaiQsddDgcRepository;

    @Autowired
    private TaisanSaiQsddDgcMapper taisanSaiQsddDgcMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTaisanSaiQsddDgcMockMvc;

    private TaisanSaiQsddDgc taisanSaiQsddDgc;

    private TaisanSaiQsddDgc insertedTaisanSaiQsddDgc;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaisanSaiQsddDgc createEntity(EntityManager em) {
        TaisanSaiQsddDgc taisanSaiQsddDgc = new TaisanSaiQsddDgc().idMaster(DEFAULT_ID_MASTER).noiCapQsdd(DEFAULT_NOI_CAP_QSDD);
        return taisanSaiQsddDgc;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaisanSaiQsddDgc createUpdatedEntity(EntityManager em) {
        TaisanSaiQsddDgc taisanSaiQsddDgc = new TaisanSaiQsddDgc().idMaster(UPDATED_ID_MASTER).noiCapQsdd(UPDATED_NOI_CAP_QSDD);
        return taisanSaiQsddDgc;
    }

    @BeforeEach
    public void initTest() {
        taisanSaiQsddDgc = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedTaisanSaiQsddDgc != null) {
            taisanSaiQsddDgcRepository.delete(insertedTaisanSaiQsddDgc);
            insertedTaisanSaiQsddDgc = null;
        }
    }

    @Test
    @Transactional
    void createTaisanSaiQsddDgc() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the TaisanSaiQsddDgc
        TaisanSaiQsddDgcDTO taisanSaiQsddDgcDTO = taisanSaiQsddDgcMapper.toDto(taisanSaiQsddDgc);
        var returnedTaisanSaiQsddDgcDTO = om.readValue(
            restTaisanSaiQsddDgcMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(taisanSaiQsddDgcDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            TaisanSaiQsddDgcDTO.class
        );

        // Validate the TaisanSaiQsddDgc in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedTaisanSaiQsddDgc = taisanSaiQsddDgcMapper.toEntity(returnedTaisanSaiQsddDgcDTO);
        assertTaisanSaiQsddDgcUpdatableFieldsEquals(returnedTaisanSaiQsddDgc, getPersistedTaisanSaiQsddDgc(returnedTaisanSaiQsddDgc));

        insertedTaisanSaiQsddDgc = returnedTaisanSaiQsddDgc;
    }

    @Test
    @Transactional
    void createTaisanSaiQsddDgcWithExistingId() throws Exception {
        // Create the TaisanSaiQsddDgc with an existing ID
        taisanSaiQsddDgc.setId(1L);
        TaisanSaiQsddDgcDTO taisanSaiQsddDgcDTO = taisanSaiQsddDgcMapper.toDto(taisanSaiQsddDgc);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaisanSaiQsddDgcMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(taisanSaiQsddDgcDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TaisanSaiQsddDgc in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTaisanSaiQsddDgcs() throws Exception {
        // Initialize the database
        insertedTaisanSaiQsddDgc = taisanSaiQsddDgcRepository.saveAndFlush(taisanSaiQsddDgc);

        // Get all the taisanSaiQsddDgcList
        restTaisanSaiQsddDgcMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taisanSaiQsddDgc.getId().intValue())))
            .andExpect(jsonPath("$.[*].idMaster").value(hasItem(DEFAULT_ID_MASTER.intValue())))
            .andExpect(jsonPath("$.[*].noiCapQsdd").value(hasItem(DEFAULT_NOI_CAP_QSDD)));
    }

    @Test
    @Transactional
    void getTaisanSaiQsddDgc() throws Exception {
        // Initialize the database
        insertedTaisanSaiQsddDgc = taisanSaiQsddDgcRepository.saveAndFlush(taisanSaiQsddDgc);

        // Get the taisanSaiQsddDgc
        restTaisanSaiQsddDgcMockMvc
            .perform(get(ENTITY_API_URL_ID, taisanSaiQsddDgc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(taisanSaiQsddDgc.getId().intValue()))
            .andExpect(jsonPath("$.idMaster").value(DEFAULT_ID_MASTER.intValue()))
            .andExpect(jsonPath("$.noiCapQsdd").value(DEFAULT_NOI_CAP_QSDD));
    }

    @Test
    @Transactional
    void getNonExistingTaisanSaiQsddDgc() throws Exception {
        // Get the taisanSaiQsddDgc
        restTaisanSaiQsddDgcMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTaisanSaiQsddDgc() throws Exception {
        // Initialize the database
        insertedTaisanSaiQsddDgc = taisanSaiQsddDgcRepository.saveAndFlush(taisanSaiQsddDgc);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the taisanSaiQsddDgc
        TaisanSaiQsddDgc updatedTaisanSaiQsddDgc = taisanSaiQsddDgcRepository.findById(taisanSaiQsddDgc.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedTaisanSaiQsddDgc are not directly saved in db
        em.detach(updatedTaisanSaiQsddDgc);
        updatedTaisanSaiQsddDgc.idMaster(UPDATED_ID_MASTER).noiCapQsdd(UPDATED_NOI_CAP_QSDD);
        TaisanSaiQsddDgcDTO taisanSaiQsddDgcDTO = taisanSaiQsddDgcMapper.toDto(updatedTaisanSaiQsddDgc);

        restTaisanSaiQsddDgcMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taisanSaiQsddDgcDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(taisanSaiQsddDgcDTO))
            )
            .andExpect(status().isOk());

        // Validate the TaisanSaiQsddDgc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedTaisanSaiQsddDgcToMatchAllProperties(updatedTaisanSaiQsddDgc);
    }

    @Test
    @Transactional
    void putNonExistingTaisanSaiQsddDgc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taisanSaiQsddDgc.setId(longCount.incrementAndGet());

        // Create the TaisanSaiQsddDgc
        TaisanSaiQsddDgcDTO taisanSaiQsddDgcDTO = taisanSaiQsddDgcMapper.toDto(taisanSaiQsddDgc);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaisanSaiQsddDgcMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taisanSaiQsddDgcDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(taisanSaiQsddDgcDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaisanSaiQsddDgc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTaisanSaiQsddDgc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taisanSaiQsddDgc.setId(longCount.incrementAndGet());

        // Create the TaisanSaiQsddDgc
        TaisanSaiQsddDgcDTO taisanSaiQsddDgcDTO = taisanSaiQsddDgcMapper.toDto(taisanSaiQsddDgc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaisanSaiQsddDgcMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(taisanSaiQsddDgcDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaisanSaiQsddDgc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTaisanSaiQsddDgc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taisanSaiQsddDgc.setId(longCount.incrementAndGet());

        // Create the TaisanSaiQsddDgc
        TaisanSaiQsddDgcDTO taisanSaiQsddDgcDTO = taisanSaiQsddDgcMapper.toDto(taisanSaiQsddDgc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaisanSaiQsddDgcMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(taisanSaiQsddDgcDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaisanSaiQsddDgc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTaisanSaiQsddDgcWithPatch() throws Exception {
        // Initialize the database
        insertedTaisanSaiQsddDgc = taisanSaiQsddDgcRepository.saveAndFlush(taisanSaiQsddDgc);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the taisanSaiQsddDgc using partial update
        TaisanSaiQsddDgc partialUpdatedTaisanSaiQsddDgc = new TaisanSaiQsddDgc();
        partialUpdatedTaisanSaiQsddDgc.setId(taisanSaiQsddDgc.getId());

        partialUpdatedTaisanSaiQsddDgc.idMaster(UPDATED_ID_MASTER);

        restTaisanSaiQsddDgcMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaisanSaiQsddDgc.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTaisanSaiQsddDgc))
            )
            .andExpect(status().isOk());

        // Validate the TaisanSaiQsddDgc in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTaisanSaiQsddDgcUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedTaisanSaiQsddDgc, taisanSaiQsddDgc),
            getPersistedTaisanSaiQsddDgc(taisanSaiQsddDgc)
        );
    }

    @Test
    @Transactional
    void fullUpdateTaisanSaiQsddDgcWithPatch() throws Exception {
        // Initialize the database
        insertedTaisanSaiQsddDgc = taisanSaiQsddDgcRepository.saveAndFlush(taisanSaiQsddDgc);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the taisanSaiQsddDgc using partial update
        TaisanSaiQsddDgc partialUpdatedTaisanSaiQsddDgc = new TaisanSaiQsddDgc();
        partialUpdatedTaisanSaiQsddDgc.setId(taisanSaiQsddDgc.getId());

        partialUpdatedTaisanSaiQsddDgc.idMaster(UPDATED_ID_MASTER).noiCapQsdd(UPDATED_NOI_CAP_QSDD);

        restTaisanSaiQsddDgcMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaisanSaiQsddDgc.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTaisanSaiQsddDgc))
            )
            .andExpect(status().isOk());

        // Validate the TaisanSaiQsddDgc in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTaisanSaiQsddDgcUpdatableFieldsEquals(
            partialUpdatedTaisanSaiQsddDgc,
            getPersistedTaisanSaiQsddDgc(partialUpdatedTaisanSaiQsddDgc)
        );
    }

    @Test
    @Transactional
    void patchNonExistingTaisanSaiQsddDgc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taisanSaiQsddDgc.setId(longCount.incrementAndGet());

        // Create the TaisanSaiQsddDgc
        TaisanSaiQsddDgcDTO taisanSaiQsddDgcDTO = taisanSaiQsddDgcMapper.toDto(taisanSaiQsddDgc);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaisanSaiQsddDgcMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, taisanSaiQsddDgcDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(taisanSaiQsddDgcDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaisanSaiQsddDgc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTaisanSaiQsddDgc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taisanSaiQsddDgc.setId(longCount.incrementAndGet());

        // Create the TaisanSaiQsddDgc
        TaisanSaiQsddDgcDTO taisanSaiQsddDgcDTO = taisanSaiQsddDgcMapper.toDto(taisanSaiQsddDgc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaisanSaiQsddDgcMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(taisanSaiQsddDgcDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaisanSaiQsddDgc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTaisanSaiQsddDgc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taisanSaiQsddDgc.setId(longCount.incrementAndGet());

        // Create the TaisanSaiQsddDgc
        TaisanSaiQsddDgcDTO taisanSaiQsddDgcDTO = taisanSaiQsddDgcMapper.toDto(taisanSaiQsddDgc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaisanSaiQsddDgcMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(taisanSaiQsddDgcDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaisanSaiQsddDgc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTaisanSaiQsddDgc() throws Exception {
        // Initialize the database
        insertedTaisanSaiQsddDgc = taisanSaiQsddDgcRepository.saveAndFlush(taisanSaiQsddDgc);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the taisanSaiQsddDgc
        restTaisanSaiQsddDgcMockMvc
            .perform(delete(ENTITY_API_URL_ID, taisanSaiQsddDgc.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return taisanSaiQsddDgcRepository.count();
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

    protected TaisanSaiQsddDgc getPersistedTaisanSaiQsddDgc(TaisanSaiQsddDgc taisanSaiQsddDgc) {
        return taisanSaiQsddDgcRepository.findById(taisanSaiQsddDgc.getId()).orElseThrow();
    }

    protected void assertPersistedTaisanSaiQsddDgcToMatchAllProperties(TaisanSaiQsddDgc expectedTaisanSaiQsddDgc) {
        assertTaisanSaiQsddDgcAllPropertiesEquals(expectedTaisanSaiQsddDgc, getPersistedTaisanSaiQsddDgc(expectedTaisanSaiQsddDgc));
    }

    protected void assertPersistedTaisanSaiQsddDgcToMatchUpdatableProperties(TaisanSaiQsddDgc expectedTaisanSaiQsddDgc) {
        assertTaisanSaiQsddDgcAllUpdatablePropertiesEquals(
            expectedTaisanSaiQsddDgc,
            getPersistedTaisanSaiQsddDgc(expectedTaisanSaiQsddDgc)
        );
    }
}
