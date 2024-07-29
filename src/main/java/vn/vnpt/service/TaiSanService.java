package vn.vnpt.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.TaiSan;
import vn.vnpt.repository.TaiSanRepository;
import vn.vnpt.service.dto.TaiSanDTO;
import vn.vnpt.service.mapper.TaiSanMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.TaiSan}.
 */
@Service
@Transactional
public class TaiSanService {

    private static final Logger log = LoggerFactory.getLogger(TaiSanService.class);

    private final TaiSanRepository taiSanRepository;

    private final TaiSanMapper taiSanMapper;

    public TaiSanService(TaiSanRepository taiSanRepository, TaiSanMapper taiSanMapper) {
        this.taiSanRepository = taiSanRepository;
        this.taiSanMapper = taiSanMapper;
    }

    /**
     * Save a taiSan.
     *
     * @param taiSanDTO the entity to save.
     * @return the persisted entity.
     */
    public TaiSanDTO save(TaiSanDTO taiSanDTO) {
        log.debug("Request to save TaiSan : {}", taiSanDTO);
        TaiSan taiSan = taiSanMapper.toEntity(taiSanDTO);
        taiSan = taiSanRepository.save(taiSan);
        return taiSanMapper.toDto(taiSan);
    }

    /**
     * Update a taiSan.
     *
     * @param taiSanDTO the entity to save.
     * @return the persisted entity.
     */
    public TaiSanDTO update(TaiSanDTO taiSanDTO) {
        log.debug("Request to update TaiSan : {}", taiSanDTO);
        TaiSan taiSan = taiSanMapper.toEntity(taiSanDTO);
        taiSan = taiSanRepository.save(taiSan);
        return taiSanMapper.toDto(taiSan);
    }

    /**
     * Partially update a taiSan.
     *
     * @param taiSanDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TaiSanDTO> partialUpdate(TaiSanDTO taiSanDTO) {
        log.debug("Request to partially update TaiSan : {}", taiSanDTO);

        return taiSanRepository
            .findById(taiSanDTO.getId())
            .map(existingTaiSan -> {
                taiSanMapper.partialUpdate(existingTaiSan, taiSanDTO);

                return existingTaiSan;
            })
            .map(taiSanRepository::save)
            .map(taiSanMapper::toDto);
    }

    /**
     * Get all the taiSans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TaiSanDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TaiSans");
        return taiSanRepository.findAll(pageable).map(taiSanMapper::toDto);
    }

    /**
     * Get all the taiSans with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<TaiSanDTO> findAllWithEagerRelationships(Pageable pageable) {
        return taiSanRepository.findAllWithEagerRelationships(pageable).map(taiSanMapper::toDto);
    }

    /**
     *  Get all the taiSans where TaiSanDuongSu is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TaiSanDTO> findAllWhereTaiSanDuongSuIsNull() {
        log.debug("Request to get all taiSans where TaiSanDuongSu is null");
        return StreamSupport.stream(taiSanRepository.findAll().spliterator(), false)
            .filter(taiSan -> taiSan.getTaiSanDuongSu() == null)
            .map(taiSanMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one taiSan by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TaiSanDTO> findOne(Long id) {
        log.debug("Request to get TaiSan : {}", id);
        return taiSanRepository.findOneWithEagerRelationships(id).map(taiSanMapper::toDto);
    }

    /**
     * Delete the taiSan by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TaiSan : {}", id);
        taiSanRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<TaiSanDTO> findByTenTaiSan(String tenTaiSan) {
        log.debug("Request to get all TaiSans by tenTaiSan");
        return taiSanRepository
            .findByTenTaiSanContainingIgnoreCase(tenTaiSan)
            .stream()
            .map(taiSanMapper::toDto)
            .collect(Collectors.toList());
    }
}
