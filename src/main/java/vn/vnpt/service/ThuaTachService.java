package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.ThuaTachDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.ThuaTach}.
 */
public interface ThuaTachService {
    /**
     * Save a thuaTach.
     *
     * @param thuaTachDTO the entity to save.
     * @return the persisted entity.
     */
    ThuaTachDTO save(ThuaTachDTO thuaTachDTO);

    /**
     * Updates a thuaTach.
     *
     * @param thuaTachDTO the entity to update.
     * @return the persisted entity.
     */
    ThuaTachDTO update(ThuaTachDTO thuaTachDTO);

    /**
     * Partially updates a thuaTach.
     *
     * @param thuaTachDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ThuaTachDTO> partialUpdate(ThuaTachDTO thuaTachDTO);

    /**
     * Get the "id" thuaTach.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ThuaTachDTO> findOne(Long id);

    /**
     * Delete the "id" thuaTach.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
