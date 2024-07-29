package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.CauHinhThongTinLoaiTaiSan;

/**
 * Spring Data JPA repository for the CauHinhThongTinLoaiTaiSan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CauHinhThongTinLoaiTaiSanRepository
    extends JpaRepository<CauHinhThongTinLoaiTaiSan, Long>, JpaSpecificationExecutor<CauHinhThongTinLoaiTaiSan> {}
