package vn.vnpt.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;
import vn.vnpt.domain.*; // for static metamodels
import vn.vnpt.domain.ThuaTach;
import vn.vnpt.repository.ThuaTachRepository;
import vn.vnpt.service.criteria.ThuaTachCriteria;
import vn.vnpt.service.dto.ThuaTachDTO;
import vn.vnpt.service.mapper.ThuaTachMapper;

/**
 * Service for executing complex queries for {@link ThuaTach} entities in the database.
 * The main input is a {@link ThuaTachCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ThuaTachDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ThuaTachQueryService extends QueryService<ThuaTach> {

    private static final Logger log = LoggerFactory.getLogger(ThuaTachQueryService.class);

    private final ThuaTachRepository thuaTachRepository;

    private final ThuaTachMapper thuaTachMapper;

    public ThuaTachQueryService(ThuaTachRepository thuaTachRepository, ThuaTachMapper thuaTachMapper) {
        this.thuaTachRepository = thuaTachRepository;
        this.thuaTachMapper = thuaTachMapper;
    }

    /**
     * Return a {@link List} of {@link ThuaTachDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ThuaTachDTO> findByCriteria(ThuaTachCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ThuaTach> specification = createSpecification(criteria);
        return thuaTachMapper.toDto(thuaTachRepository.findAll(specification));
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ThuaTachCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ThuaTach> specification = createSpecification(criteria);
        return thuaTachRepository.count(specification);
    }

    /**
     * Function to convert {@link ThuaTachCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ThuaTach> createSpecification(ThuaTachCriteria criteria) {
        Specification<ThuaTach> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), ThuaTach_.id));
            }
            if (criteria.getIdThuaTach() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdThuaTach(), ThuaTach_.idThuaTach));
            }
            if (criteria.getIdTaiSan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdTaiSan(), ThuaTach_.idTaiSan));
            }
            if (criteria.getThongTinThuaTach() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThongTinThuaTach(), ThuaTach_.thongTinThuaTach));
            }
            if (criteria.getTrangThai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrangThai(), ThuaTach_.trangThai));
            }
        }
        return specification;
    }
}
