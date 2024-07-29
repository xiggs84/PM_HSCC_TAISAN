package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.ThuaTach;
import vn.vnpt.repository.ThuaTachRepository;
import vn.vnpt.service.ThuaTachService;
import vn.vnpt.service.dto.ThuaTachDTO;
import vn.vnpt.service.mapper.ThuaTachMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.ThuaTach}.
 */
@Service
@Transactional
public class ThuaTachServiceImpl implements ThuaTachService {

    private static final Logger log = LoggerFactory.getLogger(ThuaTachServiceImpl.class);

    private final ThuaTachRepository thuaTachRepository;

    private final ThuaTachMapper thuaTachMapper;

    public ThuaTachServiceImpl(ThuaTachRepository thuaTachRepository, ThuaTachMapper thuaTachMapper) {
        this.thuaTachRepository = thuaTachRepository;
        this.thuaTachMapper = thuaTachMapper;
    }

    @Override
    public ThuaTachDTO save(ThuaTachDTO thuaTachDTO) {
        log.debug("Request to save ThuaTach : {}", thuaTachDTO);
        ThuaTach thuaTach = thuaTachMapper.toEntity(thuaTachDTO);
        thuaTach = thuaTachRepository.save(thuaTach);
        return thuaTachMapper.toDto(thuaTach);
    }

    @Override
    public ThuaTachDTO update(ThuaTachDTO thuaTachDTO) {
        log.debug("Request to update ThuaTach : {}", thuaTachDTO);
        ThuaTach thuaTach = thuaTachMapper.toEntity(thuaTachDTO);
        thuaTach = thuaTachRepository.save(thuaTach);
        return thuaTachMapper.toDto(thuaTach);
    }

    @Override
    public Optional<ThuaTachDTO> partialUpdate(ThuaTachDTO thuaTachDTO) {
        log.debug("Request to partially update ThuaTach : {}", thuaTachDTO);

        return thuaTachRepository
            .findById(thuaTachDTO.getId())
            .map(existingThuaTach -> {
                thuaTachMapper.partialUpdate(existingThuaTach, thuaTachDTO);

                return existingThuaTach;
            })
            .map(thuaTachRepository::save)
            .map(thuaTachMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ThuaTachDTO> findOne(Long id) {
        log.debug("Request to get ThuaTach : {}", id);
        return thuaTachRepository.findById(id).map(thuaTachMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ThuaTach : {}", id);
        thuaTachRepository.deleteById(id);
    }
}
