package vn.vnpt.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DanhSachTaiSan;
import vn.vnpt.repository.DanhSachTaiSanRepository;
import vn.vnpt.service.dto.DanhSachTaiSanDTO;
import vn.vnpt.service.mapper.DanhSachTaiSanMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhSachTaiSan}.
 */
@Service
@Transactional
public class DanhSachTaiSanService {

    private static final Logger log = LoggerFactory.getLogger(DanhSachTaiSanService.class);

    private final DanhSachTaiSanRepository danhSachTaiSanRepository;

    private final DanhSachTaiSanMapper danhSachTaiSanMapper;

    public DanhSachTaiSanService(DanhSachTaiSanRepository danhSachTaiSanRepository, DanhSachTaiSanMapper danhSachTaiSanMapper) {
        this.danhSachTaiSanRepository = danhSachTaiSanRepository;
        this.danhSachTaiSanMapper = danhSachTaiSanMapper;
    }

    /**
     * Save a danhSachTaiSan.
     *
     * @param danhSachTaiSanDTO the entity to save.
     * @return the persisted entity.
     */
    public DanhSachTaiSanDTO save(DanhSachTaiSanDTO danhSachTaiSanDTO) {
        log.debug("Request to save DanhSachTaiSan : {}", danhSachTaiSanDTO);
        DanhSachTaiSan danhSachTaiSan = danhSachTaiSanMapper.toEntity(danhSachTaiSanDTO);
        danhSachTaiSan = danhSachTaiSanRepository.save(danhSachTaiSan);
        return danhSachTaiSanMapper.toDto(danhSachTaiSan);
    }

    /**
     * Update a danhSachTaiSan.
     *
     * @param danhSachTaiSanDTO the entity to save.
     * @return the persisted entity.
     */
    public DanhSachTaiSanDTO update(DanhSachTaiSanDTO danhSachTaiSanDTO) {
        log.debug("Request to update DanhSachTaiSan : {}", danhSachTaiSanDTO);
        DanhSachTaiSan danhSachTaiSan = danhSachTaiSanMapper.toEntity(danhSachTaiSanDTO);
        danhSachTaiSan = danhSachTaiSanRepository.save(danhSachTaiSan);
        return danhSachTaiSanMapper.toDto(danhSachTaiSan);
    }

    /**
     * Partially update a danhSachTaiSan.
     *
     * @param danhSachTaiSanDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DanhSachTaiSanDTO> partialUpdate(DanhSachTaiSanDTO danhSachTaiSanDTO) {
        log.debug("Request to partially update DanhSachTaiSan : {}", danhSachTaiSanDTO);

        return danhSachTaiSanRepository
            .findById(danhSachTaiSanDTO.getId())
            .map(existingDanhSachTaiSan -> {
                danhSachTaiSanMapper.partialUpdate(existingDanhSachTaiSan, danhSachTaiSanDTO);

                return existingDanhSachTaiSan;
            })
            .map(danhSachTaiSanRepository::save)
            .map(danhSachTaiSanMapper::toDto);
    }

    /**
     * Get all the danhSachTaiSans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DanhSachTaiSanDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DanhSachTaiSans");
        return danhSachTaiSanRepository.findAll(pageable).map(danhSachTaiSanMapper::toDto);
    }

    /**
     * Get one danhSachTaiSan by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DanhSachTaiSanDTO> findOne(Long id) {
        log.debug("Request to get DanhSachTaiSan : {}", id);
        return danhSachTaiSanRepository.findById(id).map(danhSachTaiSanMapper::toDto);
    }

    /**
     * Delete the danhSachTaiSan by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DanhSachTaiSan : {}", id);
        danhSachTaiSanRepository.deleteById(id);
    }
}
