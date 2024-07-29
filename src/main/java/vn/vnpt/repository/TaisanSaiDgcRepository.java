package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.TaisanSaiDgc;

/**
 * Spring Data JPA repository for the TaisanSaiDgc entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaisanSaiDgcRepository extends JpaRepository<TaisanSaiDgc, Long>, JpaSpecificationExecutor<TaisanSaiDgc> {}
