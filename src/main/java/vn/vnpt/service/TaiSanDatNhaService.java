package vn.vnpt.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.TaiSanDatNha;
import vn.vnpt.repository.TaiSanDatNhaRepository;
import vn.vnpt.service.dto.TaiSanDatNhaDTO;
import vn.vnpt.service.mapper.TaiSanDatNhaMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.TaiSanDatNha}.
 */
@Service
@Transactional
public class TaiSanDatNhaService {

    private static final Logger log = LoggerFactory.getLogger(TaiSanDatNhaService.class);

    private final TaiSanDatNhaRepository taiSanDatNhaRepository;

    private final TaiSanDatNhaMapper taiSanDatNhaMapper;

    public TaiSanDatNhaService(TaiSanDatNhaRepository taiSanDatNhaRepository, TaiSanDatNhaMapper taiSanDatNhaMapper) {
        this.taiSanDatNhaRepository = taiSanDatNhaRepository;
        this.taiSanDatNhaMapper = taiSanDatNhaMapper;
    }

    /**
     * Save a taiSanDatNha.
     *
     * @param taiSanDatNhaDTO the entity to save.
     * @return the persisted entity.
     */
    public TaiSanDatNhaDTO save(TaiSanDatNhaDTO taiSanDatNhaDTO) {
        log.debug("Request to save TaiSanDatNha : {}", taiSanDatNhaDTO);
        TaiSanDatNha taiSanDatNha = taiSanDatNhaMapper.toEntity(taiSanDatNhaDTO);
        taiSanDatNha = taiSanDatNhaRepository.save(taiSanDatNha);
        return taiSanDatNhaMapper.toDto(taiSanDatNha);
    }

    /**
     * Update a taiSanDatNha.
     *
     * @param taiSanDatNhaDTO the entity to save.
     * @return the persisted entity.
     */
    public TaiSanDatNhaDTO update(TaiSanDatNhaDTO taiSanDatNhaDTO) {
        log.debug("Request to update TaiSanDatNha : {}", taiSanDatNhaDTO);
        TaiSanDatNha taiSanDatNha = taiSanDatNhaMapper.toEntity(taiSanDatNhaDTO);
        taiSanDatNha = taiSanDatNhaRepository.save(taiSanDatNha);
        return taiSanDatNhaMapper.toDto(taiSanDatNha);
    }

    /**
     * Partially update a taiSanDatNha.
     *
     * @param taiSanDatNhaDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TaiSanDatNhaDTO> partialUpdate(TaiSanDatNhaDTO taiSanDatNhaDTO) {
        log.debug("Request to partially update TaiSanDatNha : {}", taiSanDatNhaDTO);

        return taiSanDatNhaRepository
            .findById(taiSanDatNhaDTO.getId())
            .map(existingTaiSanDatNha -> {
                taiSanDatNhaMapper.partialUpdate(existingTaiSanDatNha, taiSanDatNhaDTO);

                return existingTaiSanDatNha;
            })
            .map(taiSanDatNhaRepository::save)
            .map(taiSanDatNhaMapper::toDto);
    }

    /**
     * Get all the taiSanDatNhas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TaiSanDatNhaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TaiSanDatNhas");
        return taiSanDatNhaRepository.findAll(pageable).map(taiSanDatNhaMapper::toDto);
    }

    /**
     * Get one taiSanDatNha by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TaiSanDatNhaDTO> findOne(Long id) {
        log.debug("Request to get TaiSanDatNha : {}", id);
        return taiSanDatNhaRepository.findById(id).map(taiSanDatNhaMapper::toDto);
    }

    /**
     * Delete the taiSanDatNha by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TaiSanDatNha : {}", id);
        taiSanDatNhaRepository.deleteById(id);
    }
}
