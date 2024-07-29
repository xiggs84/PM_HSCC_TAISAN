package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.TaiSanDuongSu;
import vn.vnpt.repository.TaiSanDuongSuRepository;
import vn.vnpt.service.TaiSanDuongSuService;
import vn.vnpt.service.dto.TaiSanDuongSuDTO;
import vn.vnpt.service.mapper.TaiSanDuongSuMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.TaiSanDuongSu}.
 */
@Service
@Transactional
public class TaiSanDuongSuServiceImpl implements TaiSanDuongSuService {

    private static final Logger log = LoggerFactory.getLogger(TaiSanDuongSuServiceImpl.class);

    private final TaiSanDuongSuRepository taiSanDuongSuRepository;

    private final TaiSanDuongSuMapper taiSanDuongSuMapper;

    public TaiSanDuongSuServiceImpl(TaiSanDuongSuRepository taiSanDuongSuRepository, TaiSanDuongSuMapper taiSanDuongSuMapper) {
        this.taiSanDuongSuRepository = taiSanDuongSuRepository;
        this.taiSanDuongSuMapper = taiSanDuongSuMapper;
    }

    @Override
    public TaiSanDuongSuDTO save(TaiSanDuongSuDTO taiSanDuongSuDTO) {
        log.debug("Request to save TaiSanDuongSu : {}", taiSanDuongSuDTO);
        TaiSanDuongSu taiSanDuongSu = taiSanDuongSuMapper.toEntity(taiSanDuongSuDTO);
        taiSanDuongSu = taiSanDuongSuRepository.save(taiSanDuongSu);
        return taiSanDuongSuMapper.toDto(taiSanDuongSu);
    }

    @Override
    public TaiSanDuongSuDTO update(TaiSanDuongSuDTO taiSanDuongSuDTO) {
        log.debug("Request to update TaiSanDuongSu : {}", taiSanDuongSuDTO);
        TaiSanDuongSu taiSanDuongSu = taiSanDuongSuMapper.toEntity(taiSanDuongSuDTO);
        taiSanDuongSu = taiSanDuongSuRepository.save(taiSanDuongSu);
        return taiSanDuongSuMapper.toDto(taiSanDuongSu);
    }

    @Override
    public Optional<TaiSanDuongSuDTO> partialUpdate(TaiSanDuongSuDTO taiSanDuongSuDTO) {
        log.debug("Request to partially update TaiSanDuongSu : {}", taiSanDuongSuDTO);

        return taiSanDuongSuRepository
            .findById(taiSanDuongSuDTO.getId())
            .map(existingTaiSanDuongSu -> {
                taiSanDuongSuMapper.partialUpdate(existingTaiSanDuongSu, taiSanDuongSuDTO);

                return existingTaiSanDuongSu;
            })
            .map(taiSanDuongSuRepository::save)
            .map(taiSanDuongSuMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TaiSanDuongSuDTO> findOne(Long id) {
        log.debug("Request to get TaiSanDuongSu : {}", id);
        return taiSanDuongSuRepository.findById(id).map(taiSanDuongSuMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TaiSanDuongSu : {}", id);
        taiSanDuongSuRepository.deleteById(id);
    }
}
