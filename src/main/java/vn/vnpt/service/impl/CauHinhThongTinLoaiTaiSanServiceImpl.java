package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.CauHinhThongTinLoaiTaiSan;
import vn.vnpt.repository.CauHinhThongTinLoaiTaiSanRepository;
import vn.vnpt.service.CauHinhThongTinLoaiTaiSanService;
import vn.vnpt.service.dto.CauHinhThongTinLoaiTaiSanDTO;
import vn.vnpt.service.mapper.CauHinhThongTinLoaiTaiSanMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.CauHinhThongTinLoaiTaiSan}.
 */
@Service
@Transactional
public class CauHinhThongTinLoaiTaiSanServiceImpl implements CauHinhThongTinLoaiTaiSanService {

    private static final Logger log = LoggerFactory.getLogger(CauHinhThongTinLoaiTaiSanServiceImpl.class);

    private final CauHinhThongTinLoaiTaiSanRepository cauHinhThongTinLoaiTaiSanRepository;

    private final CauHinhThongTinLoaiTaiSanMapper cauHinhThongTinLoaiTaiSanMapper;

    public CauHinhThongTinLoaiTaiSanServiceImpl(
        CauHinhThongTinLoaiTaiSanRepository cauHinhThongTinLoaiTaiSanRepository,
        CauHinhThongTinLoaiTaiSanMapper cauHinhThongTinLoaiTaiSanMapper
    ) {
        this.cauHinhThongTinLoaiTaiSanRepository = cauHinhThongTinLoaiTaiSanRepository;
        this.cauHinhThongTinLoaiTaiSanMapper = cauHinhThongTinLoaiTaiSanMapper;
    }

    @Override
    public CauHinhThongTinLoaiTaiSanDTO save(CauHinhThongTinLoaiTaiSanDTO cauHinhThongTinLoaiTaiSanDTO) {
        log.debug("Request to save CauHinhThongTinLoaiTaiSan : {}", cauHinhThongTinLoaiTaiSanDTO);
        CauHinhThongTinLoaiTaiSan cauHinhThongTinLoaiTaiSan = cauHinhThongTinLoaiTaiSanMapper.toEntity(cauHinhThongTinLoaiTaiSanDTO);
        cauHinhThongTinLoaiTaiSan = cauHinhThongTinLoaiTaiSanRepository.save(cauHinhThongTinLoaiTaiSan);
        return cauHinhThongTinLoaiTaiSanMapper.toDto(cauHinhThongTinLoaiTaiSan);
    }

    @Override
    public CauHinhThongTinLoaiTaiSanDTO update(CauHinhThongTinLoaiTaiSanDTO cauHinhThongTinLoaiTaiSanDTO) {
        log.debug("Request to update CauHinhThongTinLoaiTaiSan : {}", cauHinhThongTinLoaiTaiSanDTO);
        CauHinhThongTinLoaiTaiSan cauHinhThongTinLoaiTaiSan = cauHinhThongTinLoaiTaiSanMapper.toEntity(cauHinhThongTinLoaiTaiSanDTO);
        cauHinhThongTinLoaiTaiSan = cauHinhThongTinLoaiTaiSanRepository.save(cauHinhThongTinLoaiTaiSan);
        return cauHinhThongTinLoaiTaiSanMapper.toDto(cauHinhThongTinLoaiTaiSan);
    }

    @Override
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

    @Override
    @Transactional(readOnly = true)
    public Optional<CauHinhThongTinLoaiTaiSanDTO> findOne(Long id) {
        log.debug("Request to get CauHinhThongTinLoaiTaiSan : {}", id);
        return cauHinhThongTinLoaiTaiSanRepository.findById(id).map(cauHinhThongTinLoaiTaiSanMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CauHinhThongTinLoaiTaiSan : {}", id);
        cauHinhThongTinLoaiTaiSanRepository.deleteById(id);
    }
}
