package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.TaisanSaiDgcDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.TaisanSaiDgc}.
 */
public interface TaisanSaiDgcService {
    /**
     * Save a taisanSaiDgc.
     *
     * @param taisanSaiDgcDTO the entity to save.
     * @return the persisted entity.
     */
    TaisanSaiDgcDTO save(TaisanSaiDgcDTO taisanSaiDgcDTO);

    /**
     * Updates a taisanSaiDgc.
     *
     * @param taisanSaiDgcDTO the entity to update.
     * @return the persisted entity.
     */
    TaisanSaiDgcDTO update(TaisanSaiDgcDTO taisanSaiDgcDTO);

    /**
     * Partially updates a taisanSaiDgc.
     *
     * @param taisanSaiDgcDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TaisanSaiDgcDTO> partialUpdate(TaisanSaiDgcDTO taisanSaiDgcDTO);

    /**
     * Get the "id" taisanSaiDgc.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TaisanSaiDgcDTO> findOne(Long id);

    /**
     * Delete the "id" taisanSaiDgc.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
