package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.TaisanSaiQsddDgc;

/**
 * Spring Data JPA repository for the TaisanSaiQsddDgc entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaisanSaiQsddDgcRepository extends JpaRepository<TaisanSaiQsddDgc, Long> {}
