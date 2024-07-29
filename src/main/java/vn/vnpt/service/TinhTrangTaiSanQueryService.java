package vn.vnpt.service;

import jakarta.persistence.criteria.JoinType;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;
import vn.vnpt.domain.*; // for static metamodels
import vn.vnpt.domain.TinhTrangTaiSan;
import vn.vnpt.repository.TinhTrangTaiSanRepository;
import vn.vnpt.service.criteria.TinhTrangTaiSanCriteria;
import vn.vnpt.service.dto.TinhTrangTaiSanDTO;
import vn.vnpt.service.mapper.TinhTrangTaiSanMapper;

/**
 * Service for executing complex queries for {@link TinhTrangTaiSan} entities in the database.
 * The main input is a {@link TinhTrangTaiSanCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TinhTrangTaiSanDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TinhTrangTaiSanQueryService extends QueryService<TinhTrangTaiSan> {

    private static final Logger log = LoggerFactory.getLogger(TinhTrangTaiSanQueryService.class);

    private final TinhTrangTaiSanRepository tinhTrangTaiSanRepository;

    private final TinhTrangTaiSanMapper tinhTrangTaiSanMapper;

    public TinhTrangTaiSanQueryService(TinhTrangTaiSanRepository tinhTrangTaiSanRepository, TinhTrangTaiSanMapper tinhTrangTaiSanMapper) {
        this.tinhTrangTaiSanRepository = tinhTrangTaiSanRepository;
        this.tinhTrangTaiSanMapper = tinhTrangTaiSanMapper;
    }

    /**
     * Return a {@link List} of {@link TinhTrangTaiSanDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TinhTrangTaiSanDTO> findByCriteria(TinhTrangTaiSanCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TinhTrangTaiSan> specification = createSpecification(criteria);
        return tinhTrangTaiSanMapper.toDto(tinhTrangTaiSanRepository.findAll(specification));
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TinhTrangTaiSanCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TinhTrangTaiSan> specification = createSpecification(criteria);
        return tinhTrangTaiSanRepository.count(specification);
    }

    /**
     * Function to convert {@link TinhTrangTaiSanCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<TinhTrangTaiSan> createSpecification(TinhTrangTaiSanCriteria criteria) {
        Specification<TinhTrangTaiSan> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), TinhTrangTaiSan_.id));
            }
            if (criteria.getIdTinhTrang() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdTinhTrang(), TinhTrangTaiSan_.idTinhTrang));
            }
            if (criteria.getDienGiai() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDienGiai(), TinhTrangTaiSan_.dienGiai));
            }
            if (criteria.getTrangThai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrangThai(), TinhTrangTaiSan_.trangThai));
            }
            if (criteria.getTaiSanId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getTaiSanId(), root -> root.join(TinhTrangTaiSan_.taiSans, JoinType.LEFT).get(TaiSan_.id))
                );
            }
        }
        return specification;
    }
}
