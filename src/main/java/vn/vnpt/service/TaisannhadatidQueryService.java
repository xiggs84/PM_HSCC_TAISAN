package vn.vnpt.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;
import vn.vnpt.domain.*; // for static metamodels
import vn.vnpt.domain.Taisannhadatid;
import vn.vnpt.repository.TaisannhadatidRepository;
import vn.vnpt.service.criteria.TaisannhadatidCriteria;
import vn.vnpt.service.dto.TaisannhadatidDTO;
import vn.vnpt.service.mapper.TaisannhadatidMapper;

/**
 * Service for executing complex queries for {@link Taisannhadatid} entities in the database.
 * The main input is a {@link TaisannhadatidCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TaisannhadatidDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TaisannhadatidQueryService extends QueryService<Taisannhadatid> {

    private static final Logger log = LoggerFactory.getLogger(TaisannhadatidQueryService.class);

    private final TaisannhadatidRepository taisannhadatidRepository;

    private final TaisannhadatidMapper taisannhadatidMapper;

    public TaisannhadatidQueryService(TaisannhadatidRepository taisannhadatidRepository, TaisannhadatidMapper taisannhadatidMapper) {
        this.taisannhadatidRepository = taisannhadatidRepository;
        this.taisannhadatidMapper = taisannhadatidMapper;
    }

    /**
     * Return a {@link List} of {@link TaisannhadatidDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TaisannhadatidDTO> findByCriteria(TaisannhadatidCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Taisannhadatid> specification = createSpecification(criteria);
        return taisannhadatidMapper.toDto(taisannhadatidRepository.findAll(specification));
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TaisannhadatidCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Taisannhadatid> specification = createSpecification(criteria);
        return taisannhadatidRepository.count(specification);
    }

    /**
     * Function to convert {@link TaisannhadatidCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Taisannhadatid> createSpecification(TaisannhadatidCriteria criteria) {
        Specification<Taisannhadatid> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Taisannhadatid_.id));
            }
            if (criteria.getIdTaiSan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdTaiSan(), Taisannhadatid_.idTaiSan));
            }
            if (criteria.getThongTinTs() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThongTinTs(), Taisannhadatid_.thongTinTs));
            }
        }
        return specification;
    }
}
