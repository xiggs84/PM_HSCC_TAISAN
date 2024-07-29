package vn.vnpt.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;
import vn.vnpt.domain.*; // for static metamodels
import vn.vnpt.domain.CauHinhThongTinLoaiTaiSan;
import vn.vnpt.repository.CauHinhThongTinLoaiTaiSanRepository;
import vn.vnpt.service.criteria.CauHinhThongTinLoaiTaiSanCriteria;
import vn.vnpt.service.dto.CauHinhThongTinLoaiTaiSanDTO;
import vn.vnpt.service.mapper.CauHinhThongTinLoaiTaiSanMapper;

/**
 * Service for executing complex queries for {@link CauHinhThongTinLoaiTaiSan} entities in the database.
 * The main input is a {@link CauHinhThongTinLoaiTaiSanCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CauHinhThongTinLoaiTaiSanDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CauHinhThongTinLoaiTaiSanQueryService extends QueryService<CauHinhThongTinLoaiTaiSan> {

    private static final Logger log = LoggerFactory.getLogger(CauHinhThongTinLoaiTaiSanQueryService.class);

    private final CauHinhThongTinLoaiTaiSanRepository cauHinhThongTinLoaiTaiSanRepository;

    private final CauHinhThongTinLoaiTaiSanMapper cauHinhThongTinLoaiTaiSanMapper;

    public CauHinhThongTinLoaiTaiSanQueryService(
        CauHinhThongTinLoaiTaiSanRepository cauHinhThongTinLoaiTaiSanRepository,
        CauHinhThongTinLoaiTaiSanMapper cauHinhThongTinLoaiTaiSanMapper
    ) {
        this.cauHinhThongTinLoaiTaiSanRepository = cauHinhThongTinLoaiTaiSanRepository;
        this.cauHinhThongTinLoaiTaiSanMapper = cauHinhThongTinLoaiTaiSanMapper;
    }

    /**
     * Return a {@link List} of {@link CauHinhThongTinLoaiTaiSanDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CauHinhThongTinLoaiTaiSanDTO> findByCriteria(CauHinhThongTinLoaiTaiSanCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CauHinhThongTinLoaiTaiSan> specification = createSpecification(criteria);
        return cauHinhThongTinLoaiTaiSanMapper.toDto(cauHinhThongTinLoaiTaiSanRepository.findAll(specification));
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CauHinhThongTinLoaiTaiSanCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CauHinhThongTinLoaiTaiSan> specification = createSpecification(criteria);
        return cauHinhThongTinLoaiTaiSanRepository.count(specification);
    }

    /**
     * Function to convert {@link CauHinhThongTinLoaiTaiSanCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CauHinhThongTinLoaiTaiSan> createSpecification(CauHinhThongTinLoaiTaiSanCriteria criteria) {
        Specification<CauHinhThongTinLoaiTaiSan> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CauHinhThongTinLoaiTaiSan_.id));
            }
            if (criteria.getIdCauHinh() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdCauHinh(), CauHinhThongTinLoaiTaiSan_.idCauHinh));
            }
            if (criteria.getNoiDung() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNoiDung(), CauHinhThongTinLoaiTaiSan_.noiDung));
            }
            if (criteria.getJavascript() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getJavascript(), CauHinhThongTinLoaiTaiSan_.javascript)
                );
            }
            if (criteria.getCss() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCss(), CauHinhThongTinLoaiTaiSan_.css));
            }
            if (criteria.getIdLoaiTs() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdLoaiTs(), CauHinhThongTinLoaiTaiSan_.idLoaiTs));
            }
            if (criteria.getIdDonVi() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdDonVi(), CauHinhThongTinLoaiTaiSan_.idDonVi));
            }
            if (criteria.getTrangThai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrangThai(), CauHinhThongTinLoaiTaiSan_.trangThai));
            }
            if (criteria.getXml() != null) {
                specification = specification.and(buildStringSpecification(criteria.getXml(), CauHinhThongTinLoaiTaiSan_.xml));
            }
        }
        return specification;
    }
}
