package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.SoHuuTheo;

/**
 * Spring Data JPA repository for the SoHuuTheo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SoHuuTheoRepository extends JpaRepository<SoHuuTheo, Long> {}
