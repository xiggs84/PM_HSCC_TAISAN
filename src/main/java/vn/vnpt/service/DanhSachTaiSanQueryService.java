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
import vn.vnpt.domain.DanhSachTaiSan;
import vn.vnpt.repository.DanhSachTaiSanRepository;
import vn.vnpt.service.criteria.DanhSachTaiSanCriteria;
import vn.vnpt.service.dto.DanhSachTaiSanDTO;
import vn.vnpt.service.mapper.DanhSachTaiSanMapper;

/**
 * Service for executing complex queries for {@link DanhSachTaiSan} entities in the database.
 * The main input is a {@link DanhSachTaiSanCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DanhSachTaiSanDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DanhSachTaiSanQueryService extends QueryService<DanhSachTaiSan> {

    private static final Logger log = LoggerFactory.getLogger(DanhSachTaiSanQueryService.class);

    private final DanhSachTaiSanRepository danhSachTaiSanRepository;

    private final DanhSachTaiSanMapper danhSachTaiSanMapper;

    public DanhSachTaiSanQueryService(DanhSachTaiSanRepository danhSachTaiSanRepository, DanhSachTaiSanMapper danhSachTaiSanMapper) {
        this.danhSachTaiSanRepository = danhSachTaiSanRepository;
        this.danhSachTaiSanMapper = danhSachTaiSanMapper;
    }

    /**
     * Return a {@link List} of {@link DanhSachTaiSanDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DanhSachTaiSanDTO> findByCriteria(DanhSachTaiSanCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DanhSachTaiSan> specification = createSpecification(criteria);
        return danhSachTaiSanMapper.toDto(danhSachTaiSanRepository.findAll(specification));
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DanhSachTaiSanCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DanhSachTaiSan> specification = createSpecification(criteria);
        return danhSachTaiSanRepository.count(specification);
    }

    /**
     * Function to convert {@link DanhSachTaiSanCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DanhSachTaiSan> createSpecification(DanhSachTaiSanCriteria criteria) {
        Specification<DanhSachTaiSan> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DanhSachTaiSan_.id));
            }
            if (criteria.getIdTaiSan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdTaiSan(), DanhSachTaiSan_.idTaiSan));
            }
            if (criteria.getTenTaiSan() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenTaiSan(), DanhSachTaiSan_.tenTaiSan));
            }
            if (criteria.getTrangThai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrangThai(), DanhSachTaiSan_.trangThai));
            }
            if (criteria.getGhiChu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGhiChu(), DanhSachTaiSan_.ghiChu));
            }
            if (criteria.getNgayThaoTac() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayThaoTac(), DanhSachTaiSan_.ngayThaoTac));
            }
            if (criteria.getNguoiThaoTac() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNguoiThaoTac(), DanhSachTaiSan_.nguoiThaoTac));
            }
            if (criteria.getIdDuongSu() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdDuongSu(), DanhSachTaiSan_.idDuongSu));
            }
            if (criteria.getIdTsGoc() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdTsGoc(), DanhSachTaiSan_.idTsGoc));
            }
            if (criteria.getMaTaiSan() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaTaiSan(), DanhSachTaiSan_.maTaiSan));
            }
            if (criteria.getIdTinhTrang() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdTinhTrang(), DanhSachTaiSan_.idTinhTrang));
            }
            if (criteria.getIdLoaiNganChan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdLoaiNganChan(), DanhSachTaiSan_.idLoaiNganChan));
            }
            if (criteria.getNgayBdNganChan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayBdNganChan(), DanhSachTaiSan_.ngayBdNganChan));
            }
            if (criteria.getNgayKtNganChan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayKtNganChan(), DanhSachTaiSan_.ngayKtNganChan));
            }
            if (criteria.getIdMaster() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdMaster(), DanhSachTaiSan_.idMaster));
            }
            if (criteria.getStrSearch() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStrSearch(), DanhSachTaiSan_.strSearch));
            }
            if (criteria.getIdDonVi() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdDonVi(), DanhSachTaiSan_.idDonVi));
            }
            if (criteria.getSoHsCv() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSoHsCv(), DanhSachTaiSan_.soHsCv));
            }
            if (criteria.getSoCc() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSoCc(), DanhSachTaiSan_.soCc));
            }
            if (criteria.getSoVaoSo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSoVaoSo(), DanhSachTaiSan_.soVaoSo));
            }
            if (criteria.getMoTa() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMoTa(), DanhSachTaiSan_.moTa));
            }
            if (criteria.getLoaiNganChan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLoaiNganChan(), DanhSachTaiSan_.loaiNganChan));
            }
            if (criteria.getMaXa() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaXa(), DanhSachTaiSan_.maXa));
            }
            if (criteria.getIdLoaiTsId() != null) {
                specification = specification.and(
                    buildSpecification(
                        criteria.getIdLoaiTsId(),
                        root -> root.join(DanhSachTaiSan_.idLoaiTs, JoinType.LEFT).get(DanhMucLoaiTaiSan_.id)
                    )
                );
            }
        }
        return specification;
    }
}
