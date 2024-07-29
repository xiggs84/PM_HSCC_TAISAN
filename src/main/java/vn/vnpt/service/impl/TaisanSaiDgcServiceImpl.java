package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.TaisanSaiDgc;
import vn.vnpt.repository.TaisanSaiDgcRepository;
import vn.vnpt.service.TaisanSaiDgcService;
import vn.vnpt.service.dto.TaisanSaiDgcDTO;
import vn.vnpt.service.mapper.TaisanSaiDgcMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.TaisanSaiDgc}.
 */
@Service
@Transactional
public class TaisanSaiDgcServiceImpl implements TaisanSaiDgcService {

    private static final Logger log = LoggerFactory.getLogger(TaisanSaiDgcServiceImpl.class);

    private final TaisanSaiDgcRepository taisanSaiDgcRepository;

    private final TaisanSaiDgcMapper taisanSaiDgcMapper;

    public TaisanSaiDgcServiceImpl(TaisanSaiDgcRepository taisanSaiDgcRepository, TaisanSaiDgcMapper taisanSaiDgcMapper) {
        this.taisanSaiDgcRepository = taisanSaiDgcRepository;
        this.taisanSaiDgcMapper = taisanSaiDgcMapper;
    }

    @Override
    public TaisanSaiDgcDTO save(TaisanSaiDgcDTO taisanSaiDgcDTO) {
        log.debug("Request to save TaisanSaiDgc : {}", taisanSaiDgcDTO);
        TaisanSaiDgc taisanSaiDgc = taisanSaiDgcMapper.toEntity(taisanSaiDgcDTO);
        taisanSaiDgc = taisanSaiDgcRepository.save(taisanSaiDgc);
        return taisanSaiDgcMapper.toDto(taisanSaiDgc);
    }

    @Override
    public TaisanSaiDgcDTO update(TaisanSaiDgcDTO taisanSaiDgcDTO) {
        log.debug("Request to update TaisanSaiDgc : {}", taisanSaiDgcDTO);
        TaisanSaiDgc taisanSaiDgc = taisanSaiDgcMapper.toEntity(taisanSaiDgcDTO);
        taisanSaiDgc = taisanSaiDgcRepository.save(taisanSaiDgc);
        return taisanSaiDgcMapper.toDto(taisanSaiDgc);
    }

    @Override
    public Optional<TaisanSaiDgcDTO> partialUpdate(TaisanSaiDgcDTO taisanSaiDgcDTO) {
        log.debug("Request to partially update TaisanSaiDgc : {}", taisanSaiDgcDTO);

        return taisanSaiDgcRepository
            .findById(taisanSaiDgcDTO.getId())
            .map(existingTaisanSaiDgc -> {
                taisanSaiDgcMapper.partialUpdate(existingTaisanSaiDgc, taisanSaiDgcDTO);

                return existingTaisanSaiDgc;
            })
            .map(taisanSaiDgcRepository::save)
            .map(taisanSaiDgcMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TaisanSaiDgcDTO> findOne(Long id) {
        log.debug("Request to get TaisanSaiDgc : {}", id);
        return taisanSaiDgcRepository.findById(id).map(taisanSaiDgcMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TaisanSaiDgc : {}", id);
        taisanSaiDgcRepository.deleteById(id);
    }
}
