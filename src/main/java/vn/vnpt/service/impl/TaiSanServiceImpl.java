package vn.vnpt.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.TaiSan;
import vn.vnpt.repository.TaiSanRepository;
import vn.vnpt.service.TaiSanService;
import vn.vnpt.service.dto.TaiSanDTO;
import vn.vnpt.service.mapper.TaiSanMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.TaiSan}.
 */
@Service
@Transactional
public class TaiSanServiceImpl implements TaiSanService {

    private static final Logger log = LoggerFactory.getLogger(TaiSanServiceImpl.class);

    private final TaiSanRepository taiSanRepository;

    private final TaiSanMapper taiSanMapper;

    public TaiSanServiceImpl(TaiSanRepository taiSanRepository, TaiSanMapper taiSanMapper) {
        this.taiSanRepository = taiSanRepository;
        this.taiSanMapper = taiSanMapper;
    }

    @Override
    public TaiSanDTO save(TaiSanDTO taiSanDTO) {
        log.debug("Request to save TaiSan : {}", taiSanDTO);
        TaiSan taiSan = taiSanMapper.toEntity(taiSanDTO);
        taiSan = taiSanRepository.save(taiSan);
        return taiSanMapper.toDto(taiSan);
    }

    @Override
    public TaiSanDTO update(TaiSanDTO taiSanDTO) {
        log.debug("Request to update TaiSan : {}", taiSanDTO);
        TaiSan taiSan = taiSanMapper.toEntity(taiSanDTO);
        taiSan = taiSanRepository.save(taiSan);
        return taiSanMapper.toDto(taiSan);
    }

    @Override
    public Optional<TaiSanDTO> partialUpdate(TaiSanDTO taiSanDTO) {
        log.debug("Request to partially update TaiSan : {}", taiSanDTO);

        return taiSanRepository
            .findById(taiSanDTO.getId())
            .map(existingTaiSan -> {
                taiSanMapper.partialUpdate(existingTaiSan, taiSanDTO);

                return existingTaiSan;
            })
            .map(taiSanRepository::save)
            .map(taiSanMapper::toDto);
    }

    public Page<TaiSanDTO> findAllWithEagerRelationships(Pageable pageable) {
        return taiSanRepository.findAllWithEagerRelationships(pageable).map(taiSanMapper::toDto);
    }

    /**
     *  Get all the taiSans where TaiSanDuongSu is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TaiSanDTO> findAllWhereTaiSanDuongSuIsNull() {
        log.debug("Request to get all taiSans where TaiSanDuongSu is null");
        return StreamSupport.stream(taiSanRepository.findAll().spliterator(), false)
            .filter(taiSan -> taiSan.getTaiSanDuongSu() == null)
            .map(taiSanMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TaiSanDTO> findOne(Long id) {
        log.debug("Request to get TaiSan : {}", id);
        return taiSanRepository.findOneWithEagerRelationships(id).map(taiSanMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TaiSan : {}", id);
        taiSanRepository.deleteById(id);
    }
}
