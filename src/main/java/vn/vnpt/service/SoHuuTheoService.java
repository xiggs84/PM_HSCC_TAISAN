package vn.vnpt.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.SoHuuTheo;
import vn.vnpt.repository.SoHuuTheoRepository;
import vn.vnpt.service.dto.SoHuuTheoDTO;
import vn.vnpt.service.mapper.SoHuuTheoMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.SoHuuTheo}.
 */
@Service
@Transactional
public class SoHuuTheoService {

    private static final Logger log = LoggerFactory.getLogger(SoHuuTheoService.class);

    private final SoHuuTheoRepository soHuuTheoRepository;

    private final SoHuuTheoMapper soHuuTheoMapper;

    public SoHuuTheoService(SoHuuTheoRepository soHuuTheoRepository, SoHuuTheoMapper soHuuTheoMapper) {
        this.soHuuTheoRepository = soHuuTheoRepository;
        this.soHuuTheoMapper = soHuuTheoMapper;
    }

    /**
     * Save a soHuuTheo.
     *
     * @param soHuuTheoDTO the entity to save.
     * @return the persisted entity.
     */
    public SoHuuTheoDTO save(SoHuuTheoDTO soHuuTheoDTO) {
        log.debug("Request to save SoHuuTheo : {}", soHuuTheoDTO);
        SoHuuTheo soHuuTheo = soHuuTheoMapper.toEntity(soHuuTheoDTO);
        soHuuTheo = soHuuTheoRepository.save(soHuuTheo);
        return soHuuTheoMapper.toDto(soHuuTheo);
    }

    /**
     * Update a soHuuTheo.
     *
     * @param soHuuTheoDTO the entity to save.
     * @return the persisted entity.
     */
    public SoHuuTheoDTO update(SoHuuTheoDTO soHuuTheoDTO) {
        log.debug("Request to update SoHuuTheo : {}", soHuuTheoDTO);
        SoHuuTheo soHuuTheo = soHuuTheoMapper.toEntity(soHuuTheoDTO);
        soHuuTheo = soHuuTheoRepository.save(soHuuTheo);
        return soHuuTheoMapper.toDto(soHuuTheo);
    }

    /**
     * Partially update a soHuuTheo.
     *
     * @param soHuuTheoDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<SoHuuTheoDTO> partialUpdate(SoHuuTheoDTO soHuuTheoDTO) {
        log.debug("Request to partially update SoHuuTheo : {}", soHuuTheoDTO);

        return soHuuTheoRepository
            .findById(soHuuTheoDTO.getId())
            .map(existingSoHuuTheo -> {
                soHuuTheoMapper.partialUpdate(existingSoHuuTheo, soHuuTheoDTO);

                return existingSoHuuTheo;
            })
            .map(soHuuTheoRepository::save)
            .map(soHuuTheoMapper::toDto);
    }

    /**
     * Get all the soHuuTheos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SoHuuTheoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SoHuuTheos");
        return soHuuTheoRepository.findAll(pageable).map(soHuuTheoMapper::toDto);
    }

    /**
     * Get one soHuuTheo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SoHuuTheoDTO> findOne(Long id) {
        log.debug("Request to get SoHuuTheo : {}", id);
        return soHuuTheoRepository.findById(id).map(soHuuTheoMapper::toDto);
    }

    /**
     * Delete the soHuuTheo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SoHuuTheo : {}", id);
        soHuuTheoRepository.deleteById(id);
    }
}
