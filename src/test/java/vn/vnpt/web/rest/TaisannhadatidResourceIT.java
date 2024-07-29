package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.TaisannhadatidAsserts.*;
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
import vn.vnpt.domain.Taisannhadatid;
import vn.vnpt.repository.TaisannhadatidRepository;
import vn.vnpt.service.dto.TaisannhadatidDTO;
import vn.vnpt.service.mapper.TaisannhadatidMapper;

/**
 * Integration tests for the {@link TaisannhadatidResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TaisannhadatidResourceIT {

    private static final Long DEFAULT_ID_TAI_SAN = 1L;
    private static final Long UPDATED_ID_TAI_SAN = 2L;

    private static final String DEFAULT_THONG_TIN_TS = "AAAAAAAAAA";
    private static final String UPDATED_THONG_TIN_TS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/taisannhadatids";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaisannhadatidRepository taisannhadatidRepository;

    @Autowired
    private TaisannhadatidMapper taisannhadatidMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTaisannhadatidMockMvc;

    private Taisannhadatid taisannhadatid;

    private Taisannhadatid insertedTaisannhadatid;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Taisannhadatid createEntity(EntityManager em) {
        Taisannhadatid taisannhadatid = new Taisannhadatid().idTaiSan(DEFAULT_ID_TAI_SAN).thongTinTs(DEFAULT_THONG_TIN_TS);
        return taisannhadatid;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Taisannhadatid createUpdatedEntity(EntityManager em) {
        Taisannhadatid taisannhadatid = new Taisannhadatid().idTaiSan(UPDATED_ID_TAI_SAN).thongTinTs(UPDATED_THONG_TIN_TS);
        return taisannhadatid;
    }

    @BeforeEach
    public void initTest() {
        taisannhadatid = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedTaisannhadatid != null) {
            taisannhadatidRepository.delete(insertedTaisannhadatid);
            insertedTaisannhadatid = null;
        }
    }

    @Test
    @Transactional
    void createTaisannhadatid() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Taisannhadatid
        TaisannhadatidDTO taisannhadatidDTO = taisannhadatidMapper.toDto(taisannhadatid);
        var returnedTaisannhadatidDTO = om.readValue(
            restTaisannhadatidMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(taisannhadatidDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            TaisannhadatidDTO.class
        );

        // Validate the Taisannhadatid in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedTaisannhadatid = taisannhadatidMapper.toEntity(returnedTaisannhadatidDTO);
        assertTaisannhadatidUpdatableFieldsEquals(returnedTaisannhadatid, getPersistedTaisannhadatid(returnedTaisannhadatid));

        insertedTaisannhadatid = returnedTaisannhadatid;
    }

    @Test
    @Transactional
    void createTaisannhadatidWithExistingId() throws Exception {
        // Create the Taisannhadatid with an existing ID
        taisannhadatid.setId(1L);
        TaisannhadatidDTO taisannhadatidDTO = taisannhadatidMapper.toDto(taisannhadatid);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaisannhadatidMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(taisannhadatidDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Taisannhadatid in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTaisannhadatids() throws Exception {
        // Initialize the database
        insertedTaisannhadatid = taisannhadatidRepository.saveAndFlush(taisannhadatid);

        // Get all the taisannhadatidList
        restTaisannhadatidMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taisannhadatid.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTaiSan").value(hasItem(DEFAULT_ID_TAI_SAN.intValue())))
            .andExpect(jsonPath("$.[*].thongTinTs").value(hasItem(DEFAULT_THONG_TIN_TS)));
    }

    @Test
    @Transactional
    void getTaisannhadatid() throws Exception {
        // Initialize the database
        insertedTaisannhadatid = taisannhadatidRepository.saveAndFlush(taisannhadatid);

        // Get the taisannhadatid
        restTaisannhadatidMockMvc
            .perform(get(ENTITY_API_URL_ID, taisannhadatid.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(taisannhadatid.getId().intValue()))
            .andExpect(jsonPath("$.idTaiSan").value(DEFAULT_ID_TAI_SAN.intValue()))
            .andExpect(jsonPath("$.thongTinTs").value(DEFAULT_THONG_TIN_TS));
    }

    @Test
    @Transactional
    void getNonExistingTaisannhadatid() throws Exception {
        // Get the taisannhadatid
        restTaisannhadatidMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTaisannhadatid() throws Exception {
        // Initialize the database
        insertedTaisannhadatid = taisannhadatidRepository.saveAndFlush(taisannhadatid);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the taisannhadatid
        Taisannhadatid updatedTaisannhadatid = taisannhadatidRepository.findById(taisannhadatid.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedTaisannhadatid are not directly saved in db
        em.detach(updatedTaisannhadatid);
        updatedTaisannhadatid.idTaiSan(UPDATED_ID_TAI_SAN).thongTinTs(UPDATED_THONG_TIN_TS);
        TaisannhadatidDTO taisannhadatidDTO = taisannhadatidMapper.toDto(updatedTaisannhadatid);

        restTaisannhadatidMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taisannhadatidDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(taisannhadatidDTO))
            )
            .andExpect(status().isOk());

        // Validate the Taisannhadatid in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedTaisannhadatidToMatchAllProperties(updatedTaisannhadatid);
    }

    @Test
    @Transactional
    void putNonExistingTaisannhadatid() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taisannhadatid.setId(longCount.incrementAndGet());

        // Create the Taisannhadatid
        TaisannhadatidDTO taisannhadatidDTO = taisannhadatidMapper.toDto(taisannhadatid);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaisannhadatidMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taisannhadatidDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(taisannhadatidDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Taisannhadatid in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTaisannhadatid() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taisannhadatid.setId(longCount.incrementAndGet());

        // Create the Taisannhadatid
        TaisannhadatidDTO taisannhadatidDTO = taisannhadatidMapper.toDto(taisannhadatid);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaisannhadatidMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(taisannhadatidDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Taisannhadatid in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTaisannhadatid() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taisannhadatid.setId(longCount.incrementAndGet());

        // Create the Taisannhadatid
        TaisannhadatidDTO taisannhadatidDTO = taisannhadatidMapper.toDto(taisannhadatid);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaisannhadatidMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(taisannhadatidDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Taisannhadatid in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTaisannhadatidWithPatch() throws Exception {
        // Initialize the database
        insertedTaisannhadatid = taisannhadatidRepository.saveAndFlush(taisannhadatid);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the taisannhadatid using partial update
        Taisannhadatid partialUpdatedTaisannhadatid = new Taisannhadatid();
        partialUpdatedTaisannhadatid.setId(taisannhadatid.getId());

        restTaisannhadatidMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaisannhadatid.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTaisannhadatid))
            )
            .andExpect(status().isOk());

        // Validate the Taisannhadatid in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTaisannhadatidUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedTaisannhadatid, taisannhadatid),
            getPersistedTaisannhadatid(taisannhadatid)
        );
    }

    @Test
    @Transactional
    void fullUpdateTaisannhadatidWithPatch() throws Exception {
        // Initialize the database
        insertedTaisannhadatid = taisannhadatidRepository.saveAndFlush(taisannhadatid);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the taisannhadatid using partial update
        Taisannhadatid partialUpdatedTaisannhadatid = new Taisannhadatid();
        partialUpdatedTaisannhadatid.setId(taisannhadatid.getId());

        partialUpdatedTaisannhadatid.idTaiSan(UPDATED_ID_TAI_SAN).thongTinTs(UPDATED_THONG_TIN_TS);

        restTaisannhadatidMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaisannhadatid.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTaisannhadatid))
            )
            .andExpect(status().isOk());

        // Validate the Taisannhadatid in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTaisannhadatidUpdatableFieldsEquals(partialUpdatedTaisannhadatid, getPersistedTaisannhadatid(partialUpdatedTaisannhadatid));
    }

    @Test
    @Transactional
    void patchNonExistingTaisannhadatid() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taisannhadatid.setId(longCount.incrementAndGet());

        // Create the Taisannhadatid
        TaisannhadatidDTO taisannhadatidDTO = taisannhadatidMapper.toDto(taisannhadatid);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaisannhadatidMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, taisannhadatidDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(taisannhadatidDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Taisannhadatid in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTaisannhadatid() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taisannhadatid.setId(longCount.incrementAndGet());

        // Create the Taisannhadatid
        TaisannhadatidDTO taisannhadatidDTO = taisannhadatidMapper.toDto(taisannhadatid);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaisannhadatidMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(taisannhadatidDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Taisannhadatid in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTaisannhadatid() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taisannhadatid.setId(longCount.incrementAndGet());

        // Create the Taisannhadatid
        TaisannhadatidDTO taisannhadatidDTO = taisannhadatidMapper.toDto(taisannhadatid);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaisannhadatidMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(taisannhadatidDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Taisannhadatid in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTaisannhadatid() throws Exception {
        // Initialize the database
        insertedTaisannhadatid = taisannhadatidRepository.saveAndFlush(taisannhadatid);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the taisannhadatid
        restTaisannhadatidMockMvc
            .perform(delete(ENTITY_API_URL_ID, taisannhadatid.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return taisannhadatidRepository.count();
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

    protected Taisannhadatid getPersistedTaisannhadatid(Taisannhadatid taisannhadatid) {
        return taisannhadatidRepository.findById(taisannhadatid.getId()).orElseThrow();
    }

    protected void assertPersistedTaisannhadatidToMatchAllProperties(Taisannhadatid expectedTaisannhadatid) {
        assertTaisannhadatidAllPropertiesEquals(expectedTaisannhadatid, getPersistedTaisannhadatid(expectedTaisannhadatid));
    }

    protected void assertPersistedTaisannhadatidToMatchUpdatableProperties(Taisannhadatid expectedTaisannhadatid) {
        assertTaisannhadatidAllUpdatablePropertiesEquals(expectedTaisannhadatid, getPersistedTaisannhadatid(expectedTaisannhadatid));
    }
}
