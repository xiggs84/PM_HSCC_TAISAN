package vn.vnpt.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.TinhTrangTaiSan;
import vn.vnpt.repository.TinhTrangTaiSanRepository;
import vn.vnpt.service.dto.TinhTrangTaiSanDTO;
import vn.vnpt.service.mapper.TinhTrangTaiSanMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.TinhTrangTaiSan}.
 */
@Service
@Transactional
public class TinhTrangTaiSanService {

    private static final Logger log = LoggerFactory.getLogger(TinhTrangTaiSanService.class);

    private final TinhTrangTaiSanRepository tinhTrangTaiSanRepository;

    private final TinhTrangTaiSanMapper tinhTrangTaiSanMapper;

    public TinhTrangTaiSanService(TinhTrangTaiSanRepository tinhTrangTaiSanRepository, TinhTrangTaiSanMapper tinhTrangTaiSanMapper) {
        this.tinhTrangTaiSanRepository = tinhTrangTaiSanRepository;
        this.tinhTrangTaiSanMapper = tinhTrangTaiSanMapper;
    }

    /**
     * Save a tinhTrangTaiSan.
     *
     * @param tinhTrangTaiSanDTO the entity to save.
     * @return the persisted entity.
     */
    public TinhTrangTaiSanDTO save(TinhTrangTaiSanDTO tinhTrangTaiSanDTO) {
        log.debug("Request to save TinhTrangTaiSan : {}", tinhTrangTaiSanDTO);
        TinhTrangTaiSan tinhTrangTaiSan = tinhTrangTaiSanMapper.toEntity(tinhTrangTaiSanDTO);
        tinhTrangTaiSan = tinhTrangTaiSanRepository.save(tinhTrangTaiSan);
        return tinhTrangTaiSanMapper.toDto(tinhTrangTaiSan);
    }

    /**
     * Update a tinhTrangTaiSan.
     *
     * @param tinhTrangTaiSanDTO the entity to save.
     * @return the persisted entity.
     */
    public TinhTrangTaiSanDTO update(TinhTrangTaiSanDTO tinhTrangTaiSanDTO) {
        log.debug("Request to update TinhTrangTaiSan : {}", tinhTrangTaiSanDTO);
        TinhTrangTaiSan tinhTrangTaiSan = tinhTrangTaiSanMapper.toEntity(tinhTrangTaiSanDTO);
        tinhTrangTaiSan = tinhTrangTaiSanRepository.save(tinhTrangTaiSan);
        return tinhTrangTaiSanMapper.toDto(tinhTrangTaiSan);
    }

    /**
     * Partially update a tinhTrangTaiSan.
     *
     * @param tinhTrangTaiSanDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TinhTrangTaiSanDTO> partialUpdate(TinhTrangTaiSanDTO tinhTrangTaiSanDTO) {
        log.debug("Request to partially update TinhTrangTaiSan : {}", tinhTrangTaiSanDTO);

        return tinhTrangTaiSanRepository
            .findById(tinhTrangTaiSanDTO.getId())
            .map(existingTinhTrangTaiSan -> {
                tinhTrangTaiSanMapper.partialUpdate(existingTinhTrangTaiSan, tinhTrangTaiSanDTO);

                return existingTinhTrangTaiSan;
            })
            .map(tinhTrangTaiSanRepository::save)
            .map(tinhTrangTaiSanMapper::toDto);
    }

    /**
     * Get all the tinhTrangTaiSans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TinhTrangTaiSanDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TinhTrangTaiSans");
        return tinhTrangTaiSanRepository.findAll(pageable).map(tinhTrangTaiSanMapper::toDto);
    }

    /**
     * Get one tinhTrangTaiSan by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TinhTrangTaiSanDTO> findOne(Long id) {
        log.debug("Request to get TinhTrangTaiSan : {}", id);
        return tinhTrangTaiSanRepository.findById(id).map(tinhTrangTaiSanMapper::toDto);
    }

    /**
     * Delete the tinhTrangTaiSan by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TinhTrangTaiSan : {}", id);
        tinhTrangTaiSanRepository.deleteById(id);
    }
}
