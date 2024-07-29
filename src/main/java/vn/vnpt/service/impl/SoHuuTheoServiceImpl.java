package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.SoHuuTheo;
import vn.vnpt.repository.SoHuuTheoRepository;
import vn.vnpt.service.SoHuuTheoService;
import vn.vnpt.service.dto.SoHuuTheoDTO;
import vn.vnpt.service.mapper.SoHuuTheoMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.SoHuuTheo}.
 */
@Service
@Transactional
public class SoHuuTheoServiceImpl implements SoHuuTheoService {

    private static final Logger log = LoggerFactory.getLogger(SoHuuTheoServiceImpl.class);

    private final SoHuuTheoRepository soHuuTheoRepository;

    private final SoHuuTheoMapper soHuuTheoMapper;

    public SoHuuTheoServiceImpl(SoHuuTheoRepository soHuuTheoRepository, SoHuuTheoMapper soHuuTheoMapper) {
        this.soHuuTheoRepository = soHuuTheoRepository;
        this.soHuuTheoMapper = soHuuTheoMapper;
    }

    @Override
    public SoHuuTheoDTO save(SoHuuTheoDTO soHuuTheoDTO) {
        log.debug("Request to save SoHuuTheo : {}", soHuuTheoDTO);
        SoHuuTheo soHuuTheo = soHuuTheoMapper.toEntity(soHuuTheoDTO);
        soHuuTheo = soHuuTheoRepository.save(soHuuTheo);
        return soHuuTheoMapper.toDto(soHuuTheo);
    }

    @Override
    public SoHuuTheoDTO update(SoHuuTheoDTO soHuuTheoDTO) {
        log.debug("Request to update SoHuuTheo : {}", soHuuTheoDTO);
        SoHuuTheo soHuuTheo = soHuuTheoMapper.toEntity(soHuuTheoDTO);
        soHuuTheo = soHuuTheoRepository.save(soHuuTheo);
        return soHuuTheoMapper.toDto(soHuuTheo);
    }

    @Override
    public Optional<SoHuuTheoDTO> partialUpdate(SoHuuTheoDTO soHuuTheoDTO) {
        log.debug("Request to partially update SoHuuTheo : {}", soHuuTheoDTO);

        return soHuuTheoRepository
            .findById(soHuuTheoDTO.getId())
            .map(existingSoHuuTheo -> {
                soHuuTheoMapper.partialUpdate(existingSoHuuTheo, soHuuTheoDTO);

                return existingSoHuuTheo;
            })
            .map(soHuuTheoRepository::save)
            .map(soHuuTheoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SoHuuTheoDTO> findOne(Long id) {
        log.debug("Request to get SoHuuTheo : {}", id);
        return soHuuTheoRepository.findById(id).map(soHuuTheoMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SoHuuTheo : {}", id);
        soHuuTheoRepository.deleteById(id);
    }
}
