package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.TaiSanDgcDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.TaiSanDgc}.
 */
public interface TaiSanDgcService {
    /**
     * Save a taiSanDgc.
     *
     * @param taiSanDgcDTO the entity to save.
     * @return the persisted entity.
     */
    TaiSanDgcDTO save(TaiSanDgcDTO taiSanDgcDTO);

    /**
     * Updates a taiSanDgc.
     *
     * @param taiSanDgcDTO the entity to update.
     * @return the persisted entity.
     */
    TaiSanDgcDTO update(TaiSanDgcDTO taiSanDgcDTO);

    /**
     * Partially updates a taiSanDgc.
     *
     * @param taiSanDgcDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TaiSanDgcDTO> partialUpdate(TaiSanDgcDTO taiSanDgcDTO);

    /**
     * Get the "id" taiSanDgc.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TaiSanDgcDTO> findOne(Long id);

    /**
     * Delete the "id" taiSanDgc.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
