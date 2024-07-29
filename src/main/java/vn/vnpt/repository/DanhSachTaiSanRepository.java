package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DanhSachTaiSan;

/**
 * Spring Data JPA repository for the DanhSachTaiSan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DanhSachTaiSanRepository extends JpaRepository<DanhSachTaiSan, Long> {}
