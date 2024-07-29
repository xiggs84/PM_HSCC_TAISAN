package vn.vnpt.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.TaisanSaiQsddDgc;
import vn.vnpt.repository.TaisanSaiQsddDgcRepository;
import vn.vnpt.service.dto.TaisanSaiQsddDgcDTO;
import vn.vnpt.service.mapper.TaisanSaiQsddDgcMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.TaisanSaiQsddDgc}.
 */
@Service
@Transactional
public class TaisanSaiQsddDgcService {

    private static final Logger log = LoggerFactory.getLogger(TaisanSaiQsddDgcService.class);

    private final TaisanSaiQsddDgcRepository taisanSaiQsddDgcRepository;

    private final TaisanSaiQsddDgcMapper taisanSaiQsddDgcMapper;

    public TaisanSaiQsddDgcService(TaisanSaiQsddDgcRepository taisanSaiQsddDgcRepository, TaisanSaiQsddDgcMapper taisanSaiQsddDgcMapper) {
        this.taisanSaiQsddDgcRepository = taisanSaiQsddDgcRepository;
        this.taisanSaiQsddDgcMapper = taisanSaiQsddDgcMapper;
    }

    /**
     * Save a taisanSaiQsddDgc.
     *
     * @param taisanSaiQsddDgcDTO the entity to save.
     * @return the persisted entity.
     */
    public TaisanSaiQsddDgcDTO save(TaisanSaiQsddDgcDTO taisanSaiQsddDgcDTO) {
        log.debug("Request to save TaisanSaiQsddDgc : {}", taisanSaiQsddDgcDTO);
        TaisanSaiQsddDgc taisanSaiQsddDgc = taisanSaiQsddDgcMapper.toEntity(taisanSaiQsddDgcDTO);
        taisanSaiQsddDgc = taisanSaiQsddDgcRepository.save(taisanSaiQsddDgc);
        return taisanSaiQsddDgcMapper.toDto(taisanSaiQsddDgc);
    }

    /**
     * Update a taisanSaiQsddDgc.
     *
     * @param taisanSaiQsddDgcDTO the entity to save.
     * @return the persisted entity.
     */
    public TaisanSaiQsddDgcDTO update(TaisanSaiQsddDgcDTO taisanSaiQsddDgcDTO) {
        log.debug("Request to update TaisanSaiQsddDgc : {}", taisanSaiQsddDgcDTO);
        TaisanSaiQsddDgc taisanSaiQsddDgc = taisanSaiQsddDgcMapper.toEntity(taisanSaiQsddDgcDTO);
        taisanSaiQsddDgc = taisanSaiQsddDgcRepository.save(taisanSaiQsddDgc);
        return taisanSaiQsddDgcMapper.toDto(taisanSaiQsddDgc);
    }

    /**
     * Partially update a taisanSaiQsddDgc.
     *
     * @param taisanSaiQsddDgcDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TaisanSaiQsddDgcDTO> partialUpdate(TaisanSaiQsddDgcDTO taisanSaiQsddDgcDTO) {
        log.debug("Request to partially update TaisanSaiQsddDgc : {}", taisanSaiQsddDgcDTO);

        return taisanSaiQsddDgcRepository
            .findById(taisanSaiQsddDgcDTO.getId())
            .map(existingTaisanSaiQsddDgc -> {
                taisanSaiQsddDgcMapper.partialUpdate(existingTaisanSaiQsddDgc, taisanSaiQsddDgcDTO);

                return existingTaisanSaiQsddDgc;
            })
            .map(taisanSaiQsddDgcRepository::save)
            .map(taisanSaiQsddDgcMapper::toDto);
    }

    /**
     * Get all the taisanSaiQsddDgcs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TaisanSaiQsddDgcDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TaisanSaiQsddDgcs");
        return taisanSaiQsddDgcRepository.findAll(pageable).map(taisanSaiQsddDgcMapper::toDto);
    }

    /**
     * Get one taisanSaiQsddDgc by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TaisanSaiQsddDgcDTO> findOne(Long id) {
        log.debug("Request to get TaisanSaiQsddDgc : {}", id);
        return taisanSaiQsddDgcRepository.findById(id).map(taisanSaiQsddDgcMapper::toDto);
    }

    /**
     * Delete the taisanSaiQsddDgc by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TaisanSaiQsddDgc : {}", id);
        taisanSaiQsddDgcRepository.deleteById(id);
    }
}
