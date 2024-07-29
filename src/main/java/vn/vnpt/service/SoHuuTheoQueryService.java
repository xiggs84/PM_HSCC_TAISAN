package vn.vnpt.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;
import vn.vnpt.domain.*; // for static metamodels
import vn.vnpt.domain.SoHuuTheo;
import vn.vnpt.repository.SoHuuTheoRepository;
import vn.vnpt.service.criteria.SoHuuTheoCriteria;
import vn.vnpt.service.dto.SoHuuTheoDTO;
import vn.vnpt.service.mapper.SoHuuTheoMapper;

/**
 * Service for executing complex queries for {@link SoHuuTheo} entities in the database.
 * The main input is a {@link SoHuuTheoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SoHuuTheoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SoHuuTheoQueryService extends QueryService<SoHuuTheo> {

    private static final Logger log = LoggerFactory.getLogger(SoHuuTheoQueryService.class);

    private final SoHuuTheoRepository soHuuTheoRepository;

    private final SoHuuTheoMapper soHuuTheoMapper;

    public SoHuuTheoQueryService(SoHuuTheoRepository soHuuTheoRepository, SoHuuTheoMapper soHuuTheoMapper) {
        this.soHuuTheoRepository = soHuuTheoRepository;
        this.soHuuTheoMapper = soHuuTheoMapper;
    }

    /**
     * Return a {@link List} of {@link SoHuuTheoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SoHuuTheoDTO> findByCriteria(SoHuuTheoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SoHuuTheo> specification = createSpecification(criteria);
        return soHuuTheoMapper.toDto(soHuuTheoRepository.findAll(specification));
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SoHuuTheoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SoHuuTheo> specification = createSpecification(criteria);
        return soHuuTheoRepository.count(specification);
    }

    /**
     * Function to convert {@link SoHuuTheoCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<SoHuuTheo> createSpecification(SoHuuTheoCriteria criteria) {
        Specification<SoHuuTheo> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), SoHuuTheo_.id));
            }
            if (criteria.getIdSoHuu() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdSoHuu(), SoHuuTheo_.idSoHuu));
            }
            if (criteria.getDienGiai() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDienGiai(), SoHuuTheo_.dienGiai));
            }
            if (criteria.getTenGcn() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenGcn(), SoHuuTheo_.tenGcn));
            }
        }
        return specification;
    }
}
