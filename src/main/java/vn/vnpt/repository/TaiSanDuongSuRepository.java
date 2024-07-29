package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.TaiSanDuongSu;

/**
 * Spring Data JPA repository for the TaiSanDuongSu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaiSanDuongSuRepository extends JpaRepository<TaiSanDuongSu, Long>, JpaSpecificationExecutor<TaiSanDuongSu> {}
