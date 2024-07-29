package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.Taisannhadatid;

/**
 * Spring Data JPA repository for the Taisannhadatid entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaisannhadatidRepository extends JpaRepository<Taisannhadatid, Long>, JpaSpecificationExecutor<Taisannhadatid> {}
