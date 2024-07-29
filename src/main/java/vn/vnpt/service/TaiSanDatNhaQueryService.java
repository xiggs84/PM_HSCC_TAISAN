package vn.vnpt.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;
import vn.vnpt.domain.*; // for static metamodels
import vn.vnpt.domain.TaiSanDatNha;
import vn.vnpt.repository.TaiSanDatNhaRepository;
import vn.vnpt.service.criteria.TaiSanDatNhaCriteria;
import vn.vnpt.service.dto.TaiSanDatNhaDTO;
import vn.vnpt.service.mapper.TaiSanDatNhaMapper;

/**
 * Service for executing complex queries for {@link TaiSanDatNha} entities in the database.
 * The main input is a {@link TaiSanDatNhaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TaiSanDatNhaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TaiSanDatNhaQueryService extends QueryService<TaiSanDatNha> {

    private static final Logger log = LoggerFactory.getLogger(TaiSanDatNhaQueryService.class);

    private final TaiSanDatNhaRepository taiSanDatNhaRepository;

    private final TaiSanDatNhaMapper taiSanDatNhaMapper;

    public TaiSanDatNhaQueryService(TaiSanDatNhaRepository taiSanDatNhaRepository, TaiSanDatNhaMapper taiSanDatNhaMapper) {
        this.taiSanDatNhaRepository = taiSanDatNhaRepository;
        this.taiSanDatNhaMapper = taiSanDatNhaMapper;
    }

    /**
     * Return a {@link List} of {@link TaiSanDatNhaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TaiSanDatNhaDTO> findByCriteria(TaiSanDatNhaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TaiSanDatNha> specification = createSpecification(criteria);
        return taiSanDatNhaMapper.toDto(taiSanDatNhaRepository.findAll(specification));
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TaiSanDatNhaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TaiSanDatNha> specification = createSpecification(criteria);
        return taiSanDatNhaRepository.count(specification);
    }

    /**
     * Function to convert {@link TaiSanDatNhaCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<TaiSanDatNha> createSpecification(TaiSanDatNhaCriteria criteria) {
        Specification<TaiSanDatNha> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), TaiSanDatNha_.id));
            }
            if (criteria.getIdTaiSan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdTaiSan(), TaiSanDatNha_.idTaiSan));
            }
            if (criteria.getTenTaiSan() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenTaiSan(), TaiSanDatNha_.tenTaiSan));
            }
            if (criteria.getTrangThai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrangThai(), TaiSanDatNha_.trangThai));
            }
            if (criteria.getThongTinTs() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThongTinTs(), TaiSanDatNha_.thongTinTs));
            }
            if (criteria.getIdLoaiTs() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdLoaiTs(), TaiSanDatNha_.idLoaiTs));
            }
            if (criteria.getGhiChu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGhiChu(), TaiSanDatNha_.ghiChu));
            }
            if (criteria.getNgayThaoTac() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayThaoTac(), TaiSanDatNha_.ngayThaoTac));
            }
            if (criteria.getNguoiThaoTac() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNguoiThaoTac(), TaiSanDatNha_.nguoiThaoTac));
            }
            if (criteria.getIdDuongSu() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdDuongSu(), TaiSanDatNha_.idDuongSu));
            }
            if (criteria.getIdTsGoc() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdTsGoc(), TaiSanDatNha_.idTsGoc));
            }
            if (criteria.getMaTaiSan() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaTaiSan(), TaiSanDatNha_.maTaiSan));
            }
            if (criteria.getIdTinhTrang() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdTinhTrang(), TaiSanDatNha_.idTinhTrang));
            }
            if (criteria.getIdLoaiNganChan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdLoaiNganChan(), TaiSanDatNha_.idLoaiNganChan));
            }
            if (criteria.getNgayBdNganChan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayBdNganChan(), TaiSanDatNha_.ngayBdNganChan));
            }
            if (criteria.getNgayKtNganChan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayKtNganChan(), TaiSanDatNha_.ngayKtNganChan));
            }
            if (criteria.getIdMaster() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdMaster(), TaiSanDatNha_.idMaster));
            }
            if (criteria.getStrSearch() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStrSearch(), TaiSanDatNha_.strSearch));
            }
            if (criteria.getIdDonVi() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdDonVi(), TaiSanDatNha_.idDonVi));
            }
            if (criteria.getSoHsCv() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSoHsCv(), TaiSanDatNha_.soHsCv));
            }
            if (criteria.getSoCc() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSoCc(), TaiSanDatNha_.soCc));
            }
            if (criteria.getSoVaoSo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSoVaoSo(), TaiSanDatNha_.soVaoSo));
            }
            if (criteria.getMoTa() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMoTa(), TaiSanDatNha_.moTa));
            }
            if (criteria.getLoaiNganChan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLoaiNganChan(), TaiSanDatNha_.loaiNganChan));
            }
        }
        return specification;
    }
}
