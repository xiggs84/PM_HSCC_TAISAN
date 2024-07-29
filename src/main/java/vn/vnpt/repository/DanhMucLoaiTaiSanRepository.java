package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DanhMucLoaiTaiSan;

/**
 * Spring Data JPA repository for the DanhMucLoaiTaiSan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DanhMucLoaiTaiSanRepository extends JpaRepository<DanhMucLoaiTaiSan, Long> {}
