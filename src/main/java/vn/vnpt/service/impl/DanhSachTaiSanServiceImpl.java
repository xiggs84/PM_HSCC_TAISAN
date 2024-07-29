package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DanhSachTaiSan;
import vn.vnpt.repository.DanhSachTaiSanRepository;
import vn.vnpt.service.DanhSachTaiSanService;
import vn.vnpt.service.dto.DanhSachTaiSanDTO;
import vn.vnpt.service.mapper.DanhSachTaiSanMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhSachTaiSan}.
 */
@Service
@Transactional
public class DanhSachTaiSanServiceImpl implements DanhSachTaiSanService {

    private static final Logger log = LoggerFactory.getLogger(DanhSachTaiSanServiceImpl.class);

    private final DanhSachTaiSanRepository danhSachTaiSanRepository;

    private final DanhSachTaiSanMapper danhSachTaiSanMapper;

    public DanhSachTaiSanServiceImpl(DanhSachTaiSanRepository danhSachTaiSanRepository, DanhSachTaiSanMapper danhSachTaiSanMapper) {
        this.danhSachTaiSanRepository = danhSachTaiSanRepository;
        this.danhSachTaiSanMapper = danhSachTaiSanMapper;
    }

    @Override
    public DanhSachTaiSanDTO save(DanhSachTaiSanDTO danhSachTaiSanDTO) {
        log.debug("Request to save DanhSachTaiSan : {}", danhSachTaiSanDTO);
        DanhSachTaiSan danhSachTaiSan = danhSachTaiSanMapper.toEntity(danhSachTaiSanDTO);
        danhSachTaiSan = danhSachTaiSanRepository.save(danhSachTaiSan);
        return danhSachTaiSanMapper.toDto(danhSachTaiSan);
    }

    @Override
    public DanhSachTaiSanDTO update(DanhSachTaiSanDTO danhSachTaiSanDTO) {
        log.debug("Request to update DanhSachTaiSan : {}", danhSachTaiSanDTO);
        DanhSachTaiSan danhSachTaiSan = danhSachTaiSanMapper.toEntity(danhSachTaiSanDTO);
        danhSachTaiSan = danhSachTaiSanRepository.save(danhSachTaiSan);
        return danhSachTaiSanMapper.toDto(danhSachTaiSan);
    }

    @Override
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

    @Override
    @Transactional(readOnly = true)
    public Optional<DanhSachTaiSanDTO> findOne(Long id) {
        log.debug("Request to get DanhSachTaiSan : {}", id);
        return danhSachTaiSanRepository.findById(id).map(danhSachTaiSanMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DanhSachTaiSan : {}", id);
        danhSachTaiSanRepository.deleteById(id);
    }
}
