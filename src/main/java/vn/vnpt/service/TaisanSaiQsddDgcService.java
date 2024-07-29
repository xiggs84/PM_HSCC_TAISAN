package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.TaisanSaiQsddDgcDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.TaisanSaiQsddDgc}.
 */
public interface TaisanSaiQsddDgcService {
    /**
     * Save a taisanSaiQsddDgc.
     *
     * @param taisanSaiQsddDgcDTO the entity to save.
     * @return the persisted entity.
     */
    TaisanSaiQsddDgcDTO save(TaisanSaiQsddDgcDTO taisanSaiQsddDgcDTO);

    /**
     * Updates a taisanSaiQsddDgc.
     *
     * @param taisanSaiQsddDgcDTO the entity to update.
     * @return the persisted entity.
     */
    TaisanSaiQsddDgcDTO update(TaisanSaiQsddDgcDTO taisanSaiQsddDgcDTO);

    /**
     * Partially updates a taisanSaiQsddDgc.
     *
     * @param taisanSaiQsddDgcDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TaisanSaiQsddDgcDTO> partialUpdate(TaisanSaiQsddDgcDTO taisanSaiQsddDgcDTO);

    /**
     * Get the "id" taisanSaiQsddDgc.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TaisanSaiQsddDgcDTO> findOne(Long id);

    /**
     * Delete the "id" taisanSaiQsddDgc.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
