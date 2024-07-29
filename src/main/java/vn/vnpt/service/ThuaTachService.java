package vn.vnpt.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.ThuaTach;
import vn.vnpt.repository.ThuaTachRepository;
import vn.vnpt.service.dto.ThuaTachDTO;
import vn.vnpt.service.mapper.ThuaTachMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.ThuaTach}.
 */
@Service
@Transactional
public class ThuaTachService {

    private static final Logger log = LoggerFactory.getLogger(ThuaTachService.class);

    private final ThuaTachRepository thuaTachRepository;

    private final ThuaTachMapper thuaTachMapper;

    public ThuaTachService(ThuaTachRepository thuaTachRepository, ThuaTachMapper thuaTachMapper) {
        this.thuaTachRepository = thuaTachRepository;
        this.thuaTachMapper = thuaTachMapper;
    }

    /**
     * Save a thuaTach.
     *
     * @param thuaTachDTO the entity to save.
     * @return the persisted entity.
     */
    public ThuaTachDTO save(ThuaTachDTO thuaTachDTO) {
        log.debug("Request to save ThuaTach : {}", thuaTachDTO);
        ThuaTach thuaTach = thuaTachMapper.toEntity(thuaTachDTO);
        thuaTach = thuaTachRepository.save(thuaTach);
        return thuaTachMapper.toDto(thuaTach);
    }

    /**
     * Update a thuaTach.
     *
     * @param thuaTachDTO the entity to save.
     * @return the persisted entity.
     */
    public ThuaTachDTO update(ThuaTachDTO thuaTachDTO) {
        log.debug("Request to update ThuaTach : {}", thuaTachDTO);
        ThuaTach thuaTach = thuaTachMapper.toEntity(thuaTachDTO);
        thuaTach = thuaTachRepository.save(thuaTach);
        return thuaTachMapper.toDto(thuaTach);
    }

    /**
     * Partially update a thuaTach.
     *
     * @param thuaTachDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ThuaTachDTO> partialUpdate(ThuaTachDTO thuaTachDTO) {
        log.debug("Request to partially update ThuaTach : {}", thuaTachDTO);

        return thuaTachRepository
            .findById(thuaTachDTO.getId())
            .map(existingThuaTach -> {
                thuaTachMapper.partialUpdate(existingThuaTach, thuaTachDTO);

                return existingThuaTach;
            })
            .map(thuaTachRepository::save)
            .map(thuaTachMapper::toDto);
    }

    /**
     * Get all the thuaTaches.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ThuaTachDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ThuaTaches");
        return thuaTachRepository.findAll(pageable).map(thuaTachMapper::toDto);
    }

    /**
     * Get one thuaTach by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ThuaTachDTO> findOne(Long id) {
        log.debug("Request to get ThuaTach : {}", id);
        return thuaTachRepository.findById(id).map(thuaTachMapper::toDto);
    }

    /**
     * Delete the thuaTach by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ThuaTach : {}", id);
        thuaTachRepository.deleteById(id);
    }
}
