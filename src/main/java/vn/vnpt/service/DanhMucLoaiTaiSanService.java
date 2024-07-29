package vn.vnpt.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DanhMucLoaiTaiSan;
import vn.vnpt.repository.DanhMucLoaiTaiSanRepository;
import vn.vnpt.service.dto.DanhMucLoaiTaiSanDTO;
import vn.vnpt.service.mapper.DanhMucLoaiTaiSanMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhMucLoaiTaiSan}.
 */
@Service
@Transactional
public class DanhMucLoaiTaiSanService {

    private static final Logger log = LoggerFactory.getLogger(DanhMucLoaiTaiSanService.class);

    private final DanhMucLoaiTaiSanRepository danhMucLoaiTaiSanRepository;

    private final DanhMucLoaiTaiSanMapper danhMucLoaiTaiSanMapper;

    public DanhMucLoaiTaiSanService(
        DanhMucLoaiTaiSanRepository danhMucLoaiTaiSanRepository,
        DanhMucLoaiTaiSanMapper danhMucLoaiTaiSanMapper
    ) {
        this.danhMucLoaiTaiSanRepository = danhMucLoaiTaiSanRepository;
        this.danhMucLoaiTaiSanMapper = danhMucLoaiTaiSanMapper;
    }

    /**
     * Save a danhMucLoaiTaiSan.
     *
     * @param danhMucLoaiTaiSanDTO the entity to save.
     * @return the persisted entity.
     */
    public DanhMucLoaiTaiSanDTO save(DanhMucLoaiTaiSanDTO danhMucLoaiTaiSanDTO) {
        log.debug("Request to save DanhMucLoaiTaiSan : {}", danhMucLoaiTaiSanDTO);
        DanhMucLoaiTaiSan danhMucLoaiTaiSan = danhMucLoaiTaiSanMapper.toEntity(danhMucLoaiTaiSanDTO);
        danhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.save(danhMucLoaiTaiSan);
        return danhMucLoaiTaiSanMapper.toDto(danhMucLoaiTaiSan);
    }

    /**
     * Update a danhMucLoaiTaiSan.
     *
     * @param danhMucLoaiTaiSanDTO the entity to save.
     * @return the persisted entity.
     */
    public DanhMucLoaiTaiSanDTO update(DanhMucLoaiTaiSanDTO danhMucLoaiTaiSanDTO) {
        log.debug("Request to update DanhMucLoaiTaiSan : {}", danhMucLoaiTaiSanDTO);
        DanhMucLoaiTaiSan danhMucLoaiTaiSan = danhMucLoaiTaiSanMapper.toEntity(danhMucLoaiTaiSanDTO);
        danhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.save(danhMucLoaiTaiSan);
        return danhMucLoaiTaiSanMapper.toDto(danhMucLoaiTaiSan);
    }

    /**
     * Partially update a danhMucLoaiTaiSan.
     *
     * @param danhMucLoaiTaiSanDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DanhMucLoaiTaiSanDTO> partialUpdate(DanhMucLoaiTaiSanDTO danhMucLoaiTaiSanDTO) {
        log.debug("Request to partially update DanhMucLoaiTaiSan : {}", danhMucLoaiTaiSanDTO);

        return danhMucLoaiTaiSanRepository
            .findById(danhMucLoaiTaiSanDTO.getId())
            .map(existingDanhMucLoaiTaiSan -> {
                danhMucLoaiTaiSanMapper.partialUpdate(existingDanhMucLoaiTaiSan, danhMucLoaiTaiSanDTO);

                return existingDanhMucLoaiTaiSan;
            })
            .map(danhMucLoaiTaiSanRepository::save)
            .map(danhMucLoaiTaiSanMapper::toDto);
    }

    /**
     * Get all the danhMucLoaiTaiSans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DanhMucLoaiTaiSanDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DanhMucLoaiTaiSans");
        return danhMucLoaiTaiSanRepository.findAll(pageable).map(danhMucLoaiTaiSanMapper::toDto);
    }

    /**
     * Get one danhMucLoaiTaiSan by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DanhMucLoaiTaiSanDTO> findOne(Long id) {
        log.debug("Request to get DanhMucLoaiTaiSan : {}", id);
        return danhMucLoaiTaiSanRepository.findById(id).map(danhMucLoaiTaiSanMapper::toDto);
    }

    /**
     * Delete the danhMucLoaiTaiSan by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DanhMucLoaiTaiSan : {}", id);
        danhMucLoaiTaiSanRepository.deleteById(id);
    }
}
