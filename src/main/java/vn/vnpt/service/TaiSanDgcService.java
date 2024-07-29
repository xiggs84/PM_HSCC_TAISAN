package vn.vnpt.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.TaiSanDgc;
import vn.vnpt.repository.TaiSanDgcRepository;
import vn.vnpt.service.dto.TaiSanDgcDTO;
import vn.vnpt.service.mapper.TaiSanDgcMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.TaiSanDgc}.
 */
@Service
@Transactional
public class TaiSanDgcService {

    private static final Logger log = LoggerFactory.getLogger(TaiSanDgcService.class);

    private final TaiSanDgcRepository taiSanDgcRepository;

    private final TaiSanDgcMapper taiSanDgcMapper;

    public TaiSanDgcService(TaiSanDgcRepository taiSanDgcRepository, TaiSanDgcMapper taiSanDgcMapper) {
        this.taiSanDgcRepository = taiSanDgcRepository;
        this.taiSanDgcMapper = taiSanDgcMapper;
    }

    /**
     * Save a taiSanDgc.
     *
     * @param taiSanDgcDTO the entity to save.
     * @return the persisted entity.
     */
    public TaiSanDgcDTO save(TaiSanDgcDTO taiSanDgcDTO) {
        log.debug("Request to save TaiSanDgc : {}", taiSanDgcDTO);
        TaiSanDgc taiSanDgc = taiSanDgcMapper.toEntity(taiSanDgcDTO);
        taiSanDgc = taiSanDgcRepository.save(taiSanDgc);
        return taiSanDgcMapper.toDto(taiSanDgc);
    }

    /**
     * Update a taiSanDgc.
     *
     * @param taiSanDgcDTO the entity to save.
     * @return the persisted entity.
     */
    public TaiSanDgcDTO update(TaiSanDgcDTO taiSanDgcDTO) {
        log.debug("Request to update TaiSanDgc : {}", taiSanDgcDTO);
        TaiSanDgc taiSanDgc = taiSanDgcMapper.toEntity(taiSanDgcDTO);
        taiSanDgc = taiSanDgcRepository.save(taiSanDgc);
        return taiSanDgcMapper.toDto(taiSanDgc);
    }

    /**
     * Partially update a taiSanDgc.
     *
     * @param taiSanDgcDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TaiSanDgcDTO> partialUpdate(TaiSanDgcDTO taiSanDgcDTO) {
        log.debug("Request to partially update TaiSanDgc : {}", taiSanDgcDTO);

        return taiSanDgcRepository
            .findById(taiSanDgcDTO.getId())
            .map(existingTaiSanDgc -> {
                taiSanDgcMapper.partialUpdate(existingTaiSanDgc, taiSanDgcDTO);

                return existingTaiSanDgc;
            })
            .map(taiSanDgcRepository::save)
            .map(taiSanDgcMapper::toDto);
    }

    /**
     * Get all the taiSanDgcs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TaiSanDgcDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TaiSanDgcs");
        return taiSanDgcRepository.findAll(pageable).map(taiSanDgcMapper::toDto);
    }

    /**
     * Get one taiSanDgc by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TaiSanDgcDTO> findOne(Long id) {
        log.debug("Request to get TaiSanDgc : {}", id);
        return taiSanDgcRepository.findById(id).map(taiSanDgcMapper::toDto);
    }

    /**
     * Delete the taiSanDgc by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TaiSanDgc : {}", id);
        taiSanDgcRepository.deleteById(id);
    }
}
