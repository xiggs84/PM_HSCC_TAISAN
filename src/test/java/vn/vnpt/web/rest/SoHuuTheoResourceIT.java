package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.SoHuuTheoAsserts.*;
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
import vn.vnpt.domain.SoHuuTheo;
import vn.vnpt.repository.SoHuuTheoRepository;
import vn.vnpt.service.dto.SoHuuTheoDTO;
import vn.vnpt.service.mapper.SoHuuTheoMapper;

/**
 * Integration tests for the {@link SoHuuTheoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SoHuuTheoResourceIT {

    private static final Long DEFAULT_ID_SO_HUU = 1L;
    private static final Long UPDATED_ID_SO_HUU = 2L;

    private static final String DEFAULT_DIEN_GIAI = "AAAAAAAAAA";
    private static final String UPDATED_DIEN_GIAI = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_GCN = "AAAAAAAAAA";
    private static final String UPDATED_TEN_GCN = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/so-huu-theos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private SoHuuTheoRepository soHuuTheoRepository;

    @Autowired
    private SoHuuTheoMapper soHuuTheoMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSoHuuTheoMockMvc;

    private SoHuuTheo soHuuTheo;

    private SoHuuTheo insertedSoHuuTheo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SoHuuTheo createEntity(EntityManager em) {
        SoHuuTheo soHuuTheo = new SoHuuTheo().idSoHuu(DEFAULT_ID_SO_HUU).dienGiai(DEFAULT_DIEN_GIAI).tenGcn(DEFAULT_TEN_GCN);
        return soHuuTheo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SoHuuTheo createUpdatedEntity(EntityManager em) {
        SoHuuTheo soHuuTheo = new SoHuuTheo().idSoHuu(UPDATED_ID_SO_HUU).dienGiai(UPDATED_DIEN_GIAI).tenGcn(UPDATED_TEN_GCN);
        return soHuuTheo;
    }

    @BeforeEach
    public void initTest() {
        soHuuTheo = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedSoHuuTheo != null) {
            soHuuTheoRepository.delete(insertedSoHuuTheo);
            insertedSoHuuTheo = null;
        }
    }

    @Test
    @Transactional
    void createSoHuuTheo() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the SoHuuTheo
        SoHuuTheoDTO soHuuTheoDTO = soHuuTheoMapper.toDto(soHuuTheo);
        var returnedSoHuuTheoDTO = om.readValue(
            restSoHuuTheoMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(soHuuTheoDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            SoHuuTheoDTO.class
        );

        // Validate the SoHuuTheo in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedSoHuuTheo = soHuuTheoMapper.toEntity(returnedSoHuuTheoDTO);
        assertSoHuuTheoUpdatableFieldsEquals(returnedSoHuuTheo, getPersistedSoHuuTheo(returnedSoHuuTheo));

        insertedSoHuuTheo = returnedSoHuuTheo;
    }

    @Test
    @Transactional
    void createSoHuuTheoWithExistingId() throws Exception {
        // Create the SoHuuTheo with an existing ID
        soHuuTheo.setId(1L);
        SoHuuTheoDTO soHuuTheoDTO = soHuuTheoMapper.toDto(soHuuTheo);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSoHuuTheoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(soHuuTheoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SoHuuTheo in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSoHuuTheos() throws Exception {
        // Initialize the database
        insertedSoHuuTheo = soHuuTheoRepository.saveAndFlush(soHuuTheo);

        // Get all the soHuuTheoList
        restSoHuuTheoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(soHuuTheo.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSoHuu").value(hasItem(DEFAULT_ID_SO_HUU.intValue())))
            .andExpect(jsonPath("$.[*].dienGiai").value(hasItem(DEFAULT_DIEN_GIAI)))
            .andExpect(jsonPath("$.[*].tenGcn").value(hasItem(DEFAULT_TEN_GCN)));
    }

    @Test
    @Transactional
    void getSoHuuTheo() throws Exception {
        // Initialize the database
        insertedSoHuuTheo = soHuuTheoRepository.saveAndFlush(soHuuTheo);

        // Get the soHuuTheo
        restSoHuuTheoMockMvc
            .perform(get(ENTITY_API_URL_ID, soHuuTheo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(soHuuTheo.getId().intValue()))
            .andExpect(jsonPath("$.idSoHuu").value(DEFAULT_ID_SO_HUU.intValue()))
            .andExpect(jsonPath("$.dienGiai").value(DEFAULT_DIEN_GIAI))
            .andExpect(jsonPath("$.tenGcn").value(DEFAULT_TEN_GCN));
    }

    @Test
    @Transactional
    void getNonExistingSoHuuTheo() throws Exception {
        // Get the soHuuTheo
        restSoHuuTheoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSoHuuTheo() throws Exception {
        // Initialize the database
        insertedSoHuuTheo = soHuuTheoRepository.saveAndFlush(soHuuTheo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the soHuuTheo
        SoHuuTheo updatedSoHuuTheo = soHuuTheoRepository.findById(soHuuTheo.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedSoHuuTheo are not directly saved in db
        em.detach(updatedSoHuuTheo);
        updatedSoHuuTheo.idSoHuu(UPDATED_ID_SO_HUU).dienGiai(UPDATED_DIEN_GIAI).tenGcn(UPDATED_TEN_GCN);
        SoHuuTheoDTO soHuuTheoDTO = soHuuTheoMapper.toDto(updatedSoHuuTheo);

        restSoHuuTheoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, soHuuTheoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(soHuuTheoDTO))
            )
            .andExpect(status().isOk());

        // Validate the SoHuuTheo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedSoHuuTheoToMatchAllProperties(updatedSoHuuTheo);
    }

    @Test
    @Transactional
    void putNonExistingSoHuuTheo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        soHuuTheo.setId(longCount.incrementAndGet());

        // Create the SoHuuTheo
        SoHuuTheoDTO soHuuTheoDTO = soHuuTheoMapper.toDto(soHuuTheo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSoHuuTheoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, soHuuTheoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(soHuuTheoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SoHuuTheo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSoHuuTheo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        soHuuTheo.setId(longCount.incrementAndGet());

        // Create the SoHuuTheo
        SoHuuTheoDTO soHuuTheoDTO = soHuuTheoMapper.toDto(soHuuTheo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSoHuuTheoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(soHuuTheoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SoHuuTheo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSoHuuTheo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        soHuuTheo.setId(longCount.incrementAndGet());

        // Create the SoHuuTheo
        SoHuuTheoDTO soHuuTheoDTO = soHuuTheoMapper.toDto(soHuuTheo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSoHuuTheoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(soHuuTheoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SoHuuTheo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSoHuuTheoWithPatch() throws Exception {
        // Initialize the database
        insertedSoHuuTheo = soHuuTheoRepository.saveAndFlush(soHuuTheo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the soHuuTheo using partial update
        SoHuuTheo partialUpdatedSoHuuTheo = new SoHuuTheo();
        partialUpdatedSoHuuTheo.setId(soHuuTheo.getId());

        partialUpdatedSoHuuTheo.dienGiai(UPDATED_DIEN_GIAI).tenGcn(UPDATED_TEN_GCN);

        restSoHuuTheoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSoHuuTheo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSoHuuTheo))
            )
            .andExpect(status().isOk());

        // Validate the SoHuuTheo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSoHuuTheoUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedSoHuuTheo, soHuuTheo),
            getPersistedSoHuuTheo(soHuuTheo)
        );
    }

    @Test
    @Transactional
    void fullUpdateSoHuuTheoWithPatch() throws Exception {
        // Initialize the database
        insertedSoHuuTheo = soHuuTheoRepository.saveAndFlush(soHuuTheo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the soHuuTheo using partial update
        SoHuuTheo partialUpdatedSoHuuTheo = new SoHuuTheo();
        partialUpdatedSoHuuTheo.setId(soHuuTheo.getId());

        partialUpdatedSoHuuTheo.idSoHuu(UPDATED_ID_SO_HUU).dienGiai(UPDATED_DIEN_GIAI).tenGcn(UPDATED_TEN_GCN);

        restSoHuuTheoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSoHuuTheo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSoHuuTheo))
            )
            .andExpect(status().isOk());

        // Validate the SoHuuTheo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSoHuuTheoUpdatableFieldsEquals(partialUpdatedSoHuuTheo, getPersistedSoHuuTheo(partialUpdatedSoHuuTheo));
    }

    @Test
    @Transactional
    void patchNonExistingSoHuuTheo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        soHuuTheo.setId(longCount.incrementAndGet());

        // Create the SoHuuTheo
        SoHuuTheoDTO soHuuTheoDTO = soHuuTheoMapper.toDto(soHuuTheo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSoHuuTheoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, soHuuTheoDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(soHuuTheoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SoHuuTheo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSoHuuTheo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        soHuuTheo.setId(longCount.incrementAndGet());

        // Create the SoHuuTheo
        SoHuuTheoDTO soHuuTheoDTO = soHuuTheoMapper.toDto(soHuuTheo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSoHuuTheoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(soHuuTheoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SoHuuTheo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSoHuuTheo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        soHuuTheo.setId(longCount.incrementAndGet());

        // Create the SoHuuTheo
        SoHuuTheoDTO soHuuTheoDTO = soHuuTheoMapper.toDto(soHuuTheo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSoHuuTheoMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(soHuuTheoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SoHuuTheo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSoHuuTheo() throws Exception {
        // Initialize the database
        insertedSoHuuTheo = soHuuTheoRepository.saveAndFlush(soHuuTheo);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the soHuuTheo
        restSoHuuTheoMockMvc
            .perform(delete(ENTITY_API_URL_ID, soHuuTheo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return soHuuTheoRepository.count();
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

    protected SoHuuTheo getPersistedSoHuuTheo(SoHuuTheo soHuuTheo) {
        return soHuuTheoRepository.findById(soHuuTheo.getId()).orElseThrow();
    }

    protected void assertPersistedSoHuuTheoToMatchAllProperties(SoHuuTheo expectedSoHuuTheo) {
        assertSoHuuTheoAllPropertiesEquals(expectedSoHuuTheo, getPersistedSoHuuTheo(expectedSoHuuTheo));
    }

    protected void assertPersistedSoHuuTheoToMatchUpdatableProperties(SoHuuTheo expectedSoHuuTheo) {
        assertSoHuuTheoAllUpdatablePropertiesEquals(expectedSoHuuTheo, getPersistedSoHuuTheo(expectedSoHuuTheo));
    }
}
