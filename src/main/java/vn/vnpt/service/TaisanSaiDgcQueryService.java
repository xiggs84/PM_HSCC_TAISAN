package vn.vnpt.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;
import vn.vnpt.domain.*; // for static metamodels
import vn.vnpt.domain.TaisanSaiDgc;
import vn.vnpt.repository.TaisanSaiDgcRepository;
import vn.vnpt.service.criteria.TaisanSaiDgcCriteria;
import vn.vnpt.service.dto.TaisanSaiDgcDTO;
import vn.vnpt.service.mapper.TaisanSaiDgcMapper;

/**
 * Service for executing complex queries for {@link TaisanSaiDgc} entities in the database.
 * The main input is a {@link TaisanSaiDgcCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TaisanSaiDgcDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TaisanSaiDgcQueryService extends QueryService<TaisanSaiDgc> {

    private static final Logger log = LoggerFactory.getLogger(TaisanSaiDgcQueryService.class);

    private final TaisanSaiDgcRepository taisanSaiDgcRepository;

    private final TaisanSaiDgcMapper taisanSaiDgcMapper;

    public TaisanSaiDgcQueryService(TaisanSaiDgcRepository taisanSaiDgcRepository, TaisanSaiDgcMapper taisanSaiDgcMapper) {
        this.taisanSaiDgcRepository = taisanSaiDgcRepository;
        this.taisanSaiDgcMapper = taisanSaiDgcMapper;
    }

    /**
     * Return a {@link List} of {@link TaisanSaiDgcDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TaisanSaiDgcDTO> findByCriteria(TaisanSaiDgcCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TaisanSaiDgc> specification = createSpecification(criteria);
        return taisanSaiDgcMapper.toDto(taisanSaiDgcRepository.findAll(specification));
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TaisanSaiDgcCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TaisanSaiDgc> specification = createSpecification(criteria);
        return taisanSaiDgcRepository.count(specification);
    }

    /**
     * Function to convert {@link TaisanSaiDgcCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<TaisanSaiDgc> createSpecification(TaisanSaiDgcCriteria criteria) {
        Specification<TaisanSaiDgc> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), TaisanSaiDgc_.id));
            }
            if (criteria.getIdMaster() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdMaster(), TaisanSaiDgc_.idMaster));
            }
            if (criteria.getThongTinTs() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThongTinTs(), TaisanSaiDgc_.thongTinTs));
            }
            if (criteria.getThongTinTsDung() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThongTinTsDung(), TaisanSaiDgc_.thongTinTsDung));
            }
        }
        return specification;
    }
}
