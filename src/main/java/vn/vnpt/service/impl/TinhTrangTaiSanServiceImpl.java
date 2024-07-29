package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.TinhTrangTaiSan;
import vn.vnpt.repository.TinhTrangTaiSanRepository;
import vn.vnpt.service.TinhTrangTaiSanService;
import vn.vnpt.service.dto.TinhTrangTaiSanDTO;
import vn.vnpt.service.mapper.TinhTrangTaiSanMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.TinhTrangTaiSan}.
 */
@Service
@Transactional
public class TinhTrangTaiSanServiceImpl implements TinhTrangTaiSanService {

    private static final Logger log = LoggerFactory.getLogger(TinhTrangTaiSanServiceImpl.class);

    private final TinhTrangTaiSanRepository tinhTrangTaiSanRepository;

    private final TinhTrangTaiSanMapper tinhTrangTaiSanMapper;

    public TinhTrangTaiSanServiceImpl(TinhTrangTaiSanRepository tinhTrangTaiSanRepository, TinhTrangTaiSanMapper tinhTrangTaiSanMapper) {
        this.tinhTrangTaiSanRepository = tinhTrangTaiSanRepository;
        this.tinhTrangTaiSanMapper = tinhTrangTaiSanMapper;
    }

    @Override
    public TinhTrangTaiSanDTO save(TinhTrangTaiSanDTO tinhTrangTaiSanDTO) {
        log.debug("Request to save TinhTrangTaiSan : {}", tinhTrangTaiSanDTO);
        TinhTrangTaiSan tinhTrangTaiSan = tinhTrangTaiSanMapper.toEntity(tinhTrangTaiSanDTO);
        tinhTrangTaiSan = tinhTrangTaiSanRepository.save(tinhTrangTaiSan);
        return tinhTrangTaiSanMapper.toDto(tinhTrangTaiSan);
    }

    @Override
    public TinhTrangTaiSanDTO update(TinhTrangTaiSanDTO tinhTrangTaiSanDTO) {
        log.debug("Request to update TinhTrangTaiSan : {}", tinhTrangTaiSanDTO);
        TinhTrangTaiSan tinhTrangTaiSan = tinhTrangTaiSanMapper.toEntity(tinhTrangTaiSanDTO);
        tinhTrangTaiSan = tinhTrangTaiSanRepository.save(tinhTrangTaiSan);
        return tinhTrangTaiSanMapper.toDto(tinhTrangTaiSan);
    }

    @Override
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

    @Override
    @Transactional(readOnly = true)
    public Optional<TinhTrangTaiSanDTO> findOne(Long id) {
        log.debug("Request to get TinhTrangTaiSan : {}", id);
        return tinhTrangTaiSanRepository.findById(id).map(tinhTrangTaiSanMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TinhTrangTaiSan : {}", id);
        tinhTrangTaiSanRepository.deleteById(id);
    }
}
