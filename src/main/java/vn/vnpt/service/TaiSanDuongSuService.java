package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.TaiSanDuongSuDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.TaiSanDuongSu}.
 */
public interface TaiSanDuongSuService {
    /**
     * Save a taiSanDuongSu.
     *
     * @param taiSanDuongSuDTO the entity to save.
     * @return the persisted entity.
     */
    TaiSanDuongSuDTO save(TaiSanDuongSuDTO taiSanDuongSuDTO);

    /**
     * Updates a taiSanDuongSu.
     *
     * @param taiSanDuongSuDTO the entity to update.
     * @return the persisted entity.
     */
    TaiSanDuongSuDTO update(TaiSanDuongSuDTO taiSanDuongSuDTO);

    /**
     * Partially updates a taiSanDuongSu.
     *
     * @param taiSanDuongSuDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TaiSanDuongSuDTO> partialUpdate(TaiSanDuongSuDTO taiSanDuongSuDTO);

    /**
     * Get the "id" taiSanDuongSu.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TaiSanDuongSuDTO> findOne(Long id);

    /**
     * Delete the "id" taiSanDuongSu.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
