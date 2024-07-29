package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.CauHinhThongTinLoaiTaiSanDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.CauHinhThongTinLoaiTaiSan}.
 */
public interface CauHinhThongTinLoaiTaiSanService {
    /**
     * Save a cauHinhThongTinLoaiTaiSan.
     *
     * @param cauHinhThongTinLoaiTaiSanDTO the entity to save.
     * @return the persisted entity.
     */
    CauHinhThongTinLoaiTaiSanDTO save(CauHinhThongTinLoaiTaiSanDTO cauHinhThongTinLoaiTaiSanDTO);

    /**
     * Updates a cauHinhThongTinLoaiTaiSan.
     *
     * @param cauHinhThongTinLoaiTaiSanDTO the entity to update.
     * @return the persisted entity.
     */
    CauHinhThongTinLoaiTaiSanDTO update(CauHinhThongTinLoaiTaiSanDTO cauHinhThongTinLoaiTaiSanDTO);

    /**
     * Partially updates a cauHinhThongTinLoaiTaiSan.
     *
     * @param cauHinhThongTinLoaiTaiSanDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CauHinhThongTinLoaiTaiSanDTO> partialUpdate(CauHinhThongTinLoaiTaiSanDTO cauHinhThongTinLoaiTaiSanDTO);

    /**
     * Get the "id" cauHinhThongTinLoaiTaiSan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CauHinhThongTinLoaiTaiSanDTO> findOne(Long id);

    /**
     * Delete the "id" cauHinhThongTinLoaiTaiSan.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
