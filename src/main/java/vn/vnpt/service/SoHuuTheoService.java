package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.SoHuuTheoDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.SoHuuTheo}.
 */
public interface SoHuuTheoService {
    /**
     * Save a soHuuTheo.
     *
     * @param soHuuTheoDTO the entity to save.
     * @return the persisted entity.
     */
    SoHuuTheoDTO save(SoHuuTheoDTO soHuuTheoDTO);

    /**
     * Updates a soHuuTheo.
     *
     * @param soHuuTheoDTO the entity to update.
     * @return the persisted entity.
     */
    SoHuuTheoDTO update(SoHuuTheoDTO soHuuTheoDTO);

    /**
     * Partially updates a soHuuTheo.
     *
     * @param soHuuTheoDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SoHuuTheoDTO> partialUpdate(SoHuuTheoDTO soHuuTheoDTO);

    /**
     * Get the "id" soHuuTheo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SoHuuTheoDTO> findOne(Long id);

    /**
     * Delete the "id" soHuuTheo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
