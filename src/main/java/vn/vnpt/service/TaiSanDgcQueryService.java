package vn.vnpt.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;
import vn.vnpt.domain.*; // for static metamodels
import vn.vnpt.domain.TaiSanDgc;
import vn.vnpt.repository.TaiSanDgcRepository;
import vn.vnpt.service.criteria.TaiSanDgcCriteria;
import vn.vnpt.service.dto.TaiSanDgcDTO;
import vn.vnpt.service.mapper.TaiSanDgcMapper;

/**
 * Service for executing complex queries for {@link TaiSanDgc} entities in the database.
 * The main input is a {@link TaiSanDgcCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TaiSanDgcDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TaiSanDgcQueryService extends QueryService<TaiSanDgc> {

    private static final Logger log = LoggerFactory.getLogger(TaiSanDgcQueryService.class);

    private final TaiSanDgcRepository taiSanDgcRepository;

    private final TaiSanDgcMapper taiSanDgcMapper;

    public TaiSanDgcQueryService(TaiSanDgcRepository taiSanDgcRepository, TaiSanDgcMapper taiSanDgcMapper) {
        this.taiSanDgcRepository = taiSanDgcRepository;
        this.taiSanDgcMapper = taiSanDgcMapper;
    }

    /**
     * Return a {@link List} of {@link TaiSanDgcDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TaiSanDgcDTO> findByCriteria(TaiSanDgcCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TaiSanDgc> specification = createSpecification(criteria);
        return taiSanDgcMapper.toDto(taiSanDgcRepository.findAll(specification));
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TaiSanDgcCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TaiSanDgc> specification = createSpecification(criteria);
        return taiSanDgcRepository.count(specification);
    }

    /**
     * Function to convert {@link TaiSanDgcCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<TaiSanDgc> createSpecification(TaiSanDgcCriteria criteria) {
        Specification<TaiSanDgc> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), TaiSanDgc_.id));
            }
            if (criteria.getIdTaiSan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdTaiSan(), TaiSanDgc_.idTaiSan));
            }
            if (criteria.getTenTaiSan() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenTaiSan(), TaiSanDgc_.tenTaiSan));
            }
            if (criteria.getTrangThai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrangThai(), TaiSanDgc_.trangThai));
            }
            if (criteria.getThongTinTs() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThongTinTs(), TaiSanDgc_.thongTinTs));
            }
            if (criteria.getIdLoaiTs() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdLoaiTs(), TaiSanDgc_.idLoaiTs));
            }
            if (criteria.getGhiChu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGhiChu(), TaiSanDgc_.ghiChu));
            }
            if (criteria.getNgayThaoTac() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayThaoTac(), TaiSanDgc_.ngayThaoTac));
            }
            if (criteria.getNguoiThaoTac() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNguoiThaoTac(), TaiSanDgc_.nguoiThaoTac));
            }
            if (criteria.getIdDuongSu() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdDuongSu(), TaiSanDgc_.idDuongSu));
            }
            if (criteria.getIdTsGoc() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdTsGoc(), TaiSanDgc_.idTsGoc));
            }
            if (criteria.getMaTaiSan() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaTaiSan(), TaiSanDgc_.maTaiSan));
            }
            if (criteria.getIdTinhTrang() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdTinhTrang(), TaiSanDgc_.idTinhTrang));
            }
            if (criteria.getIdLoaiNganChan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdLoaiNganChan(), TaiSanDgc_.idLoaiNganChan));
            }
            if (criteria.getNgayBdNganChan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayBdNganChan(), TaiSanDgc_.ngayBdNganChan));
            }
            if (criteria.getNgayKtNganChan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayKtNganChan(), TaiSanDgc_.ngayKtNganChan));
            }
            if (criteria.getIdMaster() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdMaster(), TaiSanDgc_.idMaster));
            }
            if (criteria.getStrSearch() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStrSearch(), TaiSanDgc_.strSearch));
            }
            if (criteria.getIdDonVi() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdDonVi(), TaiSanDgc_.idDonVi));
            }
            if (criteria.getSoHsCv() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSoHsCv(), TaiSanDgc_.soHsCv));
            }
            if (criteria.getSoCc() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSoCc(), TaiSanDgc_.soCc));
            }
            if (criteria.getSoVaoSo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSoVaoSo(), TaiSanDgc_.soVaoSo));
            }
            if (criteria.getMoTa() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMoTa(), TaiSanDgc_.moTa));
            }
        }
        return specification;
    }
}
