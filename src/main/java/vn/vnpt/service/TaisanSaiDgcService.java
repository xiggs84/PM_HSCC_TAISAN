package vn.vnpt.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.TaisanSaiDgc;
import vn.vnpt.repository.TaisanSaiDgcRepository;
import vn.vnpt.service.dto.TaisanSaiDgcDTO;
import vn.vnpt.service.mapper.TaisanSaiDgcMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.TaisanSaiDgc}.
 */
@Service
@Transactional
public class TaisanSaiDgcService {

    private static final Logger log = LoggerFactory.getLogger(TaisanSaiDgcService.class);

    private final TaisanSaiDgcRepository taisanSaiDgcRepository;

    private final TaisanSaiDgcMapper taisanSaiDgcMapper;

    public TaisanSaiDgcService(TaisanSaiDgcRepository taisanSaiDgcRepository, TaisanSaiDgcMapper taisanSaiDgcMapper) {
        this.taisanSaiDgcRepository = taisanSaiDgcRepository;
        this.taisanSaiDgcMapper = taisanSaiDgcMapper;
    }

    /**
     * Save a taisanSaiDgc.
     *
     * @param taisanSaiDgcDTO the entity to save.
     * @return the persisted entity.
     */
    public TaisanSaiDgcDTO save(TaisanSaiDgcDTO taisanSaiDgcDTO) {
        log.debug("Request to save TaisanSaiDgc : {}", taisanSaiDgcDTO);
        TaisanSaiDgc taisanSaiDgc = taisanSaiDgcMapper.toEntity(taisanSaiDgcDTO);
        taisanSaiDgc = taisanSaiDgcRepository.save(taisanSaiDgc);
        return taisanSaiDgcMapper.toDto(taisanSaiDgc);
    }

    /**
     * Update a taisanSaiDgc.
     *
     * @param taisanSaiDgcDTO the entity to save.
     * @return the persisted entity.
     */
    public TaisanSaiDgcDTO update(TaisanSaiDgcDTO taisanSaiDgcDTO) {
        log.debug("Request to update TaisanSaiDgc : {}", taisanSaiDgcDTO);
        TaisanSaiDgc taisanSaiDgc = taisanSaiDgcMapper.toEntity(taisanSaiDgcDTO);
        taisanSaiDgc = taisanSaiDgcRepository.save(taisanSaiDgc);
        return taisanSaiDgcMapper.toDto(taisanSaiDgc);
    }

    /**
     * Partially update a taisanSaiDgc.
     *
     * @param taisanSaiDgcDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TaisanSaiDgcDTO> partialUpdate(TaisanSaiDgcDTO taisanSaiDgcDTO) {
        log.debug("Request to partially update TaisanSaiDgc : {}", taisanSaiDgcDTO);

        return taisanSaiDgcRepository
            .findById(taisanSaiDgcDTO.getId())
            .map(existingTaisanSaiDgc -> {
                taisanSaiDgcMapper.partialUpdate(existingTaisanSaiDgc, taisanSaiDgcDTO);

                return existingTaisanSaiDgc;
            })
            .map(taisanSaiDgcRepository::save)
            .map(taisanSaiDgcMapper::toDto);
    }

    /**
     * Get all the taisanSaiDgcs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TaisanSaiDgcDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TaisanSaiDgcs");
        return taisanSaiDgcRepository.findAll(pageable).map(taisanSaiDgcMapper::toDto);
    }

    /**
     * Get one taisanSaiDgc by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TaisanSaiDgcDTO> findOne(Long id) {
        log.debug("Request to get TaisanSaiDgc : {}", id);
        return taisanSaiDgcRepository.findById(id).map(taisanSaiDgcMapper::toDto);
    }

    /**
     * Delete the taisanSaiDgc by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TaisanSaiDgc : {}", id);
        taisanSaiDgcRepository.deleteById(id);
    }
}
