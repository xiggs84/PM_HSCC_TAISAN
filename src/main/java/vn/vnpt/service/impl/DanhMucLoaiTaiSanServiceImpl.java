package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DanhMucLoaiTaiSan;
import vn.vnpt.repository.DanhMucLoaiTaiSanRepository;
import vn.vnpt.service.DanhMucLoaiTaiSanService;
import vn.vnpt.service.dto.DanhMucLoaiTaiSanDTO;
import vn.vnpt.service.mapper.DanhMucLoaiTaiSanMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhMucLoaiTaiSan}.
 */
@Service
@Transactional
public class DanhMucLoaiTaiSanServiceImpl implements DanhMucLoaiTaiSanService {

    private static final Logger log = LoggerFactory.getLogger(DanhMucLoaiTaiSanServiceImpl.class);

    private final DanhMucLoaiTaiSanRepository danhMucLoaiTaiSanRepository;

    private final DanhMucLoaiTaiSanMapper danhMucLoaiTaiSanMapper;

    public DanhMucLoaiTaiSanServiceImpl(
        DanhMucLoaiTaiSanRepository danhMucLoaiTaiSanRepository,
        DanhMucLoaiTaiSanMapper danhMucLoaiTaiSanMapper
    ) {
        this.danhMucLoaiTaiSanRepository = danhMucLoaiTaiSanRepository;
        this.danhMucLoaiTaiSanMapper = danhMucLoaiTaiSanMapper;
    }

    @Override
    public DanhMucLoaiTaiSanDTO save(DanhMucLoaiTaiSanDTO danhMucLoaiTaiSanDTO) {
        log.debug("Request to save DanhMucLoaiTaiSan : {}", danhMucLoaiTaiSanDTO);
        DanhMucLoaiTaiSan danhMucLoaiTaiSan = danhMucLoaiTaiSanMapper.toEntity(danhMucLoaiTaiSanDTO);
        danhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.save(danhMucLoaiTaiSan);
        return danhMucLoaiTaiSanMapper.toDto(danhMucLoaiTaiSan);
    }

    @Override
    public DanhMucLoaiTaiSanDTO update(DanhMucLoaiTaiSanDTO danhMucLoaiTaiSanDTO) {
        log.debug("Request to update DanhMucLoaiTaiSan : {}", danhMucLoaiTaiSanDTO);
        DanhMucLoaiTaiSan danhMucLoaiTaiSan = danhMucLoaiTaiSanMapper.toEntity(danhMucLoaiTaiSanDTO);
        danhMucLoaiTaiSan = danhMucLoaiTaiSanRepository.save(danhMucLoaiTaiSan);
        return danhMucLoaiTaiSanMapper.toDto(danhMucLoaiTaiSan);
    }

    @Override
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

    @Override
    @Transactional(readOnly = true)
    public Optional<DanhMucLoaiTaiSanDTO> findOne(Long id) {
        log.debug("Request to get DanhMucLoaiTaiSan : {}", id);
        return danhMucLoaiTaiSanRepository.findById(id).map(danhMucLoaiTaiSanMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DanhMucLoaiTaiSan : {}", id);
        danhMucLoaiTaiSanRepository.deleteById(id);
    }
}
