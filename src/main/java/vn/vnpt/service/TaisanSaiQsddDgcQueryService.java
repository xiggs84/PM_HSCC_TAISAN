package vn.vnpt.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;
import vn.vnpt.domain.*; // for static metamodels
import vn.vnpt.domain.TaisanSaiQsddDgc;
import vn.vnpt.repository.TaisanSaiQsddDgcRepository;
import vn.vnpt.service.criteria.TaisanSaiQsddDgcCriteria;
import vn.vnpt.service.dto.TaisanSaiQsddDgcDTO;
import vn.vnpt.service.mapper.TaisanSaiQsddDgcMapper;

/**
 * Service for executing complex queries for {@link TaisanSaiQsddDgc} entities in the database.
 * The main input is a {@link TaisanSaiQsddDgcCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TaisanSaiQsddDgcDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TaisanSaiQsddDgcQueryService extends QueryService<TaisanSaiQsddDgc> {

    private static final Logger log = LoggerFactory.getLogger(TaisanSaiQsddDgcQueryService.class);

    private final TaisanSaiQsddDgcRepository taisanSaiQsddDgcRepository;

    private final TaisanSaiQsddDgcMapper taisanSaiQsddDgcMapper;

    public TaisanSaiQsddDgcQueryService(
        TaisanSaiQsddDgcRepository taisanSaiQsddDgcRepository,
        TaisanSaiQsddDgcMapper taisanSaiQsddDgcMapper
    ) {
        this.taisanSaiQsddDgcRepository = taisanSaiQsddDgcRepository;
        this.taisanSaiQsddDgcMapper = taisanSaiQsddDgcMapper;
    }

    /**
     * Return a {@link List} of {@link TaisanSaiQsddDgcDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TaisanSaiQsddDgcDTO> findByCriteria(TaisanSaiQsddDgcCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TaisanSaiQsddDgc> specification = createSpecification(criteria);
        return taisanSaiQsddDgcMapper.toDto(taisanSaiQsddDgcRepository.findAll(specification));
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TaisanSaiQsddDgcCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TaisanSaiQsddDgc> specification = createSpecification(criteria);
        return taisanSaiQsddDgcRepository.count(specification);
    }

    /**
     * Function to convert {@link TaisanSaiQsddDgcCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<TaisanSaiQsddDgc> createSpecification(TaisanSaiQsddDgcCriteria criteria) {
        Specification<TaisanSaiQsddDgc> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), TaisanSaiQsddDgc_.id));
            }
            if (criteria.getIdMaster() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdMaster(), TaisanSaiQsddDgc_.idMaster));
            }
            if (criteria.getNoiCapQsdd() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNoiCapQsdd(), TaisanSaiQsddDgc_.noiCapQsdd));
            }
        }
        return specification;
    }
}
