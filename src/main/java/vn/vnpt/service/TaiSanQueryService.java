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
import vn.vnpt.domain.TaiSan;
import vn.vnpt.repository.TaiSanRepository;
import vn.vnpt.service.criteria.TaiSanCriteria;
import vn.vnpt.service.dto.TaiSanDTO;
import vn.vnpt.service.mapper.TaiSanMapper;

/**
 * Service for executing complex queries for {@link TaiSan} entities in the database.
 * The main input is a {@link TaiSanCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TaiSanDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TaiSanQueryService extends QueryService<TaiSan> {

    private static final Logger log = LoggerFactory.getLogger(TaiSanQueryService.class);

    private final TaiSanRepository taiSanRepository;

    private final TaiSanMapper taiSanMapper;

    public TaiSanQueryService(TaiSanRepository taiSanRepository, TaiSanMapper taiSanMapper) {
        this.taiSanRepository = taiSanRepository;
        this.taiSanMapper = taiSanMapper;
    }

    /**
     * Return a {@link List} of {@link TaiSanDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TaiSanDTO> findByCriteria(TaiSanCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TaiSan> specification = createSpecification(criteria);
        return taiSanMapper.toDto(taiSanRepository.fetchBagRelationships(taiSanRepository.findAll(specification)));
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TaiSanCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TaiSan> specification = createSpecification(criteria);
        return taiSanRepository.count(specification);
    }

    /**
     * Function to convert {@link TaiSanCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<TaiSan> createSpecification(TaiSanCriteria criteria) {
        Specification<TaiSan> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), TaiSan_.id));
            }
            if (criteria.getIdTaiSan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdTaiSan(), TaiSan_.idTaiSan));
            }
            if (criteria.getTenTaiSan() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenTaiSan(), TaiSan_.tenTaiSan));
            }
            if (criteria.getTrangThai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrangThai(), TaiSan_.trangThai));
            }
            if (criteria.getThongTinTs() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThongTinTs(), TaiSan_.thongTinTs));
            }
            if (criteria.getGhiChu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGhiChu(), TaiSan_.ghiChu));
            }
            if (criteria.getNgayThaoTac() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayThaoTac(), TaiSan_.ngayThaoTac));
            }
            if (criteria.getNguoiThaoTac() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNguoiThaoTac(), TaiSan_.nguoiThaoTac));
            }
            if (criteria.getIdDuongSu() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdDuongSu(), TaiSan_.idDuongSu));
            }
            if (criteria.getIdTsGoc() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdTsGoc(), TaiSan_.idTsGoc));
            }
            if (criteria.getMaTaiSan() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaTaiSan(), TaiSan_.maTaiSan));
            }
            if (criteria.getIdLoaiNganChan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdLoaiNganChan(), TaiSan_.idLoaiNganChan));
            }
            if (criteria.getNgayBdNganChan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayBdNganChan(), TaiSan_.ngayBdNganChan));
            }
            if (criteria.getNgayKtNganChan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayKtNganChan(), TaiSan_.ngayKtNganChan));
            }
            if (criteria.getIdMaster() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdMaster(), TaiSan_.idMaster));
            }
            if (criteria.getStrSearch() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStrSearch(), TaiSan_.strSearch));
            }
            if (criteria.getIdDonVi() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdDonVi(), TaiSan_.idDonVi));
            }
            if (criteria.getSoHsCv() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSoHsCv(), TaiSan_.soHsCv));
            }
            if (criteria.getSoCc() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSoCc(), TaiSan_.soCc));
            }
            if (criteria.getSoVaoSo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSoVaoSo(), TaiSan_.soVaoSo));
            }
            if (criteria.getMoTa() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMoTa(), TaiSan_.moTa));
            }
            if (criteria.getLoaiNganChan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLoaiNganChan(), TaiSan_.loaiNganChan));
            }
            if (criteria.getSyncStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSyncStatus(), TaiSan_.syncStatus));
            }
            if (criteria.getIdTsGocId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getIdTsGocId(), root -> root.join(TaiSan_.idTsGocs, JoinType.LEFT).get(TaiSan_.id))
                );
            }
            if (criteria.getIdLoaiTsId() != null) {
                specification = specification.and(
                    buildSpecification(
                        criteria.getIdLoaiTsId(),
                        root -> root.join(TaiSan_.idLoaiTs, JoinType.LEFT).get(DanhMucLoaiTaiSan_.id)
                    )
                );
            }
            if (criteria.getIdTinhTrangId() != null) {
                specification = specification.and(
                    buildSpecification(
                        criteria.getIdTinhTrangId(),
                        root -> root.join(TaiSan_.idTinhTrang, JoinType.LEFT).get(TinhTrangTaiSan_.id)
                    )
                );
            }
            if (criteria.getTaiSanId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getTaiSanId(), root -> root.join(TaiSan_.taiSans, JoinType.LEFT).get(TaiSan_.id))
                );
            }
            if (criteria.getTaiSanDuongSuId() != null) {
                specification = specification.and(
                    buildSpecification(
                        criteria.getTaiSanDuongSuId(),
                        root -> root.join(TaiSan_.taiSanDuongSu, JoinType.LEFT).get(TaiSanDuongSu_.id)
                    )
                );
            }
        }
        return specification;
    }
}
