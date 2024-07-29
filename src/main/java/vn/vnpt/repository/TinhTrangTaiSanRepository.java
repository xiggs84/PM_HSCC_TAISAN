package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.TinhTrangTaiSan;

/**
 * Spring Data JPA repository for the TinhTrangTaiSan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TinhTrangTaiSanRepository extends JpaRepository<TinhTrangTaiSan, Long>, JpaSpecificationExecutor<TinhTrangTaiSan> {}
