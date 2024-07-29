package vn.vnpt.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.Taisannhadatid;
import vn.vnpt.repository.TaisannhadatidRepository;
import vn.vnpt.service.dto.TaisannhadatidDTO;
import vn.vnpt.service.mapper.TaisannhadatidMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.Taisannhadatid}.
 */
@Service
@Transactional
public class TaisannhadatidService {

    private static final Logger log = LoggerFactory.getLogger(TaisannhadatidService.class);

    private final TaisannhadatidRepository taisannhadatidRepository;

    private final TaisannhadatidMapper taisannhadatidMapper;

    public TaisannhadatidService(TaisannhadatidRepository taisannhadatidRepository, TaisannhadatidMapper taisannhadatidMapper) {
        this.taisannhadatidRepository = taisannhadatidRepository;
        this.taisannhadatidMapper = taisannhadatidMapper;
    }

    /**
     * Save a taisannhadatid.
     *
     * @param taisannhadatidDTO the entity to save.
     * @return the persisted entity.
     */
    public TaisannhadatidDTO save(TaisannhadatidDTO taisannhadatidDTO) {
        log.debug("Request to save Taisannhadatid : {}", taisannhadatidDTO);
        Taisannhadatid taisannhadatid = taisannhadatidMapper.toEntity(taisannhadatidDTO);
        taisannhadatid = taisannhadatidRepository.save(taisannhadatid);
        return taisannhadatidMapper.toDto(taisannhadatid);
    }

    /**
     * Update a taisannhadatid.
     *
     * @param taisannhadatidDTO the entity to save.
     * @return the persisted entity.
     */
    public TaisannhadatidDTO update(TaisannhadatidDTO taisannhadatidDTO) {
        log.debug("Request to update Taisannhadatid : {}", taisannhadatidDTO);
        Taisannhadatid taisannhadatid = taisannhadatidMapper.toEntity(taisannhadatidDTO);
        taisannhadatid = taisannhadatidRepository.save(taisannhadatid);
        return taisannhadatidMapper.toDto(taisannhadatid);
    }

    /**
     * Partially update a taisannhadatid.
     *
     * @param taisannhadatidDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TaisannhadatidDTO> partialUpdate(TaisannhadatidDTO taisannhadatidDTO) {
        log.debug("Request to partially update Taisannhadatid : {}", taisannhadatidDTO);

        return taisannhadatidRepository
            .findById(taisannhadatidDTO.getId())
            .map(existingTaisannhadatid -> {
                taisannhadatidMapper.partialUpdate(existingTaisannhadatid, taisannhadatidDTO);

                return existingTaisannhadatid;
            })
            .map(taisannhadatidRepository::save)
            .map(taisannhadatidMapper::toDto);
    }

    /**
     * Get all the taisannhadatids.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TaisannhadatidDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Taisannhadatids");
        return taisannhadatidRepository.findAll(pageable).map(taisannhadatidMapper::toDto);
    }

    /**
     * Get one taisannhadatid by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TaisannhadatidDTO> findOne(Long id) {
        log.debug("Request to get Taisannhadatid : {}", id);
        return taisannhadatidRepository.findById(id).map(taisannhadatidMapper::toDto);
    }

    /**
     * Delete the taisannhadatid by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Taisannhadatid : {}", id);
        taisannhadatidRepository.deleteById(id);
    }
}
