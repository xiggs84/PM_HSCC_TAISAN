package vn.vnpt.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.vnpt.service.dto.TaiSanDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.TaiSan}.
 */
public interface TaiSanService {
    /**
     * Save a taiSan.
     *
     * @param taiSanDTO the entity to save.
     * @return the persisted entity.
     */
    TaiSanDTO save(TaiSanDTO taiSanDTO);

    /**
     * Updates a taiSan.
     *
     * @param taiSanDTO the entity to update.
     * @return the persisted entity.
     */
    TaiSanDTO update(TaiSanDTO taiSanDTO);

    /**
     * Partially updates a taiSan.
     *
     * @param taiSanDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TaiSanDTO> partialUpdate(TaiSanDTO taiSanDTO);

    /**
     * Get all the TaiSanDTO where TaiSanDuongSu is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<TaiSanDTO> findAllWhereTaiSanDuongSuIsNull();

    /**
     * Get all the taiSans with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TaiSanDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" taiSan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TaiSanDTO> findOne(Long id);

    /**
     * Delete the "id" taiSan.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
