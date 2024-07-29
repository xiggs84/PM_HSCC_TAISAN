package vn.vnpt.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.CauHinhThongTinLoaiTaiSan;
import vn.vnpt.repository.CauHinhThongTinLoaiTaiSanRepository;
import vn.vnpt.service.dto.CauHinhThongTinLoaiTaiSanDTO;
import vn.vnpt.service.mapper.CauHinhThongTinLoaiTaiSanMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.CauHinhThongTinLoaiTaiSan}.
 */
@Service
@Transactional
public class CauHinhThongTinLoaiTaiSanService {

    private static final Logger log = LoggerFactory.getLogger(CauHinhThongTinLoaiTaiSanService.class);

    private final CauHinhThongTinLoaiTaiSanRepository cauHinhThongTinLoaiTaiSanRepository;

    private final CauHinhThongTinLoaiTaiSanMapper cauHinhThongTinLoaiTaiSanMapper;

    public CauHinhThongTinLoaiTaiSanService(
        CauHinhThongTinLoaiTaiSanRepository cauHinhThongTinLoaiTaiSanRepository,
        CauHinhThongTinLoaiTaiSanMapper cauHinhThongTinLoaiTaiSanMapper
    ) {
        this.cauHinhThongTinLoaiTaiSanRepository = cauHinhThongTinLoaiTaiSanRepository;
        this.cauHinhThongTinLoaiTaiSanMapper = cauHinhThongTinLoaiTaiSanMapper;
    }

    /**
     * Save a cauHinhThongTinLoaiTaiSan.
     *
     * @param cauHinhThongTinLoaiTaiSanDTO the entity to save.
     * @return the persisted entity.
     */
    public CauHinhThongTinLoaiTaiSanDTO save(CauHinhThongTinLoaiTaiSanDTO cauHinhThongTinLoaiTaiSanDTO) {
        log.debug("Request to save CauHinhThongTinLoaiTaiSan : {}", cauHinhThongTinLoaiTaiSanDTO);
        CauHinhThongTinLoaiTaiSan cauHinhThongTinLoaiTaiSan = cauHinhThongTinLoaiTaiSanMapper.toEntity(cauHinhThongTinLoaiTaiSanDTO);
        cauHinhThongTinLoaiTaiSan = cauHinhThongTinLoaiTaiSanRepository.save(cauHinhThongTinLoaiTaiSan);
        return cauHinhThongTinLoaiTaiSanMapper.toDto(cauHinhThongTinLoaiTaiSan);
    }

    /**
     * Update a cauHinhThongTinLoaiTaiSan.
     *
     * @param cauHinhThongTinLoaiTaiSanDTO the entity to save.
     * @return the persisted entity.
     */
    public CauHinhThongTinLoaiTaiSanDTO update(CauHinhThongTinLoaiTaiSanDTO cauHinhThongTinLoaiTaiSanDTO) {
        log.debug("Request to update CauHinhThongTinLoaiTaiSan : {}", cauHinhThongTinLoaiTaiSanDTO);
        CauHinhThongTinLoaiTaiSan cauHinhThongTinLoaiTaiSan = cauHinhThongTinLoaiTaiSanMapper.toEntity(cauHinhThongTinLoaiTaiSanDTO);
        cauHinhThongTinLoaiTaiSan = cauHinhThongTinLoaiTaiSanRepository.save(cauHinhThongTinLoaiTaiSan);
        return cauHinhThongTinLoaiTaiSanMapper.toDto(cauHinhThongTinLoaiTaiSan);
    }

    /**
     * Partially update a cauHinhThongTinLoaiTaiSan.
     *
     * @param cauHinhThongTinLoaiTaiSanDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CauHinhThongTinLoaiTaiSanDTO> partialUpdate(CauHinhThongTinLoaiTaiSanDTO cauHinhThongTinLoaiTaiSanDTO) {
        log.debug("Request to partially update CauHinhThongTinLoaiTaiSan : {}", cauHinhThongTinLoaiTaiSanDTO);

        return cauHinhThongTinLoaiTaiSanRepository
            .findById(cauHinhThongTinLoaiTaiSanDTO.getId())
            .map(existingCauHinhThongTinLoaiTaiSan -> {
                cauHinhThongTinLoaiTaiSanMapper.partialUpdate(existingCauHinhThongTinLoaiTaiSan, cauHinhThongTinLoaiTaiSanDTO);

                return existingCauHinhThongTinLoaiTaiSan;
            })
            .map(cauHinhThongTinLoaiTaiSanRepository::save)
            .map(cauHinhThongTinLoaiTaiSanMapper::toDto);
    }

    /**
     * Get all the cauHinhThongTinLoaiTaiSans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CauHinhThongTinLoaiTaiSanDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CauHinhThongTinLoaiTaiSans");
        return cauHinhThongTinLoaiTaiSanRepository.findAll(pageable).map(cauHinhThongTinLoaiTaiSanMapper::toDto);
    }

    /**
     * Get one cauHinhThongTinLoaiTaiSan by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CauHinhThongTinLoaiTaiSanDTO> findOne(Long id) {
        log.debug("Request to get CauHinhThongTinLoaiTaiSan : {}", id);
        return cauHinhThongTinLoaiTaiSanRepository.findById(id).map(cauHinhThongTinLoaiTaiSanMapper::toDto);
    }

    /**
     * Delete the cauHinhThongTinLoaiTaiSan by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CauHinhThongTinLoaiTaiSan : {}", id);
        cauHinhThongTinLoaiTaiSanRepository.deleteById(id);
    }
}
