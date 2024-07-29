package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.DanhMucLoaiTaiSanDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.DanhMucLoaiTaiSan}.
 */
public interface DanhMucLoaiTaiSanService {
    /**
     * Save a danhMucLoaiTaiSan.
     *
     * @param danhMucLoaiTaiSanDTO the entity to save.
     * @return the persisted entity.
     */
    DanhMucLoaiTaiSanDTO save(DanhMucLoaiTaiSanDTO danhMucLoaiTaiSanDTO);

    /**
     * Updates a danhMucLoaiTaiSan.
     *
     * @param danhMucLoaiTaiSanDTO the entity to update.
     * @return the persisted entity.
     */
    DanhMucLoaiTaiSanDTO update(DanhMucLoaiTaiSanDTO danhMucLoaiTaiSanDTO);

    /**
     * Partially updates a danhMucLoaiTaiSan.
     *
     * @param danhMucLoaiTaiSanDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DanhMucLoaiTaiSanDTO> partialUpdate(DanhMucLoaiTaiSanDTO danhMucLoaiTaiSanDTO);

    /**
     * Get the "id" danhMucLoaiTaiSan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DanhMucLoaiTaiSanDTO> findOne(Long id);

    /**
     * Delete the "id" danhMucLoaiTaiSan.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
