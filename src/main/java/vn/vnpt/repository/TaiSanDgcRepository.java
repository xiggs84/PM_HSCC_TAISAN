package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.TaiSanDgc;

/**
 * Spring Data JPA repository for the TaiSanDgc entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaiSanDgcRepository extends JpaRepository<TaiSanDgc, Long> {}
