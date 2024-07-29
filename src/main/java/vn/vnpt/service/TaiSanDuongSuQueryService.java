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
import vn.vnpt.domain.TaiSanDuongSu;
import vn.vnpt.repository.TaiSanDuongSuRepository;
import vn.vnpt.service.criteria.TaiSanDuongSuCriteria;
import vn.vnpt.service.dto.TaiSanDuongSuDTO;
import vn.vnpt.service.mapper.TaiSanDuongSuMapper;

/**
 * Service for executing complex queries for {@link TaiSanDuongSu} entities in the database.
 * The main input is a {@link TaiSanDuongSuCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TaiSanDuongSuDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TaiSanDuongSuQueryService extends QueryService<TaiSanDuongSu> {

    private static final Logger log = LoggerFactory.getLogger(TaiSanDuongSuQueryService.class);

    private final TaiSanDuongSuRepository taiSanDuongSuRepository;

    private final TaiSanDuongSuMapper taiSanDuongSuMapper;

    public TaiSanDuongSuQueryService(TaiSanDuongSuRepository taiSanDuongSuRepository, TaiSanDuongSuMapper taiSanDuongSuMapper) {
        this.taiSanDuongSuRepository = taiSanDuongSuRepository;
        this.taiSanDuongSuMapper = taiSanDuongSuMapper;
    }

    /**
     * Return a {@link List} of {@link TaiSanDuongSuDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TaiSanDuongSuDTO> findByCriteria(TaiSanDuongSuCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TaiSanDuongSu> specification = createSpecification(criteria);
        return taiSanDuongSuMapper.toDto(taiSanDuongSuRepository.findAll(specification));
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TaiSanDuongSuCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TaiSanDuongSu> specification = createSpecification(criteria);
        return taiSanDuongSuRepository.count(specification);
    }

    /**
     * Function to convert {@link TaiSanDuongSuCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<TaiSanDuongSu> createSpecification(TaiSanDuongSuCriteria criteria) {
        Specification<TaiSanDuongSu> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), TaiSanDuongSu_.id));
            }
            if (criteria.getTrangThai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrangThai(), TaiSanDuongSu_.trangThai));
            }
            if (criteria.getIdDuongSu() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdDuongSu(), TaiSanDuongSu_.idDuongSu));
            }
            if (criteria.getNgayThaoTac() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayThaoTac(), TaiSanDuongSu_.ngayThaoTac));
            }
            if (criteria.getIdLoaiHopDong() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdLoaiHopDong(), TaiSanDuongSu_.idLoaiHopDong));
            }
            if (criteria.getIdChungThuc() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdChungThuc(), TaiSanDuongSu_.idChungThuc));
            }
            if (criteria.getIdTaiSanId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getIdTaiSanId(), root -> root.join(TaiSanDuongSu_.idTaiSan, JoinType.LEFT).get(TaiSan_.id))
                );
            }
        }
        return specification;
    }
}
