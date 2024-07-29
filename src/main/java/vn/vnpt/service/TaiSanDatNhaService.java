package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.TaiSanDatNhaDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.TaiSanDatNha}.
 */
public interface TaiSanDatNhaService {
    /**
     * Save a taiSanDatNha.
     *
     * @param taiSanDatNhaDTO the entity to save.
     * @return the persisted entity.
     */
    TaiSanDatNhaDTO save(TaiSanDatNhaDTO taiSanDatNhaDTO);

    /**
     * Updates a taiSanDatNha.
     *
     * @param taiSanDatNhaDTO the entity to update.
     * @return the persisted entity.
     */
    TaiSanDatNhaDTO update(TaiSanDatNhaDTO taiSanDatNhaDTO);

    /**
     * Partially updates a taiSanDatNha.
     *
     * @param taiSanDatNhaDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TaiSanDatNhaDTO> partialUpdate(TaiSanDatNhaDTO taiSanDatNhaDTO);

    /**
     * Get the "id" taiSanDatNha.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TaiSanDatNhaDTO> findOne(Long id);

    /**
     * Delete the "id" taiSanDatNha.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
