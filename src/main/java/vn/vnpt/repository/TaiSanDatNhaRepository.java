package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.TaiSanDatNha;

/**
 * Spring Data JPA repository for the TaiSanDatNha entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaiSanDatNhaRepository extends JpaRepository<TaiSanDatNha, Long>, JpaSpecificationExecutor<TaiSanDatNha> {}
