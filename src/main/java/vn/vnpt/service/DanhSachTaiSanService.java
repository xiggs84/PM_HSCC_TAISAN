package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.DanhSachTaiSanDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.DanhSachTaiSan}.
 */
public interface DanhSachTaiSanService {
    /**
     * Save a danhSachTaiSan.
     *
     * @param danhSachTaiSanDTO the entity to save.
     * @return the persisted entity.
     */
    DanhSachTaiSanDTO save(DanhSachTaiSanDTO danhSachTaiSanDTO);

    /**
     * Updates a danhSachTaiSan.
     *
     * @param danhSachTaiSanDTO the entity to update.
     * @return the persisted entity.
     */
    DanhSachTaiSanDTO update(DanhSachTaiSanDTO danhSachTaiSanDTO);

    /**
     * Partially updates a danhSachTaiSan.
     *
     * @param danhSachTaiSanDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DanhSachTaiSanDTO> partialUpdate(DanhSachTaiSanDTO danhSachTaiSanDTO);

    /**
     * Get the "id" danhSachTaiSan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DanhSachTaiSanDTO> findOne(Long id);

    /**
     * Delete the "id" danhSachTaiSan.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
